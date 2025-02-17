package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.LinkedHashMap;
import java.util.Map;

public class FlightBookingPage {
    WebDriver driver;
    private static final Logger logger = LogManager.getLogger(FlightBookingPage.class);

    @FindBy(xpath = "//p[contains(text(),'Airline:')]")
    WebElement airlineText;

    @FindBy(xpath = "//p[contains(text(),'Flight Number:')]")
    WebElement flightNumberText;

    @FindBy(xpath = "//p[contains(text(),'Price:')]")
    WebElement priceText;

    public FlightBookingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to retrieve flight details displayed on the booking page
    public Map<String, String> getDisplayedFlightDetails() {
        Map<String, String> displayedDetails = new LinkedHashMap<>();

        displayedDetails.put("Airline", airlineText.getText().replace("Airline: ", "").trim());
        displayedDetails.put("Flight Number", flightNumberText.getText().replace("Flight Number: ", "").trim());
        displayedDetails.put("Price", priceText.getText().replace("Price: ", "").trim());

        logger.info("Retrieved flight details from the booking page: " + displayedDetails);
        return displayedDetails;
    }
}
