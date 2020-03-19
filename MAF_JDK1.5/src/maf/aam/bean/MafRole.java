/*
 * Created on 2006. 08. 30
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.aam.bean;

import maf.base.BaseObject;

public class MafRole extends BaseObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private String site = null;
	private String id = null;
	private String name = null;
	private String bigo = null;
	private String root = "N";
	private int bbslevel = 10;
//	/**
//	 * @return the site
//	 */
//	public String getSite() {
//		return site;
//	}
//	/**
//	 * @param site the site to set
//	 */
//	public void setSite(String site) {
//		this.site = site;
//	}
	/**
	 * @return the bigo
	 */
	public String getBigo() {
		return bigo;
	}
	/**
	 * @param bigo the bigo to set
	 */
	public void setBigo(String bigo) {
		this.bigo = bigo;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the root
	 */
	public boolean isRoot() {
		return ("Y".equals(this.root))?true:false;
	}
	/**
	 * @param root the root to set
	 */
	public void setRoot(String root) {
		if ("1".equals(root) || "Y".equals(root)|| "T".equals(root)) {
			this.root = "Y";
		}
	}
	/**
	 * @return the bbslevel
	 */
	public int getBbslevel() {
		return bbslevel;
	}
	/**
	 * @param bbslevel the bbslevel to set
	 */
	public void setBbslevel(int bbslevel) {
		this.bbslevel = bbslevel;
	}
	
	
}

