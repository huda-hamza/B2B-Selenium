package com.fawry.pages.b2BMerchant;

import com.fawry.constants.b2BMerchant.B2BMerchantFile_NameConstants;
import com.fawry.constants.b2BMerchant.MerchantdataConstant;
import com.fawry.pages.base.HomePage;
import com.fawry.pages.base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class RequestList extends PageBase {

    HomePage homePage;
    private static final Properties URl = propertiesReader.loadPropertiesFromFile(B2BMerchantFile_NameConstants.GENERAL_TestData_FILE_NAME);
    private By requestList = By.xpath("//a[@href='/b2b-customers/requests']");
    private By FawryID = By.name("merchantFawryId");
    private By searchButton = By.xpath("//button[contains (@class,'primary-button')]");
    private By tableResult = By.id("pr_id_10-table");
    private By row = By.tagName("tr");
    private By fawryIDColumn = By.xpath("//td[1]");
    private By requestTypeDrop = By.xpath("//*[@name='merchantType']/div/div[@aria-label=\"dropdown trigger\"]");
    private By requestTypeChoices = By.xpath("//*[contains(@class,'p-ripple p-element p-dropdown-item')]");
    private By newRequestType = By.xpath("//p-dropdownitem/li[@id='p-highlighted-option']");
    private By nodatafound = By.xpath("//td[text()='No Data found.']");
    private By secondRow = By.xpath("//div/table/tbody/tr[5]/td[1]");
    private By paginationBtn = By.xpath("//span[contains(@class,'p-paginator-icon')]/parent::button[contains(@class,'p-paginator-first')]");
    private By requesttyperesult = By.xpath("//*/tr/td[5]");
    //    private By LabelCount=By.xpath("//*[@class='p-menuitem-text ng-star-inserted']/div/span[@class='label_count']");
    private By clearButton = By.xpath("//button[contains(@class,'secondary-button')]");
    By B2bMerchant = By.xpath("//img[@alt='b2b-merchants']");
    private By Merchant_Name = By.xpath("//input[@name='merchantName']");
    private By merchantNameResult = By.xpath("//tr/td[text()='omar sameh']");
    private By exportButton = By.xpath("//div/div/button[contains(@class,'p-button-outlined')]");

    //By.xpath("//span[text()='Export all']//parent::button");
    private By monthDate = By.xpath("//button[contains(@class,'p-datepicker-month')]");
    private By yearDate = By.xpath("//button[contains(@class,'p-datepicker-year')]");
    private By nextButton = By.xpath("//button[contains(@class,'p-datepicker-next')]");
    private By preButton = By.xpath("//button[contains(@class,'p-datepicker-prev')]");
    private By requestDateIcon = By.xpath("//img[@src='assets/img/date-range.svg']");
    //By.xpath("//div/label/p-calendar[@name='registerationDate']");
    //"//label[contains(@class,'custom_calendar')]//child::img[@src='assets/img/date-range.svg']"
    private By requestDateInput = By.xpath("//p-calendar/span/input[@name='registerationDate']");
    private By updateBtn = By.xpath("//p-fileupload/div/span");
    private By enterExcelFile = By.xpath("//input[@class='ng-star-inserted']");
    private By merchantListDropdown = By.xpath("//span[contains(@class,'p-submenu-icon pi pi-angle-down ng-star-inserted')]");
    private By merchantList = By.xpath("//p-menubarsub/ul/li/a[@href='/b2b-customers/merchant']");
    private By categories = By.xpath("//span[text()='Categories']");
    private By defaultCategories = By.xpath("//app-status//div[contains(@class,'Default ng-star-inserted')]");
    private By requestDataResult = By.xpath("//div[@class='start_date']");
    private By requestDateResultlist = By.xpath("//tbody[contains(@class,'p-datatable-tbody')]//td[4]");
    private By SuccessMessage = By.xpath("//*[text()=\"Operation done successfully!\"]");
    private By requestTypeClose = By.xpath("//i[contains(@class,'p-dropdown-clear-icon pi pi-times ng-star-inserted')]");

    private By anyware = By.id("pr_id_5");

    public RequestList(WebDriver driver) {
        super(driver);
    }


    public void openB2BMerchant() throws Exception {
        homePage = new HomePage(driver);
        homePage.openSpecificApp(B2bMerchant);
        clickJSE(requestList);
        waitForInvisibility(nodatafound);
    }

    public void navigateToRequestListPage(String environment) throws Exception {
        navigateTo(URl.getProperty(MerchantdataConstant.Request_LIST_URL), environment);
        clickJSE(requestList);
        waitForVisibility(secondRow);

      //  navigateTo()
    }

    public void openRequestList() throws Exception {

        clickJSE(clearButton);

    }

    public void navigateToMerchantList() throws Exception {
        clickJSE(merchantListDropdown);
        clickJSE(merchantList);
        waitForVisibility(searchButton);
    }

    public void navigateToCategories() throws Exception {
        clickJSE(categories);
        waitForVisibility(defaultCategories);
    }

    public void enterDataonFawryAccountID(String fawryid) throws Exception {
        setText(FawryID, fawryid);
    }

    public void enterMerchantName(String Name) throws Exception {
        setText(Merchant_Name, Name);

    }

    public void selectNewFromRequestType() throws Exception {

        waitForElementToBeClickable(requestTypeDrop);
        clickJSE(requestTypeDrop);
        selectElementFromList(requestTypeChoices, 1);
    }

    public void searchButton() throws Exception {
        waitForElementToBeClickable(searchButton);
        clickJSE(searchButton);
        waitForElementToBeClickable(searchButton);
        waitForInvisibility(SuccessMessage);

    }

    public void exportAll() throws Exception {
        clickJSE(exportButton);
    }

    public Boolean exportDownSuccess() throws IOException {
        waitForFileToBeDownloaded(downloadDirectoryPath + "add-merchants-sample.xlsx");
        System.out.println(downloadDirectoryPath);
        return !isDirEmpty(downloadDirectoryPath);

    }

    public void deleteFileAfterAssert(String pathDownload) {
        deleteFile(downloadDirectoryPath + pathDownload);
    }

    public String dateResult() throws Exception {

        return getText(requestDataResult);
    }

    public Boolean results() {
        try {
            isDisplayed(fawryIDColumn);
            System.out.println(getText(fawryIDColumn));
            return true;
        } catch (Exception e) {

            return false;
        }

    }

    public Boolean merchantNameResult() {
        try {
            waitForVisibility(merchantNameResult);
            isDisplayed(merchantNameResult);
            System.out.println(getText(merchantNameResult));
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public Boolean NoDataFound() {
        try {
            isDisplayed(nodatafound);
            System.out.println(getText(nodatafound));
            return true;
        } catch (Exception e) {

            return false;
        }
    }

    public void clearFawryID() throws Exception {
        clear(FawryID);
        clear(Merchant_Name);


    }

    public String getRequestType(int index) throws Exception {

        return getElementTextAt(requesttyperesult, index);


    }

    public boolean returnNew() throws Exception {
        System.out.println(getListValues(driver.findElements(requesttyperesult)));
        return driver.findElements(requesttyperesult).stream()
                .anyMatch(e -> e.getText().equals("EXIST"));

    }


    public void calenderDate(String FirstDate, String EndDate) throws Exception {
        scrollDownJSE(paginationBtn);
        selectDateRange(requestDateInput, monthDate, yearDate, nextButton, preButton, EndDate, FirstDate, anyware);
        clickJSE(FawryID);
    }


    public void updateButton() throws Exception {

        waitForElementToBeClickable(updateBtn);
        clickJSE(updateBtn);
    }


    public static boolean assertDatesInRange(String startDate, String endDate, List<String> dateList) throws ParseException {
        boolean result = false;
        // Parse the start and end dates
        Date start = convertDate(startDate);
        Date end = convertDate(endDate);

        System.out.println(start);
        System.out.println(end);

        for (String dateString : dateList) {
            // Parse the current date string
            Date date = convertDate(dateString);
            //inputFormat.parse(dateString);

            // Check if the date falls within the range
            if (date.compareTo(start) >= 0 && date.compareTo(end) <= 0) {
                result = true;
            } else {
                result = false;
                break;// If any date is out of range, return false
            }

        }
        return result;
    }

    //    public static Date convertDate(String inputDate) throws ParseException {
//        // Define the input and output date formats
//        SimpleDateFormat inputFormat = new SimpleDateFormat("d MMMM yyyy");
//        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//        // Parse the input date string
//        Date date = inputFormat.parse(inputDate);
//
//        // Format the Date object into the target format
//        String formattedDateStr = outputFormat.format(date);
//
//        // Parse the formatted date string back into a Date object
//        return outputFormat.parse(formattedDateStr);
//    }
    public static Date convertDate(String inputDate) throws ParseException {
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        SimpleDateFormat[] inputFormats = {
                new SimpleDateFormat("d MMMM yyyy"),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"),
                new SimpleDateFormat("dd/MM/yyyy HH:mm a"),
                new SimpleDateFormat("yyyy-MM-dd")
        };

        for (SimpleDateFormat inputFormat : inputFormats) {
            try {
                Date date = inputFormat.parse(inputDate);
                String formattedDate = outputDateFormat.format(date);
                return outputDateFormat.parse(formattedDate);
            } catch (ParseException e) {
            }
        }
        throw new ParseException("Unparseable date: " + inputDate, 0);
    }

    public List<String> returnDateRange() throws Exception {
        System.out.println(getListValues(requestDateResultlist));
        return getListValues(requestDateResultlist);
    }

    public void uploadImportFile() {

        uploadFile(enterExcelFile, uploadDirectoryPath + "add-merchants-sample.xlsx");
        System.out.println(uploadDirectoryPath + "add-merchants-sample.xlsx");
    }


    public Boolean updateDownSuccess() throws IOException {
        waitForFileToBeDownloaded(downloadDirectoryPath + "merchant-requests.xlsx");
        System.out.println(downloadDirectoryPath);
        return !isDirEmpty(downloadDirectoryPath);

    }

    public void closeRequestType() throws Exception {
        if (driver.findElement(requestTypeClose).isDisplayed() && driver.findElement(requestTypeClose).isEnabled()) {
            clickJSE(requestTypeClose);
            System.out.println("Element clicked successfully.");
        } else {
            System.out.println("Element is not clickable.");
        }
    }
}