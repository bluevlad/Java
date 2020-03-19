/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.sgen;

import java.util.List;
import java.util.Map;

import maf.beans.NavigatorInfo;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SgenDB {
	private static SgenDB instance;
	private Log logger = LogFactory.getLog(this.getClass());
	
	private SgenDB() {
	}

	public static synchronized SgenDB getInstance() {
		if (instance == null) instance = new SgenDB();
		return instance;
	}
	
	
	
	/**
	 * Table의 Column 정보를 돌려준다.
	 * 
	 * @param oDb
	 * @param owner
	 * @param table_name
	 * @return ColumnBean List
	 * @throws MdbException
	 */
	public void getColumnInfo(MdbDriver oDb, NavigatorInfo navigator,Map param)
			throws MdbException {
		
		
		List list = null;

		final String sql = " SELECT   cols.column_id AS ID, cols.column_name AS NAME, nullable,"
			+ "          data_type AS TYPE,"
			+ "          DECODE (data_type,"
			+ "                  'CHAR', char_col_decl_length,"
			+ "                  'VARCHAR', char_col_decl_length,"
			+ "                  'VARCHAR2', char_col_decl_length,"
			+ "                  'NCHAR', char_col_decl_length,"
			+ "                  'NVARCHAR', char_col_decl_length,"
			+ "                  'NVARCHAR2', char_col_decl_length,"
			+ "                  NULL"
			+ "                 ) nchar_length,"
			+ "          DECODE (data_type,"
			+ "                  'NUMBER', data_precision + data_scale,"
			+ "                  data_length"
			+ "                 ) LENGTH,"
			+ "          data_precision PRECISION, data_scale scale, data_length dlength,"
			+ "          data_default, SUBSTR (comments, 1, 240) comments, nvl(c1.POSITION,0) pk"
			+ "     FROM SYS.DBA_COL_COMMENTS coms,"
			+ "          SYS.DBA_TAB_COLUMNS cols,"
			+ "          (SELECT a1.owner, a1.table_name, c1.column_name, c1.POSITION"
			+ "             FROM SYS.ALL_CONS_COLUMNS c1, SYS.ALL_CONSTRAINTS a1"
			+ "            WHERE a1.owner = c1.owner"
			+ "              AND a1.table_name = c1.table_name"
			+ "              AND a1.constraint_name = c1.constraint_name"
			+ "              AND a1.constraint_type = 'P'"
			+ " 			 AND a1.table_name = UPPER(:table_name)"
			+ "              AND a1.owner = UPPER(:owner) ) c1"
			+ "    WHERE coms.owner = cols.owner"
			+ "      AND coms.table_name = cols.table_name"
			+ "      AND coms.column_name = cols.column_name"
			+ "      AND coms.owner = c1.owner (+)"
			+ "      AND coms.table_name = c1.table_name(+)"
			+ "      AND coms.column_name = c1.column_name(+)"
			+ "      AND cols.table_name = upper(:table_name)"
			+ "      AND cols.owner = upper(:owner) ORDER BY column_id";
	
		oDb.setDebug(true);
		list = oDb.selector(Map.class, sql, param);
		navigator.setList(list);
		navigator.sync();
		
	}
	
}
