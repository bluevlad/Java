package maf.mdb.drivers;

import java.io.CharArrayReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import oracle.jdbc.OracleResultSet;
import oracle.sql.BLOB;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.oro.text.perl.Perl5Util;
import maf.logger.Logging;
import maf.mdb.MdbConfig;
import maf.mdb.MdbParameters;
import maf.mdb.exceptions.MdbException;
import maf.mdb.sqlhelper.SqlWhereBuilder;
import maf.mdb.sqlhelper.support.OracleSqlWhereBuilder;

public class MdbOCI8 extends MdbDriver {
	private Log logger = LogFactory.getLog(getClass());

	public MdbOCI8(String dbpool) {
		this.dbPoolName = dbpool;
		this.dbms = MdbDriver.DBMS_OCI8;
	}

	//    public MdbOCI8() {
	//        this(Config.DEFAULT_DB_POOL);
	//    }
	public SqlWhereBuilder getWhereBuilder() {
		return new OracleSqlWhereBuilder();
	}

	public CallableStatement prepareProcedure(String procedureName, MdbParameters p)
	        throws MdbException {
		CallableStatement stmt = null;
		try {
			String sql = "{call " + procedureName + "( ";
			if (p != null && p.getSize() > 0) {
				for (int i = 0; i < p.getSize(); i++) {
					sql += " ?, ";
					if (i < p.getSize()) {
						sql += " , ";
					}
				}
				sql += ")";
				// 값들을 할당 한다.
				stmt = super.conn.prepareCall(sql);
				for (int i = 0; i < p.getSize(); i++) {
					if (p.getINOUT(i) == MdbConfig.TYPE_PARAMETER_OUT) {
						stmt.setObject(i, p.getValue(i));
					} else {
						stmt.registerOutParameter(i, p.getSqlType(i));
					}
				}
			}
		} catch (Exception e) {
			throw new MdbException(e);
		}
		return stmt;
	}

	/**
	 * 영춘이가 만든 것.
	 * 이후에 필히 Commit을 해줄껏.
	 */
	public void updateCLOB(String tableName, String lobColumnName, String clobString,
	        MdbParameters p) throws MdbException {
		boolean oldCommit = true;
		String sql = "Select " + lobColumnName + " from " + tableName + " where ";
		String updateSql = "UPDATE "
		        + tableName
		        + " SET "
		        + lobColumnName
		        + " = empty_clob() where ";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		OracleResultSet oRS = null;
		try {
			this.chkConnection();
			oldCommit = super.conn.getAutoCommit();
			//	setAutoCommit을 false로 꼭 해야 한다.
			super.conn.setAutoCommit(false);
			for (int i = 0; i < p.getSize(); i++) {
				sql += p.getColumn(i) + " = ? ";
				updateSql += p.getColumn(i) + " = ? ";
				if (i < p.getSize() - 1) {
					sql += " and ";
					updateSql += " and ";
				}
			}
			// CLOB 데이터 입력하기 위해, empty_clob() 생성
			stmt = super.conn.prepareStatement(updateSql);
			logger.debug("======= UPDATE SQL : " + updateSql);
			for (int i = 0; i < p.getSize(); i++) {
				stmt.setObject(i + 1, p.getValue(i));
			}
			stmt.executeUpdate();
			stmt.close();
			// CLOB column에 대한 lock을 얻는다
			sql += " for update ";
			stmt = super.conn.prepareStatement(sql);
			for (int i = 0; i < p.getSize(); i++) {
				stmt.setObject(i + 1, p.getValue(i));
			}
			rs = stmt.executeQuery();
			// ################ //
			//            org.apache.commons.dbcp.DelegatingResultSet dRS = (org.apache.commons.dbcp. DelegatingResultSet) rs;
			//			oRS = (OracleResultSet)dRS.getInnermostDelegate();
			if (rs.next()) {
				setClob(rs, clobString, lobColumnName);
			}
			//			oRS.close();
			rs.close();
			stmt.close();
		} catch (SQLException e2) {
			try {
				super.conn.rollback();
			} catch (SQLException ex) {
			}
			throw new MdbException("SQL Exception !!!" + e2.getMessage(), e2);
		} catch (Exception e3) {
			Logging.trace(e3);
			try {
				super.conn.rollback();
			} catch (SQLException ex) {
			}
			throw new MdbException(" e3 알수 없는 오류 " + e3.getMessage(), e3);
		} finally {
			if (oRS != null) try {
				oRS.close();
			} catch (SQLException ex) {
			}
			if (rs != null) try {
				rs.close();
			} catch (SQLException ex) {
			}
			if (stmt != null) try {
				stmt.close();
			} catch (SQLException ex) {
			}
			try {
				super.conn.setAutoCommit(oldCommit);
			} catch (SQLException ex) {
			}
			oRS = null;
			rs = null;
			stmt = null;
		}
	}

