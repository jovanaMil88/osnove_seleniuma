package d27_01_2023;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class zadatak {
//    Ucitati stranicu https://mdbootstrap.com/docs/standard/components/toasts/#section-basic-example
//    Klik na svako dugme od PRIMARY do DARK
//    Sacekati da se toasts u desnom gornjem uglu pojavi
//    Pauza izmedju klikova 1s
//    Postavite implicitno cekanje za ucitavanje stranice i trazenje elemenata na 10s

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.get("https://mdbootstrap.com/docs/standard/components/toasts/#section-basic-example");
        driver.manage().window().maximize();
        List<WebElement> btns = driver.findElements(By.xpath(
                "//div[@class='container text-center'][not(@id='stacking-container')]/button"));
        List<WebElement> divs = driver.findElements(By.xpath(
                "//div[contains(@id,'example')][contains(@class, 'toast-fixed')]"));
        for (int i = 0; i < 8; i++) {
            btns.get(i).click();
            Thread.sleep(1000);
            divs.get(i).isDisplayed();

        }
        Thread.sleep(5000);

        driver.quit();
    }
}
