/*
 * Created on 2006. 10. 13.
 *
 * Copyright (c) 2006 UBQ All rights reserved.
 */
package modules.wlc.classroom.tutor;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import maf.beans.NavigatorInfo;
import maf.mdb.CommonDB;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class ScoreDB extends CommonDB {
    private static Log logger = LogFactory.getLog(ScoreDB.class);

    /**
     * 목록 가져오기
     *
     * @param oDb
     * @return
     * @throws MdbException
     */
    public static void getList(MdbDriver oDb, NavigatorInfo navigator, Map param, String sWhere, boolean isAll) throws MdbException {
        List list = null;
        final String sql = "SELECT * " +
        "  FROM (SELECT req.usn, usr.userid, nm, req.lec_cd, req.req_stat, grd.score1, grd.score2, grd.score3, grd.score4, " +
        "               grd.score5, grd.score6, grd.score7, grd.score8, grd.score9, grd.score10, " +
        "               tot_score, grd.grad, tot_cnt, req.is_grad " +
        "          FROM wlc_lec_req req, maf_user usr, wlc_fnl_grd grd " +
        "          WHERE req.lec_cd = grd.lec_cd(+) " +
        "          AND req.usn = grd.usn(+) " +
        "          and req.usn = usr.usn " +
        "          AND req.lec_cd = :lec_cd  " +
        "          AND req.req_stat IN ('LP', 'LE'))";

        String sOrder = navigator.getOrderSql();
		if(!isAll) {
			oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
			navigator.setTotalCount(ScoreDB.getRecordCount(oDb, param, sWhere.toString()));
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
        final String sql ="SELECT count(*) " +
        "  FROM (SELECT req.usn, usr.userid, nm, req.lec_cd, req.req_stat, grd.score1, grd.score2, grd.score3, grd.score4, " +
        "               grd.score5, grd.score6, grd.score7, grd.score8, grd.score9, grd.score10, " +
        "               tot_score, grd.grad, tot_cnt, req.is_grad " +
        "          FROM wlc_lec_req req, maf_user usr, wlc_fnl_grd grd " +
        "          WHERE req.lec_cd = grd.lec_cd(+) " +
        "          AND req.usn = grd.usn(+) " +
        "          and req.usn = usr.usn " +
        "          AND req.lec_cd = :lec_cd  " +
        "          AND req.req_stat IN ('LP', 'LE'))";

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
        String sql = "SELECT mst.lec_cd, grd.usn, " +
        "       NVL(mst.jindo_time, '') as jindo_time, NVL(FN_GET_WLCJINDO_TIME(mst.lec_cd, :usn), 0) AS jindo_time_score,  " +
        "       NVL(mst.jindo_page, '') as jindo_page, NVL(FN_GET_WLCJINDO_PAGE(mst.lec_cd, :usn), 0) AS jindo_page_score ,   " +
        "       NVL(mst.ratexam, '') as ratexam, NVL(FN_GET_WLCEXAM_SCORE(mst.lec_cd, :usn), 0) AS ratexam_score,   " +
        "       mst.ratreport, FN_GET_WLCREPORT_SCORE(mst.lec_cd, :usn) AS ratreport_score,   " +
        "       mst.ratforum, NVL(mst.ratoffline, '') as ratoffline,  " +
        "       NVL(FN_GET_WLCOFFLINE_SCORE(mst.lec_cd, :usn), 0) AS ratoffline_score,   " +
        "       mst.ratetc, FN_GET_WLCETC_SCORE(mst.lec_cd, :usn) AS ratetc_score,  " +
        "       mst.ratadd, FN_GET_WLCETC_SCORE(mst.lec_cd, :usn) AS ratadd_score,  " +
        "       NVL(FN_GET_WLCFINAL_SCORE(mst.lec_cd, :usn), 0) AS sum_score,  " +
        "       NVL(grd.tot_score,0) as tot_score,  " +
        "       grd.grad,  " +
        "       NVL(grd.tot_cnt,0) as tot_cnt, (select is_grad from wlc_lec_req where lec_cd = :lec_cd AND usn = :usn) is_grad  " +
        "  FROM wlc_rat_mst mst , (SELECT lec_cd, usn, tot_cnt, score10, tot_score, grad  " +
        "                            FROM wlc_fnl_grd  " +
        "                            WHERE lec_cd = :lec_cd  " +
        "                            AND usn = :usn) grd  " +
        "  WHERE mst.lec_cd = grd.lec_cd(+)  " +
        "  AND mst.lec_cd = :lec_cd ";
        ;
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
        String sql = "SELECT * "
            + " FROM wlc_rat_mst  "
            + " WHERE lec_cd = :lec_cd ";
        ;
        return (Map) oDb.selectorOne(Map.class, sql, param);
    }

    /**
     * 점수 등록/수정하기
     *
     * @param oDb
     * @param leccode
     * @param stdcodes[]
     * @param reqnumbs[]
     * @param addscores[]
     * @param prfcode
     * @param type
     * @return int 처리결과
     * @exception DBHandlerException, DBConnectionFailedException
     */
    public static int updateScores(MdbDriver oDb, String lec_cd, String[] stdcodes, int[] addscores, String prfcode) throws MdbException  {

        int retValue = 0, result = 0;

        String sql = "{call SP_WCE_FNL_GRD_SCOREING (?,?,?,?,?)}";

        CallableStatement cstmt = null;
        
        try {
            cstmt = oDb.prepareCall(sql);
            for(int i=0 ; i< stdcodes.length; i++) {
                cstmt.setString(1, lec_cd);        //강의코드
                cstmt.setString(2, stdcodes[i]);   //학습자 아이디
                cstmt.setFloat(3, addscores[i]);  //추가점수
                cstmt.setString(4, prfcode);     //등록자 아이디
                cstmt.registerOutParameter(5, java.sql.Types.INTEGER);

                cstmt.executeUpdate();
                result = cstmt.getInt(5);

                if( result < 0) {
                    retValue = -1;
                    break;
                } else if(result == -80) {
                    retValue = -81;
                    break;
                } else {
                    retValue = retValue + result;
                }
            }
            if(result < 0) {
                oDb.rollback();
            } else {
                oDb.commit();
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
        	"SELECT chp.itm_id, itm_title, (FN_GET_TOTAL_SEC(NVL(chp.itm_maxtimeallowed,0))) AS t_time,  " +
        	"       DECODE(SIGN((FN_GET_TOTAL_SEC(NVL(chp.itm_maxtimeallowed,0)))-(FN_GET_TOTAL_SEC(prg.total_time))), -1,   " +
        	"       FN_GET_TOTAL_SEC(NVL(chp.itm_maxtimeallowed,0)),   " +
        	"       TRUNC(FN_GET_TOTAL_SEC(prg.total_time))) AS j_time,   " +
        	"       TRUNC(FN_GET_TOTAL_SEC(prg.total_time)) AS s_time,   " +
        	"       chp.totpage AS t_page, (SELECT COUNT(itm_id) FROM wlc_page_prg   " +
        	"                                 WHERE lec_cd = chp.lec_cd AND itm_id = chp.itm_id AND usn = :usn " +
        	"                                 AND page_nm NOT LIKE '%left%'    " +
        	"                                 AND page_nm NOT LIKE '%right%'   " +
        	"                                 AND page_nm NOT LIKE '%bottom%'  " +
        	"                                 AND page_nm NOT LIKE '%top%'     " +
        	"                                 AND page_nm NOT LIKE '%frame%'     " +
        	"                           ) AS j_page,  " +
        	"       chp.itm_sequence  " +
        	"  FROM wlc_lec_chp chp, (SELECT * FROM wlc_chp_prg  " +
        	"                           WHERE lec_cd = :lec_cd  " +
        	"                           AND usn = :usn) prg  " +
        	"  WHERE chp.lec_cd = prg.lec_cd(+)  " +
        	"  AND chp.itm_id=prg.itm_id(+)   " +
        	"  AND chp.lec_cd = :lec_cd  " +
        	"  AND chp.launch_data IS NOT NULL ";

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
        final  String sql = "SELECT count(chp.itm_id) "   +
    	"  FROM wlc_lec_chp chp, (SELECT * FROM wlc_chp_prg  " +
    	"                           WHERE lec_cd = :lec_cd  " +
    	"                           AND usn = :usn) prg  " +
    	"  WHERE chp.lec_cd = prg.lec_cd(+)  " +
    	"  AND chp.itm_id=prg.itm_id(+)   " +
    	"  AND chp.lec_cd = :lec_cd  " +
    	"  AND chp.launch_data IS NOT NULL " + sWhere;
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
        " WHERE lec_cd = :lec_cd and usn = :usn";
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
    /**
     * 개인 레포트 제출정보 가져오기
     *
     * @param oDb
     * @param param
     * @param sWhere
     * @return long
     * @throws MdbException
     */
	public static List getExam(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "SELECT a.exmid, a.lec_cd, a.exmtitle, a.exmdesc, " +
		"       b.rst_sdt, b.rst_status, b.rstscore, b.rstscore100 , b.passing_yn " +
		"  FROM wlc_exm_mst a, wlc_exm_rst b " +
		"  WHERE a.exmid = b.exmid " +
		"  AND a.lec_cd = :lec_cd  " +
		"  and b.usn = :usn ";

		return oDb.selector(Map.class, sql, param);
	}

    /**
     * 개인 레포트 제출정보 가져오기
     *
     * @param oDb
     * @param param
     * @param sWhere
     * @return long
     * @throws MdbException
     */
	public static List getRptList(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "select a.lec_cd, a.rptcode, a.rpttitle, a.rptdesc, a.rpt_sdat, a.rpt_edat, a.rptrate,  " +
		"       b.usn, b.givtitle, b.givdesc, b.givdat, b.usrscore,  " +
		"       (select new_filename from file_att where pds_cd = :pds_cd and main_cd = a.lec_cd and sub_cd = a.rptcode and file_id = b.usn) new_filename,  " +
		"       (select org_filename from file_att where pds_cd = :pds_cd and main_cd = a.lec_cd and sub_cd = a.rptcode and file_id = b.usn) org_filename  " +
		"  from wlc_rpt_mst a, wlc_rpt_giv b  " +
		"  where a.lec_cd = b.lec_cd  " +
		"  and a.lec_cd = :lec_cd  " +
		"  and b.usn = :usn ";

		return oDb.selector(Map.class, sql, param);
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
            "  (SELECT lec_cd , itm_id, itm_title, itm_sequence FROM wlc_lec_chp WHERE lec_cd = :lec_cd AND place_type = 'F') a,  " +
            "  (SELECT itm_id, reg_dt, off_score, appr_dt FROM wlc_off_req WHERE lec_cd = :lec_cd and usn = :usn) b "   +
            "  WHERE a.itm_id=b.itm_id(+) " ;

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
            "  (SELECT lec_cd , itm_id, itm_title, itm_sequence FROM wlc_lec_chp WHERE lec_cd = :lec_cd AND place_type = 'F') a,  " +
            "  (SELECT itm_id, reg_dt, off_score FROM wlc_off_req WHERE lec_cd = :lec_cd and usn = :usn) b "   +
            "  WHERE a.itm_id=b.itm_id(+)  " + sWhere;

        return oDb.selectOneInt(sql, param);
    }

    /**
     * 개별 학습자 기타점수 정보 가져오기
     *
     * @param oDb
     * @param navigator
     * @param param
     * @param sWhere
     * @return
     * @throws MdbException
     */
    public static void getEtc(MdbDriver oDb, NavigatorInfo navigator, Map param, String sWhere, boolean all) throws MdbException {
        List list = null;
        final String sql =
            " SELECT * FROM ( " +
            "    SELECT  req.lec_cd, req.usn, req.lec_num, off.reg_dt, off.etcscore, " +
            "    FN_GET_USRID(req.usn) AS usrid, FN_GET_USRNAME(req.usn) AS usrname " +
            "    FROM " +
            "      wlc_lec_req req,  " +
            "      (SELECT * FROM wlc_etc_scr WHERE lec_cd = :lec_cd and lec_num = :lec_num) off "   +
            "    WHERE req.lec_cd=off.lec_cd(+) AND req.usn=off.usn(+) AND req.lec_num=off.lec_num(+) " +
            "    AND req.lec_cd = :lec_cd AND req.lec_num = :lec_num AND req.req_stat not in ('CA','CE')  " +
            "    AND req.usn = :usn" +
            " ) ";

        String sOrder = " ORDER BY FN_GET_USRNAME(usn), lec_num ASC ";

        if (!all) {
            oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
            navigator.setTotalCount(getEtcRecordCount(oDb, param, sWhere.toString()));
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
    private static long getEtcRecordCount(MdbDriver oDb, Map param, String sWhere) throws MdbException {
        final String sql =
            " SELECT count(usn) FROM ( " +
            "    SELECT  req.lec_cd, req.usn, req.lec_num, off.reg_dt, off.etcscore, " +
            "    FN_GET_USRID(req.usn) AS usrid, FN_GET_USRNAME(req.usn) AS usrname " +
            "    FROM " +
            "      wlc_lec_req req,  " +
            "      (SELECT * FROM wlc_etc_scr WHERE lec_cd = :lec_cd and lec_num = :lec_num) off "   +
            "    WHERE req.lec_cd=off.lec_cd(+) AND req.usn=off.usn(+) AND req.lec_num=off.lec_num(+) " +
            "    AND req.lec_cd = :lec_cd AND req.lec_num = :lec_num AND req.req_stat not in ('CA','CE')  " +
            "    AND req.usn = :usn " +
            " ) " + sWhere;

        return oDb.selectOneInt(sql, param);
    }

    /**
     * 개별 학습자 학습참여도 정보 가져오기
     *
     * @param oDb
     * @param navigator
     * @param param
     * @param sWhere
     * @return
     * @throws MdbException
     */
    public static void getAtt(MdbDriver oDb, NavigatorInfo navigator, Map param, String sWhere, boolean all) throws MdbException {
        List list = null;
        final String sql =
            " SELECT * FROM ( " +
            "    SELECT  req.lec_cd, req.usn, req.lec_num, FN_GET_USRID(req.usn) AS usrid, " +
            "    FN_GET_USRNAME(req.usn) AS usrname, FN_GET_USRMAIL(req.usn) AS usrmail, app.insert_dt, app.attscore " +
            "    FROM " +
            "      wlc_lec_req req,  " +
            "      (SELECT * FROM wlc_etc_scr WHERE lec_cd = :lec_cd and lec_num = :lec_num) app "   +
            "    WHERE req.lec_cd=app.lec_cd(+) AND req.usn=app.usn(+) AND req.lec_num=app.lec_num(+) " +
            "    AND req.lec_cd = :lec_cd AND req.lec_num = :lec_num AND req.req_stat not in ('CA','CE')  " +
            "    AND req.usn = :usn " +
            " ) ";

        String sOrder = " ORDER BY FN_GET_USRNAME(usn), lec_num ASC ";

        if (!all) {
            oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
            navigator.setTotalCount(getAttRecordCount(oDb, param, sWhere.toString()));
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
    private static long getAttRecordCount(MdbDriver oDb, Map param, String sWhere) throws MdbException {
        final String sql =
            " SELECT count(usn) FROM ( " +
            "    SELECT  req.lec_cd, req.usn, req.lec_num, FN_GET_USRID(req.usn) AS usrid, " +
            "    FN_GET_USRNAME(req.usn) AS usrname, FN_GET_USRMAIL(req.usn) AS usrmail, app.reg_dt, app.attscore " +
            "    FROM " +
            "      wlc_lec_req req,  " +
            "      (SELECT * FROM wln_etc_scr WHERE lec_cd = :lec_cd and lec_num = :lec_num) app "   +
            "    WHERE req.lec_cd=app.lec_cd(+) AND req.usn=app.usn(+) AND req.lec_num=app.lec_num(+) " +
            "    AND req.lec_cd = :lec_cd AND req.lec_num = :lec_num AND req.req_stat not in ('CA','CE')  " +
            "    AND req.usn = :usn " +
            " ) " + sWhere;

        return oDb.selectOneInt(sql, param);
    }

}