package com.cardetailcheck.pages;

import com.cardetailcheck.driver.SeleniumDriver;
import com.cardetailcheck.driver.SelniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class VehicleNotFoundPopup {
    public VehicleNotFoundPopup(){
        PageFactory.initElements(SeleniumDriver.getDriver(),this);
    }

    @FindBy(how= How.XPATH,using="//span[.='Vehicle Not Found']")
    public WebElement vehicleNotFoundTitle;

    @FindBy(how= How.XPATH,using="//a[.='Try Again']")
    public WebElement tryAgainButton;

    public By vehicleNotFoundBy(){
      return By.xpath("//span[.='Vehicle Not Found']");
    }

    public boolean isVehileFoundPopupVisible(){
     return (SelniumHelper.waitForElementVisible(vehicleNotFoundBy())!=null);
    }

}
