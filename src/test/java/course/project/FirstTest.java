package course.project;
 import io.github.bonigarcia.wdm.WebDriverManager;
 import openqa.selenium.WebDriver;
 import org.openqa.selenium.By;
 import org.openqa.selenium.WebDriver;
 import org.openqa.selenium.WebElement;
 import org.openqa.selenium.chrome.ChromeDriver;
 import org.testing.anotation.BeforeTest;
 import org.testng.Assert;
 import org.testng.annotations.AfterTest;
 import org.testng.annotations.BeforeTest;
 import org.testng.annotations.Test;


public class FirstTest {
    private WebDriver driver;

    @BeforeTest
    public void setUp (){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

    }
    @AfterTest
    public void tearDown(){
        driver.quit();

    }

    @Test
    public void login(){
        driver.get("https://www.saucedemo.com/");

        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.sendKeys("standard_user");
        Thread.sleep(2000);

        WebElement passwordInput = driver.findElement(By.cssSelector("[placeholder=Password]"));
        passwordInput.sendKeys("secret_sauce");
        Thread.sleep(2000);

        WebElement loginButton = driver.findElement(By.name("login button"));
        loginButton.click();
        Thread.sleep(2000);

        WebElement productsMainLabel = driver.findElement(By.ByXPath("//span[text()='Product']"));
        WebElement shoppingCartLink = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));

        Assert.assertTrue(productsMainLabel.isDisplayed());
        Assert.assertTrue(shoppingCartLink.isDisplayed());

    }

    @Test
    public void loginWithWrongUser(){
        driver.get("https://www.saucedemo.com/");

        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.sendKeys("standard_user1");

        WebElement passwordInput = driver.findElement(By.cssSelector("[placeholder=Password]"));
        passwordInput.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.name("login button"));
        loginButton.click();

        WebElement wrongUserButton = driver.findElement(By.cssSelector(".error-button"));

        Assert.assertTrue(wrongUserButton.isDisplayed());
    }

}
