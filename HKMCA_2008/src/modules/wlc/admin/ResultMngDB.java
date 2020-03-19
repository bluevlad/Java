/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.wlc.admin;

import java.util.List;
import java.util.Map;

import maf.base.BaseDAO;
import maf.beans.NavigatorInfo;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;


public class ResultMngDB extends BaseDAO {

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

		final String sql = "select x.* " +
		" from ( select b.lec_cd, b.lec_num, a.lec_nm, b.usn, c.userid, c.nm, " +
        " b.req_stat, b.req_sdat, b.req_edat, b.is_grad, e.tot_score"   +
		" from bas_lec a, wlc_lec_req b, v_jmf_user c, wlc_fnl_grd e"   +
		" where a.lec_cd = b.lec_cd"   +
		" and b.usn = c.usn"   +
		" and b.lec_cd = e.lec_cd(+)"   +
		" and b.lec_num = e.lec_num(+)"   +
		" and b.usn = e.usn(+)"   +
		" and a.lec_cd = :lec_cd" +
		"       ) x ";
		
		String sOrder = navigator.getOrderSql();

		if(!isAll) {
			oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
			navigator.setTotalCount(ResultMngDB.getRecordCount(oDb, param, sWhere.toString()));
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
		final  String sql = " select count(*) "   +
		"  from ( select b.lec_cd, b.lec_num, a.lec_nm, b.usn, c.userid, c.nm, " +
        " b.req_stat, b.req_sdat, b.req_edat, b.is_grad, e.tot_score"   +
		" from bas_lec a, wlc_lec_req b, v_jmf_user c, wlc_fnl_grd e"   +
		" where a.lec_cd = b.lec_cd"   +
		" and b.usn = c.usn"   +
		" and b.lec_cd = e.lec_cd(+)"   +
		" and b.lec_num = e.lec_num(+)"   +
		" and b.usn = e.usn(+)"   +
		" and a.lec_cd = :lec_cd" +
        "         ) x" + sWhere;
		return oDb.selectOneInt(sql, param);
	}
	

	public static List getStdList(MdbDriver oDb, Map param) throws MdbException {
		
		final String sql = "select b.lec_cd, b.lec_num, a.lec_nm, b.usn, c.userid, c.nm, " +
        " b.req_stat, b.req_sdat, b.req_edat, b.is_grad, e.tot_score"   +
		" from bas_lec a, wlc_lec_req b, v_jmf_user c, wlc_fnl_grd e"   +
		" where a.lec_cd = b.lec_cd"   +
		" and b.usn = c.usn"   +
		" and b.lec_cd = e.lec_cd(+)"   +
		" and b.lec_num = e.lec_num(+)"   +
		" and b.usn = e.usn(+)"   +
		" and a.lec_cd = :lec_cd"  ;
		
		return oDb.selector(Map.class, sql, param);
	}
	
	public static List getStdLecList(MdbDriver oDb, Map param) throws MdbException {
		
		final String sql = "select a.lec_cd, a.lec_nm, b.co_attendhours, " +
		"       b.usn, c.userid, c.nm, b.req_stat, b.req_sdat, b.req_edat, b.is_grad, " +
		"       e.tot_score " +
		" from bas_lec a, wlc_lec_req b, v_jmf_user c, wlc_fnl_grd e   " +
		" where a.lec_cd = b.lec_cd " +
		" and b.usn = c.usn " +
		" and b.lec_cd = e.lec_cd " +
		" and b.lec_num = e.lec_num " +
		" and b.usn = e.usn " +
		" and b.usn = :usn ";
		
		return oDb.selector(Map.class, sql, param);
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
		final String sql = "select a.*, b.sch_sdt, b.sch_edt  " +
		" from bas_lec a, bas_lec_sch b  " +
		" where a.lec_cd = b.lec_cd " +
		" and b.sch_type ='c' " +
		" and a.lec_cd = :lec_cd ";
		return (Map) oDb.selectorOne(Map.class, sql, param);
	}
	
	/**
	 * 강의를 종료한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static int closeOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " update BAS_LEC set " +
				" lec_stat = :lec_stat," +
				" upt_dt = sysdate," +
				" upt_id = :upt_id" +
				" where lec_cd = :lec_cd ";
		return oDb.executeUpdate(sql, param);
	}
	
	/**
	 * 수강 상태를 변경 한다.  처리 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static int updateLecReq(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " update WLC_LEC_REQ set " +
				" req_stat = :req_stat," +
				" upt_dt = sysdate," +
				" upt_id = :upt_id" +
				" where usn = :usn " +
                " and lec_cd = :lec_cd";
		return oDb.executeUpdate(sql, param);
	}
	
	
	
}