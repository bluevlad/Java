/*
 * 작성된 날짜: 2005-02-02
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
	 * 사용자 List를 돌려 준다.
	 * @param oDb
	 * @return String List
	 * @throws MdbException
	 */
    public DbUserBean[] getUserList(MdbDriver oDb) throws MdbException;
    
    /**
     * 사용자의 Table List를 돌려 준다.
     * @param oDb
     * @param owner
     * @return
     * @throws MdbException
     */
    public List getTableList(MdbDriver oDb, String owner) throws MdbException;
    
    /**
     * 사용자의 View List를 돌려 준다.
     * @param oDb
     * @param owner
     * @return
     * @throws MdbException
     */
    public List getViewList(MdbDriver oDb, String owner) throws MdbException;
    
    /**
     * 사용자의 Proc List를 돌려 준다.
     * @param oDb
     * @param owner
     * @return
     * @throws MdbException
     */
    public List getProcList(MdbDriver oDb, String owner) throws MdbException;    
    

    /**
     * 사용자의 Proc Source를 List로 돌려 준다.
     * @param owner
     * @param type
     * @param name
     * @return
     * @throws MdbException
     */
    public List getProcSource(MdbDriver oDb, String owner, String type, String name) throws MdbException;        
    
    /**
     * table의 Comments 를 가져온다.
     * @param oDb
     * @param table_name
     * @return
     * @throws MdbException
     */
    public String getTableComment(MdbDriver oDb, String owner, String table_name) throws MdbException ;
    
    /**
     * Table의 Column 정보를 돌려준다.
     * @param oDb
     * @param owner
     * @param table_name
     * @return  List [ColumnBean]
     * @throws MdbException
     */
    public List getColumnInfo(MdbDriver oDb, String owner, String table_name) throws MdbException;

    public List getColumnInfobySQL(MdbDriver oDb, String owner, String sql) throws MdbException;
    
    /**
     * Index 정보를 돌려 준다.
     * @param owner
     * @param type
     * @param indexName
     * @return
     * @throws MdbException
     */
    	public List getIndexInfo(MdbDriver oDb, String owner, String table_name) throws MdbException;
}
