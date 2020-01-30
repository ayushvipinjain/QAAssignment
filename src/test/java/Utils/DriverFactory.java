package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.BeforeTest;


public class DriverFactory {

//    private static String Browser = System.getProperty("Browser");

    public static WebDriver getDriver(DriverType browser) {
        WebDriver driver = null;
        switch (browser) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case SAFARI:
                WebDriverManager.operadriver().setup();
                driver = new SafariDriver();
                break;
            case INTERNET_EXPLORER:
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;
        }
        return driver;
    }

    public enum DriverType {
        CHROME("chrome"),
        FIREFOX("firefox"),
        INTERNET_EXPLORER("internet explorer"),
        SAFARI("safari");

        private String driverType;

        DriverType(String driverType) {
            this.driverType = driverType;
        }

        public String getDriverType() {
            return this.driverType;
        }
    }
}
