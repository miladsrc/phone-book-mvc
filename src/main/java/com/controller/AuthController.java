package com.controller;

import com.beans.User;
import com.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

//    @PostMapping(value = "/login")
//    public ResponseEntity<?> login(@RequestBody User user) {
//        Optional<User> user = authService.login(username, password);
//        if (user.isPresent()) {
//            return ResponseEntity.ok(user.get());
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//    }
//
//    @PostMapping(value = "/register")
//    public ResponseEntity<?> register(@RequestBody User user) {
//        if (user.getUsername() == null || user.getUsername().isEmpty() ||
//                user.getPassword() == null || user.getPassword().isEmpty()) {
//            return ResponseEntity.badRequest().body("Username or password cannot be empty");
//        }
//        User registerUser = User.builder()
//                .username(user.getUsername())
//                .password(user.getPassword())
//                .build();
//
//        User success = authService.register(registerUser);
//        return ResponseEntity.ok(success);
//    }

}


