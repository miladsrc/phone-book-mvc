package com.base.database.user.controller;


import com.base.database.user.util.dto.UserDto;
import com.base.database.user.util.service.UserService;
import com.base.database.user.util.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        Optional<User> user = userService.findByUsername(username);
        return ResponseEntity.ok(user.orElse(null));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
        User user = userService.save(userDto);
        return ResponseEntity.status(201).body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}

