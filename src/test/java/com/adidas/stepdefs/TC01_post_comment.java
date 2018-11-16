package com.adidas.stepdefs;

import java.io.IOException;
import com.adidas.common.*;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class TC01_post_comment {
	public static JSONObject comment_data,config_data,obj_data;
	WebDriver driver;
	public static JSONObject commentobj,configobj,objrepoobj;
	private Logger logger = Logger.getLogger(TC01_post_comment.class);
	@Given("^Open website and enter url$")
    public void get_weather_data() throws IOException, InterruptedException 
	{
		configobj = commonmethods.readJSON("\\src\\test\\java\\com\\adidas\\data\\configuration.json");
	    config_data = (JSONObject) configobj.get("config");
	    logger.info("config data from json read successfully.");
	    
		commentobj = commonmethods.readJSON(config_data.getString("comment_data"));
	    comment_data = (JSONObject) commentobj.get("comment_data");
	    logger.info("comment data from json read successfully.");
	    
	    objrepoobj = commonmethods.readJSON(config_data.getString("object_repository"));
	    obj_data = (JSONObject) objrepoobj.get("object_repository");
	    logger.info("object repository data from json read successfully.");
	    
		driver = commonmethods.openBrowser(driver,config_data.getString("browser"));
		logger.info("browser launch = "+config_data.getString("browser"));
		driver.get(config_data.getString("url"));
		logger.info("url entered = "+config_data.getString("url"));
		driver.manage().window().maximize();
		logger.info("browser maximize successfully");
    }
	@When("^enter invalid email and post comment$")
    public void enter_invalid_email() throws IOException, InterruptedException 
	{
		driver.findElement(By.xpath(obj_data.getString("sample_page"))).click();
		driver = commonmethods.postComment(driver,comment_data.getString("comment"),comment_data.getString("auther"),"",comment_data.getString("url"));
		logger.info("entered comment with invalid email id");
	}
	@Then("^verify incorrect email error message$")
    public void verify_email_message() throws IOException, InterruptedException 
	{
		//This will compare email error message with UI.
		Assert.assertEquals(driver.findElement(By.xpath(obj_data.getString("email_error_msg"))).getText(),"ERROR: please enter a valid email address.");
		logger.info("email error message displayed = "+driver.findElement(By.xpath(obj_data.getString("email_error_msg"))).getText());
		driver.findElement(By.xpath(obj_data.getString("back"))).click();
		logger.info("back button clicked successfully.");
	}
	@When("^enter valid comment data and post comment$")
    public void post_valid_comment() throws IOException, InterruptedException 
	{
		driver = commonmethods.postComment(driver,comment_data.getString("comment"),comment_data.getString("auther"),comment_data.getString("email"),comment_data.getString("url"));
		logger.info("enter comment with valid data.");
	}
	@Then("^verify post comment message$")
    public void verify_comment_message() throws IOException, InterruptedException 
	{
		//This will compare comment post message with UI
		Assert.assertTrue(driver.findElement(By.xpath(obj_data.getString("comment_msg"))).isDisplayed());
		logger.info("comment submit message displayed = "+driver.findElement(By.xpath(obj_data.getString("comment_msg"))).isDisplayed());
		driver.quit();
		driver=null;
		logger.info("browser closed successfully = "+config_data.getString("browser"));
	}
}