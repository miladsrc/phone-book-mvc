package com.base.database.contact.util.service;

import com.base.database.contact.util.dto.ContactRequestDTO;
import com.base.database.contact.util.dto.ContactResponseDTO;
import com.base.database.contact.util.model.Contact;
import com.base.database.contact.util.repository.ContactRepository;
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

    @Autowired
    public ContactService(ContactRepository contactRepository, UserRepository userRepository, UserRepository userRepository1) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository1;
    }

    //METHODS

    public List<ContactResponseDTO> findContactsByUserId(Long id) {
        return contactRepository.findContactsByUserId(id)
                .stream()
                .map(contact -> new ContactResponseDTO(contact.getId(),
                        contact.getName(),
                        contact.getPhoneNumber(),
                        contact.getUserId()))
                .collect(Collectors.toList());
    }

    public ContactResponseDTO createContact(ContactRequestDTO contactRequestDTO) {
        Contact contact = Contact.builder()
                .name(contactRequestDTO.getName())
                .phoneNumber(contactRequestDTO.getPhoneNumber())
                .userId(contactRequestDTO.getUserId())
                .build();
        Contact savedContact = contactRepository.save(contact);
        return ContactResponseDTO.builder()
                .id(savedContact.getId())
                .name(savedContact.getName())
                .phoneNumber(savedContact.getPhoneNumber())
                .userId(savedContact.getUserId())
                .build();
    }

    public ContactResponseDTO updateContact(Long contactId, ContactRequestDTO contactRequestDTO) {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new RuntimeException("Contact not found with ID: " + contactId));
        contact.setName(contactRequestDTO.getName());
        contact.setPhoneNumber(contactRequestDTO.getPhoneNumber());
        contact.setUserId(contactRequestDTO.getUserId());
        Contact updatedContact = contactRepository.save(contact);
        return ContactResponseDTO.builder()
                .id(updatedContact.getId())
                .name(updatedContact.getName())
                .phoneNumber(updatedContact.getPhoneNumber())
                .userId(updatedContact.getUserId())
                .build();
    }

    public void deleteContact(Long contactId) {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new RuntimeException("Contact not found with ID: " + contactId));
        contactRepository.delete(contact);
    }

    public void deleteContactByUserId(Long contractId) {
        contactRepository.deleteById(contractId);
    }

    //find user by username
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


