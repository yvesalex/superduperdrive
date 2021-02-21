package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {

    private final UserMapper userMapper;
    private final CredentialMapper credentialMapper;
    private final EncryptionService encryptionService;

    public CredentialService(UserMapper userMapper, CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.userMapper = userMapper;
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public int create(Credential credential) {
        return credentialMapper.insert(new Credential(this.count(),
                credential.getUrl(), credential.getUsername(),
                credential.getKey(), credential.getPassword(), credential.getUserId()));
    }

    public String generateKey(){
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        return encodedKey;
    }
    public String encrypt(String password, String encodedKey){
        String encryptedPassword = encryptionService.encryptValue(password, encodedKey);
        return encryptedPassword;
    }

    public String decrypt(String password, String encodedKey){
        String decryptedPassword = encryptionService.decryptValue(password, encodedKey);
        return decryptedPassword;
    }

    public int update(Credential credential) {
        return credentialMapper.update(credential);
    }

    private Integer count() {
        return credentialMapper.count();
    }

    public List<Credential> findByUser(Integer id) {
        return credentialMapper.findByUser(id);
    }

    public Credential findById(Integer id){
        return credentialMapper.findById(id);
    }

    public void delete(Integer id) { credentialMapper.delete(id); }
}