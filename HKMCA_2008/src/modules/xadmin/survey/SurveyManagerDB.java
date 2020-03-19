/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.xadmin.survey;

import java.util.List;
import java.util.Map;

import maf.base.BaseDAO;
import maf.beans.NavigatorInfo;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;


/**
 * 시험관리용 DAO
 * @author bluevlad
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
        final String sql = " select * from ( SELECT surveyid, surveyowner, surveytitle, surveydesc,"   +
        "    survey_sdat, survey_edat,"   +
        "    m.upt_dt,  m.upt_id, m.active_yn"   +
        "  FROM survey_mst m, survey_set s, survey_set_lang n"   +
        "  WHERE m.setid = s.setid"   +
        "  and s.setid = n.setid"   +
        "  and n.lang = :lang )"  ;
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
        final String sql = "select count(*) from ( SELECT surveyid, surveyowner, surveytitle, surveydesc,"   +
        "    to_char(survey_sdat, 'YYYY-MM-DD') as survey_sdat,"   +
        "    to_char(survey_edat, 'YYYY-MM-DD') as survey_edat,"   +
        "    m.upt_dt, m.upt_id, m.active_yn"   +
        "  FROM survey_mst m, survey_set s, survey_set_lang n"   +
        "  WHERE m.setid = s.setid"   +
        "  and s.setid = n.setid"   +
        "  and n.lang = :lang )"  + sWhere;
        return oDb.selectOneInt(sql, param);
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
        final String sql =" SELECT em.surveyid, em.surveyowner, em.surveytitle, em.surveydesc,"   +
        " em.survey_sdat,em.survey_edat,"   +
        " em.reg_id, em.reg_dt, em.upt_dt, em.upt_id, em.setid, em.active_yn, em.survey_target"   +
        " FROM survey_mst em, survey_set s"   +
        " WHERE em.surveyid = :surveyid"   +
        " AND em.setid = s.setid"  ;
        return (Map) oDb.selectorOne(Map.class, sql, param);
    }

    /**
     * 설문대상 가져오기 
     */
    public static List getTargetChkList(MdbDriver oDb, Map param) throws MdbException {
        final String sql = "select role_id, role_name, " +
        "        decode(INSTR((select survey_target from survey_mst where surveyid = :surveyid), a.role_id), 0, '',  a.role_id) chk " +
        "  from maf_role a ";
        return oDb.selector(Map.class, sql, param);
    }
    
    /**
     * 설문대상 가져오기 
     */
    public static List getTargetList(MdbDriver oDb, Map param) throws MdbException {
        final String sql = "select role_id, role_name " +
        " from maf_role ";

        return oDb.selector(Map.class, sql, param);
    }

	/**
	 * 하나의 레코드를  insert or update 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
    public static int mergeOne(MdbDriver oDb,  Map param) throws MdbException  {
    	final String sql = "MERGE INTO SURVEY_MST new " +
    	"      USING (SELECT :surveyid surveyid, :surveyowner surveyowner, :surveytitle surveytitle, :surveydesc surveydesc, " +
    	"                    :survey_sdat survey_sdat, :survey_edat survey_edat, :usn usn, " +
    	"                    :setid setid, :survey_target survey_target, :active_yn active_yn " +
    	"               FROM DUAL) src " +
    	"         ON (src.surveyid = new.surveyid) " +
    	"    WHEN MATCHED THEN " +
    	"       UPDATE SET " +
    	"       surveytitle = src.surveytitle, " +
    	"       surveydesc = src.surveydesc, " +
    	"       survey_sdat = src.survey_sdat, " +
    	"       survey_edat = src.survey_edat, " +
    	"       active_yn = src.active_yn, " +
    	"       survey_target = src.survey_target, " +
    	"       upt_dt = sysdate, " +
    	"       upt_id = src.usn " +
    	"    WHEN NOT MATCHED THEN " +
    	"       INSERT (" +
    	"                surveyid, surveyowner, surveytitle, surveydesc, survey_sdat, survey_edat, " +
        " 				 reg_id, reg_dt, upt_dt, upt_id, setid, survey_target" +
    	"       ) VALUES (" +
    	"                  TO_CHAR(sysdate, 'YYYY-MM-DD') || '-' || SEQ_EXM_MST_ID.nextval, src.usn, src.surveytitle, src.surveydesc, src.survey_sdat, src.survey_edat, " +
        " 				   src.usn, sysdate, sysdate, src.usn, src.setid, src.survey_target" +
    	"      ) ";
    	
    	return oDb.executeUpdate(sql, param);
    }

    /**
     * 결과 목록 돌려 주기 
     */
    public static List getRstList(MdbDriver oDb, Map param) throws MdbException {
        final String sql = "select * from (SELECT DECODE (i.queid, NULL, 'N', 'Y') chk, i.qseq, b.quecount, b.quetype, e.*"   +
        "                 FROM survey_set_item i, survey_bnk b, survey_bnk_lang e, survey_mst c, survey_set_lang d"   +
        "                 WHERE b.queid = i.queid"   +
        "                 and b.queid = e.queid"   +
        "                 AND b.active_yn = 'Y'"   +
        "                 and i.setid = c.setid"   +
        "                 and c.surveyid = :surveyid"   +
        "                 and i.setid = d.setid"   +
        "                 and d.lang = :lang"   +
        "                 and e.lang = :lang) aa,"   +
        "               (select queid que,"   +
        "                           sum(a1) a1, sum(a2) a2, sum(a3) a3, sum(a4) a4, sum(a5) a5,"   +
        "                       sum(a6) a6, sum(a7) a7, sum(a8) a8, sum(a9) a9, sum(a10) a10,"   +
        "                       sum(a1+a2+a3+a4+a5+a6+a7+a8+a9+a10) asum"   +
        "                 from (select surveyid, usn, qseq, queid, usransw,"   +
        "                              decode(instr(nvl(usransw,0), 1), 0,0,1) a1, decode(instr(nvl(usransw,0), 2), 0,0,1) a2,"   +
        "                              decode(instr(nvl(usransw,0), 3), 0,0,1) a3, decode(instr(nvl(usransw,0), 4), 0,0,1) a4,"   +
        "                              decode(instr(nvl(usransw,0), 5), 0,0,1) a5, decode(instr(nvl(usransw,0), 6), 0,0,1) a6,"   +
        "                              decode(instr(nvl(usransw,0), 7), 0,0,1) a7, decode(instr(nvl(usransw,0), 8), 0,0,1) a8,"   +
        "                              decode(instr(nvl(usransw,0), 9), 0,0,1) a9, decode(instr(nvl(usransw,0), 10), 0,0,1) a10"   +
        "                         from survey_rst_item )"   +
        "                         group by queid) bb"   +
        "    where aa.queid = bb.que(+)"   +
        "    order by aa.qseq asc"  ;
        return oDb.selector(Map.class, sql, param);
    }
    
    public static List getAnsList(MdbDriver oDb, Map param) throws MdbException {
        final String sql = "  select a.usn, a.surveyid, a.lang, "   +
        "         b.qseq, b.queid, b.usransw,"   +
        "       c.nm "   +
        "   from survey_rst a, survey_rst_item b, v_maf_user c"   +
        "   where a.surveyid = b.surveyid"   +
        "   and a.usn = b.usn"   +
        "   and b.usn = c.usn"   +
        "   and a.surveyid = :surveyid"  ;
        return oDb.selector(Map.class, sql, param);
    }
    
}