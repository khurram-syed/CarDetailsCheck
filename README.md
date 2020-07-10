
PROJECT CONFIGURATION :
    
    - The Porject has been developed With following Java dependencies/tools
      1- Cucumber-TestNG 4.8.0
      2- Selenium WebDriver 3.14.0
      3- Log4j 1.2.17

RUN OPTIONS:

1- It can be run with commands accordingly. 


 
 (i) Run tests with Chrome. It can be changed to 'firefox' 
   > mvn clean test
 
  or
   
   > mvn clean test -Dcucumber.options="--tags @SearchCarDetails"
  
 Note: Chrome Driver for Windows has been provided at src/main/resources/drivers but please check the compatibility 
       with your chrome browser version accordingly.

REPORTING & LOGGING :   

 - TestNG HTML Reports will be generated automatically at following location with above command execution. 
  Screenshots gets embedded in the report but due to Soft Assertions their accuracy is not certain as it is captured
  in the after test hooks .
 
  > Report Locaiton : <root>/reports/htlm/cucumber.html
 
 - Logs are recorded at following location
   
   > Logs Locaiton : <root>/logs/logging.log
  
TASK EXPLANATION:

TASK : To extract the Car Registrations info from "car_input.txt" and then each reg number will be fed into http://cartaxcheck.co.uk/
         And check if each regNo get failed/passed according to matching results in output file.
 
  - Feature File : "SearchCarDetails.feature" has been implemented the above scenario at following location
   
    > src/test/resources/features/SearchCarDetails.feature
  
  - POM (Page Object Model) Files : They have been implemented through Page Object Model. All the Page Objects are located at 
   >  src/main/java/com/cardetailcheck/pages
  
  - Step Definition : Feature file implementation is located at following location along with hooks

  >  src/test/java/com/cardetailcheck/steps

Test Fail/Pass Status : While Running through command line you'll see number of failures clear description in "Then" step
   on console along with after following "AssertionError" description.

  > java.lang.AssertionError: The following asserts failed:
  >> BW57BOW Vehicle Not Found on Site..!! expected [true] but found [false]
