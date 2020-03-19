/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.common.codeFind;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CodeFindDB {
	public Log logger = LogFactory.getLog(this.getClass());
	
	/**
	 * sql 가져오기
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static String getSQL(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " SELECT datasql"   +
						   "   FROM maf_code_find"   +
						   "  WHERE ID = :ID"  ;
		
		return (String)oDb.selectOne(sql, param);
	}
	
	/**
	 * field 항목 가져오기
	 * @param oDb
	 * @param param
	 * @return
	 * @throws MdbException
	 */
	public static List getFieldList(MdbDriver oDb, Map param) throws MdbException {
		final String sql = " SELECT *"   +
		                   "   FROM maf_code_find_st"   +
		                   "  WHERE ID = :ID"  +
		                   "  ORDER BY gubun, seq " ;
		
		return oDb.selector(Map.class, sql, param);
	}

	/**
	 * 목록 가져오기
	 * @param oDb
	 * @param param
	 * @param sql
	 * @return
	 * @throws MdbException
	 */
	public static List getList(MdbDriver oDb, Map param, String sql) throws MdbException {
		
		String[] p = {" instr(UPPER(" + param.get("schtype") + "), UPPER(:keyword)) > 0" };

		MessageFormat formatter = new MessageFormat("");

		formatter.applyPattern(sql);
		sql = formatter.format(p);
		oDb.setLimit(100);
		return oDb.selector(Map.class, sql, param);
		
	}
	
	/**
	 * 목록가져오기
	 * 
	 * @param oDb
	 * @param sql
	 * @return
	 * @throws MdbException
	 */
	public static List getTreeList(MdbDriver oDb, String sql) throws MdbException {

		return oDb.selector(Map.class, sql);
		
	}
	
}
