package d30_01_2023;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class zadatak1 {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        driver.get("https://web.dev/patterns/web-vitals-patterns/infinite-scroll/infinite-scroll/demo.html");

        WebElement select = driver.findElement(By.id("delay-select"));
        Select selectD = new Select(select);
        selectD.selectByVisibleText("2000ms");
        Thread.sleep(5000);
        Actions actions = new Actions(driver);
        WebElement scrollBtn = driver.findElement(By.id("infinite-scroll-button"));
        actions.moveToElement(scrollBtn);
        actions.perform();
        Thread.sleep(2000);
//        WebElement polje = driver.findElement(By.xpath("//*[text()='C']"));
        actions.scrollByAmount(0,5);
            actions.moveToElement(scrollBtn);
            actions.perform();


//        Thread.sleep(5000);
    wait.until(ExpectedConditions.elementToBeClickable(scrollBtn));

       scrollBtn.click();
       wait.until(ExpectedConditions.numberOfElementsToBe(By.className("item"),8));
      Thread.sleep(2000);
       wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(scrollBtn)));

        driver.quit();
    }
}
