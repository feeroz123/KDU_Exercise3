package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class BaseTest {

    public WebDriver launch_browser(String browser_type) {
        WebDriver local_driver;

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

}
