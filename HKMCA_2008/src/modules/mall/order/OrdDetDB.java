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


public class OrdDetDB extends BaseDAO {

	/**
	 * 목록 가져오기
	 * @param oDb
	 * @param param
	 * @param sql
	 * @param isAll
	 * @return
	 * @throws MdbException
	 */
    public static List getList(MdbDriver oDb, Map param) throws MdbException {
	        
    	final String sql = "select om.ord_cd, od.ord_det_no, od.ord_det_sta, od.psell_price, od.qty, od.cpn_use, " +
    	"       pm.prd_nm, pm.org_price, pm.prd_type " +
    	"  from order_mst om, ORDER_DET od, prd_mst pm, prd_sell ps   " +
    	"  where om.ord_cd = od.ord_cd  " +
    	"  and pm.prd_cd = ps.prd_cd  " +
    	"  and od.psell_cd = ps.psell_cd   " +
    	"  and om.ord_cd = :ord_cd ";
	        
    	return oDb.selector(Map.class, sql, param);
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
		final String sql = " INSERT INTO ORDER_DET "   +
		"    (ord_cd, ord_det_no, psell_cd, psell_price, ord_det_sta, snd_dt, qty, cpn_use, coupon_no) " +
		"  VALUES"   +
		"    (:ord_cd, :ord_det_no, :psell_cd, :psell_price, :ord_det_sta, :snd_dt, :qty, :cpn_use, :coupon_no)" ;
		
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
		final String sql = " update ORDER_DET set " +
			" ord_det_sta = :ord_det_sta," +
			" snd_dt = :snd_dt"   +
            " where ord_cd = :ord_cd" +
            " and ord_det_no = :ord_det_no ";
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
		final String sql = " delete from ORDER_DET  " +
	            "where ord_cd = :ord_cd" +
	            " and ord_det_no = :ord_det_no ";
		return oDb.executeUpdate(sql, param);
	}
	
}
		
		
		