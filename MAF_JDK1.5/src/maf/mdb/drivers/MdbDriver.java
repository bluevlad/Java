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
import java.io.StringReader;
import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import maf.MafUtil;
import maf.exception.MafException;
import maf.lib.calendar.MDate;
import maf.logger.Logging;
import maf.logger.Trace;
import maf.mdb.DBResource;
import maf.mdb.MdbParameters;
import maf.mdb.SmartConnection;
import maf.mdb.exceptions.MdbException;
import maf.mdb.sqlhelper.SqlWhereBuilder;
import maf.mdb.sqlhelper.support.OracleSqlWhereBuilder;
import maf.util.StringUtils;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.regexp.RE;

public class MdbDriver implements MdbDriverInf {

	protected int dbms = MdbDriverInf.DMBS_JDBC;

	protected SmartConnection conn = null;

	protected String dbPoolName = null;

	private Log logger = LogFactory.getLog(MdbDriver.class);

	protected boolean isDebug = false;

	protected boolean isScroll = false;

	protected int page = 0;

	protected int fetchSize = 0;

	protected int offset = 0;

	protected boolean autoCommit = true;
	
	public SqlWhereBuilder getWhereBuilder() {
		return new OracleSqlWhereBuilder();
	}
	
	protected void initPage() {
		this.isScroll = false;
		this.page = 0;
		this.fetchSize = 0;
		this.offset = 0;
	}

	public void setDebug(boolean isDebug) {
		if (miraenet.MiConfig.DEBUG) {
			logger.debug(" >>> turn debug info is " + isDebug);
			if(isDebug ==  true) {
				try {
					throw new MafException("mdb debug mode on at ---");
				} catch (MafException e) {
					String t = maf.logger.Trace.getStackTrace(e);
					if(t!= null) {
						System.out.println(t.substring(0, 300));
	
					}
				}
				
			}
			this.isDebug = isDebug;
		}
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
				// SmartConnection 으로 전환 
//					this.conn.close();
					SmartConnection.release(conn);  
			}

