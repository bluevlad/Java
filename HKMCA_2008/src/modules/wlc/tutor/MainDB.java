/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 (??)????? All rights reserved.
 */
package modules.wlc.tutor;

import java.util.List;
import java.util.Map;

import maf.base.BaseDAO;
import maf.beans.NavigatorInfo;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;


//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

public class MainDB extends BaseDAO {

    //private Log logger = LogFactory.getLog(this.getClass());

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
        final String sql = "SELECT x.*  " +
        "   FROM (SELECT crs.crs_cd, " +
        "                (SELECT COUNT (*) " +
        "                   FROM wlc_lec_req " +
        "                  WHERE lec_cd = b.lec_cd " +
        "                    AND req_stat = 'LR') cnt_lr, " +
        "                (SELECT COUNT (*) " +
        "                   FROM wlc_lec_req " +
        "                  WHERE lec_cd = b.lec_cd " +
        "                    AND req_stat = 'LP') cnt_lp, " +
        "                (SELECT COUNT (*) " +
        "                   FROM wlc_lec_req " +
        "                  WHERE lec_cd = b.lec_cd " +
        "                    AND req_stat = 'LE') cnt_le, b.* " +
        "           FROM bas_lec b, bas_sjt s, bas_crs crs " +
        "           WHERE b.sjt_cd = s.sjt_cd " +
        "           AND s.crs_cd = crs.crs_cd" +
        "			AND b.lec_stat in ('O','E')) x ";
        String sOrder = navigator.getOrderSql();

        if(!isAll) {
            oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
            navigator.setTotalCount(getRecordCount(oDb, param, sWhere.toString()));
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
        final  String sql = "SELECT count(*) "   +
        "   FROM (SELECT crs.crs_cd,"   +
        "                (SELECT COUNT (*)"   +
        "                   FROM wlc_lec_req"   +
        "                  WHERE lec_cd = b.lec_cd"   +
        "                    AND req_stat = 'LR') cnt_lr,"   +
        "                (SELECT COUNT (*)"   +
        "                   FROM wlc_lec_req"   +
        "                  WHERE lec_cd = b.lec_cd"   +
        "                    AND req_stat = 'LP') cnt_lp,"   +
        "                (SELECT COUNT (*)"   +
        "                   FROM wlc_lec_req"   +
        "                  WHERE lec_cd = b.lec_cd"   +
        "                    AND req_stat = 'LE') cnt_le, b.*"   +
        "           FROM bas_lec b, bas_sjt s, bas_crs crs"   +
        "           WHERE b.sjt_cd = s.sjt_cd"   +
        "           AND s.crs_cd = crs.crs_cd" +
        "			AND b.lec_stat in ('O','E')) x ";
        return oDb.selectOneInt(sql+ sWhere, param);
    }

    

    /**
     * 과정.과목 리스트를 가져온다.
     *
     * @param oDb
     * @param param
     * @return
     * @throws MdbException
     */
    public static List getCrsSub(MdbDriver oDb, Map param) throws MdbException {
        final String sql = "  select 'ROOT' as crs_cd, 'ROOT' as crs_nm, "   +
        "  crs_cd as sjt_cd, crs_nm as sjt_nm "   +
        "   from bas_crs"   +
        "  union all"   +
        "  select a.crs_cd, a.crs_nm, b.sjt_cd, b.sjt_nm "   +
        "   from bas_crs a, bas_sjt b "   +
        "   where a.crs_cd = b.crs_cd"  ;
        return oDb.selector(Map.class, sql, param);
    }

}