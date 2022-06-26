package com.esprow.step_definitions;

import com.esprow.managers.Driver;
import com.esprow.utilities.ConfigurationReader;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

public class Hook {

    @Before
    public void setup(){
        Driver.getDriver().manage().window().maximize();
    }

    @After
    public void teardown(Scenario scenario){

        if(scenario.isFailed()){
            Allure.addAttachment("ScreenShot", new ByteArrayInputStream((((TakesScreenshot)Driver.getDriver()).getScreenshotAs(OutputType.BYTES))));
        }
       Driver.close();
    }
}