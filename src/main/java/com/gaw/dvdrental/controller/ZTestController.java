package com.gaw.dvdrental.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class ZTestController {

    @GetMapping({"/hello", "/test"})
    public String hello(Model model){
        model.addAttribute("hello", "hello world");
        return "hello";
    }
}
