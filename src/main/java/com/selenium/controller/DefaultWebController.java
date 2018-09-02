package com.selenium.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.selenium.dao.TestCaseDao;
 
/*
 * author: Crunchify.com
 * 
 */
 
@Controller
public class DefaultWebController {
 
	@Autowired
	TestCaseDao testCaseDao;
	
	@RequestMapping("/index.action")
	public ModelAndView showConnectionForm(HttpServletRequest req, HttpServletResponse res) {
		String remoteIP = req.getRemoteHost();
		
		return new ModelAndView("home").addObject("remoteIP", remoteIP);
	}
	
}