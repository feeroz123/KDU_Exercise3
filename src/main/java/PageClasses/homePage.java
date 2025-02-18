package PageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class homePage {

    //Locators
    @FindBy(xpath = "//h1[text()='Welcome to the Simple Travel Agency!']")
    private WebElement blazeDemo_Heading;
    @FindBy(xpath = "//input[@value='Find Flights']")
    private WebElement findFlights_Btn;

    //Initialization
    public homePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    //Utilization

    public String getPageHeading() {
        return blazeDemo_Heading.getText();
    }

    public boolean getFindFlightsbtn() {
        return findFlights_Btn.isDisplayed();
    }

}


