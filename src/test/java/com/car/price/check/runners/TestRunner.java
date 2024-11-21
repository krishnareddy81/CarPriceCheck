package com.car.price.check.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {
                "target/test-classes"},
        plugin = {
                "pretty", "html:target/CarPriceCheck-Test-Report",
                "json:target/CarPriceCheck-Test-Report/cucumber.json",
                "rerun:target/CarPriceCheck-Test-Report/rerun.txt"},
        tags = "@carPriceCheck",
        glue = {"com/car/price/check/stepdefinations/"})
public class TestRunner {
}
