package com.fawry.pages.b2BOrder;

import com.fawry.constants.B2BOrderFile_NameConstants;
import com.fawry.constants.OrderDataConstant;
import com.fawry.pages.base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class SearchPage extends PageBase {
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    private static final Properties URl = propertiesReader.loadPropertiesFromFile(B2BOrderFile_NameConstants.GENERAL_Search_FILE_NAME);

    private By plusButton = By.xpath("//button[contains(@class,'secondary-button showandhide')]");
    private By fawryReferenceNumber = By.xpath("//p-chips[@id='orderRefNo']//input");

    private By resetButton = By.xpath("//button[contains(@class,'reset-btn')]");
    private By supplierList = By.xpath("//p-dropdown[@id='suppliers']//div//div[contains(@class,'p-dropdown-trigger')]");
    private By dropdownSupplierChoice = By.xpath("//li[contains(@class,'p-dropdown-item')]");
    private By searchBtn = By.xpath("//span[text()='Search']");

    private By supplierResultName = By.xpath("//tr//td[10]");
    private By clearSupplier = By.xpath("//div//i[contains(@class,'p-dropdown-clear-icon')]");
    private By messageDisplayed = By.xpath("//span[text()='You have to enter at least one search criteria']");
    private By textFieldInsideSupplierFilter = By.xpath("//input[contains(@class,'p-dropdown-filter')]");
    private By resultOfSearchFilter=By.xpath("//li[@class='p-dropdown-item p-ripple']//span");

    public void navigateToSearch(String environment) throws Exception {
        navigateTo(URl.getProperty(OrderDataConstant.Search), environment);
    }

//    public void accessPlusButton() throws Exception {
//        clickJSE(plusButton);
//    }
//
//    public void fawryReferenceNumber(String referenceNumber) throws Exception {
//        setText(fawryReferenceNumber, referenceNumber);
//    }

    public void accessResetButton() throws Exception {
        driver.navigate().refresh();
        clickJSE(resetButton);
    }

    public void chooseSupplier() throws Exception {
        clickJSE(supplierList);
        selectElementFromList(dropdownSupplierChoice, 0);

    }

    public String supplierNameResult() throws Exception {
        scrollIntoView(supplierResultName);
        System.out.println(getText(supplierResultName));
        return getText(supplierResultName);
    }

    public void searchBtn() throws Exception {
        clickJSE(searchBtn);
        waitForElementToBeClickable(searchBtn);
    }

    public void clearSupplierDropList() throws Exception {
        clickJSE(clearSupplier);

    }

    public Boolean displayErrorMessage() {
        try {

            return isDisplayed(messageDisplayed);
        } catch (Exception e) {
            return false;
        }

    }

    public void textSearchFunction(String Supplier) throws Exception {
        clickJSE(supplierList);
        setText(textFieldInsideSupplierFilter, Supplier);
    }

    public String resultOfSearchFilter() throws Exception {
        return getText(resultOfSearchFilter);
    }
}
