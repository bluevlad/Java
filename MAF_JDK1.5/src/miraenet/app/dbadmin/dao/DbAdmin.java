/*
 * �ۼ��� ��¥: 2005-02-02
 *
 */
package miraenet.app.dbadmin.dao;

import java.util.List;

import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;
import miraenet.app.dbadmin.beans.DbUserBean;

/**
 * @author goindole, 2005-02-02
 *
 */
public interface DbAdmin {
	/**
	 * ����� List�� ���� �ش�.
	 * @param oDb
	 * @return String List
	 * @throws MdbException
	 */
    public DbUserBean[] getUserList(MdbDriver oDb) throws MdbException;
    
    /**
     * ������� Table List�� ���� �ش�.
     * @param oDb
     * @param owner
     * @return
     * @throws MdbException
     */
    public List getTableList(MdbDriver oDb, String owner) throws MdbException;
    
    /**
     * ������� View List�� ���� �ش�.
     * @param oDb
     * @param owner
     * @return
     * @throws MdbException
     */
    public List getViewList(MdbDriver oDb, String owner) throws MdbException;
    
    /**
     * ������� Proc List�� ���� �ش�.
     * @param oDb
     * @param owner
     * @return
     * @throws MdbException
     */
    public List getProcList(MdbDriver oDb, String owner) throws MdbException;    
    

    /**
     * ������� Proc Source�� List�� ���� �ش�.
     * @param owner
     * @param type
     * @param name
     * @return
     * @throws MdbException
     */
    public List getProcSource(MdbDriver oDb, String owner, String type, String name) throws MdbException;        
    
    /**
     * table�� Comments �� �����´�.
     * @param oDb
     * @param table_name
     * @return
     * @throws MdbException
     */
    public String getTableComment(MdbDriver oDb, String owner, String table_name) throws MdbException ;
    
    /**
     * Table�� Column ������ �����ش�.
     * @param oDb
     * @param owner
     * @param table_name
     * @return  List [ColumnBean]
     * @throws MdbException
     */
    public List getColumnInfo(MdbDriver oDb, String owner, String table_name) throws MdbException;

    public List getColumnInfobySQL(MdbDriver oDb, String owner, String sql) throws MdbException;
    
    /**
     * Index ������ ���� �ش�.
     * @param owner
     * @param type
     * @param indexName
     * @return
     * @throws MdbException
     */
    	public List getIndexInfo(MdbDriver oDb, String owner, String table_name) throws MdbException;
}
