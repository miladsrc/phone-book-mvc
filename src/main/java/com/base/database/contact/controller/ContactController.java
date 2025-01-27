package com.base.database.contact.controller;

import com.base.database.contact.util.dto.ContactRequestDTO;
import com.base.database.contact.util.dto.ContactResponseDTO;
import com.base.database.contact.util.service.ContactService;
import com.base.database.security.model.service.JwtService;
import com.base.database.user.util.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;




@RestController
@RequiredArgsConstructor
@RequestMapping("/api/contacts")
public class ContactController {

    private final ContactService contactService;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;

    @GetMapping
    public ResponseEntity<List<ContactResponseDTO>> getContactsByToken(@RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);
        Long userId = jwtService.extractUserId(jwt);
        List<ContactResponseDTO> contacts = contactService.findContactsByUserId(userId);
        return ResponseEntity.ok(contacts);
    }

    @PostMapping
    public ResponseEntity<ContactResponseDTO> createContact(
            @RequestHeader("Authorization") String token,
            @RequestBody ContactRequestDTO contactRequestDTO) {
        String jwt = token.substring(7);
        Long userId = jwtService.extractUserId(jwt);
        contactRequestDTO.setUserId(userId);
        ContactResponseDTO createdContact = contactService.createContact(contactRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContact);
    }

    @PutMapping("/{contactId}")
    public ResponseEntity<ContactResponseDTO> updateContact(
            @RequestHeader("Authorization") String token,
            @PathVariable Long contactId,
            @RequestBody ContactRequestDTO contactRequestDTO) {
        String jwt = token.substring(7);
        Long userId = jwtService.extractUserId(jwt);
        contactRequestDTO.setUserId(userId);
        ContactResponseDTO updatedContact = contactService.updateContact(contactId, contactRequestDTO);
        return ResponseEntity.ok(updatedContact);
    }

    @DeleteMapping("/{contactId}")
    public ResponseEntity<Void> deleteContact(
            @RequestHeader("Authorization") String token,
            @PathVariable Long contactId) {
        String jwt = token.substring(7);
        Long userId = jwtService.extractUserId(jwt);
        contactService.deleteContactByUserId(contactId);
        return ResponseEntity.noContent().build();
    }
}


