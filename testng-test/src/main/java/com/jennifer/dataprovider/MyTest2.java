package com.jennifer.dataprovider;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class MyTest2 {
	private List<String> testData2= new ArrayList<>();
	
	
	//This method will provide data to any test method that declares that its Data Provider
	//is named "test1"
	@DataProvider(name = "test1")
	public Object[][] createData1() {
	 return new Object[][] {
	   { "Cedric", new Integer(36) },
	   { "Anne", new Integer(37)},
	   { "test", new Integer(37)},
	 };
	}
	
	//This test method declares that its data should be supplied by the Data Provider
	//named "test1"
	@Test(dataProvider = "test1")
	public void verifyData1(String n1, Integer n2) {
	 System.out.println(n1 + " " + n2);

	}
	
	
	
	
	
	@DataProvider(name = "test3")
	public Object[][] createData3() {
	 return new Object[][] {
	   { "Cedric"},
	   { "Anne"},
	   { "test"},
	 };
	}
	@Test(dataProvider = "test3")
	public void verifyData3(String n1) {
	 System.out.println("test3"+n1);

	}
	
	
	@DataProvider(name = "test2")
	public Object[][] createData2() {
		testData2.add("1111");
		testData2.add("1122");
		testData2.add("1133");
		 
		 Object[][] params = new Object[testData2.size()][1];
		 for(int i=0;i<testData2.size();i++){
			 String [] temp = new String[]{testData2.get(i)};
			 params[i] = temp;
		 }		 
		 return params;
	}
	
	@Test(dataProvider = "test2")
	public void verifyData2(String n1) {
	 System.out.println("test2: "+n1);

	}
	 

	
	


}
