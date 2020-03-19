/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.survey;

import java.util.List;
import java.util.Map;

import maf.base.BaseDAO;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

/**
 * 시험관리용 DAO
 * @author bluevlad
 *
 */
public class SurveyDB extends BaseDAO {

    /**
     * 하나의 레코드 읽어 오기
     * 
     * @param oDb
     * @param param
     * @return
     * @throws MdbException
     */
    public static List getSurveyList(MdbDriver oDb, Map param) throws MdbException {
        final String sql = "SELECT em.surveyid, em.surveyowner, em.surveytitle, em.surveydesc,"   +
        "        em.survey_sdat, em.survey_edat,"   +
        "        em.reg_id, em.reg_dt, em.upt_dt, em.upt_id, em.setid, em.active_yn "   +
        "  FROM survey_mst em, survey_set s, survey_set_lang n"   +
        "  WHERE em.setid = s.setid"   +
        "  and s.setid= n.setid"   +
        "  and n.lang = :lang"   +
        "  order by em.survey_edat desc"  ;
        return oDb.selector(Map.class, sql, param);
    }

    public static List getMySurveyList(MdbDriver oDb, Map param) throws MdbException {
        final String sql = "select * from ("   +
        "  SELECT em.surveyid, em.surveyowner, em.surveytitle, em.surveydesc,"   +
        "         em.survey_sdat, em.survey_edat,"   +
        "         em.reg_id, em.reg_dt, em.upt_dt, em.upt_id, em.setid, em.active_yn,"   +
        "       (select count(usn) from survey_rst where surveyid = em.surveyid and usn = :usn) as is_ok"   +
        "   FROM survey_mst em, survey_set s, survey_set_lang n"   +
        "   WHERE em.setid = s.setid"   +
        "   and s.setid= n.setid"   +
        "   and n.lang = :lang"   +
        "   and em.active_yn = 'Y'"   +
        "   and sysdate between em.survey_sdat and em.survey_edat"   +
        "   order by em.survey_edat desc )"   +
        "   where is_ok < 1"  ;
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
    public static Map getSurvey(MdbDriver oDb, Map param) throws MdbException {
        final String sql = "  SELECT em.surveyid, em.surveyowner, em.surveytitle, em.surveydesc,"   +
        "         em.survey_sdat, em.survey_edat,"   +
        "         em.reg_id, em.upt_dt, em.upt_id, em.setid, em.active_yn ,"   +
        "         (select count(usn) from survey_rst where surveyid = em.surveyid and usn = :usn) as is_ok,"   +
        "       (select count(surveyid) from survey_mst where sysdate between survey_sdat and survey_edat and surveyid = :surveyid) as cnt"   +
        "   FROM survey_mst em, survey_set s, survey_set_lang n"   +
        "   WHERE em.setid = s.setid"   +
        "   and s.setid= n.setid"   +
        "   and n.lang = :lang"   +
        "   and surveyid = :surveyid"  ;
        return (Map) oDb.selectorOne(Map.class, sql, param);
    }

    /**
     * 하나의 레코드를 Insert 한다.
     * 
     * @param oDb
     * @param param
     * @return
     * @throws MdbException
     */
    public static int insertRst(MdbDriver oDb, Map param) throws MdbException {
        final String sql = " INSERT INTO SURVEY_RST "
                + "    (surveyid, usn, reg_id, reg_dt, lang) "
                + "  VALUES"
                + "    (:surveyid, :usn, :usn, sysdate, :lang)";
        return oDb.executeUpdate(sql, param);
    }

    public static int insertItem(MdbDriver oDb, Map param) throws MdbException {
        final String sql = " INSERT INTO SURVEY_RST_ITEM "
                + "    (surveyid, usn, qseq, queid, usransw) "
                + "  VALUES"
                + "    (:surveyid, :usn, :qseq, :queid, :usransw)";
        return oDb.executeUpdate(sql, param);
    }

}
