/*
 * mMenu.java, @ 2005-04-22
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.mvc.menu;

import java.util.ArrayList;

import maf.MafUtil;
import maf.base.BaseObject;
import miraenet.MiConfig;

/**
 * @author goindole
 * 
 */
public class MenuItem extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// URI에서 http://와 /로 시작하는것은 디렉토리를 타지 않음.
	// private static final RE reg_OUTER = new RE("^http://|^/|^javascript:");

	// /로 끝나는것 체크
	// private static final RE reg_LASTSLASH = new RE("\\/$");
	// private Logger logger = Logger.getLogger(mMenu.class);
	private int cindex = -1;
	private int PrevNodeIndex = -1;
	private boolean hasChild = false;
	private ArrayList child;

	// DB에서 가져온 정보.
	private String pgid = null;
	private String title = null;
	private String page = null;
	private String prevNodeID = null;
	private String dir = null;
	private String URI = null;
	private String URL = null;
	private String target = null;
	private String layout = null;
	private int level = -1;
	private int seq = 0;
	private boolean isLink = true;
	private boolean topMenu = true;
	private boolean leftMenu = true;
	private boolean session_chk;
	private String admin_usn = null;
	private String contact_email = null;
	private String contact = null;
	private String help_file = null;
	private boolean sitemap = true;
	private boolean isservlet = false;
	private boolean layoutSpecified = false;
	
	public MenuItem() {

	}

	public int getCindex() {
		return this.cindex;
	}

	/**
	 * 자식 노드 보유 여부 리턴
	 * 
	 * @return True : 보유 , False : 자식 없음
	 */
	public boolean hasChild() {
		if (child != null) return true;
		else
			return false;
	}

	/**
	 * 자식 노드의 수를 리턴
	 * 
	 * @return -1:자식 없음
	 */
	public int getChildCount() {
		if (child == null) return -1;
		else
			return child.size();
	}

	/**
	 * 자식 노드 넣기
	 * 
	 * @param Child
	 */
	protected void putChild(MenuItem Child) {
		if (child == null) {
			child = new ArrayList();
		}
		child.add(Child);
	}

	/**
	 * 자식 노드들을 리턴
	 * 
	 * @return
	 */
	protected MenuItem[] getChilds() {
		if (child == null) return null;
		return (MenuItem[]) child.toArray(new MenuItem[0]);
	}

	public MenuItem getChild(int index) {
		if (child == null) return null;
		return (MenuItem) child.get(index);
	}
	
	public String getAdmin_usn() {
		return admin_usn;
	}

	public void setAdmin_usn(String admin_usn) {
		this.admin_usn = admin_usn;
	}

	public int getPrevNodeIndex() {
		return PrevNodeIndex;
	}

	public String getPgid() {
		return pgid;
	}

	public String getTitle() {
		return title;
	}

	public String getPage() {
		return page;
	}

	public String getDir() {
		return dir;
	}

	public int getLevel() {
		return level;
	}

	public boolean isTopMenu() {
		return topMenu;
	}

	public final String getURI() {
		return URI;
	}

	protected void setURI(String vURI) {
		this.URI = vURI;
	}

	public int getSeq() {
		return seq;
	}

	public String getTarget() {
		return target;
	}

	public boolean isSession_chk() {
		return session_chk;
	}

	public void setSession_chk(boolean session_chk) {
		this.session_chk = session_chk;
	}

	/**
	 * @return Returns the isLink.
	 */
	public boolean isLink() {
		return isLink;
	}

	/**
	 * Get isservlet : Servlet여부 (servlet : S, JSP File 또는 HTML : H) DB TYPE : CHAR
	 */
	public void setIsservlet(boolean isservlet) {
		this.isservlet = isservlet;
	}

	/**
	 * @return Returns the hasChild.
	 */
	public boolean isHasChild() {
		return hasChild;
	}

	/**
	 * @return Returns the lAYOUT.
	 */
	public final String getLayout() {
		return layout;
	}
	
	public final boolean isLayoutSpecified() {
		return layoutSpecified;
	}

	/**
	 * @return Returns the prevNodeID.
	 */
	public String getPrevNodeID() {
		return prevNodeID;
	}

	/**
	 * @return Returns the is_sitemap.
	 */
	public boolean isSitemap() {
		return sitemap;
	}

	/**
	 * @param pgid
	 *            The pGID to set.
	 */
	public void setPgid(String pgid) {
		this.pgid = pgid;
	}

	/**
	 * @param prevNodeID
	 *            The prevNodeID to set.
	 */
	public void setPrevNodeID(String prevNodeID) {
		this.prevNodeID = prevNodeID;
	}

	/**
	 * @return Returns the iS_LEFTMENU.
	 */
	public boolean isLeftMenu() {
		return leftMenu;
	}

	/**
	 * @param is_leftmenu
	 *            The iS_LEFTMENU to set.
	 */
	public void setLeftMenu(boolean is_leftmenu) {
		this.leftMenu = is_leftmenu;
	}

	/**
	 * @param is_sitemap
	 *            The is_sitemap to set.
	 */
	public void setSitemap(boolean is_sitemap) {
		this.sitemap = is_sitemap;
	}

	/**
	 * @param index
	 *            The cIndex to set.
	 */
	public void setCindex(int index) {
		this.cindex = index;
	}

	/**
	 * @param dir
	 *            The dir to set.
	 */
	public void setDir(String dir) {
		this.dir = MafUtil.nvl(dir, "");
	}

	/**
	 * @param is_topmenu
	 *            The iS_TOPMENU to set.
	 */
	public void setTopMenu(boolean is_topmenu) {
		this.topMenu = is_topmenu;
	}

	/**
	 * @param isLink
	 *            The isLink to set.
	 */
	public void setLink(boolean isLink) {
		this.isLink = isLink;
	}

	/**
	 * @param layout
	 *            The lAYOUT to set.
	 */
	public void setLayout(String layout) {
		this.layout = layout;
		if(!MafUtil.empty(layout)) {
			this.layoutSpecified = true;
		}
	}

	/**
	 * @param level
	 *            The level to set.
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @param page
	 *            The page to set.
	 */
	public void setPage(String page) {
		this.page = MafUtil.nvl(page, "");
	}

	/**
	 * @param prevNodeIndex
	 *            The prevNodeIndex to set.
	 */
	public void setPrevNodeIndex(int prevNodeIndex) {
		this.PrevNodeIndex = prevNodeIndex;
	}

	/**
	 * @param seq
	 *            The sEQ to set.
	 */
	public void setSeq(int seq) {
		this.seq = seq;
	}

	/**
	 * @param target
	 *            The target to set.
	 */
	public void setTarget(String target) {
		this.target = MafUtil.nvl(target, "");
	}

	/**
	 * @param title
	 *            The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return Returns the isservlet.
	 */
	public boolean isServlet() {
		return isservlet;
	}

	/**
	 * @return Returns the contact.
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * @param contact
	 *            The contact to set.
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
	 * @param contact_email
	 *            The contact_email to set.
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
	 * @param help_file
	 *            The help_file to set.
	 */
	public void setHelp_file(String help_file) {
		this.help_file = help_file;
	}
	public final String getUrl() {
		if(MafUtil.empty(this.URI)) {
			return "";
		} else {
			if(this.isservlet) {
				return MiConfig.SERVLET_PATH + this.URL;
			} else {
				return MiConfig.SERVLET_PATH + MiConfig.MVC_SERVLET_PATH+ this.URL;
			}
		}
	}


	/**
	 * @param url the uRL to set
	 */
	public void setURL(String url) {
		URL = url;
	}
	
	/**
	 * @param url the uRL to set
	 */
	public final String getURL() {
		return this.URL;
	}
}
