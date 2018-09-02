package com.selenium.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.selenium.bean.SeleniumAutomationStatus;
import com.selenium.bean.TestRunBean;
import com.selenium.bean.TestStepResultBean;
import com.selenium.util.SeleniumLogger;

public class TestStepResultDao extends JdbcDaoSupport {

	/** The Constant _log. */
	private static final Logger _log = LoggerFactory
			.getLogger(TestStepResultDao.class.getName());

	@Autowired
	SeleniumLogger logger;
	
	public void addRecord(final TestStepResultBean bean) {
		String strSQL = "INSERT INTO testcasestepresult(TestRunID,TestCaseRecordID,TestStepRecordId,Status,details)"
				+ " VALUES (?, ?, ?, ?, ?)";

		try {

			if (_log.isInfoEnabled())
				_log.info("Inserting new DB table record!");

			getJdbcTemplate().execute(strSQL, new PreparedStatementCallback() {
				public Object doInPreparedStatement(PreparedStatement ps)
						throws SQLException, DataAccessException {

					ps.setString(1, bean.getTestRunID());
					ps.setString(2, bean.getParentRecordKey());
					ps.setString(3, bean.getRecordId());
					ps.setInt(4, bean.getStatus().ordinal());
					ps.setString(5, logger.toJSONString(bean.getDetails()));
					if (_log.isDebugEnabled())
						_log.info("About to execute the insert prepared statement!");
					ps.execute();
					return bean;
				}
			});
			if (_log.isInfoEnabled())
				_log.info("Inserted new record in Test case master!");
		} finally {
			strSQL = null;
		}
	}

	public void updateRecord(final TestStepResultBean bean) {
		String strSQL = "UPDATE testcasestepresult set status=?,details = ? where TestRunID = ? and TestCaseRecordID=? AND TestStepRecordId=?";

		try {

			if (_log.isInfoEnabled())
				_log.info("Inserting new testcasestepresult record!");

			getJdbcTemplate().execute(strSQL, new PreparedStatementCallback() {
				public Object doInPreparedStatement(PreparedStatement ps)
						throws SQLException, DataAccessException {
					int i = 1;
					ps.setInt(i++, bean.getStatus().ordinal());
					ps.setString(i++, logger.toJSONString(bean.getDetails()));
					ps.setString(i++, bean.getTestRunID());
					ps.setString(i++, bean.getParentRecordKey());
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

	public TestStepResultBean readRecord(TestStepResultBean filterBean) {
		String sql = "select t.* from testcasestepresult t where TestRunID=? and TestCaseRecordID=? AND TestStepRecordId=?";
		List<Object> lstArgs = null;
		List<TestStepResultBean> lstRet = null;
		lstArgs = new ArrayList<Object>(4);
		lstArgs.add(filterBean.getTestRunID());
		lstArgs.add(filterBean.getParentRecordKey());
		lstArgs.add(filterBean.getRecordId());
		lstRet = getJdbcTemplate().query(sql, lstArgs.toArray(),
				new RowMapper() {

					@Override
					public Object mapRow(ResultSet rs, int arg1)
							throws SQLException {
						TestStepResultBean bean = new TestStepResultBean();
						bean.setStatus(SeleniumAutomationStatus.valueOf(rs
								.getInt("status")));
						bean.setTestRunID(rs.getString("TestRunID"));
						bean.setTestCaseId(rs.getString("TestCaseID"));
						bean.setTestStepId(rs.getString("TestStepId"));
						return bean;
					}

				});
		if (null != lstRet && lstRet.size() > 0)
			return lstRet.get(0);
		return null;
	}

	public List<TestStepResultBean> readList(
			TestRunBean testCaseFilterBean) {
		String sql = "select t.*,mst.* from testcasestepresult t,testcase_steps_mst mst " +
				"where mst.parent_record_id =t.TestCaseRecordID AND mst.record_id=t.TestStepRecordId " +
				"AND t.TestRunID=? and t.TestCaseRecordID=? order by mst.sequence_no";
		List<Object> lstArgs = null;
		List<TestStepResultBean> lstRet = null;
		lstArgs = new ArrayList<Object>(4);
		lstArgs.add(testCaseFilterBean.getTestRunID());
		lstArgs.add(testCaseFilterBean.getTestCaseId());
		lstRet = getJdbcTemplate().query(sql, lstArgs.toArray(),
				new RowMapper() {

					@Override
					public Object mapRow(ResultSet rs, int arg1)
							throws SQLException {
						TestStepResultBean bean = new TestStepResultBean();
						bean.setStatus(SeleniumAutomationStatus.valueOf(rs
								.getInt("status")));
						bean.setTestRunID(rs.getString("TestRunID"));
						bean.setParentRecordKey(rs.getString("TestCaseRecordID"));
						bean.setTestStepId(rs.getString("teststep_id"));
						bean.setDescription(rs.getString("description"));
						bean.setKeyword(rs.getString("keyword"));
						bean.setInputData(rs.getString("input_data"));
						bean.setObjectName(rs.getString("object_name"));
						bean.setObjectType(rs.getString("object_type"));
						bean.setDetails(logger.fromJSON(rs.getString("details")));
						return bean;
					}

				});
		if (null != lstRet && lstRet.size() > 0)
			return lstRet;
		return null;
	}
}

