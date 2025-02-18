package blazeDemo;

import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.core.net.Priority;
import org.testng.Assert;
import org.testng.annotations.Test;

public class blazeDemoConfirmation extends blazeDemoLaunch {


    @Test(priority = 1)
    public void verifyFlightBookingForm(){
        flight_Booking_FormPage.setUserName();
        flight_Booking_FormPage.setUserAddress();
        flight_Booking_FormPage.setCity();
        flight_Booking_FormPage.setState();
        flight_Booking_FormPage.setZipCode();
        flight_Booking_FormPage.SetCardType();
        flight_Booking_FormPage.setCreditCardNumberVoid();
        flight_Booking_FormPage.setCardMonth();
        flight_Booking_FormPage.setCardYear();
        flight_Booking_FormPage.setCardName();
        flight_Booking_FormPage.setRememberMe();
        flight_Booking_FormPage.setPurchaseFlight();
        test.log(Status.INFO,"Flight booking form is successfully filled and confirmation message is displayed");
    }
    @Test(priority = 2)
    public void verifyBlazeConfirmationHeading() {
        String actualHeading = "Thank you for your purchase today!";
        Assert.assertEquals(confirmation_Page.getBlazeConfirmHeading(),actualHeading, "FAIL: Confirmation message is not displayed");
        test.log(Status.INFO,confirmation_Page.getBlazeConfirmHeading() + " is displayed as confirmation message");
    }
    @Test(priority = 3)
    public void verifyBlazeFlightConfirmationPage(){
        Assert.assertTrue(confirmation_Page.isFlightIdDisplayed(),"FAIL:flight id is not displayed");
        test.log(Status.INFO,confirmation_Page.getBlazeConfirmID()+ " is the confirmationID");
        Assert.assertTrue(confirmation_Page.isFlightStatusDisplayed(), "FAIL:Flight status is not displayed");
        test.log(Status.INFO,confirmation_Page.getBlazeConfirmStatus()+ "is the status");
        Assert.assertTrue(confirmation_Page.isFlightAmountDisplayed(), "FAIL:Flight Amount is not displayed");
        test.log(Status.INFO,confirmation_Page.getBlazeConfirmAmount()+ "is the Flight Amount");
        Assert.assertTrue(confirmation_Page.isFlightExpirationDisplayed(), "FAIL:Flight Expiration is not displayed");
        test.log(Status.INFO,confirmation_Page.getBlazeConfirmExpiration()+ "is the Flight Expiration");
        Assert.assertTrue(confirmation_Page.isFlightAutoCodeDisplayed(), "FAIL:Flight Auto Code is not displayed");
        test.log(Status.INFO,confirmation_Page.getBlazeConfirmAutoCode()+ "is the Flight auto code");
        Assert.assertTrue(confirmation_Page.isFlightDateDisplayed(), "FAIL:Flight Date is not displayed");
        test.log(Status.INFO,confirmation_Page.getBlazeDate()+ "is the Flight Date");
    }
}


