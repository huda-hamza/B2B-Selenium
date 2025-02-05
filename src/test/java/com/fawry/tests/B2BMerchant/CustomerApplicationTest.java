package com.fawry.tests.b2BMerchant;

import com.fawry.constants.B2BMerchantFile_NameConstants;
import com.fawry.constants.MerchantdataConstant;
import com.fawry.pages.b2BMerchant.CustomerApplicationP;
import com.fawry.pages.base.HomePage;
import com.fawry.tests.base.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Properties;

public class CustomerApplicationTest extends TestBase {

    private static final Properties testdata =propertiesReader.loadPropertiesFromFile(B2BMerchantFile_NameConstants.TestData_Customer_APPLICATION);
    String environment = com.fawry.constants.GeneralConstants.DELTA_ENVIRONMENT;
    CustomerApplicationP CST;
    SoftAssert softAssert;
    HomePage homePage;

    @BeforeClass
    public void setup() throws Exception {
        CST = new CustomerApplicationP(driver);
        softAssert = new SoftAssert();
        homePage = new HomePage(driver);
        //CST.navigateToCustomerApplicationPage(environment);
    }
    @BeforeMethod(alwaysRun = true)
    public void customerApplicationHome() throws Exception {CST.navigateToCustomerApplicationPage(environment);}

    @Test(priority = 1)
    public void addingNewItemInCustomDefined() throws Exception {
        try{
            CST.navigateToExistMerchant();
            CST.scrollDownToPage();
            CST.addNewItemButton();
            CST.enterDataOnLabels(testdata.getProperty(MerchantdataConstant.Label_Name_Arabic),testdata.getProperty(MerchantdataConstant.Label_Name_English));
            CST.submitRequest();
            softAssert.assertTrue(CST.ConfirmationMessage());
      //softAssert.assertTrue(CST.returnToNewMerchant());
            softAssert.assertAll();
        }catch (Exception e){
            throw new Exception(e);
        }

    }

//, dependsOnMethods = "addingNewItemInCustomDefined"
    @Test(priority = 2)
    public void deletingNotRequiredItems() throws Exception {
        try{
        CST.navigateToExistMerchant();
        CST.scrollDownToPage();
        CST.navigateToDotsMenu();
        CST.deleteInMenuItem();
        CST.deleteItem();
       // softAssert.assertTrue(CST.ConfirmationMessage());
        softAssert.assertTrue(CST.returnToNewMerchant());
        softAssert.assertAll();
    }catch (Exception e){
        throw new Exception(e);
    }}

    //Cancel deleting fields from items selected for template

    @Test(priority = 3)
    public void cancelDeletingFromItemSelectedExistingMerchant() throws Exception {
     try{   CST.navigateToExistMerchant();
        CST.selectInvoiceExistMerchant();
        CST.trashItemInSelectedTemplateExistCst();
        CST.cancelDeleteBtn();
        softAssert.assertFalse(CST.checkEnableCheckboxForExistingMerchant());
        softAssert.assertAll();
    }catch (Exception e){
            throw new Exception(e);
        }}

    @Test(priority = 4, description = "#3")
    public void cancelDeletingFromItemSelectedNewMerchant() throws Exception {
      try{  CST.selectNationalIDNewMerchant();
        CST.deleteIconInSelectedTemplate();
        CST.cancelDeleteBtn();
        softAssert.assertFalse(CST.checkEnableCheckBoxForNewMerchant());
        softAssert.assertAll();

    }catch (Exception e){
        throw new Exception(e);
    }}
    //deleting fields from items selected for template

    @Test(priority = 5)
    public void deletingItemFromSelectedTemplateExistMerchant() throws Exception {
      try{  CST.navigateToExistMerchant();
         CST.selectInvoiceExistMerchant();
        CST.trashItemInSelectedTemplateExistCst();
        CST.deleteItem();
        CST.saveTemplateBtn();
        Assert.assertTrue(CST.ConfirmationMessage());
        softAssert.assertAll();
//        CST.navigateToExistMerchant();
//        softAssert.assertTrue(CST.checkEnableCheckBoxForExistingMerchant());
//        softAssert.assertAll();
    }catch (Exception e){
        throw new Exception(e);
    }}

