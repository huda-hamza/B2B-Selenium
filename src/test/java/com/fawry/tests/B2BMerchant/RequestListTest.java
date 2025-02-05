package com.fawry.tests.b2BMerchant;

import com.fawry.constants.B2BMerchantFile_NameConstants;
import com.fawry.constants.MerchantdataConstant;
import com.fawry.pages.b2BMerchant.RequestList;
import com.fawry.pages.base.HomePage;
import com.fawry.tests.base.TestBase;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.Properties;

public class RequestListTest extends TestBase {


    private static final Properties testdata = propertiesReader.loadPropertiesFromFile(B2BMerchantFile_NameConstants.GENERAL_TestData_FILE_NAME);
    String environment = com.fawry.constants.GeneralConstants.DELTA_ENVIRONMENT;
    RequestList list;
    SoftAssert softAssert;
    HomePage homePage;
    boolean containText;


    @BeforeClass
    public void initial() throws Exception {
        list = new RequestList(driver);
        softAssert = new SoftAssert();
        homePage = new HomePage(driver);
        list.navigateToRequestListPage(environment);
    }

    @BeforeMethod(alwaysRun = true)
    public void OpenRequestList() throws Exception {
        list.openRequestList();
    }


    @Test(priority = 1)
    public void updateButtonFunction() throws Exception {
        try {
            list.updateButton();
            list.uploadImportFile();
            Thread.sleep(2000);
            softAssert.assertTrue(list.updateDownSuccess());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 2)
    public void searchByMerchantName() throws Exception {
        try {
            list.enterMerchantName(testdata.getProperty(MerchantdataConstant.Merchant_Name));
            list.searchButton();
            softAssert.assertTrue(list.merchantNameResult());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }

    }

    @Test(priority = 3)
    public void searchWithValidFawryId() throws Exception {
        try {
            list.enterDataonFawryAccountID(testdata.getProperty(MerchantdataConstant.FAWRY_ID));
            list.searchButton();
            softAssert.assertTrue(list.results());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 4)
    public void searchWithInvalidFawryId() throws Exception {
        try {
            list.enterDataonFawryAccountID(testdata.getProperty(MerchantdataConstant.Linked_FAWRY_ID));
            list.searchButton();
            softAssert.assertTrue(list.NoDataFound());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }

    }

    @Test(priority = 5)
    public void filterWithNew() throws Exception {
        try {
            list.selectNewFromRequestType();
            list.searchButton();
            containText = list.returnNew();
            softAssert.assertFalse(containText, "The list contains an element with the text 'Exist'");
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 6)
    public void searchByValidDate() throws Exception {
        try {
            list.calenderDate(testdata.getProperty(MerchantdataConstant.First_DATE), testdata.getProperty(MerchantdataConstant.Second_Date));
            list.searchButton();
            softAssert.assertTrue(list.assertDatesInRange(testdata.getProperty(MerchantdataConstant.Second_Date), testdata.getProperty(MerchantdataConstant.First_DATE), list.returnDateRange()));
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    @Test(priority = 7)
    public void SearchByInValidDate() throws Exception {
        try {
            list.calenderDate(testdata.getProperty(MerchantdataConstant.Invalid_First_Date), testdata.getProperty(MerchantdataConstant.Invalid_Second_Date));
            list.searchButton();
            softAssert.assertTrue(list.NoDataFound());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    @Test(priority = 8)
    public void exportAllFunction() throws Exception {
        try {
            list.navigateToRequestListPage(environment);
            list.exportAll();
            softAssert.assertTrue(list.exportDownSuccess());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    @AfterClass
    public void deleteDownloadFiles() {
        list.deleteFileAfterAssert("merchant-requests.xlsx");
        list.deleteFileAfterAssert("add-merchants-sample.xlsx");

    }


}
