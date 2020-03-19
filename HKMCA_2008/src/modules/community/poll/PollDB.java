/*
 * Created on 2006. 10. 12
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.community.poll;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maf.base.BaseDAO;
import maf.beans.NavigatorInfo;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;


public class PollDB extends BaseDAO {
	public static Map getCurrentPoll(MdbDriver oDb) throws MdbException{
		final String sql = " *"   +
		"  FROM www_poll"   +
		"  WHERE is_use = 'T'"   +
		"  AND is_show = 'T'"   +
		"  AND ROWNUM < 2"   +
		"  AND TO_CHAR (SYSDATE, 'YYYYMMDD') BETWEEN start_dt AND end_dt"  ;  ;
		return (Map) oDb.selectorOne(Map.class, sql);
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

		final String sql = " SELECT poll_id, start_dt, end_dt, is_use, title, is_show,"   +
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
		final String sql = "select poll_id, start_dt, end_dt, is_use, title, is_show, " +
		"       (select sum(hit) from www_poll_item where poll_id = :poll_id) sum " +
		"  from WWW_POLL " +
		"  where poll_id = :poll_id ";
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
		final String sql = "SELECT item_id, seq, question, hit,  " +
		"       RANK () OVER (ORDER BY hit DESC) RANK, " +
		"       hit/SUM(decode(hit,0,1)) OVER () perc " +
		"  FROM www_poll_item  " +
		"  WHERE poll_id = :poll_id  " +
		"  ORDER BY seq, question ";

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
		final String sql = "update WWW_POLL set " +
		" start_dt = :start_dt," +
		" end_dt = :end_dt," +
		" is_use = :is_use," +
		" title = :title," +
		" is_show = :is_show" +
		" where poll_id = :poll_id";
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
				+ "    (poll_id, start_dt, end_dt, is_use, title, is_show)"
				+ "  VALUES"
				+ "    (SEQ_WWW_POLL.nextval, :start_dt, :end_dt, :is_use, :title, :is_show)";

		return oDb.executeUpdate(sql, param);
	}

	/**
	 * 하나의 레코드를  insert or update 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
    public static int mergeOne(MdbDriver oDb,  Map param) throws MdbException  {
    	final String sql = "MERGE INTO WWW_POLL new " +
    	"      USING (SELECT :poll_id poll_id, :start_dt start_dt, :end_dt end_dt, :is_use is_use, " +
    	"                    :title title, :is_show is_show " +
    	"               FROM DUAL) src " +
    	"         ON (src.poll_id = new.poll_id) " +
    	"    WHEN MATCHED THEN " +
    	"       UPDATE SET" +
		" 			start_dt = src.start_dt," +
		" 			end_dt = src.end_dt," +
		" 			is_use = src.is_use," +
		" 			title = src.title," +
		" 			is_show = src.is_show" +
    	"    WHEN NOT MATCHED THEN " +
    	"       INSERT (" +
    	"                poll_id, start_dt, end_dt, is_use, title, is_show" +
    	"       ) VALUES (" +
    	"                  SEQ_WWW_POLL.nextval, src.start_dt, src.end_dt, src.is_use, src.title, src.is_show" +
    	"      ) ";
    	
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
		final String sql = "delete WWW_POLL " +
		" where  poll_id = :poll_id";
		return oDb.executeUpdate(sql, param);
	}

	////////////////////////////////////////////////////
	/**
	 * 하나의 Item을 등록 한다.
	 */
	public static int insertItemOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " INSERT INTO www_poll_item " +
		" (poll_id, item_id, question, seq) " +
		" SELECT :poll_id, NVL(MAX (item_id), 0) + 1, :question, :seq" +
		" FROM www_poll_item" +
		" WHERE poll_id = :poll_id"  ;
		return oDb.executeUpdate(sql, param);
	}

	/**
	 * 하나의 Item을 Update 한다.
	 */
	public static int updateItemOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql =" UPDATE www_poll_item" +
		" SET question = :question," +
		" seq = :seq" +
		" WHERE poll_id = :poll_id AND item_id = :item_id";
		return oDb.executeUpdate(sql, param);
	}
	
	/**
	 * 하나의 Item을 삭제 한다.
	 */
	public static int deleteItemOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "delete www_poll_item " +
				" where poll_id = :poll_id and item_id = :item_id";
		return oDb.executeUpdate(sql, param);
	}

	/**
	 * 하나의 Item을 hit 한다.
	 */
	public static int updateItemHit(MdbDriver oDb, Map param) throws MdbException {
		final String sql =" UPDATE www_poll_item" +
		" SET hit = hit + 1" +
		" WHERE poll_id = :poll_id AND item_id = :item_id";
		return oDb.executeUpdate(sql, param);
	}
}
