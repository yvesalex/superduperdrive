package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller()
@RequestMapping("/notes")
public class NoteController {

    private final UserService userService;
    private final NoteService noteService;

    public NoteController(UserService userService, NoteService noteService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    @ModelAttribute("user")
    public User getUser(Principal principal){
        return userService.getUser(principal.getName());
    }

    @ModelAttribute("note")
    public Note getNote(){ return new Note(); }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Integer id){
        try {
            noteService.delete(id);
            return "redirect:/home?success=true";
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "home";
        }
    }

    @PostMapping
    public String create(@ModelAttribute Note note, Model model) {
        try {
            User user = (User) model.getAttribute("user");
            note.setUserId(user.getUserId());
            if(note.getNoteId() <= 0){
                if(noteService.create(note) <= 0) throw new Exception("Note has not been created...");
            } else{
                if(noteService.update(note) <= 0) throw new Exception("Note has not been updated...");
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