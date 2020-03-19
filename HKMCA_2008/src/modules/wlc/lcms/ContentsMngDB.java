/*
 * Created on 2006. 6. 22.
 *
 * Copyright (c) 2006 UBQ  All rights reserved.
 */
package modules.wlc.lcms;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


import maf.MafUtil;
import maf.beans.NavigatorInfo;
import maf.mdb.CommonDB;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;
import oracle.jdbc.OracleTypes;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ContentsMngDB extends CommonDB {
    
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
        final String sql = "select*  " +
        " from (select a.*, b.sjt_nm, b.crs_cd " +
        "         FROM wlc_inx_lst a, bas_sjt b " +
        "         where a.sjt_cd = b.sjt_cd" +
        "      ) x ";
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
        final  String sql = "select count(*) " +
        " from (select a.*, b.sjt_nm, b.crs_cd " +
        "         FROM wlc_inx_lst a, bas_sjt b " +
        "         where a.sjt_cd = b.sjt_cd" +
        "       ) x " + sWhere;
        return oDb.selectOneInt(sql, param);
    }

    /**
     * 하나의 레코드 읽어 오기
     *
     * @param oDb
     * @param param
     * @return Map
     * @throws MdbException
     */
    public static Map getOne(MdbDriver oDb, Map param) throws MdbException {
        String sql = "SELECT a.*, b.sjt_nm "   +
        "   FROM wlc_inx_lst a, bas_sjt b " +
        " where a.sjt_cd = b.sjt_cd " +
        " and a.sjt_cd = :sjt_cd" +
        " and htmcode = :htmcode";
        return (Map) oDb.selectorOne(Map.class, sql, param);
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
    public static int getHtmcode(MdbDriver oDb, Map param) throws MdbException {
        final  String sql = "select nvl(max(htmcode),0)+1 as htmcode" +
        " from wlc_inx_lst" +
        " where sjt_cd = :sjt_cd";
        return oDb.selectOneInt(sql, param);
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

        String sql = "INSERT INTO wlc_inx_lst (sjt_cd, htmcode, daecode, daename, cenname, soname, " +
        		" totpage, cnt_width, cnt_height, cnt_type, htmurl, lrntime) " +
        		" VALUES (:sjt_cd, :htmcode, :daecode, :daename, :cenname, :soname, " +
        		" :totpage, :cnt_width, :cnt_height, :cnt_type, :htmurl, :lrntime) ";
        
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
    public static int mergeOne(MdbDriver oDb,  Map param) throws MdbException  {
    	final String sql = "MERGE INTO wlc_inx_lst new " +
    	"      USING (SELECT :sjt_cd sjt_cd, :htmcode htmcode, :daecode daecode, :daename daename, :cenname cenname, " +
    	"                    :soname soname, :totpage totpage, :cnt_width cnt_width, :cnt_height cnt_height, :cnt_type cnt_type, " +
    	"                    :htmurl htmurl, :lrntime lrntime" +
    	"               FROM DUAL) src " +
    	"         ON (src.sjt_cd = new.sjt_cd and src.htmcode = new.htmcode) " +
    	"    WHEN MATCHED THEN " +
    	"       UPDATE SET " +
		" 			daecode = src.daecode, " +
		" 			daename = src.daename, " +
		" 			cenname = src.cenname, " +
		" 			soname = src.soname, " +
		" 			totpage = src.totpage, " +
		" 			cnt_width = src.cnt_width, " +
		" 			cnt_height = src.cnt_height, " +
		" 			cnt_type = src.cnt_type, " +
		" 			htmurl = src.htmurl, " +
		" 			lrntime = src.lrntime " +
    	"    WHEN NOT MATCHED THEN " +
    	"       INSERT (" +
    	"				sjt_cd, htmcode, daecode, daename, cenname, soname, " +
        " 				totpage, cnt_width, cnt_height, cnt_type, htmurl, lrntime" +
    	"       ) VALUES (" +
    	"                  	src.sjt_cd, src.htmcode, src.daecode, src.daename, src.cenname, src.soname, " +
        " 					src.totpage, src.cnt_width, src.cnt_height, src.cnt_type, src.htmurl, src.lrntime" +
    	"      ) ";
    	
    	return oDb.executeUpdate(sql, param);
    }

    /**
     * 하나의 레코드를 삭제 한다.
     *
     * @param oDb
     * @param param
     * @return int
     * @throws MdbException
     */
    public static int deleteOne(MdbDriver oDb, Map param) throws MdbException {
        String sql = "delete from wlc_inx_lst where sjt_cd = :sjt_cd and htmcode = :htmcode";
        return oDb.executeUpdate(sql, param);
    }

    /**
     * 하나의 레코드를 Update 한다.
     *
     * @param oDb
     * @param bean
     * @return int
     * @throws MdbException
     */
    public static int updateOne(MdbDriver oDb, Map param) throws MdbException {

        String sql = " UPDATE wlc_inx_lst SET " +
        		" daecode = :daecode, " +
        		" daename = :daename, " +
        		" cenname = :cenname, " +
        		" soname = :soname, " +
        		" totpage = :totpage, " +
        		" cnt_width = :cnt_width, " +
        		" cnt_height = :cnt_height, " +
        		" cnt_type = :cnt_type, " +
        		" htmurl = :htmurl, " +
        		" lrntime = :lrntime " +
        		" WHERE sjt_cd = :sjt_cd" +
        		" and htmcode = :htmcode";

        return oDb.executeUpdate(sql, param);
    }

    /**
     * 일반컨텐츠메타정보정보 삭제하기
     *
     * @param oDb
     * @param htmcode[] 일반컨텐츠메타정보코드배열
     * @return int
     * @exception DBHandlerException, DBConnectionFailedException
     */
    public static int delete(MdbDriver oDb, String[] htmcodes) throws MdbException  {

        int retValue = 0, result = 0;

        String sql = "{call sp_wra_inx_lst_delete (?, ?)}";
        CallableStatement   cstmt    = null;
        try {
            cstmt = oDb.prepareCall(sql);
            for(int i=0 ; i< htmcodes.length; i++) {
                cstmt.setLong(1,MafUtil.parseInt(htmcodes[i]));
                cstmt.registerOutParameter(2,OracleTypes.INTEGER);
                result = cstmt.executeUpdate();
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
            throw new MdbException(s);
        }finally{
            release(cstmt);
        }
        return retValue;
    }

    /**
     * 엑셀로 등록한 내용을 일괄 배치 insert 한다.
     *
     * @param oDb
     * @param fields[][]
     * @return int
     * @throws MdbException
     */
    public static int insertExcel(MdbDriver oDb, String[][] fields) throws MdbException {

        int retValue = 0;
        String sql = "{call sp_wra_inx_lst_batch (?,?,?,?,?, ?,?,?,?,?, ?,?)}";
        CallableStatement cstmt = null;

        try {
            cstmt = oDb.prepareCall(sql);
            int i, idx;
            if(fields != null) {
                for(int j = 0; j<fields.length; j++) {
                    if(fields[j] != null && fields[j].length == 12)
                    {
                        if(!"SJT_CD".equals(fields[j][0]))
                        {
                            i=0; idx =1;
                            cstmt.setString(idx++ , fields[j][i++]);  //sjt_cd
                            cstmt.setLong(idx++  , MafUtil.parseLong(fields[j][i++])); //htmcode
                            cstmt.setString(idx++ , fields[j][i++]); //daecode
                            cstmt.setString(idx++ , fields[j][i++]); //daename
                            cstmt.setString(idx++ , fields[j][i++]); //cenname
                            
                            cstmt.setString(idx++ , fields[j][i++]); //soname
                            cstmt.setString(idx++ , fields[j][i++]); //totpage
                            cstmt.setString(idx++ , fields[j][i++]); //lrntime
                            cstmt.setString(idx++ , fields[j][i++]); //htmurl
                            cstmt.setLong(idx++  , MafUtil.parseLong(fields[j][i++])); //width

                            cstmt.setLong(idx++  , MafUtil.parseLong(fields[j][i++])); //height
                            cstmt.setString(idx++ , fields[j][i++]); //active_yn
                            cstmt.addBatch();

                            retValue++;
                        }
                    }

                }
            }

            cstmt.executeBatch();
            oDb.commit();

        } catch (SQLException s) {
            retValue = 0;
            throw new MdbException(s);
        }finally{
            release(cstmt);
        }
        return retValue;
    }

    /**
     * 과목 목록 가져오기
     *
     * @param oDb
     * @return List
     * @throws MdbException
     */
    public static List getSubjectList(MdbDriver oDb) throws MdbException {
        final String sql = "SELECT sjt_cd, sjt_nm" +
        " FROM bas_sjt" +
        " where active_yn = 'Y' " +
        " ORDER BY sjt_nm";
        return oDb.selector(Map.class, sql);
    }

}