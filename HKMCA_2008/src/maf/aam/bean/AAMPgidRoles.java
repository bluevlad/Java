/*
 * Created on 2006. 08. 31
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.aam.bean;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import maf.base.BaseHttpSession;
import maf.base.UserRoles;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * PGID의 Role정보를 가지고 있는다.(사이트 정보는 윗단(TreeMenu)에서 처리
 * @author bluevlad
 *
 */
public class AAMPgidRoles {
	private Log logger = LogFactory.getLog(AAMPgidRoles.class);
	private Map roleMap = null;
	private List roleList = null;
	private String pgid = null;

	public AAMPgidRoles(String pgid) {
		if(pgid != null) {
			this.pgid = pgid;
		} else {
			logger.error("pgid is null!!!");
		}
	}
	
	public Map getRoleMap() {
		return this.roleMap;
	}
	/**
	 * List of MafRole
	 * @return
	 */
	public List getRoleList() {
		return this.roleList;
	}
	public String getPgid() {
		return this.pgid;
	}
	
	public void addRole(MafRole role) {
		if(role !=null) {
			if(roleMap == null) {
				roleMap = new HashMap();
				roleList = new ArrayList();
			}
			this.roleMap.put(role.getId(), role);
			this.roleList.add(role);
		}  else {
			logger.error("role is null!!!");
		}
	}
	
	
}

