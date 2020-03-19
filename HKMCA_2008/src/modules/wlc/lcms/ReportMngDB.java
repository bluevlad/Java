package modules.wlc.lcms;

import java.util.List;
import java.util.Map;


import maf.beans.NavigatorInfo;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ReportMngDB {
	 private Log logger = LogFactory.getLog(this.getClass());

    /**
     * 목록 가져오기
     *
     * @param MdbDriver
     * @param NavigatorInfo
     * @param param
     * @param sWhere
     * @return void
     * @throws MdbException
     */
    public static void getList(MdbDriver oDb, NavigatorInfo navigator, Map param, String sWhere) throws MdbException {
        List list = null;
        final String sql = "select * from (select a.*, b.sjt_nm, b.crs_cd " +
        " from wlc_rpt_bnk a, bas_sjt b " +
        " where a.sjt_cd = b.sjt_cd " +
        " ) x ";

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
     * @return long
     * @throws MdbException
     */
    private static long getRecordCount(MdbDriver oDb, Map param, String sWhere) throws MdbException {
        final  String sql = "select count(*) from (select a.*, b.sjt_nm, b.crs_cd " +
        " from wlc_rpt_bnk a, bas_sjt b " +
        " where a.sjt_cd = b.sjt_cd " +
        " ) x " + sWhere;
        return oDb.selectOneInt(sql, param);
    }
    
    /**
     * 해당 과목의 컨텐츠 목록을 가져온다.
     * @param oDb
     * @param param
     * @return
     * @throws MdbException
     */
    public static Map getOne(MdbDriver oDb, Map param) throws MdbException {
        
        final String sql = " select * " +
        " from wlc_rpt_bnk"   +
        " where sjt_cd = :sjt_cd" +
        " and quecode = :quecode";

        return (Map) oDb.selectorOne(Map.class, sql, param);
    }

    /**
     * 해당 과목의 컨텐츠 목록을 가져온다.
     * @param oDb
     * @param param
     * @return
     * @throws MdbException
     */
    public static String maxQuecode(MdbDriver oDb, Map param) throws MdbException {
        
        final String sql = "SELECT max(quecode)" +
        " from wlc_rpt_bnk";

        return oDb.selectOne(sql, param);
    }

    /**
     * 하나의 레코르를 insert 한다.
     *
     * @param oDb
     * @param bean
     * @return int
     * @throws MdbException
     */
    public static int insertOne(MdbDriver oDb, Map param) throws MdbException {

        String sql = "INSERT INTO wlc_rpt_bnk (quecode, sjt_cd, quetitle, " +
        		" upt_dt, upt_id, quedesc) " +
        		" VALUES (GET.maxQuecode(:sjt_cd), :sjt_cd, :quetitle, " +
        		" sysdate, :upt_id, :quedesc) ";
        
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

	        final String sql = " UPDATE wlc_rpt_bnk SET "   +
	        "        quetitle = :quetitle,"   +
	        "        upt_dt = sysdate,"   +
	        "        upt_id = :upt_id,"   +
	        "        quedesc = :quedesc"   +
	        "  WHERE sjt_cd = :sjt_cd " +
	        " and quecode = :quecode"  ;

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
    	String sql = "DELETE FROM wlc_rpt_bnk" +
    	" WHERE sjt_cd = :sjt_cd" +
    	" and quecode = :quecode";
        return oDb.executeUpdate(sql, param);
    }

}