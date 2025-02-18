package blazeDemo;

import PageClasses.homePage;
import base.baseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class blaszeDemoHome extends baseTest {
    public WebDriver driver;
    ExtentReports extent;
    ExtentTest test;
    homePage home_page;
    @Parameters("browser")
    @BeforeClass
    public void launchBrowser(String browser){

        extent = new ExtentReports();
        extent.setSystemInfo("Project Name", "BlazeDemo");
        extent.setSystemInfo("Organisation", "xyz Pvt. Ltd.");
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("ExtentReports/ExtentHomeReport.html");
        extent.attachReporter(sparkReporter);

        driver= launch_browser(browser);
        driver.manage().window().maximize();
        driver.get("https://blazedemo.com/index.php");
        test = extent.createTest("Verify URl and Home Page");
        test.log(Status.INFO,"Application is launched in the browser");
        home_page =new homePage(driver);
    }
    @Test
    public void verifyBlazeURL() {
        String actualUrl = "https://blazedemo.com/index.php";
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, URL, "FAIL:Application URl is not verified");
        test.log(Status.INFO,"Application URL is successfully verified");
    }

    @Test
    public void verifyBlazeHeading() {
        Assert.assertEquals(home_page.getPageHeading(), "Welcome to the Simple Travel Agency!", "Heading mismatch");
        test.log(Status.INFO,home_page.getPageHeading() + " is Heading is appropriate");
        Assert.assertTrue(home_page.getFindFlightsbtn(), "FAIL: Find Flight Button is displayed");
        test.log(Status.INFO,"Find flight button is present in the blazedemo indexpage");
        test.log(Status.INFO,"Home Page verified");
        }

    @AfterClass
    public void tearDown(){
        driver.quit();
        extent.flush();
    }

    }

