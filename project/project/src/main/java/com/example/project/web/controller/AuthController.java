package com.example.project.web.controller;

import com.example.project.domain.entity.Token;
import com.example.project.domain.entity.User;
import com.example.project.domain.jwt.JwtTokenProvider;
import com.example.project.domain.repository.TokenRepository;
import com.example.project.domain.repository.UserRepository;
import com.example.project.service.CustomUserDetails;
import com.example.project.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;

    @Autowired
    public AuthController(UserService userService, PasswordEncoder passwordEncoder,
                          JwtTokenProvider tokenProvider, AuthenticationManager authenticationManager,
                          TokenRepository tokenRepository,
                          UserRepository userRepository) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("api/auth/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            // Сохраняем оригинальный пароль перед кодированием
            String rawPassword = user.getPassword();

            // Сохраняем пользователя с закодированным паролем
            try {
                userService.addUser(user);
            }
            catch (DataIntegrityViolationException e) {
                return ResponseEntity.ok("User already exists!");
            }

            // Аутентифицируем с оригинальным (незакодированным) паролем
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            rawPassword  // Используем оригинальный пароль, а не закодированный
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Генерируем токены
            String jwtAccess = tokenProvider.generateToken(authentication);
            String refreshToken = tokenProvider.generateRefreshToken(authentication);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "User registered and logged in successfully!");
            response.put("username", user.getUsername());
            response.put("accessToken", jwtAccess);
            response.put("refreshToken", refreshToken);
            response.put("authorities", authentication.getAuthorities().stream()
                    .map(Object::toString)
                    .collect(Collectors.toList()));

            if (!tokenRepository.existsByToken(refreshToken)) {
                Token token = new Token(refreshToken, user);
                tokenRepository.save(token);
            }

            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Registration successful but auto-login failed. Please login manually.",
                    HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Registration failed: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("api/auth/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwtAccess = tokenProvider.generateToken(authentication);
            String refreshToken = tokenProvider.generateRefreshToken(authentication);

            CustomUserDetails currentUser = (CustomUserDetails)authentication.getPrincipal();
            User user1 = userRepository.findByUsername(currentUser.getUsername()).get();
            Token token = tokenRepository.findByUserId(user1.getId()).get();
            token.setToken(refreshToken);
            tokenRepository.save(token);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "User logged in successfully!");
            response.put("username", user.getUsername());
            response.put("accessToken", jwtAccess);
            response.put("refreshToken", refreshToken);
            response.put("authorities", authentication.getAuthorities().stream()
                    .map(Object::toString)
                    .collect(Collectors.toList()));

            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred during login", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
