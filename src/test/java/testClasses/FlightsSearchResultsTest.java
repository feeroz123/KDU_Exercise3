package testClasses;

import base.BaseTest;
import base.FlightBookingPage;
import base.FlightsSearchResultsPage;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class FlightsSearchResultsTest extends BaseTest {
    WebDriver driver;
    String baseUrl = "https://blazedemo.com/reserve.php";
    private static final Logger logger = LogManager.getLogger(FlightsSearchResultsTest.class);
    FlightsSearchResultsPage flightsSearchResultsPage;
    FlightBookingPage flightBookingPage;
    Map<String, String> selectedFlightDetails;

    @BeforeClass
    @Parameters("browser")
    public void setUp(String browser) {
        super.initExtentReports("FlightsSearchResultsPageExtentReport"); // Initialize Extent Reports
        driver = launch_browser(browser);
        driver.manage().window().maximize();
        driver.get(baseUrl);
        flightsSearchResultsPage = new FlightsSearchResultsPage(driver);
    }

    @Test
    public void testSelectAnyFlight() {
        test = extent.createTest("Flights Search Results Test", "Verify flight selection functionality");
        logger.info("Browser launched and navigated to flights search results page.");
        test.log(Status.INFO, "Checking if flights are displayed.");

        boolean flightsDisplayed = flightsSearchResultsPage.areFlightsDisplayed();
        if (flightsDisplayed) {
            test.log(Status.PASS, "Flights are displayed on the page.");
            logger.info("Flights are available.");
        } else {
            test.log(Status.FAIL, "Flights are NOT displayed on the page.");
            logger.error("No flights found");
            Assert.fail("No flights found!");
        }

        test.log(Status.INFO, "Fetching details of the 4th flight.");
        selectedFlightDetails = flightsSearchResultsPage.getFlightDetails(4);

        if (!selectedFlightDetails.isEmpty()) {
            for (Map.Entry<String, String> entry : selectedFlightDetails.entrySet()) {
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

    @Test(priority = 2, dependsOnMethods = "testSelectAnyFlight")
    public void testVerifyBookingDetails() {
        test = extent.createTest("Verify Booking Details Test", "Verify flight details on booking page");
        flightBookingPage = new FlightBookingPage(driver);

        if (selectedFlightDetails == null || selectedFlightDetails.isEmpty()) {
            test.log(Status.FAIL, "Selected flight details are missing. Cannot proceed with verification.");
            logger.error("Selected flight details are missing.");
            Assert.fail("Selected flight details are missing. Test cannot proceed.");
        }

        test.log(Status.INFO, "Fetching flight details from the booking page.");
        Map<String, String> displayedFlightDetails = flightBookingPage.getDisplayedFlightDetails();

        // Mapping keys to match between Search Results and Booking Page
        Map<String, String> expectedDetails = new LinkedHashMap<>();
        expectedDetails.put("Airline", selectedFlightDetails.get("Airline").trim());
        expectedDetails.put("Flight Number", selectedFlightDetails.get("Flight").trim());
        expectedDetails.put("Price", selectedFlightDetails.get("Price").trim());

        test.log(Status.INFO, "Expected Flight Details: " + expectedDetails);
        test.log(Status.INFO, "Actual Flight Details from Booking Page: " + displayedFlightDetails);

        logger.info("Expected Flight Details: " + expectedDetails);
        logger.info("Actual Flight Details from Booking Page: " + displayedFlightDetails);

        boolean mismatchFound = false;

        test.log(Status.INFO, "Validating flight details between search results and booking page.");
        for (String key : expectedDetails.keySet()) {
            String expectedValue = expectedDetails.get(key).toLowerCase();  // Case insenseitive comp
            String actualValue = displayedFlightDetails.getOrDefault(key, "N/A").trim().toLowerCase();

            if (!expectedValue.equals(actualValue)) {
                String errorMsg = "Mismatch in " + key + ": Expected '" + expectedDetails.get(key) + "' but found '" + actualValue + "'";
                test.log(Status.FAIL, errorMsg);
                logger.error(errorMsg);
                mismatchFound = true;
            } else {
                String successMsg = key + " matches: " + expectedDetails.get(key);
                test.log(Status.PASS, successMsg);
                logger.info(successMsg);
            }
        }

        if (mismatchFound) {
            Assert.fail("Flight details mismatches found. Check logs for details.");
        }

        logger.info("Flight details verification successful!");
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
        test.log(Status.INFO, "Closed the browser.");
        logger.info("Browser closed.");
        super.flushExtentReports();
    }
}
