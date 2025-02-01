package com.base.database.contact.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContactNamePhoneDto {
    private String name;
    private String phoneNumber;
}
