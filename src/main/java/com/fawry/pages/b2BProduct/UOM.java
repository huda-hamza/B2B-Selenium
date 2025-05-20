package com.fawry.pages.b2BProduct;

import com.fawry.constants.b2bProduct.B2BProductFile_NameConstants;
import com.fawry.constants.b2bProduct.ProductDataConstant;
import com.fawry.pages.base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class UOM extends PageBase {
    public UOM(WebDriver driver) {
        super(driver);
    }

    private static final Properties URl = propertiesReader.loadPropertiesFromFile(B2BProductFile_NameConstants.Product_UOM_File_Name);
    private By uOmEnglishName=By.xpath("//input[@name='nameEn']");
    private By uOmArabicName=By.xpath("//input[@name='nameAr']");
    private By uomCode=By.xpath("//input[@placeholder='Unit Of Measure Code']");
    private By saveBtn=By.xpath("//button[contains(@class,'saveBtn')]");
    private By cancelBtn=By.xpath("//button[contains(@class,'cancelBtn')]");
    private By messageSuccess =By.xpath("//*[text()='Operation done successfully!']");
    private By uomListMessage=By.xpath("//*[text()='Unit Of Measure List']");
    private By mandatedField=By.xpath("//input[contains(@class,'ng-invalid ng-dirty')]");

    private By messageEnglishAlreadyExist=By.xpath("//*[text()='UOM Name English Already Exist']");
    private By messageArabicAlreadyExist=By.xpath("//*[text()='UOM Name Arabic Already Exist']");

    private By messageCodeAlreadyExist=By.xpath("//*[text()='UOM Code Already Exist']");


    public void navigateToProductUOMPage(String environment) throws Exception {

        navigateTo(URl.getProperty(ProductDataConstant.Product_UOM_URL),environment);

    }
    public void setUomEnglishName(String EnglishName) throws Exception {
        setText(uOmEnglishName,EnglishName);
    }

    public void setUomEnglishNameWithSpace() throws Exception {
        setText(uOmEnglishName," ");
    }
    public void setUomArabicName(String ArabicName) throws Exception {
        setText(uOmArabicName,ArabicName);
    }

    public void setUomArabicNameWithSpace() throws Exception {
        setText(uOmArabicName," ");
    }

    public void setUomCode(String code) throws Exception {
        setText(uomCode,code);
    }

    public void saveUom() throws Exception {clickJSE(saveBtn);}

    public void cancelUom() throws Exception {clickJSE(cancelBtn);}


    public Boolean confirmationMessage()
    {
        return driver.findElement(messageSuccess).isDisplayed();
    }

    public Boolean uomListMessageDisplayed()
    {
        return driver.findElement(uomListMessage).isDisplayed();
    }

    public Boolean mandatedEnglishField()
    {
        if(driver.findElement(uOmEnglishName).getAttribute("class").contains("ng-invalid ng-dirty")) {
            System.out.println(driver.findElement(uOmEnglishName).getAttribute("class"));
            return true;
        }
        else
            return false;
    }

    public Boolean mandatedArabicField()
    {
        if(driver.findElement(uOmArabicName).getAttribute("class").contains("ng-invalid ng-dirty")) {
            System.out.println(driver.findElement(uOmArabicName).getAttribute("class"));
            return true;
        }
        else
            return false;
    }

    public Boolean ErrorMessageArabicNameAlreadyExist()
    {
        waitForVisibility(messageArabicAlreadyExist);
        return driver.findElement(messageArabicAlreadyExist).isDisplayed();
    }

    public Boolean ErrorMessageEnglishNameAlreadyExist()
    {
        waitForElementsToBePresent(messageEnglishAlreadyExist);
        return driver.findElement(messageEnglishAlreadyExist).isDisplayed();
    }

    public Boolean ErrorMessageCodeAlreadyExist()
    {
        waitForElementsToBePresent(messageCodeAlreadyExist);
        return driver.findElement(messageCodeAlreadyExist).isDisplayed();
    }
}
