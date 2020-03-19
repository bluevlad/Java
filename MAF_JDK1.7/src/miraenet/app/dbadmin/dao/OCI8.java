/*
 * 작성된 날짜: 2005-02-02
 *
 */
package miraenet.app.dbadmin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;
import miraenet.app.dbadmin.beans.ColumnBean;
import miraenet.app.dbadmin.beans.DbUserBean;
import miraenet.app.dbadmin.beans.IndexBean;
import miraenet.app.dbadmin.beans.TableBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author goindole, 2005-02-02
 *  
 */
public class OCI8 implements DbAdmin {
	private  Log logger = LogFactory.getLog(OCI8.class);

	public OCI8() {

	}

	/**
	 * 사용자 List를 돌려 준다.
	 * 
	 * @param oDb
	 * @return String List
	 * @throws MdbException
	 */
	public DbUserBean[] getUserList(MdbDriver oDb) throws MdbException {
		String sql = "SELECT username FROM sys.ALL_USERS  ORDER BY username";
		List list = oDb.selector(DbUserBean.class, sql);
		return (DbUserBean[]) list.toArray(new DbUserBean[0]);
	}

	/**
	 * 사용자의 Table List를 돌려 준다.
	 * 
	 * @param oDb
	 * @param owner
	 * @param table_name
	 * @return List [TableBean]
	 * @throws MdbException
	 */
	public List getTableList(MdbDriver oDb, String owner) throws MdbException {
		String sql = "SELECT  tabs.table_name name, cmt.comments comments "
				+ " FROM sys.DBA_ALL_TABLES tabs, SYS.ALL_TAB_COMMENTS cmt "
				+ " WHERE tabs.OWNER=:owner "
				+ " AND tabs.owner = cmt.owner (+)"
				+ " AND tabs.table_name = cmt.table_name (+) "
				+ " order by tabs.table_name";
		Map param = new HashMap();
		param.put("owner",owner);
		return oDb.selector(TableBean.class, sql, param);
	}

	/**
	 * 사용자의 View List를 돌려 준다.
	 * 
	 * @param oDb
	 * @param owner
	 * @param table_name
	 * @return
	 * @throws MdbException
	 */
	public List getViewList(MdbDriver oDb, String owner) throws MdbException {
		String sql = "SELECT  tabs.object_name name, cmt.comments comments "
				+ " FROM sys.DBA_OBJECTS tabs, SYS.ALL_TAB_COMMENTS cmt "
				+ " WHERE tabs.object_type = 'VIEW' "
				+ " AND tabs.OWNER=:OWNER " + " AND tabs.owner = cmt.owner (+)"
				+ " AND tabs.object_name = cmt.table_name (+) "
				+ " order by tabs.object_name";
		Map param = new HashMap();
		param.put("owner",owner);

		return oDb.selector(TableBean.class, sql, param);
	}

	/**
	 * 사용자의 Proc(Function, Procedure, Package) List를 돌려 준다.
	 * 
	 * @param oDb
	 * @param owner
	 * @param table_name
	 * @return
	 * @throws MdbException
	 */
	public List getProcList(MdbDriver oDb, String owner) throws MdbException {
		String sql = " select o.*, NVL(d.debuginfo, 'F') DEBUGINFO"
				+ " from ("
				+ " Select object_name, object_type, status, last_ddl_time, object_id"
				+ " from sys.user_objects"
				+ " where 1=1"
				+ " and object_type in ('FUNCTION', 'PROCEDURE', 'PACKAGE', 'PACKAGE BODY')"
				+ " ) o, sys.all_probe_objects d"
				+ " where  o.OBJECT_ID = d.object_id (+)"
				+ " And    D.Owner (+) = :owner"
				+ " And    O.Object_Name = D.Object_Name (+)"
				+ " And    ((d.object_type  is null) or (d.object_type in ('FUNCTION', 'PROCEDURE', 'PACKAGE')))"
				+ " order by 1, 2";
		Map param = new HashMap();
		param.put("owner",owner);
		return oDb.selector(Map.class, sql, param);
	}

	/**
	 * 사용자의 Proc Source를 List로 돌려 준다.
	 * 
	 * @param owner
	 * @param type
	 * @param name
	 * @return
	 * @throws MdbException
	 */
	public List getProcSource(MdbDriver oDb, String owner, String type, String name)
			throws MdbException {
		String sql = "select  LINE, TEXT from SYS.DBA_SOURCE "
				+ " where OWNER=:owner and NAME=:name and TYPE=:type "
				+ " order by LINE";

		Map param = new HashMap();
		param.put("owner",owner);
		param.put("name",name);
		param.put("type",type);
		return oDb.selector(Map.class, sql, param);

	}

	/**
	 * table의 Comments 를 가져온다.
	 * 
	 * @param oDb
	 * @param table_name
	 * @return
	 * @throws MdbException
	 */
	public String getTableComment(MdbDriver oDb, String owner, String table_name)
			throws MdbException {
		String sql = " SELECT comments " + "   FROM SYS.ALL_TAB_COMMENTS"
				+ "  WHERE owner = :owner and table_name= :table_name ";

		Map param = new HashMap();
		param.put("owner",owner);
		param.put("table_name",table_name);
		return oDb.selectOne(sql, param);

	}

