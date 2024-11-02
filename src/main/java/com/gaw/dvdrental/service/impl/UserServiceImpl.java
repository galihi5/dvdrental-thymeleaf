package com.gaw.dvdrental.service.impl;

import com.gaw.dvdrental.model.dto.UserDto;
import com.gaw.dvdrental.model.entity.Role;
import com.gaw.dvdrental.model.entity.User;
import com.gaw.dvdrental.repository.RoleRepository;
import com.gaw.dvdrental.repository.UserRepository;
import com.gaw.dvdrental.service.UserService;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  @Autowired private ModelMapper modelMapper;
  private UserRepository userRepository;
  private RoleRepository roleRepository;
  private PasswordEncoder passwordEncoder;

  public UserServiceImpl(
      UserRepository userRepository,
      RoleRepository roleRepository,
      PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public void saveUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));

    Role role = roleRepository.findByName("ROLE_ADMIN");
    if (role == null) {
      role = checkRoleExist();
    }
    user.setRoles(Set.of(role));
    userRepository.save(user);
  }

  private Role checkRoleExist() {
    Role role = new Role();
    role.setName("ROLE_ADMIN");
    return roleRepository.save(role);
  }

  @Override
  public User findByEmail(String email) {
    return userRepository.findByEmailIgnoreCase(email);
  }

  @Override
  public List<UserDto> findAllUsers() {
    return userRepository.findAll().stream().map(this::mapToUserDto).collect(Collectors.toList());
  }

  private UserDto mapToUserDto(User user) {
    return modelMapper.map(user, UserDto.class);
  }
}
