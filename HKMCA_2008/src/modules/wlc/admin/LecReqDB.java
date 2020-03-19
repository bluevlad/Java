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


public class LecReqDB extends BaseDAO {

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

		final String sql = "select x.*  " +
		"  from (select cr.crs_cd, cr.crs_nm, sj.sjt_cd, sj.sjt_nm, bl.lec_cd, bl.lec_nm, bl.lec_year,  " +
		"                bs.sch_sdt, bs.sch_edt,  " +
		"                wr.usn, mu.userid, mu.nm, to_char(wr.req_dt, 'YYYY-MM-DD') as req_dt, wr.req_stat  " +
		"           from bas_crs cr, bas_sjt sj, bas_lec bl, bas_lec_sch bs, wlc_lec_req wr, v_maf_user mu " +
		"           where cr.crs_cd = sj.crs_cd " +
		"           and sj.sjt_cd = bl.sjt_cd " +
		"           and bl.lec_cd = bs.lec_cd " +
		"           and bl.lec_cd = wr.lec_cd  " +
		"           and wr.usn = mu.usn " +
		"           and bs.sch_type = 'c' " +
		"       ) x ";
		
		String sOrder = navigator.getOrderSql();

		if(!isAll) {
			oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
			navigator.setTotalCount(LecReqDB.getRecordCount(oDb, param, sWhere.toString()));
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
		"  from ( select cr.crs_cd, cr.crs_nm, sj.sjt_cd, sj.sjt_nm, bl.lec_cd, bl.lec_nm, bl.lec_year,  " +
		"                bs.sch_sdt, bs.sch_edt,  " +
		"                wr.usn, mu.userid, mu.nm, to_char(wr.req_dt, 'YYYY-MM-DD') as req_dt, wr.req_stat  " +
		"           from bas_crs cr, bas_sjt sj, bas_lec bl, bas_lec_sch bs, wlc_lec_req wr, v_maf_user mu " +
		"           where cr.crs_cd = sj.crs_cd " +
		"           and sj.sjt_cd = bl.sjt_cd " +
		"           and bl.lec_cd = bs.lec_cd " +
		"           and bl.lec_cd = wr.lec_cd  " +
		"           and wr.usn = mu.usn " +
		"           and bs.sch_type = 'c' " +
        "         ) x" + sWhere;
		return oDb.selectOneInt(sql, param);
	}
	
    public static List getList(MdbDriver oDb, Map param, String sWhere) throws MdbException {
        
        final String sql = "select x.*  " +
		"  from (select cr.crs_cd, cr.crs_nm, sj.sjt_cd, sj.sjt_nm, bl.lec_cd, bl.lec_nm, bl.lec_year,  " +
		"                bs.sch_sdt, bs.sch_edt,  " +
		"                wr.usn, mu.userid, mu.nm, to_char(wr.req_dt, 'YYYY-MM-DD') as req_dt, get.codenm('WLC.REQ_STAT', wr.req_stat) req_stat " +
		"           from bas_crs cr, bas_sjt sj, bas_lec bl, bas_lec_sch bs, wlc_lec_req wr, v_maf_user mu " +
		"           where cr.crs_cd = sj.crs_cd " +
		"           and sj.sjt_cd = bl.sjt_cd " +
		"           and bl.lec_cd = bs.lec_cd " +
		"           and bl.lec_cd = wr.lec_cd  " +
		"           and wr.usn = mu.usn " +
		"           and bs.sch_type = 'c' " +
		"       ) x ";
        
        return oDb.selector(Map.class, sql + sWhere, param);
    }
	
    public static List getSample(MdbDriver oDb, Map param) throws MdbException {
        
        final String sql = "select wr.usn, wr.lec_num, " +
        "  TO_CHAR(wr.req_dt, 'YYYY-MM-DD') req_dt, 'LR' req_stat, wr.req_sdat, wr.req_edat, wr.lec_cd  " +
        "  from bas_lec bl, wlc_lec_req wr, v_maf_user mu  " +
        "  where bl.lec_cd = wr.lec_cd   " +
        "  and wr.usn = mu.usn  " +
        "  and rownum < 5 ";
        
        return oDb.selector(Map.class, sql, param);
    }
	
	public static int completeOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "update WLC_LEC_REQ set " +
		" req_stat = :req_stat," +
		" upt_dt = sysdate," +
		" upt_id = :upt_id" +
		" where usn = :usn " +
        " and lec_cd = :lec_cd";
		return oDb.executeUpdate(sql, param);
	}
	
}