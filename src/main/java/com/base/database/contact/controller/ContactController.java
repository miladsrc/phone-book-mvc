package com.base.database.contact.controller;

import com.base.database.contact.util.dto.ContactDto;
import com.base.database.contact.util.dto.ContactDtoNameAndPhone;
import com.base.database.contact.util.model.Contact;

import com.base.database.contact.util.service.ContactService;
import com.base.database.user.util.dto.UserDto;
import com.base.database.user.util.model.User;
import com.base.database.user.util.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private final ContactService contactService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public ContactController(ContactService contactService, UserService userService, ModelMapper modelMapper) {
        this.contactService = contactService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getContacts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(authentication.getName()).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }
        UserDto userDto = modelMapper.map(user, UserDto.class);
        List<Contact> contacts = contactService.getContactsForUser(userDto);
        return ResponseEntity.ok(contacts);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addContact(@RequestBody ContactDtoNameAndPhone contactDtoNameAndPhone) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(authentication.getName()).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }
        UserDto userDto = modelMapper.map(user, UserDto.class);
        ContactDto contactDto = modelMapper.map(contactDtoNameAndPhone, ContactDto.class);
        contactDto.setUserId(user.getId());
        contactService.addContact(contactDtoNameAndPhone, userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Contact added successfully");
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Contact deleted successfully");
    }
}


