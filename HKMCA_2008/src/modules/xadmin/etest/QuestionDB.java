package modules.xadmin.etest;

import java.util.List;
import java.util.Map;


import maf.beans.NavigatorInfo;
import maf.mdb.CommonDB;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class QuestionDB extends CommonDB {
	
    private static Log logger = LogFactory.getLog(QuestionDB.class);

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
        final String sql = "select *   " +
        "  from (select *  " +
        "         from exm_bnk a   " +
        "         where a.queid not in (select queid from exm_set_item where setid = :setid) " +
        "         and a.active_yn = 'Y' " +
        "  ) x ";

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
        "  from (select *  " +
        "         from exm_bnk a   " +
        "         where a.queid not in (select queid from exm_set_item where setid = :setid) " +
        "         and a.active_yn = 'Y' " +
        "  ) x " + sWhere;

        return oDb.selectOneInt(sql, param);
    }
    
    /**
     * 하나의 레코드를 Insert 한다.
     *
     * @param oDb
     * @param bean
     * @return
     * @throws MdbException
     */
    public static int insertOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " INSERT INTO exm_set_item " +
		" (setid, queid, qseq, reg_id, reg_dt) " +
		" VALUES (:setid, :queid, :qseq, :reg_id, SYSDATE)"  ;
		
		return oDb.executeUpdate(sql, param);
    }

}