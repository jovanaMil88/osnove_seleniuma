package d30_01_2023;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class zadatak3 {
    public static void main(String[] args) throws InterruptedException {
//        Implicitno cekanje za trazenje elemenata je maksimalno 10s
//        Implicitno cekanje za ucitavanje stranice je 5s
//        Ucitava stranicu https://docs.katalon.com/
//        Maksimizuje prozor
//        Od html elementa cita data-theme atribut.
//                Proverava da li je sadrzaj u tom atributu light i ispisuje odgovarajuce poruke
//        Klikce na dugme za zamenu tema
//        Ponovo cita data-theme atribut html elementa i validira da u atributu stoji vrednost dark
//        Izvrsava kombinaciju tastera CTRL + K.
//        Ceka da se dijalog za pretragu pojavi
//        Zatim od inputa za pretragu cita atribut type i proverava da je vrednost tog atributa search
//        Zatvara pretrazivac


        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
driver.get("https://docs.katalon.com/");
        driver.manage().window().maximize();
        Thread.sleep(5000);
        String dt = driver.findElement(By.tagName("html")).getAttribute("data-theme");
        if (dt.equals("light")){
            System.out.println("Light");
        }else {
            System.out.println("nije.");
        }
driver.findElement(By.xpath(
        "//div[@class='toggle_S7eR colorModeToggle_vKtC']/button[@aria-label='Switch between dark and light mode (currently light mode)']"))
                .click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.attributeContains(By.tagName
                ("html"),"data-theme", "dark"));
        System.out.println("dark");
        new Actions(driver).keyDown(Keys.LEFT_CONTROL).sendKeys("k").perform();
        Thread.sleep(3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.className("DocSearch-Modal")));

       String typeAtr =  driver.findElement(By.xpath(
               "//input[@class='DocSearch-Input']")).getAttribute("type");
       if (typeAtr.equals("search")){
           System.out.println("jeste.");
       }else {
           System.out.println("nije");
       }
        driver.quit();
}}
