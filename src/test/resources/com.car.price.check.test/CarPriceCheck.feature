@carPriceCheck
Feature: Car Price Check

  Scenario Outline: Car price check - Read input file and verify the details with output file
    Given I have read the input text data file <input_data_file>
    When I go to the website and get the car price details
    Then I validate the data from output file <output_data_file>

    Examples:
      | input_data_file  | output_data_file  |
      | car_input_V4.txt | car_output_V4.txt |
