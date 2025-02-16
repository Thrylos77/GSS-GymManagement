package com.gss.gss_springboot.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gss.gss_springboot.models.User;
import com.gss.gss_springboot.repositories.UserRepository;
import com.gss.gss_springboot.services.UserService;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final  BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean checkIfUsernameExists(String username) {
        return userRepository.existsByUsername(username);
    }
    
    // CREATE
    @Override
    public User createUser(User user){
        // ENDOE PASSWORD BEFORE SAVE USER
        if (checkIfUsernameExists(user.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // READ
    @Override
    public Optional<User> readUserById(Long id){
        return userRepository.findById(id);
    }
    @Override
    public List<User> readAllUsers(){
        return userRepository.findAll();
    }

    // UPDATE
    @Override
    public User updateUser(Long id, User user){
        return userRepository.findById(id).map(existingUser ->{
            existingUser.setFirstname(user.getFirstname());
            existingUser.setLastname(user.getLastname());
            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());

            return userRepository.save(existingUser);
        }).orElseThrow(()-> new RuntimeException("User not found with id : " +id));
    }
    @Override
    public void updatePassword(Long id, String oldPassword, String newPassword){
        userRepository.findById(id).map(user -> {
            if(!passwordEncoder.matches(oldPassword, user.getPassword())){
                throw new RuntimeException("Old password does not match!");
            }
            user.setPassword(passwordEncoder.encode(newPassword));
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    // DELETE
    @Override
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
