package com.service;

import com.beans.User;
import com.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String username, String password) {

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole("USER");
        return userRepository.save(user);

    }

    public Optional<User> authenticateUser(String username, String password) {

        return userRepository.findByUsername(username);

    }
}
