/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.wlc.classroom.tutor;

import java.util.List;
import java.util.Map;

import maf.beans.NavigatorInfo;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;


public class StdDB {

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

		final String sql = "SELECT *  " +
		" FROM (SELECT a.*  " +
		"        FROM maf_user a, wlc_lec_req b " +
		"        where a.usn = b.usn " +
		"        and b.lec_cd = :lec_cd) x ";

		
        String sOrder = navigator.getOrderSql();

        oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
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
		final  String sql = " SELECT count(*)"   +
							"   FROM (SELECT a.*  " +
		"        FROM maf_user a, wlc_lec_req b " +
		"        where a.usn = b.usn " +
		"        and b.lec_cd = :lec_cd) x" + sWhere;
		return oDb.selectOneInt(sql, param);
	}
	
}	