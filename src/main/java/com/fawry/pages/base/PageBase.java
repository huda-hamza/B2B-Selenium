package com.fawry.pages.base;

import com.fawry.constants.GeneralConstants;
import com.fawry.constants.PathConstants;
import com.fawry.utilities.Log;
import com.fawry.utilities.PropertiesReader;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.*;
import java.util.List;

public class PageBase {

    public WebDriver driver;
    JavascriptExecutor jse;
    Actions actions;
    public static PropertiesReader propertiesReader = new PropertiesReader();
    public static final Properties pathsProperties = propertiesReader.loadPropertiesFromFile(GeneralConstants.PATHS_CONFIGURATION_FILE_NAME);
    public static final String downloadDirectoryPath = System.getProperty(GeneralConstants.USER_DIR) + pathsProperties.getProperty(PathConstants.DOWNLOAD_DIRECTORY);
    public static final String uploadDirectoryPath = System.getProperty(GeneralConstants.USER_DIR)+pathsProperties.getProperty(PathConstants.UPLOAD_DIRECTORY);
    private static final Duration waitTime = Duration.ofSeconds(60);
    private static final Duration pollingTime = Duration.ofMillis(500);
    @FindBy(xpath = "//*[@role='option']")
    public List<WebElement> selectOptions;

    public PageBase(WebDriver driver) {
        this.driver = driver;
        this.jse = (JavascriptExecutor) driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 30);
        PageFactory.initElements(factory, this);
    }

    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    public List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    public WebElement getElementFromList(By locator, int index) {
        List<WebElement> elements = driver.findElements(locator);
        if (elements.isEmpty()) {
            return null;
        } else if (index >= 0 && index < elements.size()) {
            return elements.get(index);
        } else {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for list of size " + elements.size());
        }
    }


    public void waitForVisibility(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForTextToBePresentInElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, driver.findElement(locator).getText()));
    }

    public void waitForElementToBePresent(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    public void waitForElementsToBePresent(By... locator) {
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        for (By locat: locator) {
            wait.until(ExpectedConditions.presenceOfElementLocated(locat));
        }
    }

    public void waitForInvisibility(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void scrollToElementJSE(By locator) throws InterruptedException {
        jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView();", findElement(locator));
        Thread.sleep(1000);
    }

    public void clickJSE(By locator) throws Exception {
        if (locator != null) {
            waitForElementToBePresent(locator);
            waitForVisibility(locator);
            waitForElementToBeClickable(locator);
            jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].click();", findElement(locator));
        } else
            throw new Exception("Web element ["+locator.toString()+"] is null .. it could not be located");
    }

    public void clickJSE(WebElement element) throws Exception {
        if (element != null) {
            waitForElementToBeClickable(element);
            jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].click();", element);
        } else
            throw new Exception("Web element 'locator' is null .. it could not be located");
    }

    //check specific file is available by its name
    public Boolean isFileAvailable(String expectedFileName, String path) {
        File file = new File(path);
        File[] listOfFiles = file.listFiles();
        Boolean isFileAvailable = false;
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                String fileName = listOfFile.getName();
                if (fileName.matches(expectedFileName)) {
                    isFileAvailable = true;
                }
            }
        }
        return isFileAvailable;
    }


    public void scrollIntoViewAndClickJSE(By locator) throws Exception {
        //scroll down the page by JSE
        scrollToElementJSE(locator);
        //click by JSE
        clickJSE(locator);
    }

    public void scrollDownJSE(By secondRow) {
        //to scroll down the page
        jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,200)", "");
    }

    public void scrollToPageEnd() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(1000);
    }


    public void scrollIntoView(By locator) {
        //to scroll down the page
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", findElement(locator));
    }

    public void setText(By locator, String text) throws Exception {
        if (locator != null) {
            waitForElementToBePresent(locator);
            scrollToElementJSE(locator);
            waitForVisibility(locator);
            click(locator);
            clear(locator);
            findElement(locator).sendKeys(text);
        } else
            throw new Exception("Web element 'locator' is null .. it could not be located");
    }

    public String getText(By locator) throws Exception {
        if (locator != null) {
            waitForElementToBePresent(locator);
            waitForVisibility(locator);
            return findElement(locator).getText();
        } else
            throw new Exception("Web element 'locator' is null .. it could not be located");
    }

    public String getText(WebElement element) throws Exception {
        if (element != null) {
            waitForVisibility(element);
            return element.getText();
        } else
            throw new Exception("Web element 'locator' is null .. it could not be located");
    }

    public String getElementTextAt(By locator, int index) throws Exception {

        if (locator != null) {
            waitForElementToBePresent(locator);
            waitForVisibility(locator);
            return getElementFromList(locator, index).getText();
        } else
            throw new Exception("Web element 'locator' is null .. it could not be located");
    }
    public String getLastElementText(By locator) throws Exception {

        if (locator != null) {
            waitForElementToBePresent(locator);
            waitForVisibility(locator);
            return getElementFromList(locator, getElementsList(locator).size()-1).getText();
        } else
            throw new Exception("Web element 'locator' is null .. it could not be located");
    }

    public String getTextBoxCurrentValue(By locator) throws Exception {
        if (locator != null) {
            waitForVisibility(locator);
            Thread.sleep(5000);
            return findElement(locator).getAttribute("value");
        } else
            throw new Exception("Web element 'locator' is null .. it could not be located");
    }

    public void clear(By locator) throws Exception {
        if (locator != null) {
            waitForVisibility(locator);
            findElement(locator).clear();
        } else
            throw new Exception("Web element 'locator' is null .. it could not be located");

    }

    public void click(By locator) throws Exception {
        if (locator != null) {
            waitForElementToBePresent(locator);
            waitForVisibility(locator);
            waitForElementToBeClickable(locator);
            findElement(locator).click();
        } else
            throw new Exception("Web element 'locator' is null .. it could not be located");
    }

    public void click(WebElement element) throws Exception {

        if (element != null) {
            waitForElementToBeClickable(element);
            element.click();
        } else
            throw new Exception("Web element 'locator' is null .. it could not be located");
    }

    public void selectElementFromList(By locator, int index) throws Exception {

        if (locator != null) {
            waitForElementToBePresent(locator);
            waitForVisibility(locator);
            waitForElementToBeClickable(locator);
            getElementFromList(locator, index).click();
        } else
            throw new Exception("Web element 'locator' is null .. it could not be located");
    }

    public static String modifyURL(String url, String environment) {
        if (url.startsWith("https://") || url.startsWith("http://")) {
            int startIndex = url.indexOf("//") + 2;
            int endIndex = url.indexOf("/", startIndex);
            if (endIndex != -1) {
                return url.substring(0, startIndex) + environment + url.substring(endIndex);
            } else {
                return url.substring(0, startIndex) + environment;
            }
        }
        return url;
    }


    public void navigateTo(String URL, String environment) {
        driver.get(modifyURL(URL, environment));
    }

    public void navigateToURLAndWaitForElement(String URL, String environment, By elementShouldBeLoaded) {
        navigateTo(URL, environment);
        waitForElementToBePresent(elementShouldBeLoaded);
        waitForVisibility(elementShouldBeLoaded);
    }


    public void selectOptionByDisplayedText(By locator, String displayedText) throws Exception {
        waitForVisibility(locator);
        if (locator != null) {
            if (displayedText != null && !displayedText.trim().isEmpty()) {
                click(locator);
                if (!selectOptions.isEmpty()) {
                    for (WebElement selectOption : selectOptions)
                        if (selectOption.getText().trim().equalsIgnoreCase(displayedText.trim())) {
                            selectOption.click();
                            break;
                        }
                } else {
                    throw new Exception("Drop down list is empty and has no listed options");
                }
            }
        } else
            throw new Exception("Web element 'dropDown' is null .. it could not be located");
    }


    public void selectDateRange(By locator, By monthTextLocator, By yearTextLocator, By nextBtnLocator, By previousBtnLocator, String dayMonthYearValTo, String dayMonthYearValFrom, By afterSelectLocator) throws Exception {
        waitForElementToBeClickable(locator);
        click(locator);
        String month = getText(monthTextLocator);
        System.out.println(month);
        String year = getText(yearTextLocator);
        String dayTo = dayMonthYearValTo.split(" ")[0].trim();
        String monthTo = dayMonthYearValTo.split(" ")[1].trim();
        String yearTo = dayMonthYearValTo.split(" ")[2].trim();
        int dayFrom = Integer.parseInt(dayMonthYearValFrom.split(" ")[0].trim());
        dayFrom = dayFrom - 1;
        String monthFrom = dayMonthYearValFrom.split(" ")[1].trim();
        String yearFrom = dayMonthYearValFrom.split(" ")[2].trim();
        // To Select Date To
        while (!(month.equals(monthTo) && year.equals(yearTo))) {
            click(previousBtnLocator);
            month = getText(monthTextLocator);
            year = getText(yearTextLocator);
            monthTo = dayMonthYearValTo.split(" ")[1].trim();
            yearTo = dayMonthYearValTo.split(" ")[2].trim();
        }
        clickJSE(By.xpath("//span[text()= '" + dayTo + "']"));
        // To Select Date From
        while (!(monthTo.equals(monthFrom) && yearTo.equals(yearFrom))) {
            click(nextBtnLocator);
            monthFrom = dayMonthYearValFrom.split(" ")[1].trim();
            yearFrom = dayMonthYearValFrom.split(" ")[2].trim();
        }
        clickJSE(By.xpath("//span[text()= '" + dayFrom + "']"));

        clickJSE(afterSelectLocator);

    }


    public WebElement getLocatorByItsText(By locatorsList, String targetText) {
        List<WebElement> elementsList = driver.findElements(locatorsList);
        for (WebElement element : elementsList) {
            if (element.getText().trim().contains(targetText.trim())) {
                return element;
            }
        }

        // If no matching element is found, return null
        return null;
    }

    public void hover(WebElement element) {
        actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }
    public void hover(By locator) {
        actions = new Actions(driver);
        actions.moveToElement(findElement(locator)).perform();
    }
    public void clickByAction(WebElement Locator)
    {
        actions = new Actions(driver);
        actions.click(Locator).perform();

    }

    public List<WebElement> getElementsList(By locator) {
        return driver.findElements(locator);
    }

    public boolean checkTextInListOfElements(By locator, String targetText) {
        List<WebElement> elements = getElementsList(locator);
        for (WebElement element : elements) {
            String elementText = element.getText();
            if (elementText.contains(targetText)) {
                System.out.println(elementText);
                return true;
            }
        }

        return false;
    }

    // Method to click on a random element in a list of WebElements
    public void selectRandomElementFromList(By locator) throws Exception {
        List<WebElement> elements = getElementsList(locator);
        if (elements == null || elements.isEmpty()) {
            Log.error("The list of WebElements is empty or null.");
            return;
        }
        Random random = new Random();
        Log.info("SIZE OF LIST :: "+elements.size());
        int randomIndex = random.nextInt(elements.size());
        Log.info("SIZE OF LIST :: "+randomIndex);
        Thread.sleep(3000);
        clickJSE(elements.get(randomIndex));
    }

    public boolean isDirEmpty(String directoryPath) throws IOException {
        final Path directory = (Path) Paths.get(directoryPath);
        try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(directory)) {
            return !dirStream.iterator().hasNext();
        }
    }

    //delete Excel file from a directory by deleting the whole directory
    public void deleteFile(String filePath) {
        File folder = new File(filePath);
        File[] files = folder.listFiles();
        if (files != null) { //some JVMs return null for empty dirs
            for (File f : files) {
                if (f.isDirectory()) {
                    deleteFile(filePath);
                } else {
                    f.delete();
                }
            }
        }
        folder.delete();
    }

    //switch to the new tab
    public void switchToNewTab() throws InterruptedException {
        //list of all tabs id
        List<String> windowTabs = new ArrayList<String>(driver.getWindowHandles());
        //switch to next new tab
        driver.switchTo().window(windowTabs.get(1));
    }

    //close the new tab and back to the main tab
    public void switchToMainTab() throws InterruptedException {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        Thread.sleep(500);
    }

    //wait for a specific file to be downloaded
    public void waitForFileToBeDownloaded(String path) {
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until((WebDriver wd) -> {
            File file = new File(path);
            return file.exists();
        });
    }

    public String waitForTextToChange(By locator, String textShouldChange) throws Exception {
        int retries = 0;
        while (retries < 10) {
            if (Objects.equals(getText(locator), textShouldChange)) {
                Thread.sleep(1500);
                retries++;
            } else {
                return getText(locator);  //return the new text
            }
        }
        throw new Exception("Max retries exceeded. The text is still " + textShouldChange);
    }

    public boolean isDisplayed(By locator) {
        return findElement(locator).isDisplayed();
    }
    public boolean isElementPresent(By locator) {
        try {
            findElement(locator);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }


    public boolean login(String mail, String password, By mailLocator, By passwordLocator, By loginBtn, By welcomeMsg) {
        try {
            setText(mailLocator, mail);
            setText(passwordLocator, password);
            click(loginBtn);
            waitForVisibility(welcomeMsg);
            return isDisplayed(welcomeMsg);
        } catch (Exception e) {
            Log.error("BLOCKING ISSUE - CAN NOT LOGIN TO APPLICATION", e);
            return false;
        }
    }

    public String appendRandomName(String inputName) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        String characters = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < 10; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }

        return inputName+" "+stringBuilder.toString();
    }

    public String generateID() {
        Random random = new Random();
        return String.valueOf(random.nextInt(90000) + 10000);
    }

    public String generateRandomName() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        String characters = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < 10; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }

    // Method to generate a random mobile number
    public String generateRandomMobileNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        String[] prefixes = {"010", "011", "012", "015"};
        String randomPrefix = prefixes[random.nextInt(prefixes.length)];
        sb.append(randomPrefix);
        for (int i = 0; i < 8; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public void pressEnter() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER); //press enter key
        robot.keyRelease(KeyEvent.VK_ENTER); //release enter key
    }

    public List<String> getDDLValues(By list) throws Exception {
        waitForElementToBePresent(list);
        waitForVisibility(list);
        List<String> values = new LinkedList<>();
        List<WebElement> elementList = getElementsList(list);
        for (WebElement element: elementList){
            values.add(getText(element));
        }
        return values;
    }
    public boolean searchOptions(List<String> options, String... searchTerms) {
        for (String term : searchTerms) {
            boolean found = false;
            for (String option : options) {
                if (option.contains(term)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                Log.error("the item ["+term+"] does not found in the list");
                return false;
            }
        }
        return true;
    }

    public boolean isClickable(By locator) {
        waitForElementToBePresent(locator);
        waitForVisibility(locator);
        return findElement(locator).isEnabled();
    }

    public String getAttributeValue(By locator, String attribute) {
        return findElement(locator).getAttribute(attribute);
    }

    //check page is opened by get its url
    public boolean isURLHas(String url) {
        return driver.getCurrentUrl().contains(url);
    }

    public void scrollThenHoverAndClick(By locator) throws Exception {
        scrollToElementJSE(locator);
        hover(locator);
        click(locator);
    }

    public boolean isSelected(By locator) {
        waitForVisibility(locator);
        waitForElementToBeClickable(locator);
        return findElement(locator).isSelected();
    }

    public void refreshFunction()
    {
        driver.navigate().refresh();
    }

    public List<String> getListValues(By list) throws Exception {
        waitForVisibility(list);
        List<String> values = new LinkedList<>();
        List<WebElement> elementList = findElements(list);
        for (WebElement element : elementList) {
            values.add(getText(element));
        }
        return values;
    }

    public List<String> getListValues(List<WebElement> elements) throws Exception {
        List<String> textList = new ArrayList<>();
        for (WebElement element : elements) {
            textList.add(getText(element));
        }
        return textList;
    }

    public void uploadFile (By locator, String filePath) {
        findElement(locator).sendKeys(filePath);
    }

    public void waitForVisibilityOfList(By locator) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(waitTime)
                .pollingEvery(pollingTime)
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(org.openqa.selenium.StaleElementReferenceException.class);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }


    public void scrollAndClickByJSE(By locator) throws Exception {
        if (locator != null) {
            scrollToElement(locator);
            clickJSE(locator);
        } else
            throw new Exception("Web element 'locator' is null .. it could not be located");
    }
    public void scrollToElement(By locator) {
        jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", findElement(locator));
       // waits.waitForElementToBeInViewport(locator);
    }

    public void selectDateRange2(By locator, By monthTextLocator, By yearTextLocator, By nextBtnLocator, By previousBtnLocator, String dayMonthYearValTo, String dayMonthYearValFrom, By afterSelectLocator) throws Exception {
        scrollAndClickByJSE(locator);
        String month = getText(monthTextLocator);
        String year = getText(yearTextLocator);
        String dayTo = dayMonthYearValTo.split(" ")[0].trim();
        String monthTo = dayMonthYearValTo.split(" ")[1].trim();
        String yearTo = dayMonthYearValTo.split(" ")[2].trim();
        int dayFrom = Integer.parseInt(dayMonthYearValFrom.split(" ")[0].trim()) - 1;
        String monthFrom = dayMonthYearValFrom.split(" ")[1].trim();
        String yearFrom = dayMonthYearValFrom.split(" ")[2].trim();
        // To Select Date To
        while (!(month.equals(monthTo) && year.equals(yearTo))) {
            click(previousBtnLocator);
            month = getText(monthTextLocator);
            year = getText(yearTextLocator);
            monthTo = dayMonthYearValTo.split(" ")[1].trim();
            yearTo = dayMonthYearValTo.split(" ")[2].trim();
        }
        click(By.xpath("//span[text()= '" + dayTo + "']"));
        // To Select Date From
        while (!(monthTo.equals(monthFrom) && yearTo.equals(yearFrom))) {
            click(nextBtnLocator);
            monthFrom = dayMonthYearValFrom.split(" ")[1].trim();
            yearFrom = dayMonthYearValFrom.split(" ")[2].trim();
        }
        click(By.xpath("//span[text()= '" + dayFrom + "']"));
       // click(afterSelectLocator);
    }
}



