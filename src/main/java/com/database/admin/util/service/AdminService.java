package com.database.admin.util.service;

import com.database.admin.util.repository.AdminRepository;
import com.database.user.util.dto.UserDto;
import com.database.user.util.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AdminService(AdminRepository adminRepository, ModelMapper modelMapper) {
        this.adminRepository = adminRepository;
        this.modelMapper = modelMapper;
    }

    public List<UserDto> findAllUsers() {
        List<User> users = adminRepository.findAllUsersWithContacts();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    public Optional<UserDto> findUserByUsername(String username) {
        Optional<User> user = adminRepository.findByUsername(username);
        return user.map(u -> modelMapper.map(u, UserDto.class));
    }

    public UserDto saveUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        User savedUser = adminRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    public void deleteUser(Long id) {
        adminRepository.deleteById(Math.toIntExact(id));
    }
}

