package litecart.selenium.training.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class Test10_17 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void Test10_17(){
        driver.get("http://www.litecart.com/admin/");
        driver.findElement(By.cssSelector("[name=username]")).sendKeys("admin");
        driver.findElement(By.cssSelector("[name=password]")).sendKeys("admin");
        driver.findElement(By.cssSelector("[name=login]")).click();
        wait.until(presenceOfElementLocated(By.cssSelector("#app- a")));
        driver.findElement(By.cssSelector("#app- a[href*=catalog]")).click();
        driver.findElement(By.cssSelector(".row a[href*=\"catalog&category_id=1\"]")).click();
        int totalProducts = driver.findElements(By.cssSelector(".row a[href*=\"product&category_id=1\"]:not([title=Edit])")).size();
//        System.out.print(totalProducts);
        int i = 0;
        ArrayList<String> logMessages = new ArrayList<String>();
        while (i < totalProducts){
            List<WebElement> elements = driver.findElements(By.cssSelector(".row a[href*=\"product&category_id=1\"]:not([title=Edit])"));
            elements.get(i).click();
            for (LogEntry l : driver.manage().logs().get("driver")) {
                logMessages.add("For" + Integer.toString(i) +
                        " -th product showed next message in log" +
                        l);
            }
            for (LogEntry l : driver.manage().logs().get("browser")) {
                logMessages.add("For" + Integer.toString(i) +
                        " -th product showed next message in log" +
                        l);
            }
            for (LogEntry l : driver.manage().logs().get("client")) {
                logMessages.add("For" + Integer.toString(i) +
                        " -th product showed next message in log" +
                        l);
            }
            driver.findElement(By.cssSelector("button[name=cancel]")).click();
            i++;
        }
        for (String logMessage:logMessages) {
            System.out.println(logMessage);
        }
        assert logMessages.size() == 0;
    }

    @After
    public void stop(){
        driver.close();
        driver.quit();
        driver = null;
    }
}
