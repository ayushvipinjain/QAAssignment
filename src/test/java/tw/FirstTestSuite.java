package tw;

import Utils.DriverFactory;
import Utils.DriverFactory.DriverType;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class FirstTestSuite {

    @Test(description = "Search Quality Analyst and verify the no of results dispayed")
    public  void FirstTestCase() {

        WebDriver driver = DriverFactory.getDriver(DriverType.valueOf(System.getProperty("browser").toUpperCase()));

        driver.navigate().to("https://www.thoughtworks.com");
        driver.manage().timeouts().implicitlyWait(50000, MILLISECONDS);
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//span[text()='Search']")).click();
        driver.findElement(By.xpath("//*[@id='search-bar-input']")).sendKeys("Quality Analyst");
        driver.findElement(By.xpath("//button[text()='Search']")).click();

        driver.findElement(By.xpath("//button[text()='Careers']")).click();
        List<WebElement> jobTitles =  driver.findElements(By.xpath("//div[@class='search-results']//div[@class='search-result__title']//a"));
        Assert.assertEquals(jobTitles.size(),10);

        WebElement nextLink = driver.findElement(By.xpath("//a[text()='Next']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextLink);

        if(new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(nextLink)).isDisplayed()) {
            nextLink.click();

            WebDriverWait wait = new WebDriverWait(driver, 15);
            WebElement previousLink =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Previous']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", previousLink);
            Assert.assertTrue(previousLink.isDisplayed());
        }

        driver.quit();
    }

    @Test(description = "Search Jobs inside careers section")
    public  void seachJobsUnderCareers() {

        WebDriver driver = DriverFactory
                .getDriver(DriverType.valueOf(System.getProperty("browser").toUpperCase()));
        driver.manage().timeouts().implicitlyWait(50000, MILLISECONDS);
        driver.manage().window().maximize();
        driver.navigate().to("https://www.thoughtworks.com");

        Actions actions = new Actions(driver);

        driver.findElement(By.xpath("//text()[.='Careers']/ancestor::button")).click();
        driver.findElement(By.xpath("//div[@id='careers']//a[text()='Search jobs']")).click();

        WebDriverWait wait  = new WebDriverWait(driver,10);
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-query")));
        searchBox.sendKeys("Quality Analyst");

        WebElement selectCity = driver.findElement(By.xpath("//span[text()='City']"));
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, (document.body.scrollHeight)/5)");
        wait.until(ExpectedConditions.visibilityOf(selectCity));

        selectCity.click();
        WebElement menuItem = selectCity.findElement(By.xpath("following-sibling::div"));
        List<WebElement> menuitems = menuItem.findElements(By.xpath("//label[@class='multi-select-menuitem']"));
        for (WebElement item:menuitems) {
            if(item.isDisplayed())
            {
                String value  = item.getText().trim();
                 if(value.equals("Pune")){
                     actions.moveToElement(item);
                     if(!item.isSelected()) {
                         item.click();
                         selectCity.click();
                     }
                 }
            }
        }

        driver.quit();
    }
}
