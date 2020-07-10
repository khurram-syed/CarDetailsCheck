@SearchCarDetails
Feature: Search car details Checks
    As a car check site user, I want to validate that the search car details page is working fine so that I can use it without any issue.

  @searhCars1
  Scenario: 1- ShorterVersion - Validate search cars page
    Given I read the following input files for car details
      | Input Files     |
      | car_input.txt   |
##    | car_input_2.txt | ==> Left the provision for adding more input files
    When I navigate to the Home Page "https://cartaxcheck.co.uk/"
    Then Car input file(s) values should be matching to the following output file values
      | Output File    |
      | car_output.txt |

################ Reasoning behind SoftAssertions and Note for Adding more input Files #############
## Note#1 : Two files (one "car_input" and second "car_output") were given.
##          So I have to use soft assertions as multiple values are being checked in last step.

##Note#2 : As I have commented out second file in Given step, but can be uncommented to include it
##         or more input files.