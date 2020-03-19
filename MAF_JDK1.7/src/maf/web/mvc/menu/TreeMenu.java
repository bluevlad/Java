/*
 * TreeMenu.java, @ 2005-04-22
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.mvc.menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maf.MafUtil;
import maf.aam.AAMManager;
import maf.aam.bean.AAMBean;
import maf.aam.bean.MafRole;
import maf.base.BaseHttpSession;
import maf.base.BaseObject;
import maf.base.UserRoles;
import maf.logger.Logging;
import maf.logger.Trace;
import maf.mdb.DBResource;
import maf.web.mvc.beans.SiteInfo;
import maf.web.util.HTMLUtil;
import miraenet.MiConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.regexp.RE;

/**
 * @author goindole
 * 2005/11/17 chache 기능추가
 */
public class TreeMenu extends BaseObject{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final String BUFFER_KEY_LOGIN  = "HTML_NAVI_ON";
	private final String BUFFER_KEY_LOGOFF  = "HTML_NAVI_OFF";
	private final String BUFFER_KEY_PGID  = "PGID";
	
	private static Map instance = new HashMap();
	
	private ArrayList aMenu = null;
//	private Map menuMap = null;
	private Map bufferMap = null;
	private Map authMap = null;
	private String site;
//	private int callCount = 0;
	private final String servlet_path = MiConfig.SERVLET_PATH + MiConfig.MVC_SERVLET_PATH;
	
	Log logger = LogFactory.getLog(getClass());

	// URI에서 http://와 /로 시작하는것은 디렉토리를 타지 않음.
	private static final RE reg_OUTER = new RE("^http://|^/|^javascript:");

	private static final RE reg_LASTSLASH = new RE("\\/$");

	/**
	 * @return template_path을 리턴합니다.
	 */
	public String getServlet_path() {
		return servlet_path;
	}

	
	/**
	 * Constructor
	 * 
	 */
	private TreeMenu(String vSite) {
		this.aMenu = new ArrayList();
		this.site = vSite;
		reg_OUTER.setMatchFlags(RE.MATCH_CASEINDEPENDENT);
		bufferMap = new HashMap();
		init();
	}

	/**
	 * TreeMenu Instace 를 돌려 준다.
	 * 
	 * @return
	 */
	public static synchronized TreeMenu getInstance(String site) {
		if (!instance.containsKey(site)) {
			TreeMenu tm = new TreeMenu(site);
			
			instance.put(site, tm);
			Logging.log(TreeMenu.class, site + " is folked");
		}
		
		return (TreeMenu) instance.get(site);
	}
	
	public static synchronized TreeMenu getInstance(SiteInfo siteInfo) {
		if(siteInfo!=null) {
			return TreeMenu.getInstance(siteInfo.getSite());
		} else {
			return TreeMenu.getInstance();			
		}
	}
	public static synchronized TreeMenu getInstance() {
		return getInstance(MiConfig.DEFAULT_SITE);
	}

	/**
	 * DB에서 Data를 새로 가져옮
	 * 
	 */
	public void DataReset() {
		init();
	}

