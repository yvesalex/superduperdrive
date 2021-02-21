package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.*;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/error")
public class ErrorController {

    private final UserService userService;
    private final FileService fileService;
    private final CredentialService credentialService;
    private final NoteService noteService;

    public ErrorController(UserService userService, FileService fileService, NoteService noteService, CredentialService credentialService) {
        this.userService = userService;
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
    }

    @GetMapping(value = {"/","/home"})
    public String home(Principal principal, Model model){
        if(principal == null) return "login";

        System.out.println("success: " + model.getAttribute("success"));
        System.out.println("error: " + model.getAttribute("error"));

        User user = userService.getUser(principal.getName());
        model.addAttribute("user", user);
        if(user == null) return "login";

        List<File> files = fileService.findByUser(user.getUserId());
        model.addAttribute("files", files);
        model.addAttribute("file", new File());

        List<Note> notes = noteService.findByUser(user.getUserId());
        model.addAttribute("notes", notes);
        model.addAttribute("note", new Note());

        List<Credential> credentials = credentialService.findByUser(user.getUserId());
        List<CredentialDto> credentialDtos = new ArrayList<>();
        for (Credential c: credentials) {
            CredentialDto dto = new CredentialDto(c.getCredentialId(), c.getUrl(), c.getUsername(),
                    c.getKey(), c.getPassword(), credentialService.decrypt(c.getPassword(), c.getKey()), c.getUserId());
            credentialDtos.add(dto);
        }
        model.addAttribute("credentials", credentialDtos);
        model.addAttribute("credential", new Credential());

        System.out.println("access to home...");
        return "home";
    }
}
