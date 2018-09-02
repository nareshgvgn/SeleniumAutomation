package com.selenium.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.selenium.bean.AbstractSeleniumBean;
import com.selenium.bean.CommonBean;
import com.selenium.bean.PaginationList;
import com.selenium.bean.TestCaseBean;
import com.selenium.controller.TestCaseFilter;
import com.selenium.util.SeleniumUtils;

public class TestCaseDao extends JdbcDaoSupport {

	@Autowired
	TestCaseStepDao testCaseStepDao;

	@Autowired
	@Qualifier("seleniumSql")
	ResourceBundleMessageSource resourceBundle;

	/** The Constant _log. */
	private static final Logger _log = LoggerFactory
			.getLogger(TestCaseDao.class.getName());

	public void addRecord(final TestCaseBean<AbstractSeleniumBean> bean) {
		String strSQL = "INSERT INTO testcase_mst(testcase_id,priority,module_name,"
				+ "test_designed_by,testcase_title,testcase_description,testcase_preexecute,project_id,record_id,version)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {

			if (_log.isInfoEnabled())
				_log.info("Inserting new DB table record!");

			getJdbcTemplate().execute(strSQL, new PreparedStatementCallback() {
				public Object doInPreparedStatement(PreparedStatement ps)
						throws SQLException, DataAccessException {

					ps.setString(1, bean.getTestCaseId());
					ps.setInt(2, bean.getPriority());
					ps.setString(3, bean.getModuleName());
					ps.setString(4, bean.getTestDesinedBy());
					ps.setString(5, bean.getTestCaseTitle());
					ps.setString(6, bean.getTestcaseDescription());
					ps.setString(7, SeleniumUtils.mergeInputs(bean
							.getTestcasesPreExecute()));
					ps.setString(8, bean.getProjectId());
					ps.setString(9, bean.getRecordId());
					ps.setLong(10, bean.getVersion());
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

	public void updateRecord(final TestCaseBean<AbstractSeleniumBean> bean) {
		String strSQL = "UPDATE testcase_mst set priority=?,module_name=?,"
				+ "test_designed_by=?,testcase_title=?,testcase_description=?,testcase_preexecute=?,project_id=? where record_id = ?";

		try {

			if (_log.isInfoEnabled())
				_log.info("Inserting new testcase_mst record!");

			getJdbcTemplate().execute(strSQL, new PreparedStatementCallback() {
				public Object doInPreparedStatement(PreparedStatement ps)
						throws SQLException, DataAccessException {
					int i = 1;
					ps.setInt(i++, bean.getPriority());
					ps.setString(i++, bean.getModuleName());
					ps.setString(i++, bean.getTestDesinedBy());
					ps.setString(i++, bean.getTestCaseTitle());
					ps.setString(i++, bean.getTestcaseDescription());
					ps.setString(i++, SeleniumUtils.mergeInputs(bean
							.getTestcasesPreExecute()));
					ps.setString(i++, bean.getProjectId());
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

	public TestCaseBean readRecord(String id) {
		String sql = "select t.*, (select project_name from project_mst where project_id=t.project_id) as project_name from testcase_mst  t where record_id=?";
		List<Object> lstArgs = null;
		List<TestCaseBean> lstRet = null;
		lstArgs = new ArrayList<Object>(4);
		lstArgs.add(id);
		lstRet = getJdbcTemplate().query(sql, lstArgs.toArray(),
				new RowMapper() {

					@Override
					public Object mapRow(ResultSet rs, int arg1)
							throws SQLException {
						TestCaseBean bean = new TestCaseBean();
						bean.setModuleName(rs.getString("module_name"));
						bean.setPriority(rs.getInt("priority"));
						bean.setTestcaseDescription(rs
								.getString("testcase_description"));
						bean.setTestCaseId(rs.getString("testcase_id"));
						bean.setTestcasesPreExecute(SeleniumUtils
								.splitInputs(rs
										.getString("testcase_preexecute")));
						bean.setTestCaseTitle(rs.getString("testcase_title"));
						bean.setTestDesinedBy(rs.getString("test_designed_by"));
						bean.setProjectId(rs.getString("project_id"));
						bean.setProjectName(rs.getString("project_name"));
						return bean;
					}

				});
		if (null != lstRet && lstRet.size() > 0)
			return lstRet.get(0);
		return null;
	}

	@SuppressWarnings("unchecked")
	public PaginationList readTestCaseList(
			final TestCaseFilter testCaseFilterBean) {
		final PaginationList paginationList = new PaginationList();
		Map<String, List<TestCaseBean>> map = new HashMap<String, List<TestCaseBean>>();
		String sql = "select t.*,(select project_name from project_mst where project_id=t.project_id) as project_name from testcase_mst t";
		List<Object> lstArgs = null;
		StringBuilder sb = new StringBuilder(sql);
		sb.append(" WHERE 1=1");
		List<TestCaseBean> lstRet = null;
		lstArgs = new ArrayList<Object>(4);
		if (null != testCaseFilterBean.getTestCaseIds()) {
			sb.append(" AND record_id in ( ");
			for (int i = 0; i < testCaseFilterBean.getTestCaseIds().size(); i++) {
				sb.append("?");
				lstArgs.add(testCaseFilterBean.getTestCaseIds().get(i));
				if (i != (testCaseFilterBean.getTestCaseIds().size() - 1)) {
					sb.append(",");
				}
				if (i == (testCaseFilterBean.getTestCaseIds().size() - 1)) {
					sb.append(")");
				}
			}
		}

		lstRet = getJdbcTemplate().query(sb.toString(), lstArgs.toArray(),
				new ResultSetExtractor() {

					@Override
					public Object extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						Integer rowCount = 0;
						int rowNum = 0;
						int pgStart = -1;
						int pgEnd = -1;
						int _intPage = testCaseFilterBean.getCurrentPage() - 1;
						int _intPageSize = 10;
						pgStart = (_intPage * _intPageSize);
						pgEnd = (pgStart + _intPageSize) - 1;
						boolean blnPaginate = true;
						List<TestCaseBean> results = new ArrayList<TestCaseBean>();
						while (rs.next()) {
							if (!blnPaginate
									|| (blnPaginate && ((rowNum >= pgStart) && (rowNum <= pgEnd)))) {
								results.add(mapRow(rs, rowNum));
							}
							rowNum++;
						}
						paginationList.setTotalRows(rowNum);
						return results;
					}

					private TestCaseBean mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						TestCaseBean bean = new TestCaseBean();
						bean.setModuleName(rs.getString("module_name"));
						bean.setPriority(rs.getInt("priority"));
						bean.setTestcaseDescription(rs
								.getString("testcase_description"));
						bean.setTestCaseId(rs.getString("testcase_id"));
						bean.setTestcasesPreExecute(SeleniumUtils
								.splitInputs(rs
										.getString("testcase_preexecute")));
						bean.setTestCaseTitle(rs.getString("testcase_title"));
						bean.setTestDesinedBy(rs.getString("test_designed_by"));
						bean.setProjectId(rs.getString("project_id"));
						bean.setProjectName(rs.getString("project_name"));
						bean.setRecordId(rs.getString("record_id"));
						bean.setVersion(rs.getLong("version"));
						bean.setLstTestSteps(testCaseStepDao
								.readTestCaseSteps(bean.getRecordId()));
						return bean;
					}

				});
		paginationList.setList(lstRet);
		return paginationList;
	}

	public List<CommonBean> getValueList(String keyValue, CommonBean filterBean) {
		List<CommonBean> lstRet = null;
		String sql = resourceBundle.getMessage(keyValue, null, null);
		StringBuilder sb = new StringBuilder(sql);
		List<Object> lstArgs = new ArrayList<Object>(4);
		lstRet = getJdbcTemplate().query(sb.toString(), lstArgs.toArray(),
				new RowMapper() {

					@Override
					public Object mapRow(ResultSet rs, int arg1)
							throws SQLException {
						CommonBean bean = new CommonBean();
						bean.setValue(rs.getString("keyValue"));
						bean.setDescription(rs.getString("description"));
						try {
							bean.setValue1(rs.getString("value1"));
							bean.setValue2(rs.getString("value2"));
						} catch (Exception e) {
							_log.error(
									"Error while fetching the column value from resultset",
									e);
						}
						return bean;
					}

				});
		return lstRet;
	}

	public void deleteRecord(final String recordID) {
		String strSQL = "DELETE FROM testcase_mst WHERE record_id = ? ";

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
