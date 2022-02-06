package course.project.base;

import org.course.project.base.TestUtil;
import course.project.base.By;
import course.project.base.JavascriptExecutor;
import course.project.base.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class JavascriptExecutorTest {
    @Test
    public void logInWithJavascriptAction() {

        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.sendKeys("standard_user");

        WebElement passwordInput = driver.findElement(By.cssSelector("[placeholder=Password]"));
        passwordInput.sendKeys("secret_sauce");

        WebElement loginBtn = driver.findElement(By.name("login-button"));
        loginBtn.click();

        WebElement productsMainLabel = driver.findElement(By.xpath("//span[text()='Products']"));
        WebElement shoppingCartLink = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));

        Assert.assertTrue(productsMainLabel.isDisplayed());
        Assert.assertTrue(shoppingCartLink.isDisplayed());

        JavascriptExecutor script = (JavascriptExecutor) driver;
        script.executeScript("arguments[0].scrollIntoView",
                driver.findElement(By.xpath("//a[@href='https://www.linkedin.com/company/sauce-labs/']")));
    }
}

}
