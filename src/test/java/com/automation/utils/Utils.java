package com.automation.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;

import com.automation.testbase.TestBase;

public class Utils {

	public static WebDriver driver;
	

	public static WebDriver selectbrowser(String browser) {

		if (browser.equalsIgnoreCase("firefox") || (browser.equalsIgnoreCase("FIREFOX"))) {
			System.setProperty("webdriver.gecko.driver", TestBase.P.getProperty("firefoxbrowserpath"));
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			return driver;

		} else if (browser.equalsIgnoreCase("chrome") || (browser.equalsIgnoreCase("CHROME"))) {

			System.setProperty("webdriver.chrome.driver", TestBase.P.getProperty("chromebrowserpath"));
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}

		return driver;
	}

	public void waitlimit(int time) {
		try {
			driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void closebrowser() {
		try {
			driver.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public WebDriverWait waitelement(WebElement wb) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(wb));

		return wait;
	}

	public WebDriverWait waitelementnot(WebElement wb) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.invisibilityOf(wb));

		return wait;
	}
	public void takeScreenshot(String name) {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {

			org.apache.commons.io.FileUtils.copyFile(src, new File(	System.getProperty("user.dir") + "\\src\\test\\java\\screenshot\\" + name + System.currentTimeMillis() + ".jpg"));
			
		}

		catch (IOException e) {

		}

	}

	public void defaultframe() {
		try {
			driver.switchTo().defaultContent();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void frame(String name) {
		try {
			driver.switchTo().frame(name);
		} catch (Exception e) {

		}
	}

	public void tearbrowser() {
		try {
			driver.quit();
		} catch (Exception e) {

		}
	}

}