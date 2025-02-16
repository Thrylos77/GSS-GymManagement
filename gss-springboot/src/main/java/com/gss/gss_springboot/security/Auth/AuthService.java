package com.gss.gss_springboot.security.Auth;

public interface AuthService {
    LoginResponse authenticate(String username, String password);
}
