package course.project;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;


public class FirstTest {
    protected WebDriver driver;

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

        WebElement passwordInput = driver.findElement(By.cssSelector("[placeholder=Password]"));
        passwordInput.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.name("login button"));
        loginButton.click();

        WebElement productsMainLabel = driver.findElement(By.xpath("//span[text()='Product']"));
        WebElement shoppingCartLink = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));

        Assert.assertTrue(productsMainLabel.isDisplayed());
        Assert.assertTrue(shoppingCartLink.isDisplayed());

    }
    @DataProvider (name = "users")
    public Object[][] getUsers(){
        return new Object[][]{
                {"standard_user1", "wrongPass"},
                {"wrong_User", "secret_sauce"},
                {"wrong", "wrong"},
        };
    }
    @Test(dataProvider = "users")
    public void unsuccessfulLogin(String userName, String password){
        driver.get("https://www.saucedemo.com/");

        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.sendKeys(userName);

        WebElement passwordInput = driver.findElement(By.cssSelector("[placeholder=Password]"));
        passwordInput.sendKeys(password);

        WebElement loginButton = driver.findElement(By.name("login button"));
        loginButton.click();

        WebElement wrongUserButton = driver.findElement(By.cssSelector(".error-button"));

        Assert.assertTrue(wrongUserButton.isDisplayed());
    }

}
