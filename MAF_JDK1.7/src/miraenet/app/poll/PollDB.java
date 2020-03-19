/*
 * Created on 2006. 10. 12
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.poll;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maf.base.BaseDAO;
import maf.beans.NavigatorInfo;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

public class PollDB extends BaseDAO {
	public static Map getCurrentPoll(MdbDriver oDb, String site) throws MdbException{
		final String sql = " SELECT /*+ INDEX(www_poll IX_WWW_POLL_01) */"   +
		"        *"   +
		"   FROM www_poll"   +
		"  WHERE is_use = 'T'"   +
		"    AND is_show = 'T'"   +
		"    AND site = :site"   +
		"    AND ROWNUM < 2"   +
		"    AND TO_CHAR (SYSDATE, 'YYYYMMDD') BETWEEN start_dt AND end_dt"  ;  ;
		Map param = new HashMap();
		param.put("site",site);
		return (Map) oDb.selectorOne(Map.class, sql, param);
	}
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

		final String sql = " SELECT site, poll_id, start_dt, end_dt, is_use, title, is_show,"   +
		"        (SELECT SUM (hit)"   +
		"           FROM www_poll_item i"   +
		"          WHERE p.poll_id = i.poll_id) ans"   +
		"   FROM www_poll p"  ;

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
		final String sql = "select count(*) from WWW_POLL " + sWhere;
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
		final String sql = "select site,  poll_id,  start_dt,  end_dt,  is_use,  title,  is_show  "
				+ "from WWW_POLL "
				+ " where    poll_id = :poll_id  ";
		return (Map) oDb.selectorOne(Map.class, sql, param);
	}
	/**
	 * 하나의 Poll에 대한 item 정보 
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static List getPollItems(MdbDriver oDb, Map param) throws MdbException {
		final String sql =" SELECT   item_id, seq, question, hit, RANK () OVER (ORDER BY hit DESC) RANK,"   +
		"          hit / SUM (hit) OVER () perc"   +
		"     FROM www_poll_item"   +
		"    WHERE poll_id = :poll_id"   +
		" ORDER BY seq, question"  ;
		return  oDb.selector(Map.class, sql, param);
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
		final String sql = " update WWW_POLL set "
				+ "site = :site  ,  "
				+ "start_dt = :start_dt  ,  "
				+ "end_dt = :end_dt  ,  "
				+ "is_use = :is_use  ,  "
				+ "title = :title  ,  "
				+ "is_show = :is_show   "
				+ "where    poll_id = :poll_id  ";
		return oDb.executeUpdate(sql, param);
	}
	/**
	 * 하나의 레코드를 삭제 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static int deleteOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " delete WWW_POLL"
				+"where  poll_id = :poll_id  ";
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
		final String sql = " INSERT INTO WWW_POLL"
				+ "    ( site, poll_id, start_dt, end_dt, is_use, title, is_show)"
				+ "  VALUES"
				+ "    ( :site, :poll_id, :start_dt, :end_dt, :is_use, :title, :is_show)";

		return oDb.executeUpdate(sql, param);
	}
	////////////////////////////////////////////////////
	/**
	 * 하나의 Item을 삭제 한다.
	 */
	public static int insertItemOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " INSERT INTO ehrd.www_poll_item"   +
		"             (poll_id, item_id, question, seq)"   +
		"    SELECT :poll_id, NVL (MAX (item_id), 0) + 1, :question, :seq"   +
		"      FROM www_poll_item"   +
		"     WHERE poll_id = :poll_id"  ;
		return oDb.executeUpdate(sql, param);
	}
	/**
	 * 하나의 Item을 삭제 한다.
	 */
	public static int deleteItemOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " delete www_poll_item "
				+"where  poll_id = :poll_id  and item_id = :item_id ";
		return oDb.executeUpdate(sql, param);
	}
	/**
	 * 하나의 Item을 Update 한다.
	 */
	public static int updateItemOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql =" UPDATE ehrd.www_poll_item"   +
			"    SET question = :question,"   +
			"        seq = :seq " +
			"  WHERE poll_id = :poll_id AND item_id = :item_id"  ;
		return oDb.executeUpdate(sql, param);
	}
	
	/**
	 * 하나의 Item을 hit 한다.
	 */
	public static int updateItemHit(MdbDriver oDb, Map param) throws MdbException {
		final String sql =" UPDATE www_poll_item"   +
			"    SET hit = hit + 1"   +
			"  WHERE poll_id = :poll_id AND item_id = :item_id"  ;
		return oDb.executeUpdate(sql, param);
	}
}
