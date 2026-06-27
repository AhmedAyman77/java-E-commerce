package com.example.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.abstracts.AuthService;
import com.example.ecommerce.dtos.LoginUser;
import com.example.ecommerce.dtos.SignupUser;
import com.example.ecommerce.share.GlobalResponse;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<GlobalResponse<String>> signup(@Valid @RequestBody SignupUser user) {
        authService.signUp(user);

        return ResponseEntity.status(201)
        .body(
            new GlobalResponse<>("User registered successfully")
        );
    }
    

    @PostMapping("/login")
    public ResponseEntity<GlobalResponse<String>> login(@Valid @RequestBody LoginUser user) {
        String token = authService.login(user);
        return ResponseEntity.ok(new GlobalResponse<>(token));
    }
}
