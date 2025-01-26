package com.service;

import com.beans.Role;
import com.beans.User;
import com.repository.RoleRepository;
import com.repository.UserRepository;
import exception.ContactAPIException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthService {

    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository) {
    }

    //
//    public String register(User user) {
//
//        if(userRepository.existsByUsername(user.getUsername())) {
//            throw new ContactAPIException(HttpStatus.BAD_REQUEST, "Username already exists!");
//        }
//        User localUser = User.builder()
//                .username(user.getUsername())
//                .password(passwordEncoder.encode(user.getPassword()))
//                .contacts(user.getContacts())
//                .build();
//        //critical
//        Set<Role> role = new HashSet<>();
//        Role userRole = roleRepository.findByName("USER").get();
//        role.add(userRole);
//        user.setRoles(role);
//        userRepository.save(user);
//        return "User Registered Successfully !";
//
//    }
//
    public String login(User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "user logged successfully !";
        } catch (BadCredentialsException e) {
            return "Invalid credentials";
        }
    }
//
//    public Optional<User> authenticateUser(String username, String password) {
//        return userRepository.findByUsername(username);
//    }
}
