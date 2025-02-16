package com.gss.gss_springboot.services;

import java.util.List;
import java.util.Optional;

import com.gss.gss_springboot.models.User;

public interface UserService {
    User createUser(User user);
    Optional<User> readUserById(Long id);
    List<User> readAllUsers();
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    void updatePassword(Long id, String oldPassword, String newPassword);
    boolean checkIfUsernameExists(String username);
}
