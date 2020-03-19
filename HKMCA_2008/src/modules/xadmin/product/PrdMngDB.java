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


public class PrdMngDB extends BaseDAO {

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

		final String sql = "SELECT x.*    " +
		"  FROM (SELECT a.*, " +
		"               prd.CateFullName(a.prd_cd) cat_nm " +
		"          FROM prd_mst a  " +
		"   ) x ";
		String sOrder = navigator.getOrderSql();

		if(!isAll) {
			oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
			navigator.setTotalCount(PrdMngDB.getRecordCount(oDb, param, sWhere.toString()));
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
		"  FROM (SELECT a.*, " +
		"               prd.CateFullName(a.prd_cd) cat_nm " +
		"          FROM prd_mst a  " +
		"  ) x" + sWhere;
		return oDb.selectOneInt(sql, param);
	}
	
	/**
	 * 하나의 과목정보를 읽어온다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static Map getOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "select * " 
				+ " from prd_mst " 
		        + " where prd_cd = :prd_cd ";
		return (Map) oDb.selectorOne(Map.class, sql, param);
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
		final String sql = " update prd_mst set " +
				" prd_nm = :prd_nm, " +
				" org_price = :org_price, " +
				" prd_type = :prd_type" +
            " where prd_cd = :prd_cd ";
		return oDb.executeUpdate(sql, param);
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
		final String sql = " INSERT INTO prd_mst "   +
		"    (prd_cd, prd_nm, org_price, prd_type) " +
		"  VALUES"   +
		"    (seq_product.nextval, :prd_nm, :org_price, :prd_type)"  ;
		
		return oDb.executeUpdate(sql, param);
	}

	/**
	 * 하나의 레코드를  merging 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
    public static int mergeOne(MdbDriver oDb,  Map param) throws MdbException  {
    	final String sql = "MERGE INTO prd_mst new " +
    	"      USING (SELECT :prd_cd prd_cd, :prd_nm prd_nm, :prd_price prd_price, :prd_desc prd_desc" +
    	"               FROM DUAL) src " +
    	"         ON (src.prd_cd = new.prd_cd) " +
    	"    WHEN MATCHED THEN " +
    	"       UPDATE SET" +
        "        prd_nm = src.prd_nm,"   +
        "        prd_price = src.prd_price,"   +
        "        prd_desc = src.prd_desc"   +
    	"    WHEN NOT MATCHED THEN " +
    	"       INSERT (" +
    	"                prd_cd, prd_nm, prd_price, prd_desc " +
    	"       ) VALUES (" +
    	"                  src.prd_cd, src.prd_nm, src.prd_price, src.prd_desc" +
    	"      ) ";
    	
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
		final String sql = " delete from prd_mst  " +
        " where prd_cd = :prd_cd ";
		return oDb.executeUpdate(sql, param);
	}
	
}