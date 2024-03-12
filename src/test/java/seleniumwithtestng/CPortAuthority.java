package seleniumwithtestng;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.thedeanda.lorem.LoremIpsum;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CPortAuthority {
    public static WebDriver driver; //Instance Variable
    String fatherName = LoremIpsum.getInstance().getTitle(2);
    String motherName = LoremIpsum.getInstance().getTitle(2);

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    @BeforeTest
    public void setExtent() {
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\build\\extendReport\\ExtendReport.html");
        sparkReporter.config().setDocumentTitle("Chittagong Port Authority Project's Automation Report");
        sparkReporter.config().setReportName("Chittagong Port Authority Project's Automation Report");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();

        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Host IP", "http://123.200.20.20:5302/");
        extent.setSystemInfo("OS", "Windows");
        extent.setSystemInfo("Browser", "Firefox");
        extent.setSystemInfo("Author", "CNS Ltd");
    }

    @BeforeMethod
    public void setUp() {
        //Open Browser
        WebDriverManager.firefoxdriver().clearDriverCache().setup();
        driver = new FirefoxDriver();

        // Open Application URL
        driver.get("http://123.200.20.20:5302/");
        driver.manage().window().maximize();

        //implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void fillOnlineJobApplicationCompleteProcess() throws InterruptedException {
        test = extent.createTest("fillOnlineCompleteProcess", "fill Online Job Application Completion Process");

        List<WebElement> elementList = driver.findElements(By.cssSelector(" table#circularMst tbody tr"));
        System.out.println(elementList.size());

        if (elementList.size() > 0) {
            elementList.get(0).findElement(By.linkText("DETAILS")).click();
        }

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
        File uploadFile = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\NID_Rashedul.jpg");
        WebElement fileInput = driver.findElement(By.cssSelector("input#national_id_attachment"));
        fileInput.sendKeys(uploadFile.getAbsolutePath());

        //select date of birth
        driver.findElement(By.cssSelector("input#date_of_birth")).click();
        driver.findElement(By.cssSelector("div.top")).isDisplayed();
        driver.findElement(By.cssSelector("th.picker-switch[title='Select Month']")).click();
        driver.findElement(By.cssSelector("th.picker-switch[title='Select Year']")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("span.bx-chevron-left[title='Previous Decade']")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("span.bx-chevron-left[title='Previous Decade']")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("span.bx-chevron-left[title='Previous Decade']")).click();
        Thread.sleep(2000);

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

        //Verify NID
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("button#nid_verification")).click();
        Thread.sleep(3000);

        //Input Father Name
        driver.findElement(By.cssSelector("input#father_name")).sendKeys(fatherName);
        Thread.sleep(2000);

        //Input Mother Name
        driver.findElement(By.cssSelector("input#mother_name")).sendKeys(motherName);

        //Input Mobile No
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input#mobile")).sendKeys("01729415699");

        //verify OTP Password
        Thread.sleep(2000);
        Alert alert = driver.switchTo().alert();
        String alertPassword = alert.getText();
        //Get and Input OTP
        String password = alertPassword.replaceAll("[^0-9]", "");
        alert.accept();

        //Enter OTP
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input#otp")).sendKeys(password);
        Thread.sleep(2000);
        alert.accept();

        // Input E-mail Address
        driver.findElement(By.cssSelector(" input#email")).sendKeys("rasel.cse07@gmail.com");
        Thread.sleep(2000);

        //Select Religion
        Select selectReligion = new Select(driver.findElement(By.cssSelector("select#religion")));
        selectReligion.selectByIndex(1);
        Thread.sleep(2000);

        //Scroll Down
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)", "");

        //Attach Photo
        Thread.sleep(2000);

        File photo = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\Photo_Rashed.jpg");
        WebElement photoAttachment = driver.findElement(By.cssSelector("input#photo"));
        photoAttachment.sendKeys(photo.getAbsolutePath());
        Thread.sleep(5000);

        //Attach Signature
        File signature = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\Signature_Rashed.jpg");
        WebElement signatureAttachment = driver.findElement(By.cssSelector("input#signature"));
        signatureAttachment.sendKeys(signature.getAbsolutePath());
        Thread.sleep(5000);

        //Scroll Down
        JavascriptExecutor js2 = (JavascriptExecutor) driver;
        js2.executeScript("window.scrollBy(0,200)", "");

        //Click on Next button
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("button.nextBtn[value='first']")).click();
        Thread.sleep(2000);

        //Address Section
        //Select DIVISION
        Select selectDivision = new Select(driver.findElement(By.cssSelector("select#permanent_division")));
        selectDivision.selectByIndex(1);
        Thread.sleep(2000);

        //Select District
        Select selectDistrict = new Select(driver.findElement(By.cssSelector("select#permanent_district")));
        selectDistrict.selectByIndex(1);
        Thread.sleep(5000);

        //Select Thana
        driver.findElement(By.cssSelector("span#select2-permanent_thana-container")).click();
        Thread.sleep(2000);

        driver.findElements(By.cssSelector("ul.select2-results__options > li[id^='select2-permanent_thana-result']")).get(1).click();
        Thread.sleep(2000);

        // Input Post Office
        driver.findElement(By.cssSelector("input#permanent_post_office_name")).sendKeys("Dhaka");
        Thread.sleep(2000);

        // Input Post Code
        driver.findElement(By.cssSelector("input#permanent_post_code")).sendKeys("1250");
        Thread.sleep(2000);

        // Input Permanent Address
        driver.findElement(By.cssSelector(" input#permanent_address")).sendKeys("Banasree R 9");
        Thread.sleep(2000);

        //Scroll Down
        JavascriptExecutor js3 = (JavascriptExecutor) driver;
        js3.executeScript("window.scrollBy(0,250)", "");

        // Check same_as_permanent_address checkbox
        driver.findElement(By.cssSelector("div fieldset.form-group div.custom-checkbox")).click();
        Thread.sleep(2000);

        //Click on Next button
        driver.findElement(By.cssSelector("div#enable_after_district_verification button.nextBtn[type='button']")).click();
        Thread.sleep(2000);

        //Education Section
        //Select Exam
        Select selectExam = new Select(driver.findElement(By.cssSelector("select#education_0_exam")));
        selectExam.selectByIndex(1);
        Thread.sleep(2000);

        //Select Subject
        driver.findElement(By.cssSelector("span#select2-education_0_subject-container")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("ul.select2-results__options > li[id^='select2-education_']")).click();

        //Select Board
        driver.findElement(By.cssSelector("span#select2-education_0_exam_body-container")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("ul.select2-results__options > li[id^='select2-education_']")).click();
        Thread.sleep(2000);

        //Select Passing year
        driver.findElement(By.cssSelector("input#education_0_passing_year")).sendKeys("2024");
        Thread.sleep(2000);

        //Select Result with CGPA
        driver.findElement(By.cssSelector("span#select2-education_0_result_type-container")).click();
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("ul.select2-results__options > li[id^='select2-education_']")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input#education_0_result")).sendKeys("5");

        //Select SSC Exam
        Select selectSscExam = new Select(driver.findElement(By.cssSelector("select#education_1_exam")));
        selectSscExam.selectByIndex(1);
        Thread.sleep(2000);

        //Select Subject
        driver.findElement(By.cssSelector("span#select2-education_1_subject-container")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("ul.select2-results__options > li[id^='select2-education_']")).click();
        Thread.sleep(2000);

        //Select Board
        driver.findElement(By.cssSelector("span#select2-education_1_exam_body-container")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("ul.select2-results__options > li[id^='select2-education_1_exam_body-result']")).click();
        Thread.sleep(2000);

        //Select SSC passing year
        driver.findElement(By.cssSelector("input#education_1_passing_year")).sendKeys("2024");
        Thread.sleep(2000);

        //Select SSC Result with CGPA
        driver.findElement(By.cssSelector("span#select2-education_1_result_type-container")).click();
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("ul.select2-results__options > li[id^='select2-education_1']")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input#education_1_result")).sendKeys("5");

        //Click on Next button
        driver.findElement(By.cssSelector("div#nextButtonContainer button.nextBtn[type='button']")).click();
        Thread.sleep(2000);

        //Job Experience Section
        //Input Organization
        driver.findElement(By.cssSelector("input#jobexperience_0_organization")).sendKeys("BRAC IT");
        Thread.sleep(2000);

        //Select Designation
        /*Select designation = new Select(driver.findElement(By.cssSelector("span#select2-jobexperience_0_designation-container")));
        selectExam.selectByIndex(1);
        Thread.sleep(2000);

        //Select START DATE
        driver.findElement(By.cssSelector("input#jobexperience_0_start_date")).click();
        Thread.sleep(2000);*/
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Case failed for" + " " + result.getName());
            test.log(Status.FAIL, "Test Case failed for" + " " + result.getThrowable());
            String screenshotPath = CPortAuthority.getScreenShot(driver, result.getName());
            test.addScreenCaptureFromPath(screenshotPath);
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test Case Skipped for " + " " + result.getName());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test Case Passed for" + " " + result.getName());
        }
        driver.quit();

    }

    @AfterTest
    public void endReport() {
        extent.flush();
    }

    public static String getScreenShot(WebDriver driver, String screenshotName) throws IOException {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        String source = ts.getScreenshotAs(OutputType.BASE64);
        String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(new File(source), finalDestination);
        return destination;
    }

}

