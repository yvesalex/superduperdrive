package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.*;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.http.HttpResponse;
import java.security.Principal;

@Controller()
@RequestMapping("/files")
public class FileController {

    private final UserService userService;
    private final FileService fileService;
    private final CredentialService credentialService;
    private final NoteService noteService;

    public FileController(UserService userService, FileService fileService, NoteService noteService, CredentialService credentialService) {
        this.userService = userService;
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
    }

    @ModelAttribute("user")
    public User getUser(Principal principal){
        return userService.getUser(principal.getName());
    }

    @ModelAttribute("file")
    public File getFile(){ return new File(); }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Integer id){
        try {
            fileService.delete(id);
            return "redirect:/home?success=true";
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "home";
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<InputStreamResource> download(@PathVariable Integer id, Model model) {
        try {
            File file = fileService.findById(id);
            System.out.println("file: " + file.getFilename());
            InputStream targetStream = new ByteArrayInputStream(file.getFileData());
            InputStreamResource resource = new InputStreamResource(targetStream);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getFilename())
                    .contentType(MediaType.valueOf(file.getContentType()))
                    .contentLength(file.getFileSize())
                    .body(resource);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            model.addAttribute("error", e.getMessage());
            return ResponseEntity.ok(null);
        }
    }

    @PostMapping
    public String create(@RequestParam(name = "fileUpload") MultipartFile file, Model model,
                         RedirectAttributes redirectAttributes) {
        try {
            User user = (User) model.getAttribute("user");
            File newFile = new File(
                    fileService.count(), file.getOriginalFilename(),
                    file.getContentType(), file.getSize(), user.getUserId(),
                    file.getBytes());
            if(fileService.findByName(newFile.getFilename()) != null) throw new Exception("Filename already in use...");
            if(fileService.create(newFile) <= 0) throw new Exception("File has not been created...");
            return "redirect:/home?success=true";
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            model.addAttribute("error", e.getMessage());
            redirectAttributes.addFlashAttribute("errorFileUpload",e.getMessage());
            return "redirect:/home";
        }
    }
}