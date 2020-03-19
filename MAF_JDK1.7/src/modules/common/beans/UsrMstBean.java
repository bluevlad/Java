/*
 * UsrMstBean.java, @ 2005-04-08
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package modules.common.beans;

/**
 * @author goindole
 *
 */
public class UsrMstBean {
	private String usn = "";
	private String userid = "";
	private String nm = "";
	private String nm_eng = "";

	private String mobile = "";
	private String email = "";
	private String cust_cd = "";
	private boolean valid = false;
	

	/**
	 * @return Returns the mobile.
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile The mobile to set.
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return Returns the nm.
	 */
	public String getNm() {
		return nm;
	}
	/**
	 * @param nm The nm to set.
	 */
	public void setNm(String nm) {
		this.nm = nm;
	}


	/**
	 * @return Returns the userid.
	 */
	public String getUserid() {
		return userid;
	}
	/**
	 * @param userid The userid to set.
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
	/**
	 * @return Returns the usn.
	 */
	public String getUsn() {
		return usn;
	}
	/**
	 * @param usn The usn to set.
	 */
	public void setUsn(String usn) {
		this.usn = usn;
	}


    
    public boolean isValid() {
        return valid;
    }
    public void setValid(boolean valid) {
        this.valid = valid;
    }
	/**
	 * @return the comp_id
	 */
	public String getCust_cd() {
		return cust_cd;
	}
	/**
	 * @param comp_id the comp_id to set
	 */
	public void setCust_cd(String comp_id) {
		this.cust_cd = comp_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
