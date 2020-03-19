package maf.web.mvc.beans;

import maf.base.BaseObject;

/**
 * @author G2BKorea Create.
 * 
 */
public class SiteInfo extends BaseObject{

	private static final long serialVersionUID = 1L;
	
	private String site, title, short_desc, url;
    private String  layout; 

    private boolean session_chk = false;
    
    public String getLayout() {
        return layout;
    }
    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getShort_desc() {
        return short_desc;
    }
    public void setShort_desc(String short_desc) {
        this.short_desc = short_desc;
    }
    public String getSite() {
        return site;
    }
    public void setSite(String site) {
        this.site = site;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    
    public boolean isSession_chk() {
        return session_chk;
    }
    public void setSession_chk(boolean session_chk) {
        this.session_chk = session_chk;
    }
	/**
	 * @return Returns the url.
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url The url to set.
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String toString() {
		return this.site;
	}
}
