package com.base.contact.dto;

import lombok.*;

@Getter
@Setter
public class ContactRequestDTO {
    private String name;
    private String phoneNumber;
    private Long userId;
}
