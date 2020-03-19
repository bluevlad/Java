/*
 * Created on 2006. 9. 22.
 *
 * Copyright (c) 2006 UBQ All rights reserved.
 */
package modules.wlc.classroom.learner;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


import maf.mdb.CommonDB;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProgressDB extends CommonDB {
    
	private static Log logger = LogFactory.getLog(ProgressDB.class);

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
    public static List getList(MdbDriver oDb, Map param) throws MdbException {

        final String sql ="SELECT chp.itm_id, itm_title, (FN_GET_TOTAL_SEC(NVL(chp.itm_maxtimeallowed,0))) AS t_time, "   +
            "    DECODE(SIGN((FN_GET_TOTAL_SEC(NVL(chp.itm_maxtimeallowed,0)))-(FN_GET_TOTAL_SEC(prg.total_time))), -1,  "   +
            "    FN_GET_TOTAL_SEC(NVL(chp.itm_maxtimeallowed,0)),  "   +
            "    TRUNC(FN_GET_TOTAL_SEC(prg.total_time))) AS j_time,  "   +
            "    TRUNC(FN_GET_TOTAL_SEC(prg.total_time)) AS s_time,  "   +
            "    chp.totpage AS t_page, (SELECT COUNT(itm_id) FROM wlc_page_prg  "   +
            "                            WHERE lec_cd=chp.lec_cd AND itm_id=chp.itm_id AND usn = :usn"   +
            "                                AND page_nm NOT LIKE '%left%'   "   +
            "                                AND page_nm NOT LIKE '%right%'  "   +
            "                                AND page_nm NOT LIKE '%bottom%' "   +
            //"                                AND page_nm NOT LIKE '%top%'    "   +
            "                                AND page_nm NOT LIKE '%frame%'    "   +
            "                           ) AS j_page, "   +
            "    chp.itm_sequence "   +
            " FROM wlc_lec_chp chp, (SELECT * FROM wlc_chp_prg "   +
            "                      WHERE lec_cd = :lec_cd AND usn = :usn) prg "   +
            " WHERE chp.lec_cd=prg.lec_cd(+) AND chp.itm_id=prg.itm_id(+)  "   +
            "        AND chp.lec_cd = :lec_cd AND chp.launch_data IS NOT NULL ";
        
        return oDb.selector(Map.class, sql, param);
    }

    /**
     * 하나의 레코드 읽어 오기(진도기준 정보)
     *
     * @param oDb
     * @param param
     * @return Map
     * @throws MdbException
     */
    public static Map getOneJindo(MdbDriver oDb, Map param) throws MdbException {
        String sql = "SELECT * FROM wlc_rat_mst WHERE lec_cd = :lec_cd ";
        return (Map) oDb.selectorOne(Map.class, sql, param);
    }

    /**
     * 하나의 레코드 읽어 오기(강의별 최소 학습시간 정보)
     *
     * @param oDb
     * @param param
     * @return String
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
     * @return String
     * @throws MdbException
     */
    public static String getOneLearningTotalTime(MdbDriver oDb, Map param) throws MdbException {
        String sql = "SELECT FN_GET_TOTAL_TIME_H(sum(FN_GET_TOTAL_SEC(total_time))) " +
        " FROM wlc_chp_prg " +
        " WHERE lec_cd = :lec_cd and usn = :usn";
        return (String)oDb.selectOne(sql, param);
    }

    /**
     * 학생이 학습할 강의안 가져오기 - 이전단원, 다음단원 강의컨텐츠 URL정보
     *
     * @param oDb
     * @param param
     * @return String
     * @throws MdbException
     */
    public static String getMovingLearningInfo(MdbDriver oDb, Map param) throws MdbException {
        String sql = "select nvl(htmcode,'') as htmcode from " +
        " ( " +
        "    select htmcode from wlc_lec_chp where lec_cd=:lec_cd ";
        if("prev".equals(param.get("act_move"))) {
            sql = sql + " and itm_sequence < (select itm_sequence from wlb_lec_chp ";
        } else {
            sql = sql + " and itm_sequence > (select itm_sequence from wlb_lec_chp ";
        }
        sql = sql + " where lec_cd = :lec_cd and htmcode = :htmcode ) ";
        if("prev".equals(param.get("act_move"))) {
            sql = sql + " order by itm_sequence desc ";
        } else {
            sql = sql + " order by itm_sequence ";
        }
        sql = sql + " )";
        sql = sql + " where rownum=1 ";

        return (String)oDb.selectOne(sql, param);
    }

    /**
     * 컨텐츠 유형을 가져온다.
     *
     * @param oDb
     * @param leccode
     * @return String
     * @throws MdbException
     */
    public static String getContent_type(MdbDriver oDb, Map param) throws MdbException {
        final String sql = "SELECT a.cnt_type " +
        " FROM wlc_inx_lst a, wlc_lec_chp b " +
        " WHERE a.sjt_cd = b.sjt_cd " +
        " and a.htmcode = b.htmcode  " +
        " and b.lec_cd = :lec_cd " +
        " and b.htmcode = :htmcode ";
        return (String)oDb.selectOne(sql, param);
    }

    /**
     * 해당 차시의 정보를 가져온다
     *
     * @param oDb
     * @param param
     * @return Map
     * @throws MdbException
     */
    public static Map getLearningInfo(MdbDriver oDb, Map param) throws MdbException {
        final String sql = "select b.lec_cd, b.itm_id, a.htmcode, htmurl launch_data, htmurl, " +
        "       cenname itm_title, a.cnt_width, a.cnt_height, " +
        "       chp_sdat, chp_edat, a.lrntime " +
        " from wlc_inx_lst a, wlc_lec_chp b " +
        " where a.sjt_cd = b.sjt_cd " +
        " and a.htmcode = b.htmcode " +
        " and b.lec_cd = :lec_cd " +
        " and b.htmcode = :htmcode ";
        return (Map) oDb.selectorOne(Map.class, sql, param);
    }

    /**
     * 시간 진도 - 학습진행이력 저장한다.
     *
     * @param oDb
     * @param param -  flag 액션플래그[time, page], save_time_flag 저장플래그[S:최초, M:중간, E:최종]
     * @return long
     * @throws MdbException
     */
    public static long saveTimeProgress(MdbDriver oDb, Map param) throws MdbException {
        int retValue = 0;
        CallableStatement   cstmt    = null;
        String sql = "{call sp_wlb_chp_prg (:usn, :htmcode, :lec_cd, :lesson_location, :lesson_status, " +
        		" :core_entry, :score_raw, :total_time, :core_exit, :save_time_flag, :flag, ?)}";

        try {
            cstmt = oDb.prepareCall(sql, param);
            cstmt.registerOutParameter(12, java.sql.Types.INTEGER);

            cstmt.executeUpdate();
            retValue = cstmt.getInt(12);
            if(retValue < 0) {
                retValue = -1;
            }
        } catch (SQLException s) {
            retValue = 0;
            oDb.rollback();
            logger.error(s.getMessage());
        }finally{
            release(cstmt);
        }
        return retValue;
    }

    /**
     * 페이지 진도 - 학습진행이력 저장한다.
     *
     * @param oDb
     * @param param - page_flag //onload :N, unload :Y
     * @return long
     * @throws MdbException
     */
    public static long savePageProgress(MdbDriver oDb, Map param) throws MdbException {
        int retValue = 0;
        CallableStatement   cstmt    = null;
        String sql = "{call sp_wlb_page_prg (:usn, :lec_cd, :itm_id, :page_nm, :page_no, :page_flag, :usn, ?)}";

        try {
            cstmt = oDb.prepareCall(sql, param);
            cstmt.registerOutParameter(9, java.sql.Types.INTEGER);

            cstmt.executeUpdate();
            retValue = cstmt.getInt(9);
            if(retValue < 0) {
                retValue = -1;
            }
        } catch (SQLException s) {
            retValue = 0;
            oDb.rollback();
            logger.error(s.getMessage());
        }finally{
            release(cstmt);
        }
        return retValue;
    }

}