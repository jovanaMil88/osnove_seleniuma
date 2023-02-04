package d03_02_2023;

import com.sun.org.glassfish.gmbal.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class KatalonShopTests {
    private WebDriver driver;
    private WebDriverWait wait;
    private String baseUrl = "https://cms.demo.katalon.com";

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

    @Test(priority = 10)
    @Description("Adding product with quantity to the cart")
    public void addingProductWithQuantityToTheCart() {
        driver.get(baseUrl + "/product/flying-ninja/");
        driver.findElement(By.name("quantity")).sendKeys("3");
//        Klik na Add to cart dugme
//        Verifikovati da poruka sadrzi tekst “Flying Ninja”.

        driver.findElement(By.name("add-to-cart")).click();
        Assert.assertTrue(driver.findElement(
                        By.className("woocommerce-message")).getText().contains("Flying Ninja"),
                "Not the chosen item message displayed.");

//        Klik na Cart link iz navigacije
//        Verifikovati da u url-u stoji /cart ruta
//        Verifikovati da je broj proizvoda u korpi jednako 1

        driver.findElement(By.linkText("CART")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("/cart"),
                "Not on Cart page.");

//        Verifikovati da je broj proizvoda u korpi jednako 1

        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("cart_item"), 1));

    }

    @Test(priority = 20)
    @Description("Removing product from cart")
    public void removingProductFromCart() {
//        Klik na Cart link iz navigacije
//        Verifikovati da u url-u stoji /cart ruta
        driver.findElement(By.linkText("CART")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("/cart"),
                "Not on cart page.");

//        Verifikovati da je broj proizvoda u korpi jednako 1

        wait.until(ExpectedConditions.numberOfElementsToBe(
                By.className("cart_item"), 1));

        //        Klik na remove dugme iz prvog reda
        driver.findElement(By.className("remove")).click();

        //        Verifikovati da je broj proizvoda u korpi jedako 0
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("cart_item"), 0));

    }

    @Test(priority = 30)
    @Description("Verify error is displayed when username is missing")
    public void verifyErrorIsDisplayedWhenUsernameIsMissing() {
//        Kliknite na my account link
//        Klik na login dugme
//        Verifikovati da je prikazana poruka Error: Username is required.
        driver.findElement(By.linkText("MY ACCOUNT")).click();
        driver.findElement(By.name("login")).click();
        Assert.assertEquals(driver.findElement(By.xpath(
                        "//ul[@class='woocommerce-error']/li")).getText(),
                "Error: Username is required.",
                "Error message is not displayed");
    }

    @Test(priority = 40)
    @Description("Verify error is displayed when password is missing")
    public void verifyErrorDisplayedWhenPasswordIsMissing() {
//        Kliknite na my account link
//        Unesite username customer
//        Klik na login dugme
//        Verifikovati da je prikazana poruka ERROR: The password field is empty.

        driver.findElement(By.linkText("MY ACCOUNT")).click();
        driver.findElement(By.id("username")).sendKeys("customer");
        driver.findElement(By.name("login")).click();
        Assert.assertEquals(driver.findElement(By.xpath(
                        "//ul[@class='woocommerce-error']/li")).getText(),
                "ERROR: The password field is empty.",
                "The error message is not displayed");
    }

    @Test(priority = 50)
    @Description("Verify error is displayed when password is wrong")
    public void verifyErrorIsDisplayedWhenPasswordIsWrong() {
//        Kliknite na my account link
//        Unesite username customer
//        Unesite nevalidan pass invalidpassword
//        Klik na login dugme
//        Verifikovati da je prikazana poruka
// ERROR: The password you entered for the username customer is incorrect. Lost your password?
        driver.findElement(By.linkText("MY ACCOUNT")).click();
        driver.findElement(By.id("username")).sendKeys("customer");
        driver.findElement(By.id("password")).sendKeys("invalidpassword");
        driver.findElement(By.name("login")).click();
        Assert.assertEquals(driver.findElement(By.xpath(
                        "//ul[@class='woocommerce-error']/li")).getText(),
                "ERROR: The password you entered for the username customer is incorrect. Lost your password?",
                "Error message for the wrong password is not displayed.");
    }

    @Test(priority = 60)
    @Description("Verify error is displayed when user does not exist")
    public void verifyErrorDisplayedWhenUserDoesNotExist() {
//        Kliknite na my account link
//        Unesite username invaliduser
//        Unesite nevalidan pass (ex: pass1234)
//        Klik na login dugme
//        Verifikovati da je prikazana poruka ERROR: Invalid username. Lost your password?
        driver.findElement(By.linkText("MY ACCOUNT")).click();
        driver.findElement(By.id("username")).sendKeys("invaliduser");
        driver.findElement(By.id("password")).sendKeys("pass1234");
        driver.findElement(By.name("login")).click();
        Assert.assertEquals(driver.findElement(By.xpath(
                        "//ul[@class='woocommerce-error']/li")).getText(),
                "ERROR: Invalid username. Lost your password?",
                "Error message for the wrong username is not displayed.");

    }

    @Test(priority = 70)
    @Description("Verify successful login")
    public void verifySuccessfulLogin() {
//        Kliknite na my account link
//        Unesite username customer
//        Unesite validan pass crz7mrb.KNG3yxv1fbn
//        Klik na login dugme
//        Verifikovati na stranici pise Hello Katalon Parlitul_Changed
        driver.findElement(By.linkText("MY ACCOUNT")).click();
        driver.findElement(By.id("username")).sendKeys("customer");
        driver.findElement(By.id("password")).sendKeys("crz7mrb.KNG3yxv1fbn");
        driver.findElement(By.name("login")).click();
        Assert.assertTrue(driver.findElement(By.xpath(
                "//div[@class='woocommerce-MyAccount-content']/p[1]"))
                        .getText().contains("Hello Katalon Parlitul_Changed"),
                "Hello Katalon Parlitul_Changed is not displayed.");

    }

    @AfterClass
    public void quit() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();

    }


}

