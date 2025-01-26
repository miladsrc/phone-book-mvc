package com.repository;

import com.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<User, Integer> {
        @Query("SELECT u FROM User u LEFT JOIN FETCH u.contacts")
        List<User> findAllUsersWithContacts();
        Optional<User> findByUsername(String username);

}
