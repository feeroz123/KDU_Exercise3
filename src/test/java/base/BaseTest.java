package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class BaseTest {

    WebDriver local_driver;
    ExtentReports extent;
    ExtentTest test;

    public WebDriver launch_browser(String browser_type) {

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

        System.out.println("Launched the browser type: " + browser_type);

        local_driver.manage().window().maximize();
        local_driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        return local_driver;
    }
    // Initialize ExtentReports
    public void initExtentReports(String reportName) {
        extent = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("ExtentReports/" + reportName + ".html");
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Project Name", "blazedemo");
        extent.setSystemInfo("Organisation", "KickdrumTech");
    }

    // Close ExtentReports
    public void flushExtentReports() {
        if (extent != null) {
            extent.flush();
        }
    }

}
