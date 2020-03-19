/*
 * Created on 2006. 4. 6.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.configuration;

/**
 * Code Detail이 Site별로 다른 경우에 사용 
 * 
 * @author bluevlad
 *
 */
public class SiteCodeDetailBean extends CodeDetailBean{

	String site = null;

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
	
	
}

