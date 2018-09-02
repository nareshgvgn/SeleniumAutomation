package com.selenium.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.selenium.bean.AbstractSeleniumBean;
import com.selenium.bean.SeleniumAutomationStatus;
import com.selenium.bean.TestCaseResultBean;
import com.selenium.bean.TestRunBean;
import com.selenium.util.SeleniumUtils;

public class TestCaseResultDao extends JdbcDaoSupport {

	/** The Constant _log. */
	private static final Logger _log = LoggerFactory
			.getLogger(TestCaseResultDao.class.getName());

	public void addRecord(final TestCaseResultBean bean) {
		String strSQL = "INSERT INTO testcaseresult(TestRunID,TestCaseRecordID,Status)"
				+ " VALUES (?, ?, ?)";

		try {

			if (_log.isInfoEnabled())
				_log.info("Inserting new DB table record!");

			getJdbcTemplate().execute(strSQL, new PreparedStatementCallback() {
				public Object doInPreparedStatement(PreparedStatement ps)
						throws SQLException, DataAccessException {

					ps.setString(1, bean.getTestRunID());
					ps.setString(2, bean.getRecordId());
					ps.setInt(3, bean.getStatus().ordinal());
					if (_log.isDebugEnabled())
						_log.info("About to execute the insert prepared statement!");
					ps.execute();
					return bean;
				}
			});
			if (_log.isInfoEnabled())
				_log.info("Inserted new record in Test case master!");
		}
		catch(Exception ex){
			System.out.println(ex);
		}finally {
			strSQL = null;
		}
	}

	public void updateRecord(final TestCaseResultBean bean) {
		String strSQL = "UPDATE testcaseresult set status=? where TestRunID = ? and TestCaseRecordID=?";

		try {

			if (_log.isInfoEnabled())
				_log.info("Inserting new testcaseresult record!");

			getJdbcTemplate().execute(strSQL, new PreparedStatementCallback() {
				public Object doInPreparedStatement(PreparedStatement ps)
						throws SQLException, DataAccessException {
					int i = 1;
					ps.setInt(i++, bean.getStatus().ordinal());
					ps.setString(i++, bean.getTestRunID());
					ps.setString(i++, bean.getRecordId());
					if (_log.isDebugEnabled())
						_log.info("About to execute the insert prepared statement!");
					ps.execute();
					return bean;
				}
			});
			if (_log.isInfoEnabled())
				_log.info("Inserted new record in Beneficiary master!");
		} finally {
			strSQL = null;
		}
	}

	public TestCaseResultBean readRecord(TestCaseResultBean filterBean) {
		String sql = "select t.* from testcaseresult t where TestRunID=? and TestCaseRecordID = ?";
		List<Object> lstArgs = null;
		List<TestCaseResultBean> lstRet = null;
		lstArgs = new ArrayList<Object>(4);
		lstArgs.add(filterBean.getTestRunID());
		lstArgs.add(filterBean.getRecordId());
		lstRet = getJdbcTemplate().query(sql, lstArgs.toArray(),
				new RowMapper() {

					@Override
					public Object mapRow(ResultSet rs, int arg1)
							throws SQLException {
						TestCaseResultBean bean = new TestCaseResultBean();
						bean.setStatus(SeleniumAutomationStatus.valueOf(rs
								.getInt("status")));
						bean.setTestRunID(rs.getString("TestRunID"));
						//bean.setTestCaseId(rs.getString("TestCaseID"));
						return bean;
					}

				});
		if (null != lstRet && lstRet.size() > 0)
			return lstRet.get(0);
		return null;
	}

	public List<TestCaseResultBean> readList(
			TestRunBean testRunBean) {
		String sql = "select t.*,tm.* from testcaseresult t,testcase_mst tm where t.TestCaseRecordID = tm.record_id and  t.TestRunID = ?";
		List<Object> lstArgs = null;
		List<TestCaseResultBean> lstRet = null;
		lstArgs = new ArrayList<Object>(4);
		lstArgs.add(testRunBean.getTestRunID());
		lstRet = getJdbcTemplate().query(sql, lstArgs.toArray(),
				new RowMapper() {

					@Override
					public Object mapRow(ResultSet rs, int arg1)
							throws SQLException {
						TestCaseResultBean bean = new TestCaseResultBean();
						bean.setStatus(SeleniumAutomationStatus.valueOf(rs
								.getInt("status")));
						bean.setTestRunID(rs.getString("TestRunID"));
						bean.setTestCaseId(rs.getString("testcase_id"));
						bean.setRecordId(rs.getString("TestCaseRecordID"));
						
						bean.setModuleName(rs.getString("module_name"));
						bean.setPriority(rs.getInt("priority"));
						bean.setTestcaseDescription(rs.getString("testcase_description"));
						bean.setTestCaseId(rs.getString("testcase_id"));
						bean.setTestcasesPreExecute(SeleniumUtils.splitInputs(rs.getString("testcase_preexecute")));
						bean.setTestCaseTitle(rs.getString("testcase_title"));
						bean.setTestDesinedBy(rs.getString("test_designed_by"));
						bean.setProjectId(rs.getString("project_id"));
						return bean;
					}

				});
		if (null != lstRet && lstRet.size() > 0)
			return lstRet;
		return null;
	}
}
