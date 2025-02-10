package com.fawry.pages.b2BOrder;

import com.fawry.constants.b2BOrder.B2BOrderFile_NameConstants;
import com.fawry.constants.b2BOrder.OrderDataConstant;
import com.fawry.pages.base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;

public class DashBord extends PageBase {
    public DashBord(WebDriver driver) {
        super(driver);
    }

    LocalDate today = LocalDate.now();
    String todayDate = today.format(DateTimeFormatter.ofPattern("d"));
    private By searchBtn = By.xpath("//button[contains(@class,'primary-button')]");
    private By dateField = By.xpath("//p-calendar//input[contains(@class,'p-inputtext p-component ng-star-inserted')]");
    private By wrongValidationMessage = By.xpath("//div[text()=' Please enter a valid date range ']");

    private By todayDateSelect = By.xpath("//span[text()='" + todayDate + "']");

    private By searchOrderHeader = By.xpath("//h4[text()='Search Orders']");

    private static final Properties URl = propertiesReader.loadPropertiesFromFile(B2BOrderFile_NameConstants.GENERAL_DashBoard_FILE_NAME);

    private By totalNumberOrders = By.xpath("//span[text()='Total Orders']//parent::h5//following-sibling::h2");
    private By totalPaidOrders = By.xpath("//span[text()='Paid']//parent::h5//following-sibling::h2");
    private By totalUnpaidOrders = By.xpath("//span[text()='Unpaid']//parent::h5//following-sibling::h2");
    private By totalFaildOrders = By.xpath("//span[text()='Faild']//parent::h5//following-sibling::h2");
    private By totalExpiredOrders = By.xpath("//span[text()='Expired']//parent::h5//following-sibling::h2");
    private By totalRefundedOrders = By.xpath("//span[text()='Refunded']//parent::h5//following-sibling::h2");
    private By totalCancelledOrders = By.xpath("//span[text()='Cancelled']//parent::h5//following-sibling::h2");
    private By month = By.xpath("//div//span[contains(@class,'p-datepicker-month')]");
    private By year = By.xpath("//div//span[contains(@class,'p-datepicker-year')]");
    private By prevButton = By.xpath("//button//span[contains(@class,'p-datepicker-prev-icon')]");
    private By nextButton = By.xpath("//button//span[contains(@class,'p-datepicker-next-icon')]");
    private By dateIcon = By.xpath("//button[contains(@class,'p-datepicker-trigger')]//span[@class='p-button-icon pi pi-calendar']");

    private By list = By.xpath("//td//a");

    private By initiatedDeliveryOrder = By.xpath("//h5[text()='Initiated']");
    private By initiatedDeliveryOrderNumber = By.xpath("//h5[text()='Initiated']//parent::a//following-sibling::span");
    private By deliveredDeliveryOrders = By.xpath("//h5[text()='Delivered']");
    private By deliveredDeliveryOrdersNumber = By.xpath("//h5[text()='Delivered']//parent::a//following-sibling::span");
    private By pickedDeliveryOrders = By.xpath("//h5[text()='Picked']");
    private By pickedDeliveryOrdersNumber = By.xpath("//h5[text()='Picked']//parent::a//following-sibling::span");
    private By readyForPickUp = By.xpath("//h5[text()='Ready For Pick Up']");
    private By readyForPickUpNumber = By.xpath("//h5[text()='Ready For Pick Up']//parent::a//following-sibling::span");
    private By returnedDelivery = By.xpath("//h5[text()='Returned']");
    private By returnedDeliveryNumber = By.xpath("//h5[text()='Returned']//parent::a//following-sibling::span");

    private By settlement = By.xpath("//h4[text()='Settlement Summary']");
    private By payment = By.xpath("//h4[text()='Payment Summary']");

    public void navigateToInitiated() throws Exception {
        scrollToElement(initiatedDeliveryOrder);
        clickJSE(initiatedDeliveryOrder);

    }

    public void navigateToDeliveryOrders() throws Exception {
        waitForElementToBeClickable(searchBtn);
        scrollToElement(deliveredDeliveryOrders);
        clickJSE(deliveredDeliveryOrders);
    }

    public void navigateToPickedDeliveryOrders() throws Exception {
        clickJSE(pickedDeliveryOrders);
    }

    public void navigateToReadyForPickUp() throws Exception {
        clickJSE(readyForPickUp);
    }

    public void navigateToReturnedDelivery() throws Exception {
        scrollToElement(returnedDelivery);
        clickJSE(returnedDelivery);
    }

