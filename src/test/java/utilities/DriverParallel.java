package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class DriverParallel {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>(); //Her test için ayrı bir WebDriver instance'ı sağlayarak paralel testlerin aynı anda çalışmasını destekler.

    private DriverParallel() {}

    public static WebDriver getDriver() {

        if (driver.get() == null) {
            switch (ConfigReader.getProperty("browser")) {
                case "edge":
                    driver.set(new EdgeDriver()); //ThreadLocal'a yeni bir WebDriver instance'ı atar.
                    break;
                case "safari":
                    driver.set(new SafariDriver());
                    break;
                case "firefox":
                    driver.set(new FirefoxDriver());
                    break;
                default:
                    ChromeOptions options = new ChromeOptions();
                    driver.set(new ChromeDriver(options));
            }
            driver.get().manage().window().maximize();
            driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        return driver.get(); //ThreadLocal'dan WebDriver instance'ını alır.
    }

    public static void closeDriver() {
        if (driver.get() != null) {
            driver.get().close();
            driver.remove();
        }
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove(); //ThreadLocal'daki WebDriver instance'ını temizler.
        }
    }
}
