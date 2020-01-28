import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.internal.ITestInvoker;

import java.io.File;
import java.util.List;

public class FirstTestSuite {

    @Test(description = "TestCase One")
    public  void FirstTestCase() throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.navigate().to("https://www.thoughtworks.com");
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//span[text()='Search']")).click();
        driver.findElement(By.xpath("//*[@id='search-bar-input']")).sendKeys("Quality Analyst");
        driver.findElement(By.xpath("//button[text()='Search']")).click();

        driver.findElement(By.xpath("//button[text()='Careers']")).click();
        List<WebElement> jobTitles =  driver.findElements(By.xpath("//div[@class='search-results']//div[@class='search-result__title']//a"));
        Assert.assertEquals(jobTitles.size(),10);

        driver.close();
        driver.quit();
        System.out.println("Inside First Test" );

    }
}
