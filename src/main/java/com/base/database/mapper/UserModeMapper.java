package com.base.database.mapper;

import com.base.database.user.dto.UserRequestDTO;
import com.base.database.user.dto.UserResponseDTO;
import com.base.database.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserModeMapper {

    public User toEntity(UserRequestDTO dto, Class<User> userClass) {
        if (dto == null) {
            return null;
        }
        return User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .username(dto.getUsername())
                .password(dto.getPassword()) // رمزگذاری باید در سرویس انجام بشه
                .email(dto.getEmail())
                .role(dto.getRole())
                .build();
    }

    public UserResponseDTO toResponseDTO(User user, Class<UserResponseDTO> userResponseDTOClass) {
        if (user == null) {
            return null;
        }
        return UserResponseDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public void updateEntity(UserRequestDTO dto, User user) {
        if (dto == null || user == null) {
            return;
        }
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
    }

}
