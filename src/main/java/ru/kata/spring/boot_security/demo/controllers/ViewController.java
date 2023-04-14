package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.models.User;

@Controller
public class ViewController {


    @GetMapping("/admin")
    public String showAllUser(Model model) {
        return "admin-profile";
    }

    @GetMapping("/user")
    public String showOneUser() {
        return "user-profile";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

}
