package com.selenium.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class UtilDao extends JdbcDaoSupport {
	
	public String getUniqueId() {
		String sql = "SELECT LEFT(UUID(), 10)";
		List<Object> lstArgs = new ArrayList<Object>();
		try
		{
			return getJdbcTemplate().queryForObject(sql, lstArgs.toArray(), String.class);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return null;
	}

}
