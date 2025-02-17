package base;

import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.Map;

public class FlightsSearchResultsTest extends BaseTest{
    WebDriver driver;
    String baseUrl = "https://blazedemo.com/reserve.php";
    private static final Logger logger = LogManager.getLogger(FlightsSearchResultsTest.class);
    FlightsSearchResultsPage flightsSearchResultsPage;
    //HomePage homePage;

    @BeforeClass
    @Parameters("browser")
    public void setUp(String browser) {
        initExtentReports("FlightsSearchResultsPageExtentReport");
        driver = launch_browser(browser);
        driver.manage().window().maximize();
        driver.get(baseUrl);
        flightsSearchResultsPage = new FlightsSearchResultsPage(driver);
        //homePage = new HomePage(driver);
        //homePage.submitDetails();
        test = extent.createTest("Flights Search Results Test", "Verify flight selection functionality");
        logger.info("Browser launched and navigated to flights search results page.");
    }

    @Test
    public void testSelectAnyFlight() {
        test.log(Status.INFO, "Checking if flights are displayed.");

        // Verify flights are displayed
        boolean flightsDisplayed = flightsSearchResultsPage.areFlightsDisplayed();
        if (flightsDisplayed) {
            test.log(Status.PASS, "Flights are displayed on the page.");
            logger.info("Flights are available.");
        } else {
            test.log(Status.FAIL, "Flights are NOT displayed on the page.");
            logger.error("No flights found");
            Assert.fail("No flights found!");
        }

        // Fetch 4th flight details
        test.log(Status.INFO, "Fetching details of the 4th flight.");
        Map<String, String> flightDetails = flightsSearchResultsPage.getFlightDetails(4);

        if (!flightDetails.isEmpty()) {
            for (Map.Entry<String, String> entry : flightDetails.entrySet()) {
                test.log(Status.INFO, entry.getKey() + ": " + entry.getValue());
                logger.info(entry.getKey() + ": " + entry.getValue());
            }
        } else {
            test.log(Status.WARNING, "Could not retrieve flight details");
        }

        flightsSearchResultsPage.selectFlightByRow(4);
        test.log(Status.PASS, "Successfully selected the 4th flight.");
        logger.info("Successfully selected the 4th flight.");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        test.log(Status.INFO, "Closed the browser.");
        logger.info("Browser closed.");
        flushExtentReports();
    }
}
