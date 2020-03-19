/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2009 UBQ All rights reserved.
 */
package modules.mall.order;

import java.util.List;
import java.util.Map;

import maf.base.BaseDAO;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;


public class BasketDB extends BaseDAO {

	/**
	 * 목록 가져오기
	 * @param oDb
	 * @param param
	 * @param sql
	 * @return
	 * @throws MdbException
	 */
    public static List getList(MdbDriver oDb, Map param) throws MdbException {
        
    	final String sql = "select a.psell_cd, a.cnt, a.reg_dt, a.upt_dt,    " +
    	"       b.psell_price, c.prd_cd, c.prd_nm, c.prd_type,   " +
    	"       (select sell_nm from jmf_seller where sell_cd = b.sell_cd) sell_nm " +
    	"  from basket a, prd_sell b, prd_mst c " +
    	"  where a.psell_cd = b.psell_cd    " +
    	"  and b.prd_cd = c.prd_cd " +
    	"  and a.usn = :usn ";
	        
    	return oDb.selector(Map.class, sql, param);
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
				" from basket " +
				" where usn = :usn " +
				" and psell_cd = :psell_cd";
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
	public static int insertOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " INSERT INTO basket "   +
		"    (psell_cd, cnt, usn, reg_dt, upt_dt) " +
		"  VALUES"   +
		"    (:psell_cd, 1, :usn, sysdate, sysdate)";
		
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
		final String sql = " update basket set "   +
			" cnt = :cnt, " +
			" upt_dt = sysdate " + 
            " where usn = :usn" +
            " and psell_cd = :psell_cd ";
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
		final String sql = " delete from basket  " +
	            " where usn = :usn " +
	            " and psell_cd = :psell_cd";
		return oDb.executeUpdate(sql, param);
	}
	
	/**
	 * 장바구니를 비운다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static int delete(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " delete from basket  " +
	            " where usn = :usn ";
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
	public static int updateCoupon(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " update coupon_req set "   +
			" cp_use = 'Y', " +
			" use_dt = sysdate " + 
            " where usn = :usn" +
            " and coupon_no = :coupon_no";
		return oDb.executeUpdate(sql, param);
	}
	
}	