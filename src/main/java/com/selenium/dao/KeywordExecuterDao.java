package com.selenium.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.selenium.bean.KeywordBean;

public class KeywordExecuterDao extends JdbcDaoSupport{
	public KeywordBean getJavaCode(String keyword){

		String sql = "select t.* from keyword_mst  t where keyword=?";
		 List<Object> lstArgs = null;
		 List<KeywordBean> lstRet = null;
		 lstArgs = new ArrayList<Object>(4);
         lstArgs.add(keyword);
         lstRet = getJdbcTemplate().query(sql, lstArgs.toArray(), new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				KeywordBean bean = new KeywordBean();
				bean.setKeyword(rs.getString("keyword"));
				bean.setJavaCode(rs.getString("javacode"));
				bean.setClassName(rs.getString("class_name"));
				return bean;
			}
        	 
         });
         if (null != lstRet && lstRet.size() > 0)
             return lstRet.get(0);
		return null;
	
	}
}
