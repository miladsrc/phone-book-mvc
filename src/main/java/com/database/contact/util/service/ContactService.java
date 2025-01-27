package com.database.contact.util.service;

import com.database.contact.util.model.Contact;
import com.database.user.util.model.User;
import com.database.contact.util.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> getContactsForUser(User user) {
        return contactRepository.findByUser(user);
    }

    public void addContact(User user, String name, String phoneNumber) {
        Contact contact = new Contact();
        contact.setName(name);
        contact.setPhoneNumber(phoneNumber);
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
