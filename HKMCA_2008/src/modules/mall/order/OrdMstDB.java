/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2009 UBQ All rights reserved.
 */
package modules.mall.order;

import java.util.List;
import java.util.Map;

import maf.base.BaseDAO;
import maf.beans.NavigatorInfo;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;


public class OrdMstDB extends BaseDAO {

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

		final String sql = "select x.*  " +
		"  from (select om.* " +
		"          from order_mst om" +
		"          where om.usn = :usn " +
		"  ) x ";
		
		String sOrder = navigator.getOrderSql();

		if(!isAll) {
			oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
			navigator.setTotalCount(OrdMstDB.getRecordCount(oDb, param, sWhere.toString()));
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
		final  String sql = "select count(*)  " +
		"  from (select om.* " +
		"          from order_mst om" +
		"          where om.usn = :usn " +
		"  ) x " + sWhere;
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
		final String sql = "select * " +
				" from ORDER_MST " +
				" where ord_cd = :ord_cd";
		return (Map) oDb.selectorOne(Map.class, sql, param);
	}
	
	
	/**
	 * 하나의 레코드를 Insert 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static int insert(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " INSERT INTO ORDER_MST "   +
		"    (ord_cd, usn, cpn_use, coupon_no, ord_sta, ord_dt, total_pay, discount) " +
		"  VALUES"   +
		"    (:ord_cd, :usn, :cpn_use, :coupon_no, :ora_sta, sysdate, :total_pay, :discount)";
		
		return oDb.executeUpdate(sql, param);
	}

	/**
	 * 하나의 레코드를 Update 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static int updateOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " update ORDER_MST set "   +
			" ord_sta = :ora_sta, " +
			" total_pay = :total_pay," +
			" discount = :discount " + 
            " where ord_cd = :ord_cd ";
		return oDb.executeUpdate(sql, param);
	}
	
	/**
	 * 하나의 레코드를 삭제 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static int deleteOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " delete from ORDER_MST  " +
	            "where  ord_cd = :ord_cd ";
		return oDb.executeUpdate(sql, param);
	}
	
	/**
	 * 주문코드생성
	 */
	public static String getOrdCd(MdbDriver oDb) throws MdbException {
		
		final String sql = "SELECT TO_CHAR(sysdate,'YYYYMMDD')||seq_order.nextval " +
		" FROM dual";
		
		return oDb.selectOne(sql);
	}
	
	/**
	 * 하나의 정보를 읽어온다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static Map getPrdOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "select a.psell_price, b.prd_price " +
		"  from prd_sell a, prd_mst b    " +
		"  where a.prd_cd = b.prd_cd   " +
		"  and psell_cd = :psell_cd ";
		return (Map) oDb.selectorOne(Map.class, sql, param);
	}
}	