package com.automation.testbase;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class SuiteListener extends TestBase implements ITestListener {
	private static Logger logger = LogManager.getLogger(SuiteListener.class);

	public static ExtentSparkReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	SimpleDateFormat date = new SimpleDateFormat("ddMMYYY");
	

	public void onTestStart(ITestResult result) {
		logger.info("Test Case Started::  " + result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		logger.info(result.getMethod().getMethodName()+" Test Case is Passed");
	}

	public void onTestFailure(ITestResult result) {
		logger.info(result.getMethod().getMethodName()+" Test Case is Failed");
		new TestBase().takeScreenshot(result.getMethod().getMethodName());

	}

	public void onTestSkipped(ITestResult result) {
		logger.info(result.getMethod().getMethodName()+" Test Case is Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		logger.info(result.getMethod().getMethodName()+" Test Case is Failed With Percentage");
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		logger.info(result.getMethod().getMethodName()+" Test Case is Failed due to timeout");
	}

	public void onStart(ITestContext context) {
		logger.info("Test Execution Started on =========================>>>>" + context.getStartDate());
	}

	public void onFinish(ITestContext context) {
		logger.info("Test Execution Ended on =========================>>>>" + context.getEndDate());

	}

}