package com.cardetailcheck.steps.hooks;

import com.cardetailcheck.driver.SeleniumDriver;
import com.cardetailcheck.steps.SearchCarsDetailsStep;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class Hooks {
    static final Logger logger = LogManager.getLogger(Hooks.class);

    @Before
    public static void setUp(){
        SeleniumDriver.setUpDriver();
        logger.info("Driver has been setup successfully..!!");
    }

    @After
    public static void tearDown(Scenario scenario){
        WebDriver driver=SeleniumDriver.getDriver();
        System.out.println(scenario.isFailed());
        if (scenario.isFailed()) {
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshotBytes, "image/png");

        }
        SeleniumDriver.tearDownDriver();
        logger.info("Driver quits successfully..!!");
    }
}
