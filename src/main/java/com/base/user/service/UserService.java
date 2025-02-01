package com.base.user.service;

import com.base.mapper.UserModeMapper;
import com.base.user.dto.UserRequestDTO;
import com.base.user.dto.UserResponseDTO;
import com.base.user.entity.User;
import com.base.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserModeMapper userModeMapper;

    public List<UserResponseDTO> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + id + " not found"));
        return userModeMapper.toResponseDTO(user, UserResponseDTO.class);
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User existingUser = userModeMapper.toEntity(userRequestDTO, User.class);
        User savedUser = userRepository.save(existingUser);
        return userModeMapper.toResponseDTO(savedUser, UserResponseDTO.class);
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + id + " not found"));
        if (userRequestDTO.getFirstName() != null) {
            existingUser.setFirstName(userRequestDTO.getFirstName());
        }
        if (userRequestDTO.getLastName() != null) {
            existingUser.setLastName(userRequestDTO.getLastName());
        }
        if (userRequestDTO.getUsername() != null) {
            existingUser.setUsername(userRequestDTO.getUsername());
        }
        if (userRequestDTO.getEmail() != null) {
            existingUser.setEmail(userRequestDTO.getEmail());
        }
        if (userRequestDTO.getRole() != null) {
            existingUser.setRole(userRequestDTO.getRole());
        }
        if (userRequestDTO.getPassword() != null && !userRequestDTO.getPassword().isEmpty()) {
            existingUser.setPassword(userRequestDTO.getPassword());
        }
        User updatedUser = userRepository.save(existingUser);
        return userModeMapper.toResponseDTO(updatedUser, UserResponseDTO.class);
    }

    private UserResponseDTO mapToDTO(User user) {
        return userModeMapper.toResponseDTO(user, UserResponseDTO.class);
    }
}