package seleniumwithtestng;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginOrangeHrm {
    /*
    1. Open a browser
    2. Open URL (https://opensource-demo.orangehrmlive.com/)
    3. Enter User Name
    4. Enter password
    5. Click on login button
    6. Check the page title
   */

    @Test(priority = 1)

    public void doLoginWithValidUserPassword() throws InterruptedException {
        //Open Browser
        WebDriverManager.firefoxdriver().clearDriverCache().setup();
        WebDriver driver = new FirefoxDriver();

        // Open Application URL
        driver.get("https://opensource-demo.orangehrmlive.com");

        //implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //validate presence of User name input field

        driver.findElement(By.name("username")).isDisplayed();

        //Clear User Name Input field
        driver.findElement(By.name("username")).clear();

        //Enter User Name
        driver.findElement(By.name("username")).sendKeys("Admin");

        //validate presence of User name input field
        boolean elementPresence = driver.findElement(By.name("username")).isDisplayed();
        System.out.println(elementPresence);

        //Clear Password Input field
        driver.findElement(By.name("password")).clear();

        //Enter password
        Thread.sleep(2000);
        driver.findElement(By.name("password")).sendKeys("admin123");

        //Click on login button
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // quit browser
        driver.quit();
    }

    @Test(priority = 0)

    public void validateLoginPageTitle() throws InterruptedException {
        //Open Browser
        WebDriverManager.firefoxdriver().clearDriverCache().setup();
        WebDriver driver = new FirefoxDriver();

        // Open Application URL
        driver.get("https://opensource-demo.orangehrmlive.com");

        //implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //validate presence of User name input field

        driver.findElement(By.name("username")).isDisplayed();

        //Clear User Name Input field
        driver.findElement(By.name("username")).clear();

        //Enter User Name
        driver.findElement(By.name("username")).sendKeys("Admin");

        //validate presence of User name input field
        boolean elementPresence = driver.findElement(By.name("username")).isDisplayed();
        System.out.println(elementPresence);

        //Clear Password Input field
        driver.findElement(By.name("password")).clear();

        //Enter password
        Thread.sleep(2000);
        driver.findElement(By.name("password")).sendKeys("admin123");

        //Click on login button
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        //Assertion
        String actualTitle = driver.getTitle();
        System.out.println("Page title is: " + actualTitle);

        String expectedTitle = "OrangeHRM5";

        if (actualTitle.equals(expectedTitle)) {
            System.out.println("Test Case passed");
        }

        if (actualTitle.contains("OrangeHRM")) {
            System.out.println("Test Case passed");
        }

        // quit browser
        driver.quit();
    }

}
