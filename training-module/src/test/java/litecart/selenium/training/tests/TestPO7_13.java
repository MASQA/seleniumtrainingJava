package litecart.selenium.training.tests;

import org.junit.Test;

public class TestPO7_13 extends TestBase {


    @Test
    public void TestPO7_13(){
        app.addProductInCart();
        app.addProductInCart();
        app.addProductInCart();
        int quantityProductInCart = app.clearCart();
        assert quantityProductInCart == 0;
    }
}
