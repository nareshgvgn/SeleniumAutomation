package com.selenium.util;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.common.keyword.JQueryMultiselectCheckAll;
import com.gcp.keyword.CreateSmartGridListVariable;
import com.gcp.keyword.SelectExtAutocompleterValue;
import com.gcp.keyword.VerifySmartGridText;
import com.selenium.bean.KeywordBean;
import com.selenium.bean.Keyword;
import com.selenium.dao.KeywordExecuterDao;

public class KeywordExecuter {
	WebDriver driver;

	KeywordExecuterDao keywordExecuterDao;

	public KeywordExecuter(WebDriver driver,
			KeywordExecuterDao keywordExecuterDao) {
		this.driver = driver;
		this.keywordExecuterDao = keywordExecuterDao;
	}

	public void perform(Properties p, Map<String,Object> valueMap,String keyword, String objectName,
			String objectType, String value) throws Exception {
		Keyword opr = Keyword.valueOfKeyword(keyword);
		if(null != value)
		value = resolveValue(value, valueMap);
		switch (opr) {
		case CLICK:
			driver.findElement(this.getObject(valueMap, objectName, objectType))
					.click();
			break;
			
		case SETTEXT:
			driver.findElement(this.getObject(valueMap, objectName, objectType))
					.sendKeys(value);
			break;
			
		case GOTOURL:
			driver.get(value);
			
			break;
		case GETTEXT:
			driver.findElement(this.getObject(valueMap, objectName, objectType))
					.getText();
			break;
			
		case VERIFY_TEXT :
			String textToVerify = value;
			if(textToVerify.startsWith("${")){
				textToVerify = p.getProperty(textToVerify);
			}
			if(!driver.findElement(this.getObject(valueMap, objectName, objectType)).getText().equals(textToVerify))
				throw new Exception("Unable to verify the Texts");
			break;
			
		case WAITFORPAGELOAD:
			ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor) driver)
							.executeScript("return document.readyState")
							.toString().equals("complete");
				}
			};
			try {
				Thread.sleep(value != null ? Integer.valueOf(value) : 1000);
				WebDriverWait wait = new WebDriverWait(driver, 1000);
				wait.until(expectation);
			} catch (Throwable error) {
				throw new Exception(
						"Timeout waiting for Page Load Request to complete.");
			}
			break;
			
		case CUSTOM:
			KeywordBean kBean = keywordExecuterDao.getJavaCode(keyword);
			String javaCode = kBean.getJavaCode();
			CompileSourceInMemory.executeJavaCode(javaCode,
					kBean.getClassName(), driver);
			break;
		
		case CREATE_VARIABLE :
			createVariable(driver, valueMap, objectName, objectType, value);
			break;
		
		case VERIFY_SMARTGRID_TEXT:
			Boolean isResult = VerifySmartGridText.execute(driver, value);
			if(!isResult)
				throw new Exception("Unable to verify the Texts");
			break;
			
		case CREATE_SMARTGRID_LIST_VARIABLE:
			List<String> lstResults = null;
			StringTokenizer stk = new StringTokenizer(value, ",");
			String ivalue = stk.nextToken();
			String propertyName = stk.nextToken();
			lstResults = CreateSmartGridListVariable.execute(driver, ivalue);
			valueMap.put(propertyName, lstResults);
			break;
		
		case FILTER_EXT_AUTOCOMPLETER:
			SelectExtAutocompleterValue.execute(driver, value);
			break;
		
		case JQUERY_MULTISEL_CHECKALL:
			WebElement element = driver.findElement(this.getObject(valueMap, objectName, objectType));
			JQueryMultiselectCheckAll.execute(driver, objectName);
			break;
		default:
			break;
		}
	}

	public String resolveValue(String value, Map<String,Object> valueMap)
	{
		if(value.contains("${"))
		{
			int startIndex = value.indexOf("${");
			int startIndex1 = value.indexOf("}");
			String variable = value.substring(startIndex, startIndex1 + 1);
			value = value.replace(variable, (CharSequence) valueMap.get(variable));
			return resolveValue(value, valueMap);
		}
		else
		{
			return value;
		}
		
	}

	private void createVariable(WebDriver driver, Map<String,Object> valueMap, String objectName, String objectType, String value) throws Exception 
	{
		WebElement element = driver.findElement(this.getObject(valueMap, objectName, objectType));
		valueMap.put(value, element.getText());
	}

	/**
	 * Find element BY using object type and value
	 * 
	 * @param p
	 * @param objectName
	 * @param objectType
	 * @return
	 * @throws Exception
	 */
	private By getObject(Map<String,Object> valueMap, String objectName, String objectType)
			throws Exception {
		// Find by xpath
		if(objectName.startsWith("${"))
		{
			objectName = (String) valueMap.get(objectName);
		}
		if (objectType.equalsIgnoreCase("XPATH")) {

			return By.xpath(objectName);
		}
		// find by class
		else if (objectType.equalsIgnoreCase("CLASSNAME")) {

			return By.className(objectName);

		}
		// find by name
		else if (objectType.equalsIgnoreCase("NAME")) {

			return By.name(objectName);
		}
		// Find by css
		else if (objectType.equalsIgnoreCase("CSS")) {

			return By.cssSelector(objectName);

		}
		// find by link
		else if (objectType.equalsIgnoreCase("LINK")) {

			return By.linkText(objectName);

		} else if (objectType.equalsIgnoreCase("ID")) {

			return By.id(objectName);

		}
		// find by partial link
		else if (objectType.equalsIgnoreCase("PARTIALLINK")) {

			return By.partialLinkText(objectName);

		} else {
			throw new Exception("Wrong object type");
		}
	}
}
