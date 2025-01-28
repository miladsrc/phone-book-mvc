package com.base.database.contact.util.service;

import com.base.database.contact.util.dto.ContactRequestDTO;
import com.base.database.contact.util.dto.ContactResponseDTO;
import com.base.database.contact.util.model.Contact;
import com.base.database.contact.util.repository.ContactRepository;
import com.base.database.user.util.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;


@DependsOn("contactRepository")
@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ContactService(ContactRepository contactRepository, ModelMapper modelMapper) {
        this.contactRepository = contactRepository;
        this.modelMapper = modelMapper;
    }

    //METHODS

    public List<ContactResponseDTO> findContactsByUsername(String username) {
        return contactRepository.findContactsByUsername(username)
                .stream()
                .map(contact -> modelMapper.map(contact, ContactResponseDTO.class))
                .collect(Collectors.toList());
    }
//
//    public ContactResponseDTO createContact(ContactRequestDTO contactRequestDTO) {
//        Contact contact = modelMapper.map(contactRequestDTO, Contact.class);
//        Contact savedContact = contactRepository.save(contact);
//        return modelMapper.map(savedContact, ContactResponseDTO.class);
//    }
//
//    public ContactResponseDTO updateContact(Long contactId, ContactRequestDTO contactRequestDTO) {
//        Contact contact = contactRepository.findById(contactId)
//                .orElseThrow(() -> new RuntimeException("Contact not found with ID: " + contactId));
//        modelMapper.map(contactRequestDTO, contact); // Update fields in existing entity
//        Contact updatedContact = contactRepository.save(contact);
//        return modelMapper.map(updatedContact, ContactResponseDTO.class);
//    }
//
//    public void deleteContact(Long contactId) {
//        Contact contact = contactRepository.findById(contactId)
//                .orElseThrow(() -> new RuntimeException("Contact not found with ID: " + contactId));
//        contactRepository.delete(contact);
//    }
//
//    public List<ContactResponseDTO> findContactByUserId(Long userId) {
//        return contactRepository.findAll().stream()
//                .filter(contact -> contact.getUser().getId().equals(userId))
//                .map(contact -> modelMapper.map(contact, ContactResponseDTO.class))
//                .collect(Collectors.toList());
//    }
//
//    public void deleteContactByUserId(Long contractId) {
//        contactRepository.deleteById(contractId);
//    }
//
//    public  List<ContactResponseDTO>  findUserContactByUserId(Long userId) {
//        return contactRepository.findContactByUserId(userId).stream()
//                .map(l -> modelMapper.map(l, ContactResponseDTO.class))
//                .collect(Collectors.toList());
//    }
}


