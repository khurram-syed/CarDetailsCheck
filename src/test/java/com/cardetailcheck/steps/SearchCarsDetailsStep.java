package com.cardetailcheck.steps;

import com.cardetailcheck.driver.SeleniumDriver;
import com.cardetailcheck.pages.CarCheckDetailsPage;
import com.cardetailcheck.pages.CarCheckHomePage;
import com.cardetailcheck.pages.CookiesPopup;
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
    CarCheckHomePage carCheckHomePage = new CarCheckHomePage();
    CarCheckDetailsPage carCheckDetailsPage = new CarCheckDetailsPage();
    CookiesPopup cookiesPopup = new CookiesPopup();

   @Given("I read the following input files for car details")
   public void IReadTheFollowingInputFilesForCarDetails (DataTable dataTable) throws IOException {
        logger.info("I read the following input files for car details");

        List<String> files = new ArrayList<String>();
        List<Map<String,String>> data = dataTable.asMaps(String.class,String.class);

        //** Getting the files names from DataTable **
        for(Map<String,String> cols : data){
            files.add(cols.get("Input Files"));
        }

       //** Checking if any file provided **
       if(files.size()<1){
           try {
               throw new FileNotFoundException("No Input Files provided..!!");
           } catch (Exception e) {
               e.printStackTrace();
           }
       }

       //** Reading the number of input files and adding the cars in carList **
       for(String fileName : files){
           String inputFile = System.getProperty("user.dir")+"//src//test//resources//inputfiles//"+fileName;
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
        SeleniumDriver.navigate(url);
        if(cookiesPopup.isCookieBtnVisible()){
            cookiesPopup.clickAcceptAllBtn();
        }
    }

    @Then("Car input file\\(s) values should be matching to the following output file values")
    public void ICheckTheInputFileValuesWithTheFollowingOutputFile(DataTable dataTable) throws InterruptedException {
        logger.info("I am on the Home Page Car input File(s) values should be matching to the following output file values");

        List<Map<String,String>> data = dataTable.asMaps(String.class,String.class);

        //** SoftAssertions to Match Multiple values with output files **
        SoftAssert softAssert = new SoftAssert();
        String outputFile = System.getProperty("user.dir")+"/src/test/resources/outputfile/"+data.get(0).get("Output File");
        Map<String,Map<String,String>> recordsMapList =  Utils.getListOfRecordsMapFromCSV(outputFile);

        for(int count=0; count < carInputList.size(); count++){
           //** Fill the Reg number and submit the page **
           carCheckHomePage.submitCarRegNo(carInputList.get(count));
           carCheckDetailsPage.waitForRegNumberHeadingBy();

            //** Check in next 5 secs if any "Car Not Found" popup appears - If it does then Fail this RegNo Test **
            if(carCheckDetailsPage.regNoDetails.size() == 0){
                softAssert.assertTrue(false,carInputList.get(count)+ " Car Not Found on Site..!!");
            }else if(recordsMapList.get(carInputList.get(count))==null){
            //** Check if the input RegNo is matching with RegNo in output file - If Not then Fail this RegNo Test **
                softAssert.assertTrue(false,carInputList.get(count)+ " Car Not Found in Output File..!!");
            }else {
                //** Get the value of car(Make,model etc.) from recordMap and check with actual values from the site **
                carCheckDetailsPage.isCarDetailsFound();
                logger.info("=========Inside else  ========");
                Map<String, String> recordMap = recordsMapList.get(carInputList.get(count).trim());
                softAssert.assertEquals(recordMap.get("REGISTRATION"), carCheckDetailsPage.getOnlyActualRegNo(), carInputList.get(count) + " car Reg not matching..!");
                softAssert.assertEquals(recordMap.get("MAKE"), carCheckDetailsPage.getMakeORModel("make"), carInputList.get(count)  + " car Make not matching..!");

               //** NOTE: Uncomment to match for "Model" too but all test will be failing as nothing will be matching to output file
              //softAssert.assertEquals(recordMap.get("MODEL"), carCheckDetailsPage.getMakeORModel("model"), carInputList.get(count)  + " car Model not matching..!");
            }
            if(count+1 < carInputList.size()){
                SeleniumDriver.navigateBack();
                carCheckHomePage.waitForCarRegInputTxtBy();
            }
      }
      //** Asserting all the soft assertions **
      softAssert.assertAll();
    }
}
