/*
 * @(#) MBoardUtil.java 2005-02-22
 * 
 * Copyright (c) 2005 (주)미래넷 All rights reserved.
 */

package miraenet.app.mboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maf.mdb.Mdb;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author goindole
 * @version 1.0
 * @modifydate 2005-02-22
 */
public class MBoardUtil {
	
	static private Log logger = LogFactory.getLog(MBoardUtil.class);
    /**
     * 해당그룹의 최근 게시물 n개를 가져온다.
     * 
     * @param grp
     * @param n
     */
    public static List getRecentBoardDatabyGrp( String grp, int n) {

    	final String sql = " SELECT /*+ INDEX_DESC( d IX_MBBS_DATA_03) */"
				+ "        d.bid, d.c_index, d.c_subject, d.c_date,"
				+ "        (SELECT m.subject"
				+ "           FROM mbbs_meta m"
				+ "          WHERE m.bid = d.bid) board_nm"
				+ "   FROM mbbs_data d"
				+ "  WHERE EXISTS (SELECT *"
				+ "                  FROM mbbs_meta m0"
				+ "                 WHERE is_use = 'T' AND m0.bid = d.bid AND m0.grp = :grp)"
				+ "    AND d.c_status = 'T'" + "    AND d.c_level = 1"
				+ "    AND ROWNUM <= :num ";    
    	
        Map param = new HashMap();
        param.put("grp", grp);
        param.put("num", n+"");

        return _getList(sql, param);
    	
    }

    /**
     * 해당 게시판의 신규(1일)게시물을 가져온다.
     * @param subject
     * @param day
     * @return
     */
    public static List getNewDailyBoardDatabyBid( String bid) {

		
		final String sql = " SELECT /*+ INDEX_DESC( d IX_MBBS_DATA_03) */ d.bid,  d.c_index, d.c_subject, d.c_date, m.SUBJECT board_nm,"
				+ " TO_char(d.c_date,'YYYY-MM-DD') as yyyymmdd, TO_DATE(sysdate,'yyyy-MM-dd') as sysd"
				+ "     FROM MBBS_DATA d, MBBS_META m"
				+ "  WHERE m.bid = :bid"
				+ "  	  	AND m.is_use = 'T'"
				+ "  	  	AND m.bid = d.bid"
				+ "   		AND d.c_status = 'T'"
				+ "   		AND d.c_level = 1	" 
				+ "     	AND d.c_date >= SYSDATE-1";
		Map param = new HashMap();
		param.put("bid",bid);

		return _getList(sql, param);
	}

    /**
	 * 해당강좌의 최근 Notice n개를 가져온다.
	 * 
	 * @param cor_cd
	 * @param n
	 * @return
	 * @throws MdbException
	 */
    public static List getRecentNoticeDatabyLeccode( String leccode, int n) {

        final String sql = "   SELECT /*+  INDEX_DESC( d IX_MBBS_DATA_03) */ d.bid,  d.c_index, d.c_subject, d.c_date, m.SUBJECT board_nm"   +
	        "     FROM MBBS_DATA d, MBBS_META m"   +
	        "  WHERE  m.bid   = :leccode "   +
	        "  	  	AND m.is_use = 'T'"   +
	        "  	  	AND d.bid = m.bid "   +
	        "   		AND d.c_status = 'T'"   +
	        "   		AND d.c_level = 1"   +
	        "    		AND ROWNUM <= :cnt"  ;

        Map param = new HashMap();
		param.put("leccode",leccode+":NOTICE");
		param.put("cnt",n+"");

		return _getList(sql, param);
    }
    
    /**
	 * 해당강좌의 최근 Notice n개를 가져온다.
	 * 
	 * @param cor_cd
	 * @param n
	 * @return
	 */
    public static List getRecentNoticeDatabyClub_id( String club_id, int n) {


        final String sql = " SELECT /*+ INDEX_DESC( d IX_MBBS_DATA_03) */"   +
		    "        d.bid, d.c_index, d.c_subject, d.c_date,"   +
			"        (SELECT m.subject"   +
			"           FROM mbbs_meta m"   +
			"          WHERE m.bid = d.bid) board_nm"   +
			"   FROM mbbs_data d"   +
			"  WHERE EXISTS (SELECT *"   +
			"                  FROM mbbs_meta m0"   +
			"                 WHERE is_use = 'T'"   +
			"                   AND m0.bid = d.bid"   +
			"                   AND m0.CLUB_ID = :club_id)"   +
			"    AND d.c_status = 'T'"   +
			"    AND d.c_level = 1"   +
			"    AND ROWNUM <= :cnt"  ;

        
        Map param = new HashMap();
		param.put("club_id",club_id);
		param.put("cnt",n+"");

		return _getList(sql, param);

    }
    
    /**
     * 해당게시판의 최근 게시물 n개를 가져온다.
     * 
     * @param oDb
     * @param bid
     * @param n
     * @return MbbsDataBean[] List
     * @throws MdbException
     */
    public static List getRecentBoardDatabyBID( String bid, int n){

	    final String sql = " SELECT /*+ INDEX_DESC( d IX_MBBS_DATA_03) */"   +
		    "        d.bid, d.c_index, d.c_subject, d.c_date,"   +
			"        (SELECT m.subject"   +
			"           FROM mbbs_meta m"   +
			"          WHERE m.bid = d.bid) board_nm"   +
			"   FROM mbbs_data d"   +
			"  WHERE EXISTS (SELECT *"   +
			"                  FROM mbbs_meta m0"   +
			"                 WHERE is_use = 'T'"   +
			"                   AND m0.bid = d.bid"   +
			"                   AND m0.bid = :bid)"   +
			"    AND d.c_status = 'T'"   +
			"    AND d.c_level = 1"   +
			"    AND ROWNUM <= :num"  ;
	    
        Map param = new HashMap();
		param.put("bid",bid);
		param.put("num",n+"");

		return _getList(sql, param);

	}
    
    /**
     * 해당강좌에 사용자가 쓴 글의 수를 리턴 한다.
     *    
     * @param oDb
     * @param leccode
     * @param usn
     * @return
     * @throws MdbException
     */
    public static int getCountByUserNLeccode(MdbDriver oDb, String leccode, String usn) 
    throws MdbException {
    	Map param = new HashMap();
    	final String sql = " SELECT COUNT (md.usn) cnt"   +
			"   FROM mbbs_meta mm, mbbs_data md"   +
			"  WHERE mm.leccode = :leccode "   +
			"   	   AND mm.bid = md.bid "   +
			" 	   AND md.usn = :usn"  ;
    	param.put("leccode",leccode);
    	param.put("usn", usn);
    	return oDb.selectOneInt(sql, param);
    }
    
    
    private static List _getList(String sql, Map param) {
    	List rList = null;
    	MdbDriver oDb = Mdb.getMdbDriver();
    	try {
    		rList =  oDb.selector(Map.class, sql,param);
    	} catch (Throwable e) {
    		logger.error(e.getMessage());
    	} finally {
    		if (oDb != null) {try {oDb.close();} catch (Exception e) {};}
    		oDb = null;
    	}
    	return rList;
    }
}