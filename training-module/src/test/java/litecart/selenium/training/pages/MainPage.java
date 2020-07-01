package litecart.selenium.training.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;

public class MainPage extends Page{

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage open() {
        driver.get("http://www.litecart.com/");
        return this;
    }

    public boolean isOnMainPage() {
        return driver.findElements(By.id("#box-most-popular")).size() > 0;
    }

    public String getLinkToProduct() {
        return driver.findElement(By.cssSelector(".product >a.link")).getAttribute("href");
    }
}
