
PROJECT CONFIGURATION :
    
    - The Porject has been developed With following Java dependencies/tools
      1- Cucumber-TestNG 4.8.0
      2- Selenium WebDriver 3.14.0
      3- Log4j 1.2.17

RUN OPTIONS:

1- It can be run with commands accordingly. 
Note: Chrome Driver for Windows are provided at src/main/resources/drivers 

 
 (i) Run tests with Chrome. It can be changed to 'firefox' 
   > mvn clean test
 
  or
   
   > mvn clean test -Dcucumber.options="--tags @SearchCarDetails"
  
 

REPORTING :   

 - TestNG HTML Reports will be generated automatically at "reports/htlm/cucumber.html" with above command execution. 
  Screenshots are embedded in the report but due to Soft Assertions they can't be accurately captured.
 
  > Report Locaiton : <root>/reports/htlm/cucumber.html
   
  
TASK EXPLANATION:

TASK 1 : To extract the Car Registrations info from "car_input.txt" and then each number will be fed into http://cartaxcheck.co.uk/
         And check if tests get failed/passed according to matching results in output file.
 
  - "SearchCarDetails.feature" has been implemented the above scenario
  - They have been implemented through Page Object Model. All the Page Objects are located at 
  --  src/main/java/com.cardetailcheck.pages



  
