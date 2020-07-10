package com.cardetailcheck.pages;

import com.cardetailcheck.driver.SeleniumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CarCheckHomePage {


    public CarCheckHomePage(){
        PageFactory.initElements(SeleniumDriver.getDriver(),this);
    }

    @FindBy(how= How.ID,using="vrm-input")
    public WebElement carRegInputTxtBox;

    @FindBy(how= How.XPATH,using="//button[.='Free Car Check']")
    public WebElement freeCarCheckButton;

    public void submitCarRegNo(String regNo){
        carRegInputTxtBox.sendKeys(regNo);
        freeCarCheckButton.submit();
    }
}
