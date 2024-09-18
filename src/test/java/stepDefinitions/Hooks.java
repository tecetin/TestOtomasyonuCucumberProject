package stepDefinitions;

import io.cucumber.java.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utilities.DriverParallel;

import java.io.FileInputStream;
import java.io.IOException;

public class Hooks {

    WebDriver driver;
    private Workbook workbook;
    private FileInputStream file;

    @Before
    public void setUp(){
        if (driver != null) {
            DriverParallel.quitDriver();
        }
        driver = DriverParallel.getDriver();

    }

    @After
    public void tearDown(Scenario scenario ) throws IOException {
        final byte[] screenshot=((TakesScreenshot) DriverParallel.getDriver()).getScreenshotAs(OutputType.BYTES);
        if (scenario.isFailed()) {
            scenario.attach(screenshot, "image/png","screenshots");
        }
        DriverParallel.quitDriver();


        if (workbook != null) {
            workbook.close();
        }
        if (file != null) {
            file.close();
        }

    }
}
