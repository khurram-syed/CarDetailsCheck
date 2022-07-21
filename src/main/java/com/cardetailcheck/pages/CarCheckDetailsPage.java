package com.cardetailcheck.pages;

import com.cardetailcheck.driver.SeleniumDriver;
import com.cardetailcheck.driver.SelniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CarCheckDetailsPage {

    public CarCheckDetailsPage(){
        PageFactory.initElements(SeleniumDriver.getDriver(),this);
    }

    @FindBy(how= How.XPATH,using="//span[contains(.,'Sorry')]")
    public WebElement carNotFoundText;

    @FindBy(how= How.CSS,using="[data-test-id='your-registration-number-summary'] p")
    public List<WebElement> regNoDetails;

    public String getOnlyActualRegNo(){
        String regText = regNoDetails.get(0).getText();
        return  regText.split((":"))[1].trim();
    }

    public String getOnlyActualMakeAndModel(){
        String carDetails = regNoDetails.get(1).getText();
        return carDetails.split((":"))[1].trim();
    }

    public By regNumberHeadingBy(){
        return By.xpath("//h2[contains(.,'Your registration number')]");
    }

    public boolean waitForRegNumberHeadingBy(){
        return (SelniumHelper.waitForElementVisible(regNumberHeadingBy())!=null);
    }

    public By carDetailsFoundBy(){
        return By.xpath("[data-test-id='your-registration-number-summary'] p:first-child");
    }

    public boolean isCarDetailsFound(){
        return (SelniumHelper.waitForElementVisible(carDetailsFoundBy())!=null);
    }

    public String getMakeORModel(String makeOrModel){
        String[] carMakeAndModel = getOnlyActualMakeAndModel().split(" ",2);
        if(makeOrModel.equalsIgnoreCase("make")){
            return carMakeAndModel[0].trim();
        }else{
            return carMakeAndModel[1].trim();
        }
    }

    public String getOnlyModel(){
        String carDetails = regNoDetails.get(1).getText();
        return carDetails.split((":"))[1].trim();
    }


}
