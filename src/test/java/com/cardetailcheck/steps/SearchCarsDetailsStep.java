package com.cardetailcheck.steps;

import com.cardetailcheck.driver.SeleniumDriver;
import com.cardetailcheck.pages.CarCheckDetailsPage;
import com.cardetailcheck.pages.CarCheckHomePage;
import com.cardetailcheck.pages.VehicleNotFoundPopup;
import com.cardetailcheck.utils.Utils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.asserts.SoftAssert;
import java.io.*;
import java.util.*;

public class SearchCarsDetailsStep {

    static final Logger logger = LogManager.getLogger(SearchCarsDetailsStep.class);
    List<String> carInputList = new ArrayList<String>();
    private static String URL =null;
    CarCheckHomePage carCheckHomePage = new CarCheckHomePage();
    CarCheckDetailsPage carCheckDetailsPage = new CarCheckDetailsPage();
    VehicleNotFoundPopup vehicleNotFoundPopup = new VehicleNotFoundPopup();

   @Given("I read the following input files for car details")
   public void IReadTheFollowingInputFilesForCarDetails (DataTable dataTable) throws IOException {
        logger.info("I read the following input files for car details");
        List<String> files = new ArrayList<String>();
        List<Map<String,String>> data = dataTable.asMaps(String.class,String.class);

   //** Getting the files names from DataTable **
        for(Map<String,String> cols : data){
            files.add(cols.get("Input Files"));
            System.out.println("Input files:"+cols.get("Input Files"));
        }

   //** Checking if any file provided **
       if(files.size()<1){
           try {
               throw new FileNotFoundException("No Files provided..!!");
           } catch (Exception e) {
               e.printStackTrace();
           }
       }

    //** Reading the number of input files and adding the cars in carList **
       for(String fileName : files){
           String inputFile = System.getProperty("user.dir")+"\\src\\test\\resources\\inputfiles\\"+fileName;
           carInputList.addAll(Utils.getCarRegsFromList(Utils.getListOfRecordsFromCSV(inputFile)));
       }

    //** Checking if any valid RegNumber found in the list **
       if(carInputList.size()<1){
           try {
               throw new FileNotFoundException("No Valid Registration Number Found in Input file(s)..!!");
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
    }

    @When("I navigate to the Home Page \"(.*)\"")
    public void INavigateToTheHomePage (String url) {
        logger.info("I navigate to the Home Page "+ url);
        URL=url;
        SeleniumDriver.navigate(url);
    }

    @Then("Car input file\\(s) values should be matching to the following output file values")
    public void ICheckTheInputFileValuesWithTheFollowingOutputFile(DataTable dataTable) {
        logger.info("I am on the Home Page Car input Files values should be matching to the following output file values");
        List<Map<String,String>> data = dataTable.asMaps(String.class,String.class);

      //** SoftAssertions to Match Multiple values with output files **
        SoftAssert softAssert = new SoftAssert();
        String outputFile = System.getProperty("user.dir")+"\\src\\test\\resources\\outputfile\\"+data.get(0).get("Output File");
        Map<String,Map<String,String>> recordsMapList =  Utils.getListOfRecordsMapFromCSV(outputFile);

        for(String regNo : carInputList){

     //** Fill the Reg number and submit the page **
           carCheckHomePage.submitCarRegNo(regNo);

     //** Check in next 5 secs if any "Vehicle Not Found" popup appears - If it does then Fail this RegNo Test **
            if(vehicleNotFoundPopup.isVehileFoundPopupVisible()){
                softAssert.assertTrue(false,regNo+ " Vehicle Not Found on Site..!!");
                vehicleNotFoundPopup.tryAgainButton.click();
            }else if(recordsMapList.get(regNo)==null){
     //** Check if the input RegNo is matching with RegNo in output file - If Not then Fail this RegNo Test **
                softAssert.assertTrue(false,regNo+ " Vehicle Not Found in Output File..!!");
                SeleniumDriver.navigate(URL);
            }else {
     //** Get the value of car(Make,model etc.) from recordMap and check with actual values from the site **
                Map<String, String> recordMap = recordsMapList.get(regNo.trim());
                String actualCarReg = carCheckDetailsPage.carRegistrationTxt.getText();
                String actualCarMake = carCheckDetailsPage.carMakeTxt.getText();
                String actualCarModel = carCheckDetailsPage.carModel.getText();
                String actualCarColour = carCheckDetailsPage.carColourTxt.getText();
                String actualCarYear = carCheckDetailsPage.carYearTxt.getText();
                softAssert.assertEquals(recordMap.get("REGISTRATION"), actualCarReg, regNo + " car Reg not matching..!");
                softAssert.assertEquals(recordMap.get("MAKE"), actualCarMake, regNo + " car Make not matching..!");
                softAssert.assertEquals(recordMap.get("MODEL"), actualCarModel, regNo + " car Model not matching..!");
                softAssert.assertEquals(recordMap.get("COLOR"), actualCarColour, regNo + " car Color not matching..!");
                softAssert.assertEquals(recordMap.get("YEAR"), actualCarYear, regNo + " car Year not matching..!");
                SeleniumDriver.navigateBack();
            }
      }
     //** Asserting all the soft assertions **
        softAssert.assertAll();


    }


}
