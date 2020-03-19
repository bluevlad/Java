/*
 * Created on 2005. 12. 5.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.mboard.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

public class AdminDataDB {

	/**
	 * 모든 게시판 과 최근 7일간의 글의 수를 가져온다.
	 * 강좌의 경우 개설된 것
	 * @param oDb
	 * @return
	 * @throws MdbException
	 */
	public  static List getBoardList(MdbDriver oDb, String dur) throws MdbException{
//		String sql = "  SELECT   mm.grp, mm.club_id, mm.leccode, mm.bid, mm.subject,"   +
//		"           NVL (mc.r_cnt, 0) ncr, lm.lecname, cm.c_name,"   +
//		" 		  sum(NVL (mc.r_cnt, 0)) over(partition by mm.grp, mm.club_id, mm.leccode) s_ncr"   +
//		"      FROM mbbs_meta mm,"   +
//		"           club_master cm,"   +
//		"           (SELECT i1.leccode, i1.lecname"   +
//		"              FROM lec_mst i1"   +
//		"             WHERE i1.lecstat = 'O') lm,"   +
//		"           (SELECT   md.bid, COUNT (*) r_cnt"   +
//		"                FROM mbbs_data md"   +
//		"               WHERE md.c_date > (SYSDATE - :dur)"   +
//		"            GROUP BY bid) mc"   +
//		"     WHERE mm.bid = mc.bid(+)"   +
//		"       AND mm.leccode = lm.leccode(+)"   +
//		"       AND mm.club_id = cm.c_id(+)"   +
//		"  ORDER BY mm.grp, mm.club_id, mm.leccode, mm.subject"  ;
		String sql = " SELECT   mm.grp, mm.club_id, mm.leccode, mm.bid, mm.subject,"   +
		"          NVL (mc.r_cnt, 0) ncr, cm.c_name,"   +
		"          SUM (NVL (mc.r_cnt, 0)) OVER (PARTITION BY mm.grp, mm.club_id, mm.leccode)"   +
		"                                                                         s_ncr"   +
		"     FROM mbbs_meta mm, club_master cm, (SELECT   md.bid, COUNT (*) r_cnt"   +
		"                                             FROM mbbs_data md"   +
		"                                            WHERE md.c_date > (SYSDATE - :dur)"   +
		"                                         GROUP BY bid) mc"   +
		"    WHERE mm.bid = mc.bid(+) AND mm.club_id = cm.c_id(+)"   +
		" ORDER BY mm.grp, mm.club_id, mm.leccode, mm.subject"  ;
		Map param = new HashMap();
		param.put("dur", dur);
		return oDb.selector(Map.class, sql, param);
	}
}

