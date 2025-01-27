package com.base.database.admin.util.repository;

import com.base.database.contact.util.model.Contact;
import com.base.database.user.util.model.User;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@DependsOn({"contactRepository", "userRepository"})
public interface AdminRepository extends JpaRepository<User, Integer> {
//        @Query("select from ")
//        List<Contact> getContacts();
}
