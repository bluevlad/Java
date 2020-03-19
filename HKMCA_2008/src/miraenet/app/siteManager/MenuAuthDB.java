/*
 * Created on 2006. 09. 01
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package miraenet.app.siteManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MenuAuthDB {
	private static Log logger = LogFactory.getLog(MenuAuthDB.class);

	/**
	 * PGID에 해당하는 사용자 유형별 권한을 가져온다.
	 * @param oDb
	 * @param pgid
	 * @return
	 */
	public static List getPgAuth(MdbDriver oDb, String site, String pgid) {
		String sql = " SELECT mu.role_id ROLE, mu.role_name name, mu.comments, ma.pgid," +
				"        ma.auth_r auth_r, ma.auth_c," +
				"        ma.auth_d, ma.auth_u," +
				"        mu.is_super root" +
				"       FROM maf_role mu, (SELECT *" +
				"                            FROM maf_menu_auth" +
				"                            WHERE pgid = :pgid AND site = :site) ma" +
				"       WHERE mu.role_id = ma.role_id(+)" +
				"       ORDER BY mu.role_id";
		List rv = null;
		Map param = new HashMap();
		param.put("pgid", pgid);
		param.put("site", site);

		try {
			rv = oDb.selector(Map.class, sql, param);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return rv;
	}

	/**
	 * 선택한 메뉴PGID를 포함한 하부 메뉴 PGID를 모두 가져온다.
	 * @param oDb
	 * @param pgid
	 * @return
	 */
	public static List getMenuList(MdbDriver oDb, Map param) throws MdbException {

		final String sql = " SELECT pgid FROM MAF_MENU" +
		"  WHERE INSTR(pgid, :pgid) > 0" +
		"  AND site = :site";

		return oDb.selector(Map.class, sql, param);
	}

	public static List getRoleList(MdbDriver oDb, String site) {

		String sql = "SELECT site, role_id pgid, role_name title, 'HOME' AS pnodeid" +
				" FROM maf_role" +
				" WHERE site = :p_site" +
				" ORDER BY title";

		List rv = null;

		try {
			Map param = new HashMap();
			param.put("p_site", site);
			rv = oDb.selector(Map.class, sql, param);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return rv;
	}

	public static int insertMenuAuth(MdbDriver oDb, Map param)  throws MdbException {

		String sql = "INSERT INTO MAF_MENU_AUTH (" +
				" site, pgid, role_id, auth_c, auth_r," +
				" auth_u, auth_d" +
				" )" +
				" VALUES (" +
				" :site, :pgid, :role_id, :auth_c, :auth_r," +
				" :auth_u, :auth_d" +
				" )";
		return oDb.executeUpdate(sql, param);
	}
	
	public static int updateMenuAuth(MdbDriver oDb, Map param)  throws MdbException {

		String sql = "UPDATE MAF_MENU_AUTH" +
				" SET" +
				" auth_c = :auth_c," +
				" auth_r = :auth_r," +
				" auth_u = :auth_u," +
				" auth_d = :auth_d" +
				" WHERE site = :site" +
				" AND pgid = :pgid" +
				" AND role_id = :role_id";
		
		return oDb.executeUpdate(sql, param);
	}
	
	public static int deleteMenuAuth(MdbDriver oDb, Map param)  throws MdbException {

		String sql = "delete from MAF_MENU_AUTH" +
				" WHERE site = :site AND pgid = :pgid ";
		
		return oDb.executeUpdate(sql, param);
	}
	
	public static int deleteMenuAuthAll(MdbDriver oDb, Map param)  throws MdbException {

		String sql = "delete from MAF_MENU_AUTH" +
				" WHERE site = :site AND INSTR(pgid , :pgid) > 0 ";
		
		return oDb.executeUpdate(sql, param);
	}
	
}
