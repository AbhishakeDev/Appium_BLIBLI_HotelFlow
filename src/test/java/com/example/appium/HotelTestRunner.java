package com.example.appium;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="src/test/java/com/example/appium/features",
        glue={"com.example.appium.stepDefinition"},
        plugin = {"pretty", "html:target/cucumber-reports.html"})
public class HotelTestRunner {
}
