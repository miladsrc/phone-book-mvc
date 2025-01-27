package com.database.contact.util.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactDtoNameAndPhone {

    private String name;
    private String phoneNumber;
}
