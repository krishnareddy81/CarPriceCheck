package com.car.price.check.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class WebDriverBase {
    public  static WebDriver webDriver;
    public  static Properties properties;

    public WebDriverBase()  {
        properties=new Properties();
        try {
            ClassLoader classLoader = WebDriverBase.class.getClassLoader();
            FileInputStream fis = new FileInputStream(new File(classLoader.getResource("config.properties").getFile()).getAbsolutePath());
            properties.load(fis);
        }catch (Exception e){
            e.getMessage();
        }

    }
    public static void openBrowser(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().deleteAllCookies();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        webDriver.get(properties.getProperty("url"));
    }



public static void closeDriver(){
        webDriver.quit();
}

}
