package com.base.database.user.controller;


import com.base.database.security.service.JwtService;
import com.base.database.user.dto.UserResponseDTO;
import com.base.database.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@DependsOn("userRepository")
public class AdminController {

    private final UserService userService;
    private final JwtService jwtService;

    @Autowired
    public AdminController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/users/{id}/detail")
    public ResponseEntity<UserResponseDTO> getUserDetail(@PathVariable("id")Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    private Long extractUserIdFromToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return jwtService.extractUserId(token);
    }

}

