package litecart.selenium.training.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import litecart.selenium.training.pages.CartPage;
import litecart.selenium.training.pages.MainPage;
import litecart.selenium.training.pages.ProductPage;

public class Application {

    private WebDriver driver;

    private MainPage mainPage;
    private ProductPage productPage;
    private CartPage cartPage;

    public Application() {
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    public void quit() {
        driver.quit();
    }

    public void addProductInCart() {
        mainPage.open();
        String link = mainPage.getLinkToProduct();
        System.out.println(link);
        productPage.open(link);
        if (productPage.hasProductSize()){
            productPage.setSize();
        }
        productPage.addToCart();
        mainPage.open();
    }

    public int clearCart (){
        cartPage.open();
        do{
            cartPage.deleteProductFromCart();
        } while (cartPage.isNotEmpty());
        return cartPage.productInCart();
    }

}
