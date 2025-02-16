package com.gss.gss_springboot.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gss.gss_springboot.models.User;
import com.gss.gss_springboot.services.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/gss/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@Validated @RequestBody User user){
        if (userService.checkIfUsernameExists(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
        }

        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }

    @GetMapping("/readUser/{id}")
    public ResponseEntity<User> readUserById(@PathVariable Long id){
        Optional<User> userOpt = userService.readUserById(id);
        return userOpt.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/listUsers")
    public ResponseEntity<List<User>> readAllUsers(){
        List<User> userOpt = userService.readAllUsers();
        return ResponseEntity.ok(userOpt);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/modifyUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Validated @RequestBody User user){
        try{
            User updateUser = userService.updateUser(id, user);
            return ResponseEntity.ok(updateUser);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updatePassword/{id}")
    public ResponseEntity<Void> updatePassword(
            @PathVariable Long id,
            @RequestParam String oldPassword,
            @RequestParam String newPassword) {
        userService.updatePassword(id, oldPassword, newPassword);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
