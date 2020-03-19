/*
 * Created on 2005. 6. 29.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.common.dbsync;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import maf.logger.Trace;
import maf.mdb.Mdb;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;
import modules.common.dbsync.beans.DbSyncConfig;
import modules.common.dbsync.beans.DbSyncTable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author UBQ
 * 
 * 주기적으로 MS-SQL 데이타와 Oracle 데이타를 동기화함
 *  
 */
public class DbSyncThread implements Runnable {
    private  Log logger = LogFactory.getLog(this.getClass());



	private static boolean onlyOne = true;
	static private DbSyncThread instance = null;
	private DbSyncConfig configuration = null;

	private Thread t = null;
	private boolean active = false;
	private String error_message = null;
	
	private int sleepTime = 1*1000; // milisecond
	
	/**
	 * Get Single Instance
	 */
	public static synchronized DbSyncThread getInstance() {
		if (instance == null) {
			instance = new DbSyncThread();
		}
		return instance;
	}

	private DbSyncThread() {
		
	}

	public void setSyncConfig(DbSyncConfig configuration) {
		this.configuration = configuration;
	}

	public void run() {
		int cnt = 0;
		
		System.out.println(":==========:DB SyncThread Start()");
		try {
			while (active) {
				cnt = 0;
				if (onlyOne) {
//					System.out.println(":==========:DB SyncThread folk() " + t.toString());
					cnt = sync();
					if (cnt > 0 ) {
						System.out.println(" << " + cnt + " records sync !!" );
					}
				} else {
//					System.out.println(":==========:DB SyncThread pass ");
				}
				Thread.sleep(sleepTime); // milisecond				
			}
		} catch (InterruptedException i) {
		}
		t = null;
		System.out.println(":==========:DB SyncThread Stop()");
	}

	public boolean start() {
		error_message = null;
		if(t == null) {
			t = new Thread(this);
		}
		if(t != null) {
			active = true;
			if(!t.isAlive()) {
				t.start();
			}
			return t.isAlive();
		} else {
			return false;
		}
	}
	
	public void stop() {
		active=false;
	}

	/**
	 * 현상태의 에러 메시지를 돌려 준다.
	 * @return
	 */
	public String getErrorMessage() {
		return this.error_message;
	}
	
	public boolean isAlive() {
		if (t == null) {
			return false;
		} else {
			return t.isAlive();
		}
	}

	private synchronized int sync() {
		//sync()가 실행되고 있을때 또 다시 sync()를 호출할 수 없도록
		onlyOne = false;

		int cnt = 0;
		boolean chk = false;
//		ResultSetMetaData rsmd = null;
		PreparedStatement dstmt = null;
		Connection sqlConn = null;		
//		PreparedStatement ostmt = null;
		
		MdbDriver oradb = null;
		MdbDriver sqldb = null;
		
		
		String table_nm = null;
		String cmd = null;
		String pkCols = null;
		String pkVals = null;
		String seq = null;

		final String  selectSql = "SELECT top 100  seq, table_nm, pk_cols,  pk_vals, status, cmd "
				+ " FROM SYNC_TABLE where status ='F' AND seq > 0 order by seq ";

		final String sqldelete = "delete from SYNC_TABLE  where seq = ?";

		
		try {
			oradb = Mdb.getMdbDriver();
			sqldb = Mdb.getMdbDriver();
//			sqldb = Mdb.getMdbDriver("kaissql", MdbDriver.DBMS_SQL2000);
			
			//sqldb.setDebug(true);
			//oradb.setDebug(true);
			oradb.setAutoCommit(false);
			sqldb.setAutoCommit(false);

			sqlConn= sqldb.getConnection();

			List srcList = sqldb.selector(Map.class, selectSql);
			
			if(srcList != null) {
				for(int idx = 0; idx < srcList.size(); idx ++) {
					chk = false;
					Map record = (Map) srcList.get(idx);
					
					table_nm = (String) record.get("table_nm");
					table_nm = table_nm.toLowerCase();
					cmd = (String) record.get("cmd");
					pkCols = (String) record.get("pk_cols");
					pkVals = (String) record.get("pk_vals");

					seq = ((Long) record.get("seq")).toString();
					
					if("t_userinfo".equals(table_nm)) {
						chk = syncUserInfo(cmd, pkVals, sqldb, oradb );
					} else {
						chk = sync_oneRow(table_nm, cmd, pkCols, pkVals, sqldb, oradb );
					}

					if (chk) {
						dstmt = sqlConn.prepareStatement(sqldelete);
						dstmt.setString(1, seq);
//						System.out.println( r + " record deleted");
						sqlConn.commit();
						oradb.commit();
						cnt ++;
					} else {
						logger.debug("RollBack!!!");
						sqlConn.rollback();
						oradb.rollback();
						break;
					}

				}
				chk = true;
			}

		} catch (Throwable e) {
			logger.debug(Trace.getStackTrace(e));
			if (oradb != null)oradb.rollback();
			if (sqldb != null)sqldb.rollback();
		} finally {
			if (oradb != null) {try {oradb.close();} catch (Exception e) {};}
			if (sqldb != null) {try {sqldb.close();} catch (Exception e) {};}
			oradb = null;
			sqldb = null;
			this.active = (active && chk)?true:false;
		}
		onlyOne = true;
		return cnt;
	}

