package com.fawry.pages.b2BProduct;

import com.fawry.constants.b2BMerchant.B2BMerchantFile_NameConstants;
import com.fawry.constants.b2bProduct.B2BProductFile_NameConstants;
import com.fawry.constants.b2bProduct.ProductDataConstant;
import com.fawry.pages.base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class ProductBranchManagement extends PageBase {
    public ProductBranchManagement(WebDriver driver) {
        super(driver);
    }

    private static final Properties URl = propertiesReader.loadPropertiesFromFile(B2BProductFile_NameConstants.productBranchManagement_File_Name);

    private By selectBranch = By.xpath("//span[text()='Select Branch']");
    private By branchStatus = By.xpath("//span[contains(@class,'p-message-summary')]");
    private By noRecordDisplay = By.xpath("//tr//td");
    private By branches = By.xpath("//p-dropdown[contains(@class,'p-element p-inputwrapper')]//div[@class='p-dropdown p-component']");
    private By branchList = By.xpath("//li[@class='p-ripple p-element p-dropdown-item']");
    private By productName = By.xpath("//h6[@class='product_name']");
    private By searchBtn = By.xpath("//button[contains(@class,'primary-button ladda-button')]");

    private By ellipsesBtn = By.xpath("//p-button//button[contains(@class,'p-component p-button-icon-only')]");
    private By changeStatusOFProduct = By.xpath("//span[@class='p-inputswitch-slider']");

    private By confirmationMessage = By.xpath("//div[text()='Operation done successfully!']");
    private By productStatus = By.xpath("//tr//td[7]//div");
    private By activeProduct = By.xpath("//app-product-card//div[text()='Active products' or text()='lbl-active-products']");
    private By inActiveProduct = By.xpath("//app-product-card//div[text()='Inactive products' or text()='lbl-inactive-products']");
    private By aboutGetOutOfStock = By.xpath("//app-product-card//div[text()='About to get Out of Stock' or text()='lbl-about-out-of-stock']");
    private By aboutOutOfStockAssert = By.xpath("//span[text()='ABOUT_OUT_OF_STOCK']");
    private By outOfStock = By.xpath("//app-product-card//div[text()='Out of Stock' or text()='lbl-out-of-stock']");
    private By branchStock = By.xpath("//tr//td[6]");

    private By out_OF_Stock = By.xpath("//span[text()='OUT_OF_STOCK']");

    private By productNameSearch = By.xpath("//input[@Name='name']");

    private By moreSearchOption = By.xpath("//button[contains(@class,'p-element p-ripple secondary-button')]");

    private By lessSearchOption = By.xpath("//button[contains(@class,'p-element p-ripple p-button secondary-button ')]");
    private By sku = By.xpath("//input[@name='skuCode']");
    private By barcode = By.xpath("//input[@name='barcode']");
    private By brandDrop = By.xpath("//p-dropdown[@name='brand']//span[contains(@class,'p-dropdown-trigger-icon')]");
    private By brandResult = By.xpath("//tr//td[3]");
    private By list = By.xpath("//li[@class='p-ripple p-element p-dropdown-item']");
    private By parentCategory = By.xpath("//p-dropdown[@name='category']//span[contains(@class,'p-dropdown-trigger-icon')]");
    private By parentCategoryResult = By.xpath("//tr//td[4]");
    private By skuResult = By.xpath("//tr//td[5]");

    private By export = By.xpath("//button[contains(@class,'export_btn')]");
    private By inventory = By.xpath("//span[text()='Inventory']");

    private By updateInventoryBtn = By.xpath("//span[text()='Update Inventory']");
    private By updateInventoryField = By.xpath("//input[@class='ng-star-inserted']");

    private By messageForInvalidFile = By.xpath("//span[contains(text(),'Invalid file type')]");

    private By successMessageForUploadFile = By.xpath("//div[text()='file uploaded successfully']");
    private By automationBranch = By.xpath("//span[text()='Branch_automation ']");
    private By  numberOfOutOFStock=By.xpath("//div[text()='Out of Stock']//parent::div//following-sibling::div//div");


    public void navigateToBranchManagement(String environment) throws Exception {
        navigateTo(URl.getProperty(ProductDataConstant.Branch_Management), environment);
    }

    public boolean noBranchMessage() {
        System.out.println(driver.findElement(branchStatus).getText());
        return driver.findElement(branchStatus).getText().contains("No branch selected");
    }

    public boolean activeBranch() throws Exception {
        waitForElementToBeClickable(searchBtn);
        System.out.println(driver.findElement(branchStatus).getText());
        return driver.findElement(branchStatus).getText().contains("Branch is active");
    }

    public boolean noRecordDisplay() {
        System.out.println(driver.findElement(noRecordDisplay).getText());
        return driver.findElement(noRecordDisplay).getText().contains("No records to display");
    }

    public void selectBranch() throws Exception {
        clickJSE(branches);
        waitForElementsToBePresent(branchList);
        selectElementFromList(branchList, 0);
        scrollDownJSE(branchStatus);
        waitForTextToChange(branchStatus, "No branch selected");
    }

    public void selectAnotherBranch() throws Exception {
        clickJSE(branches);
        waitForElementsToBePresent(branchList);
        selectElementFromList(branchList, 1);
        scrollDownJSE(branchStatus);
        waitForTextToChange(branchStatus, "No branch selected");
    }

    public boolean displayProductName(String productResult) {
        waitForElementsToBePresent(productName);
        System.out.println(driver.findElement(productName).getText());
        return driver.findElement(productName).getText().contains(productResult);
    }

    public void updateStatusOFProduct() throws Exception {
        scrollToElementJSE(ellipsesBtn);
        clickJSE(ellipsesBtn);
        clickJSE(changeStatusOFProduct);

    }

    public boolean confirmationMessage() {
        return isDisplayed(confirmationMessage);


    }

    public String statusOfProductINACTIVE() throws Exception {
        // scrollToElementJSE(productStatus);
        waitForTextToChange(productStatus, "ACTIVE");
        System.out.println(driver.findElement(productStatus).getText());
        return driver.findElement(productStatus).getText();
    }

    public String statusOfProductACTIVE() throws Exception {
        waitForTextToChange(productStatus, "INACTIVE");
        System.out.println(driver.findElement(productStatus).getText());
        return driver.findElement(productStatus).getText();
    }

    public void selectActiveProduct() throws Exception {
        clickJSE(activeProduct);
        scrollToPageEnd();
    }

    public void selectInactiveProduct() throws Exception {
        clickJSE(inActiveProduct);
        scrollToPageEnd();
    }

    public void selectAboutGetOutOFStock() throws Exception {
        clickJSE(aboutGetOutOfStock);
        scrollToElementJSE(aboutOutOfStockAssert);

    }

    public boolean productStatusList() {
        List<WebElement> status = driver.findElements(productStatus);
        for (WebElement st : status) {
            String variable = st.getText();
            if (!variable.equals("ACTIVE")) {
                return false;
            }
        }
        return true;
    }


    public boolean productINACTIVEStatusList() {
        waitForElementsToBePresent(productStatus);
        List<WebElement> status = driver.findElements(productStatus);
        for (WebElement st : status) {
            String variable = st.getText();
            if (!variable.equals("INACTIVE")) {
                return false;
            }
        }
        return true;
    }


    public boolean displayOutOfStock() {
        System.out.println(driver.findElement(aboutOutOfStockAssert).getText());
        return isDisplayed(aboutOutOfStockAssert);
    }

    public void selectOutOfStock() throws Exception {
        clickJSE(outOfStock);
    }


    public boolean outOFStock() {
        waitForElementToBeClickable(searchBtn);
        waitForVisibilityOfList(branchStock);
        List<WebElement> outOfStock = driver.findElements(branchStock);
        for (WebElement out : outOfStock) {
            String variable = out.getText();
            System.out.println(variable);
            if (!variable.contains("0")) {
                return false;
            }
        }
        return true;
    }

    public boolean elementInList(By locator, String TargetText) {
        List<WebElement> elements = driver.findElements(locator);
        for (WebElement element : elements) {
            String text = element.getText();
            System.out.println(text);
            if (!text.contains(TargetText)) {
                return false;
            }
        }
        return true;
    }

    public void productName() throws Exception {
        setText(productNameSearch, "yuyu");
    }

    public void invalidProductName() throws Exception {
        setText(productNameSearch, "yuyu1");
    }

    public void search() throws Exception {
        clickJSE(searchBtn);
        waitForElementToBeClickable(searchBtn);
    }

    public boolean validSkuResult() {
        waitForVisibility(skuResult);
        System.out.println(driver.findElement(skuResult).getText());
        return driver.findElement(skuResult).getText().contains("123400");
    }

    public void accessMoreSearchOption() throws Exception {
        clickJSE(moreSearchOption);
    }

    public void lessMoreSearchOption() throws Exception {
        clickJSE(lessSearchOption);
    }

    public Boolean SkuAndBarcodeDisplayed() {

        try {
            if (isDisplayed(barcode) && isDisplayed(sku)) return true;
            else return false;
        } catch (Exception e) {
            return false;
        }

    }

    public int numberOfOutOFStock() throws Exception {
        System.out.println(getText(numberOfOutOFStock));
        return Integer.parseInt((getText(numberOfOutOFStock)));
    }

    public void searchSku(String skuNumber) throws Exception {
        setText(sku, skuNumber);//

    }

    public void searchBarcode(String barcodeNumber) throws Exception {
        setText(barcode, barcodeNumber);
    }


    public void accessParentCategory() throws Exception {
        clickJSE(parentCategory);
        selectElementFromList(list, 4);
        scrollToPageEnd();
    }

    public void accessSubCategory() throws Exception {
        clickJSE(parentCategory);
        selectElementFromList(list, 0);
        scrollToPageEnd();
    }

    public boolean displayParentCategory() {
        System.out.println(driver.findElement(parentCategoryResult).getText());
        return driver.findElement(parentCategoryResult).getText().contains("new category for product");
    }

    public boolean displaySubCategory() {
        System.out.println(driver.findElement(parentCategoryResult).getText());
        return driver.findElement(parentCategoryResult).getText().contains("samy4158");
    }

    public void selectBrand() throws Exception {
        clickJSE(brandDrop);
        selectElementFromList(list, 1);

    }

    public boolean displayBrand() {
        System.out.println(driver.findElement(brandResult).getText());
        return driver.findElement(brandResult).getText().contains("testable11");
    }

    public void export() throws Exception {
        clickJSE(export);
        clickJSE(inventory);
    }

    public Boolean exportDownSuccess() throws IOException {
        waitForFileToBeDownloaded(downloadDirectoryPath + "InventoryTracking.csv");
        System.out.println(downloadDirectoryPath);
        return !isDirEmpty(downloadDirectoryPath);

    }

    public void accessUpdateInventory() throws Exception {
        clickJSE(updateInventoryBtn);
    }

    public void uploadInValidFormat() {
        uploadFile(updateInventoryField, uploadDirectoryPath + "Pam Naps.pdf");
    }

    public void uploadValidFormat() {
        uploadFile(updateInventoryField, uploadDirectoryPath + "InventoryTracking.csv");

    }

    public boolean warningMessageForInvalidFormat() {
        return isDisplayed(messageForInvalidFile);
    }

    public boolean successMessage() {
        waitForVisibility(successMessageForUploadFile);
        return isDisplayed(successMessageForUploadFile);
    }

//    public boolean outOFStock1() throws InterruptedException {
//        List<WebElement> outOfStock = driver.findElements(branchStock);
//        return all(outOfStock, element -> element.getText().contains("0"));
//    }
//
//    private static <T> boolean all(List<T> list, Consumer<T, Boolean> predicate) {
//        Stream<T> stream = list.stream();
//        return st
//    }
//
//    @FunctionalInterface
//    interface Consumer<T, D> {
//        D accept(T element);
//    }

    public void selectAutomationBranch() throws Exception {
        clickJSE(branches);
        clickJSE(automationBranch);
    }


    public int countAllItems() throws InterruptedException {
        int totalCount = 0;

        while (true) {
            // Get items on the current page
            List<WebElement> rows = driver.findElements(By.xpath("//tbody//tr"));
            totalCount += rows.size();

            scrollToElementJSE(By.xpath("//button[contains(@class,'p-paginator-next ')]"));

            // Check if the "Next" button is disabled
            WebElement nextButton = driver.findElement(By.xpath("//button[contains(@class,'p-paginator-next ')]"));
            if (nextButton.getAttribute("class").contains("p-disabled")) {
                break; // Exit loop if "Next" button is disabled
            }

            // Click the "Next" button to move to the next page
            nextButton.click();
            try {
                Thread.sleep(1000); // Add a delay to wait for the page to load
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        return totalCount;
    }


}
