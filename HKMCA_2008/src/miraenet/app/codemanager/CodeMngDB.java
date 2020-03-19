/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package miraenet.app.codemanager;

import java.util.List;
import java.util.Map;

import maf.base.BaseDAO;
import maf.beans.NavigatorInfo;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

public class CodeMngDB extends BaseDAO {

	/**
	 * 목록 가져오기
	 * @param oDb
	 * @param param
	 * @param sql
	 * @return
	 * @throws MdbException
	 */
	public static void getList(MdbDriver oDb, NavigatorInfo navigator, Map param, String sWhere) throws MdbException {

		List list = null;

		final String sql = "select * from (select group_cd, group_name " +
		" from maf_code_mst) x ";

		String sOrder = navigator.getOrderSql();

		oDb.setPage(navigator.getCurrentPage(), navigator.getPageSize());
		oDb.setDebug(true);
		list = oDb.selector(Map.class, sql + sWhere + sOrder, param);
		navigator.setList(list);
		navigator.setTotalCount(getRecordCount(oDb, param, sWhere.toString()));
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
	private static long getRecordCount(MdbDriver oDb, Map param, String sWhere) throws MdbException {
		final String sql = "select count(*) from (select group_cd, group_name " +
		" from maf_code_mst) x " + sWhere;
		return oDb.selectOneInt(sql, param);
	}

	/**
	 * 해당 코드의 다국어 메시지를 넘겨 준다. 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public List getCodeLangList(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " SELECT :group_cd group_cd, :code_no code_no, i.code lang, c.code_name, i.allnames"   +
		"   FROM maf_lang_iso639_1 i, (SELECT *"   +
		"                                FROM maf_code_det_lang"   +
		"                               WHERE code_no = :code_no"   +
		"                                 AND group_cd = :group_cd) c"   +
		"  WHERE i.active_yn = 'Y'"   +
		"    AND i.code = c.lang(+)"  ;
		return oDb.selector(Map.class, sql, param);
	}
	
	/**
	 * 코드그룹 목록 가져오기
	 * 
	 * @param oDb
	 * @param param
	 * @param sql
	 * @return
	 * @throws MdbException
	 */
	public static List getGroupList(MdbDriver oDb) throws MdbException {
		final String sql = " SELECT group_cd, group_name, bigo, fixed_yn"   +
						   "   FROM maf_code_mst"   +
						   "  ORDER BY group_cd asc";
		return oDb.selector(Map.class, sql);
	}
	
	/**
	 * 코드 목록 가져오기
	 * 
	 * @param oDb
	 * @return
	 * @throws MdbException
	 */
	public List getCodeList(MdbDriver oDb) throws MdbException {
		final String sql = " SELECT group_cd,  code_no, code_name, bigo, seq"   +
						   "   FROM maf_code_det"   +
						   "  ORDER BY group_cd, seq, code_no"  ;
		return oDb.selector(Map.class, sql);
	}

	/**
	 * 하나의 코드그룹 읽어오기
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static Map getGroupOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "SELECT * " +
					 " FROM maf_code_mst" +
					 " WHERE group_cd = :group_cd";
		return (Map) oDb.selectorOne(Map.class, sql, param);
	}
	
	/**
	 * 하나의 코드 읽어오기
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static List getCode(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "SELECT *"   +
		   				   "  FROM maf_code_det"   +
		   				   "  WHERE group_cd = :group_cd"   +
		   				   "  order by seq" ;
		return oDb.selector(Map.class, sql, param);
	}

	/**
	 * 하나의 코드그룹을 Update 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static int UpdateGroupOne(MdbDriver oDb, Map param) throws MdbException {
		String sql = " UPDATE maf_code_mst" +
					 "  SET group_cd = :group_cd," +
					 "      group_name = :group_name " +
					 "  WHERE group_cd = :group_cd";

		return oDb.executeUpdate(sql, param);
	}

	/**
	 * 하나의 코드를 Update 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public int UpdateCodeOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " UPDATE maf_code_det"   +
					 "    SET group_cd = :group_cd,"   +
					 "        code_no = :code_no,"   +
					 "        code_name = :code_name,"   +
					 "        bigo = :bigo,"   +
					 "        seq = :seq"   +
					 "  WHERE group_cd = :ori_group_cd "   +
					 "    AND code_no = :ori_code_no"  ;
		
			return oDb.executeUpdate(sql, param);
	}
	
	/**
	 * code_group과 code_no 바 바뀌면 다국어 쪽도 바꾸어 준다. 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public int updateCodeLangCode(MdbDriver oDb, Map param) throws MdbException {
		final String sql = "update maf_code_det_lang " +
				"	set group_cd = :group_cd," +
				"		code_no = :code_no" +
				"	WHERE group_cd = :ori_group_cd" +
				"		and  code_no = :ori_code_no ";
		return oDb.executeUpdate(sql, param);
	}
	/**
	 * 하나의 코드를 Update 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public int UpdateCodeLangOne(MdbDriver oDb, Map param) throws MdbException {
		int result = 0;
		final String sql_update = " UPDATE maf_code_det_lang "   +
					 "    SET group_cd = :group_cd,"   +
					 "        code_no = :code_no,"   +
					 "        code_name = :code_name "   +
					 "  WHERE group_cd = :group_cd "   +
					 "    AND code_no = :code_no"   +
					 "    AND lang = :lang";
		
		final String sql_insert = " INSERT INTO maf_code_det_lang"   +
		"             (group_cd, code_no, lang, code_name"   +
		"             )"   +
		"      VALUES (:group_cd, :code_no, :lang, :code_name"   +
		"             )"  ;
		
		final String sql_cnt = "select count(*) cnt " +
				"	from maf_code_det_lang " +
				"	where group_cd = :group_cd" +
				"		and code_no = :code_no" +
				"		and lang = :lang ";
		
		int cnt = oDb.selectOneInt(sql_cnt, param);
		
		if(cnt > 0) {
			result = oDb.executeUpdate(sql_update, param);
		} else {
			result = oDb.executeUpdate(sql_insert, param);
		}
		
		return result;
	}
	
	/**
	 * 하나의 코드그룹을 Insert 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static int InsertGroupOne(MdbDriver oDb, Map param) throws MdbException {	
		String sql = "INSERT INTO maf_code_mst (group_cd, group_name)" +
					 " VALUES (:group_cd, :group_name)";
		return oDb.executeUpdate(sql, param);
	}
	
	/**
	 * 하나의 코드를 Insert 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static int InsertCodeOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " INSERT INTO maf_code_det"   +
					 "             (group_cd,  code_no, code_name, seq)"   +
					 "      VALUES (:group_cd, :code_no, :code_name, :seq)"  ;
		return oDb.executeUpdate(sql, param);
	}
	
	/**
	 * 하나의 코드그룹을 Delete 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static int DeleteGroupOne (MdbDriver oDb, Map param) throws MdbException {	
		String sql = " DELETE FROM maf_code_mst"   +
					 "       WHERE group_cd = :group_cd"  ;

		return oDb.executeUpdate(sql, param);
	}
	
	/**
	 * 하나의 코드를 Delete 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static int DeleteCode (MdbDriver oDb, Map param) throws MdbException {

		String sql = " DELETE FROM maf_code_det" +
					 "       WHERE group_cd = :group_cd";

		return oDb.executeUpdate(sql, param);
	}
	
	/**
	 * 하나의 코드를 Delete 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static int DeleteCodeOne (MdbDriver oDb, Map param) throws MdbException {

		String sql = " DELETE FROM maf_code_det"   +
					 "       WHERE group_cd = :group_cd "   +
					 "         AND code_no = :code_no"  ;
		return oDb.executeUpdate(sql, param);
	}
}