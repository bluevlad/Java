/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.wlc.admin.etest;

import java.util.List;
import java.util.Map;

import maf.base.BaseDAO;
import maf.beans.NavigatorInfo;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;


public class WlcSetManagerDB extends BaseDAO {

	/**
	 * 목록 가져오기
	 * @param oDb
	 * @param param
	 * @param sql
	 * @param isAll
	 * @return
	 * @throws MdbException
	 */
	public static void getList(MdbDriver oDb, NavigatorInfo navigator, Map param, String sWhere, boolean isAll) throws MdbException {
		List list = null;
		final String sql = " SELECT * "   +
		" FROM wlc_exm_set "  ;
		String sOrder = navigator.getOrderSql();
		if (!isAll) {
			oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
			navigator.setTotalCount(WlcSetManagerDB.getRecordCount(oDb, param, sWhere.toString()));
			navigator.sync();
		}
		list = oDb.selector(Map.class, sql + sWhere + sOrder, param);
		navigator.setList(list);
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
		final String sql = "select count(*) from wlc_exm_set "   + sWhere;
		return oDb.selectOneInt(sql, param);
	}	
	
	/**
	 * 목록 가져오기(Chooser용)
	 * @param oDb
	 * @param param
	 * @param sql
	 * @param isAll
	 * @return
	 * @throws MdbException
	 */
	
	public static List getListforChooser(MdbDriver oDb, Map param) throws MdbException {

		final String sql = "select a.* " +
		" from wlc_exm_set a, bas_lec b " +
		" where a.sjt_cd = b.sjt_cd " +
		" and b.lec_cd = :lec_cd ";

		return oDb.selector(Map.class, sql, param);
	}

