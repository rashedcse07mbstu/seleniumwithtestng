package seleniumwithtestng;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CPortAuthority {
    public static WebDriver driver; //Instance Variable

    @Test
    public void fillOnlineCompleteProcess() throws InterruptedException {
        //Open Browser
        WebDriverManager.firefoxdriver().clearDriverCache().setup();
        driver = new FirefoxDriver();

        // Open Application URL
        driver.get("http://123.200.20.20:5302/");
        driver.manage().window().maximize();

        //implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        List<WebElement> elementList = driver.findElements(By.cssSelector(" table#circularMst tbody tr"));
        System.out.println(elementList.size());

        if (elementList.size() > 0) {
            elementList.get(0).findElement(By.linkText("DETAILS")).click();
        }

        //Get the main window handle

        //Get handles of the windows
        String mainWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        Iterator<String> iterator = allWindowHandles.iterator();

        // Here we will check if child window has other child windows and will fetch the heading of the child window
        while (iterator.hasNext()) {
            String ChildWindow = iterator.next();
            if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
                driver.switchTo().window(ChildWindow);
                //Click on Action link on Post Action Page
                driver.findElement(By.cssSelector("table#circularDtl tbody a i.bx-show")).click();
            }
        }

        Thread.sleep(3);
        driver.findElement(By.cssSelector("a.btn-success strong")).click();

        Thread.sleep(3);
        driver.findElement(By.cssSelector(" a.btn-secondary")).click();

        //Input National ID
        Thread.sleep(3);
        driver.findElement(By.cssSelector("input#national_id")).sendKeys("8231771135");

        //Upload NID File
//        File uploadFile = new File("usr." + "src/test/resources/NID_Rashedul.jpg");
        File uploadFile = new File("C:\\seleniumwithtestng\\src\\test\\resources\\NID_Rashedul.jpg");
        WebElement fileInput = driver.findElement(By.cssSelector("input#national_id_attachment"));
        fileInput.sendKeys(uploadFile.getAbsolutePath());

//        System.out.println("File text name is: " + fileInput.getText());
//        Assert.assertEquals("NID_Rashedul.jpg", fileInput.getText());

        //select date of birth
        driver.findElement(By.cssSelector("input#date_of_birth")).click();
        driver.findElement(By.cssSelector("div.top")).isDisplayed();
//        driver.findElement(By.cssSelector("td[data-action='selectDay'].active")).click();
        driver.findElement(By.cssSelector("th.picker-switch[title='Select Month']")).click();
        driver.findElement(By.cssSelector("th.picker-switch[title='Select Year']")).click();
//        driver.findElement(By.cssSelector("span.bx-chevron-left[title='Previous Century']")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("span.bx-chevron-left[title='Previous Decade']")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("span.bx-chevron-left[title='Previous Decade']")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("span.bx-chevron-left[title='Previous Decade']")).click();
        Thread.sleep(2000);

//        driver.findElement(By.cssSelector("input#date_of_birth")).sendKeys("10-03-1985");
        driver.findElements(By.cssSelector("span[data-action='selectYear'].year")).stream()
                .filter(element -> element.getText().trim().contains("1995"))
                .findFirst().get().click();


        Thread.sleep(2000);
        driver.findElements(By.cssSelector("span[data-action='selectMonth'].month")).stream()
                .filter(element -> element.getText().trim().contains("Aug"))
                .findFirst().get().click();

        Thread.sleep(2000);
        driver.findElements(By.cssSelector("td[data-action='selectDay'][class='day']")).stream()
                .filter(element -> element.getText().trim().contains("23"))
                .findFirst().get().click();
        Thread.sleep(2000);

        /*String monthYearVal = driver.findElement(By.cssSelector("input#date_of_birth")).getText();
        System.out.println(monthYearVal);*/

    }

}

