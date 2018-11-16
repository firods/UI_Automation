package com.adidas.common;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.adidas.stepdefs.TC01_post_comment;

public class commonmethods {
	public static JSONObject obj_data,objrepoobj;
	public static Random rand = new Random();

	//This method will post comment with provided parameters.
	public static WebDriver postComment(WebDriver driver ,String comment,String auther,String email,String url) throws IOException {
	
		//This will read object repository json file.
		objrepoobj = commonmethods.readJSON(TC01_post_comment.config_data.getString("object_repository"));
	    obj_data = (JSONObject) objrepoobj.get("object_repository");
		driver.findElement(By.xpath(obj_data.getString("comment"))).clear();
		//added random number to comment so comment will be unique each time.
		driver.findElement(By.xpath(obj_data.getString("comment"))).sendKeys(comment+rand.nextInt(500) + 1);
		driver.findElement(By.xpath(obj_data.getString("auther"))).clear();
		driver.findElement(By.xpath(obj_data.getString("auther"))).sendKeys(auther);
		driver.findElement(By.xpath(obj_data.getString("email"))).clear();
		driver.findElement(By.xpath(obj_data.getString("email"))).sendKeys(email);
		driver.findElement(By.xpath(obj_data.getString("url"))).clear();
		driver.findElement(By.xpath(obj_data.getString("url"))).sendKeys(url);
		driver.findElement(By.xpath(obj_data.getString("post_comment_btn"))).click();
		return driver;
	}
	
	//This method will open browser according to browser name provided
	public static WebDriver openBrowser(WebDriver driver,String browsername) {
		
		if(browsername.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+TC01_post_comment.config_data.getString("chrome_exe"));
			driver = new ChromeDriver();
		}
		return driver;
	}
	//This method will read json file depends on path
	public static JSONObject readJSON(String path) throws IOException
	{
		File file = new File(System.getProperty("user.dir")+path);
	    String content = FileUtils.readFileToString(file, "utf-8");
	    
	    // Convert JSON string to JSONObject
	    JSONObject Obj = new JSONObject(content);
	    return Obj;
	}
}