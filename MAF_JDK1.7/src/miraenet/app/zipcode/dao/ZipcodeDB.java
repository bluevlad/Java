/*
 * Created on 2005. 7. 12.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.zipcode.dao;

import java.util.List;
import java.util.Map;

import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

/**
 * 우편번호 검색용 DAO 
 **/
public class ZipcodeDB {
	public static List getZipcodeList(MdbDriver oDb, Map param, int page, int page_size) throws MdbException {

		final String sql = " SELECT zipcode, addr1, bunji "   +
			"     FROM zipcode_kor zip"   +
			"    WHERE seq IN (SELECT seq"   +
			"                    FROM zipcode_search"   +
			"                   WHERE searchtext LIKE :keyword)"   +
			" ORDER BY addr1"  ;
		oDb.setPage(page, page_size);
		List list = oDb.selector(Map.class, sql, param);

		return list;
	}

	public static int getZipcodeCount(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " SELECT COUNT (DISTINCT seq) cnt"   +
			"   FROM zipcode_search"   +
			"  WHERE searchtext LIKE :keyword"  ;
		int cnt = oDb.selectOneInt(sql, param);
		return cnt;
	}
}
