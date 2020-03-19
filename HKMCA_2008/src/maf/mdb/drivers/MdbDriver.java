package maf.mdb.drivers;

/**
 * <p>Title: JDBC Support Driver </p>
 * <p>Description: MdbDriver</p>
 * <p>Copyright: Copyright MIraenet (c) 2005</p>
 * <p>Company: Miraenet co., Ltd</p>
 * @author 김상준
 * @version 1.0
 * @version 1.1 2006.9.10 SmartConnection 기능 추가 
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
	 * Database에 접속 한다.
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
	 *  Database Connection을 넘겨 준다.
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
	 * Connection의 AutoCommit 상태를 전환 한다.
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
	 * 현재의 AutoCommit 상태를 돌려 준다.
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
	 * 가져오기 제한 설정
	 * 
	 * @param fetchSize
	 *            가져올 Row의 수
	 * @param offset :
	 *            건너띌 수
	 */
	public void setLimit(int fetchSize, int offset) {
		this.initPage();
		this.fetchSize = fetchSize;
		this.offset = offset;
		this.page = 1;
		this.isScroll = (fetchSize < 0) ? false : true;
	}

	/**
	 * 가져올 Row의 수 제한
	 * 
	 * @param maxRows
	 */
	public void setLimit(int maxRows) {
		this.initPage();
		this.setLimit(maxRows, 0);
	}

	/**
	 * 가져올 페이지 설정
	 * 
	 * @param page :
	 *            가져올 페이지
	 * @param fetchSize :
	 *            페이지당 row수
	 */
	public void setPage(int page, int fetchSize) {
		this.initPage();
		this.page = page;
		this.fetchSize = fetchSize;
		this.offset = (page - 1) * fetchSize;
		this.isScroll = (fetchSize < 0) ? false : true;
	}

	/**
	 * cls(Bean 또는 Map) 에 sql 수행 결과를 담아 List 형태로 돌려 준다.
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
	 * cls(Bean 또는 Map) 에 sql 수행 결과를 담아 List 형태로 돌려 준다.
	 * 
	 * @param cls
	 * @param sql
	 * @param at (
	 *            List(하위호환성), Map(HashMap) 또는 bean )
	 * @return
	 * @throws MdbException
	 */
	public List selector(Class cls, String sql, Object at) throws MdbException {
		MdbSelecter ms = new MdbSelecter(cls);
		return this.pSelector(ms, sql, at);
	}

	/**
	 * Array를 가지고 ('v1','v2','v3')의 String을 만든다. 
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
	 * Collection를 가지고 'v1','v2','v3'의 String을 만든다.
	 * 주의 
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
	 * Result Set을 cls에 List 형태로 돌려 준다.
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
	 * sql수행 결과 중 첫 row의 첫값만 가져옮 주의 !!! record가 없으면 null을 넘겨 준다.
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

	//	김상준 2006년 10월 폐기
	//	/**
	//	 * Select 결과의 첫번째 column의 값만으로 String Array를 만들어 준다.
	//	 *  check Box 나 radio, select 등에 사용 
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
	 * sql수행 결과 중 첫 row의 첫값만 가져옮 주의 !!! record가 없으면 null을 넘겨 준다.
	 * 
	 * @param sql
	 * @param inputarr(
	 *            List(하위호환성), Map(HashMap) 또는 bean )
	 * @return
	 * @throws MdbException
	 */
	public Object selectorOne(Class cls, String sql) throws MdbException {
		return selectorOne(cls, sql, null);
	}

	/**
	 * 값이 없으면 Object 는 null입니다.
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
	 * 내부 함수 모음
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
	 * 데이터를 SELECT 할 위치로 커서를 이동시킨다. <br>
	 * JDBC 2.0 API 를 이용하였음.
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
	// * 각 Database 맞는 페이징 지원용 SQL 생성
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