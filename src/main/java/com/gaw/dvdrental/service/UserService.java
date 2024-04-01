package com.gaw.dvdrental.service;

import com.gaw.dvdrental.dto.UserRegistrationDto;
import com.gaw.dvdrental.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
     User save(UserRegistrationDto registrationDto);
     List<User> getAll();
}
