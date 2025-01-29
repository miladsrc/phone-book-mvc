package com.base.database.contact.util.repository;

import com.base.database.contact.util.dto.ContactResponseDTO;
import com.base.database.contact.util.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findContactsByUserId(Long id);

}
