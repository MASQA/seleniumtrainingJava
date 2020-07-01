package litecart.selenium.training.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import java.util.List;
import java.util.Set;

public class Test8_14 {


    private WebDriver driver;
    private WebDriverWait wait;

    public ExpectedCondition<String> anyWindowOtherThan(Set<String> oldWindows) {
        return new ExpectedCondition<String>() {
            public String apply(WebDriver driver) {
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
    }



    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void Test8_14(){
        driver.get("http://www.litecart.com/admin/");
        driver.findElement(By.cssSelector("[name=username]")).sendKeys("admin");
        driver.findElement(By.cssSelector("[name=password]")).sendKeys("admin");
        driver.findElement(By.cssSelector("[name=login]")).click();
        wait.until(presenceOfElementLocated(By.cssSelector("#app- a")));
        driver.findElement(By.cssSelector("#app- a[href*=countr]")).click();
        wait.until(presenceOfElementLocated(By.cssSelector("a.button[href*=edit_countr]")));
        driver.findElement(By.cssSelector("a.button[href*=edit_countr]")).click();
        wait.until(presenceOfElementLocated(By.cssSelector("a .fa-external-link")));
        int totalExternalLinks = driver.findElements(By.cssSelector("a .fa-external-link")).size();
        System.out.println("Total clicks will be" + totalExternalLinks );
        int i = 0;
        while (i < totalExternalLinks ){
            List<WebElement> externalLinks = driver.findElements(By.cssSelector("a .fa-external-link"));
            String mainWindow = driver.getWindowHandle();
            Set<String> oldWindows = driver.getWindowHandles();
            externalLinks.get(i).click();
            String newWindow = wait.until(anyWindowOtherThan(oldWindows));
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(mainWindow);
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
