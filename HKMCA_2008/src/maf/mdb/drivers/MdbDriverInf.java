package maf.mdb.drivers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import maf.mdb.exceptions.MdbException;

/**
 * JDBC Meta Driver ���� : PHPAdoDriver
 * 
 * @author bluevlad
 *
 */

public interface MdbDriverInf {

	/**
	 * ����Ÿ���̽����� ID
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
	 * ����Ÿ���̽����� ID�� �����Ѵ�.
	 * 
	 * @return
	 */
	public int getDatabaseID();

	public void setDebug(boolean isDebug);

	public boolean isConnected();

	/**
	 * Database�� ���� �Ѵ�.
	 * 
	 * @param dbpoolname
	 */
	public void setDbPoolname(String dbpoolname);

	// public void connect(String dbpoolname) ;
	// public void connect() ;
	public void close();

//	/**
//	 * Database Connection�� �Ѱ� �ش�.
//	 * 
//	 * @return
//	 * @throws MdbException
//	 */
//	public Connection getConnection() throws MdbException;

	/**
	 * Connection�� AutoCommit ���¸� ��ȯ �Ѵ�.
	 * 
	 * @param AutoCommit
	 * @throws MdbException
	 */
	public void setAutoCommit(boolean AutoCommit) throws MdbException;

	/**
	 * ������ AutoCommit ���¸� ���� �ش�.
	 * 
	 * @return
	 * @throws MdbException
	 */
	public boolean getAutoCommit() throws MdbException;

	/**
	 * Transaction�� Commit �Ѵ� .
	 * 
	 * @throws MdbException
	 */
	public void commit() throws MdbException;

	/**
	 * Transaction�� Rollback �Ѵ�.
	 * 
	 */
	public void rollback();

	// public ResultSet executeQuery(String sql, List at ) throws MdbException;
	// public ResultSet executeQuery(String sql ) throws MdbException;
	/**
	 * Update, Delete, Insert �� ���� �ϰ� ����� record���� return �Ѵ�.
	 */
	public int executeUpdate(String sql, Object bean) throws MdbException;

	public PreparedStatement prepareStatement(String sql) throws MdbException;

	/**
	 * 
	 * @param sql
	 * @param bean :
	 *            List(ArrayList-���� ȣȯ�� )�� Map �Ǵ� Bean ���� ���� ��
	 * @return
	 * @throws MdbException
	 */
	public PreparedStatement prepareStatement(String sql, Object bean) throws MdbException;

	public CallableStatement prepareCall(String sql) throws MdbException;

	/**
	 * 
	 * @param sql
	 * @param bean
	 *            (Map �Ǵ� bean �迭)
	 * @return
	 * @throws MdbException
	 */
	public CallableStatement prepareCall(String sql, Object bean) throws MdbException;

	/**
	 * ������ ������ ����
	 * 
	 * @param page :
	 *            ������ ������
	 * @param fetchSize :
	 *            �������� row��
	 */
	public void setPage(int page, int fetchSize);

	/**
	 * �������� ���� ����
	 * 
	 * @param fetchSize
	 *            ������ Row�� ��
	 * @param offset :
	 *            �ǳʶ� ��
	 */
	public void setLimit(int fetchSize, int offset);

	/**
	 * ������ Row�� �� ����
	 * 
	 * @param maxRows
	 */
	public void setLimit(int maxRows);

	/**
	 * cls(Bean �Ǵ� Map) �� sql ���� ����� ��� List ���·� ���� �ش�.
	 * 
	 * @param cls
	 * @param sql
	 * @return
	 * @throws MdbException
	 */
	public List selector(Class cls, String sql) throws MdbException;

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
	public List selector(Class cls, String sql, Object at) throws MdbException;

	/**
	 * Result Set�� cls�� List ���·� ���� �ش�.
	 * 
	 * @param cls
	 * @param rs
	 * @return
	 * @throws MdbException
	 */
	public List selector(Class cls, ResultSet rs) throws MdbException;

	/**
	 * sql���� ��� �� ù row�� ù���� ������ ���� !!! record�� ������ null�� �Ѱ� �ش�.
	 * 
	 * @param sql
	 * @return
	 */
	public String selectOne(String sql) throws MdbException;

	/**
	 * sql���� ��� �� ù row�� ù���� ������ ���� !!! record�� ������ null�� �Ѱ� �ش�.
	 * 
	 * @param sql
	 * @param inputarr(
	 *            List(����ȣȯ��), Map(HashMap) �Ǵ� bean )
	 * @return
	 * @throws MdbException
	 */
	public String selectOne(String sql, Object inputarr) throws MdbException;

	/**
	 * sql���� ��� �� ù row�� ù���� int������ ��ȯ�Ͽ� �Ѱ��� ���� !!! record�� ������ 0�� �Ѱ� �ش�.
	 * 
	 * @param sql
	 * @param inputarr :
	 *            �ĸ�����
	 * @return
	 */
	public int selectOneInt(String sql) throws MdbException;

	/**
	 * sql���� ��� �� ù row�� ù���� int������ ��ȯ�Ͽ� �Ѱ��� ���� !!! record�� ������ 0�� �Ѱ� �ش�.
	 * 
	 * @param sql
	 * @param inputarr
	 * @return
	 * @throws MdbException
	 */
	public int selectOneInt(String sql, Object inputarr) throws MdbException;

	/**
	 * sql���� ��� �� ù row�� ù���� long ������ ��ȯ�Ͽ� �Ѱ��� ���� !!! record�� ������ 0�� �Ѱ� �ش�.
	 * 
	 * @param sql
	 * @param inputarr
	 * @return
	 * @throws MdbException
	 */
	public long selectOneLong(String sql, Object inputarr) throws MdbException;

	/**
	 * sql���� ��� �� ù row�� ù���� long ������ ��ȯ�Ͽ� �Ѱ��� ���� !!! record�� ������ 0�� �Ѱ� �ش�.
	 * 
	 * @param sql
	 * @param inputarr :
	 *            �ĸ�����
	 * @return
	 */
	public long selectOneLong(String sql) throws MdbException;

	/**
	 * ù���� Row �� class�� ��� �ѱ�
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
	 *            List(����ȣȯ��), Map(HashMap) �Ǵ� bean )
	 * @return
	 * @throws MdbException
	 */
	public Object selectorOne(Class cls, String sql, Object inputarrs) throws MdbException;

	/**
	 * Connection�� Rollback �ϰ� Close���� null ó��
	 * 
	 */
	public void destructConn();

	/**
	 * Sequence�� NEXTVAL���� �����´�. (Oracle �� ����� �ٸ� DB�� null�� Return)
	 * 
	 * @param sequencename
	 * @return
	 * @throws MdbException
	 */
	public String getSequence(String sequenceName) throws MdbException;

}
