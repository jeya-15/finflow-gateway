package com.finflow.gateway.auth.dto;

import com.finflow.gateway.user.enums.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterResponse {

    private Long userId;
    private String fullName;
    private String email;
    private String role;
}