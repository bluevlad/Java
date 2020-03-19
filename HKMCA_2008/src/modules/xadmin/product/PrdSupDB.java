package modules.xadmin.product;

import java.util.List;
import java.util.Map;

import maf.base.BaseDAO;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;


public class PrdSupDB extends BaseDAO {
    

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
    public static List getList(MdbDriver oDb, Map param) throws MdbException {

        final String sql = "select *  " +
        "  from (select b.prd_cd, b.reg_dt, b.reg_price  " +
        "          from prd_mst a, prd_sup b " +
        "          where a.prd_cd = b.prd_cd  " +
        "          and a.prd_cd = :prd_cd" +
        "  ) x ";

		return oDb.selector(Map.class, sql, param);
    }

    /**
     * 하나의 레코드를 Insert 한다.
     *
     * @param oDb
     * @param bean
     * @return
     * @throws MdbException
     */
    public static int insert(MdbDriver oDb, Map param) throws MdbException {
        final String sql = " INSERT INTO prd_sup " +
        "    (prd_cd,  reg_dt, reg_price) " +
        "  VALUES"   +
        "    (:prd_cd, :reg_dt, :reg_price)"  ;
        
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

}