package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver _driver) {
        super(_driver);
    }

    By searchMagnifier = By.xpath( "//span[text()='Search']");
    By searchBox = By.xpath("//*[@id='search-bar-input']");
    By searchBtn = By.xpath("//button[text()='Search']");

    public void searchKeyWord(String keyword){
        driver.findElement(searchMagnifier).click();
        driver.findElement(searchBox).sendKeys(keyword);
        driver.findElement(searchBtn).click();
    }
}