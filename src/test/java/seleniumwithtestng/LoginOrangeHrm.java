package seleniumwithtestng;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LoginOrangeHrm extends BaseClass {
    /*
    1. Open a browser
    2. Open URL (https://opensource-demo.orangehrmlive.com/)
    3. Enter User Name
    4. Enter password
    5. Click on login button
    6. Check the page title
    */

    @Test(priority = 1, enabled = false, description = "To login application with valid User Name and password")
    public void doLoginWithValidUserPassword() throws InterruptedException {
        //Browser Set up
        setUp();

        //validate presence of User name input field
        driver.findElement(By.name("username")).isDisplayed();

        //Clear User Name Input field
        driver.findElement(By.name("username")).clear();

        //Enter User Name
        driver.findElement(By.name("username")).sendKeys(userName);

        //validate presence of User name input field
        boolean elementPresence = driver.findElement(By.name("username")).isDisplayed();
        System.out.println(elementPresence);

        //Clear Password Input field
        driver.findElement(By.name("password")).clear();

        //Enter password
        Thread.sleep(2000);
        driver.findElement(By.name("password")).sendKeys(password);

        //Click on login button
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        //Close Browser Session
        tearDown();
    }

    @Test(priority = 0, enabled = true, description = "To validate Home Page Title after login")
    public void validateLoginPageTitle() throws InterruptedException {
        //Call this method to setup browser
        setUp();

        //validate presence of User name input field
        driver.findElement(By.name("username")).isDisplayed();

        //Clear User Name Input field
        driver.findElement(By.name("username")).clear();

        //Enter User Name
        driver.findElement(By.name("username")).sendKeys(userName);

        //validate presence of User name input field
        boolean elementPresence = driver.findElement(By.name("username")).isDisplayed();
        System.out.println(elementPresence);

        //Clear Password Input field
        driver.findElement(By.name("password")).clear();

        //Enter password
        Thread.sleep(2000);
        driver.findElement(By.name("password")).sendKeys(password);

        //Click on login button
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        String expectedTitle = "OrangeHRM5";

        if (getPageTitle().equals(expectedTitle)) {
            System.out.println("Test Case passed");
        }

        if (getPageTitle().contains("OrangeHRM")) {
            System.out.println("Test Case passed");
        }

        // quit browser
        tearDown();
    }

    @Override
    public String getPageTitle() {
        return driver.getTitle();
    }
}
