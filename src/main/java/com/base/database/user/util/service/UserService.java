package com.base.database.user.util.service;

import com.base.database.user.util.dto.UserRequestDTO;
import com.base.database.user.util.dto.UserResponseDTO;
import com.base.database.user.util.model.User;
import com.base.database.user.util.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

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
        return UserResponseDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        String encodedPassword = passwordEncoder.encode(userRequestDTO.getPassword());
        User user = User.builder()
                .firstName(userRequestDTO.getFirstName())
                .lastName(userRequestDTO.getLastName())
                .username(userRequestDTO.getUsername())
                .password(encodedPassword)
                .email(userRequestDTO.getEmail())
                .role(userRequestDTO.getRole())
                .build();
        User savedUser = userRepository.save(user);
        return UserResponseDTO.builder()
                .id(savedUser.getId())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .build();
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        User oldUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + id + " not found"));

        String encodedPassword = (userRequestDTO.getPassword() != null) ?
                passwordEncoder.encode(userRequestDTO.getPassword()) : oldUser.getPassword();

        User updatedUser = User.builder()
                .id(oldUser.getId())
                .firstName(userRequestDTO.getFirstName())
                .lastName(userRequestDTO.getLastName())
                .username(userRequestDTO.getUsername())
                .password(encodedPassword)
                .email(userRequestDTO.getEmail())
                .role(userRequestDTO.getRole())
                .build();

        User savedUser = userRepository.save(updatedUser);

        return UserResponseDTO.builder()
                .id(savedUser.getId())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .build();
    }


    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User with ID " + id + " not found");
        }
        userRepository.deleteById(id);
    }

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