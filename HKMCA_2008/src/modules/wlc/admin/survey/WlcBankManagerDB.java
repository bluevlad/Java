package modules.wlc.admin.survey;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import maf.MafUtil;
import maf.base.BaseDAO;
import maf.beans.NavigatorInfo;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;


public class WlcBankManagerDB extends BaseDAO {
    

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
        " select a.queid, substr(a.sjt_cd,0,3) crs_cd, a.sjt_cd, a.quecount, a.quetype,"   +
        "        a.active_yn, a.upt_dt, "   +
        "      b.*"   +
        "  from wlc_srv_bnk a, (select queid l_queid, lang, quetitle,"   +
        "                                queviw1, queviw2, queviw3, queviw4, queviw5,"   +
        "                                queviw6, queviw7, queviw8, queviw9, queviw10"   +
        "                     from wlc_srv_bnk_lang"   +
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
        " select a.queid, substr(a.sjt_cd,0,3) crs_cd, a.sjt_cd, a.quecount, a.quetype,"   +
        "        a.active_yn, a.upt_dt, "   +
        "      b.*"   +
        "  from wlc_srv_bnk a, (select queid l_queid, lang, quetitle,"   +
        "                                queviw1, queviw2, queviw3, queviw4, queviw5,"   +
        "                                queviw6, queviw7, queviw8, queviw9, queviw10"   +
        "                     from wlc_srv_bnk_lang"   +
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
        String sql = " SELECT a.sjt_cd, a.quecount, a.quetype, "   +
        "        b.*"   +
        "  FROM wlc_srv_bnk a, (select * from wlc_srv_bnk_lang where lang = :lang) b"   +
        "  WHERE a.queid = :queid"   +
        "  and a.queid = b.queid(+)"  ;
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
        final String sql = " INSERT INTO wlc_srv_bnk " +
        "    (queid, sjt_cd,  quetitle, " +
        "     queviw1, queviw2, queviw3, queviw4, queviw5, queviw6, queviw7, queviw8, queviw9, queviw10, " +
        "     quecount, quetype, active_yn) " +
        "  VALUES"   +
        "    (:sjt_cd || lpad( seq_exm_bnk_id.NEXTVAL,10,0), :sjt_cd,  :quetitle, " +
        "     :queviw1, :queviw2, :queviw3, :queviw4, :queviw5, :queviw6, :queviw7, :queviw8, :queviw9, :queviw10, " +
        "     :quecount, :quetype, :active_yn)"  ;
        
        return oDb.executeUpdate(sql, param);
    }

    public static int InsertLanOne(MdbDriver oDb, Map param) throws MdbException {
        final String sql = " INSERT INTO wlc_srv_bnk_lang "   +
        "    (queid, lang, quetitle, " +
        "     queviw1, queviw2, queviw3, queviw4, queviw5, queviw6, queviw7, queviw8, queviw9, queviw10) " +
        "  VALUES"   +
        "    (:sjt_cd || lpad( seq_exm_bnk_id.CURRVAL,10,0), :lang, :quetitle, " +
        "     :queviw1, :queviw2, :queviw3, :queviw4, :queviw5, :queviw6, :queviw7, :queviw8, :queviw9, :queviw10)"  ;
        
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
        String sql_lang = "DELETE FROM wlc_srv_bnk_lang WHERE  queid = :queid";
        String sql = "DELETE FROM wlc_srv_bnk WHERE  queid = :queid";
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

            final String sql = " UPDATE wlc_srv_bnk"   +
            "    SET "   +
            "        quecount = :quecount,"   +
            "        quetype = :quetype,"   +
            "        active_yn = :active_yn,"   +
            "        upt_dt = sysdate ,"   +
            "        upt_id = :upt_id "   +
            "  WHERE queid = :queid"  ;
        return oDb.executeUpdate(sql, param);
    }

    public static int updateLangOne(MdbDriver oDb, Map param) throws MdbException {

        final String sql = " UPDATE wlc_srv_bnk_lang"   +
        "    SET "   +
        "        quetitle = :quetitle,"   +
        "        queviw1 = :queviw1,"   +
        "        queviw2 = :queviw2,"   +
        "        queviw3 = :queviw3,"   +
        "        queviw4 = :queviw4,"   +
        "        queviw5 = :queviw5,"   +
        "        queviw6 = :queviw6,"   +
        "        queviw7 = :queviw7,"   +
        "        queviw8 = :queviw8,"   +
        "        queviw9 = :queviw9,"   +
        "        queviw10 = :queviw10"   +
        "  WHERE queid = :queid " +
        "  and lang = :lang"  ;
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
        }finally{
            if (pstmt != null) {try {pstmt.close();} catch (Throwable ignore) {} }
            pstmt = null;
        }
        return retValue;
    }
}
