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

import com.selenium.bean.PropertyDetailBean;
import com.selenium.bean.PropertySetMstBean;
import com.selenium.controller.TestCaseFilter;

public class PropertySetDao extends JdbcDaoSupport{

	 /** The Constant _log. */
    private static final Logger _log = LoggerFactory.getLogger(PropertySetDao.class.getName());
    
	public void addRecord(final PropertySetMstBean bean)
	{
		String strSQL = "INSERT INTO property_set_mst(id,set_name,modified_by)"
                + " VALUES (?, ?, ?)";

        try
        {

            if (_log.isInfoEnabled())
                _log.info("Inserting new DB table record!");

            getJdbcTemplate().execute(strSQL, new PreparedStatementCallback()
            {
                public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException
                {
                    ps.setString(1, bean.getId());
                    ps.setString(2, bean.getName());
                    ps.setString(3, bean.getModifiedBy());
                    if (_log.isDebugEnabled())
                        _log.info("About to execute the insert prepared statement!");
                    ps.execute();
                    return bean;
                }
            });
            if (_log.isInfoEnabled())
                _log.info("Inserted new record in Test case master!");
        }
        finally
        {
            strSQL = null;
        }
	}

	public List<PropertySetMstBean> readPropertySetList(TestCaseFilter testCaseFilterBean) 
	{
		String sql = "select t.* from property_set_mst t";
		 List<Object> lstArgs = null;
		 StringBuilder sb = new StringBuilder(sql);
		 sb.append(" WHERE 1=1");
		 List<PropertySetMstBean> lstRet = null;
		 lstArgs = new ArrayList<Object>(4);
       lstRet = getJdbcTemplate().query(sb.toString(), lstArgs.toArray(), new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				PropertySetMstBean bean = new PropertySetMstBean();
				bean.setId(rs.getString("id"));
				bean.setName(rs.getString("set_name"));
				bean.setModifiedBy(rs.getString("modified_by"));
				return bean;
			}
      	 
       });
       return lstRet;
	}

	public void updateRecord(final PropertySetMstBean bean) 
	{
		String strSQL = "Update property_set_mst set set_name = ?,modified_by = ? where id = ?";

        try
        {

            if (_log.isInfoEnabled())
                _log.info("Inserting new DB table record!");

            getJdbcTemplate().execute(strSQL, new PreparedStatementCallback()
            {
                public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException
                {
                    ps.setString(1, bean.getName());
                    ps.setString(2, bean.getModifiedBy());
                    ps.setString(3, bean.getId());
                    if (_log.isDebugEnabled())
                        _log.info("About to execute the Update prepared statement!");
                    ps.execute();
                    return bean;
                }
            });
            if (_log.isInfoEnabled())
                _log.info("Updating new record in Test case master!");
        }
        finally
        {
            strSQL = null;
        }
	}

	public List<PropertyDetailBean> readPropertyList(String recordID, TestCaseFilter testCaseFilterBean) {
		String sql = "select t.* from property_set_dtl t where t.parent_record_key = ?";
		 List<Object> lstArgs = null;
		 StringBuilder sb = new StringBuilder(sql);
		 List<PropertyDetailBean> lstRet = null;
		 lstArgs = new ArrayList<Object>(4);
		 lstArgs.add(recordID);
		 lstRet = getJdbcTemplate().query(sb.toString(), lstArgs.toArray(), new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				PropertyDetailBean bean = new PropertyDetailBean();
				bean.setPropertyName(rs.getString("property_name"));
				bean.setPropertyValue(rs.getString("property_value"));
				bean.setParentRecordKey(rs.getString("parent_record_key"));
				return bean;
			}
     	 
      });
      return lstRet;
	}

	public PropertySetMstBean readRecord(String recordID) {
		String sql = "select t.* from property_set_mst t where id = ?";
		 List<Object> lstArgs = null;
		 StringBuilder sb = new StringBuilder(sql);
		 List<PropertySetMstBean> lstRet = null;
		 lstArgs = new ArrayList<Object>(4);
		 lstArgs.add(recordID);
      lstRet = getJdbcTemplate().query(sb.toString(), lstArgs.toArray(), new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				PropertySetMstBean bean = new PropertySetMstBean();
				bean.setId(rs.getString("id"));
				bean.setName(rs.getString("set_name"));
				bean.setModifiedBy(rs.getString("modified_by"));
				return bean;
			}
     	 
      });
      return lstRet.get(0);
	}

	public void addDetailRecord(final PropertyDetailBean bean) {
		String strSQL = "INSERT INTO property_set_dtl(property_name, property_value, parent_record_key)"
                + " VALUES (?, ?, ?)";

        try
        {

            if (_log.isInfoEnabled())
                _log.info("Inserting new DB table record!");

            getJdbcTemplate().execute(strSQL, new PreparedStatementCallback()
            {
                public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException
                {
                    ps.setString(1, bean.getPropertyName());
                    ps.setString(2, bean.getPropertyValue());
                    ps.setString(3, bean.getParentRecordKey());
                    if (_log.isDebugEnabled())
                        _log.info("About to execute the insert prepared statement!");
                    ps.execute();
                    return bean;
                }
            });
            if (_log.isInfoEnabled())
                _log.info("Inserted new record in Test case master!");
        }
        finally
        {
            strSQL = null;
        }
	}

	public void updateDetailRecord(final PropertyDetailBean bean) {
		String strSQL = "Update property_set_dtl set property_value = ? where property_name = ? and parent_record_key = ?";

        try
        {

            if (_log.isInfoEnabled())
                _log.info("Inserting new DB table record!");

            getJdbcTemplate().execute(strSQL, new PreparedStatementCallback()
            {
                public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException
                {
                    ps.setString(1, bean.getPropertyValue());
                    ps.setString(2, bean.getPropertyName());
                    ps.setString(3, bean.getParentRecordKey());
                    if (_log.isDebugEnabled())
                        _log.info("About to execute the Update prepared statement!");
                    ps.execute();
                    return bean;
                }
            });
            if (_log.isInfoEnabled())
                _log.info("Updating new record in Test case master!");
        }
        finally
        {
            strSQL = null;
        }
	}

	public void cloneProperties(final PropertySetMstBean bean, final String sourceRecordKey) 
	{
		String strSQL = "INSERT INTO property_set_dtl SELECT property_name, property_value, ? FROM property_set_dtl d WHERE d.parent_record_key=?";
		try
        {

            if (_log.isInfoEnabled())
                _log.info("Inserting new DB table record!");

            getJdbcTemplate().execute(strSQL, new PreparedStatementCallback()
            {
                public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException
                {
                    ps.setString(1, bean.getId());
                    ps.setString(2, sourceRecordKey);
                    if (_log.isDebugEnabled())
                        _log.info("About to execute the Update prepared statement!");
                    ps.execute();
                    return bean;
                }
            });
            if (_log.isInfoEnabled())
                _log.info("Updating new record in Test case master!");
        }
        finally
        {
            strSQL = null;
        }
	}
}
