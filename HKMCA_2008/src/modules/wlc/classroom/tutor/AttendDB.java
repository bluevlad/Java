/*
 * Created on 2006. 10. 06.
 *
 * Copyright (c) 2006 UBQ All rights reserved.
 */
package modules.wlc.classroom.tutor;

import java.util.List;
import java.util.Map;

import maf.beans.NavigatorInfo;
import maf.mdb.CommonDB;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;


public class AttendDB extends CommonDB {

    /**
     * 오프라인 강의 목록 가져오기
     *
     * @param oDb
     * @param navigator
     * @param param
     * @param sWhere
     * @return
     * @throws MdbException
     */
    public static void getList(MdbDriver oDb, NavigatorInfo navigator, Map param, String sWhere) throws MdbException {
        List list = null;
        final String sql = "select *    " +
        "  from (select a.lec_cd, a.usn, b.att_score, c.userid, c.nm, " +
        "               (select count(*) from wlc_att_log where lec_cd = :lec_cd and usn = a.usn) att_cnt " +
        "          from wlc_lec_req a, wlc_etc_scr b, maf_user c   " +
        "          where a.lec_cd = b.lec_cd(+)   " +
        "          and a.usn = b.usn(+)   " +
        "          and a.usn = c.usn   " +
        "          and a.lec_cd = :lec_cd) x ";

        String sOrder = navigator.getOrderSql();

        oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
        list = oDb.selector(Map.class, sql + sOrder, param);
        navigator.setList(list);
        navigator.setTotalCount(getRecordCount(oDb, param, ""));
        navigator.sync();
    }

    /**
     * 오프라인 강의 레코드 카운트 가져오기
     *
     * @param oDb
     * @param param
     * @param sWhere
     * @return long
     * @throws MdbException
     */
    private static long getRecordCount(MdbDriver oDb, Map param, String sWhere) throws MdbException {
        final String sql = "select count(*) " +
        "  from (select a.lec_cd, a.usn, b.att_score, c.userid, c.nm, " +
        "               (select count(*) from wlc_att_log where lec_cd = :lec_cd and usn = a.usn) att_cnt " +
        "          from wlc_lec_req a, wlc_etc_scr b, maf_user c   " +
        "          where a.lec_cd = b.lec_cd(+)   " +
        "          and a.usn = b.usn(+)   " +
        "          and a.usn = c.usn   " +
        "  and a.lec_cd = :lec_cd) x " + sWhere;

        return oDb.selectOneInt(sql, param);
    }

    /**
     * 오프라인 강의 신청자 목록 가져오기
     * @param oDb
     * @param param [leccode, itm_id] 
     * @return
     * @throws MdbException
     */
    public static List getAttendList(MdbDriver oDb, Map param) throws MdbException {
    	final String sql = "SELECT * " +
    	"  FROM wlc_att_log " +
    	"  WHERE lec_cd = :lec_cd " +
    	"  AND usn = :std_usn ";
    	return oDb.selector(Map.class, sql, param);
    }
    
    /**
     * 오프라인 점수 입력 및 출석 체크
     * @param oDb
     * @param param [leccode, itm_id, userid, lecnumb, off_score]
     * @return
     * @throws MdbException
     */
    public static int updateAttend(MdbDriver oDb,  Map param) throws MdbException  {
    	final String sql = "MERGE INTO wlc_etc_scr dst " +
    	"      USING (SELECT :lec_cd lec_cd, :usn usn, " +
    	"                    NVL (:att_score, 0) att_score " +
    	"               FROM DUAL) src " +
    	"         ON (    src.lec_cd = dst.lec_cd " +
    	"             AND src.usn = dst.usn) " +
    	"    WHEN MATCHED THEN " +
    	"       UPDATE " +
    	"          SET dst.upt_dt = SYSDATE, dst.att_score = src.att_score " +
    	"    WHEN NOT MATCHED THEN " +
    	"       INSERT (lec_cd, usn, att_score, upt_dt) " +
    	"       VALUES (src.lec_cd, src.usn, src.att_score, SYSDATE) ";
    	
    	
    	return oDb.executeUpdate(sql, param);
    		
    }

}