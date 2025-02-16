package com.gss.gss_springboot.security.Auth;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gss.gss_springboot.models.User;
import com.gss.gss_springboot.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public LoginResponse authenticate(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                // Authentification réussie : renvoie le username et le role
                return new LoginResponse(user.getIdUser(), user.getRole());
            } else {
                throw new RuntimeException("Mot de passe invalide");
            }
        } else {
            throw new RuntimeException("Utilisateur non trouvé avec le nom : " + username);
        }
    }
}
