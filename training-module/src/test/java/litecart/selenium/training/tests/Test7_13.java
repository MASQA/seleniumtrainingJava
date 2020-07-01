package litecart.selenium.training.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.not;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfElementsToBeLessThan;

//делайте сценарий для добавления товаров в корзину и удаления товаров из корзины.

//        1) открыть главную страницу
//        2) открыть первый товар из списка
//        2) добавить его в корзину (при этом может случайно добавиться товар, который там уже есть, ничего страшного)
        //3) подождать, пока счётчик товаров в корзине обновится
//        4) вернуться на главную страницу, повторить предыдущие шаги ещё два раза, чтобы в общей сложности в корзине было 3 единицы товара
//        5) открыть корзину (в правом верхнем углу кликнуть по ссылке Checkout)
    //    6) удалить все товары из корзины один за другим, после каждого удаления подождать, пока внизу обновится таблица



public class Test7_13 {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,20);
    }

    @Test
    public void Test7_13(){
        driver.get("http://www.litecart.com/en/");

        int j = 3;
        do {
            wait.until(presenceOfElementLocated(By.cssSelector("#box-most-popular")));
            String lastText = driver.findElement(By.cssSelector("#cart .content .quantity")).getText();
//            System.out.println(lastText);
            int valueProduct = Integer.parseInt(lastText);
//            System.out.println("valueProduct " +valueProduct);
            valueProduct++;
            String newText = Integer.toString(valueProduct);
//            System.out.println("newText  " +newText );
            driver.findElement(By.cssSelector(".product >a.link:not([title*=Yellow])")).click();
            wait.until(presenceOfElementLocated(By.cssSelector("#box-product .title")));
            driver.findElement(By.cssSelector("button[name=add_cart_product]")).click();
            String currentText = driver.findElement(By.cssSelector("#cart .content .quantity")).getText();
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#cart .content .quantity"),newText));
//            System.out.println("currentText   " +currentText );
            driver.findElement(By.cssSelector("#logotype-wrapper a[href*=litecart]")).click();
            j--;
        } while (j > 0);

        driver.findElement(By.cssSelector("a[href*=checkout].link")).click();
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(".dataTable :not(.header) > .item"),0));
        List<WebElement> elementsInCart= driver.findElements(By.cssSelector(".shortcuts li"));
        int quantityElementsInCart = elementsInCart.size();
        List<WebElement> elementsInTable = driver.findElements(By.cssSelector(".dataTable :not(.header) > .item"));
        int quantityProductsInDataTable = elementsInTable.size();


        int i = 0;
        while (i < quantityElementsInCart) {
            driver.findElement(By.cssSelector("button[name=remove_cart_item]")).click();
            wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector(".dataTable :not(.header) > .item"),quantityProductsInDataTable - i));
            i++;
        }


    }

    @After
    public void stop(){
        driver.close();
        driver.quit();
        driver = null;
    }

}
