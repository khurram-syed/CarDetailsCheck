package com.cardetailcheck;



import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/resources/features"},
        plugin = {"pretty","json:target/positive/cucumber.json",
                  "html:reports/htlm/cucumber.html",
                   },
         glue = {"com.cardetailcheck.steps"}
        ,tags="@searhCars1"
)
public class TestRunner extends AbstractTestNGCucumberTests {

}


