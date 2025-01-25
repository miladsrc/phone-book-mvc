package com.controller;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        Optional<User> user = authService.authenticateUser(username, password);
        if (user.isPresent()) {
            return "redirect:/user/contacts"; // به صفحه لیست کانتکت‌ها می‌رود
        }
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        authService.registerUser(username, password);
        return "redirect:/login";
    }
}
