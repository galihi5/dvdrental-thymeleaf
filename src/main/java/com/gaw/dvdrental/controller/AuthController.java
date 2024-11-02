package com.gaw.dvdrental.controller;

import com.gaw.dvdrental.config.auth.MyCustomAuthenticationSuccessHandler;
import com.gaw.dvdrental.model.dto.ActorDto;
import com.gaw.dvdrental.model.dto.UserDto;
import com.gaw.dvdrental.model.entity.Actor;
import com.gaw.dvdrental.model.entity.User;
import com.gaw.dvdrental.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Controller
public class AuthController {

    @Autowired
    private ModelMapper modelMapper;

    private UserService userService;

    @Value("${spring.application.name}")
    private String appName;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/", "/index"})
    public String home(Model model){
        Map<String, String> map;
        Set<Map<String, String>> listMenu = new HashSet<>();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (final GrantedAuthority grantedAuthority : authorities) {
            String authorityName = grantedAuthority.getAuthority();
            if("ROLE_ADMIN".equalsIgnoreCase(authorityName)) {
                map = new HashMap<>();
                map.put("name", "users");
                map.put("path", "/admin/users");
                listMenu.add(map);

                map = new HashMap<>();
                map.put("name", "staff");
                map.put("path", "/admin/staffs");
                listMenu.add(map);

                map = new HashMap<>();
                map.put("name", "store");
                map.put("path", "/admin/stores");
                listMenu.add(map);
            }

            if("ROLE_USER".equalsIgnoreCase(authorityName) || "ROLE_ADMIN".equalsIgnoreCase(authorityName)) {
                map = new HashMap<>();
                map.put("name", "users");
                map.put("path", "/user/users");
                listMenu.add(map);

                map = new HashMap<>();
                map.put("name", "actor");
                map.put("path", "/user/actors");
                listMenu.add(map);

                map = new HashMap<>();
                map.put("name", "address");
                map.put("path", "/user/addresses");
                listMenu.add(map);
            }
        }

        model.addAttribute("appName", appName);
        model.addAttribute("menus", listMenu);
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        System.out.println("### login");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }

        String target = MyCustomAuthenticationSuccessHandler.determineTargetUrl(authentication);
        return "redirect:"+target;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model){
        User existingUser = userService.findByEmail(userDto.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null, "There is already an account registered with the same email");
        }

        if (result.hasErrors()){
            model.addAttribute("user", userDto);
            return "register";
        }

        User user = convertToEntity(userDto);
        userService.saveUser(user);

        return "redirect:/register?success";
    }

    private UserDto convertToDto(User user) {
        UserDto dto = modelMapper.map(user, UserDto.class);
//        postDto.setSubmissionDate(post.getSubmissionDate(),
//                userService.getCurrentUser().getPreference().getTimezone());
        return dto;
    }

    private User convertToEntity(UserDto dto) {
        User user = modelMapper.map(dto, User.class);
//        post.setSubmissionDate(postDto.getSubmissionDateConverted(
//                userService.getCurrentUser().getPreference().getTimezone()));
//
//        if (postDto.getId() != null) {
//            Post oldPost = postService.getPostById(postDto.getId());
//            post.setRedditID(oldPost.getRedditID());
//            post.setSent(oldPost.isSent());
//        }
        return user;
    }


}
