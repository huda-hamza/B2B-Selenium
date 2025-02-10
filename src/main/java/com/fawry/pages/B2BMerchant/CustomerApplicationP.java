package com.fawry.pages.b2BMerchant;

import com.fawry.constants.b2BMerchant.B2BMerchantFile_NameConstants;
import com.fawry.constants.b2BMerchant.MerchantdataConstant;
import com.fawry.pages.base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class CustomerApplicationP extends PageBase {

    private static final Properties URl = propertiesReader.loadPropertiesFromFile(B2BMerchantFile_NameConstants.TestData_Customer_APPLICATION);
    String ariaSelected;
    private By existMerchant= By.id("p-tabpanel-1-label");
    private By newMerchant=By.id("p-tabpanel-0-label");
    private By addNewItemBtn= By.xpath("//div//button[contains(@class,'p-button-success')]");
            //By.id("//button[contains(@class,'p-button-success')]");
    private By addnewItemAssert=By.xpath("//span[text()='Add New item']");
    private By labelNameArabic =By.xpath("//input[@placeholder='label Name (AR)']");
    private By labelNameEnglish =By.xpath("//input[@placeholder='label Name (EN)']");
    private By valueChoices=By.xpath("p-ripple p-element p-dropdown-item");
    private By saveButton=By.xpath("//button[contains(@class,'p-button-success')]");

    private By cancelBtn=By.xpath("//button[contains(@class,'deleteAction ')]");
    private By selectCheckBox = By.xpath("//tr//td[1]");
    private By dotsMenu=By.xpath("//tr[.//div[text()='Test']]//button[contains(@class,'p-component')]");
    private By deleteItem=By.xpath("//*[contains(@class,'p-element ng-tns-c58')]");
    private By deleteBtn=By.xpath("//p-button[contains(@class,'deleteBtn')]//button");
    private By cancelDeleteBtn=By.xpath("//p-button[contains(@class,'CancelBtn ')]//button");
    private By nationalIDCheckBox=By.xpath("//div[contains(text(),'الرقم القومي')]//parent::td//preceding-sibling::td//p-checkbox[@id='predefinedCheckBox']//div[contains(@class,'p-checkbox-box')]");
          //  "//div[contains(text(),'الرقم القومي')]//parent::td//preceding-sibling::td//p-checkbox[@id='predefinedCheckBox']//div\""

   private By requiredButton=By.xpath("//span[contains(@class,'currentStatus')]");
   private By requiredButtonExistMerchant=By.xpath("//tr[1]//span[@class='p-button-label']");
    private By trash = By.xpath("//div//span[@class='pi pi-trash']");

    private By trashIconSelectedForTemplate =By.xpath("//div[text()='NATIONALID']//parent::div//following-sibling::div//span[contains(@class,'pi pi-trash')]");
    private By invoiceExistMerchant=By.xpath("//p-checkbox[@id='existPredefinedCheckBox']//div[@class='p-checkbox-box']");

    private By invoiceNewMerchant=By.xpath("//p-checkbox[@id='predefinedCheckBox']//div[@class='p-checkbox-box']");
    private By invoiceinactiveassert=By.xpath("//span[text()='رقم الفاتوره']//parent::div//parent::td//preceding-sibling::td//div");
    private By arabicName=By.xpath("//h6[text()='Label Arabic Name']//following-sibling::input");
    private By saveBtnEditLabel=By.xpath("//div[@role='dialog']//button[contains(@class,'p-element p-button')]//span[text()='Save']");
    private By englishName= By.xpath("//h6[text()='Label English Name']//following-sibling::input");

    private By editLabelValue=By.xpath("//p-dropdown[contains(@class,'customDropdown')]//child::div");

    private By choicesOFLabelValue=By.xpath("//p-dropdownitem[contains(@class,'p-element')]");
    private By accountNumberNewMerchant=By.xpath("//tr//td[2]//*[text()='رقم الحساب']");
    private By accountNumberExistMerchant=By.xpath("//tr//td[2]//*[contains(@class,'p-inputtext')]");
    private By saveTemplate =By.xpath("//span[text()='Save Template']");

    private By confirmationMessage=By.xpath("//p-toast/descendant::div[contains(@class,'p-toast-detail')]");
            //("//div[text()='Operation done successfully!']");
    private By addedSuccessMessage = By.xpath("//div[contains(text(),'Added To Template')]");
    public CustomerApplicationP(WebDriver driver) {
        super(driver);
    }


    public void navigateToCustomerApplicationPage(String environment) throws Exception {

        navigateTo(URl.getProperty(MerchantdataConstant.Customer_Application_URl),environment);

    }


    public void navigateToExistMerchant() throws Exception {

        waitForElementToBeClickable(newMerchant);
        clickJSE(existMerchant);
    }

    public void navigateTOExistMerchantDuringExecution() throws Exception {
        Thread.sleep(2000);
        clickJSE(existMerchant);}

    public void scrollDownToPage() throws InterruptedException {
        waitForElementsToBePresent(selectCheckBox);
        scrollToPageEnd();
    }

    public void addNewItemButton() throws Exception {

        clickJSE(addNewItemBtn);
    }

    public void navigateToNewMerchant() throws Exception {
        clickJSE(newMerchant);
        waitForElementsToBePresent(selectCheckBox);
        scrollToPageEnd();}

    public void submitRequest() throws Exception {
        clickJSE(saveButton);

    }

    public void cancelRequest() throws Exception {
        clickJSE(cancelBtn);
        }

    public void enterDataOnLabels(String Arabicname,String Englishname) throws Exception {
        setText(labelNameArabic,Arabicname);
        setText(labelNameEnglish,Englishname);
    }

    public void navigateToDotsMenu() throws Exception {

            clickJSE(dotsMenu);

    }

    public void deleteInMenuItem() throws Exception {
       // waitForElementToBeClickable(deleteItem);
        selectElementFromList(deleteItem,1);
    }

    public void editInMenuItem() throws Exception {
        selectElementFromList(deleteItem,0);
    }

    public void deleteItem() throws Exception {
        clickJSE(deleteBtn);
        waitForInvisibility(deleteBtn);
    }

    public void selectNationalIDNewMerchant() throws Exception {
        waitForElementsToBePresent(nationalIDCheckBox);
        clickJSE(nationalIDCheckBox);
    }

    public void selectInvoiceExistMerchant() throws Exception {
        clickJSE(invoiceExistMerchant);
    }

    public void deleteIconInSelectedTemplate() throws Exception {
        clickJSE(trashIconSelectedForTemplate);
    }
    public void trashItemInSelectedTemplateExistCst() throws Exception {
        clickJSE(trash);}


    public void cancelDeleteBtn() throws Exception {
        clickJSE(cancelDeleteBtn);
    }

    public void editArabicName(String ArabicName) throws Exception {
        setText(arabicName,ArabicName);
    }

    public void editEnglishName(String EnglishName) throws Exception {
        setText(englishName,EnglishName);
    }

    public void saveEditLabel() throws Exception {
        clickJSE(saveBtnEditLabel);
    }

   public Boolean returnToNewMerchant() {
        try {
            Thread.sleep(6000);
            ariaSelected = driver.findElement(newMerchant).getAttribute("aria-selected");
            System.out.println("aria-selected value: " + ariaSelected);
            return ariaSelected.equalsIgnoreCase("true");

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

//    public boolean isDeletePopupDisplayed(){
//        waitForElementToBePresent(By.xpath("//*[text()='Operation done successfully!']"));
//
//        return driver.findElement(By.xpath("//*[text()='Operation done successfully!']")).isDisplayed();
//
//    }
        public Boolean checkEnableCheckboxForExistingMerchant() throws Exception {
                String disabled = driver.findElement(invoiceinactiveassert).getAttribute("class");
                System.out.println("Is the checkbox Disabled: " + disabled);
                if(!disabled.contains("disabled"))
                return true;
          else
                return false;
            }

        public Boolean checkEnableCheckBoxForNewMerchant()
        {
            String disabled = driver.findElement(nationalIDCheckBox).getAttribute("class");
            System.out.println("Is the checkbox Disabled: " + disabled);
            if(!disabled.contains("disabled"))
                return true;
            else
                return false;

        }

public Boolean validateEditingArbicName()
{
    String arabicNameV = driver.findElement(arabicName).getText();
    System.out.println("Updated Arabic Name: " + arabicNameV);
    if(arabicNameV.contains("Customer"))
        return true;
    else
        return false;

}


public void accessValueEditList() throws Exception
{
    clickJSE(editLabelValue);
}

 public void selectFileUpload() throws Exception {
     selectElementFromList(choicesOFLabelValue,1);
 }




 public Boolean mandatedLabelArName()
 {

       String name=driver.findElement(labelNameArabic).getAttribute("class");
       System.out.println(name);

     if (name.contains("ng-dirty") && name.contains("ng-invalid"))
         return true;
        else
       return false;
 }

 public Boolean mandatedLabelEnName()
 {
     String name=driver.findElement(labelNameEnglish).getAttribute("class");
     System.out.println(name);

     if (name.contains("ng-dirty") && name.contains("ng-invalid"))
         return true;
     else

         return false;
 }

    public Boolean results() {
        try {
            isDisplayed(addnewItemAssert);
            System.out.println(getText(addnewItemAssert));
            return true;
        } catch (Exception e) {

            return false;
        }

    }

    public Boolean editAccountResult()
    {
        waitForElementsToBePresent(accountNumberNewMerchant);
        String Tag=driver.findElement(accountNumberNewMerchant).getTagName();
        System.out.println(Tag);
        if (Tag.equalsIgnoreCase("input"))
            return true;

        else
            return false;
    }

    public Boolean editAccountResultExistMerchant()
    {
        waitForElementsToBePresent(accountNumberExistMerchant);
        String Tag=driver.findElement(accountNumberExistMerchant).getTagName();
        System.out.println(Tag);
        if (Tag.equalsIgnoreCase("input"))
            return true;

        else
            return false;
    }

    public void selectInvoiceOnNewMerchant() throws Exception {
        waitForElementsToBePresent(invoiceNewMerchant);
        clickJSE(invoiceNewMerchant);
    }
    public void requiredBtn() throws Exception {
        clickJSE(requiredButton);
    }

    public void requiredBtnExistMerchant() throws Exception {
        clickJSE(requiredButtonExistMerchant);
    }

    public Boolean ValidateRequiredFieldNewMerchant() {
        String name = driver.findElement(requiredButton).getText();
        System.out.println(name);
        if (name.equalsIgnoreCase("Required"))
        return true;
       else
        return false;

    }

    public Boolean ValidateRequiredFieldExistMerchant() {
        String name = driver.findElement(requiredButtonExistMerchant).getText();
        System.out.println(name);
        if (name.equalsIgnoreCase("Required"))
            return true;
        else
            return false;

    }

    public void saveTemplateBtn() throws Exception {
        clickJSE(saveTemplate);

    }


    public Boolean ConfirmationMessage()
    {
        try {
            System.out.println(driver.findElement(confirmationMessage).getText());
            isDisplayed(confirmationMessage);
            return true;
        }catch (Exception e) {
            System.out.println(driver.findElement(confirmationMessage).getText());
            return false;

        }

    }

    public Boolean addedSuccessMessage()
    {
        try {
            System.out.println(driver.findElement(addedSuccessMessage).getText());
            isDisplayed(addedSuccessMessage);
            return true;
        }catch (Exception e) {
            System.out.println(driver.findElement(addedSuccessMessage).getText());
            return false;

        }

    }

}



