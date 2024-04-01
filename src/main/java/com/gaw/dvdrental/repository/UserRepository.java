package com.gaw.dvdrental.repository;

import com.gaw.dvdrental.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String enail);
}
