package com.fawry.tests.b2BProduct;

import com.fawry.pages.b2BProduct.AddPricingSchema;
import com.fawry.tests.base.TestBase;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AddPricingSchemaTest extends TestBase {


    SoftAssert softAssert;
    AddPricingSchema AddPricingSchemaObj;

    String environment = com.fawry.constants.GeneralConstants.DELTA_ENVIRONMENT;

    @BeforeClass
    public void initial() throws Exception {
        AddPricingSchemaObj = new AddPricingSchema(driver);
        softAssert = new SoftAssert();
    }

    @BeforeMethod(alwaysRun = true)
    public void productUOMHome() throws Exception {
        AddPricingSchemaObj.navigateToAddPricingSchema(environment);
    }


    @Test(priority = 1, description = "#1 addPricingSchema")
    public void addPricingSchema() throws Exception {
        try {
            AddPricingSchemaObj.accessProductDropDown();
            AddPricingSchemaObj.searchByProduct("NewProduct225");
            AddPricingSchemaObj.selectProductFromSearch();
            AddPricingSchemaObj.setNewPrice("100");
            AddPricingSchemaObj.openDatePicker();
            AddPricingSchemaObj.selectDate();
            AddPricingSchemaObj.selectCategory();
            AddPricingSchemaObj.savePrice();
            softAssert.assertTrue(AddPricingSchemaObj.displayToastMessage());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }

    }

    @Test(priority = 2, description = "#1 VerifyAddingDifferentPriceForDifferentCategoriesOnDifferentDates")
    public void VerifyAddingDifferentPriceForDifferentCategoriesOnDifferentDates() throws Exception {
        try {
            addPricingSchema();
            softAssert.assertTrue(AddPricingSchemaObj.displayToastMessage());
            AddPricingSchemaObj.pricingSchemaAccess();
            AddPricingSchemaObj.addPricingSchema();
            AddPricingSchemaObj.accessProductDropDown();
            AddPricingSchemaObj.searchByProduct("NewProduct225");
            AddPricingSchemaObj.selectProductFromSearch();
            AddPricingSchemaObj.setNewPrice("120");
            AddPricingSchemaObj.openDatePicker();
            AddPricingSchemaObj.selectDifferentDate();
            AddPricingSchemaObj.selectDifferentCategory();
            AddPricingSchemaObj.savePrice();
            softAssert.assertTrue(AddPricingSchemaObj.displayToastMessage());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    @Test(priority = 3, description = "#3 VerifyAddingDifferentPriceForDifferentCategoriesOnSameDate")
    public void VerifyAddingDifferentPriceForDifferentCategoriesOnSameDate() throws Exception {
        try {
            addPricingSchema();
            softAssert.assertTrue(AddPricingSchemaObj.displayToastMessage());
            AddPricingSchemaObj.pricingSchemaAccess();
            AddPricingSchemaObj.addPricingSchema();
            AddPricingSchemaObj.accessProductDropDown();
            AddPricingSchemaObj.searchByProduct("NewProduct225");
            AddPricingSchemaObj.selectProductFromSearch();
            AddPricingSchemaObj.setNewPrice("120");
            AddPricingSchemaObj.openDatePicker();
            AddPricingSchemaObj.selectDate();
            AddPricingSchemaObj.selectDifferentCategory();
            AddPricingSchemaObj.savePrice();
            softAssert.assertTrue(AddPricingSchemaObj.displayToastMessage());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 4, description = "#4 verifySelectInvalidDate")
    public void verifySelectInvalidDate() throws Exception {
        try {
            AddPricingSchemaObj.accessProductDropDown();
            AddPricingSchemaObj.searchByProduct("NewProduct225");
            AddPricingSchemaObj.selectProductFromSearch();
            AddPricingSchemaObj.setNewPrice("100");
            AddPricingSchemaObj.selectCategory();
            AddPricingSchemaObj.openDatePicker();
            softAssert.assertTrue(AddPricingSchemaObj.selectToday());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }

    }

    @Test(priority = 5, description = "#5 verifyDifferentPriceForSameCategoryOnDifferentDate")
    public void verifyDifferentPriceForSameCategoryOnDifferentDate() throws Exception {
        try {
            addPricingSchema();
            AddPricingSchemaObj.pricingSchemaAccess();
            AddPricingSchemaObj.addPricingSchema();
            AddPricingSchemaObj.accessProductDropDown();
            AddPricingSchemaObj.searchByProduct("NewProduct225");
            AddPricingSchemaObj.selectProductFromSearch();
            AddPricingSchemaObj.setNewPrice("120");
            AddPricingSchemaObj.openDatePicker();
            AddPricingSchemaObj.selectDifferentDate();
            AddPricingSchemaObj.selectCategory();
            AddPricingSchemaObj.savePrice();
            softAssert.assertTrue(AddPricingSchemaObj.displayToastMessage());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    @Test(priority = 6, description = "#6 verifyEnterValidNewPriceWithDecimalNumber")
    public void verifyEnterValidNewPriceWithDecimalNumber() throws Exception {
        try {
            AddPricingSchemaObj.accessProductDropDown();
            AddPricingSchemaObj.searchByProduct("NewProduct225");
            AddPricingSchemaObj.selectProductFromSearch();
            AddPricingSchemaObj.setNewPrice("10.95");
            AddPricingSchemaObj.openDatePicker();
            AddPricingSchemaObj.selectDate();
            AddPricingSchemaObj.selectCategory();
            AddPricingSchemaObj.savePrice();
            softAssert.assertTrue(AddPricingSchemaObj.displayToastMessage());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 7, description = "#7 verifyProductHasUom")
    public void verifyProductHasUom() throws Exception {
        try {
            AddPricingSchemaObj.accessProductDropDown();
            AddPricingSchemaObj.searchByProduct("NewProduct225");
            AddPricingSchemaObj.selectProductFromSearch();
            softAssert.assertTrue(AddPricingSchemaObj.uomAssertion());
            softAssert.assertFalse(AddPricingSchemaObj.uomNotEmpty());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 8, description = "#8 verifyProductImgExist")
    public void verifyProductImgExist() throws Exception {
        try {
            AddPricingSchemaObj.accessProductDropDown();
            AddPricingSchemaObj.searchByProduct("NewProduct225");
            AddPricingSchemaObj.selectProductFromSearch();
            softAssert.assertTrue(AddPricingSchemaObj.imgDisplay());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 9, description = "#9 verifyProductNameExist")
    public void verifyProductNameExist() throws Exception {
        try {
            AddPricingSchemaObj.accessProductDropDown();
            AddPricingSchemaObj.searchByProduct("NewProduct225");
            AddPricingSchemaObj.selectProductFromSearch();
            softAssert.assertTrue(AddPricingSchemaObj.productNameDisplay());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 10, description = "#10 public void verifyAddingSamePriceForDifferentCategoryOnSameDates")
    public void verifyAddingSamePriceForDifferentCategoryOnSameDates() throws Exception {
        try {
            addPricingSchema();
            AddPricingSchemaObj.pricingSchemaAccess();
            AddPricingSchemaObj.addPricingSchema();
            AddPricingSchemaObj.accessProductDropDown();
            AddPricingSchemaObj.searchByProduct("NewProduct225");
            AddPricingSchemaObj.selectProductFromSearch();
            AddPricingSchemaObj.setNewPrice("100");
            AddPricingSchemaObj.openDatePicker();
            AddPricingSchemaObj.selectDate();
            AddPricingSchemaObj.selectDifferentCategory();
            AddPricingSchemaObj.savePrice();
            softAssert.assertTrue(AddPricingSchemaObj.displayToastMessage());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 11, description = "#11 verifyProductImgExist")
    public void verifyCancelBtn() throws Exception {
        try {
            AddPricingSchemaObj.accessProductDropDown();
            AddPricingSchemaObj.searchByProduct("NewProduct225");
            AddPricingSchemaObj.selectProductFromSearch();
            AddPricingSchemaObj.cancelBtn();
            softAssert.assertTrue(AddPricingSchemaObj.displayPricingSchemaList());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 12, description = "#12 verifyFunctionOfCloseIconOfSelectingCategory")
    public void verifyFunctionOfCloseIconOfSelectingCategory() throws Exception {
        try {
            AddPricingSchemaObj.accessProductDropDown();
            AddPricingSchemaObj.searchByProduct("NewProduct225");
            AddPricingSchemaObj.selectProductFromSearch();
            AddPricingSchemaObj.selectCategory();
            AddPricingSchemaObj.closeCategory();
            softAssert.assertTrue(AddPricingSchemaObj.displaySelectCategory());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 13, description = "#13 verifyFunctionOfCloseIconOfSelectingProduct ")
    public void verifyFunctionOfCloseIconOfSelectingProduct() throws Exception {
        try {
            AddPricingSchemaObj.accessProductDropDown();
            AddPricingSchemaObj.searchByProduct("NewProduct225");
            AddPricingSchemaObj.selectProductFromSearch();
            AddPricingSchemaObj.closeProduct();
            softAssert.assertTrue(AddPricingSchemaObj.displaySelectProduct());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 14, description = "#14 verifyMandatoryCategory")
    public void verifyMandatoryCategory() throws Exception {
        try {
            AddPricingSchemaObj.accessProductDropDown();
            AddPricingSchemaObj.searchByProduct("NewProduct225");
            AddPricingSchemaObj.selectProductFromSearch();
            AddPricingSchemaObj.setNewPrice("100");
            AddPricingSchemaObj.openDatePicker();
            AddPricingSchemaObj.selectDate();
            AddPricingSchemaObj.savePrice();
            softAssert.assertTrue(AddPricingSchemaObj.mandatoryCategory());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 15, description = "#15 verifyNewPriceCurrencyISEG")
    public void verifyNewPriceCurrencyISEG() throws Exception {
        try {
            AddPricingSchemaObj.accessProductDropDown();
            AddPricingSchemaObj.searchByProduct("NewProduct225");
            AddPricingSchemaObj.selectProductFromSearch();
            softAssert.assertTrue(AddPricingSchemaObj.currency());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    @Test(priority = 16, description = "#16 verifyNewPriceAcceptNumbersOnly ")
    public void verifyNewPriceAcceptNumbersOnly() throws Exception {
        try {
            AddPricingSchemaObj.accessProductDropDown();
            AddPricingSchemaObj.searchByProduct("NewProduct225");
            AddPricingSchemaObj.selectProductFromSearch();
            AddPricingSchemaObj.setNewPrice("ppp@@@");
            AddPricingSchemaObj.openDatePicker();
            AddPricingSchemaObj.selectDate();
            AddPricingSchemaObj.selectCategory();
            AddPricingSchemaObj.savePrice();
            softAssert.assertTrue(AddPricingSchemaObj.newPriceRequired());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 17, description = "#17 verifySpecificPriceForTheProduct")
    public void verifySpecificPriceForTheProduct() throws Exception {
        try {
            AddPricingSchemaObj.accessProductDropDown();
            AddPricingSchemaObj.searchByProduct("NewProduct225");
            AddPricingSchemaObj.selectProductFromSearch();
            softAssert.assertTrue(AddPricingSchemaObj.priceAssertion());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 18, description = "#18 verifyUpdatingSelectingProduct")
    public void verifyUpdateSelectingProduct() throws Exception {
        try {
            AddPricingSchemaObj.accessProductDropDown();
            AddPricingSchemaObj.searchByProduct("NewProduct225");
            AddPricingSchemaObj.selectProductFromSearch();
            AddPricingSchemaObj.openListOFProduct();
            AddPricingSchemaObj.searchByProduct("Persil");
            AddPricingSchemaObj.selectAnotherProduct();
            softAssert.assertTrue(AddPricingSchemaObj.displayNewProduct());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 19, description = "#19 verifyUpdatingSelectingCategory")
    public void verifyUpdatingSelectingCategory() throws Exception {
        try {
            AddPricingSchemaObj.accessProductDropDown();
            AddPricingSchemaObj.searchByProduct("NewProduct225");
            AddPricingSchemaObj.selectProductFromSearch();
            AddPricingSchemaObj.selectCategory();
            AddPricingSchemaObj.closeCategory();
            AddPricingSchemaObj.selectDifferentCategory();
            softAssert.assertTrue(AddPricingSchemaObj.displayCategory());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

  /*  @Test(priority = 20, description = "#20 verifyEnterInvalidNewPrice")
    public void verifyEnterInvalidNewPrice() throws Exception {
        try {
            AddPricingSchemaObj.accessProductDropDown();
            AddPricingSchemaObj.searchByProduct("NewProduct225");
            AddPricingSchemaObj.selectProductFromSearch();
            AddPricingSchemaObj.setNewPrice("0");
            AddPricingSchemaObj.openDatePicker();
            AddPricingSchemaObj.selectDate();
            AddPricingSchemaObj.selectCategory();
            AddPricingSchemaObj.savePrice();
            softAssert.assertTrue(AddPricingSchemaObj.newPriceRequired());
            softAssert.assertFalse(AddPricingSchemaObj.displayToastMessage());
            softAssert.assertAll();

        } catch (Exception e) {
            throw new Exception(e);
        }
    }*/


   @Test(priority = 21, description = "#21 verifySampleFileIsDownload")
    public void verifySampleFileIsDownload() throws Exception {
        try {
            AddPricingSchemaObj.uploadBatch();
            AddPricingSchemaObj.downloadSampleFile();
            softAssert.assertTrue(AddPricingSchemaObj.downloadSampleSuccess());
            softAssert.assertAll();

        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    @Test(priority = 22, description = "#22 verifySampleFileIsDownload")
    public void verifyUpdatingWithInvalidFile() throws Exception {
        try {
            AddPricingSchemaObj.uploadBatch();
            AddPricingSchemaObj.uploadFile();
            AddPricingSchemaObj.uploadInvalidFormat();
            softAssert.assertTrue(AddPricingSchemaObj.invalidMessage());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 23, description = "#23 verifyUpdatingWithValidFile")
    public void verifyUpdatingWithValidFile() throws Exception {
        try {
            AddPricingSchemaObj.uploadBatch();
            AddPricingSchemaObj.uploadFile();
            AddPricingSchemaObj.uploadValidSheet();
            softAssert.assertTrue(AddPricingSchemaObj.validUploadFileMessage());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 24, description = "#24 verifyFunctionOFCancelButton")
    public void verifyFunctionOFCancelButton() throws Exception {
        try {
            AddPricingSchemaObj.uploadBatch();
            AddPricingSchemaObj.cancelBatchUpload();
            softAssert.assertTrue(AddPricingSchemaObj.displayPricingSchemaList());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @AfterClass
    public void deleteDownloadFiles() throws InterruptedException {

        AddPricingSchemaObj.deleteFileAfterAssert("prices-result.xlsx");
        AddPricingSchemaObj.deleteFileAfterAssert("prices-sampleFile.xlsx");
    }

}
