/*
 * 작성자 : 김상준
 * Created on 2004. 10. 11.
 *
 */
package maf.menu;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author 김상준(goindole@miraenet.com)
 * Create by 2004. 10. 11.
 * 
 */
public class MstMenuBean {
    private String site = ""; 
    private String pgid = "";
    private String oldpgid = "";
    private String title = "";
    private String title_en = "";    
    private String page = "";
    private String href = "";
    private int seq = 0;
    private String pnodeid = "";
    private String isuse = "T";
    private String target = "_self";
    private String dir = "";
    private String islink = "T";
    private String usn=null;
    private String uname = "";
    private Date update_dt = null;
    private String layout = "";
    private String is_tmenu = "F";
    private String is_lmenu = "T";
    private String short_desc = "";
    private String session_chk = "F";
    private String is_sitemap = "T";
    private String style = "";
	private String contact_email = null;
	private String contact = null;
	private String help_file = null;
	private String p_cnt = "0";
	private String p_cnt_ok = "0";
	private String update_id = null;
	private String messagekey = null;
    private int admin_usn;
    /**
	* Servlet여부 (servlet : T, JSP File 또는 HTML : F)
	*/	
	private String isservlet = "T";

	
    public int getAdmin_usn() {
        return admin_usn;
    }
    public void setAdmin_usn(int adminusn) {
        this.admin_usn = adminusn;
    }
    /**
     * @return Returns the hREF.
     */
    public String getHref() {
        return href;
    }
    /**
     * @param href The hREF to set.
     */
    public void setHref(String href) {
        this.href = href;
    }
    /**
     * @return Returns the iSUSE.
     */
    public String getIsuse() {
        return isuse;
    }
    /**
     * @param isuse The iSUSE to set.
     */
    public void setIsuse(String isuse) {
        this.isuse = isuse;
    }
    /**
     * @return Returns the pGID.
     */
    public String getPgid() {
        return pgid;
    }
    /**
     * @param pgid The pGID to set.
     */
    public void setPgid(String pgid) {
        this.pgid = pgid;
    }
    /**
     * @return Returns the pNODEID.
     */
    public String getPnodeid() {
        return pnodeid;
    }
    /**
     * @param pnodeid The pNODEID to set.
     */
    public void setPnodeid(String pnodeid) {
        this.pnodeid = pnodeid;
    }
    /**
     * @return Returns the sEQ.
     */
    public int getSeq() {
        return seq;
    }
    /**
     * @param seq The sEQ to set.
     */
    public void setSeq(int seq) {
        this.seq = seq;
    }
    /**
     * @return Returns the sITE.
     */
    public String getSite() {
        return site;
    }
    /**
     * @param site The sITE to set.
     */
    public void setSite(String site) {
        this.site = site;
    }
    /**
     * @return Returns the tARGET.
     */
    public String getTarget() {
        return target;
    }
    /**
     * @param target The tARGET to set.
     */
    public void setTarget(String target) {
        this.target = target;
    }
    /**
     * @return Returns the tITLE.
     */
    public String getTitle() {
        return title;
    }
    /**
     * @param title The tITLE to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setPage(String args){
        this.page = args;
    }
    public String getPage(){
        return this.page; 
    }
    public void setDir (String args){
        this.dir = args;
    }
    public String getDir (){
        return this.dir;
    }
    public void setIslink(String args){
        this.islink = args;
    }
    public String getIslink(){
        return this.islink;
    }
    public void setUsn(String args){
        this.usn = args;
    }
    public String getUsn(){
        return this.usn;
    }
    public void setUname(String args){
        this.uname = args;
    }
    public String getUname(){
        return this.uname;
    }
    public void setUpdate_dt(java.util.Date args){
        this.update_dt = args; 
    }
    public java.util.Date getUpdate_dt(){
        return this.update_dt;
    }
    public String getUpdate_dt_String(String format){
        String dateStr = "";
        try{
            SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.KOREAN);
            dateStr = sdf.format(this.update_dt);
        }catch(Exception e){}
        
        return dateStr;
    }
    
    public String getIs_lmenu() {
        return is_lmenu;
    }
    public void setIs_lmenu(String is_lmenu) {
        this.is_lmenu = is_lmenu;
    }
    public String getIs_tmenu() {
        return is_tmenu;
    }
    public void setIs_tmenu(String is_tmenu) {
        this.is_tmenu = is_tmenu;
    }
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
    
    public String getSession_chk() {
        return session_chk;
    }
    public void setSession_chk(String session_chk) {
        this.session_chk = session_chk;
    }
	/**
	 * Set isservlet : Servlet여부 (servlet : T, JSP File 또는 HTML : F) DB TYPE :
	 * CHAR
	 */
	public void setIsservlet(String isservlet) {
		this.isservlet = isservlet;
	}

