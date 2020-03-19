/*
 * TreeMenu.java, @ 2005-04-22
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.menu;

import java.util.HashMap;
import java.util.Map;
import maf.MafProperties;
import maf.logger.Logging;
import maf.web.mvc.beans.SiteInfo;

/**
 * @author goindole
 * 2007/4/25  Super Class 분리 
 */
public class TreeMenu extends BaseTreeMenu{
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    
	private static Map instance = new HashMap();
	
	protected TreeMenu(String vSite) {
	    super(vSite);
    }
	
	/**
	 * TreeMenu Instace 를 돌려 준다.
	 * 
	 * @return
	 */
	public static TreeMenu getInstance(String site) {
		if (!instance.containsKey(site)) {
			TreeMenu tm = new TreeMenu(site);
			
			instance.put(site, tm);
			Logging.log(TreeMenu.class, site + " is folked");
		}
		
		return (TreeMenu) instance.get(site);
	}
	
	public static  TreeMenu getInstance(SiteInfo siteInfo) {
		if(siteInfo!=null) {
			return TreeMenu.getInstance(siteInfo.getSite());
		} else {
			return TreeMenu.getInstance();			
		}
	}
	public static  TreeMenu getInstance() {
		return TreeMenu.getInstance(MafProperties.DEFAULT_SITE);
	}
}
