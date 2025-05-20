package com.fawry.tests.b2BOrder;

import com.fawry.pages.b2BOrder.SearchPage;
import com.fawry.tests.base.TestBase;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SearchPageTest extends TestBase {

    SearchPage searchObj;
    String environment = com.fawry.constants.GeneralConstants.DELTA_ENVIRONMENT;
    String supplierName="hadeer sapplier";

    @BeforeClass
    public void initial() throws Exception {
        searchObj = new SearchPage(driver);
        softAssert = new SoftAssert();
    }

    @BeforeMethod(alwaysRun = true)
    public void productUOMHome() throws Exception {
        searchObj.navigateToSearch(environment);
    }

    @Test(priority = 1, description = "#1 validateSearchOrderSupplierFilter")
    public void validateSearchOrderSupplierFilter() throws Exception {
        searchObj.accessResetButton();
        searchObj.chooseSupplier();
        searchObj.searchBtn();
        Thread.sleep(1000);
        softAssert.assertEquals(searchObj.supplierNameResult(), supplierName);
        softAssert.assertAll();

    }

    @Test(priority = 2, description = "#2 validateClearSupplierFilter", dependsOnMethods = "validateSearchOrderSupplierFilter")
    public void validateClearSupplierFilter() throws Exception {
        searchObj.clearSupplierDropList();
        searchObj.searchBtn();
        softAssert.assertTrue(searchObj.displayErrorMessage());
        softAssert.assertAll();


    }

    @Test(priority = 3, description = "#3 validateSearchFunctionInsideSupplierFilter")
    public void validateSearchFunctionInsideSupplierFilter() throws Exception {
        searchObj.accessResetButton();
        searchObj.textSearchFunction(supplierName);
        softAssert.assertEquals(searchObj.resultOfSearchFilter(),supplierName);
        softAssert.assertAll();
    }
}
