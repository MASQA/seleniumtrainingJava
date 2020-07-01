package litecart.selenium.training.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends Page{

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CartPage open() {
        driver.get("http://www.litecart.com/en/checkout");
        return this;
    }

    public boolean isOnThisPage() {
        return driver.findElements(By.id("box-login")).size() > 0;
    }

    public boolean isNotEmpty() {
        return driver.findElements(By.cssSelector(".dataTable :not(.header) > .item")).size() > 0;
    }

    public void deleteProductFromCart() {
        List<WebElement> elementsInTable = driver.findElements(By.cssSelector(".dataTable :not(.header) > .item"));
        int quantityProductsInDataTableBeforeRemoving = elementsInTable.size();
        driver.findElement(By.cssSelector("button[name=remove_cart_item]")).click();
        wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector(".dataTable :not(.header) > .item"),quantityProductsInDataTableBeforeRemoving));


    }

    public int productInCart() {
        return driver.findElements(By.cssSelector(".dataTable :not(.header) > .item")).size();
    }
}



