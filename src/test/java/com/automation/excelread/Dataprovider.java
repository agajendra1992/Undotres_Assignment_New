package com.automation.excelread;
import org.testng.annotations.DataProvider;

	public class Dataprovider {

	@DataProvider
	public static Object[][]rechargedetails() throws Exception{
		Object[][] readdata =Excelread.getdata(Excelread.path,Excelread.Sheet); 
		return (readdata);
	}




	}


