package com.fleam.accountservice.repository;

import com.fleam.accountservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    List<User> findByCreatorTrue();
}
