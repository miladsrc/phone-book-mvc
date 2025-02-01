package com.base.database.contact.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactRequestDTO {
    private String name;
    private String phoneNumber;
    private Long userId;
}
