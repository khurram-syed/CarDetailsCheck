package com.cardetailcheck.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.cardetailcheck.driver.SeleniumDriver.getDriver;


public class SelniumHelper {
    private static final int WAIT_TIME=3;

    /**
     * This wait method returns boolean(true/false) when supplied WebElement to it.
     *
     * @param element
     * @return boolean
     */
    public static boolean isElementVisible(WebElement element){
        try{
            return element.isDisplayed();
        }catch(NoSuchElementException nse){
            return false;
        }
    }

    /**
     * This wait method returns the WebElement visible to user in the DOM when supplied By locator to it
     *
     * @param by
     * @return WebElement
     */
    public static WebElement waitForElementVisible(final By by) {
        try {
        Wait<WebDriver> wait = new WebDriverWait(getDriver(), WAIT_TIME);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        }catch (Exception e){
            return null;
        }
        return getDriver().findElement(by);

    }

}