	private boolean sync_oneRow(String table_nm, String cmd, String pkCols, String pkVals, MdbDriver sDb, MdbDriver tDb) {
		boolean chk = false;
		String keys[] = pkCols.split(":");
		String vals[] = pkVals.split(":");
		Map keyMap = new HashMap();
		Map srcRecord = null;
		int cnt = 0;
		DbSyncTable syncTable = this.configuration.get(table_nm);
		for (int i = 0; i < keys.length; i++) {
			keyMap.put(keys[i], vals[i]);
			//logger.debug("key: " + keys[i] + " val:" + vals[i]);
		}
		try {
			if ("D".equals(cmd)) {
				cnt = tDb.executeUpdate(syncTable.getDeleteSql(), keyMap);
			} else if ("I".equals(cmd)) {
				srcRecord = (Map) sDb.selectorOne(Map.class, syncTable.getSelectSql(), keyMap);
				
				cnt = tDb.executeUpdate(syncTable.getInsertSql(), srcRecord);
			} else if ("U".equals(cmd)) {
				srcRecord = (Map) sDb.selectorOne(Map.class, syncTable.getSelectSql(), keyMap);
				cnt = tDb.executeUpdate(syncTable.getUpdateSql(), srcRecord);
			}
			chk = (cnt > 0 )?true:false;
		} catch (MdbException e) {
			error_message = e.getMessage();
			logger.error( cmd + " - Error " + e.getMessage());
			chk = false;
		}

		return chk;
	}
	private boolean syncUserInfo(String cmd, String user_id, MdbDriver sDb, MdbDriver tDb) {
		final String selectSql = " SELECT PASSWORD, user_id, juso1, jumin2,"   +
			"        mobile1 + '-' + mobile2 + '-' + mobile3 mobile,"   +
			"        zipno1 + '-' + zipno2 zipno, user_hname, email, jumin1,"   +
			"        jumin1 + '-' + jumin2 jumin, juso2"   +
			"   FROM t_userinfo"   +
			"  WHERE user_id = :user_id"  ; 
		final String mergeSql = " MERGE INTO mst_user t1"   +
			"      USING (SELECT :PASSWORD passwd, :user_id user_id, :juso1 juso1,"   +
			"                    :jumin2 jumin2, :mobile mobile, :zipno zipno,"   +
			"                    :user_hname user_hname, :email email, :jumin1 jumin1,"   +
			"                    :jumin jumin, :juso2 juso2"   +
			"               FROM DUAL) s1"   +
			"         ON (t1.userid = s1.user_id)"   +
			"    WHEN MATCHED THEN"   +
			"       UPDATE"   +
			"          SET passwd = s1.passwd, address_1 = s1.juso1, pin_2 = s1.jumin2,"   +
			"              mobile = s1.mobile, zip = s1.zipno, nm = s1.user_hname,"   +
			"              email = s1.email, pin_1 = s1.jumin1, pin = s1.jumin,"   +
			"              address_2 = s1.juso2"   +
			"    WHEN NOT MATCHED THEN"   +
			"       INSERT (passwd, address_1, pin_2, mobile, zip, nm, email, pin_1, pin,"   +
			"               address_2, usn, src, userid)"   +
			"       VALUES (s1.passwd, s1.juso1, s1.jumin2, s1.mobile, s1.zipno,"   +
			"               s1.user_hname, s1.email, s1.jumin1, s1.jumin, s1.juso2,"   +
			"               seq_usn.NEXTVAL, 'K', s1.user_id)"  ;
//		final String deleteSql = "DELETE FROM mst_user WHERE userid = :user_id ";
		final String deleteSql = "UPDATE mst_user SET userid = null, pin = null WHERE userid = :user_id ";
		boolean chk = false;

		Map keyMap = new HashMap();
		Map srcRecord = null;
		int cnt = 0;
		keyMap.put("user_id", user_id);
		try {
			if ("D".equals(cmd)) {
				cnt = tDb.executeUpdate(deleteSql, keyMap);
				cnt = 1;
			} else if ("I".equals(cmd) || "U".equals(cmd) ) {
				srcRecord = (Map) sDb.selectorOne(Map.class, selectSql, keyMap);
				
				cnt = tDb.executeUpdate(mergeSql, srcRecord);
			}
			chk = (cnt > 0 )?true:false;
			if(cnt == 0) {
				System.out.println(cmd + "," + user_id + " fail");
			}
		} catch (MdbException e) {
			logger.error( cmd + " - Error " + e.getMessage());
			System.out.println(cmd + "," + user_id + " fail");
			chk = false;
		}
		return chk;
	}
}
