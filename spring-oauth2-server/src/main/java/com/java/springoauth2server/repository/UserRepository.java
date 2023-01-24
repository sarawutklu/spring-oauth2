package com.java.springoauth2server.repository;

import com.java.springoauth2server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
