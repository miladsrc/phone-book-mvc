package com.repository;

import com.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<User, Integer> {

    User findAlLByName(String name);

}
