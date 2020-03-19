/*
 * Created on 2005. 12. 5.
 *
 * Copyright (c) 2004 bluevlad  All rights reserved.
 */
package modules.community.mboard.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;


public class AdminDataDB {

	/**
	 * ��� �Խ��� �� �ֱ� 7�ϰ��� ���� ���� �����´�.
	 * ������ ��� ������ ��
	 * @param oDb
	 * @return
	 * @throws MdbException
	 */
	public  static List getBoardList(MdbDriver oDb, String dur) throws MdbException{
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

