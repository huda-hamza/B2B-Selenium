package com.fawry.tests.b2BProduct;

import com.fawry.constants.b2BMerchant.B2BMerchantFile_NameConstants;
import com.fawry.constants.b2bProduct.B2BProductFile_NameConstants;
import com.fawry.pages.b2BProduct.ProductBranchManagement;
import com.fawry.tests.base.TestBase;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Properties;

public class ProductBranchManagementTest extends TestBase {

    ProductBranchManagement branchObj;
    String environment = com.fawry.constants.GeneralConstants.DELTA_ENVIRONMENT;
    private static final Properties testdata = propertiesReader.loadPropertiesFromFile(B2BProductFile_NameConstants.productBranchManagement_File_Name);

    @BeforeClass
    public void initial() throws Exception {
        branchObj = new ProductBranchManagement(driver);
        softAssert = new SoftAssert();

    }

    @BeforeMethod(alwaysRun = true)
    public void productUOMHome() throws Exception {
        branchObj.navigateToBranchManagement(environment);
    }

    @Test(priority = 1, description = "#1 validateNOSelectedBranch")
    public void validateNoSelectedBranch() throws Exception {
        try {
            softAssert.assertTrue(branchObj.noRecordDisplay());
            softAssert.assertTrue(branchObj.noBranchMessage());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 2, description = "#2 validateProductDisplayAfterFilteringByBranch")
    public void validateProductDisplayAfterFilteringByBranch() throws Exception {
        try {
            branchObj.selectBranch();
            softAssert.assertTrue(branchObj.displayProductName("Juhayna Almond Milk"));
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    @Test(priority = 3, description = "#3 validateActiveBranch")
    public void validateActiveBranch() throws Exception {
        try {
            branchObj.selectBranch();
            softAssert.assertTrue(branchObj.activeBranch());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 4, description = "#4 validateUpdateProductStatus")
    public void validateUpdateProductStatusINACTIVE() throws Exception {
        try {
            branchObj.selectBranch();
            branchObj.updateStatusOFProduct();
            softAssert.assertTrue(branchObj.confirmationMessage());
            //  softAssert.assertEquals(branchObj.statusOfProductINACTIVE(), "INACTIVE");
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 5, description = "#5 validateUpdateProductStatusACTIVE", dependsOnMethods = "validateUpdateProductStatusINACTIVE")
    public void validateUpdateProductStatusACTIVE() throws Exception {
        try {
            branchObj.selectBranch();
            branchObj.updateStatusOFProduct();
            softAssert.assertTrue(branchObj.confirmationMessage());
            //  softAssert.assertEquals(branchObj.statusOfProductACTIVE(), "ACTIVE");
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /*
        @Test(priority = 6, description = "#6 validateFilterByActiveProduct")
        public void validateFilterByActiveProduct() throws Exception {
            try {
                branchObj.selectBranch();
                branchObj.selectActiveProduct();
                softAssert.assertTrue(branchObj.productStatusList());
                softAssert.assertAll();
            } catch (Exception e) {
                throw new Exception(e);
            }
        }


        @Test(priority = 7, description = "#7 validateFilterByINActiveProduct")
        public void validateFilterByINActiveProduct() throws Exception {
            try {
                branchObj.selectBranch();
                branchObj.selectInactiveProduct();
                softAssert.assertTrue(branchObj.productINACTIVEStatusList());
                softAssert.assertAll();
            } catch (Exception e) {
                throw new Exception(e);
            }
        }

        @Test(priority = 8, description = "#8 validateFilterByAboutGetOutOFStock")
        public void validateFilterByAboutGetOutOFStock() throws Exception {
            try {
                branchObj.selectBranch();
                branchObj.selectAboutGetOutOFStock();
                softAssert.assertTrue(branchObj.displayOutOfStock());
                softAssert.assertAll();
            } catch (Exception e) {
                throw new Exception(e);
            }
        }


        @Test(priority = 9, description = "#9 validateFilterByOutOFStock")
        public void validateFilterByOutOFStock() throws Exception {
            try {
                branchObj.selectAnotherBranch();
                branchObj.selectOutOfStock();
                softAssert.assertTrue(branchObj.outOFStock());
                softAssert.assertAll();
            } catch (Exception e) {
                throw new Exception(e);
            }
        }

    */
    @Test(priority = 10, description = "#10 validateSearchByProductName")
    public void validateSearchByProductName() throws Exception {
        try {
            branchObj.selectBranch();
            branchObj.productName();
            branchObj.search();
            softAssert.assertTrue(branchObj.displayProductName("yuyu"));
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    @Test(priority = 11, description = "#11 validateSearchByInValidProductName")
    public void validateSearchByInValidProductName() throws Exception {
        try {
            branchObj.selectBranch();
            branchObj.invalidProductName();
            branchObj.search();
            softAssert.assertTrue(branchObj.noRecordDisplay());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 12, description = "#12 validateSearchByValidSku")
    public void validateSearchByValidSku() throws Exception {
        try {
            branchObj.selectBranch();
            branchObj.accessMoreSearchOption();
            branchObj.searchSku("123400");
            branchObj.search();
            softAssert.assertTrue(branchObj.validSkuResult());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 13, description = "#13 validateSearchByINValidSku")
    public void validateSearchByInvalidSku() throws Exception {
        try {
            branchObj.selectBranch();
            branchObj.accessMoreSearchOption();
            branchObj.searchSku("96300");
            branchObj.search();
            softAssert.assertTrue(branchObj.noRecordDisplay());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 14, description = "#14 validateSearchByValidBarCode")
    public void validateSearchByValidBarCode() throws Exception {
        try {
            branchObj.selectBranch();
            branchObj.accessMoreSearchOption();
            branchObj.searchBarcode("863863");
            branchObj.search();
            softAssert.assertTrue(branchObj.displayProductName("yuyu"));
            softAssert.assertAll();

        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 15, description = "#15 validateSearchByInvalidBarCode")
    public void validateSearchByInvalidBarCode() throws Exception {
        try {
            branchObj.selectBranch();
            branchObj.accessMoreSearchOption();
            branchObj.searchBarcode("550800");
            branchObj.search();
            softAssert.assertTrue(branchObj.noRecordDisplay());
            softAssert.assertAll();


        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 16, description = "#16 validateSearchByParentCategory")
    public void validateSearchByParentCategory() throws Exception {
        try {
            branchObj.selectBranch();
            branchObj.accessParentCategory();
            branchObj.search();
            softAssert.assertTrue(branchObj.displayParentCategory());
            softAssert.assertAll();

        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 17, description = "#17 validateSearchBySubCategory")
    public void validateSearchBySubCategory() throws Exception {
        try {
            branchObj.selectAnotherBranch();
            branchObj.accessSubCategory();
            branchObj.search();
            softAssert.assertTrue(branchObj.displaySubCategory());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 18, description = "#18 validateSearchByBrand")
    public void validateSearchByBrand() throws Exception {
        try {

            branchObj.selectBranch();
            branchObj.selectBrand();
            branchObj.search();
            softAssert.assertTrue(branchObj.displayBrand());
            softAssert.assertAll();

        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 19, description = "#19 validateExportFunction")
    public void validateExportFunction() throws Exception {
        try {
            branchObj.selectBranch();
            branchObj.export();
            softAssert.assertTrue(branchObj.exportDownSuccess());
            softAssert.assertAll();

        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 20, description = "#20 validateUploadInvalidFile")
    public void validateUploadInvalidFile() throws Exception {
        try {
            branchObj.selectBranch();
            branchObj.accessUpdateInventory();
            branchObj.uploadInValidFormat();
            softAssert.assertTrue(branchObj.warningMessageForInvalidFormat());
            softAssert.assertAll();

        } catch (Exception e) {
            throw new Exception(e);
        }

    }

    @Test(priority = 21, description = "#21 validateUploadValidFile")
    public void validateUploadValidFile() throws Exception {
        try {
            branchObj.selectBranch();
            branchObj.accessUpdateInventory();
            branchObj.uploadValidFormat();
            softAssert.assertTrue(branchObj.successMessage());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }

    }

    @Test(priority = 22, description = "#22validatePlusButtonIsClickable")
    public void validatePlusButtonIsClickable() throws Exception {
        try {
            branchObj.selectBranch();
            branchObj.accessMoreSearchOption();
            softAssert.assertTrue(branchObj.SkuAndBarcodeDisplayed());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 23, description = "#23validateLessOptionIsClickable")
    public void validateLessOptionIsClickable() throws Exception {
        try {
            branchObj.selectBranch();
            branchObj.accessMoreSearchOption();
            branchObj.lessMoreSearchOption();
            softAssert.assertFalse(branchObj.SkuAndBarcodeDisplayed());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    @Test(priority = 24, description = "#24 validateOutOFStockNumbers")
    public void validateOutOFStockNumbers() throws Exception {
        try {
            branchObj.selectAutomationBranch();
            branchObj.selectOutOfStock();
            Thread.sleep(2000);
            softAssert.assertEquals(branchObj.numberOfOutOFStock(), branchObj.countAllItems());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

}