package com.fawry.tests.base;

//import com.fawry.backendservices.ServicesDelegate;
import com.fawry.constants.B2BMerchantFile_NameConstants;
import com.fawry.constants.GeneralConstants;
import com.fawry.constants.PathConstants;
import com.fawry.listeners.TestExecutionListener;
import com.fawry.pages.base.HomePage;
import com.fawry.pages.base.LoginPage;
import com.fawry.utilities.Log;
import com.fawry.utilities.PropertiesReader;
import com.fawry.utilities.Helper;
import com.paulhammant.ngwebdriver.NgWebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

public class TestBase extends TestExecutionListener {
    public static WebDriver driver;
    NgWebDriver ngDriver;
    JavascriptExecutor jse;
    public SoftAssert softAssert;
    protected static final PropertiesReader propertiesReader = new PropertiesReader();
    private static final Properties generalConfigurationProps = propertiesReader.loadPropertiesFromFile(GeneralConstants.GENERAL_CONFIGURATION_FILE_NAME);
    private static final Properties pathsProperties = propertiesReader.loadPropertiesFromFile(GeneralConstants.PATHS_CONFIGURATION_FILE_NAME);
    private static final String browserDefaultDownloadPath = System.getProperty(GeneralConstants.USER_DIR) + pathsProperties.getProperty(PathConstants.DOWNLOAD_DIRECTORY);

    String username;
    String password;
    public LoginPage loginPage;
    public HomePage homePage;
   // public ServicesDelegate backendService;

    

    private ChromeOptions setChromeOption() {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default.content_settings.popups", 0);
        chromePrefs.put("download.default_directory", browserDefaultDownloadPath);
        chromePrefs.put("download.prompt_for_download", false);
        options.setExperimentalOption("prefs", chromePrefs);
        options.setAcceptInsecureCerts(true);
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
        //options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        //options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-dev-shm-usage");
        //hide popup Chrome message to save password
        chromePrefs.put("credentials_enable_service", false);
        //disable password manager
        chromePrefs.put("profile.password_manager_enabled", false);
        return options;
    }


    @Parameters({"url", "User"})
    @BeforeTest(alwaysRun = true)
    public void setup(@Optional("https://delta.supply-chain.fawry.io/be-login/auth/login") String url, @Optional("DELTA_BE") String User) {
        try {
            Log.info("Initialize Selenium WebDriver");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(setChromeOption());
            jse = (JavascriptExecutor) driver;
            ngDriver = new NgWebDriver(jse).withRootSelector("\"app-root\"");
            driver.get(url);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            Log.info("Selenium web driver was initialized successfully");

            //login
            Log.info("Start login");
            switch (User) {
                case ("BO") -> {
                    username = generalConfigurationProps.getProperty(GeneralConstants.VALID_BO_MAIL);
                    password = generalConfigurationProps.getProperty(GeneralConstants.VALID_BO_PASSWORD);
                }
                case ("DELTA_BE") -> {
                    username = generalConfigurationProps.getProperty(GeneralConstants.VALID_BE_DELTA_MAIL);
                    password = generalConfigurationProps.getProperty(GeneralConstants.VALID_BE_DELTA_PASSWORD);
                }
                case ("OMEGA_BE") -> {
                    username = generalConfigurationProps.getProperty(GeneralConstants.VALID_BE_OMEGA_MAIL);
                    password = generalConfigurationProps.getProperty(GeneralConstants.VALID_BE_OMEGA_PASSWORD);
                }
            }
            loginPage = new LoginPage(driver);
            if (loginPage.loginSuccessfully(username, password)) {
                homePage = new HomePage(driver);
              //  backendService = new ServicesDelegate();
                Log.info("Logged in successfully");
            } else {
                Log.error("Could not login using the supplied credentials username: " + username + " and password: " + password);
                driver.quit();
                System.exit(1);
            }

        } catch (Exception e) {
            Log.error("Error occurred while initializing selenium web driver", e);
            driver.quit();
            System.exit(1);
        }


    }


    @AfterTest
    public void quit() {
        Log.info("Closing selenium Web driver after test");
      //  driver.quit();
    }

    @AfterMethod
    public void screenshotFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.SKIP || result.getStatus() == ITestResult.FAILURE) {
            Log.info("Taking Screenshot...");
            Helper.CaptureScreenshot(driver, result.getName());
        }
    }


}
