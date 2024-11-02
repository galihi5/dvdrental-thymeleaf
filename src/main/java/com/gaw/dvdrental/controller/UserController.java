package com.gaw.dvdrental.controller;

import com.gaw.dvdrental.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

  @Autowired ModelMapper modelMapper;

  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/admin/users")
  public String usersAdmin(Model model) {
    System.out.println("users~Admin");

    model.addAttribute("users", userService.findAllUsers());
    return "users";
  }

  @GetMapping("/user/users")
  public String usersUser(Model model) {
    System.out.println("users~User");

    model.addAttribute("users", userService.findAllUsers());
    return "users";
  }
}
