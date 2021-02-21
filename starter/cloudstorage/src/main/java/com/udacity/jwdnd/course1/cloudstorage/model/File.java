package com.udacity.jwdnd.course1.cloudstorage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;
import javax.persistence.Lob;

public class File {

    @Id
    private int fileId;
    private String filename;
    private String contentType;
    private long fileSize;
    private int userId;
    @Lob
    @JsonIgnore
    private byte[] fileData;

    public File() {
    }

    public File(int fileId, String filename, String contentType, long fileSize, int userId, byte[] fileData) {
        this.fileId = fileId;
        this.filename = filename;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.userId = userId;
        this.fileData = fileData;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
}
