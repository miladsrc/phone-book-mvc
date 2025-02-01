package com.base.database.user.repository;

import com.base.database.contact.dto.ContactNamePhoneDto;
import com.base.database.contact.entity.Contact;
import com.base.database.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);

    Optional<User> findByUsername(String username);
    User findUserById(Long userId);

    @Query("select c.name as name, c.phoneNumber phoneNumber " +
            "from Contact c join User u on c.userId = u.id")
    Optional<List<ContactNamePhoneDto>> findContactListById(Contact contact);

}

