package com.base.user.dto;

import com.base.security.entity.Role;
import lombok.*;

@Getter
@Setter
@Builder
public class UserResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private Role role;
}
