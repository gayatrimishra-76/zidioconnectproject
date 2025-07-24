package com.zidio.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zidio.auth.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
