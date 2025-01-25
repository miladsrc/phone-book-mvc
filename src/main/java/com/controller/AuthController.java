package com.controller;

import com.beans.User;
import com.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        Optional<User> user = authService.authenticateUser(username, password);
        if (user.isPresent()) {
            return "redirect:/user/contacts";
        }
        return "login?error=true";
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return "register?error=true";
        }
        User success = authService.registerUser(username, password);
        if (success.getId() != null) {
            return "redirect:/login";
        }
        return "register?error=true";
    }
}

