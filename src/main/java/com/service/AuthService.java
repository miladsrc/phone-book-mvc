package com.service;

import com.beans.User;
import com.repository.RoleRepository;
import com.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public String Register(User user) {

        //check if username exists in database
        if(userRepository.existsBy(user.getUsername())) {
            throw new TodoAPIException(HttpStatus.BAD_REQUEST, "Username already exists!");
        }

        //check if email exists in database
        if(userRepository.existsByEmail(registerDTO.getEmail())){
            throw new TodoAPIException(HttpStatus.BAD_REQUEST, "Email already exists!");
        }

        User user = User.builder()
                .username(registerDTO.getUsername())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .email(registerDTO.getEmail())
                .name(registerDTO.getName())
                .build();


        Set<Role> role = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        role.add(userRole);

        user.setRoles(role);

        userRepository.save(user);

        return "User Registered Successfully !";
    }


    public Optional<User> authenticateUser(String username, String password) {
        return userRepository.findByUsername(username);
    }
}
