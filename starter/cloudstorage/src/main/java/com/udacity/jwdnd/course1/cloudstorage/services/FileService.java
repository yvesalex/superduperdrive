package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    private final UserMapper userMapper;
    private final FileMapper fileMapper;

    public FileService(UserMapper userMapper, FileMapper fileMapper) {
        this.userMapper = userMapper;
        this.fileMapper = fileMapper;
    }

    public int create(File file) {
         return fileMapper.insert(file);
    }

    public int update(File file) {
        return fileMapper.update(file);
    }

    public Integer count() {
        return fileMapper.count();
    }

    public List<File> findByUser(Integer id) {
        return fileMapper.findByUser(id);
    }

    public File findById(Integer id){
        return fileMapper.findById(id);
    }

    public void delete(Integer id) { fileMapper.delete(id); }

    public File findByName(String filename) {
        return fileMapper.findByFilename(filename);
    }
}