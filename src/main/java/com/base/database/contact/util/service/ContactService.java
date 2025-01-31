package com.base.database.contact.util.service;

import com.base.database.contact.util.dto.ContactRequestDTO;
import com.base.database.contact.util.dto.ContactResponseDTO;
import com.base.database.contact.util.model.Contact;
import com.base.database.contact.util.repository.ContactRepository;
import com.base.database.mapper.ContactModeMapper;
import com.base.database.user.util.dto.UserResponseDTO;
import com.base.database.user.util.repository.UserRepository;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;


@DependsOn("contactRepository")
@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final UserRepository userRepository;
    private final ContactModeMapper contactModeMapper;

    @Autowired
    public ContactService(ContactRepository contactRepository, UserRepository userRepository, UserRepository userRepository1, ContactModeMapper contactModeMapper) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository1;
        this.contactModeMapper = contactModeMapper;
    }

    //METHODS
    public List<ContactResponseDTO> findContactsByUserId(Long userId) {
        return contactRepository.findContactsByUserId(userId)
                .stream()
                .map(contactModeMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public ContactResponseDTO createContact(ContactRequestDTO contactRequestDTO) {
        Contact contact = contactModeMapper.toEntity(contactRequestDTO);
        Contact savedContact = contactRepository.save(contact);
        return contactModeMapper.toResponseDTO(savedContact);
    }

    public ContactResponseDTO updateContact(Long contactId, ContactRequestDTO contactRequestDTO) {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new RuntimeException("Contact not found with ID: " + contactId));
        contact.setName(contactRequestDTO.getName());
        contact.setPhoneNumber(contactRequestDTO.getPhoneNumber());
        contact.setUserId(contactRequestDTO.getUserId());
        Contact updatedContact = contactRepository.save(contact);
        return contactModeMapper.toResponseDTO(updatedContact);
    }

    public void deleteContact(Long contactId) {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new RuntimeException("Contact not found with ID: " + contactId));
        contactRepository.delete(contact);
    }

    //TODO: create a method to delete contact bu id of the contact
    public void deleteContactByUserId(Long userId) {
        contactRepository.deleteById(userId);
    }

    // find user by username
    public UserResponseDTO getUserByUsername(String username) throws ChangeSetPersister.NotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new UserResponseDTO(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getRole()))
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

}


