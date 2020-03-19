/*
 * Created on 2006. 10. 13.
 *
 * Copyright (c) 2006 UBQ All rights reserved.
 */
package modules.wlc.classroom.learner;

import java.util.List;
import java.util.Map;

import maf.base.BaseDAO;
import maf.beans.NavigatorInfo;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;


public class ScoreDB extends BaseDAO {

    /**
     * 목록 가져오기
     *
     * @param oDb
     * @return
     * @throws MdbException
     */
    public void getList(MdbDriver oDb, NavigatorInfo navigator, Map param, String sWhere) throws MdbException {
        List list = null;
        final String sql =
            "SELECT * FROM ( " +
            "    SELECT req.usn, FN_GET_USRNAME(req.usn) AS usernm, " +
            "    req.lec_cd, req.lec_num, req.lec_num AS reqnumb, req.req_stat, " +
            "    grd.score1, grd.score2, grd.score3, grd.score4, grd.score5, grd.score6, grd.score7, " +
            "    grd.score8, grd.score9, NVL(grd.score10,0.0) AS score10, NVL(grd.tot_score,0.0) AS tot_score, grd.totgrad, NVL(grd.tot_cnt,0) AS tot_cnt,  " +
            "    FN_GET_WLCFINAL_SCORE(req.lec_cd, req.usn, req.lec_num) AS sum_score, req.is_grad " +
            "    FROM wlc_lec_req req, (SELECT * FROM wlc_fnl_grd WHERE lec_cd=:lec_cd) grd " +
            "    WHERE req.lec_cd=grd.lec_cd(+) AND req.usn=grd.usn(+) AND req.lec_num=grd.lec_num(+) AND req.req_stat in ('LP','LE') " +
            " )";

        String sOrder = navigator.getOrderSql();
        if(null == sOrder || "".equals(sOrder) || (sOrder.trim()).equals((("ORDER BY null").trim()))) {
            sOrder = " ORDER BY usernm, lec_num ASC " ;
        }

        oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
        list = oDb.selector(Map.class, sql + sWhere + sOrder, param);
        navigator.setList(list);
        navigator.setTotalCount(this.getRecordCount(oDb, param, sWhere.toString()));
        navigator.sync();
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
    private long getRecordCount(MdbDriver oDb, Map param, String sWhere) throws MdbException {
        final String sql =
            "SELECT count(userid) FROM ( " +
            "    SELECT req.usn, FN_GET_USRNAME(req.usn) AS usernm, " +
            "    req.lec_cd, req.lec_num, req.lec_num AS reqnumb, req.req_stat, " +
            "    grd.score1, grd.score2, grd.score3, grd.score4, grd.score5, grd.score6, grd.score7, " +
            "    grd.score8, grd.score9, grd.score10, grd.tot_score, grd.totgrad, NVL(grd.tot_cnt,0) AS tot_cnt,  " +
            "    FN_GET_WLCFINAL_SCORE(req.lec_cd, req.usn, req.lec_num) AS sum_score, req.is_grad " +
            "    FROM wlc_lec_req req, (SELECT * FROM wlc_fnl_grd WHERE lec_cd=:lec_cd) grd " +
            "    WHERE req.lec_cd=grd.lec_cd(+) AND req.usn=grd.usn(+) AND req.lec_num=grd.lec_num(+) AND req.req_stat in ('LP','LE') " +
            " )";

        return oDb.selectOneInt(sql + sWhere, param);
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
        final String sql = "SELECT mst.lec_cd, grd.usn,  " +
        "       NVL(mst.jindo_time, '') as jindo_time, NVL(FN_GET_WLCJINDO_TIME(mst.lec_cd, :usn), 0) AS jindo_time_score,  " +
        "       NVL(mst.jindo_page, '') as jindo_page, NVL(FN_GET_WLCJINDO_PAGE(mst.lec_cd, :usn), 0) AS jindo_page_score,  " +
        "       NVL(mst.ratexam, '') as ratexam, NVL(FN_GET_WLCEXAM_SCORE(mst.lec_cd, :usn), 0) AS ratexam_score, " +
        "       NVL(mst.ratreport, '') as ratreport, NVL(FN_GET_WLCREPORT_SCORE(mst.lec_cd, :usn), 0) AS ratreport_score,  " +
        "       NVL(mst.ratoffline, '') as ratoffline, NVL(FN_GET_WLCOFFLINE_SCORE(mst.lec_cd, :usn), 0) AS ratoffline_score,  " +
        "       NVL(mst.ratforum, '') as ratforum, NVL(FN_GET_WLCFORUM_SCORE(mst.lec_cd, :usn), 0) AS ratforum_score,  " +
        "       NVL(mst.ratproject, '') as ratproject, NVL(FN_GET_WLCPROJECT_SCORE(mst.lec_cd, :usn), 0) AS ratproject_score,  " +
        "       NVL(mst.ratdiscuss, '') as ratdiscuss, NVL(FN_GET_WLCOFFLINE_SCORE(mst.lec_cd, :usn), 0) AS ratdiscuss_score,  " +
        "       NVL(mst.ratetc, '') as ratetc, NVL(FN_GET_WLCETC_SCORE(mst.lec_cd, :usn), 0) AS ratetc_score,  " +
        "       NVL(mst.ratadd, '') as ratadd, NVL(FN_GET_WLCADD_SCORE(mst.lec_cd, :usn), 0) AS ratadd_score,  " +
        "       NVL(FN_GET_WLCFINAL_SCORE(mst.lec_cd, :usn), 0) AS sum_score,   " +
        "       NVL(grd.tot_score,0) as tot_score, grd.grad, NVL(grd.tot_cnt,0) as tot_cnt,   " +
        "       (select is_grad from wlc_lec_req where lec_cd = :lec_cd AND usn = :usn) is_grad  " +
        "  FROM wlc_rat_mst mst, (SELECT lec_cd, usn, tot_cnt, score10, tot_score, grad  " +
        "                           FROM wlc_fnl_grd  " +
        "                           WHERE lec_cd = :lec_cd  " +
        "                           AND usn = :usn) grd  " +
        "  WHERE mst.lec_cd = grd.lec_cd(+)  " +
        "  AND mst.lec_cd = :lec_cd ";
        
        return (Map) oDb.selectorOne(Map.class, sql, param);
    }

    /**
     * 하나의 레코드 읽어 오기
     *
     * @param oDb
     * @param param
     * @return
     * @throws MdbException
     */
    public static Map getOneRate(MdbDriver oDb, Map param) throws MdbException {
        String sql = "SELECT *" +
        		" FROM wlc_rat_mst" +
        		" WHERE lec_cd = :lec_cd";
        return (Map) oDb.selectorOne(Map.class, sql, param);
    }

    /**
     * 학습자 진도 정보 가져오기
     *
     * @param oDb
     * @param navigator
     * @param param
     * @param sWhere
     * @return
     * @throws MdbException
     */
    public static void getJindo(MdbDriver oDb, NavigatorInfo navigator, Map param, String sWhere, boolean all) throws MdbException {
        List list = null;
        final String sql =
            "SELECT chp.itm_id, itm_title, (FN_GET_TOTAL_SEC(NVL(chp.itm_maxtimeallowed,0))) AS t_time, "   +
            "    DECODE(SIGN((FN_GET_TOTAL_SEC(NVL(chp.itm_maxtimeallowed,0)))-(FN_GET_TOTAL_SEC(prg.total_time))), -1,  "   +
            "    FN_GET_TOTAL_SEC(NVL(chp.itm_maxtimeallowed,0)),  "   +
            "    TRUNC(FN_GET_TOTAL_SEC(prg.total_time))) AS j_time,  "   +
            "    TRUNC(FN_GET_TOTAL_SEC(prg.total_time)) AS s_time,  "   +
            "    chp.totpage AS t_page, (SELECT COUNT(itm_id) FROM wlb_page_prg  "   +
            "                            WHERE lec_cd=chp.lec_cd AND itm_id=chp.itm_id AND usn = :usn AND lec_num = :lec_num "   +
            "                                AND page_nm NOT LIKE '%left%'   "   +
            "                                AND page_nm NOT LIKE '%right%'  "   +
            "                                AND page_nm NOT LIKE '%bottom%' "   +
            //"                                AND page_nm NOT LIKE '%top%'    "   +
            "                                AND page_nm NOT LIKE '%frame%'    "   +
            "                           ) AS j_page, "   +
            "    chp.itm_sequence "   +
            " FROM wlc_lec_chp chp, (SELECT * FROM wlc_chp_prg "   +
            "                      WHERE lec_cd = :lec_cd AND usn = :usn AND lec_num = :lec_num) prg "   +
            " WHERE chp.lec_cd=prg.lec_cd(+) AND chp.itm_id=prg.itm_id(+)  "   +
            "        AND chp.lec_cd = :lec_cd AND chp.launch_data IS NOT NULL ";

        String sOrder = " ORDER BY to_number(chp.itm_sequence) ASC";

        if (!all) {
            oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
            navigator.setTotalCount(getJinodRecordCount(oDb, param, sWhere.toString()));
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
    private static long getJinodRecordCount(MdbDriver oDb, Map param, String sWhere) throws MdbException {
        final  String sql =
            "SELECT count(chp.itm_id) "   +
            " FROM wlc_lec_chp chp, (SELECT * FROM wlc_chp_prg "   +
            "                      WHERE lec_cd = :lec_cd AND usn = :usn AND lec_num = :lec_num) prg "   +
            " WHERE chp.lec_cd=prg.lec_cd(+) AND chp.itm_id=prg.itm_id(+)  "   +
            "        AND chp.lec_cd = :lec_cd AND chp.launch_data IS NOT NULL "  + sWhere;
        return oDb.selectOneInt(sql, param);
    }

    /**
     * 하나의 레코드 읽어 오기(진도기준 정보)
     *
     * @param oDb
     * @param param
     * @return
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
     * @return
     * @throws MdbException
     */
    public static String getOneLimit_study_time(MdbDriver oDb, Map param) throws MdbException {
        String sql = "SELECT NVL(limit_study_time,'0') FROM bas_lec WHERE lec_cd = :lec_cd ";
        return (String)oDb.selectOne(sql, param);
    }

    /**
     * 총학습시간 가져오기
     *
     * @param oDb
     * @param param
     * @return
     * @throws MdbException
     */
    public static String getOneLearningTotalTime(MdbDriver oDb, Map param) throws MdbException {
        String sql = "SELECT FN_GET_TOTAL_TIME_H(sum(FN_GET_TOTAL_SEC(total_time))) " +
        " FROM wlc_chp_prg " +
        " WHERE lec_cd = :lec_cd and usn = :usn and lec_num = :lec_num ";
        return (String)oDb.selectOne(sql, param);
    }

    /**
     * 개별학습자 시험 결과 가져오기
     *
     * @param oDb
     * @param navigator
     * @param param
     * @param sWhere
     * @return
     * @throws MdbException
     */
    public static void getExam(MdbDriver oDb, NavigatorInfo navigator, Map param, String sWhere, boolean all) throws MdbException {
        List list = null;
        final String sql =
            "SELECT mst.exmcode, mst.exmtitle, mst.exm_sdat_t1, mst.exm_edat_t1, mst.exm_sdat_t2,  " +
            " mst.exm_edat_t2, mst.exmcnt5, mst.exmcnt4, mst.exmcnt3, mst.exmcnt2, mst.exmcnt1, mst.exmtime,  " +
            " mst.exmtype, mst.exmopen, mst.exmrate, mst.insert_dt,  " +
            " FN_GET_WCCLECREQ_COUNT(mst.lec_cd, 'LP') AS lec_num, " +
            " FN_GET_WEXMMARKING_COUNT(mst.lec_cd, mst.exmcode) AS exmmarking, " +
            " rst.rstflag, rst.rst_sdt, DECODE(rst.rstflag, 'Y', NVL(rst.rstatscore,0)+NVL(rst.rstmtscore,0)+NVL(rst.rstoffscore,0), 0) AS usrscore, " +
            " rst.rstatscore, rst.rstmtscore, rst.rstoffscore, rst.rstcont, rst.rstfinal, rst.update_dt  " +
            " FROM wlc_exm_mst mst, (SELECT * FROM wlc_exm_rst WHERE usn = :usn AND lec_num = :lec_num ) rst  " +
            " WHERE mst.lec_cd=rst.lec_cd(+) AND mst.exmcode=rst.exmcode(+) AND mst.lec_cd = :lec_cd ";

        String sOrder = navigator.getOrderSql();
        if(null == sOrder || "".equals(sOrder) || (sOrder.trim()).equals((("ORDER BY null").trim()) )) {
            sOrder = " ORDER BY mst.exm_sdat_t1, mst.exm_sdat_t2 ASC ";
        }

        if (!all) {
            oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
            navigator.setTotalCount(getExamRecordCount(oDb, param, sWhere.toString()));
            navigator.sync();
        }
        list = oDb.selector(Map.class, sql + sOrder, param);
        navigator.setList(list);
    }

    /**
     * 레코드 카운트 가져오기
     *
     * @param oDb
     * @param param
     * @param sWhere
     * @return long
     * @throws MdbException
     */
    private static long getExamRecordCount(MdbDriver oDb, Map param, String sWhere) throws MdbException {
        final String sql =
            "SELECT count(mst.exmcode)  " +
            " FROM wlc_exm_mst mst, (SELECT * FROM wlc_exm_rst WHERE usn = :usn AND lec_num = :lec_num) rst  " +
            " WHERE mst.lec_cd=rst.lec_cd(+) AND mst.exmcode=rst.exmcode(+) AND mst.lec_cd = :lec_cd";

        return oDb.selectOneInt(sql, param);
    }

    /**
     * 개별 학습자 오프라인 정보 가져오기
     *
     * @param oDb
     * @param navigator
     * @param param
     * @param sWhere
     * @return
     * @throws MdbException
     */
    public static void getOff(MdbDriver oDb, NavigatorInfo navigator, Map param, String sWhere, boolean all) throws MdbException {
        List list = null;
        final String sql =
            "SELECT a.lec_cd , a.itm_id, a.itm_title, a.itm_sequence, b.reg_dt, b.off_score, b.appr_dt " +
            "  FROM " +
            "  (SELECT lec_cd , itm_id, itm_title, itm_sequence FROM wlc_lec_chp WHERE lec_cd = :lec_cd) a,  " +
            "  (SELECT itm_id, reg_dt, off_score, appr_dt FROM wlc_off_req WHERE lec_cd = :lec_cd and usn = :usn) b "   +
            "  WHERE a.itm_id=b.itm_id(+)  ";

        String sOrder = " ORDER BY itm_id ASC";

        if (!all) {
            oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
            navigator.setTotalCount(getOffRecordCount(oDb, param, sWhere.toString()));
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
    private static long getOffRecordCount(MdbDriver oDb, Map param, String sWhere) throws MdbException {
        final String sql =
            "SELECT count(a.itm_id) "   +
            "  FROM  " +
            "  (SELECT lec_cd , itm_id, itm_title, itm_sequence FROM wlc_lec_chp WHERE lec_cd = :lec_cd) a,  " +
            "  (SELECT itm_id, reg_dt, off_score FROM wlc_off_req WHERE lec_cd = :lec_cd and usn = :usn) b "   +
            "  WHERE a.itm_id=b.itm_id(+)  " +
            sWhere;

        return oDb.selectOneInt(sql, param);
    }

}