package com.weborders.step_definitions;

import com.weborders.utilities.ConfigurationReader;
import com.weborders.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    @Before                   // before cucumber
    public void setup(Scenario scenario){
        System.out.printf("Starting test automation");
        System.out.println("Browser type :: "+ ConfigurationReader.getProperty("browser"));
        System.out.println("Environment :: " + ConfigurationReader.getProperty("url"));
        System.out.println("Test scenario :: "+ scenario.getName());
        Driver.getDriver().manage().window().maximize();
    }

    @After  // imported from cucumber not selenium
    public void tearDown(Scenario scenario){
        if(scenario.isFailed() ){
            TakesScreenshot takesScreenshot = (TakesScreenshot) Driver.getDriver();
            byte[] image = takesScreenshot.getScreenshotAs(OutputType.BYTES);
            scenario.embed(image, "image/png", scenario.getName());
        }
        Driver.closeDriver();
    }
}
