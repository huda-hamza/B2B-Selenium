package com.fawry.pages.B2BMerchant;

import com.fawry.constants.B2BMerchantFile_NameConstants;
import com.fawry.constants.MerchantdataConstant;
import com.fawry.constants.ProductDataConstant;
import com.fawry.pages.base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class PricingSchemaList extends PageBase {
    public PricingSchemaList(WebDriver driver) {
        super(driver);
    }


    private static final Properties URl = propertiesReader.loadPropertiesFromFile(B2BMerchantFile_NameConstants.Product_PricingList_File_Name);
    private By productName = By.name("name");
    private By SKU = By.name("skuCode");
    private By Barcode = By.name("barCode");
    private By searchBtn = By.xpath("//button//span[text()='Search']");
    private By plusIcon = By.xpath("//button//span[@class='p-button-icon pi pi-plus']");
    private By minusIcon = By.xpath("//button//span[@class='p-button-icon pi pi-minus']");
    private By currentDate =By.xpath("//tr[1]//td[5]");
    private By trash=By.xpath("//button[contains(@class,'p-element p-ripple p-button-outline')]");

    public void navigateToPricingListPage(String environment) throws Exception {
        navigateTo(URl.getProperty(ProductDataConstant.Product_PricingList), environment);
    }

    public void productName(String Name) throws Exception {
        setText(productName, Name);
    }

    public void skuNumber(String Number) throws Exception {
        setText(SKU, Number);
    }

    public void barCode(String Code) throws Exception {
        setText(Barcode, Code);
    }

    public void searchBtn() throws Exception {
        clickJSE(searchBtn);
    }

    public void plusBtn() throws Exception {
        waitForElementToBeClickable(plusIcon);
        clickJSE(plusIcon);
    }


    public Boolean SkuAndBarcodeDisplayed() {

        try {
           if(isDisplayed(Barcode) && isDisplayed(SKU)) return true;
            else return false;
            } catch (Exception e) {
            return false;
        }

    }

    public void minusBtn() throws Exception {
        clickJSE(minusIcon);
    }

    public Boolean trashClickable() throws Exception {
        //clickJSE(trash);
        return findElement(trash).isEnabled();

    }

    public String dateResult() throws Exception {
        System.out.println(getText(currentDate));
        return getText(currentDate);

    }

    public static boolean isDateLessThanToday(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateStr, formatter);
        LocalDate today = LocalDate.now();
        return!date.isAfter(today);
    }
}
