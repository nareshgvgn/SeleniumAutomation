package com.gcp.keyword;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class CreateSmartGridListVariable {

	public static List<String> execute(WebDriver driver, String inputData) throws Exception
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		InputStream in = VerifySmartGridText.class.getClassLoader()
                .getResourceAsStream("createSmartGridListVariable.txt");
		Scanner sc = new Scanner(in);
		String inject = ""; 
		    while (sc.hasNext()) {          
		        String[] s = sc.next().split("\r\n");   
		        for (int i = 0; i < s.length; i++) {
		            inject += s[i];
		            inject += " ";
		        }           
		    } 
		    inject = inject.replace(":COLUMN_LABEL", inputData);
		    return (List<String>) jse.executeScript(inject);

	}

}
