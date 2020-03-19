/*
 * Created on 2006. 07. 19
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.mdb.sqlhelper;

import java.util.HashMap;
import java.util.Map;

import maf.mdb.sqlhelper.support.OracleSqlWhereBuilder;

public class Test {
	public static String getSql() {
		SqlWhereBuilder  sw = new OracleSqlWhereBuilder();
		Map param = new HashMap();
		param.put("reg_name", "1");
		param.put("title", "srch_title");
		String[] st = {"a","b","c"};
		param.put("cat", st);
//		param.put("start_dt", "start_dt");
//		param.put("end_dt", "end_dt");

		sw.addCond(Where.eq("reg_name", ":reg_name"));
		sw.addCond(Where.like("title", ":title"));
		sw.addCond(Where.in("cat", ":cat"));
		sw.addCond(Where.between("reg_dt", "to_date(:start_dt,'YYYYMMDD')", ":end_dt"));
		
		return sw.getWhereString(param);
	}
}

