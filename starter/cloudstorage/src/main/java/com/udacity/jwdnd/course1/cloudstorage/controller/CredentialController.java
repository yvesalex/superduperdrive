package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller()
@RequestMapping("/credentials")
public class CredentialController {

    private final UserService userService;
    private final CredentialService credentialService;

    public CredentialController(UserService userService, CredentialService credentialService) {
        this.userService = userService;
        this.credentialService = credentialService;
    }

    @ModelAttribute("user")
    public User getUser(Principal principal){
        return userService.getUser(principal.getName());
    }

    @ModelAttribute("credential")
    public Credential getCredential(){ return new Credential(); }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Integer id){
        try {
            credentialService.delete(id);
            return "redirect:/home?success=true";
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "home";
        }
    }

    @PostMapping
    public String create(@ModelAttribute Credential credential, Model model) {
        try {
            User user = (User) model.getAttribute("user");
            credential.setUserId(user.getUserId());
            credential.setKey(credentialService.generateKey());
            credential.setPassword(credentialService.encrypt(credential.getPassword(), credential.getKey()));
            if(credential.getCredentialId() <= 0){
                if(credentialService.create(credential) <= 0) throw new Exception("Credential has not been created...");
            } else{
                if(credentialService.update(credential) <= 0) throw new Exception("Credential has not been updated...");
            }
            return "redirect:/home?success=true";
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "home";
        }
    }
}