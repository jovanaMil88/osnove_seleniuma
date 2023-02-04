package d31_01_2023;

import helper.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class zadatak3 {
    public static void main(String[] args) throws InterruptedException {
//        Ucitava stranicu https://itbootcamp.rs/
//        Skrola do slajdera na dnu stranice (u kome su slike Java, Php, Selenium, …)
//        Cita sve linkove slika iz slajdera
//        Proverava url svake slike, i za sve slike koje imaju status veci i jednak od 200 a manji od 300,
//            skida i smesta u folder itbootcamp_slider u okviru projekta
//        Azurirajte gitignore da ignorise itbootcamp_slider folder

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://itbootcamp.rs/");
        driver.manage().window().maximize();
        new Actions(driver).scrollToElement(driver.findElement(By.className(
                "vc_column-inner"))).perform();
        List<WebElement> links = driver.findElements(By.xpath(
                "//div[@class='vc_column-inner']//a"));
        for (int i = 0; i < links.size(); i++) {
            String urlString = links.get(i).getAttribute("href");
            try {
                int status = new Helper().getHTTPResponseStatusCode(urlString);
                if (status >= 200 && status < 300){
                    new Helper().downloadUsingStream(urlString,
                            new File("itbootcamp_slider/slika" + i + ".jpg").getAbsolutePath());
                }else {
                    System.out.println("Link: " + urlString + " is not available");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }

        Thread.sleep(5000);

        driver.quit();
}}
