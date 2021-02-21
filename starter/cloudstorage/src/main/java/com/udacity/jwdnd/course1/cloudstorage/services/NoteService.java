package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class NoteService {

    private final UserMapper userMapper;
    private final NoteMapper noteMapper;

    public NoteService(UserMapper userMapper, NoteMapper noteMapper) {
        this.userMapper = userMapper;
        this.noteMapper = noteMapper;
    }

    public int create(Note note) {
        System.out.print("new note: " + note.getNoteTitle() + " - "
                + note.getNoteDescription() + " (" + note.getUserId() + ")");
        return noteMapper.insert(new Note(this.count(), note.getNoteTitle(), note.getNoteDescription(), note.getUserId()));
    }

    public int update(Note note) {
        return noteMapper.update(note);
    }

    private Integer count() {
        return noteMapper.count();
    }

    public List<Note> findByUser(Integer id) {
        return noteMapper.findByUser(id);
    }

    public Note findById(Integer id){
        return noteMapper.findById(id);
    }

    public void delete(Integer id) { noteMapper.delete(id); }
}