package maf.mdb.drivers;

/**
 * <p>Title: JDBC Support Driver </p>
 * <p>Description: MdbDriver</p>
 * <p>Copyright: Copyright MIraenet (c) 2005</p>
 * <p>Company: Miraenet co., Ltd</p>
 * @author �����
 * @version 1.0
 * @version 1.1 2006.9.10 SmartConnection ��� �߰� 
 */
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


import maf.MafProperties;
import maf.MafUtil;
import maf.mdb.DBResource;
import maf.mdb.SmartConnection;
import maf.mdb.drivers.support.BaseMdbDriver;
import maf.mdb.exceptions.MdbException;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MdbDriver extends BaseMdbDriver {
	protected int dbms = MdbDriverInf.DMBS_JDBC;
	protected Connection conn = null;
	protected String dbPoolName = null;
	private Log logger = LogFactory.getLog(MdbDriver.class);
	protected boolean isDebug = false;
	protected boolean isScroll = false;
	protected int page = 0;
	protected int fetchSize = 0;
	protected int offset = 0;
	protected boolean autoCommit = true;

	protected void initPage() {
		this.isScroll = false;
		this.page = 0;
		this.fetchSize = 0;
		this.offset = 0;
	}

	protected MdbDriver() {
	}

	
	protected MdbDriver(String dbpoolname) {
		this.dbPoolName = dbpoolname;
	}

	public int getDatabaseID() {
		return this.dbms;
	}

	public boolean isConnected() {
		if (this.conn != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Database�� ���� �Ѵ�.
	 * 
	 * @param dbpoolname
	 */
	public void setDbPoolname(String dbpoolname) {
		this.dbPoolName = dbpoolname;
	}

	public void close() {
		if (this.conn != null) {
			
			try {
				if(MafProperties.USE_SMARTCON) {
					SmartConnection.release(conn);
				} else {
					this.conn.close();
				}
			} catch (SQLException e) {
			}
		}
		this.conn = null;
	}

	/**
	 *  Database Connection�� �Ѱ� �ش�.
	 * 
	 * @return
	 * @throws MdbException
	 * 
	 */
	public Connection getConnection() throws MdbException {
		this.chkConnection();
		return this.conn;
	}

	/**
	 * Connection�� AutoCommit ���¸� ��ȯ �Ѵ�.
	 * 
	 * @param AutoCommit
	 * @throws MdbException
	 */
	public void setAutoCommit(boolean AutoCommit) throws MdbException {
		this.autoCommit = AutoCommit;
		try {
			chkConnection();
			if (this.conn != null) {
				this.conn.setAutoCommit(AutoCommit);
			}
		} catch (SQLException e) {
			throw new MdbException(e);
		}
	}

	/**
	 * ������ AutoCommit ���¸� ���� �ش�.
	 * 
	 * @return
	 * @throws MdbException
	 */
	public final boolean getAutoCommit() throws MdbException {
		try {
			if (this.conn != null && !this.conn.isClosed()) {
				return this.conn.getAutoCommit();
			} else {
				return true;
			}
		} catch (SQLException e) {
			throw new MdbException(e);
		}
	}

	/**
	 * �������� ���� ����
	 * 
	 * @param fetchSize
	 *            ������ Row�� ��
	 * @param offset :
	 *            �ǳʶ� ��
	 */
	public void setLimit(int fetchSize, int offset) {
		this.initPage();
		this.fetchSize = fetchSize;
		this.offset = offset;
		this.page = 1;
		this.isScroll = (fetchSize < 0) ? false : true;
	}

	/**
	 * ������ Row�� �� ����
	 * 
	 * @param maxRows
	 */
	public void setLimit(int maxRows) {
		this.initPage();
		this.setLimit(maxRows, 0);
	}

	/**
	 * ������ ������ ����
	 * 
	 * @param page :
	 *            ������ ������
	 * @param fetchSize :
	 *            �������� row��
	 */
	public void setPage(int page, int fetchSize) {
		this.initPage();
		this.page = page;
		this.fetchSize = fetchSize;
		this.offset = (page - 1) * fetchSize;
		this.isScroll = (fetchSize < 0) ? false : true;
	}

	/**
	 * cls(Bean �Ǵ� Map) �� sql ���� ����� ��� List ���·� ���� �ش�.
	 * 
	 * @param cls
	 * @param sql
	 * @return
	 * @throws MdbException
	 */
	public List selector(Class cls, String sql) throws MdbException {
		MdbSelecter ms = new MdbSelecter(cls);
		return this.pSelector(ms, sql, null);
	}

	/**
	 * cls(Bean �Ǵ� Map) �� sql ���� ����� ��� List ���·� ���� �ش�.
	 * 
	 * @param cls
	 * @param sql
	 * @param at (
	 *            List(����ȣȯ��), Map(HashMap) �Ǵ� bean )
	 * @return
	 * @throws MdbException
	 */
	public List selector(Class cls, String sql, Object at) throws MdbException {
		MdbSelecter ms = new MdbSelecter(cls);
		return this.pSelector(ms, sql, at);
	}

	/**
	 * Array�� ������ ('v1','v2','v3')�� String�� �����. 
	 * @param arr
	 * @return
	 */
	public String getInString(Object[] arr) {
		if (arr == null) {
			return "(null)";
		}
		boolean chk = false;
		StringBuffer sb = new StringBuffer();
		sb.append("(");
		for (int i = 0; i < arr.length; i++) {
			if (i > 0) {
				sb.append(",");
			}
			//			logger.debug(i+"," +arr[i] );
			sb.append("'");
			sb.append(StringEscapeUtils.escapeSql((String) arr[i]));
			sb.append("'");
			chk = true;
		}
		if (!chk) {
			sb.append("null");
		}
		sb.append(")");
		return sb.toString();
	}

	/**
	 * Collection�� ������ 'v1','v2','v3'�� String�� �����.
	 * ���� 
	 * @param arr
	 * @return
	 */
	public String getInString(Collection coll) {
		if (coll == null) {
			return "(null)";
		}
		boolean chk = false;
		StringBuffer sb = new StringBuffer();
		Iterator it = coll.iterator();
		int i = 0;
		while (it.hasNext()) {
			if (i > 0) {
				sb.append(",");
			}
			sb.append("'");
			sb.append(StringEscapeUtils.escapeSql((String) it.next()));
			sb.append("'");
			i++;
			chk = true;
		}
		if (!chk) {
			sb.append("null");
		}
		sb.append(")");
		return sb.toString();
	}

	/**
	 * Result Set�� cls�� List ���·� ���� �ش�.
	 * 
	 * @param cls
	 * @param rs
	 * @return
	 * @throws MdbException
	 */
	public List selector(Class cls, ResultSet rs) throws MdbException {
		MdbSelecter ms = new MdbSelecter(cls);
		return this.pSelector(ms, rs);
	}

	/**
	 * sql���� ��� �� ù row�� ù���� ������ ���� !!! record�� ������ null�� �Ѱ� �ش�.
	 * 
	 * @param sql
	 * @return
	 */
	public Object selectorOne(Class cls, ResultSet rs) throws MdbException {
		List items = null;
		Object obj = null;
		items = this.selector(cls, rs);
		if (items != null && items.size() > 0) {
			obj = items.get(0);
		}
		return obj;
	}

	//	����� 2006�� 10�� ���
	//	/**
	//	 * Select ����� ù��° column�� �������� String Array�� ����� �ش�.
	//	 *  check Box �� radio, select � ��� 
	//	 * @param sql
	//	 * @param inputarrs
	//	 * @return
	//	 * @throws MdbException
	//	 */
	//	public String[] selectValues(String sql, Object inputarrs, String columnName)throws MdbException {
	//		List items = null;
	//		List rItem = new ArrayList();
	//		Map t = null;
	//		items = this.selector(Map.class, sql, inputarrs);
	//		if (items != null && items.size() > 0) {
	//			for(int i =0 ; i < items.size(); i++ ) {
	//				t = (Map) items.get(i);
	//				rItem.add( t.get(columnName));
	//			}
	//			
	//		}
	//		return StringUtils.toStringArray(rItem);
	//	}
	/**
	 * sql���� ��� �� ù row�� ù���� ������ ���� !!! record�� ������ null�� �Ѱ� �ش�.
	 * 
	 * @param sql
	 * @param inputarr(
	 *            List(����ȣȯ��), Map(HashMap) �Ǵ� bean )
	 * @return
	 * @throws MdbException
	 */
	public Object selectorOne(Class cls, String sql) throws MdbException {
		return selectorOne(cls, sql, null);
	}

	/**
	 * ���� ������ Object �� null�Դϴ�.
	 */
	public Object selectorOne(Class cls, String sql, Object inputarrs)
	        throws MdbException {
		List items = null;
		Object obj = null;
		this.setLimit(1);
		items = this.selector(cls, sql, inputarrs);
		if (items != null && items.size() > 0) {
			obj = items.get(0);
		}
		this.initPage();
		return obj;
	}

	protected List selectLimit(MdbSelecter ms, String sql, Object at) throws MdbException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// Logging.log(this.getClass(), "selectLimit execute");
		try {
			if (at != null) {
				stmt = this.prepareStatement(sql, at);
			} else {
				stmt = this.prepareStatement(sql);
			}
			try {
				rs = stmt.executeQuery();
				moveCursor(rs);
			} catch (SQLException se) {
				logger.debug(">> Sql Error : \n" + sql);
				throw new MdbException(se);
			}
			return ms.tokenResultSet(rs, this.fetchSize, this.isDebug);
		} finally {
			if (rs != null) try {
				rs.close();
				rs = null;
			} catch (Exception ex) {
			}
			if (stmt != null) try {
				stmt.close();
				stmt = null;
			} catch (Exception ex) {
			}
			this.chkClose();
		}
	}

	protected List select(MdbSelecter ms, String sql, Object at) throws MdbException {
		return super._select(ms, sql, at, fetchSize);
	}

	/*******************************************************************************************************************************
	 * 
	 * ���� �Լ� ����
	 * 
	 */
	protected List pSelector(MdbSelecter ms, String sql, Object at) throws MdbException {
		try {
			if (this.isScroll) {
				return this.selectLimit(ms, sql, at);
			} else {
				return this.select(ms, sql, at);
			}
			// return ms.tokenResultSet(rs, this.fetchSize);
//		} catch (Exception e) {
//			logger.debug("SQL : \n" + sql + "\n" + MafUtil.getString(at));
//			throw new MdbException(e);
		} finally {
			initPage();
		}
	}

	protected List pSelector(MdbSelecter ms, ResultSet rs) throws MdbException {
		try {
			this.moveCursor(rs);
			return ms.tokenResultSet(rs, this.fetchSize, this.isDebug);
		} catch (Exception e) {
			throw new MdbException(e);
		} finally {
			if (rs != null) try {
				rs.close();
				rs = null;
			} catch (Exception ex) {
			}
			this.chkClose();
		}
	}

	/**
	 * �����͸� SELECT �� ��ġ�� Ŀ���� �̵���Ų��. <br>
	 * JDBC 2.0 API �� �̿��Ͽ���.
	 */
	protected void moveCursor(ResultSet rs) throws SQLException {
		super._moveCursor(this.offset, rs);
	}

	protected synchronized void chkConnection() throws MdbException {
		try {
			if (this.conn == null) {
				this.conn = DBResource.getConnection(this.dbPoolName);
				if (this.conn != null) {
					this.conn.setAutoCommit(this.autoCommit);
				} else {
					throw new MdbException("DB Pool ["
					        + this.dbPoolName
					        + "] Connection Failure!!!");
				}
			}
		} catch (Exception e) {
			maf.logger.Logging.trace(e);
			throw new MdbException("DB Pool ["
			        + this.dbPoolName
			        + "] Connection Failure!!! \n "
			        + e.getMessage(), e);
		}
	}

	// /**
	// * �� Database �´� ����¡ ������ SQL ����
	// *
	// * @param sql
	// * @return
	// */
	// protected String getLimitSql(String sql) {
	// return sql;
	// }
	public void destructConn() {
		try {
			if (this.conn != null) {
				this.conn.rollback();
				logging("Roll Back !!");
			}
		} catch (SQLException _ignored) {
		}
		this.close();
	}

	public boolean isDebug() {
		return isDebug;
	}

	public void setDebug(boolean isDebug) {
		super._setDebug(isDebug);
		this.isDebug = isDebug;
	}

	public String getDbPoolName() {
    	return dbPoolName;
    }

	public void setDbPoolName(String dbPoolName) {
    	this.dbPoolName = dbPoolName;
    }
	
	//===============================================================================
}