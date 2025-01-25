package com.controller;

import com.beans.Contact;
import com.beans.User;
import com.service.ContactService;
import com.service.UserService;
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


@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    private final ContactService contactService;
    private final UserService userService;

    public ContactController(ContactService contactService, UserService userService) {
        this.contactService = contactService;
        this.userService = userService;
    }

    @GetMapping
    public String getContacts(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        com.beans.User user = userService.findByUsername(authentication.getName()).orElse(null);
        List<Contact> contacts = contactService.getContactsForUser(user);
        model.addAttribute("contacts", contacts);
        return "contacts";
    }

    @PostMapping("/add")
    public String addContact(@RequestParam String name, @RequestParam String phoneNumber) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        com.beans.User user = userService.findByUsername(authentication.getName()).orElse(null);
        contactService.addContact(user, name, phoneNumber);
        return "redirect:/user/contacts";
    }

    @PostMapping("/delete/{id}")
    public String deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return "redirect:/user/contacts";
    }
}

