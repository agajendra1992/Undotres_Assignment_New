package com.automation.testscript;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.Assert.ThrowingRunnable;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.automation.excelread.Dataprovider;
import com.automation.pagelib.Recharge;
import com.automation.testbase.TestBase;

public class Recharge_TC extends TestBase {

	private static Logger logger = LogManager.getLogger(Recharge_TC.class);

	@Test(dataProvider = "rechargedetails", dataProviderClass = Dataprovider.class)
	public void mobileRecharge(String InputType, String Operator, String MobileNumber, String Amount, String PageVerify,
			String PaymentScreen, String CardNumber, String ExpMonth, String CarExp, String CVV, String Email,
			String Expectedmessage) throws Exception {
		SoftAssert softas = new SoftAssert();
		if (InputType.trim().equalsIgnoreCase("Y")) {

			TestBase.test = report.extent.createTest("Recharge_TC");
			if (new Recharge().RechagrCellularPage().getText().trim().equalsIgnoreCase(PageVerify.trim())) {
				new Recharge().operatorClick();
				test.pass("Operator is clicked");
				new Recharge().operatorselect(Operator);
				test.pass("Operator is Selected" + Operator);
				new Recharge().setMobileNumber(MobileNumber);
				test.pass("Set the Mobile Number as " + MobileNumber);
				new Recharge().amountClick();
				test.pass("Click on Amount Field");
				new Recharge().amountselect(Amount);
				test.pass("Select the Amount as" + Amount);
				new Recharge().nextbtnClick();
				test.pass("Click on Next Button");

				if (new Recharge().PaymentPage().getText().trim().equalsIgnoreCase(PaymentScreen.trim())) {
					new Recharge().paymentTypeClick();
					;
					test.pass("Click on Payment Type ");
					new Recharge().paymentmethodClick();
					Thread.sleep(2000);
					new Recharge().paymentmethodClick();
					test.pass("Click on Payment Method ");
					new Recharge().cardNumber(CardNumber);
					test.pass("Enter the Card Number as " + CardNumber);
					new Recharge().cardexpMonth(ExpMonth);
					test.pass("Enter the Card ExpMonth as " + ExpMonth);
					new Recharge().cardexpyear(CarExp);
					test.pass("Enter the Card ExpYear as " + CarExp);
					new Recharge().cardCVV(CVV);
					test.pass("Enter the CVV as " + CVV);
					Thread.sleep(2000);
					new Recharge().email(Email);
					test.pass("Enter the email as " + Email);
					new Recharge().submitDetails();
					test.pass("Click on Submit");
					new Recharge().userId(P.getProperty("userid"));
					test.pass("Enter the Username");
					new Recharge().password(P.getProperty("password"));
					test.pass("Enter the Password");
					new Recharge().captcha();
					test.pass("Click on Captcha");
					Thread.sleep(2000);
					new Recharge().accessBtn();
					test.pass("Click on Access button");
					Thread.sleep(2000);
					String actualPaymentMessage = new Recharge().verifyPaymentmesage().getText();
					if (actualPaymentMessage.equals(Expectedmessage)) {
						Assert.assertEquals(actualPaymentMessage, Expectedmessage);
						test.pass("Recharge is Successfull");
					} else {
						test.fail("Recharge is not Successfull");
						Assert.assertEquals(actualPaymentMessage, Expectedmessage);
					}
				} else {

					test.fail(PaymentScreen + " page is not displayed");
					boolean paymentscr = new Recharge().PaymentPage().isDisplayed();
					Assert.assertEquals(paymentscr, true);
				}

			} else {
				test.fail(PageVerify + " page is not displayed");
				boolean pageV = new Recharge().RechagrCellularPage().isDisplayed();

				Assert.assertEquals(pageV, true);
			}

		}

		else {
			logger.info("TesCase Not Executed");
			test.skip("Test Case skipped");

		}

	}

}
