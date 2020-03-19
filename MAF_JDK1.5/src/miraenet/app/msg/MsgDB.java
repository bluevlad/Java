/*
 * Created on 2006. 08. 17
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.msg;



import java.util.List;
import java.util.Map;

import maf.beans.NavigatorInfo;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MsgDB {
	private static MsgDB  instance;
	private Log logger = LogFactory.getLog(MsgDB.class);
	
	private MsgDB() {
	}

	public static synchronized MsgDB  getInstance() {
		if (instance == null) instance = new MsgDB();
		return instance;
	}
	
	/**
	 * 목록 가져오기
	 * @param oDb
	 * @param param
	 * @param sql
	 * @return
	 * @throws MdbException
	 */
	public void getList(MdbDriver oDb, NavigatorInfo navigator, Map param, String sWhere) throws MdbException {
		
		List list = null;

		final String sql = " select * from MSG_RCV ";
		
		String sOrder = navigator.getOrderSql();

		oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
		oDb.setDebug(true);
		list = oDb.selector(Map.class, sql + sWhere + sOrder, param);
		navigator.setList(list);
		navigator.setTotalCount(this.getRecordCount(oDb, param, sWhere.toString()));
		navigator.sync();
		
	}
	
	/**
	 * 레코드 카운트 가져오기
	 * 
	 * @param oDb
	 * @param param
	 * @param sWhere
	 * @return
	 * @throws MdbException
	 */
	private long getRecordCount(MdbDriver oDb, Map param, String sWhere) throws MdbException {
		final  String sql = "select count(*) from MSG_RCV " + sWhere;
		return oDb.selectOneInt(sql, param);
	}
	
	/**
	 * 하나의 레코드 읽어 오기
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public Map getOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "select * from MSG_RCV " 
		             + "where   msgid = :msgid  ";
		return (Map) oDb.selectorOne(Map.class, sql, param);
	}

	
	/**
	 * 하나의 레코드를 Update 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public int updateOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " update MSG_RCV set "   +
			"msgid = :msgid  ,  " +
			"usn_snd = :usn_snd  ,  " +
			"usn_rcv = :usn_rcv  ,  " +
			"nm_snd = :nm_snd  ,  " +
			"nm_rcv = :nm_rcv  ,  " +
			"title = :title  ,  " +
			"contents = :contents  ,  " +
			"reg_dt = :reg_dt  ,  " +
			"rcv_dt = :rcv_dt  ,  " +
			"deleted = :deleted   " +
			
	            "where  msgid = :msgid  and  usn_snd = :usn_snd  and  usn_rcv = :usn_rcv  ";
		return oDb.executeUpdate(sql, param);
	}
	
	/**
	 * 하나의 레코드를 Insert 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public int insertOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " INSERT INTO MSG_RCV"   +
		"    ( msgid, usn_snd, usn_rcv, nm_snd, nm_rcv, title, contents, reg_dt, rcv_dt, deleted)"   +
		"  VALUES"   +
		"    ( :msgid, :usn_snd, :usn_rcv, :nm_snd, :nm_rcv, :title, :contents, :reg_dt, :rcv_dt, :deleted)"  ;
		
		return oDb.executeUpdate(sql, param);
	}
	
}


		