    @Test(priority = 6)
    public void deletingItemFromSelectedTemplateNewMerchant() throws Exception {
       try{ CST.selectNationalIDNewMerchant();
        CST.deleteIconInSelectedTemplate();
        CST.deleteItem();
        CST.saveTemplateBtn();
        softAssert.assertTrue(CST.checkEnableCheckBoxForNewMerchant());
        softAssert.assertAll();
    }catch (Exception e){
        throw new Exception(e);
    }}

    @Test(priority =  7)
    public void editArabicNameForCustomDefined() throws Exception {
     try{   addingNewItemInCustomDefined();
        CST.navigateTOExistMerchantDuringExecution();
        CST.scrollDownToPage();
        CST.navigateToDotsMenu();
        CST.editInMenuItem();
        CST.editArabicName("Customer");
        CST.saveEditLabel();
        softAssert.assertTrue(CST.returnToNewMerchant());
        softAssert.assertAll();
   }catch (Exception e){
         throw new Exception(e);
     }}

    @Test (priority =  8)
    public void editValueToFileUpload() throws Exception {
      try{  CST.navigateToExistMerchant();
        CST.scrollDownToPage();
        CST.navigateToDotsMenu();
        CST.editInMenuItem();
        CST.accessValueEditList();
        CST.selectFileUpload();
        CST.saveEditLabel();
        softAssert.assertTrue(CST.returnToNewMerchant());
        softAssert.assertAll();
    }catch (Exception e){
          throw new Exception(e);
      }}

    @Test(priority = 9)
    public void editEnglishNameForCustomDefined() throws Exception {
      try{  CST.navigateToExistMerchant();
        CST.scrollDownToPage();
        CST.navigateToDotsMenu();
        CST.editInMenuItem();
        CST.editEnglishName("Test Customer");
        CST.saveEditLabel();
        softAssert.assertTrue(CST.returnToNewMerchant());
        softAssert.assertAll();
    }catch (Exception e){
          throw new Exception(e);
      }}

    @Test(priority = 10)
    public void mandatedLabelArName() throws Exception {
      try{  CST.navigateToExistMerchant();
        CST.scrollDownToPage();
        CST.addNewItemButton();
        CST.enterDataOnLabels("",testdata.getProperty(MerchantdataConstant.Label_Name_English));
        CST.submitRequest();
        softAssert.assertTrue(CST.mandatedLabelArName());
        softAssert.assertAll();

    }catch (Exception e){
          throw new Exception(e);
      }}

    @Test(priority = 11)
    public void mandatedLabelENName() throws Exception {
       try{ CST.navigateToExistMerchant();
        CST.scrollDownToPage();
        CST.addNewItemButton();
        CST.enterDataOnLabels(testdata.getProperty(MerchantdataConstant.Label_Name_Arabic),"");
        CST.submitRequest();
        softAssert.assertTrue(CST.mandatedLabelEnName());
        softAssert.assertAll();
    }catch (Exception e){
           throw new Exception(e);
       }}

    @Test(priority = 12)
    public void cancelAddNewItem() throws Exception {
        CST.navigateToExistMerchant();
        CST.scrollDownToPage();
        CST.addNewItemButton();
        CST.enterDataOnLabels(testdata.getProperty(MerchantdataConstant.Label_Name_Arabic),testdata.getProperty(MerchantdataConstant.Label_Name_English));
        CST.cancelRequest();
        softAssert.assertTrue(CST.results());
        softAssert.assertAll();
    }


    @Test(priority = 13)
    public void validateNotEditOnAccountNumberInNewMerchant()
    {
        softAssert.assertFalse(CST.editAccountResult());
        softAssert.assertAll();
    }

    @Test(priority = 14)
    public void validateEditOnAccountNumberInExistMerchant() throws Exception {
        CST.navigateToExistMerchant();
        softAssert.assertTrue(CST.editAccountResultExistMerchant());
        softAssert.assertAll();
    }

    @Test(priority = 15)
    public void ValidateOnRequiredFieldOnNewMerchant() throws Exception {
        CST.selectInvoiceOnNewMerchant();
        CST.requiredBtn();
        softAssert.assertTrue(CST.ValidateRequiredFieldNewMerchant());
        softAssert.assertAll();
    }

    @Test(priority = 16)
    public void ValidateOnRequiredFieldOnExistMerchant() throws Exception {
        CST.navigateToExistMerchant();
        CST.selectInvoiceExistMerchant();
        CST.requiredBtnExistMerchant();
        softAssert.assertTrue(CST.ValidateRequiredFieldExistMerchant());
        softAssert.assertAll();
    }

}
