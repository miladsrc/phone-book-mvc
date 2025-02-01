package com.base.database.contact.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactResponseDTO {
    private Long id;
    private String name;
    private String phoneNumber;
    private Long userId;
}
