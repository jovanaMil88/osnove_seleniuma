package d_26_01_2023;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class zadatak1 {
    public static void main(String[] args) throws InterruptedException {
//        Niz todo-a (niz stringova) koje treba da uneti. Niz je:
//        Visit Paris
//        Visit Prague
//        Visit London
//        Visit New York
//        Visit Belgrade
//        Maksimizirati prozor
//        Ucitati stranicu https://example.cypress.io/todo
//        Program petljom prolazi kroz niz todo-a i svaki unosi na stranicu
//        Nakon svakog unosa todo-a, unosi se enter
//        Validira da li je novi todo dodat na stranici
//        Na kraju programa proci petljom i izbrisati svaki todo sa stranice
//                (klikom na x dugme svakog todo-a)
//        Validirati da je na kraju programa broj todo-a na stranici 0.
//        Cekanje od 5s
//        Zatvorite pretrazivac

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        ArrayList<String> todos = new ArrayList<>();
        todos.add("Visit Paris");
        todos.add("Visit Prague");
        todos.add("Visit London");
        todos.add("Visit New York");
        todos.add("Visit Belgrade");

        driver.manage().window().maximize();

        driver.get("https://example.cypress.io/todo");
        for (int i = 0; i < todos.size(); i++) {
            driver.findElement(By.className("new-todo")).sendKeys(todos.get(i));
            Thread.sleep(1000);
            driver.findElement(By.className("new-todo")).sendKeys(Keys.ENTER);
        }
        for (int i = 1; i < todos.size() + 1; i++) {
            WebElement todo = driver.findElement(By.xpath(
                    "//ul[@class='todo-list']/li[" + i + "]/div"));
        }
        Actions actions = new Actions(driver);
        WebElement target;
        for (int i = 1; i < todos.size() + 3; i++) {
            target = driver.
                    findElement(By.xpath(
                            "(//ul[@class='todo-list']/li/div/label)[1]"));
            actions.moveToElement(target).perform();
            Thread.sleep(1000);
            driver.findElement(By.xpath(
                            "(//ul[@class='todo-list']/li/div/button)[1]"))
                    .click();
            Thread.sleep(1000);
        }
        List<WebElement> btns = driver.findElements(By.xpath(
                "//button[contains(@class,'destroy')]"));
        if (btns.size() == 0) {
            System.out.println("Broj todo-a na stranici je 0.");
        }else {
            System.out.println("Broj todo-a na stranici nije 0.");
        }


        Thread.sleep(5000);

        driver.quit();
    }
}
