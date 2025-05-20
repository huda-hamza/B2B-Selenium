package com.fawry.pages.b2BProduct;

import com.fawry.constants.b2bProduct.B2BProductFile_NameConstants;
import com.fawry.constants.b2bProduct.ProductDataConstant;
import com.fawry.pages.base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;

public class PricingSchemaList extends PageBase {
    public PricingSchemaList(WebDriver driver) {
        super(driver);
    }


    private static final Properties URl = propertiesReader.loadPropertiesFromFile(B2BProductFile_NameConstants.Product_PricingList_File_Name);
    private By productName = By.name("name");
    private By SKU = By.name("skuCode");
    private By Barcode = By.name("barCode");
    private By searchBtn = By.xpath ("//span[text() = 'Search']//parent::button");
    private By plusIcon = By.xpath("//button//span[@class='p-button-icon pi pi-plus']");
    private By minusIcon = By.xpath("//button//span[@class='p-button-icon pi pi-minus']");
    private By currentDate = By.xpath("//tr[1]//td[5]");
    private By trash = By.xpath("//button[contains(@class,'p-element p-ripple p-button-outline')]");
    private By pricingSchemaList = By.xpath("//span[text()='Pricing Schema']");
    private By addPricingSchema = By.xpath("//span[text()='Add Pricing Schema']");
    private By products = By.xpath("//tbody//tr//td[1]");
    private By skus = By.xpath("//tbody//tr//td[2]");
    private By resultOfBarcode = By.xpath("//tr//td[text()=' ِشاي ليبتون باكت 250 جرام']");

    private By trashElments = By.xpath("//tr//td[7]//button");

    private By DeleteConfirmation = By.xpath("//span[text()='Delete']");
    private By confirmationMessageForDelete = By.xpath("//div[text()='Operation done successfully!']");
    private By messageForInvalidFile = By.xpath("//span[contains(text(),'Invalid file type')]");
    private By batchDelete = By.xpath("//span[text()='Batch Delete']");
    private By batchFile = By.xpath("//input[@class='ng-star-inserted']");
    private By messageUploadSuccess = By.xpath("//div[text()='file uploaded successfully']");
    private By exportBtn=By.xpath("//span[text()='Export']");
    private By price=By.xpath("//span[text()='Prices']");



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
        waitForElementToBeClickable(searchBtn);

    }

    public void plusBtn() throws Exception {
        waitForElementToBeClickable(plusIcon);
        clickJSE(plusIcon);
    }


    public Boolean SkuAndBarcodeDisplayed() {

        try {
            if (isDisplayed(Barcode) && isDisplayed(SKU)) return true;
            else return false;
        } catch (Exception e) {
            return false;
        }

    }

    public void minusBtn() throws Exception {
        clickJSE(minusIcon);
    }

    public Boolean trashClickable() throws Exception {

        return findElement(trash).isEnabled();

    }

    public void deletePricingSchema() throws Exception {
        List<WebElement> buttons =
                driver.findElements(trashElments);
        for (WebElement button : buttons) {
            if (button.isEnabled()) {
                clickJSE(button);
            }
        }
    }

    public boolean validateProductsName(String targetProductName) {
        List<WebElement> productsName = driver.findElements(products);
        System.out.println(productsName.size());
        for (WebElement product : productsName) {
            String elementText = product.getText();
            if (elementText.equals(targetProductName)) {
                System.out.println("Product Found: " + product.getText());
                return true;
            }
        }
        System.out.println("No matching product found for: " + targetProductName);
        return false;
    }


    public boolean validateSKU(String targetSkus) {
        List<WebElement> Skus = driver.findElements(skus);
        System.out.println(Skus.size());
        for (WebElement sku : Skus) {
            String elementText = sku.getText();
            if (elementText.equals(targetSkus)) {
                System.out.println("SKu Found: " + sku.getText());
                return true;
            }
        }
        System.out.println("No matching Sku found for: " + targetSkus);
        return false;
    }

    public boolean validateBarcode() {
        try {
            return isDisplayed(resultOfBarcode);

        } catch (Exception e) {
            return false;
        }
    }

    public String dateResult() throws Exception {
        System.out.println(getText(currentDate));
        return getText(currentDate);

    }

    public static boolean isDateLessThanToday(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateStr, formatter);
        LocalDate today = LocalDate.now();
        return !date.isAfter(today);
    }

    public void accessPricingSchemaList() throws Exception {
        clickJSE(pricingSchemaList);
    }

    public void addPricingSchema() throws Exception {
        clickJSE(addPricingSchema);
    }

    public void deleteConfirmation() throws Exception {

        clickJSE(DeleteConfirmation);
    }

    public Boolean confirmationMessageForDelete() {
        try {
            waitForVisibility(confirmationMessageForDelete);
            if (isDisplayed(confirmationMessageForDelete))
                return true;
        } catch (Exception e) {

        }
        return false;

    }

    public void batchDelete() throws Exception {
        clickJSE(batchDelete);
    }

    public void uploadInvalidFormat() {
        uploadFile(batchFile, uploadDirectoryPath + "Pam Naps.pdf");
        System.out.println(uploadDirectoryPath + "Pam Naps.pdf");
    }

    public void uploadValidFormat() {
        uploadFile(batchFile, uploadDirectoryPath + "prices.xlsx");
        System.out.println(uploadDirectoryPath + "prices.xlsx");
    }

    public boolean warningMessageForInvalidFile() {
        try {waitForVisibility(messageForInvalidFile);
            if (isDisplayed(messageForInvalidFile))
                return true;
        } catch (Exception e) {

        }
        return false;
    }


    public Boolean updateSuccessMessage() throws IOException {
        try {
            waitForElementsToBePresent(messageUploadSuccess);
            return isDisplayed(messageUploadSuccess);
        } catch (Exception e) {
            return false;
        }
    }

    public void deleteFileAfterAssert(String pathDownload) {
        //waitForFileToBeDownloaded(downloadDirectoryPath + "Prices-Report.xlsx");
        deleteFile(downloadDirectoryPath + pathDownload);
    }

    public void exportFile() throws Exception {
        clickJSE(exportBtn);
        clickJSE(price);

       // waitForFileToBeDownloaded();
    }

    public Boolean exportDownSuccess() throws IOException {
        waitForFileToBeDownloaded(downloadDirectoryPath + "prices.xlsx");
        System.out.println(downloadDirectoryPath);
        return !isDirEmpty(downloadDirectoryPath);

    }
}



