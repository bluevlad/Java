/*
 * Created on 2005. 12. 6.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.aam.bean;

import maf.base.BaseObject;

public class AAMBean extends BaseObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String site = null;
	
	private String pgid = null;

	/**
	* 사용자 그룹
	*/	
	private String role = null;

	/**
	* Insert/Update
	*/	
	private boolean auth_c = false;

	/**
	* Select / Read
	*/	
	private boolean auth_r = false;

	/**
	* Update
	*/	
	private boolean auth_u = false;

	/**
	* Delete
	*/	
	private boolean auth_d = false;


	////////////////////////////////////////////////////////////////////////////////
	/**
	* Get pgid : 
	* DB TYPE : VARCHAR2
	*/
	public String getPgid(){
		return this.pgid;
	}
	/**
	* Set pgid : 
	* DB TYPE : VARCHAR2
	*/
	public void setPgid(String pgid){
		this.pgid = pgid;
	}

	/**
	* Get utype : 사용자 그룹
	* DB TYPE : VARCHAR2
	*/
	public String getRole(){
		return this.role;
	}
	/**
	* Set utype : 사용자 그룹
	* DB TYPE : VARCHAR2
	*/
	public void setRole(String utype){
		this.role = utype;
	}
	/**
	 * Insert/Update
	 * @return Returns the auth_c.
	 */
	public boolean isAuth_c() {
		return auth_c;
	}
	/**
	 * Insert/Update
	 * @param auth_c The auth_c to set.
	 */
	public void setAuth_c(boolean auth_c) {
		this.auth_c = auth_c;
	}
	/**
	 * Delete
	 * @return Returns the auth_d.
	 */
	public boolean isAuth_d() {
		return auth_d;
	}
	/**
	 * Delete
	 * @param auth_d The auth_d to set.
	 */
	public void setAuth_d(boolean auth_d) {
		this.auth_d = auth_d;
	}
	/**
	 * Select / Read
	 * @return Returns the auth_r.
	 */
	public boolean isAuth_r() {
		return auth_r;
	}
	/**
	 * Select / Read
	 * @param auth_r The auth_r to set.
	 */
	public void setAuth_r(boolean auth_r) {
		this.auth_r = auth_r;
	}
	/**
	 * Update
	 * @return Returns the auth_u.
	 */
	public boolean isAuth_u() {
		return auth_u;
	}
	/**
	 * Update
	 * @param auth_u The auth_u to set.
	 */
	public void setAuth_u(boolean auth_u) {
		this.auth_u = auth_u;
	}
	/**
	 * @return Returns the site.
	 */
	public String getSite() {
		return site;
	}
	/**
	 * @param site The site to set.
	 */
	public void setSite(String site) {
		this.site = site;
	}
	
	public void setFalse() {
		this.auth_c = false;
		this.auth_d = false;
		this.auth_r = false;
		this.auth_u = false;
		
	}


}

