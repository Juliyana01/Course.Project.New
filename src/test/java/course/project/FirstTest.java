package course.project;
 import io.github.bonigarcia.wdm.WebDriverManager;
 import openqa.selenium.WebDriver;
 import org.openqa.selenium.By;
 import org.openqa.selenium.WebDriver;
 import org.openqa.selenium.WebElement;
 import org.openqa.selenium.chrome.ChromeDriver;
 import org.testing.anotation.BeforeTest;
 import org.testng.annotations.BeforeTest;
 import org.testng.annotations.Test;


public class FirstTest {
    private WebDriver driver;

    @BeforeTest
    public void setUp (){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

    }

    @Test
    public void login(){
        driver.get("https://www.saucedemo.com/");

        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.sendKeys("standard_user");

        WebElement passwordInput = driver.findElement(By.cssSelector("[placeholder=Password]"));
        passwordInput.sendKeys("secret_sauce");
    }

}
