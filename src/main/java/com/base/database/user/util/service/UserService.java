package com.base.database.user.util.service;

import com.base.database.user.util.dto.UserRequestDTO;
import com.base.database.user.util.dto.UserResponseDTO;
import com.base.database.user.util.model.User;
import com.base.database.user.util.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserResponseDTO> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }
//
//    public UserResponseDTO getUserById(Long id) {
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("User with ID " + id + " not found"));
//        return modelMapper.map(user, UserResponseDTO.class);
//    }
//
//    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
//        User existingUser = modelMapper.map(userRequestDTO, User.class);
//        User savedUser = userRepository.save(existingUser);
//        return modelMapper.map(savedUser, UserResponseDTO.class);
//    }
//
//    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
//        User existingUser = userRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("User with ID " + id + " not found"));
//        User existUser = modelMapper.map(userRequestDTO, User.class);
//        User updatedUser = userRepository.save(existUser);
//        return modelMapper.map(updatedUser, UserResponseDTO.class);
//    }
//
//    public void deleteUser(Long id) {
//        if (!userRepository.existsById(id)) {
//            throw new IllegalArgumentException("User with ID " + id + " not found");
//        }
//        userRepository.deleteById(id);
//    }
    private UserResponseDTO mapToDTO(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}