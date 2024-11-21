package com.car.price.check.pageobject;

import com.car.price.check.base.WebDriverBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class CarExplorePage extends WebDriverBase {

    @FindBy(id = "onetrust-accept-btn-handler")
    private WebElement acceptCookies;
    @FindBy(id = "vehicleReg")
    private WebElement vechileRegistrationNumber;
    @FindBy(id = "Mileage")
    private WebElement mileage;
    @FindBy(id = "btn-go")
    private WebElement valuationButton;
    @FindBy(id = "btn-back")
    private WebElement backButton;
    @FindBy(id = "e2e-valueDifferentVehicle")
    private WebElement valueDifferentCar;
    @FindBy(xpath = "//*[@id=\"wbac-app-container\"]/div/div/vehicle-registration-check/section[1]/div/div[1]/div/div[1]/h1")
    private WebElement errorDetails;

    public CarExplorePage() {
        PageFactory.initElements(new AjaxElementLocatorFactory(webDriver, 60), this);
    }

    public void clickAcceptLink() {
        acceptCookies.click();
    }

    public CarDetailsPage sendRegistrationNumAndRandomMileage(String number) {
        WebDriverWait wait = new WebDriverWait(webDriver, 20);
        wait.until(ExpectedConditions.visibilityOf(mileage));
        vechileRegistrationNumber.sendKeys(number);
        mileage.sendKeys(String.valueOf(new Random().nextInt(10000)));
        valuationButton.click();
        return new CarDetailsPage();
    }

}
