package com.finflow.gateway.auth.controller;

import com.finflow.gateway.auth.dto.LoginRequest;
import com.finflow.gateway.auth.dto.LoginResponse;
import com.finflow.gateway.auth.dto.RegisterRequest;
import com.finflow.gateway.auth.dto.RegisterResponse;
import com.finflow.gateway.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(
            @Valid @RequestBody RegisterRequest request
    ) {
        RegisterResponse response = authService.registerCustomer(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}