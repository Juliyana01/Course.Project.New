package course.project;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class WaitTest {
    protected WebDriver driver;
    protected WebElement wrongUserButton;

    @BeforeTest
    public void setUp (){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }
    @AfterTest
    public void tearDown(){
        driver.quit();

    }
    @Test
    public void unsuccessfulLogin (){
        driver.get("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.sendKeys("standard_user");

        WebElement passwordInput = driver.findElement(By.cssSelector("[placeholder=Password]"));
        passwordInput.sendKeys("secret_sauce");

        WebElement loginBtn = driver.findElement(By.name("login-button"));

        wait.until(ExpectedCondition.elementToBeClikable(driver.findElement(By.name("login-button"))));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        loginBtn.click();

        WebElement wrongUserBtn = driver.findElement(By.cssSelector(".error-button"));

        Assert.assertTrue(wrongUserButton.isDisplayed());

        }

    @Test
    public void unsuccessfulLoginFluentWait(){
        driver.get("https://www.saucedemo.com/");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoreAll(Collections.singleton(NoSuchElementException.class));

        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.sendKeys("standard_user");

        WebElement passwordInput = driver.findElement(By.cssSelector("[placeholder=Password]"));
        passwordInput.sendKeys("secret_sauce");

        WebElement loginBtn = driver.findElement(By.name("login-button"));

        FluentWait.until(ExpectedCondition.elementToBeClikable(driver.findElement(By.name("login-button"))));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        loginBtn.click();

        WebElement wrongUserBtn = driver.findElement(By.cssSelector(".error-button"));

        Assert.assertTrue(wrongUserButton.isDisplayed());

    }
}

