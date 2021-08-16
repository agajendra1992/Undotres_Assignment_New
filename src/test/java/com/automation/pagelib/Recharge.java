package com.automation.pagelib;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.automation.testbase.TestBase;

public class Recharge extends TestBase {

	private static Logger logger = LogManager.getLogger(Recharge.class);

	public void operatorClick() throws Exception {
		new TestBase().defaultframe();

		logger.info("Click on Operaton option");
		getWebElement("app.operator").click();
	}

	public void operatorselect(String Operator) throws Exception {
		logger.info("Select the  Operator");
		for (int i = 0; i < getWebElements("app.operatorselect").size(); i++) {
			if (getWebElements("app.operatorselect").get(i).getText().equalsIgnoreCase(Operator)) {
				getWebElements("app.operatorselect").get(i).click();
			}
		}

	}

	public void setMobileNumber(String Name) throws Exception {
		logger.info("Enter the Mobile Number");
		getWebElement("app.mobilenumber").clear();
		getWebElement("app.mobilenumber").sendKeys(Name);
	}

	public void amountClick() throws Exception {
		new TestBase().defaultframe();
		logger.info("Click on Operaton option");

		getWebElement("app.amount").click();

	}

	public void amountselect(String Amount) throws Exception {
		logger.info("Select the  Amount");
		for (int i = 0; i < getWebElements("app.amountselect").size(); i++) {
			if (getWebElements("app.amountselect").get(i).getText().equalsIgnoreCase(Amount)) {
				getWebElements("app.amountselect").get(i).click();
			}
		}

	}

	public void nextbtnClick() throws Exception {
		new TestBase().defaultframe();
		logger.info("Click on Next  Button");
		getWebElement("app.nextbtn").click();

	}

	public void paymentTypeClick() throws Exception {
		new TestBase().defaultframe();
		logger.info("Click on Payment type");
		getWebElement("app.paymenttype").click();

	}

	public void paymentmethodClick() throws Exception {
		new TestBase().defaultframe();
		logger.info("Click on Payment Method");
		getWebElement("app.paymentmethod").click();

	}

	public WebElement RechagrCellularPage() throws Exception {
		new TestBase().defaultframe();
		return getWebElement("app.recharge");
	}

	public WebElement PaymentPage() throws Exception {
		new TestBase().defaultframe();
		return getWebElement("app.paymentscreen");
	}

	public WebElement Cellular() throws Exception {
		new TestBase().defaultframe();
		// waitelement(getWebElement("app.spinner"));
		return getWebElement("app.spinner");
	}

	public void cardNumber(String cardNumber) throws Exception {
		new TestBase().defaultframe();
		logger.info("Enter the Card Number");
		getWebElement("app.cardnumber").sendKeys(cardNumber);
	}

	public void cardexpMonth(String cardexp) throws Exception {
		new TestBase().defaultframe();
		logger.info("Enter the Exp Month");
		getWebElement("app.month").sendKeys(cardexp);
	}

	public void cardexpyear(String expyear) throws Exception {
		 new TestBase().defaultframe();
		logger.info("Enter the Exp Year");
		getWebElement("app.year").sendKeys(expyear);
	}

	public void cardCVV(String cvv) throws Exception {
		new TestBase().defaultframe();
		logger.info("Enter the CVV Num");
		getWebElement("app.cvvno").sendKeys(cvv);
	}

	public void email(String email) throws Exception {
		new TestBase().defaultframe();
		logger.info("Enter the email");
		getWebElement("app.email").sendKeys(email);
	}

	public void submitDetails() throws Exception {
		new TestBase().defaultframe();
		logger.info("Click on Submit details");
		getWebElement("app.submit").click();
	}

	public void userId(String userId) throws Exception {
		new TestBase().defaultframe();
		logger.info("Enter the UserName");
		getWebElement("app.userid").sendKeys(userId);
	}

	public void password(String password) throws Exception {
		new TestBase().defaultframe();
		logger.info("Enter the Password");
		getWebElement("app.password").sendKeys(password);
	}

	public void captcha() throws Exception {
		new TestBase().defaultframe();
		logger.info("Click on Captcha checkobox ");
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.delay(1000);
		robot.keyPress(KeyEvent.VK_SPACE);
		robot.keyRelease(KeyEvent.VK_SPACE);
	}

	public void accessBtn() throws Exception {
		new TestBase().defaultframe();
		logger.info("Click on Access button ");
		getWebElement("app.access").click();
	}

	public WebElement verifyPaymentmesage() throws Exception {
		new TestBase().defaultframe();
		return getWebElement("app.message");
	}

}
