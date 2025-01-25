package com.service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    
    public User registerUser(String username, String password) {
        // ایجاد کاربر جدید و ذخیره آن
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);  // در عمل باید رمز عبور هش شود
        user.setRole("USER");
        return userRepository.save(user);
    }

    public Optional<User> authenticateUser(String username, String password) {
        // بررسی اعتبار نام کاربری و رمز عبور
        return userRepository.findByUsername(username);
    }
}
