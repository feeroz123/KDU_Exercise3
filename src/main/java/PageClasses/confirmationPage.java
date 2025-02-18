package PageClasses;

import base.basePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class confirmationPage extends basePage {
        //Locators
        @FindBy(xpath = "//h1[text()=\"Thank you for your purchase today!\"]")
        private WebElement bDConfirmation_Heading;
        @FindBy(xpath = "//tr[td[text()='Id']]/td[2]")
        private WebElement flight_Id;
        @FindBy(xpath = "//tr[td[text()='Status']]/td[2]")
        private WebElement flightStatus;
        @FindBy(xpath = "//tr[td[text()='Amount']]/td[2]")
        private WebElement flightAmount;
        @FindBy(xpath = "//tr[td[text()='Expiration']]/td[2]")
        private WebElement flightExpiration;
        @FindBy(xpath = "//tr[td[text()='Auth Code']]/td[2]")
        private WebElement flightAutoCode;
        @FindBy(xpath = "//tr[td[text()='Date']]/td[2]")
        private WebElement flightDate;

        //Initialization
        public confirmationPage(WebDriver driver) {
            PageFactory.initElements(driver, this);
        }

        //Utilization

        public String getBlazeConfirmHeading() {return getElementText(bDConfirmation_Heading,"confirmationHeading");}

        public String getBlazeConfirmID() {return getElementText(flight_Id,"confirmationID");}

        public String getBlazeConfirmStatus() {return getElementText(flightStatus,"confirmationStatus");}

        public String getBlazeConfirmAmount(){return getElementText(flightAmount,"confirmationAmount");}

        public String getBlazeConfirmExpiration(){return getElementText(flightExpiration,"confirmationExp");}

        public String getBlazeConfirmAutoCode(){return getElementText(flightAutoCode,"ConfirmAutoCode");}

        public String getBlazeDate(){return getElementText(flightDate,"ConfirmatrionDate");}



        public Boolean isFlightIdDisplayed(){return isElementDisplayed(flight_Id,"Flight ID");}

        public Boolean isFlightStatusDisplayed(){return  isElementDisplayed(flightStatus,"Flight Status");}

        public Boolean isFlightAmountDisplayed(){return isElementDisplayed(flightAmount,"Flight Amount");}

        public Boolean isFlightExpirationDisplayed(){return isElementDisplayed(flightExpiration,"Flight Expiration");}

        public Boolean isFlightAutoCodeDisplayed(){return isElementDisplayed(flightAutoCode,"Flight AutoCode");}

        public Boolean isFlightDateDisplayed(){return isElementDisplayed(flightDate,"Flight Date");}



}


