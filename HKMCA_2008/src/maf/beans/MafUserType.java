/*
 * Created on 2005. 12. 17.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.beans;
/**
 * 사용자 유형 정보
 * @author bluevlad
 *
 */
public class MafUserType {
	private String utype = null;
	private String description = null;
	private boolean superAdmin = false;
	
	public MafUserType(String utype, String description, boolean superAdmin){
		this.utype = utype;
		this.description = description;
		this.superAdmin = superAdmin;
	}
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return Returns the utype.
	 */
	public String getUtype() {
		return utype;
	}
	/**
	 * @return Returns the superAdmin.
	 */
	public boolean isSuperAdmin() {
		return superAdmin;
	}

	
	
	
}

