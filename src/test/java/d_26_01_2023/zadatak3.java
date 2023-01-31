package d_26_01_2023;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class zadatak3 {
//    Ucitava stranicu https://s.bootsnipp.com/iframe/Dq2X
//    Klikce na svaki iks da ugasi obavestenje i proverava
//    da li se nakon klika element obrisao sa stranice i ispisuje odgovarajuce poruke
//            (OVO JE POTREBNO RESITI PETLJOM)
//    POMOC: Brisite elemente odozdo.
//            (ZA VEZBANJE)Probajte da resite da se elemementi brisu i odozgo

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://s.bootsnipp.com/iframe/Dq2X");
        driver.manage().window().maximize();
        Thread.sleep(1000);
        WebElement btn;
        List<WebElement> btns = driver.findElements(By.xpath(
                "//div[@class='col-md-12']//button"));
        for (int i = 0; i < 5; i++) {
            int befDel = btns.size();
            btn = driver.findElement(By.xpath(
                    "//div[@class='col-md-12']/div[last()]/button"));
            btn.click();
            int aftDel = btns.size() - 1;
            Thread.sleep(1000);
            btns = driver.findElements(By.xpath(
                    "//div[@class='col-md-12']//button"));
            if (befDel == aftDel + 1){
                System.out.println("Obrisan.");
            }else {
                System.out.println("nije obrisan.");
            }
            }



        Thread.sleep(5000);

        driver.quit();
    }
}
