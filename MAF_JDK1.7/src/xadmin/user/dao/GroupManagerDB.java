/*
 * Created on 2005. 8. 11.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package xadmin.user.dao;

import java.util.HashMap;
import java.util.Map;

import maf.logger.Logging;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

/**
 * @author goindole
 *
 */
public class GroupManagerDB {
	public static Map getGroupInfo(MdbDriver oDb, String grp_id) throws MdbException  {
		final String sql = "SELECT * from MST_ADMIN_GRP where grp_id = :grp_id";
		Map param = new HashMap();
		param.put("grp_id", grp_id);
		return (Map) oDb.selectorOne(Map.class, sql, param);
	}
	
	public static void updateGroup(MdbDriver oDb, Map param)  throws MdbException  {
		final String sql ="update MST_ADMIN_GRP " +
				" set grp_name = :grp_name " + 
				" where grp_id = :grp_id ";
		oDb.executeUpdate(sql,param);		
	}
	
	public static void insertGroup(MdbDriver oDb, Map param)  throws MdbException  {
		final String sql ="insert INTO MST_ADMIN_GRP " +
				" (grp_id, grp_name) " + 
				" values (:grp_id, :grp_name ) ";
		oDb.executeUpdate(sql,param);		
	}
	
	public static boolean deleteGroup(MdbDriver oDb, Map param)  throws MdbException  {
		boolean chk = false;
		try {
			final String sql ="delete from  MST_ADMIN_GRP " +
					" where grp_id = :grp_id ";
			oDb.executeUpdate(sql,param);
			chk = true;
		} catch (Throwable e) {
			Logging.log(GroupManagerDB.class, e.getMessage());
		}
		return chk;
	}
}

