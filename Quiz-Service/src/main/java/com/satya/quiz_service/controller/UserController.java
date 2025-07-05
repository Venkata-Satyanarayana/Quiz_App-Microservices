package com.satya.quiz_service.controller;

import com.satya.quiz_service.model.UserDto;
import com.satya.quiz_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

   private UserDto userDto;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("userDto") UserDto userDto, Model model) {
        userService.register(userDto);
        model.addAttribute("message", "Successfully registered!");
        return "register_success";
    }
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
}
