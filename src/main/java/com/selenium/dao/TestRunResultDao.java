package com.selenium.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.selenium.bean.AbstractSeleniumBean;
import com.selenium.bean.SeleniumAutomationStatus;
import com.selenium.bean.TestRunBean;
import com.selenium.controller.TestCaseFilter;

public class TestRunResultDao extends JdbcDaoSupport{
	
	 /** The Constant _log. */
   private static final Logger _log = LoggerFactory.getLogger(TestRunResultDao.class.getName());
   
	public void addRecord(final TestRunBean bean)
	{
		String strSQL = "INSERT INTO testrunresult(TestRunID,TestRunBy,StartTime,EndTime,status,title,machine,browser)"
               + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

       try
       {

           if (_log.isInfoEnabled())
               _log.info("Inserting new DB table record!");

           getJdbcTemplate().execute(strSQL, new PreparedStatementCallback()
           {
               public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException
               {
                   
                   ps.setString(1, bean.getTestRunID());
                   ps.setString(2, bean.getTestRunBy());
                   ps.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));
                   ps.setTimestamp(4, null);
                   ps.setInt(5, bean.getStatus().ordinal());
                   ps.setString(6, bean.getTitle());
                   ps.setString(7, bean.getMachineName());
                   ps.setString(8, bean.getBrowser());
                   if (_log.isDebugEnabled())
                       _log.info("About to execute the insert prepared statement!");
                   ps.execute();
                   return bean;
               }
           });
           if (_log.isInfoEnabled())
               _log.info("Inserted new record in Test case master!");
       }
       catch(Exception ex)
       {
    	   System.out.println(ex);
       }
       finally
       {
           strSQL = null;
       }
	}
	
	public void updateRecord(final TestRunBean bean)
	{
		String strSQL = "UPDATE testrunresult set status=?,endtime=? where TestRunID = ?";

       try
       {

           if (_log.isInfoEnabled())
               _log.info("Inserting new testrunresult record!");

           getJdbcTemplate().execute(strSQL, new PreparedStatementCallback()
           {
               public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException
               {
                   int i = 1;
                   ps.setInt(i++, bean.getStatus().ordinal());
                   ps.setTimestamp(i++, new java.sql.Timestamp(System.currentTimeMillis()));
                   ps.setString(i++, bean.getTestRunID());
                   if (_log.isDebugEnabled())
                       _log.info("About to execute the insert prepared statement!");
                   ps.execute();
                   return bean;
               }
           });
           if (_log.isInfoEnabled())
               _log.info("Inserted new record in Beneficiary master!");
       }
       finally
       {
           strSQL = null;
       }
	}

	public TestRunBean readRecord(String id){
		String sql = "select t.* from testrunresult t where TestRunID=?";
		 List<Object> lstArgs = null;
		 List<TestRunBean> lstRet = null;
		 lstArgs = new ArrayList<Object>(4);
        lstArgs.add(id);
        lstRet = getJdbcTemplate().query(sql, lstArgs.toArray(), new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				TestRunBean bean = new TestRunBean();
				bean.setStatus(SeleniumAutomationStatus.valueOf(rs.getInt("status")));
				bean.setTestRunID(rs.getString("TestRunID"));
				bean.setTestRunBy(rs.getString("TestRunBy"));
				bean.setStartTime(rs.getDate("StartTime"));
				bean.setEndTime(rs.getDate("EndTime"));
				bean.setTitle(rs.getString("title"));
				bean.setMachineName(rs.getString("machine"));
				bean.setBrowser(rs.getString("browser"));
				return bean;
			}
       	 
        });
        if (null != lstRet && lstRet.size() > 0)
            return lstRet.get(0);
		return null;
	}

	public List<TestRunBean> readTestRunList(TestCaseFilter testCaseFilterBean) 
	{
		String sql = "select t.* from testrunresult t order by StartTime desc,EndTime desc ";
		 List<Object> lstArgs = null;
		 List<TestRunBean> lstRet = null;
		 lstArgs = new ArrayList<Object>(4);
       lstRet = getJdbcTemplate().query(sql, lstArgs.toArray(), new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				TestRunBean bean = new TestRunBean();
				bean.setStatus(SeleniumAutomationStatus.valueOf(rs.getInt("status")));
				bean.setTestRunID(rs.getString("TestRunID"));
				bean.setTestRunBy(rs.getString("TestRunBy"));
				bean.setStartTime(rs.getTimestamp("StartTime"));
				bean.setEndTime(rs.getTimestamp("EndTime"));
				bean.setTitle(rs.getString("title"));
				bean.setMachineName(rs.getString("machine"));
				bean.setBrowser(rs.getString("browser"));
				return bean;
			}
      	 
       });
       if (null != lstRet && lstRet.size() > 0)
           return lstRet;
		return null;
	}
}

