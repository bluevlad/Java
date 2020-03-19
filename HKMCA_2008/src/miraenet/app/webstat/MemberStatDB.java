/*
 * Created on 2006. 09. 27
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package miraenet.app.webstat;

import java.util.List;
import java.util.Map;

import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

public class MemberStatDB {

	/**
	 * 목록 가져오기
	 * @param oDb
	 * @param param
	 * @param sql
	 * @param isAll
	 * @return
	 * @throws MdbException
	 */
	public static List getSex(MdbDriver oDb, Map param) throws MdbException {
		
		String sql = "select aa.sex, count(aa.sex) cnt " +
		" from (select decode(mod(substr(pin,8,1),2),0,'여자','남자') sex " +
		" from maf_user) aa " +
		" group by aa.sex ";

		return oDb.selector(Map.class, sql, param);
	}
	
	public static List getAge(MdbDriver oDb, Map param) throws MdbException {
		
		String sql = "select aa.age*10||'대' age, count(aa.age) cnt" +
		" from (select FLOOR (decode(a.age,100,99.9,a.age)/10) age " +
		"        from (select (TO_CHAR(sysdate, 'YYYY') - " +
		"                       TO_NUMBER(case " +
		"                                   when substr(pin,8,1) in ('1','2') then '19' " +
		"                                   when substr(pin,8,1) in ('3','4') then '20' " +
		"                                 end || substr(pin,1,2)) -  " +
		"                                        case when substr(pin,3,4) >= substr(TO_CHAR(sysdate,'YYYYMMDD'),5,4)     " +
		"                                          then 1 else 0 " +
		"                                        end) as age " +
		"        from maf_user ) a) aa, " +
		"          (SELECT ROWNUM sc_range " +
		"             FROM all_objects " +
		"            WHERE ROWNUM <= 20) s " +
		"    WHERE s.sc_range = aa.age(+) " +
		"    and age is not null " +
		" GROUP BY aa.age " +
		" order by aa.age ";

		return oDb.selector(Map.class, sql, param);
	}
	
	public static List getAddr(MdbDriver oDb, Map param) throws MdbException {
		
		String sql = "select b.sido, count(b.sido) cnt " +
		" from maf_user a, zipcode_kr b " +
		" where replace(zip,'-','') = b.zipcode " +
		" group by sido " +
		" order by sido ";

		return oDb.selector(Map.class, sql, param);
	}
	
	public static List getGeoip(MdbDriver oDb, Map param) throws MdbException {
		
		String sql = "select ROW_NUMBER () OVER (ORDER BY count(a.countrycode) desc) RANK, a.countrycode, countryname, count(a.countrycode) cnt   " +
		"  from maf_log_connect a, MAF_GEOIP_COUNTRY b " +
		"  where a.countrycode = b.countrycode " +
		"  group by a.countrycode, b.countryname ";

		return oDb.selector(Map.class, sql, param);
	}

}