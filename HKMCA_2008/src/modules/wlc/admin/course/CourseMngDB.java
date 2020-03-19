/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2009 UBQ  All rights reserved.
 */
package modules.wlc.admin.course;

import java.util.List;
import java.util.Map;

import maf.base.BaseDAO;
import maf.beans.NavigatorInfo;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

public class CourseMngDB extends BaseDAO {

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

		final String sql = " select * from ( " +
				" select * " +
				" from BAS_CRS ) x ";
		
		String sOrder = navigator.getOrderSql();

		if(!isAll) {
			oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
			navigator.setTotalCount(CourseMngDB.getRecordCount(oDb, param, sWhere.toString()));
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
		final  String sql = "select count(*) from ( " +
				" select * " +
				" from BAS_CRS ) x " + sWhere;
		return oDb.selectOneInt(sql, param);
	}
	
	public static List getList(MdbDriver oDb, Map param, String sWhere) throws MdbException {
		
		final String sql = "  select * from ("   +
		"  select crs_cd, crs_nm, "   +
		" 		isuse, (select code_name from jmf_code_det where group_cd = 'ISUSE' and code_no = bas_crs.isuse) as isuse_name"   +
		"  from bas_crs ) x"  ;
		
		return oDb.selector(Map.class, sql + sWhere, param);
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
		final String sql = "select * " 
				+ " from BAS_CRS " 
		        + " where crs_cd = :crs_cd ";
		return (Map) oDb.selectorOne(Map.class, sql, param);
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
    	final String sql = "MERGE INTO BAS_CRS new " +
    	"      USING (SELECT :crs_cd crs_cd, :crs_nm crs_nm, :active_yn active_yn, :usn usn " +
    	"               FROM DUAL) src " +
    	"         ON (src.crs_cd = new.crs_cd) " +
    	"    WHEN MATCHED THEN " +
    	"       UPDATE SET " +
		" 			crs_nm = :crs_nm," +
		" 			active_yn = :active_yn," +
		" 			upt_dt = sysdate," +
		" 			upt_id = :usn" +
    	"    WHEN NOT MATCHED THEN " +
    	"       INSERT (" +
    	"                crs_cd, crs_nm, active_yn, reg_dt, upt_dt, upt_id" +
    	"       ) VALUES (" +
    	"                  src.crs_cd, src.crs_nm, src.active_yn, sysdate, sysdate, src.usn" +
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
		final String sql = " delete from BAS_CRS  " +
	            " where crs_cd = :crs_cd ";
		return oDb.executeUpdate(sql, param);
	}
	
	/**
	 * 활성화된 과정 목록 가져온다.(과정중 인증서는 제외)
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static List getCourse(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "SELECT *"   +
		" FROM bas_crs" +
		" WHERE active_yn = 'Y'" +
		" ORDER BY crs_nm";
		return oDb.selector(Map.class, sql, param);
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
	public static int getMaxNum(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "SELECT NVL (MAX (to_number(SUBSTR (crs_cd, common.lastindex (crs_cd, '-') + 1))), 0) + 1 nmax " +
		" FROM bas_crs" +
		" WHERE crs_cd LIKE :pre_crs_cd";
		return oDb.selectOneInt(sql, param);
	}
	
}