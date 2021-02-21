package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.util.Assert;

public class HomePage {

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "btnLogout")
    private WebElement logoutButton;

    //for note
    @FindBy(id = "nav-notes-tab")
    private WebElement btnNavNotesTab;
    @FindBy(id = "btnAddNote")
    private WebElement btnAddNote;
    @FindBy(id = "note-title")
    private WebElement noteTitle;
    @FindBy(id = "note-description")
    private WebElement noteDescription;
    @FindBy(id = "btnNoteSubmit")
    private WebElement btnNoteSubmit;

    //for credential
    @FindBy(id = "nav-credentials-tab")
    private WebElement btnNavCredentialsTab;
    @FindBy(id = "btnAddCredential")
    private WebElement btnAddCredential;
    @FindBy(id = "credential-url")
    private WebElement credentialURL;
    @FindBy(id = "credential-username")
    private WebElement credentialUsername;
    @FindBy(id = "credential-password")
    private WebElement credentialPassword;
    @FindBy(id = "btnCredentialSubmit")
    private WebElement btnCredentialSubmit;


    public HomePage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void logout(){
        this.logoutButton.click();
    }

    public void createNote(Note note) throws InterruptedException {
        btnNavNotesTab.click();
        Thread.sleep(1000);
        btnAddNote.click();
        Thread.sleep(1000);
        noteTitle.sendKeys(note.getNoteTitle());
        Thread.sleep(1000);
        noteDescription.sendKeys(note.getNoteDescription());
        Thread.sleep(1000);
        btnNoteSubmit.click();
    }

    public void verifyNoteExists(Note note, WebDriver driver) throws InterruptedException {
        btnNavNotesTab.click();
        Thread.sleep(1000);
        boolean found = false;
        if(driver.findElement(By.id("btn-edit-" + note.getNoteId())) != null){
            found = true;
        }
        Assertions.assertTrue(found);
    }

    public void verifyNoteDeleted(Note note, WebDriver driver) throws InterruptedException {
        btnNavNotesTab.click();
        Thread.sleep(1000);
        boolean found = false;
        if(driver.findElement(By.id("btn-edit-" + note.getNoteId())) != null){
            found = true;
        }
        Assertions.assertFalse(found);
    }

    public void editNote(Note note, WebDriver driver) throws InterruptedException {
        WebElement btnEdit = driver.findElement(By.id("btn-edit-" + note.getNoteId()));
        btnEdit.click();
        Thread.sleep(1000);
        noteTitle.sendKeys(" edited");
        Thread.sleep(1000);
        noteDescription.sendKeys(" updated");
        Thread.sleep(1000);
        btnNoteSubmit.click();
    }

    public void deleteNote(Note note, WebDriver driver) {
        WebElement btnDelete = driver.findElement(By.linkText("Delete"));
        btnDelete.click();
    }


    public void createCredential(Credential credential) throws InterruptedException {
        btnNavCredentialsTab.click();
        Thread.sleep(1000);
        btnAddCredential.click();
        Thread.sleep(1000);
        credentialURL.sendKeys(credential.getUrl());
        Thread.sleep(1000);
        credentialUsername.sendKeys(credential.getUsername());
        Thread.sleep(1000);
        credentialPassword.sendKeys(credential.getPassword());
        Thread.sleep(1000);
        btnCredentialSubmit.click();
    }

    public void verifyCredentialExists(Credential credential, WebDriver driver) throws InterruptedException {
        btnNavCredentialsTab.click();
        Thread.sleep(1000);
        boolean found = false;
        if(driver.findElement(By.id("btn-edit-credential-" + credential.getCredentialId())) != null){
            found = true;
        }
        Assertions.assertTrue(found);
    }

    public void verifyCredentialDeleted(Credential credential, WebDriver driver) throws InterruptedException {
        btnNavCredentialsTab.click();
        Thread.sleep(1000);
        boolean found = false;
        if(driver.findElement(By.id("btn-edit-credential-" + credential.getCredentialId())) != null){
            found = true;
        }
        Assertions.assertFalse(found);
    }

    public void editCredential(Credential credential, WebDriver driver) throws InterruptedException {
        WebElement btnEdit = driver.findElement(By.id("btn-edit-credential-" + credential.getCredentialId()));
        btnEdit.click();
        Thread.sleep(1000);
        credentialURL.sendKeys("z");
        Thread.sleep(1000);
        btnCredentialSubmit.click();
    }

    public void deleteCredential(Credential credential, WebDriver driver) {
        WebElement btnDelete = driver.findElement(By.linkText("Delete"));
        btnDelete.click();
    }
}

