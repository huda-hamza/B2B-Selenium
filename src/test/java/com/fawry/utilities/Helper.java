package com.fawry.utilities;

import com.fawry.constants.GeneralConstants;
import com.fawry.constants.PathConstants;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Helper {

    private static final PropertiesReader propertiesReader = new PropertiesReader();
    private static final Properties generalConfigurationProperties = propertiesReader.loadPropertiesFromFile(GeneralConstants.PATHS_CONFIGURATION_FILE_NAME);

    public static void CaptureScreenshot(WebDriver driver, String ScreenShotName)
    {
        Path dest = Paths.get(System.getProperty(GeneralConstants.USER_DIR)+generalConfigurationProperties.getProperty(PathConstants.SCREENSHOT_DIRECTORY), ScreenShotName+".png");
        try {
            Files.createDirectories(dest.getParent());
            FileOutputStream out = new FileOutputStream(dest.toString());
            out.write(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES));
            out.close();
        } catch (IOException e) {
            System.out.println("Exception while taking screenshot"+e.getMessage());
        }


    }
}
