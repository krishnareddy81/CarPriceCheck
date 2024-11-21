package com.car.price.check.stepdefinations;

import com.car.price.check.base.WebDriverBase;
import com.car.price.check.pageobject.CarDetailsPage;
import com.car.price.check.pageobject.CarExplorePage;
import com.car.price.check.util.FileReadUtil;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class CarPriceCheckStepDef extends WebDriverBase {

    private List<String> carRegistrationNumbers;
    private CarExplorePage carExplorePage;
    private CarDetailsPage carDetailsPage;
    private Map<String, List<String>> actualCarDetailsMap;

    @Before
    public void setup() {
        WebDriverBase.openBrowser();
    }

    @Given("I have read the input text data file (.*)")
    public void i_have_read_the_input_text_data_file_car_input_txt(String inputDataFileName) throws IOException {
        carRegistrationNumbers = FileReadUtil.getCarRegNumbers(inputDataFileName);

    }

    @When("I go to the website and get the car price details")
    public void i_go_to_the_website_and_get_the_car_price_details() {
        actualCarDetailsMap = new HashMap<String, List<String>>();
        carExplorePage = new CarExplorePage();
        carExplorePage.clickAcceptLink();
        carDetailsPage = new CarDetailsPage();
        for (String carRegistrationNumber : carRegistrationNumbers) {
            List<String> actualCarDetailsList = new ArrayList<String>();
            carDetailsPage = carExplorePage.sendRegistrationNumAndRandomMileage(carRegistrationNumber);
            if (carDetailsPage.isRegistrationNumberDisplayed()) {
                actualCarDetailsList.add(carDetailsPage.getCarRegistrationNumber());
                actualCarDetailsList.add(carDetailsPage.getCarMake());
                actualCarDetailsList.add(carDetailsPage.getCarModel());
                actualCarDetailsList.add(carDetailsPage.getCarYear());
                actualCarDetailsMap.put(carRegistrationNumber, actualCarDetailsList);
                carExplorePage = carDetailsPage.clickBackButtonToSearchPage();
            } else {
                System.out.println("Entered Car registration details not found :" + carRegistrationNumber);
                carExplorePage = carDetailsPage.clickBackButtonToSearchPage();
            }

        }
    }


    @Then("I validate the data from output file (.*)")
    public void i_validate_the_data_from_output_file_car_output_txt(String outputDataFileName) throws IOException {
        Map<String, List<String>> expectedCarDetailMap = FileReadUtil.getOutputDataDetails(outputDataFileName);
        System.out.println("Expected Car Price Details map size :" + expectedCarDetailMap.size());
        for (Map.Entry<String, List<String>> entry : expectedCarDetailMap.entrySet()) {
            System.out.println("Expected car price details : " + entry.getKey() + ", Value : " + entry.getValue());

        }
        System.out.println("Actual Car Details map size :" + actualCarDetailsMap.size());
        for (Map.Entry<String, List<String>> entry : actualCarDetailsMap.entrySet()) {
            System.out.println("Actual car price details : " + entry.getKey() + ", Value : " + entry.getValue());

        }


        assertEquals("Car price input details not matched with output car price details", expectedCarDetailMap, actualCarDetailsMap);

    }

    @After
    public void tearDown() {
        WebDriverBase.closeDriver();
    }

}

