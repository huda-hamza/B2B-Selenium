package com.fawry.tests.B2BMerchant;

import com.fawry.constants.MerchantdataConstant;
import com.fawry.pages.B2BMerchant.PricingSchemaList;
import com.fawry.pages.B2BMerchant.UOM;
import com.fawry.pages.base.HomePage;
import com.fawry.tests.base.TestBase;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PricingSchemaListTest extends TestBase {


    SoftAssert softAssert ;
    PricingSchemaList PricingList;
    String environment = com.fawry.constants.GeneralConstants.DELTA_ENVIRONMENT;

    @BeforeClass
    public void initial() throws Exception {
        PricingList = new PricingSchemaList(driver);
        softAssert = new SoftAssert();

    }

    @BeforeMethod(alwaysRun = true)
    public void productUOMHome() throws Exception { PricingList.navigateToPricingListPage(environment);}


    @Test
    public void searchByProductName() throws Exception {
        try {
            PricingList.productName("test");
            PricingList.searchBtn();

        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Test
    public void searchBySKU() throws Exception {
        try {
            PricingList.plusBtn();
            PricingList.skuNumber("123");
            PricingList.searchBtn();

        }catch (Exception e){
            throw new Exception(e);
        }
    }


    @Test
    public void searchByBarcode() throws Exception {
        try {
            PricingList.plusBtn();
            PricingList.barCode("253");
            PricingList.searchBtn();

        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Test
    public void verifyFunctionalityMoreAndLessOptionButton() throws Exception {
        try {
            PricingList.plusBtn();
            softAssert.assertTrue(PricingList.SkuAndBarcodeDisplayed());
            PricingList.minusBtn();
            softAssert.assertFalse(PricingList.SkuAndBarcodeDisplayed());
            softAssert.assertAll();
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Test
    public void verifyPricingSchemaOnlyDeletedForFutureDates() throws Exception {
     String date= PricingList.dateResult();
    softAssert.assertTrue(PricingList.isDateLessThanToday(date));
    softAssert.assertFalse(PricingList.trashClickable());
    softAssert.assertAll();

    }

}
