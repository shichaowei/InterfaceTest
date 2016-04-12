package interfaceshikai;


import org.testng.annotations.Test;

import unittest.AesEncrypt;
import unittest.ExcelDataProvider;
import unittest.HttpRequest;
import unittest.Map2Json;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestRegisterr {
	
	  @Test(dataProvider = "dp")
	  public void TestLogin(Map<String,String> data) {
		  String datajson=Map2Json.mapToJson(data);
		  System.out.println(datajson);
		  byte[] encryptdata=AesEncrypt.encrypt(datajson, "ssss");
		  System.out.println(encryptdata);
		  String postdata=new String(encryptdata);
		  System.out.println(postdata);
		  HttpRequest.sendPost("http://192.168.1.1:8080/WebServer/user/register", postdata);
	  }

	  @DataProvider(name = "dp")
	  public Iterator<Object[]> dataFortestMethod(Method method) throws IOException {
	      return new ExcelDataProvider("app/login",method.getName());
	  }
	  
	  @BeforeClass
	  public void beforeClass() {
		  
	  }

	  @AfterClass(alwaysRun = true)
	  public void afterClass() {
	  }

	  @BeforeSuite
	  public void beforeSuite() {
	  }

	  @AfterSuite
	  public void afterSuite() {
	  }
	

}
