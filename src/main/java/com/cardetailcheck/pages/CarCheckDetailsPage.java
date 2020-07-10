package com.cardetailcheck.pages;

import com.cardetailcheck.driver.SeleniumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CarCheckDetailsPage {

    public CarCheckDetailsPage(){
        PageFactory.initElements(SeleniumDriver.getDriver(),this);
    }

    @FindBy(how= How.XPATH,using="//dt[.='Registration']/..//dd")
    public WebElement carRegistrationTxt;

    @FindBy(how= How.XPATH,using="//dt[.='Make']/..//dd")
    public WebElement carMakeTxt;

    @FindBy(how= How.XPATH,using="//dt[.='Model']/..//dd")
    public WebElement carModel;

    @FindBy(how= How.XPATH,using="//dt[.='Colour']/..//dd")
    public WebElement carColourTxt;

    @FindBy(how= How.XPATH,using="//dt[.='Year']/..//dd")
    public WebElement carYearTxt;

}
