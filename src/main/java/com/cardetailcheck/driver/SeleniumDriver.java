package com.cardetailcheck.driver;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class SeleniumDriver {
    static final Logger logger = LogManager.getLogger(SeleniumDriver.class);
    private static SeleniumDriver seleniumDriver;
    private  static WebDriver driver;
    public final static int TIME_OUT = 3;
    public final static int PAGE_LOAD_TIMEOUT = 10;
    private static String driverName="chrome";

    private SeleniumDriver(){
        String driverPath=System.getProperty("user.dir")+"\\src\\main\\resources\\";
        logger.info("Driver Path :"+driverPath);

       if(driverName.equalsIgnoreCase("chrome")) {
           System.setProperty("webdriver.chrome.driver", driverPath + "drivers/chromedriver.exe");
           driver = new ChromeDriver();
       }else{
           System.setProperty("webdriver.gecko.driver", driverPath + "drivers/geckodriver.exe");
           driver = new FirefoxDriver();
       }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(TIME_OUT, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        logger.info("Driver Handle :"+driver.getWindowHandle());
        System.out.println("Driver Handle :"+driver.getWindowHandle());
    }

   public static void setUpDriver(){
        if(seleniumDriver ==null){
            seleniumDriver = new SeleniumDriver();
        }
   }

    public static void tearDownDriver(){
        if(driver !=null){
            driver.close();
            driver.quit();
        }
        seleniumDriver =null;
    }

    public static void navigate(String url){
        driver.get(url);
    }

    public static void navigateBack(){
        driver.navigate().back();
    }

    public static WebDriver getDriver(){
        return driver;
    }

}
