/*
 * Created on 2006. 08. 30
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import maf.MafUtil;
import maf.aam.MafRoleManager;
import maf.aam.bean.MafRole;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UserRoles extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Log logger = LogFactory.getLog(UserRoles.class);
	private Map roleMap = null;
	private List roleList = null;
//	private String site = null;
	private int bbsLevel = 10;
	
	protected UserRoles() {

	}
	
	public Map getRoleMap() {
		if (this.roleMap == null) {
			return java.util.Collections.EMPTY_MAP;
		} else {
			return this.roleMap;
		}
	}
	/**
	 * List of MafRole
	 * @return
	 */
	public List getRoleList() {
		if (this.roleList == null) {
			return java.util.Collections.EMPTY_LIST;
		} else {
			return this.roleList;
		}
	}
//	public String getSite() {
//		return this.site;
//	}
	
	protected void addRole(String roleid) {
		if(roleid !=null) {
			if(roleMap == null) {
				roleMap = new HashMap();
				roleList = new ArrayList();
			}
			MafRoleManager mrm = MafRoleManager.getInstance();
			
			MafRole r =  mrm.getRole( roleid);
			if( r!= null) {
				if ( bbsLevel > r.getBbslevel() ) {
					bbsLevel = r.getBbslevel();
				}
				this.roleMap.put(roleid, r);
				this.roleList.add(r);
			} else {
				logger.error("roleid " + roleid + " is invalid !!!");
			}
		}  else {
			logger.error("role is null!!!");
		}
	}

	/**
	 * @return the bbsLevel
	 */
	public int getBbsLevel() {
		return bbsLevel;
	}

	/**
	 * @param bbsLevel the bbsLevel to set
	 */
	public void setBbsLevel(int bbsLevel) {
		this.bbsLevel = bbsLevel;
	}
	
	/**
	 * 해당 ID Role을 소유 했는지 확인 
	 * @param roleid
	 * @return
	 */
	public boolean hasRole(String roleid) {
		if (this.roleMap != null && this.roleMap.containsKey(MafUtil.getString(roleid))) {
			return true;
		} else {
			return false;
		}
		
	}
}

