package com.base.contact.dto;

import lombok.*;

@Getter
@Setter
@Builder
public class ContactResponseDTO {
    private Long id;
    private String name;
    private String phoneNumber;
    private Long userId;
}
