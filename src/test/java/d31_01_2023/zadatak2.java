package d31_01_2023;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class zadatak2 {
    public static void main(String[] args) throws InterruptedException {
//        Ucitava stranu https://itbootcamp.rs/
//        Misem prelazi preko Vesti iz navigacionog menija
//        Ceka da se prikaze padajuci meni za Vesti
//        Misem prelazi preko Kursevi iz navigacionog menija
//        Ceka da se prikaze padajuci meni za Kursevi

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://itbootcamp.rs/");
        driver.manage().window().maximize();
        new Actions(driver).moveToElement(driver.findElement(By.xpath(
                "//*[text()='Vesti ']"))).perform();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//li[@id='menu-item-6408']/ul[@role='menu'][@class=' dropdown-menu']")));
        new Actions(driver).moveToElement(driver.findElement(By.xpath(
                "//*[text()='Kursevi ']"))).perform();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//li[@id='menu-item-5362']/ul")));

        //        Misem prelazi preko Prijava i pravilnik iz navigacionog menija
        //        Ceka da se prikaze padajuci meni za Prijava i pravilnik

        new Actions(driver).moveToElement(driver.findElement(By.xpath(
                "//*[text()='Prijava i pravilnik ']"))).perform();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//li[@id='menu-item-5453']/ul")));

        Thread.sleep(5000);

        driver.quit();
}}
