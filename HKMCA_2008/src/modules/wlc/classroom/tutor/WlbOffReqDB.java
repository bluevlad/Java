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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class WlbOffReqDB extends CommonDB {

    private static Log logger = LogFactory.getLog(WlbOffReqDB.class);

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
        final String sql =
        	" SELECT lec_cd, a.itm_id, itm_title, itm_sequence, nvl(req_cnt,0) req_cnt"   +
        	"   FROM (SELECT lec_cd, itm_id, itm_title, itm_sequence"   +
        	"           FROM wlc_lec_chp"   +
        	"          WHERE lec_cd = :lec_cd"   +
        	"            AND place_type = 'F') a,"   +
        	"        (SELECT COUNT (usn) AS req_cnt, itm_id"   +
        	"             FROM wlc_off_req"   +
        	"            WHERE lec_cd = :lec_cd"   +
        	"         GROUP BY itm_id) b"   +
        	"  WHERE a.itm_id = b.itm_id(+)"  ;

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
        final String sql =
            "SELECT count(a.itm_id)  "   +
            "   FROM "  +
            "   ( " +
            "     SELECT lec_cd , itm_id, itm_title, itm_sequence " +
            "     FROM wlc_lec_chp " +
            "     WHERE lec_cd = :lec_cd AND place_type='F' " +
            "   ) a, " +
            "   ( "  +
            "     SELECT COUNT(usn) AS req_cnt, itm_id " +
            "     FROM wlc_off_req " +
            "     WHERE lec_cd = :lec_cd  " +
            "     GROUP BY itm_id  " +
            "   ) b "  +
            " WHERE a.itm_id = b.itm_id(+) " +
            sWhere;

        return oDb.selectOneInt(sql, param);
    }

    /**
     * 하나의 레코드 읽어 오기
     *
     * @param oDb
     * @param param
     * @return Map
     * @throws MdbException
     */
    public static Map getOne(MdbDriver oDb, Map param) throws MdbException {
        String sql = "select * from wlc_lec_chp where lec_cd = :lec_cd and itm_id = :itm_id ";
        return (Map) oDb.selectorOne(Map.class, sql, param);
    }

    /**
     * 오프라인 강의 정보 가져오기
     *
     * @param oDb
     * @param param
     * @return Map
     * @throws MdbException
     */
    public static Map getLecChpOff(MdbDriver oDb, Map param) throws MdbException {
        final String sql = "SELECT itm_id, itm_title, off_place, off_date_info, netg_cid, netg_sub_cid " +
            " FROM wlb_lec_chp  " +
            " WHERE lec_cd = :lec_cd " +
            " AND itm_id = :itm_id ";

        return (Map) oDb.selectorOne(Map.class, sql, param);
    }

    /**
     * 오프라인 강의 신청자 목록 가져오기
     * @param oDb
     * @param param [leccode, itm_id] 
     * @return
     * @throws MdbException
     */
    public static List getWlboffreqList(MdbDriver oDb, Map param) throws MdbException {
    	final String sql = " SELECT mu.nm, lr.leccode, :itm_id itm_id, lr.lec_num, lr.usn, lo.user_chk,"   +
    	"        NVL (lo.off_score, 0) off_score, NVL (lo.prf_chk, 'N') prf_chk, req_stat"   +
    	"   FROM (SELECT *"   +
    	"           FROM wlc_off_req"   +
    	"          WHERE lec_cd = :lec_cd"   +
    	"            AND itm_id = :itm_id) lo, v_jmf_user mu, wlc_lec_req lr"   +
    	"  WHERE lr.usn = mu.usn"   +
    	"    AND lr.lec_cd = :lec_cd"   +
    	"    AND lr.lec_cd = lo.lec_cd(+)"   +
    	"    AND lr.usn = lo.usn(+)"   +
    	"    AND lr.lec_num = lo.lec_num(+)"  ;
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
    	final String sql = " MERGE INTO wlb_off_req dst"   +
    	"      USING (SELECT :lec_cd lec_cd, :itm_id itm_id, :usn usn, :lec_num lec_num,"   +
    	"                    NVL (:off_score, 0) off_score"   +
    	"               FROM DUAL) src"   +
    	"         ON (    src.lec_cd = dst.lec_cd"   +
    	"             AND src.itm_id = dst.itm_id"   +
    	"             AND src.usn = dst.usn"   +
    	"             AND src.lec_num = dst.lec_num)"   +
    	"    WHEN MATCHED THEN"   +
    	"       UPDATE"   +
    	"          SET dst.appr_dt = SYSDATE, dst.off_score = src.off_score,"   +
    	"              dst.prf_chk = DECODE (src.off_score, 0, 'N', 'Y')"   +
    	"    WHEN NOT MATCHED THEN"   +
    	"       INSERT (lec_cd, itm_id, lec_num, usn, user_chk, reg_dt, off_score, prf_chk, appr_dt)"   +
    	"       VALUES (src.lec_cd, src.itm_id, src.lec_num, src.usn, 'Y', SYSDATE, src.off_score,"   +
    	"               DECODE (src.off_score, 0, 'N', 'Y'), SYSDATE)"  ;
    	
    	
    	return oDb.executeUpdate(sql, param);
    		
    }
