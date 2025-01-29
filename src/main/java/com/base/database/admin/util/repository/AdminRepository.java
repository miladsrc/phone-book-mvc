package com.base.database.admin.util.repository;

import com.base.database.user.util.model.User;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
@DependsOn({"contactRepository", "userRepository"})
public interface AdminRepository extends JpaRepository<User, Integer> {

}

