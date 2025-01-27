package com.database.contact.util.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactDto {

    private Long id;
    private String name;
    private String phoneNumber;
    private Long userId;
}
