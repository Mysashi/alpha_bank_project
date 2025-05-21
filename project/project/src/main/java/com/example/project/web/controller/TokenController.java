package com.example.project.web.controller;

import com.example.project.domain.entity.Token;
import com.example.project.domain.jwt.JwtTokenProvider;
import com.example.project.domain.repository.TokenRepository;
import com.example.project.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.CachingUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TokenController {

    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    private final CustomUserDetailsService customUserDetailsService;

    public TokenController(JwtTokenProvider tokenProvider, AuthenticationManager authenticationManager, TokenRepository tokenRepository, CustomUserDetailsService customUserDetailsService) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.tokenRepository = tokenRepository;
        this.customUserDetailsService = customUserDetailsService;
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@RequestBody String refreshToken) {
        try {
            // 1. Валидация refresh-токена
            if (!tokenProvider.validateToken(refreshToken)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
            }

            // 2. Получение username из токена
            String username = tokenProvider.getUsernameFromJWT(refreshToken);

            // 3. Загрузка UserDetails
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

            // 4. Проверка существования токена в базе
            if (!tokenRepository.existsByToken(refreshToken)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token not found in database");
            }

            // 5. Создание новой аутентификации
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());

            // 6. Генерация новых токенов
            String newAccessToken = tokenProvider.generateToken(authentication);
            String newRefreshToken = tokenProvider.generateRefreshToken(authentication);

            // 7. Обновление токена в базе
            Token storedToken = tokenRepository.findByToken(refreshToken);
            storedToken.setToken(newRefreshToken);
            tokenRepository.save(storedToken);

            // 8. Формирование ответа
            Map<String, String> response = new HashMap<>();
            response.put("accessToken", newAccessToken);
            response.put("refreshToken", newRefreshToken);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token refresh failed: " + e.getMessage());
        }
    }
}


