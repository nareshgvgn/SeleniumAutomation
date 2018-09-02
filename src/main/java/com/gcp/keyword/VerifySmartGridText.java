package com.gcp.keyword;

import java.io.InputStream;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class VerifySmartGridText {
	
	public static Boolean execute(WebDriver driver, String inputData) throws Exception
	{
		boolean returnValue = true;
		StringTokenizer stk = new StringTokenizer(inputData,",");
		while(stk.hasMoreTokens())
		{
			String token = stk.nextToken();
			StringTokenizer inner = new StringTokenizer(token,":");
			String columnLabel = inner.nextToken();
			String columnValue = inner.nextToken();
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			
			InputStream in = VerifySmartGridText.class.getClassLoader()
                    .getResourceAsStream("GCP_JS_TEMPLATES/verifySmartGridColumnValue.txt");
			Scanner sc = new Scanner(in);
			String inject = ""; 
			    while (sc.hasNext()) {          
			        String[] s = sc.next().split("\r\n");   
			        for (int i = 0; i < s.length; i++) {
			            inject += s[i];
			            inject += " ";
			        }           
			    } 
			    inject = inject.replace(":COLUMN_LABEL", columnLabel);
			    inject = inject.replace(":COLUMN_VALUE", columnValue);
			    returnValue = returnValue && (Boolean) jse.executeScript(inject);
		}
		return returnValue;
	}
}