	public static void getListforChooser(MdbDriver oDb, NavigatorInfo navigator, Map param, String sWhere, boolean isAll) throws MdbException {
		List list = null;
		final String sql = " SELECT * FROM (SELECT * FROM wlc_exm_set ) x"  ; ;
		String sOrder = navigator.getOrderSql();
		if (!isAll) {
			oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
			navigator.setTotalCount(WlcSetManagerDB.getChooserRecordCount(oDb, param, sWhere.toString()));
			navigator.sync();
		}
		list = oDb.selector(Map.class, sql + sWhere + sOrder, param);
		navigator.setList(list);
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
	private static long getChooserRecordCount(MdbDriver oDb, Map param, String sWhere) throws MdbException {
		final String sql = "select count(*) from (SELECT * FROM wlc_exm_set) x" + sWhere;
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
		final String sql = "SELECT a.*, b.sjt_nm  " +
		"  FROM wlc_exm_set a, bas_sjt b " +
		"  WHERE a.sjt_cd = b.sjt_cd " +
		"  and setid = :setid ";
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
	public static int updateSetOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " update wlc_EXM_SET set "
				+ " upt_dt = sysdate, "
				+ " upt_id = :upt_id, "
				+ " settitle = :settitle, "
				+ " setdesc = :setdesc, "
				+ " exmtime = :exmtime, "
				+ " viewrand = :viewrand, "
				+ " exmtype = :exmtype, "
				+ " lang = :lang, "
				+ " passing_score = :passing_score "
				+ " where  setid = :setid ";
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
		final String sql = " INSERT INTO wlc_EXM_SET "
				+ "    (setid, settitle, setdesc, exmtime, reg_dt, reg_id, " +
						" viewrand, passing_score, sjt_cd, exmtype, lang) "
				+ "  VALUES"
				+ "    (SEQ_EXM_SET_ID.NEXTVAL, :settitle, :setdesc, :exmtime, sysdate, :reg_id, " +
						" :viewrand, :passing_score, :sjt_cd, :exmtype, :lang)";
		return oDb.executeUpdate(sql, param);
	}
	
	/**
	 * 해당 문제 set의 item들을 모두 삭제 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static int deleteSetItems(MdbDriver oDb, Map param) throws MdbException {
		
		final String sql  = "delete from wlc_exm_set_item"
				+ " where setid = :setid";
		return oDb.executeUpdate(sql, param);
	}
	
	/**
	 * 해당 문제 set의 item들을 모두 삭제 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static int deleteSet(MdbDriver oDb, Map param) throws MdbException {
		
		final String sql  = " delete from wlc_exm_set"
				+ " where setid = :setid";
		return oDb.executeUpdate(sql, param);
	}
	
	/**
	 * 해당 문제 set의 item들을 등록 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static int insertSetItems(MdbDriver oDb, Map param) throws MdbException {
		final String sql_insert = " INSERT INTO wlc_exm_set_item " +
				" (setid, queid, qseq, reg_id, reg_dt) " +
				" VALUES (:setid, :queid, :qseq, :reg_id, SYSDATE)"  ;
		return oDb.executeUpdate(sql_insert, param);
	
	}
	
	/**
	 * set의 문항수를 최대치로 바꾼다 .
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static int updateMaxCnt(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " update wlc_EXM_SET set "
				+ " exmcnt1 = :exmcnt1, "
				+ " exmcnt2 = :exmcnt2, "
				+ " exmcnt3 = :exmcnt3, "
				+ " exmcnt4 = :exmcnt4, "
				+ " exmcnt5 = :exmcnt5 "
				+ "where  setid = :setid ";
		return oDb.executeUpdate(sql, param);
	}
	
	/**
	 * 	
	 * 문제 Set에 속한 문제들을 가져 온다 . 
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static List getSetItems(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "SELECT DECODE (i.queid, NULL, 'N', 'Y') chk, b.quelevel, " +
		"        b.queid, b.quescore, b.quetitle, etest.getlanglist(b.queid) quelang, i.qseq  " +
		"  FROM (SELECT * " +
		"             FROM wlc_exm_set_item " +
		"             WHERE setid = :setid) i, wlc_exm_bnk b " +
		"  WHERE b.queid = i.queid  " +
		"  AND b.active_yn = 'Y' " +
		" ORDER BY qseq, b.queid ";

		return oDb.selector(Map.class, sql, param);
	}

	/**
	 * 목록 가져오기
	 * @param oDb
	 * @param param
	 * @param sql
	 * @param isAll
	 * @return
	 * @throws MdbException
	 */
	public static void getSetItems(MdbDriver oDb, NavigatorInfo navigator, Map param, String sWhere, boolean isAll) throws MdbException {
		List list = null;
		final String sql = "select * from (SELECT DECODE (i.queid, NULL, 'N', 'Y') chk, b.quelevel, " +
		"        b.queid, b.quescore, b.quetitle, etest.getlanglist(b.queid) quelang, i.qseq  " +
		"  FROM (SELECT * " +
		"             FROM wlc_exm_set_item " +
		"             WHERE setid = :setid) i, wlc_exm_bnk b " +
		"  WHERE b.queid = i.queid(+)  " +
		"  AND b.active_yn = 'Y' ) x " +
		" ORDER BY qseq, queid ";
		
		String sOrder = navigator.getOrderSql();
		if (!isAll) {
			oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
			navigator.setTotalCount(WlcSetManagerDB.getSetItemCount(oDb, param, sWhere.toString()));
			navigator.sync();
		}
		list = oDb.selector(Map.class, sql + sWhere + sOrder, param);
		navigator.setList(list);
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
	private static long getSetItemCount(MdbDriver oDb, Map param, String sWhere) throws MdbException {
		final String sql = "select count(*) from (SELECT DECODE (i.queid, NULL, 'N', 'Y') chk, b.quelevel, " +
		"        b.queid, b.quescore, b.quetitle, etest.getlanglist(b.queid) quelang, i.qseq " +
		" FROM (SELECT * " +
		"             FROM wlc_exm_set_item " +
		"             WHERE setid = :setid) i, wlc_exm_bnk b " +
		"  WHERE b.queid = i.queid(+)  " +
		"  AND b.active_yn = 'Y' ) x "  + sWhere;
		return oDb.selectOneInt(sql, param);
	}	
	
	
	
	
	/**
	 * 현재 문제 Set의 난위도별 최대치를 구한다. 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static Map getQueMaxCnt(MdbDriver oDb, Map param) throws MdbException {
		final String sql ="  SELECT   nvl(SUM (DECODE (b.quelevel, 1, 1, 0)) ,0) exmcnt1,"   +
		"           nvl(SUM (DECODE (b.quelevel, 2, 1, 0)) ,0) exmcnt2,"   +
		"           nvl(SUM (DECODE (b.quelevel, 3, 1, 0)) ,0) exmcnt3,"   +
		"           nvl(SUM (DECODE (b.quelevel, 4, 1, 0)) ,0) exmcnt4,"   +
		"           nvl(SUM (DECODE (b.quelevel, 5, 1, 0)) ,0) exmcnt5"   +
		"      FROM (SELECT *"   +
		"              FROM wlc_exm_set_item"   +
		"             WHERE setid = :setid) i, wlc_exm_bnk b"   +
		"     WHERE b.queid = i.queid AND b.active_yn = 'Y'"  ; ;
		return (Map) oDb.selectorOne(Map.class, sql, param);
	}
	
	/**
	 * 해당 set의 언어별 문제수를 돌려 준다. 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static List getQueLangCnt(MdbDriver oDb, Map param)  throws MdbException {
		final String sql = " SELECT b.lang, b.langname, NVL (a.cnt, 0) cnt, DECODE (sl.lang, NULL, 'N', 'Y') chk"   +
		"   FROM (SELECT 'def.' lang, COUNT (*) cnt"   +
		"           FROM (SELECT *"   +
		"                   FROM wlc_exm_set_item"   +
		"                  WHERE setid = :setid) i, wlc_exm_bnk b"   +
		"          WHERE b.queid = i.queid"   +
		"            AND b.active_yn = 'Y'"   +
		"         UNION ALL"   +
		"         SELECT   bl.lang, COUNT (*) cnt"   +
		"             FROM (SELECT *"   +
		"                     FROM wlc_exm_set_item"   +
		"                    WHERE setid = :setid) i, wlc_exm_bnk b, wlc_exm_bnk_lang bl"   +
		"            WHERE b.queid = i.queid"   +
		"              AND b.active_yn = 'Y'"   +
		"              AND b.queid = bl.queid"   +
		"              AND bl.active_yn = 'Y'"   +
		"         GROUP BY bl.lang) a,"   +
		"        (SELECT 'def.' lang, 'default' langname"   +
		"           FROM DUAL"   +
		"         UNION"   +
		"         SELECT code lang, allnames langname"   +
		"           FROM v_jmf_lang) b,"   +
		"        (SELECT *"   +
		"           FROM wlc_exm_set_lang sl"   +
		"          WHERE setid = :setid) sl"   +
		"  WHERE b.lang = a.lang(+)"   +
		"    AND b.lang = sl.lang(+)"  ;
		return oDb.selector(Map.class, sql, param);
		
	}

	/**
	 * 	
	 * 문제 Set에 속한 문제들을 가져 온다 . 
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static List getSetAllItems(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "select aa.*, DECODE(bb.queid, NULL, 'N', 'Y') chk, bb.qseq  " +
		"  from (select a.QUEID, a.SJT_CD, b.quetitle, b.lang, a.QUETEXT, a.QUEIMAG, " +
		"               a.QUEVIW1, a.QUEVIW2, a.QUEVIW3, a.QUEVIW4, a.QUEVIW5, " +
		"               a.QUEVIW6, a.QUEVIW7, a.QUEVIW8, a.QUEVIW9, a.QUEVIW10, " +
		"               a.QUECOUNT, a.QUEDESC, a.QUEDESCIMG, a.QUEANSW, a.QUESCORE, " +
		"               a.QUETYPE, a.QUELEVEL, a.ACTIVE_YN " +
		"         from wlc_exm_bnk a, wlc_exm_bnk_lang b " +
		"         where a.queid = b.queid " +
		"         and b.lang = :lang " +
		"         and a.sjt_cd = (select sjt_cd from wlc_exm_set where setid = :setid)) aa,  " +
		"       (select a.sjt_cd, b.queid, b.qseq " +
		"          from wlc_exm_set a, wlc_exm_set_item b " +
		"          where a.setid = b.setid " +
		"          and a.setid = :setid " +
		"          and a.lang = :lang) bb " +
		"   where aa.queid = bb.queid(+) ";

		return oDb.selector(Map.class, sql, param);
	}
	
}