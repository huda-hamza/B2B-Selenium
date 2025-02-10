package com.fawry.tests.b2BProduct;

import com.fawry.constants.b2BMerchant.B2BMerchantFile_NameConstants;
import com.fawry.constants.b2bProduct.B2BProductFile_NameConstants;
import com.fawry.constants.b2bProduct.ProductDataConstant;
import com.fawry.pages.b2BProduct.UOMList;
import com.fawry.pages.base.HomePage;
import com.fawry.tests.base.TestBase;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class UOMListTest extends TestBase {
    UOMList UOMListObj;

    String timeStamp = new SimpleDateFormat("hhmmss").format(new Date());
    private static final Properties testdata = propertiesReader.loadPropertiesFromFile(B2BProductFile_NameConstants.Product_UOM_File_Name);
    String environment = com.fawry.constants.GeneralConstants.DELTA_ENVIRONMENT;

    String Arabic_Name = testdata.getProperty(ProductDataConstant.ArabicName) + timeStamp;
    String English_Name = testdata.getProperty(ProductDataConstant.EnglishName) + timeStamp;
    String Arabic_Name_Exist = testdata.getProperty(ProductDataConstant.Arabic_Name_Exist);
    String English_Name_exist = testdata.getProperty(ProductDataConstant.English_Name_Exist);

    @BeforeClass
    public void initial() throws Exception {
        UOMListObj = new UOMList(driver);
        softAssert = new SoftAssert();
        homePage = new HomePage(driver);

    }

    @BeforeMethod(alwaysRun = true)
    public void productUOMHome() throws Exception {
        UOMListObj.navigateToUOMListPage(environment);
    }


    @Test(priority = 1, description = "#1 ValidateEditArabicName")
    public void ValidateEditArabicUOMName() throws Exception {
        try {
            UOMListObj.editUom();
            UOMListObj.setEditUOMArabic(Arabic_Name);
            UOMListObj.saveEdit();
            softAssert.assertTrue(UOMListObj.ConfirmationMessage());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }

    }

    @Test(priority = 2, description = "#2 ValidateEditEnglishName")
    public void ValidateEditEnglishUOMName() throws Exception {
        try {
            UOMListObj.editUom();
            UOMListObj.setEditUOMEnglish(English_Name);
            UOMListObj.saveEdit();
            softAssert.assertTrue(UOMListObj.ConfirmationMessage());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 3, description = "#3 ValidateCanNotEditUOMCOde")
    public void ValidateCanNOtEditUOMCode() throws Exception {
        try {
            UOMListObj.editUom();
            softAssert.assertFalse(UOMListObj.CodeNotEditable());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 4, description = "#4 ValidateCanNotEditToExistArabicName")
    public void ValidateCanNotEditTOExistArabicName() throws Exception {
        try {
            UOMListObj.editUom();
            UOMListObj.setEditUOMArabic(Arabic_Name_Exist);
            UOMListObj.saveEdit();
            softAssert.assertTrue(UOMListObj.ErrorMessageArabicNameAlreadyExist());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 5, description = "#5 ValidateCanNotEditToExistEnglishName")
    public void ValidateCanNotEditTOExistEnglishName() throws Exception {
        try {
            UOMListObj.editUom();
            UOMListObj.setEditUOMEnglish(English_Name_exist);
            UOMListObj.saveEdit();
            softAssert.assertTrue(UOMListObj.ErrorMessageEnglishNameAlreadyExist());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    @Test(priority = 6, description = "#6 ValidateMandatedArabicNameWhileEdit")
    public void ValidateMandatedArabicNameWhileEdit() throws Exception {
        try {
            UOMListObj.editUom();
            UOMListObj.ClearArabicName();
            softAssert.assertTrue(UOMListObj.DimmedSaveBtn());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }

    }

    @Test(priority = 7, description = "#7 ValidateMandatedEnglishNameWhileEdit")
    public void ValidateMandatedEnglishNameWhileEdit() throws Exception {
        try {
            UOMListObj.editUom();
            UOMListObj.ClearEnglishName();
            softAssert.assertTrue(UOMListObj.DimmedSaveBtn());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }

    }

    @Test(priority = 8, description = "#8 ValidateCanNotEditToSpaceArabicName")
    public void ValidateCanNotEditToSpaceArabicName() throws Exception {
        try {
            UOMListObj.editUom();
            UOMListObj.setEditUOMArabic(" ");
            UOMListObj.saveEdit();
            softAssert.assertTrue(UOMListObj.DimmedSaveBtn());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 9, description = "#9 ValidateCanNotEditToSpaceEnglishName")
    public void ValidateCanNotEditToSpaceEnglishName() throws Exception {
        try {
            UOMListObj.editUom();
            UOMListObj.setEditUOMEnglish(" ");
            UOMListObj.saveEdit();
            softAssert.assertTrue(UOMListObj.DimmedSaveBtn());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    @Test(priority = 10, description = "#10 ValidateCancelEditUOM")
    public void ValidateCancelEditUOM() throws Exception {
        try {
            String ExistEnglishNAme=UOMListObj.NameForExistEnglishNameForAssertion();
            UOMListObj.editUom();
            UOMListObj.cancelDeleteAndEditUom();
            softAssert.assertEquals(UOMListObj.NameForExistEnglishNameForAssertion(),ExistEnglishNAme);
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 11, description = "#9 ValidateDeleteUOM")
    public void ValidateDeleteUOM() throws Exception {
        try {
            UOMListObj.pagination();
            UOMListObj.deleteUom();
            UOMListObj.confirmDelete();
            softAssert.assertTrue(UOMListObj.ConfirmationMessage());
            softAssert.assertAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Test(priority = 12, description = "#10 ValidateCancelDeleteUOM")
    public void ValidateCancelDeleteUOM() throws Exception {
        try {
            UOMListObj.pagination();
            UOMListObj.deleteUom();
            UOMListObj.cancelDeleteAndEditUom();
        } catch (Exception e) {
            throw new Exception(e);
        }

    }


}
