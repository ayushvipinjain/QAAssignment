package tw;

import Utils.DriverFactory;
import Utils.DriverFactory.DriverType;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class FirstTestSuite {

    @Test(description = "Search Quality Analyst and verify the no of results dispayed")
    public  void FirstTestCase() throws InterruptedException {

        WebDriver driver = DriverFactory.getDriver(DriverType.valueOf(System.getProperty("browser").toUpperCase()));

        driver.navigate().to("https://www.thoughtworks.com");
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//span[text()='Search']")).click();
        driver.findElement(By.xpath("//*[@id='search-bar-input']")).sendKeys("Quality Analyst");
        driver.findElement(By.xpath("//button[text()='Search']")).click();

        driver.findElement(By.xpath("//button[text()='Careers']")).click();
        List<WebElement> jobTitles =  driver.findElements(By.xpath("//div[@class='search-results']//div[@class='search-result__title']//a"));
        Assert.assertEquals(jobTitles.size(),10);

        WebElement nextLink = driver.findElement(By.xpath("//a[text()='Next']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextLink);

        if(nextLink.isDisplayed()) {
            nextLink.click();

            WebDriverWait wait = new WebDriverWait(driver, 15);
            WebElement previousLink =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Previous']")));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", previousLink);
            Assert.assertEquals(previousLink.isDisplayed(), true);
        }

        driver.quit();
    }
}
