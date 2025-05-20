package com.fawry.pages.b2BProduct;

import com.fawry.constants.GeneralConstants;
import com.fawry.constants.PathConstants;
import com.fawry.constants.b2bProduct.B2BProductFile_NameConstants;
import com.fawry.constants.b2bProduct.ProductDataConstant;
import com.fawry.pages.base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class AddPricingSchema extends PageBase {
    public AddPricingSchema(WebDriver driver) {
        super(driver);
    }


    private static final Properties URl = propertiesReader.loadPropertiesFromFile(B2BProductFile_NameConstants.Product_PricingList_File_Name);


    public void navigateToAddPricingSchema(String environment) throws Exception {
        navigateTo(URl.getProperty(ProductDataConstant.Product_AddPricing), environment);
    }

    LocalDate today = LocalDate.now();
    String todayDate = today.format(DateTimeFormatter.ofPattern("d"));
    ;
    LocalDate tomorrow = LocalDate.now().plusDays(1);
    String day = tomorrow.format(DateTimeFormatter.ofPattern("d"));

    LocalDate AfterTomorrow = LocalDate.now().plusDays(2);
    String dayAfterTomorrow = AfterTomorrow.format(DateTimeFormatter.ofPattern("d"));
    private By selectDropdown = By.xpath("//span[text()='Select...']");
    private By searchInsideDrop = By.xpath("//input[contains(@class,'p-dropdown-filter')]");
    private By resultOfSearch = By.xpath("//ul//li//div[text()='NewProduct225']");
    private By resultOfAnotherProduct = By.xpath("//ul//li//div[text()='Persil']");
    private By newPrice = By.xpath("//input[@name='newPrice']");
    private By newPriceRequired = By.xpath("//input[@name='newPrice']//parent::span//parent::p-inputnumber");
    private By dateField = By.xpath("//p-calendar//input[@name='applyDate']");
    private By dateToSelect = By.xpath("//span[text()='" + day + "'and not(contains(@class,'disabled'))]");
    private By dateToSelectAfterTomorrow = By.xpath("//span[text()='" + dayAfterTomorrow + "'and not(contains(@class,'disabled'))]");

    private By todayDateSelect = By.xpath("//span[text()='" + todayDate + "']");
    private By assertCategory = By.xpath("//p-dropdown[@name='category']");
    private By category = By.xpath("//p-dropdown[@name='category']//div[contains(@class,'p-dropdown-clearable')]");
    private By categoryBtn = By.xpath("//p-dropdown[@name='category']//span[contains(@class,'p-dropdown-trigger-icon pi')]");
    private By categoryList = By.xpath("//*[@class='p-ripple p-element p-dropdown-item']");
    private By saveBtn = By.xpath("//button//span[text()='Save']");

    private By toastMessage = By.xpath("//div[text()='Pricing Schema Added']");
    private By pricingSchema = By.xpath("//span[text()='Pricing Schema']");
    private By addPricingSchema = By.xpath("//span[text()='Add Pricing Schema']");
    private By uom = By.xpath("//tr//td[5]");
    private By img = By.xpath("//tr//td[1]//child::img");

    private By productName = By.xpath("//tr//td[2]");
    private By persilProductName = By.xpath("//tr//td[2][text()='Persil']");
    private By productDropdown = By.xpath("//span[contains(@class,'p-dropdown-trigger-icon pi pi-chevron-down')]");

    private By pricingSchemaList = By.xpath("//h4[text()='Pricing Schema List']");
    private By cancelBtn = By.xpath("//button[contains(@class,'cancelBtn')]");
    private By closeCategory = By.xpath("//p-dropdown[@name='category']//i[contains(@class,'p-dropdown-clear-icon')]");
    private By closeProduct = By.xpath("//i[contains(@class,'p-dropdown-clear-icon')]");
    private By selectCategory = By.xpath("//span[contains(@class,'placeholder')]");

    private By selectProduct = By.xpath("//span[contains(@class,'placeholder')]");
    private By Currency = By.xpath("//tr//td[4]");
    private By categoryAssert = By.xpath("//span[text()='test code']");

    private By uploadBatchBtn = By.xpath("//span[text()='Upload Batch']");
    private By downloadSampleFile = By.xpath("//span[text()='Download sample file']");
    private By cancelUploadBtn = By.xpath("//button[contains(@class,'cancelBtn')]");
    private By chooseFileUpload = By.xpath("//input[@class='ng-star-inserted']");
    private By chooseFile = By.xpath("//span[text()='Choose File']");
    public static final String downloadDirectoryPath = System.getProperty(GeneralConstants.USER_DIR) + pathsProperties.getProperty(PathConstants.DOWNLOAD_DIRECTORY);
    public static final String uploadDirectoryPath = System.getProperty(GeneralConstants.USER_DIR) + pathsProperties.getProperty(PathConstants.UPLOAD_DIRECTORY);

    private By invalidMessage = By.xpath("//span[contains(text(),'Invalid file type')]");
    private By ValidMessageForUploadFile = By.xpath("//div[text()='file uploaded successfully']");

    public void setNewPrice(String price) throws Exception {
        waitForElementsToBePresent(newPrice);
        waitForElementToBeClickable(newPrice);
        setText(newPrice, price);
    }

    public void openDatePicker() throws Exception {
        waitForVisibility(dateField);
        clickJSE(dateField);
    }

    public void selectDate() throws Exception {
        waitForElementToBeClickable(dateToSelect);
        clickJSE(dateToSelect);
    }

    public Boolean selectToday() {
        System.out.println(getAttributeValue(todayDateSelect, "class"));
        return getAttributeValue(todayDateSelect, "class").contains("disabled");
    }

    public void selectDifferentDate() throws Exception {
        clickJSE(dateToSelectAfterTomorrow);
    }

    public void selectCategory() throws Exception {
        clickJSE(category);
        selectElementFromList(categoryList, 1);

    }

    public void selectDifferentCategory() throws Exception {
        clickJSE(category);
        selectElementFromList(categoryList, 3);

    }

    public void savePrice() throws Exception {
        clickJSE(saveBtn);
    }

    public void accessProductDropDown() throws Exception {
        clickJSE(selectDropdown);
    }


    public void searchByProduct(String product) throws Exception {
        setText(searchInsideDrop, product);

    }

    public void selectProductFromSearch() throws Exception {

        clickJSE(resultOfSearch);
        waitForElementsToBePresent(newPrice);

    }

    public void selectAnotherProduct() throws Exception {
        clickJSE(resultOfAnotherProduct);
        waitForElementsToBePresent(newPrice);
    }

    public Boolean displayToastMessage() {
        waitForElementToBePresent(toastMessage);
        waitForVisibility(toastMessage);
        return isDisplayed(toastMessage);
    }

    public void pricingSchemaAccess() throws Exception {

        clickJSE(pricingSchema);
    }

    public void addPricingSchema() throws Exception {
        clickJSE(addPricingSchema);
    }

    public boolean newPriceRequired() {
        System.out.println(getAttributeValue(newPriceRequired, "class"));
        return getAttributeValue(newPriceRequired, "class").contains("ng-invalid ng-dirty");
    }

    public boolean uomAssertion() {
        System.out.println(driver.findElement(uom).getText());
        return isDisplayed(uom);
    }

    public boolean uomNotEmpty() {
        return driver.findElement(uom).getText().trim().isEmpty();
    }

    public boolean imgDisplay() {
        System.out.println(driver.findElement(img).getAttribute("src"));
        return isDisplayed(img);
    }

    public boolean productNameDisplay() {
        return isDisplayed(productName);
    }

    public boolean displayPricingSchemaList() {
        return isDisplayed(pricingSchemaList);

    }

    public void cancelBtn() throws Exception {

        clickJSE(cancelBtn);
    }

    public void closeCategory() throws Exception {
        clickJSE(closeCategory);
    }

    public boolean displaySelectCategory() {
        return isDisplayed(selectCategory);
    }


    public void closeProduct() throws Exception {
        clickJSE(closeProduct);
    }

    public boolean displaySelectProduct() {
        return isDisplayed(selectProduct);
    }

    public boolean mandatoryCategory() {
        return driver.findElement(assertCategory).getAttribute("class").contains("ng-invalid ng-dirty");
    }


    public boolean currency() throws Exception {
        System.out.println(getText(Currency));
        return getText(Currency).contains("EG");
    }


    public boolean priceAssertion() throws Exception {
        try {
            System.out.println(getText(Currency));
            return getText(Currency).contains("100 EG");
        } catch (Exception e) {
            return false;
        }
    }


    public void openListOFProduct() throws Exception {
        clickJSE(productDropdown);
        clear(searchInsideDrop);
    }

    public boolean displayNewProduct() throws Exception {
        waitForVisibility(persilProductName);
        return getText(persilProductName).contains("Persil");
    }

    public boolean displayCategory() {
        return isDisplayed(categoryAssert);
    }

    public void openCategoryList() throws Exception {
        clickJSE(categoryBtn);
    }

    public void uploadBatch() throws Exception {
        clickJSE(uploadBatchBtn);
    }

    public void downloadSampleFile() throws Exception {
        clickJSE(downloadSampleFile);
    }

    public void cancelBatchUpload() throws Exception {
        clickJSE(cancelUploadBtn);
    }

    public Boolean downloadSampleSuccess() throws IOException {
        waitForFileToBeDownloaded(downloadDirectoryPath + "prices-sampleFile.xlsx");
        System.out.println(downloadDirectoryPath);
        return !isDirEmpty(downloadDirectoryPath);

    }

    public void uploadInvalidFormat() {
        uploadFile(chooseFileUpload, uploadDirectoryPath + "Pam Naps.pdf");
        System.out.println(uploadDirectoryPath + "Pam Naps.pdf");
    }

    public void uploadValidSheet() {
        uploadFile(chooseFileUpload, uploadDirectoryPath + "prices-sampleFile.xlsx");
        System.out.println(uploadDirectoryPath + "prices-sampleFile.xlsx");
    }


    public void uploadFile() throws Exception {
        clickJSE(chooseFile);
    }

    public boolean invalidMessage() {
        waitForVisibility(invalidMessage);
        return isDisplayed(invalidMessage);
    }

    public boolean validUploadFileMessage() {
        waitForVisibility(ValidMessageForUploadFile);
        return isDisplayed(ValidMessageForUploadFile);
    }

    public void deleteFileAfterAssert(String pathDownload) {
        //waitForFileToBeDownloaded(downloadDirectoryPath + "Prices-Report.xlsx");
        deleteFile(downloadDirectoryPath + pathDownload);
    }
}
