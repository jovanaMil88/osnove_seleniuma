package d_26_01_2023;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class zadatak4 {
//    Napisati program koji ucitava stranicu https://geodata.solutions/
//    Bira Country, State i City po vasoj zelji
//    Pritom potrebno je izvrsiti cekanje da se povaje State-ovi nakon izbora Country-a
//    I takodje je potrebno izvrsiti cekanje da se ucitaju gradovi nakon izbora State-a
//    Izabrerit Country, State i City tako da imate podatke da selektujete!

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://geodata.solutions/");
        driver.manage().window().maximize();
        WebElement select = driver.findElement(By.name("country"));
        Select selectC = new Select(select);
        selectC.selectByVisibleText("Albania");
        Thread.sleep(2000);
        WebElement select1 = driver.findElement(By.name("state"));
        Select selectSt = new Select(select1);
        selectSt.selectByVisibleText("Korce");
        Thread.sleep(2000);
        WebElement select2 = driver.findElement(By.name("city"));
        Select selectCity = new Select(select2);
        selectCity.selectByVisibleText("Kor");
        Thread.sleep(3000);
        driver.quit();
}}
