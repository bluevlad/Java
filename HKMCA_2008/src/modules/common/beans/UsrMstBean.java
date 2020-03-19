/*
 * UsrMstBean.java, @ 2005-04-08
 * 
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.common.beans;

/**
 * @author bluevlad
 *
 */
public class UsrMstBean {
	private String usn = "";
	private String userid = "";
	private String nm = "";
	

	private String mobile = "";
	private String email = "";
	private String org_cd = "";
	private String org_type = "";
	private String mst_section = "";
	private String sub_section = "";
	
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
	public String getOrg_cd() {
		return org_cd;
	}
	/**
	 * @param comp_id the comp_id to set
	 */
	public void setOrg_cd(String comp_id) {
		this.org_cd = comp_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMst_section() {
    	return mst_section;
    }
	public void setMst_section(String mst_section) {
    	this.mst_section = mst_section;
    }
	public String getSub_section() {
    	return sub_section;
    }
	public void setSub_section(String sub_section) {
    	this.sub_section = sub_section;
    }
	public String getOrg_type() {
    	return org_type;
    }
	public void setOrg_type(String org_type) {
    	this.org_type = org_type;
    }

}
