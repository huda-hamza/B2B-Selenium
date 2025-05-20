package com.fawry.tests.b2BProduct;

import com.fawry.constants.b2bProduct.B2BProductFile_NameConstants;
import com.fawry.constants.b2bProduct.ProductDataConstant;
import com.fawry.pages.b2BProduct.AddPricingSchema;
import com.fawry.pages.b2BProduct.PricingSchemaList;
import com.fawry.tests.base.TestBase;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.Properties;

public class PricingSchemaListTest extends TestBase {


    SoftAssert softAssert;
    PricingSchemaList PricingList;
    AddPricingSchema AddPricingSchemaObj;
    String environment = com.fawry.constants.GeneralConstants.DELTA_ENVIRONMENT;
    private static final Properties testdata = propertiesReader.loadPropertiesFromFile(B2BProductFile_NameConstants.Product_PricingList_File_Name);

    @BeforeClass
    public void initial() throws Exception {
        PricingList = new PricingSchemaList(driver);
        AddPricingSchemaObj = new AddPricingSchema(driver);
        softAssert = new SoftAssert();

    }

    @BeforeMethod(alwaysRun = true)
    public void productUOMHome() throws Exception {
        PricingList.navigateToPricingListPage(environment);
    }

    @Test(priority = 1, description = "#1 verifyExportFile")
    public void verifyExportFile() throws Exception {
        try {
            PricingList.exportFile();
            softAssert.assertTrue(PricingList.exportDownSuccess());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 2, description = "#2 verifyUpdatingValidFile")
    public void verifyUpdatingValidFile() throws Exception {
        try {
            PricingList.batchDelete();
            PricingList.uploadValidFormat();
            softAssert.assertTrue(PricingList.updateSuccessMessage());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 3, description = "#3 ValidateSearchByProductName")
    public void searchByProductName() throws Exception {
        try {
            PricingList.productName(testdata.getProperty(ProductDataConstant.Product_Name));
            PricingList.searchBtn();
            softAssert.assertTrue(PricingList.validateProductsName(testdata.getProperty(ProductDataConstant.Product_Name)));
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 4, description = "#4 ValidateSearchBySKU")
    public void searchBySKU() throws Exception {
        try {
            PricingList.plusBtn();
            PricingList.skuNumber(testdata.getProperty(ProductDataConstant.Sku));
            PricingList.searchBtn();
            softAssert.assertTrue(PricingList.validateSKU(testdata.getProperty(ProductDataConstant.Sku)));
            softAssert.assertAll();

        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    @Test(priority = 5, description = "#5 ValidateSearchByBarcode")
    public void searchByBarcode() throws Exception {
        try {
            PricingList.plusBtn();
            PricingList.barCode(testdata.getProperty(ProductDataConstant.Barcode));
            PricingList.searchBtn();
            softAssert.assertTrue(PricingList.validateBarcode());
            softAssert.assertAll();

        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 6, description = "#6 verifyFunctionalityMoreAndLess")
    public void verifyFunctionalityMoreAndLessOptionButton() throws Exception {
        try {
            PricingList.plusBtn();
            softAssert.assertTrue(PricingList.SkuAndBarcodeDisplayed());
            PricingList.minusBtn();
            softAssert.assertFalse(PricingList.SkuAndBarcodeDisplayed());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 7, description = "#7 verifyPricingSchemaOnlyDeletedForFutureDates")
    public void verifyPricingSchemaOnlyDeletedForFutureDates() throws Exception {
        try {
            String date = PricingList.dateResult();
            softAssert.assertTrue(PricingList.isDateLessThanToday(date));
            softAssert.assertFalse(PricingList.trashClickable());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }

    }

    @Test(priority = 8, description = "#8 VerifyDeletePricingSchema")
    public void VerifyDeletePricingSchema() throws Exception {
        try {
            PricingList.accessPricingSchemaList();
            PricingList.addPricingSchema();
            AddPricingSchemaObj.accessProductDropDown();
            AddPricingSchemaObj.searchByProduct(testdata.getProperty(ProductDataConstant.Product_Name));
            AddPricingSchemaObj.selectProductFromSearch();
            AddPricingSchemaObj.setNewPrice("100");
            AddPricingSchemaObj.openDatePicker();
            AddPricingSchemaObj.selectDate();
            AddPricingSchemaObj.selectCategory();
            AddPricingSchemaObj.savePrice();
            PricingList.productName(testdata.getProperty(ProductDataConstant.Product_Name));
            PricingList.searchBtn();
            PricingList.deletePricingSchema();
            PricingList.deleteConfirmation();
            softAssert.assertTrue(PricingList.confirmationMessageForDelete());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 9, description = "#9 verifyUpdatingInvalidFile")
    public void verifyUpdatingInvalidFile() throws Exception {
        try {
            PricingList.batchDelete();
            PricingList.uploadInvalidFormat();
            softAssert.assertTrue(PricingList.warningMessageForInvalidFile());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }

    }


    @AfterClass
    public void deleteDownloadFiles() throws InterruptedException {

        PricingList.deleteFileAfterAssert("Prices-Report.xlsx");
        PricingList.deleteFileAfterAssert("prices.xlsx");
    }


}
