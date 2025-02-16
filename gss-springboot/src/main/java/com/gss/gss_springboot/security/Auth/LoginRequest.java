package com.gss.gss_springboot.security.Auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
