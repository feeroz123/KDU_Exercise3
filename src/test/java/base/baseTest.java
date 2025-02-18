package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class baseTest {

    public WebDriver launch_browser(String browser_type) {
        WebDriver local_driver;
        ExtentReports extent;
        ExtentTest test;

        extent = new ExtentReports();
        extent.setSystemInfo("Project Name", "BlazeDemo");
        extent.setSystemInfo("Organisation", "xyz Pvt. Ltd.");
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("ExtentReports/ExtentReport.html");
        extent.attachReporter(sparkReporter);

        switch (browser_type.toLowerCase()) {

            case "chrome":
                local_driver = new ChromeDriver();
                break;

            case "firefox":
                local_driver = new FirefoxDriver();
                break;
            case "edge":
                local_driver = new EdgeDriver();
                break;
            default:
                local_driver = new ChromeDriver();
                break;
        }

        test = extent.createTest("Launched the browser type: " + browser_type);

        local_driver.manage().window().maximize();
        local_driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        return local_driver;
    }

}