		this.conn = null;
	}
	
	/** 
	 * AutoCommit 이 false 일 경우
	 * 작업 종료후 Connection을 Close 해준다.
	 *
	 */
	protected void chkClose() {
		// 성능상의 의미로 사용안함 (Smart Connection 으로 대체)
//		if( this.autoCommit ) {
//			this.close();
//		}
	}

	/**
	 *  Database Connection을 넘겨 준다.
	 * 
	 * @return
	 * @throws MdbException
	 * @deprecated
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
	 * Transaction을 Commit 한다 .
	 * 
	 * @throws MdbException
	 */
	public void commit() throws MdbException {
		try {
			this.conn.commit();
			logging("Commited !!");
		} catch (SQLException e) {
			throw new MdbException(e);
		}
	}

	/**
	 * RollBack 할때 SQLException을 검사하지 않기 위한 메소드
	 * 
	 */
	public void rollback() {
		try {
			if (this.conn != null) {
				this.conn.rollback();
				logging("Roll Back !!");
			}
		} catch (SQLException _ignored) {
		}
	}
	/**
	 * Update, Delete, Insert 를 수행 하고 적용된 record수를 return 한다.
	 */
	public int executeUpdate(String sql) throws MdbException {
		return executeUpdate(sql, null);
	}
	/**
	 * Update, Delete, Insert 를 수행 하고 적용된 record수를 return 한다.
	 */
	public int executeUpdate(String sql, Object bean) throws MdbException {
		PreparedStatement stmt = null;
		try {
			this.chkConnection();
			stmt = this.prepareStatement(sql, bean);
			int cnt = -1;
			if(stmt != null) {
				cnt = stmt.executeUpdate();
			} else {
				throw new MdbException("statement is null)");
			}
			logging(cnt + " record('s') affected ");
			return cnt;
		} catch (Exception e) {
//			Logging.trace(e);
			logger.debug(e.getClass() + " / msg:" + e.getMessage() + " \n >> Sql : \n" + MafUtil.nvl(sql, "NULL")+"\n"+bean);
			throw new MdbException(e.getMessage());
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
			} catch (Exception ex) {
			}
			 this.chkClose();
		}
	}

	private PreparedStatement _prepareStatement(String sql, MdbParameters p) throws MdbException {
		PreparedStatement stmt = null;
		try {
			this.chkConnection();
			logging("PreparedStatement SQL : \n " + sql);
			stmt = this.conn.prepareStatement(sql);
			if (p != null && p.getSize() > 0) {
				// 값들을 할당 한다.
				int cnt = p.getSize();
				for (int i = 0; i < cnt; i++) {
					Object obj = p.getValue(i);
					String colName = p.getColumn(i);
					if (obj != null) {
						logging("" + i + "." + colName + " : [" + obj.getClass() + "] = [" + obj.toString() + "]");
						if (obj.getClass().equals(java.util.Date.class)) {
							java.util.Date nDate = (java.util.Date) obj;
							java.sql.Date sqldate = new java.sql.Date(nDate.getTime());
							stmt.setDate(i + 1, sqldate);
						} else {
							stmt.setObject(i + 1, MafUtil.nvl(obj, ""));
						}
					} else {
						logging("" + i + "." + colName + " value is null");
						stmt.setObject(i + 1, "");
					}
					// logging("cols : " + i + " size : " + cnt);

				}
			}
		} catch (Exception e) {
			logger.error(Trace.getStackTrace(e));
			throw new MdbException(e);
		}
		return stmt;
	}

	
	public PreparedStatement prepareStatement(String sql) throws MdbException {
		return _prepareStatement(sql, null);
	}

	/**
	 * 
	 * @param sql
	 * @param bean :
	 *            List(ArrayList-하위 호환성 )와 Map 또는 Bean 으로 구분 됨
	 * @return
	 * @throws MdbException
	 */
	public PreparedStatement prepareStatement(String sql, Object at) throws MdbException {
		if (at instanceof List) {
			// if(List.class.equals(cls) || ArrayList.class.equals(cls)) {
			return this.prepareStatementList(sql, (List) at);
		} else {
			return this.prepareStatementObj(sql, at);
		}
	}

	/**
	 * Object 계열 prepareStatement(List 와 ArrayList 제외)
	 */
	private PreparedStatement prepareStatementObj(String sql, Object bean) throws MdbException {
		MdbParameters p = null;

		p = setCall(sql, bean);

		return _prepareStatement(p.getInnersql(), p);
	}

	/**
	 * List 계열 prepareStatement
	 * 
	 * @param sql
	 * @param at
	 * @return
	 * @throws MdbException
	 */
	public PreparedStatement prepareStatementList(String sql, List at) throws MdbException {
		PreparedStatement stmt = null;
		try {
			this.chkConnection();
			// if (isScroll) {
			// sql = getLimitSql(sql);
			// }
			stmt = this.conn.prepareStatement(sql);
			logging("prepareStatement SQL :" + sql);
			if (at != null && at.size() > 0) {
				// 값들을 할당 한다.
				for (int i = 0; i < at.size(); i++) {
					Object obj = at.get(i);
					if (obj == null) {
						stmt.setObject(i + 1, "");
					} else {
						if (String.class.equals(obj.getClass())) {
							String data = (String) at.get(i);
							// if (data.length() > 1000 ) {
							StringReader sr = new StringReader(data);
							stmt.setCharacterStream(i + 1, sr, data.length());
							// } else{
							// stmt.setObject(i+1, at.get(i));
							//	       
						} else if (java.util.Date.class.equals(obj.getClass())) {
							Date nDate = (Date) obj;
							java.sql.Date sqldate = new java.sql.Date(nDate.getTime());
							stmt.setDate(i + 1, sqldate);
						} else if (MDate.class.equals(obj.getClass())) {

							Date nDate = (Date) obj;
							java.sql.Date sqldate = new java.sql.Date(nDate.getTime());
							stmt.setDate(i + 1, sqldate);
						} else {
							stmt.setObject(i + 1, obj);
						}
					}

					logging("Bind column " + (i + 1) + " : [" + obj + "]");
				}
			}

		} catch (Exception e) {
			logger.error(sql + "\n" + e.getMessage());
			throw new MdbException(e.getMessage(), e);
		}
		return stmt;
	}

	private CallableStatement _prepareCall(String sql, MdbParameters p) throws MdbException {
		CallableStatement stmt = null;
		try {
			this.chkConnection();
			logging("prepareCall SQL :" + sql);
			stmt = this.conn.prepareCall(sql);
			if (p != null && p.getSize() > 0) {
				// 값들을 할당 한다.
				for (int i = 1; i <= p.getSize(); i++) {
					stmt.setObject(i, p.getValue(i - 1));
					logging(i + " : [" + p.getValue(i - 1) + "]");
				}
			}
		} catch (SQLException e) {
			throw new MdbException(e);
		}
		return stmt;
	}

	/**
	 * 
	 * @param sql
	 * @param bean
	 *            (Map 또는 bean 계열)
	 * @return
	 * @throws MdbException
	 */
	public CallableStatement prepareCall(String sql) throws MdbException {
		return _prepareCall(sql, null);
	}

	public CallableStatement prepareCall(String sql, Object bean) throws MdbException {
		MdbParameters p = null;

		p = setCall(sql, bean);

		return _prepareCall(p.getInnersql(), p);
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
			sb.append(StringEscapeUtils.escapeSql((String)arr[i]));
			sb.append("'");
			chk = true;
		}
		if(!chk) {
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
			sb.append(StringEscapeUtils.escapeSql((String)it.next()));
			sb.append("'");
			i++;
			chk = true;
		}
		if(!chk) {
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
	public Object selectorOne(Class cls, String sql, Object inputarrs) throws MdbException {
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

	/**
	 * sql수행 결과 중 첫 row의 첫값만 가져옮
	 * 
	 * @param sql
	 * @param inputarr :
	 *            파리미터
	 * @return
	 */
	public String selectOne(String sql, Object inputarr) throws MdbException {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		String st = null;
		Object o = null;
		try {
			stmt = this.prepareStatement(sql, inputarr);
			stmt.setFetchSize(1);
			rs = stmt.executeQuery();
			if (rs.next()) {
				o = rs.getObject(1);
				if (o != null) {
					st = o.toString();
				}
			}

		} catch (SQLException e) {
			throw new MdbException("selectOne Error !!! ", e);
		} finally {
			if (rs != null) try {
				rs.close();
				rs = null;
			} catch (SQLException ex) {
			}
			if (stmt != null) try {
				stmt.close();
				stmt = null;
			} catch (SQLException ex) {
			}
			this.chkClose();
		}
		return st;
	}

	public String selectOne(String sql) throws MdbException {
		return selectOne(sql, null);
	}
	/**
	 * sql수행 결과 중 첫 row의 첫값을 int형으로 전환하여 넘겨줌 주의 !!! record가 없으면 0을 넘겨 준다.
	 * 
	 * @param sql
	 * @param inputarr :
	 *            파리미터
	 * @return
	 */
	public int selectOneInt(String sql) throws MdbException {
		return MafUtil.parseInt(selectOne(sql, null), 0);
	}
	/**
	 * sql수행 결과 중 첫 row의 첫값을 int형으로 전환하여 넘겨줌 주의 !!! record가 없으면 0을 넘겨 준다.
	 * 
	 * @param sql
	 * @param inputarr
	 * @return
	 * @throws MdbException
	 */
	public int selectOneInt(String sql, Object inputarr) throws MdbException {
		return MafUtil.parseInt(selectOne(sql, inputarr), 0);
	}

	public long selectOneLong(String sql) throws MdbException {
		return MafUtil.parseLong(selectOne(sql, null), 0);
	}

	public long selectOneLong(String sql, Object inputarr) throws MdbException {
		return MafUtil.parseLong(selectOne(sql, inputarr), 0);
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
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = this.prepareStatement(sql, at);
			rs = stmt.executeQuery();
			return ms.tokenResultSet(rs, this.fetchSize, this.isDebug);
		} catch (Exception e) {
			logger.debug(">> Sql : \n" + sql);
			throw new MdbException(e);
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

		} catch (Exception e) {
			logger.debug("SQL : " + sql);
			throw new MdbException(e);
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

		// int row = (page - 1) * fetchSize; // 찾아갈 로우의 위치
		if (!(offset <= 0)) {

			// logger.debug("absolute move Row : " + offset + ", Page:" + page
			// + ", fetchSize : " + fetchSize);
			rs.absolute(offset);
		}

	}

	protected void logging(String msg) {

		if (this.isDebug) {
			Logging.log(this.getClass(), msg);
		}
	}

	protected synchronized void chkConnection() throws MdbException {
		try {
			if (this.conn == null) {
				this.conn = new SmartConnection(DBResource.getConnection(this.dbPoolName));
				if (this.conn != null) {
					this.conn.setAutoCommit(this.autoCommit);
				} else {
					throw new MdbException("DB Pool [" + this.dbPoolName + "] Connection Failure!!!");
				}
			}
		} catch (Exception e) {
			throw new MdbException("DB Pool [" + this.dbPoolName + "] Connection Failure!!!", e);
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

	protected MdbParameters setCall(String sql, Object bean) throws MdbException {
		Class cls = null;
		boolean hasError = false;
		StringBuffer error = null;
		if (bean != null) {
			cls = bean.getClass();
		}
		int i = 0;
		// List where = null;
		MdbParameters p = null;
		if (sql != null) {
			p = new MdbParameters();
			RE re = new RE("(:[\\w*]+)|(:[@\\w*]+)");
			// where = new ArrayList();
			while (re.match(sql)) {
				sql = re.subst(sql, "?", RE.REPLACE_FIRSTONLY);

				String key = re.getParen(0).substring(1).toLowerCase();
				Object obj = null;
				if (Map.class.equals(cls) || HashMap.class.equals(cls)) {
					// logger.debug("===================== MAP!!!");
					if (((Map) bean).containsKey(key)) {
						obj = ((Map) bean).get(key);
					} else {
						if (error ==null) {
							hasError = true;
							 error = new StringBuffer(50);
							 error.append("Map : Key not found !!!\n < " + key);
						} else {
							error.append(", " + key);
						}
						
					}
				} else {
					// logger.debug("===================== Class : " + cls.toString());
					try {
						Method method = null;
						try {
							method = cls.getDeclaredMethod("get" + StringUtils.capitalize(key), null);
						} catch (Exception e) {
							logger.error("No Such Method Name : " + "get" + StringUtils.capitalize(key));
							throw new NoSuchMethodException("No Such Method Name : " + "get" + StringUtils.capitalize(key));
						}
						Object[] args = null;
						obj = method.invoke(bean, args);
					} catch (Exception e) {
						throw new MdbException(cls + " : get" + StringUtils.capitalize(key) + " method not found");
					}
				}
				p.add(key, obj);
				i++;
				if (obj != null) {
					logging(i + "." + key + ":" + obj.getClass() + " | [" + obj.toString() + "]");
				} else {
					logging(i + "." + key + ": null");
				}
			}
		}
		if(hasError) {
			error.append(" >");
			throw new MdbException(error.toString());
		}
		p.setInnersql(sql);
		return p;
	}

	/**
	 * Sequence의 값을 가져온다. 
	 * 
	 * @param sequencename
	 * @param is_cur
	 * @return
	 * @throws MdbException
	 */
	public String getSequence(String sequenceName) throws MdbException {
		
		return null;
	}
}