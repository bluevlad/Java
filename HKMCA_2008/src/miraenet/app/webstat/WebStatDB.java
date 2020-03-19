/*
 * Created on 2006. 09. 27
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package miraenet.app.webstat;

import java.util.List;
import java.util.Map;


import maf.mdb.Mdb;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class WebStatDB {

	public static List getLogInDaily(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " SELECT d.ymd2 category, nvl(r.cnt,0) cnt, d.dow"   +
			"   FROM dual_ymd d,"   +
			"        (SELECT   TO_CHAR (TRUNC (m.logindt), 'YYYY-MM-DD') CATEGORY,"   +
			"                  COUNT (*) cnt"   +
			"             FROM maf_log_user m"   +
			"            WHERE m.yyyy = :yyyy AND m.mm = :mm"   +
			"         GROUP BY TRUNC (m.logindt)) r"   +
			"  WHERE d.ymd2 = r.CATEGORY(+) AND d.ymd LIKE (:yyyy || :mm || '%')"   +
			"  order by d.ymd"  ;
		

			
//			oDb.setDebug(true);
			return oDb.selector(Map.class, sql, param);

	}
	
	/**
	 * 일자별 접속자수 session 기준 
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static List getDailyconnect(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " SELECT d.ymd2 category, nvl(r.cnt,0) cnt, d.dow"   +
			"   FROM dual_ymd d,"   +
			"        (SELECT   TO_CHAR (TRUNC (m.logindt), 'YYYY-MM-DD') CATEGORY,"   +
			"                  COUNT (*) cnt"   +
			"             FROM maf_log_CONNECT m"   +
			"            WHERE m.yyyy = :yyyy AND m.mm = :mm"   +
			"         GROUP BY TRUNC (m.logindt)) r"   +
			"  WHERE d.ymd2 = r.CATEGORY(+) AND d.ymd LIKE (:yyyy || :mm || '%')"   +
			"  order by d.ymd"  ;
		

			
//			oDb.setDebug(true);
			return oDb.selector(Map.class, sql, param);

	}
}
