package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "btnLogin")
    private WebElement submitButton;

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password){
        this.inputUsername.sendKeys(username);
        this.inputPassword.sendKeys(password);
        this.submitButton.click();
    }

    public void login(User user) throws InterruptedException {
        this.inputUsername.sendKeys(user.getUsername());
        Thread.sleep(1000);
        this.inputPassword.sendKeys(user.getPassword());
        Thread.sleep(1000);
        this.submitButton.click();
    }

}

