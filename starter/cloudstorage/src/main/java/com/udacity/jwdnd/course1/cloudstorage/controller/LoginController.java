package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public User getUser(){ return new User(); }

    @GetMapping()
    public String loginView() {
        return "login";
    }

    @PostMapping()
    public String loginUser(@ModelAttribute User user, Model model) {
        String loginError = null;
        System.out.println("user: " + user.getUsername() + ": " + user.getPassword());
        User userFound = userService.getUser(user.getUsername());

        if (userFound == null) {
            loginError = "The user does not exist.";
        }
        else{
            System.out.println(userFound.getPassword());
        }

        if (loginError == null) {
            int rowsAdded = userService.createUser(user);
            if (rowsAdded < 0) {
                loginError = "There was an error signing you up. Please try again.";
            }
        }

        if (loginError == null) {
            model.addAttribute("loginSuccess", true);
        } else {
            model.addAttribute("loginError", loginError);
        }

        return "login";
    }
}
