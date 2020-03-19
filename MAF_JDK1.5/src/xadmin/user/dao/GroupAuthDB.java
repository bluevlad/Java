/*
 * Created on 2005. 8. 11.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package xadmin.user.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

/**
 * @author goindole
 *
 */
public class GroupAuthDB {
	public static List getMenuList(MdbDriver oDb)  throws MdbException {
		final String sql = " select  ma.menu_code, ma.menu_name, mc.menu_cname"   +
			" from MST_ADMIN_MENU ma, MST_ADMIN_MENU_CATEGORY mc"   +
			" where ma.MENU_CCODE = mc.MENU_CCODE"   +
			" order by mc.MENU_CNAME, ma.MENU_NAME"  ;		
		return (List) oDb.selector(Map.class, sql);
	}
	
	public static List getGroupMenuList(MdbDriver oDb, String grp_id)  throws MdbException {
		final String sql = " select  ma.menu_code, ma.menu_name, mc.menu_cname"   +
			" from MST_ADMIN_MENU ma, MST_ADMIN_MENU_CATEGORY mc"   +
			" where ma.MENU_CCODE = mc.MENU_CCODE "   +
			" 	  and exists (select * from MST_ADMIN_MENU_GRP mg "   +
			" 	  	  		 where ma.MENU_CODE = mg.MENU_CODE"   +
			" 				 	   and mg.GRP_ID = :grp_id)"   +
			" order by mc.MENU_CNAME, ma.MENU_NAME "  ;
		
		Map param = new HashMap();
		param.put("grp_id", grp_id);
		return (List) oDb.selector(Map.class, sql, param);
	}
	
	public static List getGroupList(MdbDriver oDb)  throws MdbException {
		final String sql = " select *"   +
			" from MST_ADMIN_GRP ma"   +
			" order by grp_id"  ;
		return (List) oDb.selector(Map.class, sql);
	}
	
	/**
	 * 권한 제거
	 * @param oDb
	 * @param param
	 * @throws MdbException
	 */
	public static void removeAuth(MdbDriver oDb, Map param)  throws MdbException  {
		final String sql ="delete from MST_ADMIN_MENU_GRP "
				+ " where menu_code = :menu_code "
				+ " 	and grp_id = :grp_id ";
		oDb.executeUpdate(sql,param);		
	}
	
	public static void addAuth(MdbDriver oDb, Map param)  throws MdbException  {
		final String sql = "INSERT INTO MST_ADMIN_MENU_GRP (" 
			  + " menu_code, grp_id " 
			  + " ) "
			  + " VALUES ("
			  + " :menu_code, :grp_id "
			  + ") ";

		oDb.executeUpdate(sql,param);		
	}
}