    public void navigateToDashBoard(String environment) throws Exception {
        navigateTo(URl.getProperty(OrderDataConstant.dash_Board_URL), environment);
    }

    public void selectDateRange(String dateFrom, String dateTo) throws Exception {
        scrollToElement(dateField);
        waitForElementToBeClickable(searchBtn);
        selectDateRange2(dateField, month, year, nextButton, prevButton, dateFrom, dateTo, dateIcon);
    }

    public void openDatePicker() throws Exception {
        waitForVisibility(dateField);
        clickJSE(dateField);

    }

    public void searchBtn() throws Exception {
        clickJSE(searchBtn);
        waitForElementToBeClickable(searchBtn);
    }

    public int totalSumOfAllOrders() throws Exception {
        return Integer.parseInt(getText(totalPaidOrders)) + Integer.parseInt(getText(totalUnpaidOrders))
                + Integer.parseInt(getText(totalFaildOrders)) + Integer.parseInt(getText(totalExpiredOrders))
                + Integer.parseInt(getText(totalRefundedOrders)) + Integer.parseInt(getText(totalCancelledOrders));
    }


    public int totalSumOfDeliveryStatusOrders() throws Exception {
        return (Integer.parseInt(getText(initiatedDeliveryOrderNumber)) + Integer.parseInt(getText(deliveredDeliveryOrdersNumber))
                + Integer.parseInt((getText(pickedDeliveryOrdersNumber))) + Integer.parseInt(getText(readyForPickUpNumber)) + Integer.parseInt(getText(returnedDeliveryNumber)));
    }

    public int totalOrders() throws Exception {
        return Integer.parseInt(getText(totalNumberOrders));

    }

    public void navigateToTotalOrdersNumber() throws Exception {
        clickJSE(totalNumberOrders);
    }

    public boolean errorMessageValidationDate() {
        try {
            waitForElementToBePresent(wrongValidationMessage);
            System.out.println(getText(wrongValidationMessage));
            return isDisplayed(wrongValidationMessage);
        } catch (Exception e) {
            return false;
        }
    }

    public void selectToday() throws Exception {
        clickJSE(todayDateSelect);

    }

    public void navigateToCancelOrders() throws Exception {
        clickJSE(totalCancelledOrders);
    }

    public void navigateToExpiredOrders() throws Exception {
        clickJSE(totalExpiredOrders);
    }

    public void navigateToRefundedOrders() throws Exception {
        clickJSE(totalRefundedOrders);
    }

    public void navigateToFailedOrders() throws Exception {
        clickJSE(totalFaildOrders);
    }

    public void navigateToUnpaidOrders() throws Exception {
        clickJSE(totalUnpaidOrders);
    }

    public void navigateToPaidOrders() throws Exception {
        clickJSE(totalPaidOrders);
    }

    public Boolean navigateToSearchOrders() {
        try {
            waitForElementToBePresent(searchOrderHeader);
            System.out.println(getText(searchOrderHeader));
            return isDisplayed(searchOrderHeader);
        } catch (Exception e) {
            return false;
        }
    }


    public String selectedDataRange() {
        System.out.println(getAttributeValue(dateField, "value"));
        return getAttributeValue(dateField, "value");
    }


    public boolean isDateFieldDisplayingCurrentWeek() {
        waitForElementToBeClickable(searchBtn);
        // Locate the date field

        String fieldValue = getAttributeValue(dateField, "value");
        // Calculate the start and end of the current week range (inclusive of today)
        System.out.println(fieldValue);

        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.minusDays(7); // Start is 6 days before today
        LocalDate endOfWeek = today;               // End is today

        // Format the dates to match the field's display format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String expectedValue = startOfWeek.format(formatter) + " - " + endOfWeek.format(formatter);
        System.out.println(expectedValue);
        // Compare the expected value with the field value
        return fieldValue.equals(expectedValue);
    }

    public int numberOfOrders() throws Exception {
        try {
            waitForElementToBeClickable(searchBtn);
            List<WebElement> elements = driver.findElements(list);

            // Get the count of elements
            int elementCount = elements.size();

            // Print the count
            System.out.println(elementCount);
            return elementCount;
        } catch (Exception e) {
            throw new Exception(e);
        }

    }

    public boolean settlementSection() {
        try {
            return isDisplayed(settlement);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean paymentSection() {
        try {
            return isDisplayed(payment);
        } catch (Exception e) {
            return false;
        }
    }
}
