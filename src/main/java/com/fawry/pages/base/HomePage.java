package com.fawry.pages.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends PageBase{

    public HomePage(WebDriver driver){
        super(driver);}
    By welcomeMsg = By.xpath("//fawry-header//small[contains(text(), 'Welcome')]");

    By AppIcon = By.xpath("//i[@class='appicon']") ;

    public By getWelcomeMsg() {
        return welcomeMsg;
    }
    public void openSpecificApp(By appLocator) throws Exception {
        openApplicationsList();
        if (appLocator != null) {
            clickJSE(appLocator);
        } else
            throw new Exception("Web element 'locator' is null .. it could not be located");
    }
    public void openApplicationsList() throws Exception {
        waitForVisibility(AppIcon);
        clickJSE(AppIcon);
    }


}
