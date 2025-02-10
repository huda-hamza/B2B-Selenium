package com.fawry.tests.b2BOrder;

import com.fawry.constants.b2BOrder.B2BOrderFile_NameConstants;
import com.fawry.constants.b2BOrder.OrderDataConstant;
import com.fawry.pages.b2BOrder.DashBord;
import com.fawry.tests.base.TestBase;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Properties;

public class DashboardTest extends TestBase {

    private static final Properties testdata = propertiesReader.loadPropertiesFromFile(B2BOrderFile_NameConstants.GENERAL_DashBoard_FILE_NAME);

    DashBord dashObj;
    private final String startDate = testdata.getProperty(OrderDataConstant.ORDERS_SEARCH_ORDER_START_DATE);
    private final String endDate = testdata.getProperty(OrderDataConstant.ORDERS_SEARCH_ORDER_END_DATE);
    private final String startDates = testdata.getProperty(OrderDataConstant.START_DATE);
    private final String endDates = testdata.getProperty(OrderDataConstant.END_DATE);

    String environment = com.fawry.constants.GeneralConstants.DELTA_ENVIRONMENT;


    @BeforeClass
    public void initial() throws Exception {
        dashObj = new DashBord(driver);
        softAssert = new SoftAssert();
    }

    @BeforeMethod(alwaysRun = true)
    public void productUOMHome() throws Exception {
        dashObj.navigateToDashBoard(environment);
    }


