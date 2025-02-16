package com.gss.gss_springboot.security.Auth;

import com.gss.gss_springboot.models.Role;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private Long id;
    private Role role;
}
