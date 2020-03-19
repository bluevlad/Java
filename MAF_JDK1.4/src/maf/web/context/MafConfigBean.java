package maf.web.context;

import maf.base.BaseObject;

public class MafConfigBean extends BaseObject {
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
	private String contextPath=null;
	private String contextImagePath=null;
	private String siteTemplate_src=null;
	private String siteTemplate_path=null;
	private String locale_resolver=null;
	private String site_resolver=null;
	private String dbms_name=null;
	private String dbms_type=null;
	private String smtp_host=null;
	private String smtp_userid=null;
	private String smtp_password=null;
	
	public String getContextImagePath() {
		return contextImagePath;
	}
	public void setContextImagePath(String contextImagePath) {
		this.contextImagePath = contextImagePath;
	}
	public String getContextPath() {
		return contextPath;
	}
	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}
	public String getDbms_name() {
		return dbms_name;
	}
	public void setDbms_name(String dbms_name) {
		this.dbms_name = dbms_name;
	}
	public String getDbms_type() {
		return dbms_type;
	}
	public void setDbms_type(String dbms_type) {
		this.dbms_type = dbms_type;
	}
	public String getLocale_resolver() {
		return locale_resolver;
	}
	public void setLocale_resolver(String locale_resolver) {
		this.locale_resolver = locale_resolver;
	}
	public String getSite_resolver() {
		return site_resolver;
	}
	public void setSite_resolver(String site_resolver) {
		this.site_resolver = site_resolver;
	}
	public String getSiteTemplate_path() {
		return siteTemplate_path;
	}
	public void setSiteTemplate_path(String siteTemplate_path) {
		this.siteTemplate_path = siteTemplate_path;
	}
	public String getSiteTemplate_src() {
		return siteTemplate_src;
	}
	public void setSiteTemplate_src(String siteTemplate_src) {
		this.siteTemplate_src = siteTemplate_src;
	}
	public String getSmtp_host() {
		return smtp_host;
	}
	public void setSmtp_host(String smtp_host) {
		this.smtp_host = smtp_host;
	}
	public String getSmtp_password() {
		return smtp_password;
	}
	public void setSmtp_password(String smtp_password) {
		this.smtp_password = smtp_password;
	}
	public String getSmtp_userid() {
		return smtp_userid;
	}
	public void setSmtp_userid(String smtp_userid) {
		this.smtp_userid = smtp_userid;
	}
	

	

}
