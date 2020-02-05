package pages;

import org.openqa.selenium.WebDriver;

public class BasePage {

    WebDriver driver;

    public BasePage(WebDriver _driver){
        driver = _driver;
    }

    public void waitForPageToLoad()
    {

    }
}
