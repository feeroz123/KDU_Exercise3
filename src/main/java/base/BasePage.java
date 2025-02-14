package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class BasePage {

    public WebElement get_element(WebDriver driver, String locator) {
        WebElement element = null;

        String[] locator_components = locator.split("=>");
        String locator_type = locator_components[0].trim().toLowerCase();
        String locator_value = locator_components[1].trim();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        try {
            switch (locator_type) {
                case "id":
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locator_value)));
                    element = driver.findElement(By.id(locator_value));
                    break;
                case "name":
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.name(locator_value)));
                    element = driver.findElement(By.name(locator_value));
                    break;
                case "xpath":
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator_value)));
                    element = driver.findElement(By.xpath(locator_value));
                    break;
                case "css":
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locator_value)));
                    element = driver.findElement(By.cssSelector(locator_value));
                    break;
                case "class_name":
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.className(locator_value)));
                    element = driver.findElement(By.className(locator_value));
                    break;
            }
        } catch (Exception e) {
            System.out.println("Element not found with given locator: " + locator_value);
        }

        return element;
    }

    public void click_element(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void enter_value(WebDriver driver, WebElement element, String value) {
        System.out.println("Entering value : '" + value);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(value);
    }

}
