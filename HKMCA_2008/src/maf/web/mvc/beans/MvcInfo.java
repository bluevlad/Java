/*
 * ContentBean.java, @ 2005-04-27
 * 
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.mvc.beans;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import maf.context.support.LocaleUtil;
import maf.menu.MenuItem;
import maf.web.mvc.configuration.TemplateInfoConfig;
import maf.web.mvc.configuration.ViewInfoConfig;
import maf.web.session.MySession;

/**
 * @author rainend
 *
 */
public class MvcInfo {
//	private Log logger = LogFactory.getLog(MvcInfo.class);
    private String pgid = null;
    private String title = null; // page title
    private String titleKey = null; 
    private String muri  = null; // 메뉴용 uri
    private MenuItem curMenu = null;
    private long lastModified = 0;
    private Date last_dt = null;
    private TemplateInfoConfig templateInfo = null;
    private String ClassName = null;
    private SiteInfo siteInfo = null;
    private Map templateMapConfig = null;
    private ViewInfoConfig viewInfo = null;
    private String file = null;
    private boolean fileSpecified = false;
    private Locale locale = null; 
   
    public void addMapConfig(String key, String uri ) {
    	if ( templateMapConfig == null) {
    		templateMapConfig = new HashMap();
    	}
    	templateMapConfig.put(key, uri);
    }
    
    public MvcInfo(HttpServletRequest request, HttpServletResponse response) {
    	this.locale = LocaleUtil.getLocale(request);
    	if (MySession.getSessionBean(request, response) != null) {
        	this.login = true;
        } else {
        	this.login = false;

		}
    }
//    private String mid = null; // level 1 id M030101 -> m03 

    private boolean login = false;
    
    /**
     * @return file을 리턴합니다.
     */
    public String getFile() {
    	if(this.fileSpecified ) { 
    		return file;
    	} else {
    		return viewInfo.getUri();
    	}
    }
    public void setFile(String file) {
        this.file = file;
        this.fileSpecified = true;
    }
    /**
     * @return mUri을 리턴합니다.
     */
    public String getMuri() {
        return muri;
    }
    /**
     * @param uri 설정하려는 mUri.
     */
    public void setMuri(String uri) {
        muri = uri;
    }
    /**
     * @return pgid을 리턴합니다.
     */
    public String getPgid() {
        return pgid;
    }
    /**
     * @param pgid 설정하려는 pgid.
     */
    public void setPgid(String pgid) {
        this.pgid = pgid;
    }
    /**
     * @return title을 리턴합니다.
     */
    public String getTitle() {
//    	logger.debug(this.getPgid() + " get title : " + this.title);
        return this.title;
    }
    /**
     * @param title 설정하려는 title.
     */
    public void setTitle(String title) {
        this.title = title;
//        logger.debug(this.getPgid() + " set title : " + title);
    }
    /**
     * @return titleKey을 리턴합니다.
     */
    public String getTitleKey() {
        return titleKey;
    }
    /**
     * @param titleKey 설정하려는 titleKey.
     */
    public void setTitleKey(String titleKey) {
        this.titleKey = titleKey;
    }
    
    
//    /**
//     * @return menu을 리턴합니다.
//     */
//    public TreeMenu getTreeMenu() {
//        return treeMenu;
//    }
//    /**
//     * @param menu 설정하려는 menu.
//     */
//    public void setTreeMenu(TreeMenu menu) {
//        this.treeMenu = menu;
//    }

    
    /**
     * @return lastModified을 리턴합니다.
     */
    public long getLastModified() {
        return lastModified;
    }
    /**
     * @param lastModified 설정하려는 lastModified.
     */
    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
        this.last_dt = new Date();
		this.last_dt.setTime(lastModified);
    }
    
    /**
     * @return mid을 리턴합니다.
     */
    public String getMid() {
    	String mid = null;
    	try {
    		mid = this.pgid.toLowerCase().substring(0, 3);
    	} catch (Exception e) { }
    	return mid;
        
    }

	/**
	 * @return Returns the last_dt.
	 */
	public Date getLast_dt() {
		return last_dt;
	}
	/**
	 * @return Returns the curMenu.
	 */
	public MenuItem getCurMenu() {
		return curMenu;
	}
	/**
	 * @param curMenu The curMenu to set.
	 */
	public void setCurMenu(MenuItem curMenu) {
		
		if (curMenu != null) {
			this.setTitle( curMenu.getTitle(this.getLocale()));
		}
		this.curMenu = curMenu;
	}
	
	/**
	 * @return Returns the login.
	 */
	public boolean isLogin() {
		return login;
	}
//	/**
//	 * @param login The login to set.
//	 */
//	public void setLogin(boolean login) {
//		this.login = login;
//		
//	}
	/**
	 * @return Returns the templateInfo.
	 */
	public TemplateInfoConfig getTemplateInfo() {
		return templateInfo;
	}
	/**
	 * @param templateInfo The templateInfo to set.
	 */
	public void setTemplateInfo(TemplateInfoConfig templateInfo) {
		this.templateInfo = templateInfo;
	}
	/**
	 * Command Class의 class name을 돌려준다.
	 * @return Returns the className.
	 */
	public String getClassName() {
		return ClassName;
	}
	/**
	 * @param className The className to set.
	 */
	public void setClassName(String className) {
		ClassName = className;
	}
	/**
	 * @return Returns the site.
	 */
	public SiteInfo getSiteInfo() {
		return siteInfo;
	}
	/**
	 * @param site The site to set.
	 */
	public void setSiteInfo(SiteInfo site) {
		this.siteInfo = site;
//		/this.title = site.getTitle();
	}
	
	public String getSite() {
		if (this.siteInfo != null) {
			return this.siteInfo.getSite();
		} else {
			return null;
		}
	}
	/**
	 * @return the viewInfo
	 */
	public ViewInfoConfig getViewInfo() {
		return viewInfo;
	}
	/**
	 * @return the viewInfo
	 */
	public String getViewName() {
		if(viewInfo != null) {
			return viewInfo.getName();
		} else {
			return null;
		}
	}
	/**
	 * @param viewInfo the viewInfo to set
	 */
	public void setViewInfo(ViewInfoConfig viewInfo) {
		this.viewInfo = viewInfo;
	}
	public Locale getLocale() {
		return locale;
	}
//	public void setLocale(Locale locale) {
//		this.locale = locale;
//	}

    
}
