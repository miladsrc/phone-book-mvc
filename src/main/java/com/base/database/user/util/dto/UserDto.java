package com.base.database.user.util.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
}
