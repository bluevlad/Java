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
public class UserAuthDB {
	public static List getAdminUserList(MdbDriver oDb)  throws MdbException {
//		final String sql = " SELECT   *"   +
//			"     FROM mst_user mu"   +
//			"    WHERE utype = 'A' "   +
//			"    		 AND NOT EXISTS (SELECT *"   +
//			"                              FROM mst_admin_grp_user ma"   +
//			"                             WHERE ma.user_id = mu.userid)"   +
//			" ORDER BY nm"  ;
		final String sql = " SELECT   *"   +
			"     FROM mst_user mu"   +
			"    WHERE utype = 'A' "   +
			" ORDER BY nm"  ;		
		return (List) oDb.selector(Map.class, sql);
	}
	
	public static List getGroupUserList(MdbDriver oDb, String grp_id)  throws MdbException {
		final String sql = " select ma.grp_id, ma.user_id, mu.nm"   +
			" from MST_ADMIN_GRP_USER ma, mst_user mu"   +
			" where ma.USER_ID = mu.USERID"   +
			"		and ma.grp_id = :grp_id " +
			" order by mu.nm"  ; 
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
	public static void delUserAuth(MdbDriver oDb, Map param)  throws MdbException  {
		final String sql ="delete from MST_ADMIN_GRP_USER "
				+ " where user_id = :user_id "
				+ " 	and grp_id = :grp_id ";
//		oDb.setDebug(true);
		oDb.executeUpdate(sql,param);		
	}
	
	public static void insertUserAuth(MdbDriver oDb, Map param)  throws MdbException  {
		final String sql = "INSERT INTO MST_ADMIN_GRP_USER (" 
			  + "grp_id, user_id " 
			  + " ) "
			  + " VALUES ("
			  + " :grp_id, :user_id "
			  + ") ";

		oDb.executeUpdate(sql,param);		
	}
	
}