	/**
	 * PGID가 위치한 ArrayList에서의 Index를 찾아줌 
	 * 외부에서는 aMenu만 검색 가능함 DataReset 즉 init에서는 tempArray에서 찾음
	 * 
	 * @param vPGID
	 * @param aT
	 * @return
	 */
	private int getIndex(String vPGID, ArrayList aT) {
		MenuItem oT;
		if ((vPGID != null) && aT != null) {
			for (int i = 0; i < aT.size(); i++) {
				oT = (MenuItem) aT.get(i);
				if (vPGID.equals(oT.getPgid())) {
					aT.set(i, oT);
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * PGID를 이용 Index 를 가져 온다.
	 * 
	 * @param vPGID
	 * @return
	 */
	private int getIndex(String vPGID) {
		return getIndex(vPGID, aMenu);
	}

	/**
	 * Menu 오브젝트를 넘겨줌
	 * 
	 * @param index
	 * @return
	 */
	public MenuItem getMenu(int index) {
		if (index < 0 || index > aMenu.size()) {
			return null;
		} else {
			return (MenuItem) aMenu.get(index);
		}
	}

	/**
	 * PGID에 해당하는 메뉴를 가져온다.
	 * @param vPGID
	 * @return
	 */
	public MenuItem getMenu(String vPGID) {
		 
		Map buffer = getBufferMap(BUFFER_KEY_PGID);
		MenuItem oM = null;
		if (vPGID != null) {
			 oM = (MenuItem) buffer.get(vPGID);
			if(oM == null) {
				for (int i = 0; i < aMenu.size(); i++) {
					if (vPGID.equals(((MenuItem) aMenu.get(i)).getPgid())) {
						oM = (MenuItem) aMenu.get(i);
						if(oM != null) {
							buffer.put(vPGID, oM);
						}
						break;
					}
				}
			} 
		}
		return oM;
	}

	/**
	 * 전체 Menu수 즉 aMenu의 length를 넘겨줌
	 * 
	 * @return
	 */
	public int length() {
		if (this.aMenu == null) {
			return 0;
		} else {
			return this.aMenu.size();
		}
	}

	/**
	 * URI 기준으로 index값을 넘겨줌 현재 파일이 없으면 
	 * Directory 기준으로 하나씩 Parent로 올라 가며 구해 온다. 
	 * 없거나 오류시 -1을 돌려줌
	 * 
	 * @param URI
	 * @return
	 */
	public MenuItem findNode(String URI) {
		final String BUFFER_KEY  = "URI_MENU";
		Map nodeBuffer = getBufferMap(BUFFER_KEY);
		MenuItem oRv = null;
		if (URI == null) {
			URI = "";
		}
		
		// Menu Buffer 이용 ㅋㅋ
		if(nodeBuffer.containsKey(URI)) {
			 oRv = (MenuItem) nodeBuffer.get(URI);
		} else {
			MenuItem oT = null;
			MenuItem oT_child = null;
			if (reg_OUTER.match(URI)) {
	//			logger.debug( ":::::::::findnode:" + URI );
				for (int i = 0; i < aMenu.size(); i++) {
					oT = (MenuItem) aMenu.get(i);
					if (oT != null) {
						if (URI.equals(oT.getURI())) {
							oRv = oT;
							// logger.debug( ":::::::::findnode:" + URI + ":" + oT.getAdmin_usn() );
							if(oT.hasChild()) {
								for(int j = 0; j < oT.getChildCount(); j++) {
									oT_child = oT.getChild(j);
									if(URI.equals(oT_child.getURI())) {
										oRv = oT_child;
									}
								}
							}
							break;
						}
					}
				}
	
			}
			// 없으면 디렉토리 따라 하나씩 올라감 
			if (oRv == null) {
				URI = URI.trim();
				oT = _findNode(URI, true);
				if (oT == null)
					URI = URI + "/";
	
				while (oT == null && URI.lastIndexOf("/") > 0) {
					URI = URI.substring(0, URI.lastIndexOf("/"));
					if (URI.lastIndexOf("/") > -1) {
						oT = _findNode(URI.substring(0, URI.lastIndexOf("/")), false);
					} else {
						oT = null;
					}
				}
				oRv = oT;
			}
			nodeBuffer.put(URI, oRv);
//			logger.debug(BUFFER_KEY + " no hit " + URI);
		}
		return oRv;
	}
	/**
	 * URI 기준으로 관련 PGID를 구함.
	 * 
	 * @param URI
	 * @return
	 */
	public String findPGID(String URI) {
		MenuItem oT = null;
		oT = findNode(URI);
		if (oT != null) {
			return oT.getPgid();
		} else {
			return null;
		}
	}

	/**
	 * FindNode 내부용 상위경로 찾기
	 * 
	 * @param URI
	 * @return
	 */

	private MenuItem _findNode(String URI, boolean isURI) {
		MenuItem oT;

		if ((URI != null) && aMenu != null) {
			// logger.debug( URI );
			for (int i = 0; i < aMenu.size(); i++) {
				oT = (MenuItem) aMenu.get(i);
				if (isURI && URI.equals(oT.getURI())) {
					return oT;
				} else if (URI.equals(oT.getDir())) {
					return oT;
				}
			}
		}
		return null;
	}

	/**
	 * 네비 게이션을 넘겨줌
	 * 
	 * @param oT :
	 *            현재 메뉴
	 * @return
	 */
	public String genHtmlNavigator(MenuItem oT, boolean islogin) {
		
		String buffer_key = null;
		String rv = null;
		if(islogin) {
			buffer_key= BUFFER_KEY_LOGIN;
		} else {
			buffer_key= BUFFER_KEY_LOGOFF;
		}
		Map nodeBuffer = getBufferMap(buffer_key);
		rv = (String) nodeBuffer.get(oT);
		if (rv == null) {
			if (oT == null) {
				return "<a href='/' >HOME</a> &gt;";
			}
			StringBuffer sNavi = new StringBuffer();
			// if(Root == null) Root = "";
			MenuItem[] aParents = getParents(oT);
			if (aParents != null) {
				for (int i = 0; i < aParents.length - 1; i++) {
					sNavi.append(genMenuHref(aParents[i], "N", islogin));
					sNavi.append("&gt;");
				}
				sNavi.append("<strong>" + oT.getTitle() + "</strong>");
			}
			rv =sNavi.toString();
			nodeBuffer.put(oT, rv);
			//logger.debug(buffer_key + " no hit ");
		}
		return rv;
	}
	/**
	 * 네비 게이션을 넘겨줌
	 * 
	 * @param oT :
	 *            현재 메뉴
	 * @return
	 */
	public String genHtmlNavigatorNoHref(MenuItem oT) {
		
		String buffer_key = null;
		String rv = null;

			buffer_key= BUFFER_KEY_LOGOFF;

		Map nodeBuffer = getBufferMap(buffer_key);
		rv = (String) nodeBuffer.get(oT);
		if (rv == null) {

			StringBuffer sNavi = new StringBuffer();
			// if(Root == null) Root = "";
			MenuItem[] aParents = getParents(oT);
			if (aParents != null) {
				for (int i = 0; i < aParents.length - 1; i++) {
					if(! "HOME".equals(aParents[i].getPgid() )) {
						sNavi.append(aParents[i].getTitle());
						sNavi.append(" &gt;");
					}
				}
				sNavi.append("<strong>" + oT.getTitle() + "</strong>");
			}
			rv =sNavi.toString();
			nodeBuffer.put(oT, rv);
			//logger.debug(buffer_key + " no hit ");
		}
		return rv;
	}
	/**
	 * 상단 메뉴용 HTML을 생성
	 * @param PGID
	 * @param islogin
	 * @return
	 */
	public String genHtmlTopMenu(String PGID, boolean islogin) {
		
		if (PGID == null) {
			return "";
		}
		
		StringBuffer sNavi = new StringBuffer(100);

		MenuItem topMenu = getMenu(PGID);
		MenuItem oT = null;
		if (topMenu == null) {
			return "";
		}
		int q = 0;
		
		for (int i = 0; i < topMenu.getChildCount(); i++) {
			oT = topMenu.getChild(i);
			if (oT != null && oT.isTopMenu()) {
				if (q > 0) {
					sNavi.append("&nbsp;<FONT color='#a0a0a0'>|</FONT>&nbsp;");
				}
				sNavi.append(genMenuHref(oT, "T", islogin));
				q++;
			}
		}
		return sNavi.toString();
	}

	/**
	 * oT메뉴를 가지고 aHref를 만듬
	 * 
	 * @param oT
	 * @param mType :
	 *            T:Top메뉴용, L:LMenu용, N: Navigation 용
	 * @return
	 */
	public String genMenuHref(MenuItem oT, String mType, boolean islogin) {
		final String BUFFER_KEY_LOGIN  = "HTML_MHREF_ON_";
		final String BUFFER_KEY_LOGOFF  = "HTML_MHREF_OFF_";
		
		String buffer_key = null;
		String rv = null;
		if(islogin) {
			buffer_key= BUFFER_KEY_LOGIN+mType;
		} else {
			buffer_key= BUFFER_KEY_LOGOFF+mType;;
		}
		Map nodeBuffer = getBufferMap(buffer_key);
		rv = (String) nodeBuffer.get(oT);
		if (rv == null) {
			rv =  _genMenuHref(oT, "", mType, null, islogin);
			nodeBuffer.put(oT,rv);
		}
		return rv;
	}
	
	public String genMenuHref(MenuItem oT, String mType, String target, boolean islogin) {
		final String BUFFER_KEY_LOGIN  = "HTML_MHREF_ON_";
		final String BUFFER_KEY_LOGOFF  = "HTML_MHREF_OFF_";
		
		if(oT == null) {
			return "";
		} else {
			String buffer_key = null;
			String buffer_key2 = null;
			String rv = null;
			if(islogin) {
				buffer_key= BUFFER_KEY_LOGIN+mType;
			} else {
				buffer_key= BUFFER_KEY_LOGOFF+mType;;
			}
			Map nodeBuffer = getBufferMap(buffer_key);
			buffer_key2 = oT.getPgid() +"|" + mType +"|"+ target;
			rv = (String) nodeBuffer.get(buffer_key);
			if (rv == null) {
				rv =  _genMenuHref(oT, "", mType, target, islogin);
				nodeBuffer.put(buffer_key2,rv);
			}
			return rv;
		}
	}

	private String _genMenuHref(MenuItem oT, String Root, String mType, String starget, boolean islogin) {
		StringBuffer sH = new StringBuffer(20);
		String target = null;
		String uri = null;
		if (oT == null)
			return "";
		if (("T".equals(mType) && oT.isTopMenu()) || ("L".equals(mType) && oT.isLeftMenu()) || ("N".equals(mType))) {
			if (oT.isLink()) {
				if (islogin || !oT.isSession_chk()) {
					if (oT.isServlet()) {
						uri = MiConfig.SERVLET_PATH + oT.getURL();
					} else {
						uri = oT.getURL();
					}
					if ("window".equals(oT.getTarget())) {
						sH.append("javascript:KMM_openBrWindow('");
						if (reg_OUTER.match(oT.getPage())) {
							sH.append(oT.getURL());
						} else {
							sH.append(Root);
							sH.append(uri);
						}
						sH.append("','width=10, height=10, scrollbars=no, menubar=no, scrollbars=no, status=no, titlebar=yes, toolbar=no, location=no,  resizable=no, top=-50, left=-50');");
					} else {

						if (reg_OUTER.match(oT.getPage())) {
							sH.append(uri);
						} else {
							if (servlet_path != null) {
								sH.append(servlet_path);
//								sH.append("/");
//								sH.append(site);
								// logger.debug("######" + sH);
							}
							sH.append(Root);
							sH.append(uri);
						}
						if (starget != null) {
							target = starget;
						} else {
							target = oT.getTarget();
						}
					}
				} else {
					sH.append("javascript:loginRequest();");
				}

				return HTMLUtil.getHtmlAtag(sH.toString(), oT.getTitle(), target);
			} else {
				return oT.getTitle();
			}
		} else {
			return "";
		}
	}
	
	public String genMenuUri(MenuItem oT,  boolean islogin) {
		StringBuffer sH = new StringBuffer(20);

		String uri = null;
		if (oT == null)
			return "";
			if (oT.isLink()) {
				if (islogin || !oT.isSession_chk()) {
					if (oT.isServlet()) {
						uri = MiConfig.SERVLET_PATH + oT.getURL();
					} else {
						uri = oT.getURL();
					}
					if ("window".equals(oT.getTarget())) {
						sH.append("javascript:KMM_openBrWindow('");
						if (reg_OUTER.match(oT.getPage())) {
							sH.append(oT.getURL());
						} else {
							sH.append(uri);
						}
						sH.append("','width=10, height=10, scrollbars=no, menubar=no, scrollbars=no, status=no, titlebar=yes, toolbar=no, location=no,  resizable=no, top=-50, left=-50');");
					} else {

						if (reg_OUTER.match(oT.getPage())) {
							sH.append(uri);
						} else {
							if (servlet_path != null) {
								sH.append(servlet_path);
								sH.append("/");
								sH.append(site);
							}
							sH.append(uri);
						}
					}


				return sH.toString();
			} else {
				return oT.getTitle();
			}
		} else {
			return "";
		}
	}
	/**
	 * 상위 노드들을 구해 배열로 넘겨줌(할아버지, 아버지, 나)
	 * 
	 * @param oT
	 * @return
	 */
	public MenuItem[] getParents(MenuItem oT) {
		ArrayList Parents = new ArrayList();
		if (oT == null)
			return null;

		_getParents(oT, Parents);
		return (MenuItem[]) Parents.toArray(new MenuItem[0]);
	}
	
	/**
	 * 상위 노드들을 구해 배열로 넘겨줌(할아버지, 아버지, 나)
	 * 
	 * @param oT
	 * @return
	 */
	private void _getParents(MenuItem oT, ArrayList aParent) {

		if (oT != null) {
			if (oT.getPrevNodeIndex() > -1) {
				_getParents(getParent(oT), aParent);
			}
			aParent.add(oT);
		}
	}

	public String getL3PGID(MenuItem oT) {
		if (oT == null)
			return null;
		MenuItem[] aParents = getParents(oT);
		if (aParents != null && aParents.length > 2) {
			return aParents[2].getPgid();
		} else {
			return null;
		}

	}

	/**
	 * 데이터베이스에서 데이터를 가져와 aMenu에 넣는다. 오류시에는 이전값을 그대로 가지고 있음
	 * 
	 */
	private void init() {
		boolean chk = false;
		ArrayList tempMenu = new ArrayList();
		String sql = "   SELECT  SITE, PGID, TITLE, PAGE, SEQ, PNODEID,"
				+ "   ISUSE, TARGET, DIR, LEVEL as \"LVL\", ISLINK, IS_TMENU, IS_LMENU, "
				+ "    SESSION_CHK, ADMIN_USN, STYLE, isservlet, is_sitemap, "
				+ "	  contact_email, contact, help_file, LAYOUT  " 
				+ "   FROM maf_menu" 
				+ "   where ISUSE = 'T'"
				+ "   START WITH PGID ='HOME' and site = :site " 
				+ "   CONNECT BY PRIOR PGID = PNODEID and prior site = site"
				+ "   order  siblings by seq, PGID";

		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = null;
		
		try {
			conn = DBResource.getConnection();

			System.out.println("### " + this.getClass() + " menu init start ### ");
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, this.site);
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					MenuItem oM = new MenuItem();
					oM.setPrevNodeID(rs.getString("PNODEID"));
					oM.setPgid(rs.getString("PGID"));
					oM.setTitle(rs.getString("TITLE"));
					oM.setPage(rs.getString("PAGE"));
					oM.setDir(rs.getString("DIR"));
					oM.setLink("T".equals(rs.getString("ISLINK"))?true:false);
					oM.setTarget(rs.getString("TARGET"));
					oM.setLayout(rs.getString("LAYOUT"));
					oM.setLevel(rs.getInt("LVL"));
					oM.setSeq(rs.getInt("SEQ"));
					oM.setTopMenu("T".equals(rs.getString("IS_TMENU"))?true:false);
					oM.setLeftMenu("T".equals(rs.getString("IS_LMENU"))?true:false);
					oM.setSession_chk("T".equals(rs.getString("SESSION_CHK"))?true:false);
					oM.setAdmin_usn(rs.getString("ADMIN_USN"));
					oM.setIsservlet("T".equals(rs.getString("isservlet"))?true:false);
					oM.setSitemap("T".equals(rs.getString("is_sitemap"))?true:false);
					oM.setContact(rs.getString("contact"));
					oM.setContact_email(rs.getString("contact_email"));
					oM.setCindex( tempMenu.size());
					oM.setHelp_file(rs.getString("help_file"));
					addMenu(tempMenu, oM);
				}
			}
			chk = true;
			System.out.println("### " + this.getClass() + " menu init success ### ");
		} catch (Exception e) {
			logger.error(Trace.getStackTrace(e));
		} finally {
			if (rs != null)try {rs.close();} catch (Exception e) {}
			if (stmt != null)try {stmt.close();} catch (Exception e) {}
			if (conn != null)try {conn.close();} catch (Exception e) {}
			rs = null;
			stmt = null;
			conn = null;
		}
		if (chk) {
			synchronized (aMenu) {
				authMap = AAMManager.getInstance().getSiteMenuAuth(site);
				aMenu = tempMenu;
			}
			bufferMap.clear();
			logger.info(this.site + " Menu Init Ok!!");
		} else {
			logger.error(this.site + "Menu Init Failure!!!");
		}
	}

	
	
	
	/**
	 * 접근(읽기) 가능여부 확인 
	 * @param pgid
	 * @param utype
	 * @return
	 */
	public boolean chkAuth_READ(String pgid, BaseHttpSession session) {
		
		if(session == null) {
			AAMBean oa = (AAMBean) authMap.get(pgid + AAMManager.ROLE_KEY_GUEST);
			if (oa!= null){
				if(oa.isAuth_r()) {
					return true;
				}
			}
		} else {
			if(session.hasRole(AAMManager.ROLE_KEY_ROOT) ) {
				return true;
			} else {
				UserRoles uroles = session.getUserRole();
				if (uroles != null) {
					List t = uroles.getRoleList();
					if (t != null) {
						for (int i = 0; i < t.size(); i++) {
							MafRole r = (MafRole) t.get(i);
							AAMBean oa = (AAMBean) authMap.get(pgid + r.getId());
							if (oa != null) {
								if (oa.isAuth_r()) {
									return true;
								}
							}
						}
					}
				}
			}

		}
		return false;
	}
	/**
	 * pgid 하부에 접근(읽기) 가능여부 확인 
	 * @param pgid
	 * @param utype
	 * @return
	 */
	public boolean chkAuth_Menu(String pgid, BaseHttpSession session) {
		boolean chk = false;
		
		chk = this.chkAuth_READ(pgid, session);
		if (!chk ) {
			MenuItem oMenu = this.getMenu(pgid);
			chk = this._chkAuth_Menu(oMenu, session);
		}
		return chk;
	}
	
	/**
	 *현재 메뉴 및 하위에 읽기 권한이 있으면 True
	 * @param oMenu
	 * @param session
	 * @return
	 */
	private boolean _chkAuth_Menu(MenuItem oMenu, BaseHttpSession session) {
		boolean chk = false;
		String pgid = null;
		if (oMenu != null) {
			chk = this.chkAuth_READ(pgid, session);
			if (!chk && oMenu.hasChild()) {
				MenuItem[] aMenu = oMenu.getChilds();
				for(int i = 0; i < aMenu.length; i++) {
					chk = this._chkAuth_Menu(aMenu[i], session);
					if(chk) {
						return chk;// 읽기 권한이 하나라도 있으면 return;
					}
				}
			}
		}
		return chk;
	}
	/**
	 * 현재 페이지 권한을 가져온다.
	 * @param pgid
	 * @param session
	 * @return
	 */
	public AAMBean getAuthInfo(String pgid, BaseHttpSession session) {
		AAMBean aam = new AAMBean();
		aam.setPgid(pgid);
		aam.setFalse();
		
		if(session == null) {
			AAMBean oa = (AAMBean) authMap.get(pgid + AAMManager.ROLE_KEY_GUEST);
			if (oa!= null){
				if(oa.isAuth_r()) {
					aam.setAuth_r(true);
				}
				if(oa.isAuth_c()) {
					aam.setAuth_c(true);
				}
				if(oa.isAuth_u()) {
					aam.setAuth_u(true);
				}
				if(oa.isAuth_d()) {
					aam.setAuth_d(true);
				}
			}
		} else {
			// 슈퍼관리자 ROOT는 모든 페이지의 권한을 가짐 
			if(session.hasRole(AAMManager.ROLE_KEY_ROOT)) {
				aam.setAuth_c(true);
				aam.setAuth_r(true);
				aam.setAuth_u(true);
				aam.setAuth_d(true);
			} else {
				UserRoles uroles = session.getUserRole();
				List t = uroles.getRoleList();
				for(int i = 0 ; i < t.size(); i++) {
					MafRole r = (MafRole) t.get(i);
					AAMBean oa = (AAMBean) authMap.get(pgid + r.getId());
					
					if (oa!= null){
						if(oa.isAuth_r()) {
							aam.setAuth_r(true);
						}
						if(oa.isAuth_c()) {
							aam.setAuth_c(true);
						}
						if(oa.isAuth_u()) {
							aam.setAuth_u(true);
						}
						if(oa.isAuth_d()) {
							aam.setAuth_d(true);
						}
					}
				}
				
			}
			
		}
		return aam;
	}
	/**
	 * 노드 정보를 받아 메뉴개체를 생성해 tempMenu넣 추가 한다.
	 * 
	 * @param tempMenu
	 * @param pNode
	 * @param PGID
	 * @param Title
	 * @param Page
	 * @param DIR
	 * @param Level
	 * @param IsLink
	 */
	private void addMenu(ArrayList tempMenu, MenuItem oM) {
		int PnodeIndex = getIndex(oM.getPrevNodeID(), tempMenu);
		MenuItem ParentNode = null;
		MenuItem newNode = null;
		String pDir = "";

		//logger.debug("T:" + oM.getPrevNodeID() + ", Index = " +  PnodeIndex);
		oM.setPrevNodeIndex(PnodeIndex);
		if (PnodeIndex < 0) {
			newNode = this.InitMenu(oM);
		} else {
			ParentNode = (MenuItem) tempMenu.get(PnodeIndex);

			if ("".equals(oM.getDir())) {
				pDir = ParentNode.getDir();
				if (reg_LASTSLASH.match(pDir)) {
					oM.setDir(pDir);
				} else {
					oM.setDir(pDir + "/");

				}
			} else {
				pDir = ParentNode.getDir();
				if (reg_LASTSLASH.match(pDir)) {
					oM.setDir(pDir + oM.getDir());
				} else {
					oM.setDir(pDir + "/" + oM.getDir());
				}
			}
			newNode = this.InitMenu(oM);


			// ParentNode에 Child 등록
			ParentNode.putChild(newNode);
			tempMenu.set(PnodeIndex, ParentNode);
		}
		tempMenu.add(newNode);
	}

	/**
	 * 현재 노드의 형제 노드를 구한다.
	 * 
	 * @param curIndex
	 * @return
	 */
	private MenuItem[] getSibiling(MenuItem oMenu) {
		int ParentIndex = -1;
		MenuItem oT;
		if (oMenu != null) {
			ParentIndex = oMenu.getPrevNodeIndex();
			oT = this.getMenu(ParentIndex);
			return oT.getChilds();
		}
		return null;
	}

	private MenuItem[] getSibiling(int curIndex) {
		MenuItem oT;
		int ParentIndex = -1;
		if (curIndex < 0) {
			return null;
		}

		oT = this.getMenu(curIndex);

		if (oT != null) {
			ParentIndex = oT.getPrevNodeIndex();
			oT = this.getMenu(ParentIndex);
			if (oT != null) {
				return oT.getChilds();
			}
		}
		return null;
	}

	/**
	 * 현재 노드의 자식 노드를 구한다.
	 * 
	 * @param curIndex
	 * @return 없으면 null;
	 */
	public MenuItem[] getChilds(MenuItem oMenu) {
		if (oMenu != null) {
			return oMenu.getChilds();
		} 
		return null;
	}

	private MenuItem[] getChilds(int ParentIndex) {
		MenuItem oT = this.getMenu(ParentIndex);
		if (oT != null) {
			return (oT.getChilds());
		} else {
			return null;
		}
	}
	/**
	 * 읽기 권한이 있는 자식 노드들을 리턴
	 * 
	 * @return
	 */
	public MenuItem[] getChilds(MenuItem oMenu, BaseHttpSession session) {
		MenuItem[] aMenu = oMenu.getChilds();
		List child = new ArrayList();
		if(aMenu != null) {
			for(int i = 0; i < aMenu.length; i++) {
				if(this._chkAuth_Menu(aMenu[i], session)) {
					child.add(aMenu[i]);
				}
			}
		}
		return (MenuItem[]) child.toArray(new MenuItem[0]);
	}
	public MenuItem getParent(MenuItem oT) {
		int i = oT.getPrevNodeIndex();
		return (i > -1) ? (MenuItem) aMenu.get(i) : null;
	}

	/**
	 * TopMenu를 리턴한다.
	 * 
	 * @param TopMenu
	 *            or Null
	 * @return
	 */
	public MenuItem getTopMenu(MenuItem oT) {
		if (oT == null)
			return null;
		if (oT.getLevel() < 3)
			return oT;
		else
			return getTopMenu(getParent(oT));
	}
	
	/**
	 * mMenu Path 정리
	 * @param oM
	 * @return
	 */
	private MenuItem InitMenu(MenuItem oM) {
		String page = oM.getPage();
		
		if(page!= null) {
			int lastIndex = page.lastIndexOf("?");
			if(lastIndex > 0 ) {
				page = page.substring(0, lastIndex);
			}
		}
		oM.setURI(page);
		oM.setURL(oM.getPage());
		if (!oM.isServlet()) {
			if (!MafUtil.empty(oM.getDir())) {
				//if (vPage.indexOf("/") == 0) {
				// HTTP:// 나 /로 시작 하면.
				if (reg_OUTER.match(page)) {
					oM.setURI(page);
					oM.setURL(oM.getPage());
				} else {
					//if (vDir.lastIndexOf("/") == (vDir.length()-1)) {
					if (reg_LASTSLASH.match(oM.getDir())) {
						oM.setURI(oM.getDir() + page);
						oM.setURL(oM.getDir() + oM.getPage());
					} else {
						if (MafUtil.empty(page)) {
							oM.setURI(oM.getDir());
							oM.setURL(oM.getDir());
						} else {
							oM.setURI(oM.getDir() +"/"+ page);
							oM.setURL(oM.getDir() +"/"+ oM.getPage());
						}
					}
				}
			}
		}
		return oM;
	}
	
	

	
	/**
	 * 유형별 Cache용 Map을 돌려 준다.
	 * @param bufferKey
	 * @return
	 */
	private Map getBufferMap(String bufferKey){
		
		Map nodeBuffer = (Map) this.bufferMap.get(bufferKey);
		
		if(nodeBuffer == null) {
			nodeBuffer = new HashMap();
			this.bufferMap.put(bufferKey, nodeBuffer);
		}
		return nodeBuffer;
	}
}
