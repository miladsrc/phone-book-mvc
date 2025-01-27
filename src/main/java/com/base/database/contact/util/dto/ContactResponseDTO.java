package com.base.database.contact.util.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContactResponseDTO {
    private Long id;
    private String name;
    private String phoneNumber;
    private Long userId;
}
