/*
 * Created on 2006. 10. 12
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.community.mboard.dao;

import java.util.List;
import java.util.Map;

import maf.base.BaseDAO;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

public class MainDB extends BaseDAO {
	
	/**
	 * 메인 이미지 리스트 
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static List getList(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "select *  " +
		"  from (select a.*, b.subject, b.fl_board_type, b.pgid, c.thumbnail, c.real_filename   " +
		"          from mbbs_data a, mbbs_meta b, mbbs_attach c, maf_menu d    " +
		"          where a.bid = b.bid     " +
		"          and a.bid = c.bid   " +
		"          and a.c_index = c.c_index   " +
		"          and b.pgid = d.pgid    " +
		"          and b.is_use = 'T'    " +
		"          and d.isuse = 'T'    " +
		"          and c.thumbnail = 'T'  " +
		"          and c.real_filename is not null  " +
		"          order by c_date desc)  " +
		"  where rownum <= 12 ";

		return  oDb.selector(Map.class, sql, param);
	}
}