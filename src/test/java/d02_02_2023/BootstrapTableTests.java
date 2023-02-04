package d02_02_2023;

import com.sun.org.glassfish.gmbal.Description;
import helper.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class BootstrapTableTests {

    private WebDriver driver;
    private WebDriverWait wait;
    private String baseUrl = "https://s.bootsnipp.com";

//    Podaci:
//    First Name: ime polaznika
//    Last Name: prezime polaznika
//    Middle Name: srednje ime polanzika

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get(baseUrl);
    }

    @Test
    @Description("Edit Row")
    public void editRow() throws InterruptedException {
//        Ucitati stranu /iframe/K5yrx
//        Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
//        Klik na Edit dugme prvog reda
//        Sacekati da dijalog za Editovanje bude vidljiv
        driver.get(baseUrl + "/iframe/K5yrx");
        Assert.assertEquals(driver.getTitle(),
                "Table with Edit and Update Data - Bootsnipp.com",
                "Not the right page title.");
        driver.findElement(By.xpath(
                        "//button[@data-uid='1'][@data-target='#edit']"))
                .click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("modal-content")));
        List<WebElement> inputs = driver.findElements(By.xpath(
                "//div[@class='modal-body']/input"));
        for (int i = 0; i < inputs.size(); i++) {
            inputs.get(i).clear();
        }
        inputs.get(0).sendKeys("Milan");
        Thread.sleep(1000);
        inputs.get(1).sendKeys("Jovanovic");
        Thread.sleep(1000);
        inputs.get(2).sendKeys("Zmaj");
        Thread.sleep(1000);

//        Klik na Update dugme
//        Sacekati da dijalog za Editovanje postane nevidljiv

        driver.findElement(By.id("up")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("modal-content")));

        //        Verifikovati da se u First Name celiji prvog reda tabele javlja uneto ime
        //        Verifikovati da se u Last Name celiji prvog reda tabele javlja uneto prezime
        //        Verifikovati da se u Middle Name celiji prvog reda tabele
        //        javlja uneto srednje ime

        Assert.assertEquals(driver.findElement(By.id("f1")).getText(),
                "Milan",
                "Not the right First Name displayed");
        Assert.assertEquals(driver.findElement(By.id("l1")).getText(),
                "Jovanovic",
                "Not the right Last Name displayed");
        Assert.assertEquals(driver.findElement(By.id("m1")).getText(),
                "Zmaj",
                "Not the right Middle Name displayed");

    }

    @Test
    @Description("Delete Row")
    public void deleteRow() {
//        Podaci:
//        First Name: ime polaznika
//        Last Name: prezime polaznika
//        Middle Name: srednje ime polanzika

//        Ucitati stranu /iframe/K5yrx
        driver.get(baseUrl + "/iframe/K5yrx");

//        Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
        Assert.assertEquals(driver.getTitle(),
                "Table with Edit and Update Data - Bootsnipp.com",
                "Not the right page title.");
        List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));
//        Klik na Delete dugme prvog reda
        driver.findElement(By.xpath(
                        "//button[@data-uid='1'][@data-target='#delete']"))
                .click();
//        Sacekati da dijalog za brisanje bude vidljiv
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//div[@id='delete']/div/div")));
//        Klik na Delete dugme iz dijaloga
        driver.findElement(By.id("del")).click();
//        Sacekati da dijalog za Editovanje postane nevidljiv
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(
                "//div[@id='delete']/div/div")));
//        Verifikovati da je broj redova u tabeli za jedan manji
//        Assert.assertEquals(rows.size(),
//                rows.size() - 1,
//                "Number of rows is wrong");
//        Za sve validacije ispisati odgovarajuce poruke u slucaju greske
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("d1")));

    }

    @Test
    @Description("Take a Screenshot")
    public void takeAScreenshot() throws IOException {
//        Ucitati stranu  /iframe/K5yrx
        driver.get(baseUrl + "/iframe/K5yrx");
//        Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
        Assert.assertEquals(driver.getTitle(),
                "Table with Edit and Update Data - Bootsnipp.com",
                "Not the right page title.");
//        Kreirati screenshot stranice.
//                Fajl cuvajte na putanji gde su vam bile slike od proslog domaceg.
//                Na putanji: screenshots/slike.png
        new Helper().takeScreenshot(driver, new File("screenshots/slika.png")
                .getAbsolutePath());
    }
}
