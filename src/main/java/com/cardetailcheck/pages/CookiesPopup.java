package com.cardetailcheck.pages;

import com.cardetailcheck.driver.SeleniumDriver;
import com.cardetailcheck.driver.SelniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CookiesPopup {
    public CookiesPopup(){
        PageFactory.initElements(SeleniumDriver.getDriver(),this);
    }

    @FindBy(how= How.XPATH,using="//button[.='Accept All']")
    public WebElement acceptAllBtn;


    public By acceptAllBtnFoundBy(){
      return By.xpath("//button[.='Accept All']");
    }

    public boolean isCookieBtnVisible(){
     return (SelniumHelper.waitForElementVisible(acceptAllBtnFoundBy())!=null);
    }

    public void clickAcceptAllBtn(){
        acceptAllBtn.click();
    }

}
