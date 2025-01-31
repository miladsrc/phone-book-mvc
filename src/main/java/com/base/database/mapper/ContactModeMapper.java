package com.base.database.mapper;

import com.base.database.contact.util.dto.ContactNamePhoneDto;
import com.base.database.contact.util.dto.ContactRequestDTO;
import com.base.database.contact.util.dto.ContactResponseDTO;
import com.base.database.contact.util.model.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactModeMapper {

    public Contact toEntity(ContactRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        return Contact.builder()
                .name(dto.getName())
                .phoneNumber(dto.getPhoneNumber())
                .userId(dto.getUserId())
                .build();
    }

    public ContactResponseDTO toResponseDTO(Contact contact) {
        if (contact == null) {
            return null;
        }
        return ContactResponseDTO.builder()
                .id(contact.getId())
                .name(contact.getName())
                .phoneNumber(contact.getPhoneNumber())
                .userId(contact.getUserId())
                .build();
    }

    public Contact toEntity(ContactNamePhoneDto dto, Long userId) {
        if (dto == null) {
            return null;
        }
        return Contact.builder()
                .name(dto.getName())
                .phoneNumber(dto.getPhoneNumber())
                .userId(userId)
                .build();
    }
}
