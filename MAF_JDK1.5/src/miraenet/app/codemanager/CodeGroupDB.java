/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.codemanager;

import java.util.List;
import java.util.Map;

import maf.MafUtil;
import maf.beans.NavigatorInfo;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CodeGroupDB {
	private static CodeGroupDB instance;

	private Log logger = LogFactory.getLog(this.getClass());

	private CodeGroupDB() {
	}

	public static synchronized CodeGroupDB getInstance() {
		if (instance == null)
			instance = new CodeGroupDB();
		return instance;
	}	
	
	/**
	 * 목록 가져오기
	 * 
	 * @param oDb
	 * @param param
	 * @param sql
	 * @return
	 * @throws MdbException
	 */
	public void getList(MdbDriver oDb, NavigatorInfo navigator, Map param,
			String[] searchFields) throws MdbException {

		List list = null;

		final String sql = " SELECT group_cd, group_name, bigo, fixed_yn"   +
						   "   FROM maf_code_mst"  ;
		StringBuffer sWhere = new StringBuffer();
		String t = null;

		for (int i = 0; i < searchFields.length; i++) {
			t = (String) param.get(searchFields[i]);
			if (!MafUtil.empty(t)) {
				if (sWhere.length() > 0) {
					sWhere.append(" and ");
				} else {
					sWhere.append(" where ");
				}
				//검색 조건 생성
				if(searchFields[i].indexOf("_start") > -1) {
					sWhere.append(searchFields[i].substring(0, searchFields[i].indexOf("_start")) + " >= :" + searchFields[i]);
				} else if (searchFields[i].indexOf("_end") > -1){
					sWhere.append(searchFields[i].substring(0, searchFields[i].indexOf("_end")) + " <= :" + searchFields[i]);
				} else if (searchFields[i].indexOf("_eql") > -1){
					sWhere.append(searchFields[i].substring(0, searchFields[i].indexOf("_eql")) + " = :" + searchFields[i]);
				} else if (searchFields[i].indexOf("_dup") > -1){
					sWhere.append(" instr(" + searchFields[i].substring(0, searchFields[i].indexOf("_dup")) + ", :" + searchFields[i] + ") > 0");					
				} else {
					sWhere.append(" instr(" + searchFields[i] + ", :" + searchFields[i] + ") > 0");
				}
			}
		}

		String sOrder = navigator.getOrderSql();

		oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
		oDb.setDebug(true);
		list = oDb.selector(Map.class, sql + sWhere + sOrder, param);
		navigator.setList(list);
		navigator.setTotalCount(this.getRecordCount(oDb, param, sWhere.toString()));
		navigator.sync();

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
	private long getRecordCount(MdbDriver oDb, Map param, String sWhere)
			throws MdbException {
		final String sql = "SELECT count(*) FROM maf_code_mst " + sWhere;
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
	public Map getOne(MdbDriver oDb, Map param) throws MdbException {
		String sql = " SELECT group_cd, group_name, bigo, fixed_yn"   +
					 "   FROM maf_code_mst"   +
					 "  WHERE group_cd = :group_cd" ;
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
	public int UpdateOne(MdbDriver oDb, Map param) throws MdbException {
		String sql = " UPDATE maf_code_mst"   +
					 "    SET group_cd = :group_cd,"   +
					 "        group_name = :group_name,"   +
					 "        bigo = :bigo,"   +
					 "        fixed_yn = :fixed_yn"   +
					 "  WHERE group_cd = :ori_group_cd"  ;
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
	public int InsertOne(MdbDriver oDb, Map param) throws MdbException {
		
		String sql = " INSERT INTO maf_code_mst"   +
					 "             (group_cd, group_name, bigo, fixed_yn"   +
					 "             )"   +
					 "      VALUES (:group_cd, :group_name, :bigo, :fixed_yn"   +
					 "             )"  ;

		return oDb.executeUpdate(sql, param);
	}
	
	/**
	 * 하나의 레코드를 Delete 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public int Delete (MdbDriver oDb, Map param) throws MdbException {
		
		String sql = " DELETE FROM maf_code_mst"   +
					 "       WHERE group_cd = :group_cd"  ;
		
		return oDb.executeUpdate(sql, param);
	}

}
