package com.udacity.jwdnd.course1.cloudstorage.model;

import javax.persistence.Id;

public class Note {
    @Id
    private int noteId;
    private String noteTitle;
    private String noteDescription;
    private int userId;

    public Note() {
    }

    public Note(int noteId, String noteTitle, String noteDescription, int userId) {
        this.noteId = noteId;
        this.noteDescription = noteDescription;
        this.noteTitle = noteTitle;
        this.userId = userId;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

