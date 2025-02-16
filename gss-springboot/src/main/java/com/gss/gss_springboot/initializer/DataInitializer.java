package com.gss.gss_springboot.initializer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.gss.gss_springboot.models.Role;
import com.gss.gss_springboot.models.User;
import com.gss.gss_springboot.repositories.UserRepository;
import com.gss.gss_springboot.services.UserService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner{
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            User defaultAdmin = new User();
            defaultAdmin.setFirstname("Gss-Admin");
            defaultAdmin.setLastname("GSS-ADMIN");
            defaultAdmin.setUsername("admin");
            defaultAdmin.setPassword("admin"); // Vous pouvez encoder ce mot de passe si nécessaire
            defaultAdmin.setEmail("admin@gss.com");
            defaultAdmin.setRole(Role.ADMIN);

            userService.createUser(defaultAdmin);
            System.out.println("Admin par défaut créé");
        } else {
            System.out.println("Admin déjà existant, aucune action n'est effectuée.");
        }
    }
}
