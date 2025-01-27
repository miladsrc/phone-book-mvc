package com.base.database.user.util.dto;

import com.base.database.security.bean.Role;
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
