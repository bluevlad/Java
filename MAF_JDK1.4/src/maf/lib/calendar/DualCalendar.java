/*
 * Created on 2006. 10. 20
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.lib.calendar;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maf.lib.calendar.beans.DualCalendarInfo;
import maf.mdb.Mdb;
import maf.mdb.drivers.MdbDriver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DualCalendar {
    private static DualCalendar instance = new DualCalendar();
    private Log logger = LogFactory.getLog(this.getClass());

    private DualCalendar() {
    }

    public static synchronized DualCalendar getInstance() {
        return instance;
    }
    
    /**
     * 두일자간에 포함된 날자들을 Return 
     * @param dt1 (yyyymmdd)
     * @param dt2 (yyyymmdd)
     * @return
     */
    public DualCalendarInfo[] getDayList(String dt1, String dt2) {
    	MdbDriver oDb = null;
    	List dList = null;
    	final String sql = " SELECT *"   +
	    	"   FROM dual_ymd"   +
	    	"  WHERE ymd BETWEEN :d1 AND :d2"  ;
    	try {
    		oDb = Mdb.getMdbDriver();
    		Map param = new HashMap();
    		param.put("d1", dt1);
    		param.put("d2", dt2);
    		dList = oDb.selector(DualCalendarInfo.class, sql, param);
//    		dList = 
    	} catch ( Exception e) {
    		logger.debug(e.getMessage());
    	} finally {

             if (oDb != null) try {oDb.close();} catch (Exception ex) {}
             oDb = null;
         }
    	if( dList == null) {
    		dList = Collections.EMPTY_LIST;
    	}
    	return (DualCalendarInfo[]) dList.toArray(new DualCalendarInfo[dList.size()]);
    }
    
    /**
     * 두일자간에 근무일수를 돌려 준다.
     * @param dt1
     * @param dt2
     * @return
     */
    public int getWorkDays(String dt1, String dt2) {
    	MdbDriver oDb = null;
    	int cnt = 0;
    	final String sql = " SELECT sum(work_day)"   +
	    	"   FROM dual_ymd"   +
	    	"  WHERE ymd BETWEEN :d1 AND :d2"  ;
    	try {
    		oDb = Mdb.getMdbDriver();
    		Map param = new HashMap();
    		param.put("d1", dt1);
    		param.put("d2", dt2);
    		cnt = oDb.selectOneInt(sql, param);
    	} catch ( Exception e) {
    		logger.debug(e.getMessage());
    	} finally {

             if (oDb != null) try {oDb.close();} catch (Exception ex) {}
             oDb = null;
         }
    	return cnt;
    }
    
    /**
     * 두일자간에 금융계 근무일수를 돌려 준다.
     * @param dt1
     * @param dt2
     * @return
     */
    public int getBankWorkDays(String dt1, String dt2) {
    	MdbDriver oDb = null;
    	int cnt = 0;
    	final String sql = " SELECT sum(bank_work)"   +
	    	"   FROM dual_ymd"   +
	    	"  WHERE ymd BETWEEN :d1 AND :d2"  ;
    	try {
    		oDb = Mdb.getMdbDriver();
    		Map param = new HashMap();
    		param.put("d1", dt1);
    		param.put("d2", dt2);
    		cnt = oDb.selectOneInt(sql, param);
    	} catch ( Exception e) {
    		logger.debug(e.getMessage());
    	} finally {

             if (oDb != null) try {oDb.close();} catch (Exception ex) {}
             oDb = null;
         }
    	return cnt;
    }
}

