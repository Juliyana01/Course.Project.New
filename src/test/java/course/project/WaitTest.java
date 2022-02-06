package course.project;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
import java.util.concurrent.TimeUnit;

public class WaitTest extends course.project.base.TestUtil{

    @Test
    public void unsuccessfulLogin (){
        driver.get("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(TimeUnit.SECONDS.toSeconds(5), TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, TimeUnit.SECONDS.toSeconds(5));

        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.sendKeys("standard_user");

        WebElement passwordInput = driver.findElement(By.cssSelector("[placeholder=Password]"));
        passwordInput.sendKeys("secret_sauce");

        WebElement loginBtn = driver.findElement(By.name("login-button"));

        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("login-button"))));
        driver.manage().timeouts().implicitlyWait(TimeUnit.SECONDS.toSeconds(5), TimeUnit.SECONDS);
        loginBtn.click();

        WebElement wrongUserBtn = driver.findElement(By.cssSelector(".error-button"));

        Assert.assertTrue(wrongUserBtn.isDisplayed());

        }

    @Test
    public void unsuccessfulLoginFluentWait(){
        driver.get("https://www.saucedemo.com/");

        driver.manage().timeouts().implicitlyWait(TimeUnit.SECONDS.toSeconds(5), TimeUnit.SECONDS);

        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoreAll(Collections.singleton(NoSuchElementException.class));

        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.sendKeys("standard_user");

        WebElement passwordInput = driver.findElement(By.cssSelector("[placeholder=Password]"));
        passwordInput.sendKeys("secret_sauce");

        WebElement loginBtn = driver.findElement(By.name("login-button"));

        fluentWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("login-button"))));
        driver.manage().timeouts().implicitlyWait(TimeUnit.SECONDS.toSeconds(5), TimeUnit.SECONDS);
        loginBtn.click();

        WebElement wrongUserBtn = driver.findElement(By.cssSelector(".error-button"));

        Assert.assertTrue(wrongUserBtn.isDisplayed());

    }
}

