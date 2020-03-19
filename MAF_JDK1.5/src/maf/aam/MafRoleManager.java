/*
 * Created on 2006. 08. 30
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.aam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maf.aam.bean.MafRole;
import maf.mdb.Mdb;
import maf.mdb.drivers.MdbDriver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MafRoleManager {
	private static MafRoleManager  instance;
	private Log logger = LogFactory.getLog(MafRoleManager.class);

	private Map siteRoleMap = null;
	private List roleList = null;
	
	private MafRoleManager() {
		if ( siteRoleMap == null ) {
			reloadDB();
		}
	}

	public static synchronized MafRoleManager  getInstance() {
		if (instance == null) instance = new MafRoleManager();
		return instance;
	}
	
	public Map getRoleMap() {
		if( siteRoleMap == null) {
			return null;
		} else {
//			MafSiteRole siteRole = (MafSiteRole) siteRoleMap.get(site);
//			if(siteRole != null) {
//				return siteRole.getRoleMap();
//			} else {
//				return null;
//			}
			return siteRoleMap;
		}
	}
	
//	public MafRole getRole(String site, String roleid) {
	public MafRole getRole( String roleid) {
//		MafSiteRole siteRole = (MafSiteRole) siteRoleMap.get(site);
		if(siteRoleMap != null) {
			return (MafRole) siteRoleMap.get(roleid);
		} else {
			return null;
		}
	}
	public List getRoleList() {

//			MafSiteRole siteRole = (MafSiteRole) siteRoleMap.get(site);
			if(roleList != null) {
				return roleList;
			} else {
				return null;
			}

	}
	
	public synchronized void reloadDB() {
		MdbDriver oDb = null;
		Map tmpMap = new HashMap();
		List tmpList = null;
		final String sql = " SELECT    role_id ID, role_name NAME, bigo, is_super root, bbs_level bbslevel "   +
			"     FROM maf_role"   +
			" ORDER BY  role_id"  ;
		try {
			
            oDb = Mdb.getMdbDriver();
            tmpList = oDb.selector(MafRole.class, sql);
            
            if(tmpList != null) {

            	MafRole tRole = null;
            	
            	for(int i = 0; i < tmpList.size(); i++) {
            		tRole = (MafRole) tmpList.get(i);
            		tmpMap.put(tRole.getId(), tRole);
    
            	}
            }
            oDb.close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally{
			if (oDb != null) try {oDb.close();} catch (Exception e) {};
			oDb = null;
		}
		if(siteRoleMap == null) {
			siteRoleMap = new HashMap();
		}
		synchronized (siteRoleMap) {
			siteRoleMap = tmpMap;
		}
		if( roleList != null) {
			synchronized (roleList) {
				roleList = tmpList;
			}
		} else {
			roleList = tmpList;
		}
		 
	}
}

