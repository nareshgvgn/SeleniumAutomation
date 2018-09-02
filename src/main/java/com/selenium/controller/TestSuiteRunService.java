package com.selenium.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.selenium.bean.ActionResult;
import com.selenium.bean.DetailInfo;
import com.selenium.bean.TestCaseResultBean;
import com.selenium.bean.TestRunBean;
import com.selenium.bean.TestStepResultBean;
import com.selenium.dao.TestCaseResultDao;
import com.selenium.dao.TestRunResultDao;
import com.selenium.dao.TestStepResultDao;
import com.selenium.util.TestSuiteUtility;

@RestController
public class TestSuiteRunService {
	
	@Autowired
	TestSuiteUtility testSuiteUtility;
	
	@Autowired
	TestRunResultDao testRunResultDao;
	
	@Autowired
	TestCaseResultDao testCaseResultDao;
	
	@Autowired
	TestStepResultDao testStepResultDao;
	
	@RequestMapping("/startTestRun.json")
	public @ResponseBody ActionResult startTestRun(@RequestBody TestRunBean testRunBean)
	{
		
		try
		{
			testSuiteUtility.startTestRun(testRunBean);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return null;
	}
	
	@RequestMapping("/testResultList.json")
	public @ResponseBody Map<String,Object> testResultList(@RequestBody TestCaseFilter testCaseFilterBean)
	{
		Map<String,Object> result = new HashMap<String, Object>();
		List<TestRunBean> list = new ArrayList<TestRunBean>();
		list = testRunResultDao.readTestRunList(testCaseFilterBean);
		result.put("list",list);
		if(list != null)
		result.put("totalRows",list.size());
		return result;
	}
	
	@RequestMapping("/testCaseResultList.json")
	public @ResponseBody Map<String,Object> testCaseResultList(@RequestBody TestRunBean testCaseFilterBean)
	{
		Map<String,Object> result = new HashMap<String, Object>();
		List<TestCaseResultBean> list = new ArrayList<TestCaseResultBean>();
		list = testCaseResultDao.readList(testCaseFilterBean);
		result.put("list",list);
		if(list != null)
		result.put("totalRows",list.size());
		return result;
	}
	
	@RequestMapping("/testStepResultList.json")
	public @ResponseBody Map<String,Object> testStepResultList(@RequestBody TestRunBean testCaseFilterBean)
	{
		DetailInfo detailInfo = null;
		String msg = null;
		Map<String,Object> result = new HashMap<String, Object>();
		List<TestStepResultBean> list = new ArrayList<TestStepResultBean>();
		list = testStepResultDao.readList(testCaseFilterBean);
		result.put("list",list);
		if(list != null)
		result.put("totalRows",list.size());
		return result;
	}
	
	@RequestMapping(value = "/screenshot/{fileName}.json")
	public void getFile(
	    @PathVariable("fileName") String fileName, 
	    HttpServletResponse response) {
	    try {
	      // get your file as InputStream
	      InputStream is = new FileInputStream(new File("D:/screenshots/"+fileName+".png"));
	      // copy it to response's OutputStream
	      org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
	      response.flushBuffer();
	    } catch (IOException ex) {
	      throw new RuntimeException("IOError writing file to output stream");
	    }

	}
}
