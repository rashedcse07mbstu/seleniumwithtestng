package seleniumwithtestng;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public abstract class BaseClass {
    public static WebDriver driver; //Instance Variable
    protected String applicationUrl = "https://opensource-demo.orangehrmlive.com";
    protected String userName = "Admin";
    protected String password = "admin123";

    public abstract String getPageTitle();

    //To setup browser info before executing test method
    @BeforeMethod
    public void setUp() {
        //Open Browser
        WebDriverManager.firefoxdriver().clearDriverCache().setup();
        driver = new FirefoxDriver();

        // Open Application URL
        driver.get(applicationUrl);

        //implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
