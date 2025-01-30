package com.base.database.admin.controller;


import com.base.database.admin.util.service.AdminService;
import com.base.database.security.model.service.JwtService;
import com.base.database.user.util.dto.UserResponseDTO;
import com.base.database.user.util.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@DependsOn("adminRepository")
public class AdminController {

//    private final AdminService adminService;
//    private final UserService userService;
//    private final JwtService jwtService;

//    @Autowired
//    public AdminController(AdminService adminService, UserService userService, JwtService jwtService) {
//        this.adminService = adminService;
//        this.userService = userService;
//        this.jwtService = jwtService;
//    }
//
//    @GetMapping("/users")
//    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
//        return ResponseEntity.ok(userService.getAllUsers());
//    }

    //the association is eager then contacts must fetch with dto itself
//    @GetMapping("/users/{id}/detail")
//    public ResponseEntity<UserResponseDTO> getUserDetail(@PathVariable("id")Long userId) {
//        return ResponseEntity.ok(userService.getUserById(userId));
//    }

//    @DeleteMapping("/users/{id}/delete")
//    public ResponseEntity<Void> deleteUserById(@PathVariable("id") Long userId) {
//        userService.deleteUser(userId);
//        return ResponseEntity.noContent().build();
//    }

    // Extract user ID from token
//    private Long extractUserIdFromToken(String token) {
//        if (token != null && token.startsWith("Bearer ")) {
//            token = token.substring(7);
//        }
//        return jwtService.extractUserId(token);
//    }


    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("test admin ok!");
    }
}

