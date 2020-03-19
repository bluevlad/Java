/*
 * 작성자 : UBQ
 * Created on 2004. 10. 11.
 *
 */
package miraenet.app.siteManager;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author UBQ
 * Create by 2004. 10. 11.
 * 
 */
public class MstMenuDB {
	private static Log logger = LogFactory.getLog(MstMenuDB.class);

	/**
	 * v_Site의 PGID의 정보값을 가져 온다.
	 * @param conn
	 * @param v_Site
	 * @param v_PGID
	 * @return
	 * @throws SQLException
	 */
	public static Map view(MdbDriver oDb, Map param) throws MdbException {

		String sql = "SELECT a.*, " +
		" (select title from maf_menu_lang where site = a.site and pgid = a.pgid and lang = :lang) lang_title  " +
		" FROM MAF_MENU a " +
		" WHERE a.SITE = :SITE and a.PGID = :PGID ";

		return (Map) oDb.selectorOne(Map.class, sql, param);
	}

	public static Map getDesc(MdbDriver oDb, Map param) throws MdbException {

		String sql = "SELECT *" +
		" FROM MAF_MENU_LANG" +
		" WHERE SITE = :SITE and PGID = :PGID and lang = :lang";

		return (Map) oDb.selectorOne(Map.class, sql, param);
	}

	public static int insert(MdbDriver oDb, Map param) throws MdbException {
		int retValue = 0;
		String sql = "INSERT INTO MAF_MENU (" +
		" site, pgid, title, page, seq," +
		" pnodeid, isuse, target, islink," +
		" is_lmenu, is_tmenu," +
		" short_desc, session_chk," +
		" isservlet, is_sitemap," +
		" contact_email, contact, help_file, messagekey, upt_id" +
		" )" +
		" VALUES (" +
		" :site, :pgid, :title, :page, :seq," +
		" :pnodeid, :isuse, :target, :islink," +
		" :is_lmenu, :is_tmenu," +
		" :short_desc, :session_chk," +
		" :isservlet, :is_sitemap," +
		" :contact_email, :contact, :help_file, :messagekey, :upt_id" +
		" )";

		String sql_lang = "INSERT INTO MAF_MENU_LANG (site, pgid, lang, title)" +
		" VALUES (:site, :pgid, :lang, :title)";
		try {
			retValue = oDb.executeUpdate(sql, param);
			retValue = oDb.executeUpdate(sql_lang, param);
		} catch (Throwable e) {
			logger.error(e.getMessage());
		}
		return retValue;
	}

	/**
	 * 메뉴삭제
	 * @param oDb
	 * @param site
	 * @param pgid
	 * @return
	 * @throws Exception
	 */
	public static int delete(MdbDriver oDb, Map param) throws MdbException {
		//        String sql = "{call MI_MENU.deleteMenu (:pSite, :pPgid, :rValue)}";
		int cnt = 0;
		
		final String sql_del_menu = "delete from MAF_MENU where site = :site and pgid = :pgid";
		final String sql_del_auth = "delete from MAF_MENU_AUTH where site = :site and pgid = :pgid";
		final String sql_del_lang = "delete from MAF_MENU_LANG where site = :site and pgid = :pgid and lang = :lang";

		cnt += oDb.executeUpdate(sql_del_auth, param);
		cnt += oDb.executeUpdate(sql_del_lang, param);
		cnt += oDb.executeUpdate(sql_del_menu, param);

		return cnt;
	}

	public static int update(MdbDriver oDb, Map param, String pgidold) throws MdbException {
		int retValue = 0;
		String sql = "UPDATE MAF_MENU" +
				" SET" +
				" site = :site," +
				" pgid = :pgid," +
				" page = :page," +
				" seq = :seq," +
				" pnodeid = :pnodeid," +
				" isuse = :isuse," +
				" target = :target," +
				" islink = :islink," +
				" usn = :usn," +
				" upt_dt = sysdate," +
				" is_lmenu = :is_lmenu," +
				" is_tmenu = :is_tmenu," +
				" short_desc = :short_desc," +
				" session_chk = :session_chk," +
				" admin_usn = :admin_usn," +
				" isservlet = :isservlet," +
				" is_sitemap = :is_sitemap," +
				" contact_email = :contact_email," +
				" contact = :contact," +
				" help_file = :help_file," +
				" messagekey = :messagekey," +
				" upt_id = :upt_id" +
				" WHERE site = :site" +
				" AND pgid = :oldpgid ";
		String sql_lang_ins = "insert into MAF_MENU_LANG" +
			" (title, site, pgid, lang)" +
			" values(:title, :site, :pgid, :lang)";
		String sql_lang_upt = "update MAF_MENU_LANG" +
				" set title = :title" +
				" WHERE site = :site" +
				" AND pgid = :oldpgid" +
				" and lang = :lang";

		param.put("oldpgid", pgidold);
		boolean chk = false; 
		
		try {
			oDb.setAutoCommit(false);
			retValue = oDb.executeUpdate(sql, param);
			if (param.get("lang_title") != "" && param.get("lang_title") != null){
				retValue = oDb.executeUpdate(sql_lang_upt, param);
			}else {
				retValue = oDb.executeUpdate(sql_lang_ins, param);
			}
			oDb.commit();
			chk = true;
		} catch (Throwable e) {
			oDb.rollback();
			logger.error(e.getMessage());
		} finally {
			oDb.setAutoCommit(true);
		}
		if(chk) {
			retValue = 1;
			
		} else {
			retValue = 0;
		}
		return retValue;
	}

