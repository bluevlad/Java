package maf.mdb.drivers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import maf.mdb.exceptions.MdbException;

/**
 * JDBC Meta Driver 참조 : PHPAdoDriver
 * 
 * @author bluevlad
 *
 */

public interface MdbDriverInf {

	/**
	 * 데이타베이스유형 ID
	 */
	public static final int DMBS_JDBC = 0;

	/**
	 * Oracle OCI8
	 */
	public static final int DBMS_OCI8 = 10;

	public static final int DBMS_SQL2000 = 20;
	
	/**
	 * MY SQL
	 */
    public static final int DBMS_MYSQL = 30;
	/**
	 * 데이타베이스유형 ID를 리턴한다.
	 * 
	 * @return
	 */
	public int getDatabaseID();

	public void setDebug(boolean isDebug);

	public boolean isConnected();

	/**
	 * Database에 접속 한다.
	 * 
	 * @param dbpoolname
	 */
	public void setDbPoolname(String dbpoolname);

	// public void connect(String dbpoolname) ;
	// public void connect() ;
	public void close();

//	/**
//	 * Database Connection을 넘겨 준다.
//	 * 
//	 * @return
//	 * @throws MdbException
//	 */
//	public Connection getConnection() throws MdbException;

	/**
	 * Connection의 AutoCommit 상태를 전환 한다.
	 * 
	 * @param AutoCommit
	 * @throws MdbException
	 */
	public void setAutoCommit(boolean AutoCommit) throws MdbException;

	/**
	 * 현재의 AutoCommit 상태를 돌려 준다.
	 * 
	 * @return
	 * @throws MdbException
	 */
	public boolean getAutoCommit() throws MdbException;

	/**
	 * Transaction을 Commit 한다 .
	 * 
	 * @throws MdbException
	 */
	public void commit() throws MdbException;

	/**
	 * Transaction을 Rollback 한다.
	 * 
	 */
	public void rollback();

	// public ResultSet executeQuery(String sql, List at ) throws MdbException;
	// public ResultSet executeQuery(String sql ) throws MdbException;
	/**
	 * Update, Delete, Insert 를 수행 하고 적용된 record수를 return 한다.
	 */
	public int executeUpdate(String sql, Object bean) throws MdbException;

	public PreparedStatement prepareStatement(String sql) throws MdbException;

	/**
	 * 
	 * @param sql
	 * @param bean :
	 *            List(ArrayList-하위 호환성 )와 Map 또는 Bean 으로 구분 됨
	 * @return
	 * @throws MdbException
	 */
	public PreparedStatement prepareStatement(String sql, Object bean) throws MdbException;

	public CallableStatement prepareCall(String sql) throws MdbException;

	/**
	 * 
	 * @param sql
	 * @param bean
	 *            (Map 또는 bean 계열)
	 * @return
	 * @throws MdbException
	 */
	public CallableStatement prepareCall(String sql, Object bean) throws MdbException;

	/**
	 * 가져올 페이지 설정
	 * 
	 * @param page :
	 *            가져올 페이지
	 * @param fetchSize :
	 *            페이지당 row수
	 */
	public void setPage(int page, int fetchSize);

	/**
	 * 가져오기 제한 설정
	 * 
	 * @param fetchSize
	 *            가져올 Row의 수
	 * @param offset :
	 *            건너띌 수
	 */
	public void setLimit(int fetchSize, int offset);

	/**
	 * 가져올 Row의 수 제한
	 * 
	 * @param maxRows
	 */
	public void setLimit(int maxRows);

	/**
	 * cls(Bean 또는 Map) 에 sql 수행 결과를 담아 List 형태로 돌려 준다.
	 * 
	 * @param cls
	 * @param sql
	 * @return
	 * @throws MdbException
	 */
	public List selector(Class cls, String sql) throws MdbException;

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
	public List selector(Class cls, String sql, Object at) throws MdbException;

	/**
	 * Result Set을 cls에 List 형태로 돌려 준다.
	 * 
	 * @param cls
	 * @param rs
	 * @return
	 * @throws MdbException
	 */
	public List selector(Class cls, ResultSet rs) throws MdbException;

	/**
	 * sql수행 결과 중 첫 row의 첫값만 가져옮 주의 !!! record가 없으면 null을 넘겨 준다.
	 * 
	 * @param sql
	 * @return
	 */
	public String selectOne(String sql) throws MdbException;

	/**
	 * sql수행 결과 중 첫 row의 첫값만 가져옮 주의 !!! record가 없으면 null을 넘겨 준다.
	 * 
	 * @param sql
	 * @param inputarr(
	 *            List(하위호환성), Map(HashMap) 또는 bean )
	 * @return
	 * @throws MdbException
	 */
	public String selectOne(String sql, Object inputarr) throws MdbException;

	/**
	 * sql수행 결과 중 첫 row의 첫값을 int형으로 전환하여 넘겨줌 주의 !!! record가 없으면 0을 넘겨 준다.
	 * 
	 * @param sql
	 * @param inputarr :
	 *            파리미터
	 * @return
	 */
	public int selectOneInt(String sql) throws MdbException;

	/**
	 * sql수행 결과 중 첫 row의 첫값을 int형으로 전환하여 넘겨줌 주의 !!! record가 없으면 0을 넘겨 준다.
	 * 
	 * @param sql
	 * @param inputarr
	 * @return
	 * @throws MdbException
	 */
	public int selectOneInt(String sql, Object inputarr) throws MdbException;

	/**
	 * sql수행 결과 중 첫 row의 첫값을 long 형으로 전환하여 넘겨줌 주의 !!! record가 없으면 0을 넘겨 준다.
	 * 
	 * @param sql
	 * @param inputarr
	 * @return
	 * @throws MdbException
	 */
	public long selectOneLong(String sql, Object inputarr) throws MdbException;

	/**
	 * sql수행 결과 중 첫 row의 첫값을 long 형으로 전환하여 넘겨줌 주의 !!! record가 없으면 0을 넘겨 준다.
	 * 
	 * @param sql
	 * @param inputarr :
	 *            파리미터
	 * @return
	 */
	public long selectOneLong(String sql) throws MdbException;

	/**
	 * 첫번재 Row 만 class에 담아 넘김
	 * 
	 * @param cls
	 * @param rs
	 * @return
	 * @throws MdbException
	 */
	public Object selectorOne(Class cls, ResultSet rs) throws MdbException;

	public Object selectorOne(Class cls, String sql) throws MdbException;

	/**
	 * 
	 * @param cls
	 * @param sql
	 * @param inputarrs (
	 *            List(하위호환성), Map(HashMap) 또는 bean )
	 * @return
	 * @throws MdbException
	 */
	public Object selectorOne(Class cls, String sql, Object inputarrs) throws MdbException;

	/**
	 * Connection을 Rollback 하고 Close한후 null 처리
	 * 
	 */
	public void destructConn();

	/**
	 * Sequence의 NEXTVAL값을 가져온다. (Oracle 만 적용됨 다른 DB는 null을 Return)
	 * 
	 * @param sequencename
	 * @return
	 * @throws MdbException
	 */
	public String getSequence(String sequenceName) throws MdbException;

}
