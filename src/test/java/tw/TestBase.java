package tw;

import Utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class TestBase {

    WebDriver driver = null;
    @BeforeMethod(description = "Runs Before All the Test Cases")
    public void LauchApp(){
        driver = DriverFactory.getDriver(DriverFactory.DriverType.valueOf(System.getProperty("browser").toUpperCase()));
        driver.navigate().to("https://www.thoughtworks.com");
        driver.manage().timeouts().implicitlyWait(50000, MILLISECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod(description = "Close Browser")
    public void ExitBrowser(){

        if(driver!=null){
            driver.close();
        }

        if(driver!=null){
            driver.quit();
        }
     }
}