	public static int updateDesc(MdbDriver oDb, Map param) throws MdbException {

		String sql = "UPDATE MAF_MENU_LANG" +
				" SET" +
				" menu_desc = :menu_desc" +
				" WHERE site = :site" +
				" AND pgid = :pgid" +
				" and lang = :lang";

        return oDb.executeUpdate(sql, param);
	}

	/**
	 * 사이트 목록을 가져온다.
	 * @param oDb
	 * @return
	 */
	public static List getSimpleSites(MdbDriver oDb) {
		List rv = null;
		String sql = "select site, site_title from maf_site order by site_title";
		try {
			rv = oDb.selector(Map.class, sql);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return rv;
	}

	public static List getMenuTree(MdbDriver oDb, Map param) {
		String sql = "SELECT a.site, a.pgid, dir, seq, NVL (pnodeid, -1) AS pnodeid, isuse, target, LEVEL," +
		" decode((select title from maf_menu_lang where site = a.site and pgid = a.pgid and lang = :lang), '', a.title," +
		"  (select title from maf_menu_lang where site = a.site and pgid = a.pgid and lang = :lang)) title" +
		" FROM maf_menu a" +
		" START WITH a.pgid = 'HOME'" +
		" CONNECT BY PRIOR a.pgid = pnodeid" +
		" and a.site = :site" +
		" order siblings by seq, PGID";

		List rv = null;
		try {
			rv = oDb.selector(Map.class, sql, param);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return rv;
	}

	public static List getMenuDescTree(MdbDriver oDb, Map param) {
		String sql = "SELECT a.site, a.pgid, NVL (a.pnodeid, -1) AS pnodeid," +
		"       decode((select title from maf_menu_lang where site = a.site and pgid = a.pgid and lang = :lang), '', a.title," +
		"      (select title from maf_menu_lang where site = a.site and pgid = a.pgid and lang = :lang)) title" +
		" FROM maf_menu a, maf_menu b" +
		" where a.site = b.site" +
		" and a.pgid = b.pgid" +
		" START WITH a.pgid = 'HOME'" +
		" CONNECT BY PRIOR a.pgid = a.pnodeid" +
		" AND a.site = :site" +
		" order siblings by a.seq, PGID";

		List rv = null;
		try {
			rv = oDb.selector(Map.class, sql, param);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return rv;
	}

	public static List getSiteMap(MdbDriver oDb, Map param) throws MdbException {
		String sql = "SELECT site, pgid, dir, seq, NVL (pnodeid, -1) AS pnodeid, isuse, " +
		"       page, isservlet, target, LEVEL, " +
		"       decode((select title from maf_menu_lang where site = a.site and pgid = a.pgid and lang = :lang), '', a.title, " +
		"              (select title from maf_menu_lang where site = a.site and pgid = a.pgid and lang = :lang)) title  " +
		"  FROM maf_menu a " +
		"  where isuse = 'T' " +
		"  and LEVEL = :lvl " +
		"  and pgid < 'M90' " +
		"  START WITH pgid = 'HOME' AND site = :site CONNECT BY PRIOR pgid = pnodeid " +
		"  order  siblings by seq, PGID ";

		return oDb.selector(Map.class, sql, param);
	}

	public static List getAuthAll(MdbDriver oDb, String site) {
		String sql = "SELECT   mu.role_id role, mu.comments, ma.pgid, NVL (ma.auth_r, 'Y') auth_r," +
				"  NVL (ma.auth_c, 'Y') auth_c, NVL (ma.auth_d, 'Y') auth_d," +
				"  NVL (ma.auth_u, 'Y') auth_u" +
				" FROM maf_role mu, (SELECT *" +
				"                      FROM maf_menu_auth" +
				"                      WHERE site = :site) ma" +
				" WHERE mu.role_id = ma.role_id(+)" +
				" ORDER BY mu.role_id";
		;
		Map param = new HashMap();
		List r = null;
		try {
			param.put("site", site);
			r = oDb.selector(Map.class, sql, param);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return r;
	}
}
