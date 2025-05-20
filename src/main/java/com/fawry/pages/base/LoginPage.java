package com.fawry.pages.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends PageBase {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    By mailLocator = By.xpath("//input[@id='userId']");
    By passwordLocator = By.xpath("//input[@id='password']");
    By loginBtn = By.id("loginbtn");


    public boolean loginSuccessfully(String mail, String password) {
        HomePage homePage = new HomePage(driver);
        By welcomeMsg = homePage.getWelcomeMsg();
        return login(mail, password, mailLocator, passwordLocator, loginBtn, welcomeMsg);
    }

}
