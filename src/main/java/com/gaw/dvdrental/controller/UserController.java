package com.gaw.dvdrental.controller;

import com.gaw.dvdrental.model.dto.UserDto;
import com.gaw.dvdrental.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/users")
    public String usersAdmin(Model model){
        System.out.println("users~Admin");
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/user/users")
    public String usersUser(Model model){
        System.out.println("users~User");
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}
