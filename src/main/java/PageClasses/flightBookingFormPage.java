package PageClasses;

import base.basePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class flightBookingFormPage extends basePage {
    //Locators
    @FindBy(xpath = "//input[@id='inputName']")
    private WebElement Name;
    @FindBy(xpath = "//input[@id='address']")
    private WebElement Address;
    @FindBy(xpath = "//input[@id='city']")
    private WebElement City;
    @FindBy(xpath = "//input[@id='state']")
    private WebElement State;
    @FindBy(xpath = "//input[@id='zipCode']")
    private WebElement Zipcode;
    @FindBy(xpath = "//select[@id='cardType']")
    private WebElement Cardtype;
    @FindBy(xpath = "//input[@id='creditCardMonth']")
    private WebElement Creditcard;
    @FindBy(xpath = "//input[@id='creditCardNumber']")
    private WebElement CreditCardNumber;
    @FindBy(xpath = "//input[@id='creditCardMonth']")
    private WebElement CreditCardMonth;
    @FindBy(xpath = "//input[@id='creditCardYear']")
    private WebElement CreditCardYear;
    @FindBy(xpath = "//input[@id='nameOnCard']")
    private WebElement CreditCardName;
    @FindBy(xpath = "//input[@id='rememberMe']")
    private WebElement RememberMecheck;
    @FindBy(xpath = "//input[@value='Purchase Flight']")
    private WebElement PurchaseFlightbtn;


    public flightBookingFormPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void setUserName() { Name.sendKeys("Jennie");}
    public void setUserAddress() { Address.sendKeys("Rajajinagar");}
    public void setCity() { City.sendKeys("Bengaluru");}
    public void setState() { State.sendKeys("Karnataka");}
    public void setZipCode() { Zipcode.sendKeys("564444");}
    public void SetCardType()
    {
        Select sel=new Select(Cardtype);
        sel.selectByValue("amex");
    }
    public void setCreditCardNumberVoid() { Creditcard.sendKeys("56786567865467");}
    public void setCardMonth() { CreditCardMonth.sendKeys("12");}
    public void setCardYear() { CreditCardYear.sendKeys("2026");}
    public void setCardName() { CreditCardName.sendKeys("jennie");}
    public void setRememberMe() { RememberMecheck.click();}
    public void setPurchaseFlight() { PurchaseFlightbtn.click();}
    }

