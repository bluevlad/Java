package maf.aam;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import maf.MafProperties;
import maf.aam.bean.AAMBean;
import maf.aam.bean.AAMPgidRoles;
import maf.logger.Trace;
import maf.mdb.Mdb;
import maf.mdb.drivers.MdbDriver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class DefaultAAMLoader implements AAMLoader{
	private static DefaultAAMLoader  instance;
	
	private Log logger = LogFactory.getLog(DefaultAAMLoader.class);
	
	List authList = null;
	
	private DefaultAAMLoader() {
		if ( authList == null ) {
			getSiteMenuAuth();
		}
	}
	
	public static synchronized DefaultAAMLoader  getInstance() {
		if (instance == null) instance = new DefaultAAMLoader();
		return instance;
	}
	
	
	/**
	 * Map of Pgid()+ob.getRole(), AAMBean 
	 * @param site
	 * @return
	 */
	private synchronized void getSiteMenuAuth() {
		MdbDriver oDb = null;
		AAMBean ob = null;
		Map tmp= null;
		Map tmp_authMap = new HashMap();
		List list = new ArrayList();
		try {
			System.out.println("### " + this.getClass() + " menu initAuth start ### ");
			oDb = Mdb.getMdbDriver();
//			List utypes = MstMenuDB.getUtype(oDb);
			List auth = getAuthAll(oDb, MafProperties.DEFAULT_SITE);
			String pgid_utype = null;
			for(int i = 0; i < auth.size(); i++ ) {
				tmp = (Map) auth.get(i);
				ob = new AAMBean();
				ob.setSite(MafProperties.DEFAULT_SITE);
				ob.setPgid((String)tmp.get("pgid"));
				ob.setRole((String)tmp.get("role"));
				ob.setAuth_c(("Y".equals((String)tmp.get("auth_c"))) ? true:false);
				ob.setAuth_r(("Y".equals((String)tmp.get("auth_r"))) ? true:false);
				ob.setAuth_u(("Y".equals((String)tmp.get("auth_u"))) ? true:false);
				ob.setAuth_d(("Y".equals((String)tmp.get("auth_d"))) ? true:false);
				AAMPgidRoles prole = (AAMPgidRoles) tmp_authMap.get(ob.getPgid());
				if(prole == null) {
					prole = new AAMPgidRoles(ob.getPgid());
				}
				
				pgid_utype = ob.getPgid()+ob.getRole();
				tmp_authMap.put(pgid_utype, ob);
				list.add(ob);
			}

			System.out.println("### " + this.getClass() + " menu initAuth finish ### ");
		} catch (Exception e) {
			logger.error(Trace.getStackTrace(e));
		} finally {
			if(oDb != null) {oDb.close();}
			oDb = null;
		}
		
//		synchronized (authList) {
			authList = list;
//		}


	}

	public final List getAuthAll(String site) {
	    
	    return authList ;
    }
	
	
	public  List getAuthAll(MdbDriver oDb, String site) {
		String sql = " SELECT   mu.role_id role, mu.comments, ma.pgid, NVL (ma.auth_r, 'Y') auth_r,"
				+ "          NVL (ma.auth_c, 'Y') auth_c, NVL (ma.auth_d, 'Y') auth_d,"
				+ "          NVL (ma.auth_u, 'Y') auth_u"
				+ "     FROM maf_role mu, (SELECT *"
				+ "                               FROM maf_menu_auth"
				+ "                              WHERE site = :site) ma"
				+ "    WHERE mu.role_id = ma.role_id(+)"
				+ " ORDER BY mu.role_id";
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

	public synchronized void reload() {
		getSiteMenuAuth();
	    
    }
	
	
}