    @Test(priority = 1, description = "#1 ValidateOrderInitiationDateContainsSevenDays")
    public void ValidateOrderInitiationDateContainsSevenDays() throws Exception {
        try {
            softAssert.assertTrue(dashObj.isDateFieldDisplayingCurrentWeek());//noted this issue is being that current date is 8
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 2, description = "#2 ValidateSelectingValidDifferentDateRange")
    public void ValidateSelectingValidDifferentDateRange() throws Exception {
        try {
            dashObj.selectDateRange(startDate, endDate);
          //  dashObj.searchBtn();
            softAssert.assertEquals(dashObj.selectedDataRange(), "12/01/2024 - 12/29/2024");
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    @Test(priority = 3, description = "#3 ValidateOnSelectingInvalidDates")
    public void ValidateOnSelectingInvalidDates() throws Exception {
        try {
            dashObj.openDatePicker();
            dashObj.selectToday();
            dashObj.searchBtn();
            softAssert.assertTrue(dashObj.errorMessageValidationDate());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 4, description = "#4 ValidatePaidCardPaymentStatusIsClickable")
    public void ValidatePaidCardPaymentStatusIsClickable() throws Exception {
        try {
            dashObj.selectDateRange(startDate, endDate);
            dashObj.searchBtn();
            dashObj.navigateToPaidOrders();
            softAssert.assertEquals(dashObj.numberOfOrders(), 5);
            softAssert.assertTrue(dashObj.navigateToSearchOrders());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 5, description = "#5 ValidatePaidCardPaymentStatusIsClickable")
    public void ValidateUnpaidCardPaymentStatusIsClickable() throws Exception {
        try {
            dashObj.selectDateRange(startDates, endDates);
            dashObj.searchBtn();
            dashObj.searchBtn();
            dashObj.navigateToUnpaidOrders();
            softAssert.assertEquals(dashObj.numberOfOrders(), 9);
            softAssert.assertTrue(dashObj.navigateToSearchOrders());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 6, description = "#6 ValidateFailedCardPaymentStatusIsClickable")
    public void ValidateFailedCardPaymentStatusIsClickable() throws Exception {
        try {
            dashObj.selectDateRange(startDate, endDate);
            dashObj.searchBtn();
            dashObj.navigateToFailedOrders();
            softAssert.assertEquals(dashObj.numberOfOrders(), 1);
            softAssert.assertTrue(dashObj.navigateToSearchOrders());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 7, description = "#7 ValidateExpiredCardPaymentStatusIsClickable")
    public void ValidateExpiredCardPaymentStatusIsClickable() throws Exception {
        try {
            dashObj.selectDateRange(startDate, endDate);
            dashObj.searchBtn();
            dashObj.navigateToExpiredOrders();
            softAssert.assertEquals(dashObj.numberOfOrders(), 1);
            softAssert.assertTrue(dashObj.navigateToSearchOrders());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    @Test(priority = 8, description = "#8 ValidateRefundedCardPaymentStatusIsClickable")
    public void ValidateRefundedCardPaymentStatusIsClickable() throws Exception {
        try {
            dashObj.selectDateRange(startDate, endDate);
            dashObj.searchBtn();
            dashObj.navigateToRefundedOrders();
            softAssert.assertEquals(dashObj.numberOfOrders(), 1);
            softAssert.assertTrue(dashObj.navigateToSearchOrders());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 9, description = "#9 ValidateCancelledCardPaymentStatusIsClickable")
    public void ValidateCancelledCardPaymentStatusIsClickable() throws Exception {
        try {
            dashObj.selectDateRange(startDate, endDate);
            dashObj.searchBtn();
            dashObj.navigateToCancelOrders();
            softAssert.assertTrue(dashObj.navigateToSearchOrders());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 10, description = "#10 ValidateTotalOrdersCardInPaymentStatusISTheTotalOFRemainingCard")
    public void ValidateTotalOrdersCardInPaymentStatusISTheTotalOFRemainingCard() throws Exception {
        try {
            softAssert.assertEquals(dashObj.totalSumOfAllOrders(), dashObj.totalOrders());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 11, description = "#11 ValidateTotalOrdersCardIsClickable")
    public void ValidateTotalOrdersCardIsClickable() throws Exception {
        try {
            dashObj.selectDateRange(startDates, endDates);
            dashObj.searchBtn();
            dashObj.navigateToTotalOrdersNumber();
            softAssert.assertEquals(dashObj.numberOfOrders(), 10);
            softAssert.assertTrue(dashObj.navigateToSearchOrders());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }

    }

    @Test(priority = 12, description = "#12 ValidateDeliveryCardInDeliveryStatus")
    public void ValidateDeliveryCardInDeliveryStatus() throws Exception {
        try {
            dashObj.selectDateRange(startDate, endDate);
            dashObj.searchBtn();
            dashObj.navigateToDeliveryOrders();
            softAssert.assertEquals(dashObj.numberOfOrders(), 1);
            softAssert.assertTrue(dashObj.navigateToSearchOrders());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 13, description = "#13 ValidateInitiatedCardInDeliveryStatus")
    public void ValidateInitiatedCardInDeliveryStatus() throws Exception {
        try {
            dashObj.selectDateRange(startDates, endDates);
            dashObj.searchBtn();
            dashObj.navigateToInitiated();
            softAssert.assertEquals(dashObj.numberOfOrders(), 2);
            softAssert.assertTrue(dashObj.navigateToSearchOrders());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 14, description = "#14 ValidateReadyForPickupCardInDeliveryStatus")
    public void ValidateReadyForPickupCardInDeliveryStatus() throws Exception {
        try {
            dashObj.selectDateRange(startDate, endDate);
            dashObj.searchBtn();
            dashObj.navigateToReadyForPickUp();
            softAssert.assertEquals(dashObj.numberOfOrders(), 1);
            softAssert.assertTrue(dashObj.navigateToSearchOrders());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 15, description = "#15 ValidatePickedCardInDeliveryStatus")
    public void ValidatePickedCardInDeliveryStatus() throws Exception {
        try {
            dashObj.selectDateRange(startDate, endDate);
            dashObj.searchBtn();
            dashObj.navigateToPickedDeliveryOrders();
            softAssert.assertEquals(dashObj.numberOfOrders(), 1);
            softAssert.assertTrue(dashObj.navigateToSearchOrders());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    @Test(priority = 16, description = "#16 ValidateReturnedCardInDeliveryStatus")
    public void ValidateReturnedCardInDeliveryStatus() throws Exception {
        try {
            dashObj.selectDateRange(startDate, endDate);
            dashObj.searchBtn();
            dashObj.navigateToReturnedDelivery();
            softAssert.assertEquals(dashObj.numberOfOrders(), 1);
            softAssert.assertTrue(dashObj.navigateToSearchOrders());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 17, description = "#17 ValidateTotalCountCardInDeliveryStatus")
    public void ValidateTotalCountCardInDeliveryStatus() throws Exception {
        try {
            dashObj.selectDateRange(startDate, endDate);
            dashObj.searchBtn();
            Thread.sleep(1000);
            softAssert.assertEquals(dashObj.totalSumOfDeliveryStatusOrders(), 20);
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 18, description = "#18 ValidateExistenceSettlementSummarySection")
    public void ValidateExistenceSettlementSummarySection() throws Exception {
        try {
            softAssert.assertTrue(dashObj.settlementSection());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 19, description = "#19 ValidateExistencePaymentSummarySection")
    public void ValidateExistencePaymentSummarySection() throws Exception {
        try {
            softAssert.assertTrue(dashObj.paymentSection());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}