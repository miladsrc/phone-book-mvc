package com.controller;

import com.beans.Contact;
import com.beans.User;
import com.service.ContactService;
import com.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private final ContactService contactService;
    private final UserService userService;

    public ContactController(ContactService contactService, UserService userService) {
        this.contactService = contactService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getContacts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        com.beans.User user = userService.findByUsername(authentication.getName()).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }
        List<Contact> contacts = contactService.getContactsForUser(user);
        return ResponseEntity.ok(contacts);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addContact(@RequestParam String name, @RequestParam String phoneNumber) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        com.beans.User user = userService.findByUsername(authentication.getName()).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }
        contactService.addContact(user, name, phoneNumber);
        return ResponseEntity.ok("Contact added successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return ResponseEntity.ok("Contact deleted successfully");
    }
}


