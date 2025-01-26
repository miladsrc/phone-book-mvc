package com.service;

import com.beans.User;
import com.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<User> findAllUsers() {
        return adminRepository.findAll();
    }

    public Optional<User> findUserByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    public User saveUser(User user) {
        return adminRepository.save(user);
    }

    public void deleteUser(Long id) {
        adminRepository.deleteById(Math.toIntExact(id));
    }
}
