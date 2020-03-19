package modules.xadmin.survey;

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
        final String sql = " select * from (" +
        " select a.queid, a.queowner, a.quecount, a.quetype,"   +
        "        a.active_yn, a.upt_dt, b.*"   +
        "  from survey_bnk a, (select queid l_queid, lang, quetitle,"   +
        "                                queviw1, queviw2, queviw3, queviw4, queviw5,"   +
        "                                queviw6, queviw7, queviw8, queviw9, queviw10"   +
        "                     from survey_bnk_lang"   +
        "                     where lang = :lang ) b"   +
        "  where a.queid = b.l_queid(+) " +
        "  ) x"  ;

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
        final String sql = " select count(*) from (" +
        " select a.queid, a.queowner, a.quecount, a.quetype,"   +
        "        a.active_yn, a.upt_dt, b.*"   +
        "  from survey_bnk a, (select queid l_queid, lang, quetitle,"   +
        "                                queviw1, queviw2, queviw3, queviw4, queviw5,"   +
        "                                queviw6, queviw7, queviw8, queviw9, queviw10"   +
        "                     from survey_bnk_lang"   +
        "                     where lang = :lang ) b"   +
        "  where a.queid = b.l_queid(+) " +
        "  ) x" + sWhere;

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
        String sql = " SELECT a.queowner, a.quecount, a.quetype, "   +
        "        b.*"   +
        "  FROM survey_bnk a, (select * from survey_bnk_lang where lang = :lang) b"   +
        "  WHERE a.queid = :queid"   +
        "  and a.queid = b.queid(+)"  ;
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
    	final String sql = "MERGE INTO SURVEY_BNK new " +
    	"      USING (SELECT :queid queid, :queowner queowner, :quetitle quetitle, " +
    	"                    :queviw1 queviw1, :queviw2 queviw2, :queviw3 queviw3, :queviw4 queviw4, :queviw5 queviw5, " +
    	"                    :queviw6 queviw6, :queviw7 queviw7, :queviw8 queviw8, :queviw9 queviw9, :queviw10 queviw10, " +
    	"                    :quecount quecount, :quetype quetype, NVL(:active_yn, 'Y') active_yn, :upt_id upt_id" +
    	"               FROM DUAL) src " +
    	"         ON (src.queid = new.queid) " +
    	"    WHEN MATCHED THEN " +
    	"       UPDATE SET" +
        "        quecount = src.quecount,"   +
        "        quetype = src.quetype,"   +
        "        active_yn = src.active_yn,"   +
        "        upt_dt = sysdate ,"   +
        "        upt_id = src.upt_id "   +
    	"    WHEN NOT MATCHED THEN " +
    	"       INSERT (" +
    	"                queid, queowner,  quetitle, " +
    	"                queviw1, queviw2, queviw3, queviw4, queviw5, queviw6, queviw7, queviw8, queviw9, queviw10, " +
    	"                quecount, quetype, active_yn, upt_dt, upt_id " +
    	"       ) VALUES (" +
    	"                  to_char(sysdate,'DD') || lpad( seq_exm_bnk_id.NEXTVAL,10,0), src.queowner, src.quetitle, " +
    	"                  src.queviw1, src.queviw2, src.queviw3, src.queviw4, src.queviw5, src.queviw6, src.queviw7, src.queviw8, src.queviw9, src.queviw10, " +
    	"                  src.quecount, src.quetype, src.active_yn, sysdate, src.upt_id" +
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
    public static int mergeLangOne(MdbDriver oDb,  Map param) throws MdbException  {
    	final String sql = "MERGE INTO SURVEY_BNK_LANG new " +
    	"      USING (SELECT :queid queid, :lang lang, :quetitle quetitle, " +
    	"                    :queviw1 queviw1, :queviw2 queviw2, :queviw3 queviw3, :queviw4 queviw4, :queviw5 queviw5, " +
    	"                    :queviw6 queviw6, :queviw7 queviw7, :queviw8 queviw8, :queviw9 queviw9, :queviw10 queviw10, " +
    	"                    NVL(:active_yn, 'Y') active_yn " +
    	"               FROM DUAL) src " +
    	"         ON (src.queid = new.queid and src.lang = new.lang) " +
    	"    WHEN MATCHED THEN " +
    	"       UPDATE SET" +
        "        quetitle = src.quetitle,"   +
        "        queviw1 = src.queviw1,"   +
        "        queviw2 = src.queviw2,"   +
        "        queviw3 = src.queviw3,"   +
        "        queviw4 = src.queviw4,"   +
        "        queviw5 = src.queviw5,"   +
        "        queviw6 = src.queviw6,"   +
        "        queviw7 = src.queviw7,"   +
        "        queviw8 = src.queviw8,"   +
        "        queviw9 = src.queviw9,"   +
        "        queviw10 = src.queviw10,"   +
        "        active_yn = src.active_yn "   +
    	"    WHEN NOT MATCHED THEN " +
    	"       INSERT (" +
    	"                queid, lang, quetitle, " +
    	"                queviw1, queviw2, queviw3, queviw4, queviw5, queviw6, queviw7, queviw8, queviw9, queviw10, " +
    	"                active_yn" +
    	"       ) VALUES (" +
    	"                  to_char(sysdate,'DD') || lpad( seq_exm_bnk_id.CURRVAL,10,0), src.lang, src.quetitle, " +
    	"                  src.queviw1, src.queviw2, src.queviw3, src.queviw4, src.queviw5, src.queviw6, src.queviw7, src.queviw8, src.queviw9, src.queviw10, " +
    	"                  src.active_yn" +
    	"      ) ";
    	
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
        String sql_lang = "DELETE FROM SURVEY_BNK_LANG WHERE  queid = :queid";
        String sql = "DELETE FROM SURVEY_BNK WHERE  queid = :queid";
        int r = oDb.executeUpdate(sql_lang, param);
        r += oDb.executeUpdate(sql, param);
        return r;
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

        String sql = "DELETE FROM wlc_survey_bnk WHERE quecode = :quecode ";
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
}
