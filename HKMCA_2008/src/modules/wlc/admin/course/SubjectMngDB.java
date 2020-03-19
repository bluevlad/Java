/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.wlc.admin.course;

import java.util.List;
import java.util.Map;

import maf.base.BaseDAO;
import maf.beans.NavigatorInfo;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;


public class SubjectMngDB extends BaseDAO {

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

		final String sql = " SELECT x.*  "   +
		"   FROM (SELECT s.*, c.crs_nm"   +
		"          FROM bas_sjt s, bas_crs c"   +
		"          WHERE s.crs_cd = c.crs_cd) x"  ;
		String sOrder = navigator.getOrderSql();

		if(!isAll) {
			oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
			navigator.setTotalCount(SubjectMngDB.getRecordCount(oDb, param, sWhere.toString()));
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
		final String sql =  " SELECT count(*) "   +
		"   FROM (SELECT s.*"   +
		"           FROM bas_sjt s, bas_crs c"   +
		"          WHERE s.crs_cd = c.crs_cd) x" + sWhere ;
		return oDb.selectOneInt(sql, param);
	}
	
	public static List getList(MdbDriver oDb, Map param, String sWhere) throws MdbException {
		
		final String sql =" SELECT *"   +
		"   FROM (SELECT sjt_cd, sjt_nm, (SELECT code_name"   +
		"                                   FROM jmf_code_det"   +
		"                                   WHERE group_cd = 'FIELD'"   +
		"                                   AND code_no = cr.FIELD) AS field_name,"   +
		"                (SELECT code_name"   +
		"                   FROM jmf_code_det"   +
		"                   WHERE group_cd = 'CERT_LVL'"   +
		"                   AND code_no = cr.cert_lvl) AS lvl_name,"   +
		"                (SELECT code_name"   +
		"                   FROM jmf_code_det"   +
		"                   WHERE group_cd = 'ACTIVE_YN'"   +
		"                   AND code_no = sj.isuse) AS isuse_name"   +
		"           FROM bas_sjt sj, bas_crs cr"   +
		"           WHERE sj.crs_cd = cr.crs_cd) x"  ;
		
		return oDb.selector(Map.class, sql + sWhere, param);
	}
	
	/**
	 * 하나의 과목정보를 읽어온다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static Map getOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "select * " 
				+ " from BAS_SJT " 
		        + " where  sjt_cd = :sjt_cd ";
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
		final String sql = " update BAS_SJT set " +
				" sjt_nm = :sjt_nm, " +
				" crs_cd = :crs_cd, " +
				" contents = :contents," +
				" active_yn = :active_yn," +
				" upt_dt = sysdate," +
				" upt_id = :usn" +
            " where sjt_cd = :sjt_cd ";
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
		final String sql = " INSERT INTO BAS_SJT "   +
		"    (sjt_cd, crs_cd, sjt_nm, contents, active_yn, reg_dt, upt_dt, upt_id) " +
		"  VALUES"   +
		"    (:sjt_cd, :crs_cd, :sjt_nm, :contents, :active_yn, sysdate, sysdate, :usn)"  ;
		
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
    	final String sql = "MERGE INTO BAS_SJT new " +
    	"      USING (SELECT :sjt_cd sjt_cd, :crs_cd crs_cd, :sjt_nm sjt_nm, :contents contents, :usn usn, :active_yn active_yn " +
    	"               FROM DUAL) src " +
    	"         ON (src.sjt_cd = new.sjt_cd) " +
    	"    WHEN MATCHED THEN " +
    	"       UPDATE SET " +
		" 			sjt_nm = src.sjt_nm, " +
		" 			crs_cd = src.crs_cd, " +
		" 			contents = src.contents," +
		" 			active_yn = src.active_yn," +
		" 			upt_dt = sysdate," +
		" 			upt_id = src.usn" +
    	"    WHEN NOT MATCHED THEN " +
    	"       INSERT (" +
    	"                sjt_cd, crs_cd, sjt_nm, contents, active_yn, reg_dt, upt_dt, upt_id" +
    	"       ) VALUES (" +
    	"                  src.sjt_cd, src.crs_cd, src.sjt_nm, src.contents, src.active_yn, sysdate, sysdate, src.usn" +
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
		final String sql = " delete from BAS_SJT  " +
        " where sjt_cd = :sjt_cd ";
		return oDb.executeUpdate(sql, param);
	}
	
	/**
	 * 과정에 등록된 과목목록을 가져온다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static List getSubject(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "select * " 
				+ " from BAS_SJT " 
		        + " where crs_cd = :crs_cd ";
		return oDb.selector(Map.class, sql, param);
	}

	/**
	 * 
	 * 
	 * @param oDb
	 * @param param
	 * @param sWhere
	 * @return
	 * @throws MdbException
	 */
	static String getMaxNum(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "SELECT NVL (count (crs_cd), 0) + 1 nmax " +
		"   FROM bas_sjt " +
		"   WHERE crs_cd = :crs_cd ";

		return oDb.selectOne(sql, param);
	}
	
}