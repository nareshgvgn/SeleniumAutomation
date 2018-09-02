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

import com.selenium.bean.TestCaseStep;

public class TestCaseStepDao extends JdbcDaoSupport {

	/** The Constant _log. */
	private static final Logger _log = LoggerFactory
			.getLogger(TestCaseDao.class.getName());

	public void addRecord(final TestCaseStep bean) {
		String strSQL = "INSERT INTO testcase_steps_mst(teststep_id,sequence_no,keyword,"
				+ "description,input_data,object_name,object_type,record_id,parent_record_id)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {

			if (_log.isInfoEnabled())
				_log.info("Inserting new DB table record!");

			getJdbcTemplate().execute(strSQL, new PreparedStatementCallback() {
				public Object doInPreparedStatement(PreparedStatement ps)
						throws SQLException, DataAccessException {
					int i = 1;
					ps.setString(i++, bean.getTestStepId());
					ps.setInt(i++, bean.getSequenceNo());
					ps.setString(i++, bean.getKeyword());
					ps.setString(i++, bean.getDescription());
					ps.setString(i++, bean.getInputData());
					ps.setString(i++, bean.getObjectName());
					ps.setString(i++, bean.getObjectType());
					ps.setString(i++, bean.getRecordId());
					ps.setString(i++, bean.getParentRecordKey());
					if (_log.isDebugEnabled())
						_log.info("About to execute the insert prepared statement!");
					ps.execute();
					return bean;
				}
			});
			if (_log.isInfoEnabled())
				_log.info("Inserted new record in Test case master!");
		} catch (Exception ex) {
			System.out.println(ex);
		} finally {
			strSQL = null;
		}
	}

	public void updateRecord(final TestCaseStep bean) {
		String strSQL = "UPDATE testcase_steps_mst set sequence_no=?,keyword=?,"
				+ "description=?,input_data=?,object_name=?,object_type=?,teststep_id=? where record_id = ?";

		try {

			if (_log.isInfoEnabled())
				_log.info("Inserting new testcase_steps_mst record!");

			getJdbcTemplate().execute(strSQL, new PreparedStatementCallback() {
				public Object doInPreparedStatement(PreparedStatement ps)
						throws SQLException, DataAccessException {
					int i = 1;
					ps.setInt(i++, bean.getSequenceNo());
					ps.setString(i++, bean.getKeyword());
					ps.setString(i++, bean.getDescription());
					ps.setString(i++, bean.getInputData());
					ps.setString(i++, bean.getObjectName());
					ps.setString(i++, bean.getObjectType());
					ps.setString(i++, bean.getTestStepId());
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

	public TestCaseStep readRecord(String id) {
		String sql = "select * from testcase_steps_mst where teststep_id=?";
		List<Object> lstArgs = null;
		List<TestCaseStep> lstRet = null;
		lstArgs = new ArrayList<Object>(4);
		lstArgs.add(id);
		lstRet = getJdbcTemplate().query(sql, lstArgs.toArray(),
				new RowMapper() {

					@Override
					public Object mapRow(ResultSet rs, int arg1)
							throws SQLException {
						TestCaseStep bean = new TestCaseStep();
						bean.setTestStepId(rs.getString("teststep_id"));
						bean.setSequenceNo(rs.getInt("sequence_no"));
						bean.setDescription(rs.getString("description"));
						bean.setInputData(rs.getString("input_data"));
						bean.setKeyword(rs.getString("keyword"));
						bean.setObjectName(rs.getString("object_name"));
						bean.setObjectType(rs.getString("object_type"));
						return bean;
					}

				});
		if (null != lstRet && lstRet.size() > 0)
			return lstRet.get(0);
		return null;
	}

	public List<TestCaseStep> readTestCaseSteps(String id) {
		String sql = "select * from testcase_steps_mst where parent_record_id=? order by sequence_no";
		List<Object> lstArgs = null;
		List<TestCaseStep> lstRet = new ArrayList<TestCaseStep>();
		lstArgs = new ArrayList<Object>(4);
		lstArgs.add(id);
		lstRet = getJdbcTemplate().query(sql, lstArgs.toArray(),
				new RowMapper() {

					@Override
					public Object mapRow(ResultSet rs, int arg1)
							throws SQLException {
						TestCaseStep bean = new TestCaseStep();
						bean.setTestStepId(rs.getString("teststep_id"));
						bean.setSequenceNo(rs.getInt("sequence_no"));
						bean.setDescription(rs.getString("description"));
						bean.setInputData(rs.getString("input_data"));
						bean.setKeyword(rs.getString("keyword"));
						bean.setObjectName(rs.getString("object_name"));
						bean.setObjectType(rs.getString("object_type"));
						bean.setRecordId(rs.getString("record_id"));
						bean.setParentRecordKey(rs.getString("parent_record_id"));
						bean.setVersion(rs.getLong("version"));
						return bean;
					}

				});
		if (null != lstRet && lstRet.size() > 0)
			return lstRet;
		return null;
	}
	
	public void deleteRecord(final String recordID) {
		String strSQL = "DELETE FROM testcase_steps_mst WHERE record_id = ? ";

		getJdbcTemplate().execute(strSQL, new PreparedStatementCallback() {
			public Object doInPreparedStatement(PreparedStatement ps)
					throws SQLException, DataAccessException {

				try {
					ps.setString(1, recordID);
					ps.execute();
					return null;
				} finally {

				}
			}
		});
	}
}
