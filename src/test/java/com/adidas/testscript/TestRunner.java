package com.adidas.testscript;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"resources"},
        glue = {"classpath:com/adidas/stepdefs"}
)
public class TestRunner {

}