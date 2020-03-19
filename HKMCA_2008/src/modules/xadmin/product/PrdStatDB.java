/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.xadmin.product;

import java.util.List;
import java.util.Map;

import maf.base.BaseDAO;
import maf.beans.NavigatorInfo;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;


public class PrdStatDB extends BaseDAO {

	/**
	 * 목록 가져오기
	 * @param oDb
	 * @param param
	 * @param sql
	 * @param isAll
	 * @return
	 * @throws MdbException
	 */
	public static void getList(MdbDriver oDb, NavigatorInfo navigator, Map param, String sWhere, boolean isAll) throws MdbException {
		
		List list = null;

		final String sql = "select *     " +
		"  from (select a.*, c.userid, c.nm " +
		"          from order_mst a, (select ord_cd   " +
		"                               from order_det a, prd_sell b, jmf_user c  " +
		"                               where a.psell_cd = b.psell_cd  " +
		"                               and b.sell_cd = c.sell_cd  " +
		"                               and c.usn = :usn  " +
		"                               group by ord_cd) b, jmf_user c " +
		"  where a.ord_cd = b.ord_cd " +
		"  and a.usn = c.usn " +
		"  ) x ";

		String sOrder = navigator.getOrderSql();

		if(!isAll) {
			oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
			navigator.setTotalCount(PrdStatDB.getRecordCount(oDb, param, sWhere.toString()));
			navigator.sync();
		}
		
		list = oDb.selector(Map.class, sql + sWhere + sOrder, param);
		navigator.setList(list);
		
	}
	
	/**
	 * 레코드 카운트 가져오기
	 * 
	 * @param oDb
	 * @param param
	 * @param sWhere
	 * @return
	 * @throws MdbException
	 */
	private static long getRecordCount(MdbDriver oDb, Map param, String sWhere) throws MdbException {
		final String sql =  " SELECT count(*) "   +
		"  from (select a.*, c.userid, c.nm " +
		"          from order_mst a, (select ord_cd   " +
		"                               from order_det a, prd_sell b, jmf_user c  " +
		"                               where a.psell_cd = b.psell_cd  " +
		"                               and b.sell_cd = c.sell_cd  " +
		"                               and c.usn = :usn  " +
		"                               group by ord_cd) b, jmf_user c " +
		"  where a.ord_cd = b.ord_cd " +
		"  and a.usn = c.usn " +
		"  ) x" + sWhere;
		return oDb.selectOneInt(sql, param);
	}
	
	/**
	 * 하나의 레코드 읽어 오기
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static Map getOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "select a.*, b.userid, b.nm  " +
		"  from order_mst a, jmf_user b  " +
		"  where a.usn = b.usn " +
		" and ord_cd = :ord_cd";
		return (Map) oDb.selectorOne(Map.class, sql, param);
	}
	
	
    public static List getStatMoney(MdbDriver oDb, Map param) throws MdbException {
        
        final String sql = "select to_char(ord_dt, 'YYYY-MM-DD') title, count(om.ord_cd) cnt,sum(total_pay) total " +
        "  from order_mst om, (select ord_cd    " +
        "                        from order_det a, prd_sell b   " +
        "                        where a.psell_cd = b.psell_cd " +
        "                        and b.sell_cd = :sell_cd " +
        "                        group by ord_cd) b " +
        "  where om.ord_cd = b.ord_cd  " +
        "  group by to_char(ord_dt, 'YYYY-MM-DD') ";
        
        return oDb.selector(Map.class, sql, param);
    }
}