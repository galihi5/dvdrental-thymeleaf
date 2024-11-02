package com.gaw.dvdrental.repository;

import com.gaw.dvdrental.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByEmailIgnoreCase(String email);
}
