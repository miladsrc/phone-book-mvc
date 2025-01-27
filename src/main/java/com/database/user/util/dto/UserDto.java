package com.database.user.util.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;            // ID of the user
    private String firstName;   // User's first name
    private String lastName;    // User's last name
    private String username;    // User's username
    private String email;       // User's email
    private String role;        // Role of the user (e.g., ADMIN, USER)
}
