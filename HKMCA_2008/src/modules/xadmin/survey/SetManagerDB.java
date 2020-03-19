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

public class SetManagerDB extends BaseDAO {

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
        final String sql = " select * from (" +
        " SELECT a.* "   +
        "  FROM survey_set a, survey_set_lang b"   +
        "  where a.setid = b.setid"   +
        "  and b.lang = :lang ) x"  ;  ;
        String sOrder = navigator.getOrderSql();
        if (!isAll) {
            oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
            navigator.setTotalCount(SetManagerDB.getRecordCount(oDb, param, sWhere.toString()));
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
        final String sql = " select count(*) from (" +
        " SELECT a.* "   +
        "  FROM survey_set a, survey_set_lang b"   +
        "  where a.setid = b.setid"   +
        "  and b.lang = :lang ) x"   + sWhere;
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
        final String sql = " SELECT * "   +
        "   FROM survey_set "   +
        "  WHERE setid = :setid"  ;;
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
        final String sql = " update SURVEY_SET set "
                + " upt_dt = sysdate, "
                + " upt_id = :upt_id, "
                + " settitle = :settitle, "
                + " setdesc = :setdesc, "
                + " active_yn = :active_yn "
                + " where setid = :setid ";
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
        final String sql = " INSERT INTO SURVEY_SET "
                + "    (setid, setowner, settitle, setdesc, active_yn, "
                + "    reg_dt, upt_dt, reg_id, upt_id) "
                + "  VALUES"
                + "    (SEQ_EXM_SET_ID.NEXTVAL, :reg_id, :settitle, :setdesc, 'N', "
                + "    sysdate, sysdate, :reg_id, :reg_id)";
        return oDb.executeUpdate(sql, param);
    }
    
    public static int insertLangOne(MdbDriver oDb, Map param) throws MdbException {
        final String sql = " INSERT INTO SURVEY_SET_Lang "
                + "    (setid, lang) "
                + "  VALUES"
                + "    (SEQ_EXM_SET_ID.CURRVAL, :lang)";
        return oDb.executeUpdate(sql, param);
    }
    
    /**
     * 해당 문제 set의 item들을 모두 삭제 
     * @param oDb
     * @param param
     * @return
     * @throws MdbException
     */
    public static int deleteSetItems(MdbDriver oDb, Map param) throws MdbException {
        
        final String sql_delete  = " delete from survey_set_item "
                + "where  setid = :setid ";
        return oDb.executeUpdate(sql_delete, param);
    }
    
    /**
     * 해당 문제 set의 item들을 모두 삭제 
     * @param oDb
     * @param param
     * @return
     * @throws MdbException
     */
    public static int insertSetItems(MdbDriver oDb, Map param) throws MdbException {
        final String sql_insert = " INSERT INTO survey_set_item"   +
        "             (setid, queid, qseq, reg_id, reg_dt"   +
        "             )"   +
        "      VALUES (:setid, :queid, :qseq, :reg_id, SYSDATE"   +
        "             )"  ;
        return oDb.executeUpdate(sql_insert, param);
    
    }
    
     /**
     * 문제 Set에 속한 문제들을 가져 온다 . 
     */
     //@param setid
    public static List getAllItems(MdbDriver oDb, Map param) throws MdbException {
        final String sql = " select DECODE (i.queid, NULL, 'N', 'Y') chk, b.queid, i.qseq, "   +
        "        c.lang, c.quetitle"   +
        "  from (SELECT *"   +
        "         FROM survey_set_item"   +
        "         WHERE setid = :setid) i, survey_bnk b, survey_bnk_lang c"   +
        "  where b.queid = i.queid(+)"   +
        "  and b.active_yn = 'Y'"   +
        "  and b.queid = c.queid"   +
        "  and c.lang = :lang"   +
        "  order by chk desc, i.qseq"  ;
        return oDb.selector(Map.class, sql, param);
    }
    
    /**
     * 문제 Set에 속한 문제들을 가져 온다 . 
     */
     //@param setid
    public static List getSetItems(MdbDriver oDb, Map param) throws MdbException {
        final String sql = " SELECT DECODE (i.queid, NULL, 'N', 'Y') chk, i.qseq, "   +
        "        c.*"   +
        "  FROM survey_set_item i, survey_bnk b, survey_bnk_lang c"   +
        "  WHERE b.queid = i.queid"   +
        "  and b.queid = c.queid"   +
        "  AND b.active_yn = 'Y'"   +
        "  and setid = :setid"   +
        "  and c.lang = :lang"   +
        "  ORDER BY i.qseq asc"  ;
        return oDb.selector(Map.class, sql, param);
    }
    
    // @param surveyid
    public static List getSetSurvey(MdbDriver oDb, Map param) throws MdbException {
        final String sql = " SELECT DECODE (i.queid, NULL, 'N', 'Y') chk, i.qseq, b.*"   +
        "  FROM survey_set_item i, survey_bnk b, survey_mst c"   +
        "  WHERE b.queid = i.queid "   +
        "  AND b.active_yn = 'Y'"   +
        "  and i.setid = c.setid"   +
        "  and c.surveyid = :surveyid"   +
        "  ORDER BY i.qseq asc"  ;
        return oDb.selector(Map.class, sql, param);
    }

}