package com.service;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserRepository userRepository;

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
}