	/**
	 * Table의 Column 정보를 돌려준다.
	 * 
	 * @param oDb
	 * @param owner
	 * @param table_name
	 * @return  List [ColumnBean]
	 * @throws MdbException
	 */
	public List getColumnInfo(MdbDriver oDb, String owner, String table_name)
			throws MdbException {
		final String sql1 = " SELECT   cols.column_id AS ID, cols.column_name AS NAME, nullable,"
				+ "          data_type AS TYPE,"
				+ "          DECODE (data_type,"
				+ "                  'CHAR', char_col_decl_length,"
				+ "                  'VARCHAR', char_col_decl_length,"
				+ "                  'VARCHAR2', char_col_decl_length,"
				+ "                  'NCHAR', char_col_decl_length,"
				+ "                  'NVARCHAR', char_col_decl_length,"
				+ "                  'NVARCHAR2', char_col_decl_length,"
				+ "                  NULL"
				+ "                 ) nchar_length,"
				+ "          DECODE (data_type,"
				+ "                  'NUMBER', data_precision + data_scale,"
				+ "                  data_length"
				+ "                 ) LENGTH,"
				+ "          data_precision PRECISION, data_scale scale, data_length dlength,"
				+ "          data_default, SUBSTR (comments, 1, 240) comments, c1.POSITION pk"
				+ "     FROM SYS.DBA_COL_COMMENTS coms,"
				+ "          SYS.DBA_TAB_COLUMNS cols,"
				+ "          (SELECT a1.owner, a1.table_name, c1.column_name, c1.POSITION"
				+ "             FROM SYS.ALL_CONS_COLUMNS c1, SYS.ALL_CONSTRAINTS a1"
				+ "            WHERE a1.owner = c1.owner"
				+ "              AND a1.table_name = c1.table_name"
				+ "              AND a1.constraint_name = c1.constraint_name"
				+ "              AND a1.constraint_type = 'P'"
				+ " 			 AND a1.table_name = :table_name"
				+ "              AND a1.owner = :owner ) c1"
				+ "    WHERE coms.owner = cols.owner"
				+ "      AND coms.table_name = cols.table_name"
				+ "      AND coms.column_name = cols.column_name"
				+ "      AND coms.owner = c1.owner (+)"
				+ "      AND coms.table_name = c1.table_name(+)"
				+ "      AND coms.column_name = c1.column_name(+)"
				+ "      AND cols.table_name = :table_name"
				+ "      AND cols.owner = :owner" + " ORDER BY column_id";
		Map param = new HashMap();
		param.put("owner",owner);
		param.put("table_name",table_name);

		return oDb.selector(ColumnBean.class, sql1, param);

	}

	public List getColumnInfobySQL(MdbDriver oDb, String owner, String sql)
			throws MdbException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		List at = new ArrayList();
		try {
			oDb.setAutoCommit(false);
			String nSql = "select * from (" + sql + ") x WHERE NULL = NULL";
			stmt = oDb.prepareStatement(nSql);
			rs = stmt.executeQuery();
			rsmd = rs.getMetaData();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				ColumnBean cb = new ColumnBean();
				cb.setName(rsmd.getColumnName(i));
				cb.setType(rsmd.getColumnTypeName(i));

				at.add(cb);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException ex) {
				}
		}
		oDb.rollback();
		return at;
	}

	public List getIndexInfo(MdbDriver oDb, String owner, String table_name)
			throws MdbException {
		final String sql = " SELECT "
				+ "     ix.INDEX_NAME name,"
				+ "     ix.INDEX_TYPE type,"
				+ "     ix.UNIQUENESS Uniqueness,"
				+ "     ix.LAST_ANALYZED LastAnalyzed,"
				+ "     ix.DISTINCT_KEYS DistinctKeys,"
				+ "     ix.NUM_ROWS NumRows, "
				+ " 	ic.COLUMN_NAME, ic.COLUMN_POSITION, ic.DESCEND, ic.COLUMN_LENGTH, "
				+ "   iex.COLUMN_EXPRESSION"
				+ " FROM  SYS.DBA_INDEXES ix, sys.DBA_IND_COLUMNS ic, sys.DBA_IND_EXPRESSIONS iex"
				+ " WHERE ix.OWNER =:owner" + " AND  ix.TABLE_NAME=:table_name"
				+ " AND ix.OWNER = ic.INDEX_OWNER"
				+ " AND ix.INDEX_NAME = ic.INDEX_NAME"
				+ " AND ic.INDEX_OWNER = iex.INDEX_OWNER(+)"
				+ " AND ic.INDEX_NAME = iex.INDEX_NAME(+)"
				+ " AND ic.COLUMN_POSITION = iex.COLUMN_POSITION(+)"
				+ " ORDER BY ix.INDEX_NAME, ic.COLUMN_POSITION";
		Map param = new HashMap();
		param.put("owner",owner);
		param.put("table_name",table_name);
		
		return oDb.selector(IndexBean.class, sql, param);
	}
}
