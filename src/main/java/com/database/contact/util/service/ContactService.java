package com.database.contact.util.service;

import com.database.contact.util.dto.ContactDtoNameAndPhone;
import com.database.contact.util.model.Contact;
import com.database.user.util.dto.UserDto;
import com.database.contact.util.repository.ContactRepository;
import com.database.user.util.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ContactService(ContactRepository contactRepository, ModelMapper modelMapper) {
        this.contactRepository = contactRepository;
        this.modelMapper = modelMapper;
    }

    public List<Contact> getContactsForUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        return contactRepository.findByUser(user); // Pass the User entity to the repository
    }

    public void addContact(ContactDtoNameAndPhone contactDtoNameAndPhone, UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        Contact contact = modelMapper.map(contactDtoNameAndPhone, Contact.class);
        contact.setUser(user);
        contactRepository.save(contact);
    }

    public void deleteContact(Long contactId) {
        contactRepository.deleteById(contactId);
    }

    public Contact getContactById(Long contactId) {
        return contactRepository.findById(contactId).orElse(null);
    }
}


