package com.controller;


import com.beans.User;
import com.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return adminService.findAllUsers();
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        Optional<User> user = adminService.findUserByUsername(username);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public Optional<User> getUsersByUsernameLike(@RequestParam String username) {
        return adminService.findUserByUsername(username);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = adminService.saveUser(user);
        return ResponseEntity.status(201).body(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        Optional<User> existingUser = adminService.findUserByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            user.setId(id);
            User updatedUser = adminService.saveUser(user);
            return ResponseEntity.ok(updatedUser);  // 200 OK
        }
        return ResponseEntity.notFound().build();  // 404 Not Found
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<User> existingUser = adminService.findUserByUsername(id.toString());
        if (existingUser.isPresent()) {
            adminService.deleteUser(id);
            return ResponseEntity.noContent().build();  // 204 No Content
        }
        return ResponseEntity.notFound().build();  // 404 Not Found
    }

}
