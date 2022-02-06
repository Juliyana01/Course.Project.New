package course.project;

import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestUtil {
    //Selenium
    public WebDriver driver;
    private String applicationUrl, browser;
    private int implicitWaitSeconds;

    @BeforeMethod //This cames from TestNG
    public void setUp(){
        setupBrowserDriver();
        loadInitialPage();
    }

    private void loadInitialPage() {
        driver.get(applicationUrl);
    }

    @AfterMethod //This cames from TestNG
    public void tearDown(){
        //driver.close(); closes only the current drive not closing the session
        driver.quit(); //closing and killing the session
        //driver = null;
    }

    private void setupBrowserDriver(){
        try{
            FileInputStream configFile = new
                    FileInputStream("src/test/resources/config.properties");
            Properties config = new Properties();
            config.load(configFile);
            applicationUrl = config.getProperty("url");
            //implicitWaitSeconds = Integer.parseInt(config.getProperty("implicitWait"));
            browser = config.getProperty("targetBrowser");
            System.out.println("webhook test");
        }catch (IOException e){
            System.out.println(e);
        }

        switch (browser){
            case "chrome":
                driver = DriverFactory.getChromeDriver();
                break;
            case "firefox":
                driver = DriverFactory.getFireFoxDriver();
                break;
        }
    }
}