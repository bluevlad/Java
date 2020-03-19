package maf.mdb.drivers.support;

import java.io.StringReader;
import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.regexp.RE;
import maf.MafProperties;
import maf.MafUtil;
import maf.exception.MafException;
import maf.lib.calendar.MDate;
import maf.logger.Logging;
import maf.logger.Trace;
import maf.mdb.MdbParameters;
import maf.mdb.drivers.MdbDriverInf;
import maf.mdb.drivers.MdbSelecter;
import maf.mdb.exceptions.MdbException;
import maf.mdb.sqlhelper.SqlWhereBuilder;
import maf.mdb.sqlhelper.support.OracleSqlWhereBuilder;
import maf.util.StringUtils;

public abstract class BaseMdbDriver implements MdbDriverInf {
	private Log logger = LogFactory.getLog(BaseMdbDriver.class);

	public abstract boolean isDebug();

	public abstract Connection getConnection() throws MdbException;

	public SqlWhereBuilder getWhereBuilder() {
		return new OracleSqlWhereBuilder();
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

	public void _setDebug(boolean isDebug) {
		if (MafProperties.DEBUG) {
			logger.debug(" >>> turn debug info is " + isDebug);
			if (isDebug == true) {
				try {
					throw new MafException("mdb debug mode on at ---");
				} catch (MafException e) {
					String t = maf.logger.Trace.getStackTrace(e);
					if (t != null) {
						System.out.println(t.substring(0, 300));
					}
				}
			}
		}
	}

	protected void logging(String msg) {
		if (isDebug()) {
			Logging.log(this.getClass(), msg);
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
		return _executeUpdate(sql, bean);
	}

	protected List _select(MdbSelecter ms, String sql, Object at, int fetchSize)
	        throws MdbException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = this.prepareStatement(sql, at);
			rs = stmt.executeQuery();
			return ms.tokenResultSet(rs, fetchSize, isDebug());
		} catch (Exception e) {
			logger.debug(">> Sql Error : \n" + sql);
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
		}
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
			logger.error(e.getMessage() + " \nSQL : " + sql);
			throw new MdbException("selectOne Error !!! : ", e);
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

	private PreparedStatement _prepareStatement(String sql, MdbParameters p)
	        throws MdbException {
		PreparedStatement stmt = null;
		try {
			logging("PreparedStatement SQL : \n " + sql);
			stmt = getConnection().prepareStatement(sql);
			if (p != null && p.getSize() > 0) {
				// 값들을 할당 한다.
				int cnt = p.getSize();
				for (int i = 0; i < cnt; i++) {
					Object obj = p.getValue(i);
					String colName = p.getColumn(i);
					if (obj != null) {
						logging(""
						        + i
						        + "."
						        + colName
						        + " : ["
						        + obj.getClass()
						        + "] = ["
						        + obj.toString()
						        + "]");
						//						if (obj.getClass().equals(java.util.Date.class)) {
						if (obj instanceof java.util.Date) {
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

	/**
	 * Object 계열 prepareStatement(List 와 ArrayList 제외)
	 */
	private PreparedStatement prepareStatementObj(String sql, Object bean)
	        throws MdbException {
		MdbParameters p = null;
		p = setCall(sql, bean);
		return _prepareStatement(p.getInnersql(), p);
	}

	/**
	 * Update, Delete, Insert 를 수행 하고 적용된 record수를 return 한다.
	 */
	protected int _executeUpdate(String sql, Object bean) throws MdbException {
		PreparedStatement stmt = null;
		try {
			stmt = this.prepareStatement(sql, bean);
			int cnt = -1;
			if (stmt != null) {
				cnt = stmt.executeUpdate();
			} else {
				throw new MdbException("statement is null)");
			}
			logging(cnt + " record('s') affected ");
			return cnt;
		} catch (Exception e) {
			
			logger.debug(e.getClass()
			        + " / msg:"
			        + e.getMessage()
			        + " \n >> Sql : \n"
			        + MafUtil.nvl(sql, "NULL")
			        + "\n"
			        + bean);
			Logging.trace(e);
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

	/**
	 * List 계열 prepareStatement
	 * 
	 * @param sql
	 * @param at
	 * @return
	 * @throws MdbException
	 */
	public PreparedStatement prepareStatementList(String sql, List at)
	        throws MdbException {
		PreparedStatement stmt = null;
		try {
			stmt = getConnection().prepareStatement(sql);
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

	private PreparedStatement _prepareStatement(String sql, MdbParameters p,
	        Connection conn) throws MdbException {
		PreparedStatement stmt = null;
		try {
			logging("PreparedStatement SQL : \n " + sql);
			stmt =conn.prepareStatement(sql);
			if (p != null && p.getSize() > 0) {
				// 값들을 할당 한다.
				int cnt = p.getSize();
				for (int i = 0; i < cnt; i++) {
					Object obj = p.getValue(i);
					String colName = p.getColumn(i);
					if (obj != null) {
						logging(""
						        + i
						        + "."
						        + colName
						        + " : ["
						        + obj.getClass()
						        + "] = ["
						        + obj.toString()
						        + "]");
						//						if (obj.getClass().equals(java.util.Date.class)) {
						if (obj instanceof java.util.Date) {
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

	protected CallableStatement _prepareCall(String sql, MdbParameters p)
	        throws MdbException {
		CallableStatement stmt = null;
		try {
			logging("prepareCall SQL :" + sql);
			stmt = getConnection().prepareCall(sql);
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
	 * Transaction을 Commit 한다 .
	 * 
	 * @throws MdbException
	 */
	public void commit() throws MdbException {
		try {
			getConnection().commit();
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
			Connection conn = getConnection();
			if (conn != null) {
				conn.rollback();
				logging("Roll Back !!");
			}
		} catch (MdbException _ignored) {
		} catch (SQLException _ignored) {
		}
	}

	/**
	 * 데이터를 SELECT 할 위치로 커서를 이동시킨다. <br>
	 * JDBC 2.0 API 를 이용하였음.
	 */
	protected void _moveCursor(int offset, ResultSet rs) throws SQLException {
		// int row = (page - 1) * fetchSize; // 찾아갈 로우의 위치
		if (!(offset <= 0)) {
			// logger.debug("absolute move Row : " + offset + ", Page:" + page
			// + ", fetchSize : " + fetchSize);
			rs.absolute(offset);
		}
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
						if (error == null) {
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
							method = cls.getDeclaredMethod("get"
							        + StringUtils.capitalize(key), null);
						} catch (Exception e) {
							logger.error("No Such Method Name : "
							        + "get"
							        + StringUtils.capitalize(key));
							throw new NoSuchMethodException("No Such Method Name : "
							        + "get"
							        + StringUtils.capitalize(key));
						}
						Object[] args = null;
						obj = method.invoke(bean, args);
					} catch (Exception e) {
						throw new MdbException(cls
						        + " : get"
						        + StringUtils.capitalize(key)
						        + " method not found");
					}
				}
				p.add(key, obj);
				i++;
				if (obj != null) {
					logging(i
					        + "."
					        + key
					        + ":"
					        + obj.getClass()
					        + " | ["
					        + obj.toString()
					        + "]");
				} else {
					logging(i + "." + key + ": null");
				}
			}
		}
		if (hasError) {
			error.append(" >");
			throw new MdbException(error.toString());
		}
		p.setInnersql(sql);
		return p;
	}
}
