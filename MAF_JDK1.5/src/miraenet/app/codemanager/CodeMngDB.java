/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.codemanager;

import java.util.List;
import java.util.Map;

import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CodeMngDB {
	private static CodeMngDB instance;

	private Log logger = LogFactory.getLog(this.getClass());

	private CodeMngDB() {
	}

	public static synchronized CodeMngDB getInstance() {
		if (instance == null)
			instance = new CodeMngDB();
		return instance;
	}	
	
	/**
	 * 사이트 목록 가져오기
	 * 
	 * @param oDb
	 * @return
	 * @throws MdbException
	 */
	public List getSiteList(MdbDriver oDb) throws MdbException {
		final String sql = " SELECT site, title, short_desc, layout, url"   +
						   "   FROM maf_site"   +
						   "  ORDER BY site";
		return oDb.selector(Map.class, sql);
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
	public List getGroupList(MdbDriver oDb) throws MdbException {
		final String sql = " SELECT group_cd, group_name, bigo, fixed_yn"   +
						   "   FROM maf_code_mst"   +
						   "  ORDER BY group_name";
		return oDb.selector(Map.class, sql);
	}
	
	/**
	 * 코드 목록 가져오기
	 * 
	 * @param oDb
	 * @return
	 * @throws MdbException
	 */
	public List getCodeList(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " SELECT group_cd, site, code_no, code_name, bigo, seq"   +
						   "   FROM maf_code_det"   +
						   "  WHERE site = :site"   +
						   "  ORDER BY group_cd, site, seq, code_no"  ;
		return oDb.selector(Map.class, sql, param);
	}

	/**
	 * 하나의 코드그룹 읽어오기
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public Map getGroupOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " SELECT group_cd, group_name, bigo, fixed_yn"   +
					 "   FROM maf_code_mst"   +
					 "  WHERE group_cd = :group_cd" ;
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
	public Map getCodeOne(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " SELECT group_cd, site, code_no, code_name, bigo, seq"   +
		   				   "   FROM maf_code_det"   +
		   				   "  WHERE group_cd = :group_cd"   +
		   				   "    AND site = :site"   +
		   				   "    AND code_no = :code_no" ;
		return (Map) oDb.selectorOne(Map.class, sql, param);
	}

	/**
	 * 하나의 코드그룹을 Update 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public int UpdateGroupOne(MdbDriver oDb, Map param) throws MdbException {
		int result = 0;
		String sql = " UPDATE maf_code_mst"   +
					 "    SET group_cd = :group_cd,"   +
					 "        group_name = :group_name,"   +
					 "        bigo = :bigo,"   +
					 "        fixed_yn = :fixed_yn"   +
					 "  WHERE group_cd = :ori_group_cd"  ;
		try {
			result = oDb.executeUpdate(sql, param);
		} catch (Throwable e) {
			logger.error(e.getMessage());
		}
		return result;
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
		int result = 0;
		String sql = " UPDATE maf_code_det"   +
					 "    SET group_cd = :group_cd,"   +
					 "        site = :site,"   +
					 "        code_no = :code_no,"   +
					 "        code_name = :code_name,"   +
					 "        bigo = :bigo,"   +
					 "        seq = :seq"   +
					 "  WHERE group_cd = :ori_group_cd "   +
					 "    AND site = :ori_site "   +
					 "    AND code_no = :ori_code_no"  ;
		try {
			result = oDb.executeUpdate(sql, param); 
		} catch (Throwable e) {
			logger.error(e.getMessage());
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
	public int InsertGroupOne(MdbDriver oDb, Map param) throws MdbException {	
		int result = 0;
		String sql = " INSERT INTO maf_code_mst"   +
					 "             (group_cd, group_name, bigo, fixed_yn"   +
					 "             )"   +
					 "      VALUES (:group_cd, :group_name, :bigo, :fixed_yn"   +
					 "             )"  ;
		try {
			result = oDb.executeUpdate(sql, param);
		} catch (Throwable e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 하나의 코드를 Insert 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public int InsertCodeOne(MdbDriver oDb, Map param) throws MdbException {
		int result = 0;
		String sql = " INSERT INTO maf_code_det"   +
					 "             (group_cd, site, code_no, code_name, bigo, seq"   +
					 "             )"   +
					 "      VALUES (:group_cd, :site, :code_no, :code_name, :bigo, :seq"   +
					 "             )"  ;
		try {
			result = oDb.executeUpdate(sql, param);
		} catch (Throwable e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 하나의 코드그룹을 Delete 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public int DeleteGroupOne (MdbDriver oDb, Map param) throws MdbException {	
		int result = 0;
		String sql = " DELETE FROM maf_code_mst"   +
					 "       WHERE group_cd = :group_cd"  ;
		try {
			result = oDb.executeUpdate(sql, param);
		} catch (Throwable e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 하나의 코드를 Delete 한다.
	 * 
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public int DeleteCodeOne (MdbDriver oDb, Map param) throws MdbException {
		int result = 0;
		String sql = " DELETE FROM maf_code_det"   +
					 "       WHERE group_cd = :group_cd "   +
					 "         AND site = :site "   +
					 "         AND code_no = :code_no"  ;
		try {
			result = oDb.executeUpdate(sql, param);
		} catch (Throwable e) {
			logger.error(e.getMessage());
		}
		return result;
	}

}