	/**
	 * Oracle 의 CLOB column에 값을 넣는다.
	 * @param rs
	 * @param contents
	 * @param fieldName
	 * @throws SQLException
	 * @throws IOException
	 */
	private synchronized void setClob(ResultSet rs, String contents, String fieldName)
	        throws MdbException {
		try {
			Object obj = rs.getObject(1); //  oracle.sql.CLOB
			logger.debug("obj class : " + obj.getClass());
			oracle.sql.CLOB clob = (oracle.sql.CLOB) obj;
			Writer writer = clob.getCharacterOutputStream();
			Reader src = new CharArrayReader(contents.toCharArray());
			char buffer[] = new char[1024];
			int read = 0;
			while ((read = src.read(buffer, 0, 1024)) != -1)
				writer.write(buffer, 0, read); // CLOB으로 입력되는 곳
			src.close();
			writer.close();
		} catch (Throwable e) {
			Logging.trace(e);
			throw new MdbException("setClob 오류 ", e);
		}
	}

	/**
	 * Table의 Blob Column에 file을 저장 한다. 
	 * @param table
	 * @param lobColumnName
	 * @param file
	 * @param p
	 * @return
	 * @throws MdbException
	 */
	public boolean updateBLOB(String table, String lobColumnName, File file,
	        MdbParameters p) throws MdbException {
		try {
			return updateBLOB(table, lobColumnName, new FileInputStream(file), p);
		} catch (Exception e) {
			throw new MdbException(e);
		}
	}
	
	/**
	 * Table의 Blob Column에 Stream을 저장 한다. 
	 * @param table
	 * @param lobColumnName
	 * @param file
	 * @param p
	 * @return
	 * @throws MdbException
	 */
	public boolean updateBLOB(String table, String lobColumnName, InputStream instream,
	        MdbParameters p) throws MdbException {
		String sql = "Select " + lobColumnName + " from " + table + " where ";
		String updateSql = "UPDATE "
		        + table
		        + " SET "
		        + lobColumnName
		        + " = empty_blob() where ";
		boolean chk = false;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		OracleResultSet oRS = null;
		try {
			this.chkConnection();
			boolean oldCommit = super.conn.getAutoCommit();
			if (oldCommit) {
				throw new MdbException("Autocommit is true!!!");
			} else {
				for (int i = 0; i < p.getSize(); i++) {
					sql += p.getColumn(i) + " = ? ";
					updateSql += p.getColumn(i) + " = ? ";
					if (i < p.getSize() - 1) {
						sql += " and ";
						updateSql += " and ";
					}
				}
				// BLOB 데이터 입력하기 위해, empty_blob() 생성
				stmt = conn.prepareStatement(updateSql);
				logging("======= UPDATE SQL : " + updateSql);
				for (int i = 0; i < p.getSize(); i++) {
					stmt.setObject(i + 1, p.getValue(i));
				}
				stmt.executeUpdate();
				stmt.close();
				// BLOB column에 대한 lock을 얻는다
				sql += " for update ";
				stmt = super.conn.prepareStatement(sql);
				for (int i = 0; i < p.getSize(); i++) {
					stmt.setObject(i + 1, p.getValue(i));
				}
				rs = stmt.executeQuery();
				// ################ //
				org.apache.commons.dbcp.DelegatingResultSet dRS = (org.apache.commons.dbcp.DelegatingResultSet) rs;
				oRS = (OracleResultSet) dRS.getInnermostDelegate();
				// ################ //
				if (oRS.next()) {
					chk = setBlob(oRS, instream, lobColumnName);
				}
			}
		} catch (Exception e3) {
			try {
				super.conn.rollback();
			} catch (SQLException ex) {
			}
			throw new MdbException(e3.getMessage(), e3);
		} finally {
			if (oRS != null) try {
				oRS.close();
			} catch (SQLException ex) {
			}
			if (rs != null) try {
				rs.close();
			} catch (SQLException ex) {
			}
			if (stmt != null) try {
				stmt.close();
			} catch (SQLException ex) {
			}
			oRS = null;
			rs = null;
			stmt = null;
		}
		return chk;
	}

	/**
	 * Oracle 의 CLOB column에 값을 넣는다.
	 * @param rs
	 * @param contents
	 * @param fieldName
	 * @throws SQLException
	 * @throws IOException
	 */
	private synchronized boolean setBlob(OracleResultSet rs, InputStream instream,
	        String fieldName) throws MdbException {
		//		FileInputStream instream = null;
		OutputStream outstream = null;
		boolean chk = false;
		try {
			BLOB blob = ((OracleResultSet) rs).getBLOB(1);
			//			instream = new FileInputStream(file);
			outstream = blob.getBinaryOutputStream();
			int size = blob.getBufferSize();
			//            System.out.println("BufferSize: " + size + " bytes (#)\n");
			byte[] buffer = new byte[size];
			int length = -1;
			while ((length = instream.read(buffer)) != -1) {
				outstream.write(buffer, 0, length);
				//                System.out.print("#");
			}
			chk = true;
		} catch (Throwable e) {
			logger.error(e.getMessage());
			throw new MdbException("setBlob 오류", e);
		} finally {
			if (instream != null) {
				try {
					instream.close();
				} catch (Throwable e) {
				}
			}
			if (outstream != null) {
				try {
					outstream.close();
				} catch (Throwable e) {
				}
			}
			instream = null;
			outstream = null;
		}
		return chk;
	}

