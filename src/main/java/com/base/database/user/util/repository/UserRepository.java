package com.base.database.user.util.repository;

import com.base.database.user.util.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);
    Optional<User> findByUsername(String username);

}
