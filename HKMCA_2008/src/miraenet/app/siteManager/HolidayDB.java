/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package miraenet.app.siteManager;

import java.util.List;
import java.util.Map;

import maf.beans.NavigatorInfo;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

public class HolidayDB {

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

		final String sql = "select * from (select * from DUAL_YMD) x ";
		
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
	private static long getRecordCount(MdbDriver oDb, Map param, String sWhere) throws MdbException {
		final  String sql = "select count(*) from (select * from DUAL_YMD) x " + sWhere;
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
		final String sql = "select *   " 
				+ "from DUAL_YMD " 
		        + " where ymd = :ymd  ";
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
	public static int updateOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " update DUAL_YMD set "   +
			"bank_work = :bank_work  ,  " +
			"bigo = :bigo  ,  " +
			"work_day = :work_day    " +
            "where  ymd = :ymd  ";

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
	public static int insertOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " INSERT INTO DUAL_YMD"   +
		"    ( ymd, bank_work, bigo, work_day)"   +
		"  VALUES"   +
		"    ( :ymd, :bank_work, :bigo, :work_day)"  ;
		
		return oDb.executeUpdate(sql, param);
	}
	
//	/**
//	 * 하나의 레코드를 삭제 한다.
//	 * 
//	 * @param oDb
//	 * @param param
//	 * @return
//	 * @throws MdbException
//	 */
//	public static int deleteOne(MdbDriver oDb, Map param) throws MdbException {
//		final String sql = " delete from DUAL_YMD " +
//	            "where  ymd = :ymd  ";
//		return oDb.executeUpdate(sql, param);
//	}
	
}


		