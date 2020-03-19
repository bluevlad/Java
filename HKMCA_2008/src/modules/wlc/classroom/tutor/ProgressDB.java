/*
 * Created on 2006. 9. 22.
 *
 * Copyright (c) 2006 UBQ All rights reserved.
 */
package modules.wlc.classroom.tutor;

import java.util.List;
import java.util.Map;

import maf.base.BaseDAO;
import maf.beans.NavigatorInfo;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;


public class ProgressDB extends BaseDAO {

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
		final String sql = "select * from ( " +
    	"SELECT a.usn, userid, nm, b.email,  " +
    	"       lec_cd, req_stat,  " +
    	"       FN_GET_WLCJINDO_TIME(lec_cd, a.usn) AS jindo_time,  " +
    	"       FN_GET_WLCJINDO_PAGE(lec_cd, a.usn) AS jindo_page  " +
    	"  FROM wlc_lec_req a, maf_user b " +
    	"  where a.usn = b.usn " +
    	"  and a.lec_cd = :lec_cd " +
    	"  and req_stat not in ('CA','CE') ) x ";
		
		String sOrder = navigator.getOrderSql();

		if(!isAll) {
			oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
			navigator.setTotalCount(ProgressDB.getRecordCount(oDb, param, sWhere.toString()));
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
        final String sql =
        	"select count(*) from ( " +
        	"SELECT a.usn  " +
        	"  FROM wlc_lec_req a, maf_user b " +
        	"  where a.usn = b.usn " +
        	" and a.lec_cd = :lec_cd " +
        	"  and req_stat not in ('CA','CE') ) x " + sWhere;

        return oDb.selectOneInt(sql, param);
    }

    /**
     * 목록 가져오기
     *
     * @param oDb
     * @param navigator
     * @param param
     * @param sWhere
     * @return
     * @throws MdbException
     */
    public static List getJindo(MdbDriver oDb, Map param) throws MdbException {
        final String sql = "SELECT chp.itm_id, itm_title, (FN_GET_TOTAL_SEC(NVL(chp.itm_maxtimeallowed,0))) AS t_time, " +
        "    DECODE(SIGN((FN_GET_TOTAL_SEC(NVL(chp.itm_maxtimeallowed,0)))-(FN_GET_TOTAL_SEC(prg.total_time))), -1, " +
        "    FN_GET_TOTAL_SEC(NVL(chp.itm_maxtimeallowed,0)),    " +
        "    TRUNC(FN_GET_TOTAL_SEC(prg.total_time))) AS j_time,    " +
        "    TRUNC(FN_GET_TOTAL_SEC(prg.total_time)) AS s_time,    " +
        "    chp.totpage AS t_page, (SELECT COUNT(itm_id) FROM wlc_page_prg    " +
        "                            WHERE lec_cd=chp.lec_cd AND itm_id=chp.itm_id AND usn = :usn " +
        "                                AND page_nm NOT LIKE '%left%'     " +
        "                                AND page_nm NOT LIKE '%right%'    " +
        "                                AND page_nm NOT LIKE '%bottom%'   " +
        "                                AND page_nm NOT LIKE '%top%'      " +
        "                                AND page_nm NOT LIKE '%frame%'      " +
        "                           ) AS j_page,   " +
        "    chp.itm_sequence   " +
        "  FROM wlc_lec_chp chp, (SELECT * FROM wlc_chp_prg   " +
        "                           WHERE lec_cd = :lec_cd AND usn = :usn) prg   " +
        "  WHERE chp.lec_cd = prg.lec_cd(+)  " +
        "  AND chp.htmcode = prg.htmcode(+) " +
        "  AND chp.lec_cd = :lec_cd " +
        "  AND chp.launch_data IS NOT NULL  " +
        "  ORDER BY to_number(chp.itm_sequence) ASC ";

        return oDb.selector(Map.class, sql, param);
    }

    /**
     * 하나의 레코드 읽어 오기(강의별 최소 학습시간 정보)
     *
     * @param oDb
     * @param param
     * @return
     * @throws MdbException
     */
    public static String getOneLimit_study_time(MdbDriver oDb, Map param) throws MdbException {
        String sql = "SELECT NVL(limit_study_time,'0') FROM bas_lec WHERE lec_cd = :lec_cd ";
        return (String)oDb.selectOne(sql, param);
    }

    /**
     * 하나의 레코드 읽어 오기
     *
     * @param oDb
     * @param param
     * @return
     * @throws MdbException
     */
    public static String getOneLearningTotalTime(MdbDriver oDb, Map param) throws MdbException {
        String sql = "SELECT FN_GET_TOTAL_TIME_H(sum(FN_GET_TOTAL_SEC(total_time))) " +
        " FROM wlc_chp_prg " +
        " WHERE lec_cd = :lec_cd and usn = :usn";
        return (String)oDb.selectOne(sql, param);
    }

}