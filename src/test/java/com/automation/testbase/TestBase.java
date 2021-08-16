package com.automation.testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.automation.utils.Utils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TestBase extends Utils {

	public static Properties P = new Properties();
	public File f;
	public FileInputStream FI;
	public static SuiteListener lis = new SuiteListener();
	private static Logger logger = LogManager.getLogger(TestBase.class);
	public static TestBase report = new TestBase();
	public static ExtentReports extent = new ExtentReports();
	public static ExtentSparkReporter htmlreport;
	public static ExtentTest test;
	static Date date = new Date();
	static SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");

//	@Parameters("browser")
	@BeforeTest
	
	public void launch() throws IOException {
		loadproperties();
		logger.info("Loading the report method");
		report.loadreport();
		report.test = report.extent.createTest("Launch Browser and Navigate to Application Url");
		logger.info("Load the Properties");
		selectbrowser(P.getProperty("browser"));
		logger.info("Launch the  " + P.getProperty("browser") + " browser ");
		report.test.info("Launch the  " + P.getProperty("browser") + " browser ");
		driver.get(P.getProperty("url"));
		report.test.info("Open the  " + P.getProperty("url") + " application");
		logger.info("Open the  " + P.getProperty("url") + " application ");
		waitlimit(20);

	}

	public void loadreport() throws IOException {
		loadproperties();
		htmlreport = new ExtentSparkReporter("Reports//Undrotres" + "_" + format.format(date) + ".html");
		htmlreport.config().setTheme(Theme.DARK);
		htmlreport.config().setDocumentTitle("Undotres Automation Report");
		htmlreport.config().setReportName("Automation Report");
		extent.attachReporter(htmlreport);
		extent.setSystemInfo("HOSTNAME", System.getenv().get("COMPUTERNAME"));
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("Browser", P.getProperty("browser").toUpperCase());
		extent.attachReporter(htmlreport);
	}

	public void loadproperties() throws IOException {
		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		f = new File(System.getProperty("user.dir") + "\\src\\test\\java\\com\\automation\\config\\Config.properties");
		FI = new FileInputStream(f);
		P.load(FI);

		f = new File(System.getProperty("user.dir")
				+ "\\src\\test\\java\\com\\automation\\applocator\\Undotreslocator.properties");
		FI = new FileInputStream(f);
		P.load(FI);
	}

	public WebElement getlocator(String locator) throws Exception {
		String locatorkey = locator.split("_")[0];
		String locatorvalue = locator.split("_")[1];

		if (locatorkey.toLowerCase().equals("id"))
			return driver.findElement(By.id(locatorvalue));

		else if (locatorkey.toLowerCase().equals("xpath"))
			return driver.findElement(By.xpath(locatorvalue));

		else if (locatorkey.toLowerCase().equals("name"))
			return driver.findElement(By.xpath(locatorvalue));

		else if ((locatorkey.toLowerCase().equals("classname")) || (locatorkey.toLowerCase().equals("class")))
			return driver.findElement(By.className(locatorvalue));

		else if ((locatorkey.toLowerCase().equals("tagname")) || (locatorkey.toLowerCase().equals("tag")))
			return driver.findElement(By.className(locatorvalue));
		else if ((locatorkey.toLowerCase().equals("linktext")) || (locatorkey.toLowerCase().equals("link")))
			return driver.findElement(By.linkText(locatorvalue));
		else if (locatorkey.toLowerCase().equals("partiallinktext"))
			return driver.findElement(By.partialLinkText(locatorvalue));
		else if ((locatorkey.toLowerCase().equals("cssselector")) || (locatorkey.toLowerCase().equals("css")))
			return driver.findElement(By.cssSelector(locatorvalue));

		else
			throw new Exception("ELEMENT" + locatorkey);
	}

	public static List<WebElement> getLocators(String locator) throws Exception {
		String locatorkey = locator.split("_")[0];
		String locatorValue = locator.split("_")[1];

		if (locatorkey.toLowerCase().equals("id"))
			return driver.findElements(By.id(locatorValue));
		else if (locatorkey.toLowerCase().equals("name"))
			return driver.findElements(By.name(locatorValue));
		else if ((locatorkey.toLowerCase().equals("classname")) || (locatorkey.toLowerCase().equals("class")))
			return driver.findElements(By.className(locatorValue));
		else if ((locatorkey.toLowerCase().equals("tagname")) || (locatorkey.toLowerCase().equals("tag")))
			return driver.findElements(By.className(locatorValue));
		else if ((locatorkey.toLowerCase().equals("linktext")) || (locatorkey.toLowerCase().equals("link")))
			return driver.findElements(By.linkText(locatorValue));
		else if (locatorkey.toLowerCase().equals("partiallinktext"))
			return driver.findElements(By.partialLinkText(locatorValue));
		else if ((locatorkey.toLowerCase().equals("cssselector")) || (locatorkey.toLowerCase().equals("css")))
			return driver.findElements(By.cssSelector(locatorValue));
		else if (locatorkey.toLowerCase().equals("xpath"))
			return driver.findElements(By.xpath(locatorValue));
		else
			throw new Exception("Unknown locator type '" + locatorkey + "'");

	}

	public WebElement getWebElement(String locator) throws Exception {
		return getlocator(P.getProperty(locator));
	}

	public List<WebElement> getWebElements(String locator) throws Exception {
		return getLocators(P.getProperty(locator));
	}

	public Actions getact() {
		Actions action = new Actions(driver);
		return action;
	}

	public Select getselect(WebElement element) {

		Select select = new Select(element);
		return select;
	}

	public void killbrowserprocess() throws IOException {
		Runtime runtime = Runtime.getRuntime();
		runtime.exec("taskkill /im chromedriver.exe /f");
		runtime.exec("taskkill /im geckodriver.exe /f");
	}
	@AfterSuite
	public void teardown() throws IOException {
	tearbrowser();
		report.extent.flush();
		killbrowserprocess();
		

	}

}