	/**
	 * Get isservlet : Servlet여부 (servlet : T, JSP File 또는 HTML : F) DB TYPE :
	 * CHAR
	 */
	public String getIsservlet() {
		return this.isservlet;
	}
	
	/**
	* Get is_sitemap : 사이트맵 표시여부 (T:표시, F:표시안함)
	* DB TYPE : CHAR
	*/
	public String getIs_sitemap(){
		return this.is_sitemap;
	}
	/**
	* Set is_sitemap : 사이트맵 표시여부 (T:표시, F:표시안함)
	* DB TYPE : CHAR
	*/
	public void setIs_sitemap(String is_sitemap){
		this.is_sitemap = is_sitemap;
	}
	/**
	 * @return Returns the oldpgid.
	 */
	public String getOldpgid() {
		return oldpgid;
	}
	/**
	 * @param oldpgid The oldpgid to set.
	 */
	public void setOldpgid(String oldpgid) {
		this.oldpgid = oldpgid;
	}
	/**
	 * @return Returns the contact.
	 */
	public String getContact() {
		return contact;
	}
	/**
	 * @param contact The contact to set.
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
	/**
	 * @return Returns the contact_email.
	 */
	public String getContact_email() {
		return contact_email;
	}
	/**
	 * @param contact_email The contact_email to set.
	 */
	public void setContact_email(String contact_email) {
		this.contact_email = contact_email;
	}
	/**
	 * @return Returns the help_file.
	 */
	public String getHelp_file() {
		return help_file;
	}
	/**
	 * @param help_file The help_file to set.
	 */
	public void setHelp_file(String help_file) {
		this.help_file = help_file;
	}
	/**
	 * @return the title_en
	 */
	public String getTitle_en() {
		return title_en;
	}
	/**
	 * @param title_en the title_en to set
	 */
	public void setTitle_en(String title_en) {
		this.title_en = title_en;
	}
	/**
	 * @return the style
	 */
	public String getStyle() {
		return style;
	}
	/**
	 * @param style the style to set
	 */
	public void setStyle(String style) {
		this.style = style;
	}
	/**
	 * @return the p_cnt
	 */
	public String getP_cnt() {
		return p_cnt;
	}
	/**
	 * @param p_cnt the p_cnt to set
	 */
	public void setP_cnt(String p_cnt) {
		this.p_cnt = p_cnt;
	}
	/**
	 * @return the p_cnt_ok
	 */
	public String getP_cnt_ok() {
		return p_cnt_ok;
	}
	/**
	 * @param p_cnt_ok the p_cnt_ok to set
	 */
	public void setP_cnt_ok(String p_cnt_ok) {
		this.p_cnt_ok = p_cnt_ok;
	}
	/**
	 * @return the update_id
	 */
	public String getUpdate_id() {
		return update_id;
	}
	/**
	 * @param update_id the update_id to set
	 */
	public void setUpdate_id(String update_id) {
		this.update_id = update_id;
	}
	public String getMessagekey() {
		return messagekey;
	}
	public void setMessagekey(String messagekey) {
		this.messagekey = messagekey;
	}
	
}
