package modules.xadmin.etest;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


import maf.MafUtil;
import maf.beans.NavigatorInfo;
import maf.mdb.CommonDB;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BankManagerDB extends CommonDB {
	
    private static Log logger = LogFactory.getLog(BankManagerDB.class);

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
        " from (select * from exm_bnk) x ";

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
        " from (select * from exm_bnk) x " + sWhere;

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
        "   FROM exm_bnk"   +
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
        "   FROM exm_bnk_lang b"   +
        "  WHERE queid = :queid and lang = :lang "  ;
        return (Map) oDb.selectorOne(Map.class, sql, param);
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
    	final String sql = "MERGE INTO EXM_BNK new " +
    	"      USING (SELECT :queid queid, :quetitle quetitle, :quetext quetext, :queimag queimag," +
    	"                    :queviw1 queviw1, :queviw2 queviw2, :queviw3 queviw3, :queviw4 queviw4, :queviw5 queviw5, " +
    	"                    :queviw6 queviw6, :queviw7 queviw7, :queviw8 queviw8, :queviw9 queviw9, :queviw10 queviw10, " +
    	"                    :quecount quecount, :quedesc quedesc, :quedescimg quedescimg, :queansw queansw, :quescore quescore, " +
    	"                    :quetype quetype, :quelevel quelevel, NVL(:active_yn, 'Y') active_yn, :usn usn" +
    	"               FROM DUAL) src " +
    	"         ON (src.queid = new.queid) " +
    	"    WHEN MATCHED THEN " +
    	"       UPDATE SET" +
        " 			quetitle = src.quetitle," +
        " 			quetext = src.quetext," +
        " 			queimag = src.queimag," +
        " 			queviw1 = src.queviw1," +
        " 			queviw2 = src.queviw2," +
        " 			queviw3 = src.queviw3," +
        " 			queviw4 = src.queviw4," +
        " 			queviw5 = src.queviw5," +
        " 			queviw6 = src.queviw6," +
        " 			queviw7 = src.queviw7," +
        " 			queviw8 = src.queviw8," +
        " 			queviw9 = src.queviw9," +
        " 			queviw10 = src.queviw10," +
        " 			quecount = src.quecount," +
        " 			quedesc = src.quedesc," +
        " 			queansw = src.queansw," +
        " 			quescore = src.quescore," +
        " 			quetype = src.quetype," +
        " 			quelevel = src.quelevel," +
        " 			active_yn = src.active_yn," +
        " 			upt_dt = sysdate," +
        " 			upt_id = src.usn," +
        " 			queowner = src.usn" +
    	"    WHEN NOT MATCHED THEN " +
    	"       INSERT (" +
    	"               queid, queowner,  quetitle, quetext, queimag, " +
		"				queviw1, queviw2, queviw3, queviw4, queviw5, queviw6, queviw7, queviw8, queviw9, queviw10, " +
		"				quecount, quedesc, quedescimg, queansw, quescore, quetype, quelevel, active_yn, " +
		"    			reg_dt, reg_id, upt_dt, upt_id " +
    	"       ) VALUES (" +
    	"                  to_char(sysdate,'DD') || lpad( seq_exm_bnk_id.NEXTVAL,10,0), src.usn, src.quetitle, src.quetext, src.queimag, " +
    	"                  src.queviw1, src.queviw2, src.queviw3, src.queviw4, src.queviw5, src.queviw6, src.queviw7, src.queviw8, src.queviw9, src.queviw10, " +
    	"                  src.quecount, src.quedesc, src.quedescimg, src.queansw, src.quescore, src.quetype, src.quelevel, src.active_yn, " +
    	"                  sysdate, src.usn, sysdate, src.usn" +
    	"      ) ";
    	
    	return oDb.executeUpdate(sql, param);
    }

	/**
	 * 하나의 레코드를  insert or update 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
    public static int mergeLangOne(MdbDriver oDb, Map param) throws MdbException {

	        final String sql = " MERGE INTO exm_bnk_lang new"   +
	        "      USING (SELECT :usn usn, :queid queid, :lang lang, :quetitle quetitle, nvl(:quetext,'') quetext,"   +
	        "                    nvl(:queviw1,'') queviw1, nvl(:queviw2,'') queviw2, " +
            "                    nvl(:queviw3,'') queviw3, nvl(:queviw4,'') queviw4,"   +
	        "                    nvl(:queviw5,'') queviw5, nvl(:queviw6,'') queviw6, " +
            "                    nvl(:queviw7,'') queviw7, nvl(:queviw8,'') queviw8,"   +
	        "                    nvl(:queviw9,'') queviw9, nvl(:queviw10,'') queviw10, " +
            "                    nvl(:queansw,'') queansw"   +
	        "               FROM DUAL ) src"   +
	        "         ON (src.queid = new.queid AND src.lang = new.lang)"   +
	        "    WHEN MATCHED THEN"   +
	        "       UPDATE"   +
	        "          SET  new.quetitle = src.quetitle, new.quetext = src.quetext,"   +
            "              new.queviw1 = src.queviw1, " +
            "              new.queviw2 = src.queviw2,"   +
	        "              new.queviw3 = src.queviw3, " +
	        "              new.queviw4 = src.queviw4, " +
	        "              new.queviw5 = src.queviw5,"   +
	        "              new.queviw6 = src.queviw6, " +
	        "              new.queviw7 = src.queviw7, " +
	        "              new.queviw8 = src.queviw8,"   +
	        "              new.queviw9 = src.queviw9, " +
	        "              new.queviw10 = src.queviw10, " +
	        "              new.queansw = src.queansw, "   +
	        "              new.upt_id = src.usn, " +
	        "              new.upt_dt = SYSDATE"   +
	        "    WHEN NOT MATCHED THEN"   +
	        "       INSERT (" +
	        "				queid, lang, quetitle, quetext, " +
	        "               queviw1, queviw2, queviw3, queviw4, queviw5, queviw6, queviw7, queviw8, queviw9, queviw10, " +
            "               queansw, upt_dt, upt_id" +
            "		) VALUES (" +
            "				src.queid, src.lang, src.quetitle, src.quetext, " +
	        "               src.queviw1, src.queviw2, src.queviw3, src.queviw4, src.queviw5, src.queviw6, src.queviw7, src.queviw8, src.queviw9, src.queviw10, "   +
	        "               src.queansw, SYSDATE, src.usn" +
	        "		)";

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
    	String sql_lang = "DELETE FROM EXM_BNK_LANG WHERE  queid = :queid";
        String sql = "DELETE FROM EXM_BNK WHERE  queid = :queid";
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

	        final String sql = "UPDATE exm_bnk" +
	        " SET " +
	        " quetitle = :quetitle," +
	        " quetext = :quetext," +
	        " queimag = :queimag," +
	        " queviw1 = :queviw1," +
	        " queviw2 = :queviw2," +
	        " queviw3 = :queviw3," +
	        " queviw4 = :queviw4," +
	        " queviw5 = :queviw5," +
	        " queviw6 = :queviw6," +
	        " queviw7 = :queviw7," +
	        " queviw8 = :queviw8," +
	        " queviw9 = :queviw9," +
	        " queviw10 = :queviw10," +
	        " quecount = :quecount," +
	        " quedesc = :quedesc," +
	        " queansw = :queansw," +
	        " quescore = :quescore," +
	        " quetype = :quetype," +
	        " quelevel = :quelevel," +
	        " active_yn = :active_yn," +
	        " upt_dt = sysdate," +
	        " upt_id = :upt_id," +
	        " queowner = :upt_id" +
	        " WHERE queid = :queid"  ;

	        return oDb.executeUpdate(sql, param);
    }

    /**
     * 멀티 레코드 삭제하기
     *
     * @param quecodes[] 시험문제은행코드
     * @param sjt_cd 과목 코드
     * @return int 처리결과
     * @exception DBHandlerException, DBConnectionFailedException
     */
    public static int delete(MdbDriver oDb,  String[] quecodes) throws MdbException  {

        int retValue = 0, result = 0;

        String sql = "DELETE FROM wlc_exm_bnk WHERE quecode = :quecode ";
        PreparedStatement   pstmt    = null;
        
        try {
            pstmt = oDb.prepareCall(sql);
            for(int i=0 ; i< quecodes.length; i++) {

                pstmt.setLong(1,MafUtil.parseInt(quecodes[i]));
                result = pstmt.executeUpdate();
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
       	  	if (pstmt != null) {try {pstmt.close();} catch (Throwable ignore) {} }
            pstmt = null;
        }
        return retValue;
    }
    
    /**
     * 번역용 Source List 가져오기 
     * @param oDb
     * @param param
     * @return
     * @throws MdbException
     */
    public static List getTrasnSource(MdbDriver oDb, Map param) throws MdbException  {
    	final String sql = " SELECT   b.queid, b.cat_id, c.cat_name, :lang lang, b.active_yn, b.quetitle,"   +
    	"          b.quetext, b.queimag, b.queansw, b.queviw1, b.queviw2, b.queviw3,"   +
    	"          b.queviw4, b.queviw5, b.queviw6, b.queviw7, b.queviw8, b.queviw9,"   +
    	"          b.queviw10, b.quedesc, b.quedescimg"   +
    	"     FROM EXM_BNK b, EXM_CATEGORY c"   +
    	"    WHERE b.cat_id = c.cat_id AND c.cat_id = :cat_id AND c.active_yn = 'Y'"   +
    	" ORDER BY b.queid"  ;
    	return oDb.selector(Map.class, sql, param);
    }
}
