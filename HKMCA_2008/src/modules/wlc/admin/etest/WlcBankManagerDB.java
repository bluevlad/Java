package modules.wlc.admin.etest;

import java.util.List;
import java.util.Map;


import maf.beans.NavigatorInfo;
import maf.mdb.CommonDB;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class WlcBankManagerDB extends CommonDB {
	
    private static Log logger = LogFactory.getLog(WlcBankManagerDB.class);

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
    public static void getList(MdbDriver oDb, NavigatorInfo navigator, Map param, String sWhere) throws MdbException {
        List list = null;
        final String sql = "select *  " +
        " from (select a.*, b.sjt_nm, b.crs_cd " +
        "        from wlc_exm_bnk a, bas_sjt b " +
        "        where a.sjt_cd = b.sjt_cd) x ";

        String sOrder = navigator.getOrderSql();

        oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
        list = oDb.selector(Map.class, sql + sWhere + sOrder, param);
        navigator.setList(list);
        navigator.setTotalCount(getRecordCount(oDb, param, sWhere.toString()));
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
    private static long getRecordCount(MdbDriver oDb, Map param, String sWhere) throws MdbException {
        final String sql = "select count(*) " +
        " from (select a.*, b.sjt_nm, b.crs_cd " +
        "        from wlc_exm_bnk a, bas_sjt b " +
        "        where a.sjt_cd = b.sjt_cd) x " + sWhere;

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
        String sql = " SELECT *"   +
        "   FROM wlc_exm_bnk"   +
        "  WHERE queid = :queid";
        return (Map) oDb.selectorOne(Map.class, sql, param);
    }
    
    /**
     * 번역 문제 하나 가져오기 
     * @param oDb
     * @param param
     * @return
     * @throws MdbException
     */
    public static Map getOneLang(MdbDriver oDb, Map param) throws MdbException {
        String sql = " SELECT b.*"   +
        "   FROM wlc_exm_bnk_lang b"   +
        "  WHERE queid = :queid and lang = :lang "  ;
        return (Map) oDb.selectorOne(Map.class, sql, param);
    }

    /**
     * 하나의 레코드를 Insert 한다.
     *
     * @param oDb
     * @param bean
     * @return
     * @throws MdbException
     */
    public static int InsertOne(MdbDriver oDb, Map param) throws MdbException {
    	final String sql = " INSERT INTO wlc_EXM_BNK "   +
		"    (queid, sjt_cd,  quetitle, quetext, queimag, " +
		"		queviw1, queviw2, queviw3, queviw4, queviw5, queviw6, queviw7, queviw8, queviw9, queviw10, " +
		"		quecount, quedesc, quedescimg, queansw, quescore, quetype, quelevel, active_yn, " +
		"    	reg_dt, reg_id, upt_dt, upt_id) " +
		"  VALUES"   +
		"    (to_char(sysdate,'DD') || lpad( seq_exm_bnk_id.NEXTVAL,10,0), :sjt_cd,  :quetitle, :quetext, :queimag, " +
		"		:queviw1, :queviw2, :queviw3, :queviw4, :queviw5, :queviw6, :queviw7, :queviw8, :queviw9, :queviw10, " +
		"		:quecount, :quedesc, :quedescimg, :queansw, :quescore, :quetype, :quelevel, :active_yn, " +
		"    sysdate, :usn, sysdate, :usn)"  ;
		
		return oDb.executeUpdate(sql, param);
    }

    public static int InsertLangOne(MdbDriver oDb, Map param) throws MdbException {
    	final String sql = " INSERT INTO wlc_exm_bnk_lang "   +
		"    (queid, lang, quetitle, quetext, " +
		"		queviw1, queviw2, queviw3, queviw4, queviw5, queviw6, queviw7, queviw8, queviw9, queviw10, " +
		"		queansw, upt_dt, upt_id) " +
		"  VALUES"   +
		"    (to_char(sysdate,'DD') || lpad( seq_exm_bnk_id.CURRVAL,10,0), :lang,  :quetitle, :quetext, " +
		"		:queviw1, :queviw2, :queviw3, :queviw4, :queviw5, :queviw6, :queviw7, :queviw8, :queviw9, :queviw10, " +
		"		:queansw, sysdate, :usn)"  ;
		
		return oDb.executeUpdate(sql, param);
    }

    /**
     * 하나의 레코드를 삭제 한다.
     *
     * @param oDb
     * @param param
     * @return
     * @throws MdbException
     */
    public static int deleteOne(MdbDriver oDb, Map param) throws MdbException {
    	String sql_lang = "DELETE FROM wlc_EXM_BNK_LANG WHERE  queid = :queid";
        String sql = "DELETE FROM wlc_EXM_BNK WHERE  queid = :queid";
        int r = oDb.executeUpdate(sql_lang, param);
        r += oDb.executeUpdate(sql, param);
        return r;
    }

    /**
     * 하나의 레코드를 Update 한다.
     *
     * @param oDb
     * @param bean
     * @return
     * @throws MdbException
     */
    public static int updateOne(MdbDriver oDb, Map param) throws MdbException {


	        final String sql = " UPDATE wlc_exm_bnk"   +
	        "    SET "   +
	        "        quetitle = :quetitle,"   +
	        "        quetext = :quetext,"   +
	        "        queimag = :queimag,"   +
	        "        queviw1 = :queviw1,"   +
	        "        queviw2 = :queviw2,"   +
	        "        queviw3 = :queviw3,"   +
	        "        queviw4 = :queviw4,"   +
	        "        queviw5 = :queviw5,"   +
	        "        queviw6 = :queviw6,"   +
	        "        queviw7 = :queviw7,"   +
	        "        queviw8 = :queviw8,"   +
	        "        queviw9 = :queviw9,"   +
	        "        queviw10 = :queviw10,"   +
	        "        quecount = :quecount,"   +
	        "        quedesc = :quedesc,"   +
	        "        quedescimg = :quedescimg,"   +
	        "        queansw = :queansw,"   +
	        "        quescore = :quescore,"   +
	        "        quetype = :quetype,"   +
	        "        quelevel = :quelevel,"   +
	        "        active_yn = :active_yn,"   +
	        "        upt_dt = sysdate ,"   +
	        "        upt_id = :upt_id,"   +
	        "		 sjt_cd = :sjt_cd" + 
	        "  WHERE queid = :queid"  ;

	        return oDb.executeUpdate(sql, param);
    }

    /**
     * 하나의 레코드를 Update 한다.
     *
     * @param oDb
     * @param bean
     * @return
     * @throws MdbException
     */
    public static int mergeLangOne(MdbDriver oDb, Map param) throws MdbException {


	        final String sql = " MERGE INTO wlc_exm_bnk_lang dst"   +
	        "      USING (SELECT :queid queid, :lang lang, :quetitle quetitle, nvl(:quetext,'') quetext,"   +
	        "                    nvl(:queviw1,'') queviw1, nvl(:queviw2,'') queviw2, " +
            "                    nvl(:queviw3,'') queviw3, nvl(:queviw4,'') queviw4,"   +
	        "                    nvl(:queviw5,'') queviw5, nvl(:queviw6,'') queviw6, " +
            "                    nvl(:queviw7,'') queviw7, nvl(:queviw8,'') queviw8,"   +
	        "                    nvl(:queviw9,'') queviw9, nvl(:queviw10,'') queviw10, " +
            "                    nvl(:quedesc,'') quedesc, nvl(:queansw,'') queansw,"   +
	        "                    :upt_id upt_id "   +
	        "               FROM DUAL ) src"   +
	        "         ON (    src.queid = dst.queid"   +
	        "             AND src.lang = dst.lang)"   +
	        "    WHEN MATCHED THEN"   +
	        "       UPDATE"   +
	        "          SET dst.quetitle = src.quetitle, dst.quetext = src.quetext,"   +
            "              dst.queviw1 = src.queviw1, dst.queviw2 = src.queviw2,"   +
	        "              dst.queviw3 = src.queviw3, dst.queviw4 = src.queviw4, dst.queviw5 = src.queviw5,"   +
	        "              dst.queviw6 = src.queviw6, dst.queviw7 = src.queviw7, dst.queviw8 = src.queviw8,"   +
	        "              dst.queviw9 = src.queviw9, dst.queviw10 = src.queviw10, " +
            "              dst.quedesc = src.quedesc, dst.queansw = src.queansw, "   +
	        "              dst.upt_id = src.upt_id, dst.upt_dt = SYSDATE"   +
	        "    WHEN NOT MATCHED THEN"   +
	        "       INSERT (queid, lang, quetitle, quetext, " +
	        "               queviw1, queviw2, queviw3, queviw4, queviw5,"   +
	        "               queviw6, queviw7, queviw8, queviw9, queviw10, " +
            "               quedesc,  queansw, upt_dt, upt_id)"   +
	        "       VALUES (src.queid, src.lang, src.quetitle, src.quetext, " +
	        "               src.queviw1, src.queviw2, src.queviw3, src.queviw4, src.queviw5, " +
	        "               src.queviw6, src.queviw7, src.queviw8, src.queviw9, src.queviw10, " +
	        "               src.quedesc, src.queansw, SYSDATE, src.upt_id)";

	        return oDb.executeUpdate(sql, param);
    }
    
}