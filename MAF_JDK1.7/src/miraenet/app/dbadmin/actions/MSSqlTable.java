/*
 * Created on 2005. 8. 18.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.dbadmin.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletResponse;

import maf.mdb.Mdb;
import maf.mdb.drivers.MdbDriver;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.beans.ResultMessage;

/**
 * @author goindole
 *
 */
public class MSSqlTable extends DbAdminAction {
    public void doWork(MyHttpServletRequest _req, HttpServletResponse response)
    {
    	super.result.setForward("list");
    	MdbDriver sqldb = Mdb.getMdbDriver("kaissql", MdbDriver.DBMS_SQL2000);

    	String sql_columns = "select o.id,  o.name table_name, c.name column_name, c.colid, "
    		+ " t.name column_type, c.length, c.isnullable, c.typestat, "
			+ "	(select count(*) "
			+ "	 from dbo.sysindexes i, dbo.sysindexkeys k "
			+ "	 where c.colid = k.colid "
			+ "		and  i.id = o.id "
			+ "		and i.id = k.id "
			+ "		and i.indid = k.indid "
			+ "		and status = '18450' )  P "
			+ "from sysobjects o, syscolumns c, systypes t "
			+ "where o.xtype = 'U'  "
			+ "	and o.id = c.id "
			+ " and o.id = object_id(:table_nm)"
			+ "	and c.xtype = t.xtype "
			+ "	and c.xusertype = t.xusertype "
			+ "order by o.name, c.colid";
    	
    	
    	String sql_tables = "select name from sysobjects o where o.xtype = 'U' order by name";
    	String sql_count = "select count(*) cnt from ";
    	String table_nm = null;
    	
    	try {
	    	List ltables = sqldb.selector(Map.class, sql_tables);
	
	    	Map tables = new TreeMap();
	    	Map mcount = new HashMap();
	    	if (tables != null) {
	    		Map param = new HashMap();
	    		for( int i = 0; i < ltables.size(); i++){
	    			Map t = (Map) ltables.get(i);
	    			table_nm = ((String)t.get("name")).toLowerCase() ;
	    			param.put("table_nm", table_nm);
	    			List columns = sqldb.selector(Map.class, sql_columns, param);
	    			tables.put(table_nm, columns);
	    			String rcount = sqldb.selectOne(sql_count + table_nm);
	    			mcount.put(table_nm, rcount);
	    		}
	    	}
	    	result.setAttribute("tables", tables);
	    	result.setAttribute("mcount", mcount);
    	} catch ( Exception e) {
    		result.setSuccess(false, new ResultMessage(e.getMessage()));
    	} finally {
    		if (sqldb != null) {try {sqldb.close();} catch (Exception e) {};}
    	}
    	
    }
}

