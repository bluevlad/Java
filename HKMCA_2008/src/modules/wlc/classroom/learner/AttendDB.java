/*
 * Created on 2006. 10. 06.
 *
 * Copyright (c) 2006 UBQ All rights reserved.
 */
package modules.wlc.classroom.learner;

import java.util.List;
import java.util.Map;


import maf.mdb.CommonDB;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AttendDB extends CommonDB {
    private static Log logger = LogFactory.getLog(AttendDB.class);

    /**
     * 오프라인 강의 신청자 목록 가져오기
     * @param oDb
     * @param param [leccode, itm_id] 
     * @return
     * @throws MdbException
     */
    public static List getAttendList(MdbDriver oDb, Map param) throws MdbException {
    	final String sql = "SELECT * " +
    	"  FROM wlc_att_log " +
    	"  WHERE lec_cd = :lec_cd " +
    	"  AND usn = :std_usn ";
    	return oDb.selector(Map.class, sql, param);
    }
    
    /**
     * 출석 체크
     * @param oDb
     * @param param [lec_cd, usn, att_dat]
     * @return
     * @throws MdbException
     */
    public static int doAttend(MdbDriver oDb,  Map param) throws MdbException  {
    	final String sql = "MERGE INTO wlc_att_log dst  " +
    	"      USING (SELECT :lec_cd lec_cd, :usn usn, to_char(sysdate, 'YYYY-MM-DD') att_dat " +
    	"               FROM DUAL) src  " +
    	"         ON ( src.lec_cd = dst.lec_cd  " +
    	"             AND src.usn = dst.usn " +
    	"             and src.att_dat = dst.att_dat)  " +
    	"    WHEN MATCHED THEN " +
    	"       UPDATE  " +
    	"          SET dst.acs_e_tm = SYSDATE " +
    	"    WHEN NOT MATCHED THEN  " +
    	"       INSERT (lec_cd, usn, att_dat, acs_s_tm, acs_e_tm)  " +
    	"       VALUES (src.lec_cd, src.usn, src.att_dat, sysdate, SYSDATE) ";
    	
    	
    	return oDb.executeUpdate(sql, param);
    		
    }

}