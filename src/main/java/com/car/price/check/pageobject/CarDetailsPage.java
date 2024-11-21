package com.car.price.check.pageobject;

import com.car.price.check.base.WebDriverBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class CarDetailsPage extends WebDriverBase {

    @FindBy(xpath = "/html/body/div[1]/wbac-app/div[1]/div/div/vehicle-questions/div/section[1]/div/div[1]/div/div[3]/div/vehicle-details/div[3]/div[1]/div[2]")
    private WebElement registrationNumber;
    @FindBy(xpath = "//*[@id=\"wbac-app-container\"]/div/div/vehicle-questions/div/section[1]/div/div[1]/div/div[3]/div/vehicle-details/div[3]/div[2]/div[1]/div[2]")
    private WebElement make;
    @FindBy(xpath = "//*[@id=\"wbac-app-container\"]/div/div/vehicle-questions/div/section[1]/div/div[1]/div/div[3]/div/vehicle-details/div[3]/div[2]/div[2]/div[2]")
    private WebElement model;
    @FindBy(xpath = "//*[@id=\"wbac-app-container\"]/div/div/vehicle-questions/div/section[1]/div/div[1]/div/div[3]/div/vehicle-details/div[3]/div[2]/div[3]/div[2]")
    private WebElement year;

    @FindBy(id = "btn-back")
    private WebElement backButton;
    @FindBy(id = "e2e-valueDifferentVehicle")
    private WebElement valueDifferentCar;

    public CarDetailsPage() {
        PageFactory.initElements(new AjaxElementLocatorFactory(webDriver, 60), this);
    }

    public String getCarRegistrationNumber() {
        return registrationNumber.getText();
    }

    public String getCarMake() {
        return make.getText();
    }

    public String getCarModel() {
        return model.getText();
    }

    public String getCarYear() {
        return year.getText();
    }

    public CarExplorePage clickBackButtonToSearchPage() {
        webDriver.navigate().back();
        webDriver.navigate().refresh();
        return new CarExplorePage();
    }

    public Boolean isRegistrationNumberDisplayed() {
        try {
            webDriver.findElement(new By.ByXPath("/html/body/div[1]/wbac-app/div[1]/div/div/vehicle-questions/div/section[1]/div/div[1]/div/div[3]/div/vehicle-details/div[3]/div[1]/div[2]"));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }


}

