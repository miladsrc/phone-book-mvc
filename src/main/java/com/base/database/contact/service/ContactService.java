package com.base.database.contact.service;

import com.base.database.contact.dto.ContactRequestDTO;
import com.base.database.contact.dto.ContactResponseDTO;
import com.base.database.contact.entity.Contact;
import com.base.database.contact.repository.ContactRepository;
import com.base.database.mapper.ContactModeMapper;
import com.base.database.mapper.UserModeMapper;
import com.base.database.user.dto.UserResponseDTO;
import com.base.database.user.entity.User;
import com.base.database.user.repository.UserRepository;
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
    private final UserModeMapper userModeMapper;

    @Autowired
    public ContactService(ContactRepository contactRepository, UserRepository userRepository, UserRepository userRepository1, ContactModeMapper contactModeMapper, UserModeMapper userModeMapper) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository1;
        this.contactModeMapper = contactModeMapper;
        this.userModeMapper = userModeMapper;
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
        User user = userRepository.findUserById(contactRequestDTO.getUserId());
        contact.setUser(user);
        Contact savedContact = contactRepository.save(contact);
        return contactModeMapper.toResponseDTO(savedContact);
    }

    public ContactResponseDTO updateContact(Long contactId, ContactRequestDTO contactRequestDTO) {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new RuntimeException("Contact not found with ID: " + contactId));
        contact.setName(contactRequestDTO.getName());
        contact.setPhoneNumber(contactRequestDTO.getPhoneNumber());
        contact.setUserId(contactRequestDTO.getUserId());
        User user = userRepository.findUserById(contactRequestDTO.getUserId());
        contact.setUser(user);
        Contact updatedContact = contactRepository.save(contact);
        return contactModeMapper.toResponseDTO(updatedContact);
    }

    // find user by username
    public UserResponseDTO getUserByUsername(String username) throws ChangeSetPersister.NotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> userModeMapper.toResponseDTO(user, UserResponseDTO.class))
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

}


