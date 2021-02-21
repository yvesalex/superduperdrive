package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    @FindBy(id = "inputFirstName")
    private WebElement inputFirstName;

    @FindBy(id = "inputLastName")
    private WebElement inputLastName;

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(css = "#btnSignup")
    private WebElement submitButton;

    public SignupPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void signup(String firstName, String lastName, String username, String password){
        this.inputFirstName.sendKeys(firstName);
        this.inputLastName.sendKeys(lastName);
        this.inputUsername.sendKeys(username);
        this.inputPassword.sendKeys(password);
        this.submitButton.click();
    }

    public void signup(User user) throws InterruptedException {
        this.inputFirstName.sendKeys(user.getFirstname());
        Thread.sleep(1000);
        this.inputLastName.sendKeys(user.getLastname());
        Thread.sleep(1000);
        this.inputUsername.sendKeys(user.getUsername());
        Thread.sleep(1000);
        this.inputPassword.sendKeys(user.getPassword());
        Thread.sleep(1000);
        this.submitButton.click();
    }

}

