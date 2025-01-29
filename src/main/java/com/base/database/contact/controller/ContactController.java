package com.base.database.contact.controller;

import com.base.database.contact.util.dto.ContactRequestDTO;
import com.base.database.contact.util.dto.ContactResponseDTO;
import com.base.database.contact.util.service.ContactService;
import com.base.database.security.model.service.JwtService;
import com.base.database.user.util.dto.UserResponseDTO;
import com.base.database.user.util.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/contacts")
public class ContactController {

    private final ContactService contactService;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;

    @GetMapping
    public ResponseEntity<List<ContactResponseDTO>> getContactsByToken(@RequestHeader("Authorization") String token) throws ChangeSetPersister.NotFoundException {
        String jwt = token.substring(7);
        String username = jwtService.extractUsername(jwt);
        UserResponseDTO userResponseDTO = contactService.getUserByUsername(username);
        List<ContactResponseDTO> contacts = contactService.findContactsByUserId(userResponseDTO.getId());
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/id")
    public ResponseEntity<List<ContactResponseDTO>> getContactsByToken(@PathVariable("id") Long userId) throws ChangeSetPersister.NotFoundException {
        List<ContactResponseDTO> contacts = contactService.findContactsByUserId(userId);
        return ResponseEntity.ok(contacts);
    }

    @PostMapping
    public ResponseEntity<ContactResponseDTO> createContact(
            @RequestHeader("Authorization") String token,
            @RequestBody ContactRequestDTO contactRequestDTO) throws ChangeSetPersister.NotFoundException {
        Long userId = getUserIdFromToken(token);
        contactRequestDTO.setUserId(userId);
        ContactResponseDTO createdContact = contactService.createContact(contactRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContact);
    }

    @PutMapping("/{contactId}")
    public ResponseEntity<ContactResponseDTO> updateContact(
            @RequestHeader("Authorization") String token,
            @PathVariable Long contactId,
            @RequestBody ContactRequestDTO contactRequestDTO) throws ChangeSetPersister.NotFoundException {
        Long userId = getUserIdFromToken(token);
        contactRequestDTO.setUserId(userId);
        ContactResponseDTO updatedContact = contactService.updateContact(contactId, contactRequestDTO);
        return ResponseEntity.ok(updatedContact);
    }

    private Long getUserIdFromToken(String token) throws ChangeSetPersister.NotFoundException {
        if (token == null || !token.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }
        String jwt = token.substring(7);
        String username = jwtService.extractUsername(jwt);
        UserResponseDTO user = contactService.getUserByUsername(username);
        return user.getId();
    }

}