//    /**
//     * 오프라인 강의 신청자 목록 가져오기
//     *
//     * @param oDb
//     * @param navigator
//     * @param param
//     * @param sWhere
//     * @return
//     * @throws MdbException
//     */
//    public static void getWlboffreqList(MdbDriver oDb, NavigatorInfo navigator, Map param, String sWhere) throws MdbException {
//        List list = null;
//        final String sql =
//            "SELECT a.*, b.userid as usrid, b.nm, b.email, b.hp "   +
//            "  FROM wlb_off_req a , v_jmf_user b "  +
//            "  WHERE a.userid = b.usn " +
//            "  AND a.leccode   = :leccode " +
//            "  AND a.itm_id      = :itm_id  "
//            ;
//
//        String sOrder = " ORDER BY a.userid ";
//
//        oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
//        list = oDb.selector(Map.class, sql+sOrder, param);
//        navigator.setList(list);
//        navigator.setTotalCount(getWlboffreqRecordCount(oDb, param, ""));
//        navigator.sync();
//    }
//
//    /**
//     * 오프라인 강의 신청자 레코드 카운트 가져오기
//     *
//     * @param oDb
//     * @param param
//     * @param sWhere
//     * @return
//     * @throws MdbException
//     */
//    private static long getWlboffreqRecordCount(MdbDriver oDb, Map param, String sWhere) throws MdbException {
//        final String sql =
//            "SELECT count(a.userid) "   +
//            "  FROM wlb_off_req a , v_jmf_user b  "  +
//            "  WHERE a.userid = b.usn " +
//            "  AND a.leccode   = :leccode " +
//            "  AND a.itm_id      = :itm_id  " +
//            sWhere;
//
//        return oDb.selectOneInt(sql, param);
//    }

//    /**
//     * 오프라인 점수 입력
//     *
//     * @param seq_no[]
//     * @param off_score[]
//     * @return int
//     * @exception DBHandlerException, DBConnectionFailedException
//     */
//    public static int update(MdbDriver oDb,  String[] seq_no, String[] off_score) throws MdbException  {
//
//        int retValue = 0, result = 0;
//        int i = 0;
//
//        String sql = "{call sp_wlb_off_req_score_update (?,?,?)}";
//        CallableStatement   cstmt    = null;
//        try {
//            cstmt = oDb.prepareCall(sql);
//            for(int j=0 ; j< seq_no.length; j++) {
//                i = 1;
//
//                cstmt.setString(i++, seq_no[j]);
//                cstmt.setLong(i++,MafUtil.parseInt(off_score[j]));
//                cstmt.registerOutParameter(i, OracleTypes.INTEGER);
//                result = cstmt.executeUpdate();
//                if( result < 0) {
//                    retValue = -1;
//                    break;
//                } else if(result == -80) {
//                    retValue = -81;
//                    break;
//                } else {
//                    retValue = retValue + result;
//                }
//            }
//            if(result < 0) {
//                oDb.rollback();
//            } else {
//                oDb.commit();
//            }
//        } catch (SQLException s) {
//            retValue = 0;
//            oDb.rollback();
//            logger.error(s.getMessage());
//        }finally{
//            release(cstmt);
//        }
//        return retValue;
//    }
//    
//    /**
//     * 강사 출석 체크 입력
//     *
//     * @param seq_no[]
//     * @return int
//     * @exception DBHandlerException, DBConnectionFailedException
//     */
//    public static int updateAttend(MdbDriver oDb,  String[] seq_no) throws MdbException  {
//
//        int retValue = 0, result = 0;
//        int i = 0;
//
//        String sql = "{call sp_wlb_off_req_attend_update (?,?)}";
//        CallableStatement   cstmt    = null;
//        try {
//            cstmt = oDb.prepareCall(sql);
//            for(int j=0 ; j< seq_no.length; j++) {
//                i = 1;
//
//                cstmt.setString(i++, seq_no[j]);
//                cstmt.registerOutParameter(i, OracleTypes.INTEGER);
//                result = cstmt.executeUpdate();
//                if( result < 0) {
//                    retValue = -1;
//                    break;
//                } else if(result == -80) {
//                    retValue = -81;
//                    break;
//                } else {
//                    retValue = retValue + result;
//                }
//            }
//            if(result < 0) {
//                oDb.rollback();
//            } else {
//                oDb.commit();
//            }
//        } catch (SQLException s) {
//            retValue = 0;
//            oDb.rollback();
//            logger.error(s.getMessage());
//        }finally{
//            release(cstmt);
//        }
//        return retValue;
//    }
    
}