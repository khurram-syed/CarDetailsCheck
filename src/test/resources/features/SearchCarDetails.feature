@SearchCarDetails
Feature: Search car details Checks
    As a car check site user, I want to validate that the search car details page is working fine
    so that I can search for my car confidently.

  @searhCars1
  Scenario: 1- Check if cars registration given in input file are valid and matches to ouput file info
    Given I read the following input files for car details
      | Input Files      |
      | car_input_v1.txt |
   ## | car_input_v2.txt |  ##==> Left the provision for adding more input files
    When I navigate to the Home Page "https://www.cazoo.co.uk/value-my-car/"
    Then Car input file(s) values should be matching to the following output file values
      | Output File       |
      | car_output_v1.txt |

################ Reasoning behind SoftAssertions and Note for Adding more input Files #############
## Note#1 : Two files (one "car_input_v1" and second "car_output_v1") were given.
##          So I have to use soft assertions as multiple values are being checked in last step.

##Note#2 : As I have commented out second file in Given step, but can be uncommented to include it
##         or momore input files cna be added.