package com.gcp.keyword;

import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

import net.sf.json.JSONObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class SelectExtAutocompleterValue
{
	public static void execute(WebDriver driver, String inputData)
			throws Exception
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		InputStream in = VerifySmartGridText.class.getClassLoader()
				.getResourceAsStream(
						"/GCP_JS_TEMPLATES/SelectExtAutoCompleter.txt");
		String label = null;
		String value = null;
		StringTokenizer stk = new StringTokenizer(inputData, ",");
		while (stk.hasMoreTokens())
		{
			String token = stk.nextToken();
			StringTokenizer inner = new StringTokenizer(token, ":");
			label = inner.nextToken();
			value = inner.nextToken();
		}

		Scanner sc = new Scanner(in);
		String inject = "";
		while (sc.hasNext())
		{
			String[] s = sc.next().split("\r\n");
			for (int i = 0; i < s.length; i++)
			{
				inject += s[i];
				inject += " ";
			}
		}
		inject = inject.replace(":AUTOCOMPLETER_LBL", label);
		inject = inject.replace(":AUTOCOMPLETER_VALUE", value);
		Object result = jse.executeScript(inject);
		Thread.sleep(1000);
		Map<String, Object> rsultMap = (Map<String, Object>) result;
		if (rsultMap.get("status").equals("ERROR"))
			throw new Exception((String) rsultMap.get("message"));
	}
}