	protected void moveCursor(ResultSet rs) throws SQLException {
		int count = 0;
		//int row = (page - 1) * fetchSize; // 찾아갈 로우의 위치
		if (offset > 0) {
			while (rs.next()) {
				count++;
				if (offset == count) break;
			}
		}
		logging(" Move to Row : "
		        + offset
		        + ", Page:"
		        + page
		        + ", fetchSize : "
		        + fetchSize
		        + ",count :"
		        + count);
	}

	/**
	 * Oracle용 Paging
	 * 
	 * @param sql
	 * @param inputarr
	 * @param numrows
	 * @param offset
	 * @return
	 * @throws Exception
	 */
	protected List selectLimit(MdbSelecter ms, String sql, List at) throws MdbException {
		//        String limitSql = null;
		PreparedStatement stmt = null;
		int vi = 0;
		List list = null;
		ResultSet rs = null;
		Perl5Util perl = new Perl5Util();
		StringBuffer rSql = new StringBuffer(50);
		StringBuffer qSql = new StringBuffer(50);
		StringBuffer fields = new StringBuffer(50);
		// for Oracle 8.1.7 이상
		//		int chgNum = 0;
		if (sql.indexOf("/*+") > 0) {
			rSql.append(sql.replaceAll("\\/\\*\\+", "/*+FIRST_ROWS "));
		} else {
			perl.substitute(rSql, "s#^[ \t\n]*select#SELECT /*+FIRST_ROWS*/#i", sql);
		}
		// for Oracle 8.1.6
		//        rSql.append(sql); // Oracle 8.1.6 View 에서 오류 관계로 .. Forst_rows 제거
		// Algorithm by Tomas V V Cox, from PEAR DB oci8.php
		StringBuffer q_fields = new StringBuffer();
		q_fields.append("SELECT * FROM ( ");
		q_fields.append(rSql);
		q_fields.append(") x WHERE NULL = NULL");
		try {
			chkConnection();
			stmt = prepareStatement(q_fields.toString(), at);
			rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				if (i > 1) fields.append(", ");
				fields.append(rsmd.getColumnName(i));
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new MdbException(e);
		} finally {
			if (rs != null) try {
				rs.close();
			} catch (SQLException ex) {
			}
			if (stmt != null) try {
				stmt.close();
			} catch (SQLException ex) {
			}
			rs = null;
			stmt = null;
		}
		try {
			/*
			 * 실제 수행할 SQL 생성
			 */
			qSql.append("SELECT ");
			qSql.append(fields);
			qSql.append(" FROM (SELECT rownum as adodb_rownum,");
			qSql.append(fields);
			qSql.append(" FROM ( ");
			qSql.append(rSql);
			qSql.append(") WHERE rownum <= :adodb_nrows");
			qSql.append(") WHERE adodb_rownum >= :adodb_offset");
			int numrows = this.fetchSize;
			numrows += offset;
			offset += 1; // in Oracle rownum starts at 1
			//            logging("Sql = [ " + qSql.toString() + " ]");
			stmt = prepareStatement(qSql.toString(), at);
			if (at == null) {
				logging("null");
				vi = 0;
			} else {
				vi = at.size();
			}
			stmt.setInt(++vi, numrows);
			//logging("Bind [" + vi +"] = [" + numrows + "]");
			stmt.setInt(++vi, offset);
			//logging("Bind [" + vi +"] = [" + offset + "]");
			rs = stmt.executeQuery();
			list = ms.tokenResultSet(rs, this.fetchSize, this.isDebug);
		} catch (Exception e) {
			throw new MdbException(e);
		} finally {
			if (rs != null) try {
				rs.close();
			} catch (SQLException ex) {
			}
			if (stmt != null) try {
				stmt.close();
			} catch (SQLException ex) {
			}
			rs = null;
			stmt = null;
		}
		return list;
	}

	/**
	 * Oracle의 Sequence 를 가져옴
	 * 
	 * @param sequenceName :
	 *            Oracle Sequence Name
	 * @return SequenceName.NEXTVAL
	 * @throws SQLException
	 */
	public String getSequence(String sequenceName) throws MdbException {
		long nSeq = -1;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		String sql = "SELECT " + sequenceName.trim() + ".NEXTVAL as SEQ FROM dual";
		try {
			chkConnection();
			stmt = super.conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				nSeq = rs.getLong(1);
			}
		} catch (Exception e) {
			throw new MdbException("GetSequence [" + sequenceName + "] Error !!!", e);
		} finally {
			if (rs != null) try {
				rs.close();
			} catch (SQLException ex) {
			}
			if (stmt != null) try {
				stmt.close();
			} catch (SQLException ex) {
			}
		}
		rs = null;
		stmt = null;
		return String.valueOf(nSeq);
	}
}