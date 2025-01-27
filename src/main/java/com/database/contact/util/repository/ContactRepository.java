package com.database.contact.util.repository;

import com.database.contact.util.model.Contact;
import com.database.user.util.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByUser(User user);
}
