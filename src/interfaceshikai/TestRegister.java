package interfaceshikai;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import unittest.*;





@RunWith(Parameterized.class )
public class TestRegister
{


    private String phone;
    private String password;
    private String securitycode;
    private String state;
    private Map testmap=new HashMap();
    


   @Parameters
    public   static  Collection data() throws BiffException, IOException
   {
	   //ParamXml testdata=new ParamXml();
	   
	   jxl.Workbook readwb = null;

		InputStream instream = new FileInputStream("/data/userregister.xls");
		readwb = Workbook.getWorkbook(instream);
		Sheet readsheet = readwb.getSheet(0);
		int rsColumns = readsheet.getColumns();
		int rsRows = readsheet.getRows();
		Object[][] test = new Object[rsRows-1][rsColumns];
		for (int i = 1; i < rsRows; i++) {
			for (int j = 0; j < rsColumns; j++) {
				Cell cell = readsheet.getCell(j, i);
				test[i-1][j] = cell.getContents();
				/*
				if (j == 0)
				{
					test[i-1][j] = Long.parseLong(cell.getContents());
					System.out.print(test[i-1][j]);
				}
				else if(j == 2)
					test[i-1][j] = Integer.parseInt(cell.getContents());
				else
					test[i-1][j] = cell.getContents();
					*/
			}
			
		}
		readwb.close();
		
		return Arrays.asList(test);
   } 
    
   // 构造函数，对变量进行初始化 
   public  TestRegister(String phone,String password,String securitycode)  {
       this.phone  =  phone;
       this.password=password;
       this.securitycode=securitycode;
  } 
   
   
	@Before
	public void setUp() throws Exception
	{
	}

	@After
	public void tearDown() throws Exception
	{
	}



}

