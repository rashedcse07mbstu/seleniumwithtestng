package seleniumwithtestng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginOrangeHrm extends BaseClass {
    WebElement userNameEle;
    WebElement passEle;
    WebElement loginBtnEle;
    public String expectedTitle = "OrangeHRM";

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
        userNameEle = driver.findElement(By.name("username"));
        userNameEle.isDisplayed();

        //Clear User Name Input field
        userNameEle.clear();

        //Enter User Name
        fillUpInputFieldByValue(userNameEle, userName);

        //Clear Password Input field
        passEle = driver.findElement(By.name("password"));
        passEle.clear();

        //Enter password
        Thread.sleep(2000);
        fillUpInputFieldByValue(passEle, password);

        //Click on login button
        Thread.sleep(2000);

        loginBtnEle = driver.findElement(By.xpath("//button[@type='submit']"));
        //Click on Login Button
        doClick(loginBtnEle);

        //Close Browser Session
        tearDown();
    }

    @Test(priority = 0, enabled = true, description = "To validate Home Page Title after login")
    public void validateLoginPageTitle() throws InterruptedException {
        //Browser Set up
        setUp();

        //validate presence of User name input field
        userNameEle = driver.findElement(By.name("username"));
        userNameEle.isDisplayed();

        //Clear User Name Input field
        userNameEle.clear();

        //Enter User Name
        fillUpInputFieldByValue(userNameEle, userName);

        //Clear Password Input field
        passEle = driver.findElement(By.name("password"));
        passEle.clear();

        //validate presence of password input field
        passEle.isDisplayed();

        //Enter password
        Thread.sleep(2000);
        fillUpInputFieldByValue(passEle, password);

        //Click on login button
        Thread.sleep(2000);

        loginBtnEle = driver.findElement(By.xpath("//button[@type='submit']"));
        //Click on Login Button
        doClick(loginBtnEle);

        //Assertion Login Page Title
        Assert.assertEquals(getPageTitle(), expectedTitle);

        /*String expectedTitle = "OrangeHRM5";

        if (getPageTitle().equals(expectedTitle)) {
            System.out.println("Test Case passed");
        }

        if (getPageTitle().contains("OrangeHRM")) {
            System.out.println("Test Case passed");
        }*/

        // quit browser
        tearDown();
    }

    @Override
    public String getPageTitle() {
        return driver.getTitle();
    }
}
