package com.example.project.web.controller;

import com.example.project.domain.entity.User;
import com.example.project.service.CustomUserDetails;
import com.example.project.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/me")
    public ResponseEntity<CustomUserDetails> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails currentUser = (CustomUserDetails)authentication.getPrincipal();

        System.out.println(currentUser);

        return ResponseEntity.ok(currentUser);
    }

}

