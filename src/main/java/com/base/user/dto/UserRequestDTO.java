package com.base.user.dto;

import com.base.security.entity.Role;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDTO {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private Role role;
}
