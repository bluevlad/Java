/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.event;

import java.util.List;
import java.util.Map;

import maf.beans.NavigatorInfo;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.drivers.MdbOCI8;
import maf.mdb.exceptions.MdbException;

public class eventDB {
	
	/**
	 * 목록 가져오기
	 * @param oDb
	 * @param param
	 * @param sql
	 * @return
	 * @throws MdbException
	 */
	public static void getList(MdbDriver oDb, NavigatorInfo navigator, Map param, String sWhere) throws MdbException {

		List list = null;

		final String sql = "select * from (select * from EVENT) x ";

		String sOrder = navigator.getOrderSql();

		oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
		oDb.setDebug(true);
		list = oDb.selector(Map.class, sql + sWhere + sOrder, param);
		navigator.setList(list);
		navigator.setTotalCount(getRecordCount(oDb, param, sWhere.toString()));
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
	private static long getRecordCount(MdbDriver oDb, Map param, String sWhere)
	throws MdbException {
		
		final String sql = "select count(*) from" +
		" (select *  FROM EVENT) x " + sWhere;
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
	public static Map getOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "select * from EVENT " 
		             + "where evt_id = :evt_id  ";
		return (Map) oDb.selectorOne(Map.class, sql, param);
	}

	/**
	 * 하나의 레코드를 Insert 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static String insertOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " INSERT INTO EVENT "   +
		"    ( evt_id, evt_title,  evt_contents,  evt_period,  evt_stdt, evt_endt,  " +
		"      evt_req, evt_updt, evt_upid, evt_yn, evt_img)"   +
		"  VALUES"   +
		"    ( :seqeventid, :evt_title, :evt_contents, :evt_period, :evt_stdt, :evt_endt, " +
		"      :evt_req, sysdate, :evt_upid, :evt_yn, :evt_img)"  ;
		
		String event_id = ((MdbOCI8)oDb).getSequence("seq_event_id");
		param.put("seqeventid", event_id);
		
		oDb.executeUpdate(sql, param);
		return event_id;
	}
	
	

	/**
	 * 하나의 레코드를 Update 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static int updateOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " update EVENT set "   +
			"evt_title = :evt_title  ,  " +
			"evt_contents = :evt_contents  ,  " +
			"evt_period = :evt_period  ,  " +
			"evt_stdt = :evt_stdt  ,  " +
			"evt_endt = :evt_endt  ,  " +
			"evt_req = :evt_req  ,  " +
			"evt_updt = sysdate  ,  " +
			"evt_upid = :evt_upid  ,  " +
			"evt_yn = :evt_yn," +
			"evt_img = :evt_img   " +
            "where evt_id = :evt_id  ";
		return oDb.executeUpdate(sql, param);
	}

	/**
	 * 하나의 레코드를 Delete 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static int deleteOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " delete from EVENT "   +
            " where evt_id = :evt_id  ";
		return oDb.executeUpdate(sql, param);
	}


	
}


		