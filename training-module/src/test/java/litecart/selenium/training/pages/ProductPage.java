package litecart.selenium.training.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class ProductPage extends Page{

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage open(String link) {
        driver.get(link);
        return this;
    }


    public boolean isOnProductPage() {
        return driver.findElements(By.cssSelector("#box-product .title")).size() > 0;
    }

    public boolean hasProductSize (){
        return driver.findElements(By.cssSelector("select[name*=options]")).size() > 0;
    }

    public void addToCart() {
        String lastText = driver.findElement(By.cssSelector("#cart .content .quantity")).getText();
        int valueProduct = Integer.parseInt(lastText);
        valueProduct++;
        String newText = Integer.toString(valueProduct);
        driver.findElement(By.cssSelector("button[name=add_cart_product]")).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#cart .content .quantity"),newText));
    }

    public void setSize() {
        driver.findElement(By.cssSelector("select[name*=options]")).click();
        driver.findElement(By.cssSelector("select option[value=Small]")).click();
    }
}
