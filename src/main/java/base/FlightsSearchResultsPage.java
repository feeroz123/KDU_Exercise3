package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FlightsSearchResultsPage {
    WebDriver driver;
    private static final Logger logger = LogManager.getLogger(FlightsSearchResultsPage.class);


    @FindBy(xpath = "//table[@class='table']/tbody/tr/td[1]/input")
    List<WebElement> chooseFlightButtons;

    @FindBy(xpath = "//table[@class='table']/tbody/tr")
    List<WebElement> flightRows;

    public FlightsSearchResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean areFlightsDisplayed() {
        return flightRows.size() > 0;
    }

    // Method to fetch flight details for a given row
    public Map<String, String> getFlightDetails(int rowIndex) {
        Map<String, String> flightDetails = new LinkedHashMap<>();

        if (flightRows.size() >= rowIndex) {
            WebElement selectedRow = flightRows.get(rowIndex - 1); // 0based index
            List<WebElement> rowCells = selectedRow.findElements(By.tagName("td"));

            flightDetails.put("Flight", rowCells.get(1).getText());
            flightDetails.put("Airline", rowCells.get(2).getText());
            flightDetails.put("Departure Time", rowCells.get(3).getText());
            flightDetails.put("Arrival Time", rowCells.get(4).getText());
            flightDetails.put("Price", rowCells.get(5).getText());
        }
        return flightDetails;
    }

    public void selectFlightByRow(int rowIndex) {
        if (flightRows.size() >= rowIndex) {
            WebElement selectedRow = flightRows.get(rowIndex - 1);
            List<WebElement> rowCells = selectedRow.findElements(org.openqa.selenium.By.tagName("td"));

            logger.info("Flight Details (Row " + rowIndex + ")");
            logger.info("Flight: " + rowCells.get(1).getText());
            logger.info("Airline: " + rowCells.get(2).getText());
            logger.info("Departure Time: " + rowCells.get(3).getText());
            logger.info("Arrival Time: " + rowCells.get(4).getText());
            logger.info("Price: " + rowCells.get(5).getText());

            chooseFlightButtons.get(rowIndex - 1).click();
            logger.info("Flight in row " + rowIndex + " selected!");
        } else {
            logger.warn("Not enough flights availablefor Requested row: " + rowIndex);
        }
    }
}

