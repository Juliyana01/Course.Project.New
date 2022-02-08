package course.project;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class LoginTests {
    protected WebDriver driver;

//}
    @DataProvider(name = "usersCsv")
    public static Object[][] readUsersFromCsv() throws IOException, CsvException {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/userList.csv"))) {
            List<String[]> csvData = csvReader.readAll();
            Object[][] csvDataObject = new Object[csvData.size()][2];
            for (int i = 0; i < csvData.size(); i++) {
                csvDataObject[i] = csvData.get(i);
            }
            return csvDataObject;
        }
    }

        @Test(dataProvider = "usersCsv")
        public void unsuccessfulLogin (String userName, String password){
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

