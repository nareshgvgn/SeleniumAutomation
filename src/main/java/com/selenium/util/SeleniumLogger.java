package com.selenium.util;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import com.selenium.bean.DetailInfo;
import com.selenium.bean.CommonBean;
import com.selenium.bean.KeywordBean;
import com.selenium.bean.Keyword;
import com.selenium.bean.TestCaseStep;
import com.selenium.bean.TestStepResultBean;

@Component
public class SeleniumLogger {
	
	@Autowired
	@Qualifier("messageSource")
	ReloadableResourceBundleMessageSource resourceBundle;
	
	public void log(TestStepResultBean step)
	{
		
	}
	
	public String toJSONString(DetailInfo info) 
	{
		JSONObject obj = new JSONObject();
		obj.put("description", info.getDescription());
		if(null != info.getImagePath()){
			obj.put("imagePath", info.getImagePath());
		}
		return obj.toString();
	}
	
	public DetailInfo fromJSON(String jsonData) 
	{
		JSONObject obj = JSONObject.fromObject(jsonData);
		DetailInfo detailInfo = new DetailInfo();
		detailInfo.setDescription(obj.getString("description"));
		if(obj.has("imagePath"))
		detailInfo.setImagePath(obj.getString("imagePath"));
		return detailInfo;
	}

	public DetailInfo logElementNotFound(TestStepResultBean testStepResultBean,	TestCaseStep step, Map<String, KeywordBean> keywordMap) 
	{
		DetailInfo dtlInfo = new DetailInfo();
		dtlInfo.setDescription(resourceBundle.getMessage("reportinfo.keyword.elementNoFound", null, null));
		return dtlInfo;
	}

	public DetailInfo logSuccess(TestStepResultBean testStepResultBean,	TestCaseStep step, Map<String, KeywordBean> keywordMap) 
	{
		KeywordBean keywordBean = keywordMap.get(step.getKeyword());
		DetailInfo dtlInfo = new DetailInfo();
		dtlInfo.setDescription(MessageFormat.format(keywordBean.getReportSucessMessage(), getArguments(step, keywordBean)));
		return dtlInfo;
	}
	
	private Object[] getArguments(TestCaseStep testCaseStep, KeywordBean bean)
	{
		List<Object> args = new ArrayList<Object>();
		if(Keyword.CLICK.toString().equals(bean.getKeyword()))
		{
			args.add(testCaseStep.getObjectName());
		}
		else if(Keyword.GOTOURL.toString().equals(bean.getKeyword()))
		{
			args.add(testCaseStep.getInputData());
		}
		else if(Keyword.SETTEXT.toString().equals(bean.getKeyword()))
		{
			args.add(testCaseStep.getInputData());
			args.add(testCaseStep.getObjectName());
		}
		else if(Keyword.GETTEXT.toString().equals(bean.getKeyword()))
		{
			args.add(testCaseStep.getInputData());
			args.add(testCaseStep.getObjectName());
		}
		return args.toArray();
	}

	public DetailInfo logDefaultError(TestStepResultBean testStepResultBean, Exception exc)
	{
		DetailInfo dtlInfo = new DetailInfo();
		dtlInfo.setDescription(exc.toString());
		return dtlInfo;
	}

}
