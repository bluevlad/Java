/*
 * 작성자 : 김상준
 * Created on 2004. 10. 11.
 *
 */
package miraenet.app.siteManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;
import maf.web.mvc.beans.MstMenuBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author 김상준(goindole@miraenet.com)
 * Create by 2004. 10. 11.
 * 
 */
public class MstMenuDB  {
	private static Log logger = LogFactory.getLog(MstMenuDB.class);
	
    public MstMenuDB() {
        
    }
    /**
     * v_Site의 PGID의 정보값을 가져 온다.
     * @param conn
     * @param v_Site
     * @param v_PGID
     * @return
     * @throws SQLException
     */
	public static MstMenuBean view(MdbDriver oDb, String v_Site, String v_PGID) throws MdbException{
	    MstMenuBean oB = new MstMenuBean();
	    
		if (v_Site != null) {
		    String sql =   " SELECT SITE, PGID, TITLE, "   +
            					" PAGE, SEQ, PNODEID, ISUSE, nvl(TARGET,'_self') as TARGET, DIR, ISLINK, "   +
                                " USN,  UPDATE_DT, " +
                                " LAYOUT, IS_LMENU, IS_TMENU, SHORT_DESC, SESSION_CHK, ADMIN_USN, " +
                                " isservlet, is_sitemap, " +
                				" contact_email, contact, help_file, p_cnt, p_cnt_ok, update_id " + 
            					" FROM MAF_MENU" +
            					" WHERE SITE = :SITE and PGID = :PGID";
		
		    List at = new ArrayList();
		    at.add(v_Site);
		    at.add(v_PGID);
		   
		    oB = (MstMenuBean) oDb.selectorOne(MstMenuBean.class, sql, at);

		}
		return oB;
	}
    
    public static int insert (MdbDriver oDb, MstMenuBean mbean) throws MdbException {
        int retValue = 0;

        String sql = "INSERT INTO MAF_MENU (" 
        	  + "site, pgid, title, page, seq, " 
        	  + " pnodeid, isuse, target, dir, islink, " 
        	  + " usn, layout, is_lmenu, is_tmenu, " 
        	  + " short_desc, session_chk, admin_usn, title_en, style, " 
        	  + " isservlet, is_sitemap," 
        	  +	" contact_email, contact, help_file, p_cnt, p_cnt_ok, update_id  " 
        	  + " ) "
        	  + " VALUES ("
        	  + " :site, :pgid, :title, :page, :seq, " 
        	  + " :pnodeid, :isuse, :target, :dir, :islink, " 
        	  + " :usn,  :layout, :is_lmenu, :is_tmenu, " 
        	  + " :short_desc, :session_chk, :admin_usn, :title_en, :style, " 
        	  + " :isservlet, :is_sitemap, "
        	  + " :contact_email, :contact, :help_file, :p_cnt, :p_cnt_ok, :update_id "
        	  + ") ";

        try {
        	retValue = oDb.executeUpdate(sql,mbean);

        } catch (Throwable e) {
        	logger.error( e.getMessage());
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
    public static int delete (MdbDriver oDb, String site, String pgid) throws MdbException {

//        String sql = "{call MI_MENU.deleteMenu (:pSite, :pPgid, :rValue)}";
        int cnt = 0;
        final String sql_del_menu = "delete from MAF_MENU where site = :site and pgid = :pgid";
        final String sql_del_auth = "delete from MAF_MENU_AUTH where site = :site and pgid = :pgid";
        try {
        	oDb.setAutoCommit(false);
        	Map param = new HashMap();
        	param.put("site",site);
        	param.put("pgid",pgid);
        	cnt = oDb.executeUpdate(sql_del_auth, param);
        	cnt = oDb.executeUpdate(sql_del_menu, param);
        	oDb.commit();
        } catch (Exception e) {
        	oDb.rollback();
        	cnt = 0;
        	logger.error( e.getMessage());
        }
        oDb.setAutoCommit(true);
        return cnt;

    }
    
    public static int update (MdbDriver oDb, MstMenuBean bean, String pgidold) throws MdbException {
        int retValue = 0;

        String sql = "UPDATE  MAF_MENU "
        	  + " SET " 
        	  + " site = :site,  " 
        	  + " pgid = :pgid,  " 
        	  + " title = :title,  " 
        	  + " page = :page,  " 
        	  + " seq = :seq,  " 
        	  + " pnodeid = :pnodeid,  " 
        	  + " isuse = :isuse,  " 
        	  + " target = :target,  " 
        	  + " dir = :dir,  " 
        	  + " islink = :islink,  " 
        	  + " usn = :usn,  " 
        	  + " update_dt = :update_dt,  " 
        	  + " layout = :layout,  " 
        	  + " is_lmenu = :is_lmenu,  " 
        	  + " is_tmenu = :is_tmenu,  " 
        	  + " short_desc = :short_desc,  " 
        	  + " session_chk = :session_chk,  " 
        	  + " admin_usn = :admin_usn,  " 
        	  + " isservlet = :isservlet,  " 
        	  + " is_sitemap = :is_sitemap, "
        	  + " contact_email = :contact_email,"
        	  + " contact = :contact,"
        	  + " help_file = :help_file," 
        	  + " p_cnt = :p_cnt, " 
        	  + " p_cnt_ok = :p_cnt_ok , " 
        	  + " update_id = :update_id"
        	  + " WHERE  site = :site AND pgid = :oldpgid ";
        bean.setOldpgid(pgidold);
        try {
        	retValue = oDb.executeUpdate(sql,bean);
        } catch (Throwable e) {
        	logger.error( e.getMessage());
        }
        return retValue;
    }    

    /**
     * 사이트 목록을 가져온다.
     * @param oDb
     * @return
     */
    public static List getSimpleSites(MdbDriver oDb) {
        List rv = null;
        String sql = "select site, title from maf_site order by title";
        try {
            rv = oDb.selector( Map.class, sql );
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return rv;
    }
    


    
    
    public static List getMenuTree(MdbDriver oDb, String site) {
        
        String sql = " SELECT     site, pgid, title, dir, seq, NVL (pnodeid, -1) AS pnodeid, isuse,"   +
        "            target, LEVEL"   +
        "       FROM  maf_menu "   +
        " START WITH pgid = 'HOME' AND site = :p_site"   +
        " CONNECT BY PRIOR pgid = pnodeid AND PRIOR site = site"   +
        " order  siblings by seq, PGID";
        
        List rv = null;
        
        try {
            List at = new ArrayList();
            at.add(site);
            rv = oDb.selector( Map.class, sql, at);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return rv;
    }
    

    
    public static List getAuthAll(MdbDriver oDb , String site) {
    	String sql =" SELECT   mu.role_id role, mu.bigo, ma.pgid, NVL (ma.auth_r, 'Y') auth_r,"   +
    	"          NVL (ma.auth_c, 'Y') auth_c, NVL (ma.auth_d, 'Y') auth_d,"   +
    	"          NVL (ma.auth_u, 'Y') auth_u"   +
    	"     FROM maf_role mu, (SELECT *"   +
    	"                               FROM maf_menu_auth"   +
    	"                              WHERE site = :site) ma"   +
    	"    WHERE mu.role_id = ma.role_id(+)"   +
    	" ORDER BY mu.role_id"  ;  ;
    	Map param = new HashMap();
    	List r = null;
    	try {
    		param.put("site", site);
    		r = oDb.selector(Map.class, sql, param);
    	} catch ( Exception e) {
    		logger.error(e.getMessage());
    	}
    	return r;
    }
 
    

}
