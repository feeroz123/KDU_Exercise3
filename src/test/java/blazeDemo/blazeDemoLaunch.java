package blazeDemo;

import PageClasses.confirmationPage;
import PageClasses.flightBookingFormPage;
import base.baseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class blazeDemoLaunch extends baseTest {

        public WebDriver driver;
        ExtentReports extent;
        ExtentTest test;
        flightBookingFormPage flight_Booking_FormPage;
        confirmationPage  confirmation_Page;

        @Parameters("browser")
        @BeforeClass
        public void launchBrowser(String browser){
            extent = new ExtentReports();
            extent.setSystemInfo("Project Name", "BlazeDemo");
            extent.setSystemInfo("Organisation", "xyz Pvt. Ltd.");
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("ExtentReports/ExtentReport.html");
            extent.attachReporter(sparkReporter);

            driver= launch_browser(browser);
            driver.manage().window().maximize();
            driver.get("https://blazedemo.com/purchase.php");
            test= extent.createTest("Application is launched in the browser");
            flight_Booking_FormPage=new flightBookingFormPage(driver);
            confirmation_Page=new confirmationPage(driver);

        }
        @AfterClass
        public void tearDown(){
            driver.quit();
            extent.flush();
        }
}
