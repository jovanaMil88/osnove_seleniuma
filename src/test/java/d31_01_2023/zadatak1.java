package d31_01_2023;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class zadatak1 {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(" https://boomf.com/apps/proxy/boomf-bomb/i-bloody-love-you");
        driver.manage().window().maximize();

        driver.findElement(By.className("edit-image")).click();
        driver.findElement(By.id("image-option-remove")).click();
        driver.findElement(By.className("edit-image")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("sc-eGugkK")));

        driver.findElement(By.id("imageUpload"))
                .sendKeys(new File("test_data/download.jfif").getAbsolutePath());
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(
                "//div[contains(@class,'sc-bjfHbI')]"), 1));
        driver.findElement(By.xpath("//img[@id='image-option-0']")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Thread.sleep(2000);

        driver.findElement(By.className("edit-image")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//div[contains(@class,'sc-eGugkK')]")));
//        Uploadujte right.jpg sliku
//        Sacekajte da je ispod uploada slike, broj slika 2.
//        Klik na sliku
//        Klik na Done dugme
//        Sacekajte 2s
        driver.findElement(By.id("imageUpload"))
                .sendKeys(new File("downloads/flying-ninja.jpg").getAbsolutePath());
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(
                "//div[contains(@class,'sc-bjfHbI')]"), 2));
        driver.findElement(By.xpath("//img[@id='image-option-1']")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(2000);
//        Klik na Add Image dugme
//        Sacekajte da se pojavi desni meni
//        Uploadujte back.jpg sliku
//        Sacekajte da je ispod uploada slike, broj slika 3.
//        Klik na sliku
//        Klik na Done dugme
//        Sacekajte 2s
        driver.findElement(By.className("edit-image")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//div[contains(@class,'sc-eGugkK')]")));
        driver.findElement(By.id("imageUpload"))
                .sendKeys(new File("test_data/3798165661391510699.png")
                        .getAbsolutePath());
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(
                "//div[contains(@class,'sc-bjfHbI')]"), 3));
        driver.findElement(By.xpath("//img[@id='image-option-2']")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(2000);

//        Klik na Add Image dugme
//        Sacekajte da se pojavi desni meni
//        Uploadujte back.jpg sliku
//        Sacekajte da je ispod uploada slike, broj slika 4.
//        Klik na sliku
//        Klik na Done dugme
//        Sacekajte 2s

        driver.findElement(By.className("edit-image")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//div[contains(@class,'sc-eGugkK')]")));
        driver.findElement(By.id("imageUpload"))
                .sendKeys(new File("downloads/41Far+NxmUL._AC_UL600_SR600,400_.jpg")
                        .getAbsolutePath());
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(
                "//div[contains(@class,'sc-bjfHbI')]"), 4));
        driver.findElement(By.xpath("//img[@id='image-option-3']")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(2000);

//        Sacekajte da Next dugme bude klikljivo
//        Klik na Next dugme
//        Unesite tekst
//        Klik na Next
//        Klik na Preview
//        Klik na Add to cart

        wait.until(ExpectedConditions.elementToBeClickable(By.id("next-button")));
        driver.findElement(By.id("next-button")).click();
        driver.findElement(By.id("textareaID")).sendKeys("hello");
        driver.findElement(By.id("text-editor-done")).click();
        driver.findElement(By.id("next-button")).click();
        driver.findElement(By.id("next-button")).click();
        driver.findElement(By.id("next-button")).click();

        Thread.sleep(5000);
        driver.quit();
    }
}
