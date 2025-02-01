package com.base.user.controller;


import com.base.user.dto.UserResponseDTO;
import com.base.user.service.UserService;
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

    @Autowired
    public AdminController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/users/{id}/detail")
    public ResponseEntity<UserResponseDTO> getUserDetail(@PathVariable("id")Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

}

