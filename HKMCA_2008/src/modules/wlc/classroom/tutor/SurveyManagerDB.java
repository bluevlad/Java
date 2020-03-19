/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.wlc.classroom.tutor;

import java.util.List;
import java.util.Map;

import maf.base.BaseDAO;
import maf.beans.NavigatorInfo;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;


/**
 * 설문관리용 DAO
 * @author ubq
 *
 */
public class SurveyManagerDB extends BaseDAO {

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
        final String sql = "select * from (SELECT wlc_srv_mst.*, " +
        " (select count(usn) from wlc_lec_req where lec_cd = wlc_srv_mst.lec_cd and req_stat in ('LP', 'LE')) cnt,  " +
        " (select count(usn) from wlc_srv_rst where lec_cd = wlc_srv_mst.lec_cd and surveyid = wlc_srv_mst.surveyid) srv  " +
        " FROM wlc_srv_mst " +
        " where lec_cd = :lec_cd) x ";


        String sOrder = navigator.getOrderSql();
        if (!isAll) {
            oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
            navigator.setTotalCount(SurveyManagerDB.getRecordCount(oDb, param, sWhere.toString()));
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
    private static long getRecordCount(MdbDriver oDb, Map param, String sWhere)
            throws MdbException {
        final String sql = "select count(*) from (SELECT wlc_srv_mst.*, " +
        " (select count(usn) from wlc_lec_req where lec_cd = wlc_srv_mst.lec_cd and req_stat in ('LP', 'LE')) cnt,  " +
        " (select count(usn) from wlc_srv_rst where lec_cd = wlc_srv_mst.lec_cd and surveyid = wlc_srv_mst.surveyid) srv  " +
        " FROM wlc_srv_mst " +
        " where lec_cd = :lec_cd)"  + sWhere;
        return oDb.selectOneInt(sql, param);
    }

    /**
     * 설문문항 가져오기 
     */
    public static List getSrvBnkList(MdbDriver oDb, Map param) throws MdbException {
        final String sql = "select aa.sjt_cd, aa.lec_cd, aa.surveyid, aa.queid chk, aa.seq,  " +
        " bb.quetitle, bb.queviw1, bb.queviw3, bb.queviw4, bb.queviw5,  " +
        " bb.queviw6, bb.queviw7, bb.queviw8, bb.queviw9, bb.queviw10, bb.queid, bb.quecount, bb.quetype " +
        " from (select a.sjt_cd, b.lec_cd, c.* " +
        " from bas_lec a, wlc_srv_mst b, wlc_srv_obj c " +
        " where a.lec_cd = b.lec_cd " +
        " and b.surveyid = c.surveyid(+) " +
        " and b.lec_cd = :lec_cd) aa, " +
        " (select a.sjt_cd, a.quetype, a.quecount, b.* " +
        " from wlc_srv_bnk a, wlc_srv_bnk_lang b " +
        " where a.queid = b.queid " +
        " and a.active_yn = 'Y' " +
        " and a.sjt_Cd = :sjt_cd " +
        " and b.lang = :lang) bb " +
        " where bb.queid = aa.queid(+) " +
        " order by surveyid, seq asc";

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
        final String sql =" SELECT * "   +
        "  FROM wlc_srv_mst "   +
        "  WHERE surveyid = :surveyid";
        return (Map) oDb.selectorOne(Map.class, sql, param);
    }

    /**
     * 하나의 레코드를 Update 한다.
     * 
     * @param oDb
     * @param param
     * @return
     * @throws MdbException
     */
    public static int updateOne(MdbDriver oDb, Map param) throws MdbException {
        final String sql = "update wlc_srv_mst set " +
      	" surveytitle = :surveytitle, " +
      	" surveydesc = :surveydesc, " +
      	" survey_sdat = :survey_sdat, " +
      	" survey_edat = :survey_edat, " +
      	" active_yn = :active_yn, " +
      	" upt_dt = sysdate, " +
      	" upt_id = :upt_id " +
      	" where surveyid = :surveyid";
        return oDb.executeUpdate(sql, param);
    }

    /**
     * 하나의 레코드를 Insert 한다.
     * 
     * @param oDb
     * @param param
     * @return
     * @throws MdbException
     */
    public static int insertOne(MdbDriver oDb, Map param) throws MdbException {
        final String sql = " INSERT INTO wlc_srv_mst "
                + " (surveyid, lec_cd, surveytitle, surveydesc, survey_sdat, survey_edat, upt_dt, upt_id) "
                + "  VALUES"
                + " (:lec_cd || SEQ_EXM_MST_ID.nextval, :lec_cd, :surveytitle, :surveydesc, :survey_sdat, :survey_edat, sysdate, :upt_id)";
        return oDb.executeUpdate(sql, param);
    }

    /**
     * 하나의 레코드를 Update 한다.
     * 
     * @param oDb
     * @param param
     * @return
     * @throws MdbException
     */
    public static int insertSrvObj(MdbDriver oDb, Map param) throws MdbException {
        final String sql = "insert into wlc_srv_obj " +
      	" (surveyid, queid, reg_id, reg_dt, seq) " +
      	" values (:surveyid, :queid, :reg_id, sysdate, :seq)";
        return oDb.executeUpdate(sql, param);
    }

    public static int deleteSrvObj(MdbDriver oDb, Map param) throws MdbException {
        final String sql = "delete wlc_srv_obj " +
      	" where surveyid = :surveyid";
        return oDb.executeUpdate(sql, param);
    }

    /**
     * 결과 목록 돌려 주기 
     */
    public static List getRstList(MdbDriver oDb, Map param) throws MdbException {
        final String sql = "select *  " +
        " from (select a.lec_cd, b.seq, a.surveyid, c.quetype, c.quecount, d.* " +
        "        from wlc_srv_mst a, wlc_srv_obj b, wlc_srv_bnk c, wlc_srv_bnk_lang d " +
        "        where a.surveyid = b.surveyid " +
        "        and b.queid = c.queid " +
        "        and c.queid = d.queid " +
        "        and a.surveyid = :surveyid " +
        "        and d.lang = :lang) aa, " +
        "      (select queid que, " +
        "              sum(a1) a1, sum(a2) a2, sum(a3) a3, sum(a4) a4, sum(a5) a5, " +
        "              sum(a6) a6, sum(a7) a7, sum(a8) a8, sum(a9) a9, sum(a10) a10, " +
        "              sum(a1+a2+a3+a4+a5+a6+a7+a8+a9+a10) asum " +
        "        from (select surveyid, usn, queid, usransw, " +
        "                     decode(instr(nvl(usransw,0), 1), 0,0,1) a1, decode(instr(nvl(usransw,0), 2), 0,0,1) a2, " +
        "                     decode(instr(nvl(usransw,0), 3), 0,0,1) a3, decode(instr(nvl(usransw,0), 4), 0,0,1) a4, " +
        "                     decode(instr(nvl(usransw,0), 5), 0,0,1) a5, decode(instr(nvl(usransw,0), 6), 0,0,1) a6, " +
        "                     decode(instr(nvl(usransw,0), 7), 0,0,1) a7, decode(instr(nvl(usransw,0), 8), 0,0,1) a8, " +
        "                     decode(instr(nvl(usransw,0), 9), 0,0,1) a9, decode(instr(nvl(usransw,0), 10), 0,0,1) a10 " +
        "                from wlc_srv_rst_item ) " +
        "                group by queid) bb " +
        " where aa.queid = bb.que(+) " +
        " order by aa.seq asc ";
        
        return oDb.selector(Map.class, sql, param);
    }
    
    public static List getAnsList(MdbDriver oDb, Map param) throws MdbException {
        final String sql = "select a.surveyid, a.usn, b.queid, b.usransw " +
        "   from wlc_srv_rst a, wlc_srv_rst_item b " +
        "   where a.surveyid = b.surveyid " +
        "   and a.usn = b.usn " +
        "   and a.surveyid = :surveyid ";

        return oDb.selector(Map.class, sql, param);
    }
    
}
