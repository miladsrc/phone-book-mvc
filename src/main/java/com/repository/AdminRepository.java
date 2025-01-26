package com.repository;

import com.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<User, Integer> {
        @Query("SELECT u FROM Users u")
        List<User> findAllUsers();
        Optional<User> findByUsername(String username);

}
