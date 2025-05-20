package com.fawry.pages.b2BProduct;

import com.fawry.constants.b2bProduct.B2BProductFile_NameConstants;
import com.fawry.constants.b2bProduct.ProductDataConstant;
import com.fawry.pages.base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class UOMList extends PageBase {
    public UOMList(WebDriver driver) {
        super(driver);
    }

    private static final Properties URl = propertiesReader.loadPropertiesFromFile(B2BProductFile_NameConstants.Product_UOM_File_Name);
    private By editBtn = By.xpath("//button[contains(@class,'edit')]");
    private By trashBtn = By.xpath("//button[contains(@class,'p-button-danger')]");
    private By editEnglishName = By.name("nameEn");
    private By editArabicName = By.name("nameAr");
    private By uomCode = By.xpath("//h6[text() = 'Unit Of Measure Code']//following-sibling::input[contains(@class,'p-filled')]");
    private By saveBtn = By.xpath("//p-button[contains(@class,'savBtn ')]//button");
    //("//button//span[text()='Save']");
    private By cancelBtn = By.xpath("//button//span[text()='Cancel']");
    private By deleteBtn = By.xpath("//button//span[text()='Delete']");

    private By nextPage = By.xpath("//button[contains(@class,'p-paginator-next')]");
    private By messageSuccess = By.xpath("//*[text()='Operation done successfully!']");

    private By paginationBtn = By.xpath("//p-dropdown[@styleclass='p-paginator-rpp-options']//div[@role='button']");
    private By pagination100Page = By.xpath("//li//span[text()='100']");

    private By messageArabicAlreadyExist = By.xpath("//*[text()='UOM Name Arabic Already Exist']");

    private By messageEnglishAlreadyExist = By.xpath("//*[text()='UOM Name English Already Exist']");
    private By uomListMessage = By.xpath("//*[text()='Unit Of Measure List']");

    private By ExistValueEnglishNAme = By.xpath("//tr[1]//td[1]");

    public void navigateToUOMListPage(String environment) throws Exception {

        navigateTo(URl.getProperty(ProductDataConstant.Product_UOM_List), environment);

    }

    public void editUom() throws Exception {
        clickJSE(editBtn);
    }

    public String NameForExistEnglishNameForAssertion() throws Exception {
        return getText(ExistValueEnglishNAme);
    }

    public void ClearArabicName() throws Exception {
        driver.findElement(editArabicName).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        driver.findElement(editArabicName).sendKeys(Keys.DELETE);
    }


    public void ClearEnglishName() throws Exception {
        driver.findElement(editEnglishName).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        driver.findElement(editEnglishName).sendKeys(Keys.DELETE);
    }

    public void deleteUom() throws Exception {
        if (driver.findElement(trashBtn).isDisplayed())
            clickJSE(trashBtn);
    }

    public void cancelDeleteAndEditUom() throws Exception {
        clickJSE(cancelBtn);
    }

    public void confirmDelete() throws Exception {
        clickJSE(deleteBtn);
    }

    public void saveEdit() throws Exception {
        clickJSE(saveBtn);
    }

    public void setEditUOMArabic(String Arabic_Name) throws Exception {
        setText(editArabicName, Arabic_Name);
    }


    public void setEditUOMEnglish(String EnglishName) throws Exception {
        setText(editEnglishName, EnglishName);
    }

    public Boolean ConfirmationMessage() {
        try {
            waitForElementsToBePresent(messageSuccess);
            if (driver.findElement(messageSuccess).isDisplayed())
                return true;
            else return false;
        } catch (Exception e) {
            return false;
        }
    }


    public Boolean CodeNotEditable() throws Exception {
        waitForVisibility(uomCode);
        return isClickable(uomCode);
    }

    public void pagination() throws Exception {
        waitForVisibility(uomListMessage);
        scrollThenHoverAndClick(paginationBtn);
        clickJSE(pagination100Page);
    }

    public Boolean ErrorMessageArabicNameAlreadyExist() {
        waitForVisibility(messageArabicAlreadyExist);
        return driver.findElement(messageArabicAlreadyExist).isDisplayed();
    }


    public Boolean ErrorMessageEnglishNameAlreadyExist() {
        waitForElementsToBePresent(messageEnglishAlreadyExist);
        return driver.findElement(messageEnglishAlreadyExist).isDisplayed();
    }

    public Boolean DimmedSaveBtn() {
        System.out.println(driver.findElement(saveBtn).getAttribute("class"));

        if (driver.findElement(saveBtn).getAttribute("class").contains("disabled"))
            return true;
        else
            return false;
    }

}
