package com.fawry.tests.B2BMerchant;

import com.fawry.constants.B2BMerchantFile_NameConstants;
import com.fawry.constants.MerchantdataConstant;
import com.fawry.constants.ProductDataConstant;
import com.fawry.pages.B2BMerchant.RequestList;
import com.fawry.pages.B2BMerchant.UOM;
import com.fawry.pages.base.HomePage;
import com.fawry.tests.base.TestBase;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class UOMTest extends TestBase {

    String timeStamp = new SimpleDateFormat("hhmmss").format(new Date());
    String environment = com.fawry.constants.GeneralConstants.DELTA_ENVIRONMENT;
    private static final Properties testdata =propertiesReader.loadPropertiesFromFile(B2BMerchantFile_NameConstants.Product_UOM_File_Name);

    UOM uomObj;
    String Arabic_Name=testdata.getProperty(ProductDataConstant.ArabicName)+timeStamp;
    String English_Name=testdata.getProperty(ProductDataConstant.EnglishName)+timeStamp;
    String Arabic_Name_1=testdata.getProperty(ProductDataConstant.ArabicName_1)+timeStamp;
    String English_Name_1=testdata.getProperty(ProductDataConstant.EnglishName_1)+timeStamp;
    String Arabic_Name_2=testdata.getProperty(ProductDataConstant.ArabicName_2)+timeStamp;
    String English_Name_2=testdata.getProperty(ProductDataConstant.EnglishNAme_2)+timeStamp;
    String Code=testdata.getProperty(ProductDataConstant.UOM_Code)+timeStamp;
    String Arabic_Name_Exist=testdata.getProperty(ProductDataConstant.Arabic_Name_Exist);
    String English_Name_exist=testdata.getProperty(ProductDataConstant.English_Name_Exist);
    String Code_Exist=testdata.getProperty(ProductDataConstant.UOM_Code_Exist);

    @BeforeClass
    public void initial() throws Exception {
        uomObj = new UOM(driver);
        softAssert = new SoftAssert();
        homePage = new HomePage(driver);

    }

    @BeforeMethod(alwaysRun = true)
    public void productUOMHome() throws Exception { uomObj.navigateToProductUOMPage(environment);}

    @Test (priority = 1, description = "#1 ValidateAddingUOM")
    public void validateAddUOMWithCode() throws Exception {
        try {
            uomObj.setUomArabicName(Arabic_Name);
            uomObj.setUomEnglishName(English_Name);
            uomObj.setUomCode(Code);
            uomObj.saveUom();
            softAssert.assertTrue(uomObj.confirmationMessage());
            softAssert.assertAll();
        }catch (Exception e){
            throw new Exception(e);
        }
    }


    @Test(priority = 2, description = "#2 ValidateOptionalUOMCode")
    public void validateOptionalUOMCode() throws Exception {
        try {
            uomObj.setUomArabicName(Arabic_Name_1);
            uomObj.setUomEnglishName(English_Name_1);
            uomObj.saveUom();
            softAssert.assertTrue(uomObj.confirmationMessage());
            softAssert.assertAll();
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Test(priority = 3, description = "#3 ValidateCancelAddUOM")
    public void validateCancelAddUOM() throws Exception {
        try {
            uomObj.setUomArabicName(Arabic_Name);
            uomObj.setUomEnglishName(English_Name);
            uomObj.cancelUom();
            softAssert.assertTrue(uomObj.uomListMessageDisplayed());
            softAssert.assertAll();
        }catch (Exception e){
            throw new Exception(e);
        }
    }


    @Test(priority = 4, description = "#4 validateMandatoryArabicField")
    public void validateMandatoryArabicField() throws Exception {
        try {
            uomObj.setUomEnglishName(English_Name);
            uomObj.saveUom();
            softAssert.assertTrue(uomObj.mandatedArabicField());
            softAssert.assertAll();
        }catch (Exception e){
            throw new Exception(e);
        }
        }

    @Test(priority = 5, description = "#5 validateMandatoryEnglishField")
    public void validateMandatoryEnglishField() throws Exception {
        try {
            uomObj.setUomArabicName(Arabic_Name);
            uomObj.saveUom();
            softAssert.assertTrue(uomObj.mandatedEnglishField());
            softAssert.assertAll();
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Test(priority = 6, description = "#6 validateExistArabicName")
    public void validateExistArabicName() throws Exception {
        try {
            uomObj.setUomArabicName(Arabic_Name_Exist);
            uomObj.setUomEnglishName(English_Name_2);
            uomObj.saveUom();
            Thread.sleep(1000);
            softAssert.assertTrue(uomObj.ErrorMessageArabicNameAlreadyExist());
            softAssert.assertAll();
        }catch (Exception e){
            throw new Exception(e);
        }

    }


    @Test(priority = 7, description = "#7 validateExistEnglishName")
    public void validateExistEnglishName() throws Exception {
        try {
            uomObj.setUomArabicName(Arabic_Name_2);
            uomObj.setUomEnglishName(English_Name_exist);
            uomObj.saveUom();
            Thread.sleep(1000);
            softAssert.assertTrue(uomObj.ErrorMessageEnglishNameAlreadyExist());
            softAssert.assertAll();
        }catch (Exception e){
            throw new Exception(e);
        }

    }


    @Test(priority = 8, description = "#8 validateExistCode")
    public void validateExistCode() throws Exception {
        try {
            uomObj.setUomArabicName(Arabic_Name_2);
            uomObj.setUomEnglishName(English_Name_2);
            uomObj.setUomCode(Code_Exist);
            uomObj.saveUom();
            Thread.sleep(1000);
            softAssert.assertTrue(uomObj.ErrorMessageCodeAlreadyExist());
            softAssert.assertAll();
        }catch (Exception e){
            throw new Exception(e);
        }

    }

    @Test(priority = 9, description = "#9 validateArabicNameNotAcceptSpace")
    public void validateArabicNameNotAcceptSpace() throws Exception {
        try {

            uomObj.setUomArabicNameWithSpace();
            uomObj.setUomEnglishName(English_Name);
            uomObj.saveUom();
            softAssert.assertTrue(uomObj.mandatedArabicField());
            softAssert.assertAll();

        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Test(priority = 10, description = "#10 validateEnglishNameNotAcceptSpace")
    public void validateEnglishNameNotAcceptSpace() throws Exception {
        try {
            uomObj.setUomArabicName(Arabic_Name);
            uomObj.setUomEnglishNameWithSpace();
            uomObj.saveUom();
            softAssert.assertTrue(uomObj.mandatedEnglishField());
            softAssert.assertAll();
        }catch (Exception e){
            throw new Exception(e);
        }
    }


}



