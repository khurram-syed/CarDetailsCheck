package com.cardetailcheck.pages;

import com.cardetailcheck.driver.SeleniumDriver;
import com.cardetailcheck.driver.SelniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CarCheckHomePage {


    public CarCheckHomePage(){
        PageFactory.initElements(SeleniumDriver.getDriver(),this);
    }

    @FindBy(how= How.ID,using="vrm")
    public WebElement carRegInputTxtBox;

    @FindBy(how= How.XPATH,using="//button[.='Start valuation']")
    public WebElement startValuation;

    public void submitCarRegNo(String regNo){
        carRegInputTxtBox.clear();
        carRegInputTxtBox.sendKeys(regNo);
        startValuation.submit();
    }

    public By carRegInputTxtBy(){
        return By.cssSelector("#vrm");
    }

    public boolean waitForCarRegInputTxtBy(){
        return (SelniumHelper.waitForElementVisible(carRegInputTxtBy())!=null);
    }
}
