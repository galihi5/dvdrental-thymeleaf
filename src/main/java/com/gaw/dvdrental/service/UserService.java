package com.gaw.dvdrental.service;

import com.gaw.dvdrental.model.dto.UserDto;
import com.gaw.dvdrental.model.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    User findByEmail(String email);

    List<UserDto> findAllUsers();
}
