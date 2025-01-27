package com.base.database.contact.util.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContactRequestDTO {
    private String name;
    private String phoneNumber;
    private Long userId; // ID of the associated user
}
