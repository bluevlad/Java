package maf.menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import maf.MafProperties;
import maf.MafUtil;
import maf.aam.AAMManager;
import maf.aam.bean.AAMBean;
import maf.aam.bean.MafRole;
import maf.base.BaseHttpSession;
import maf.base.BaseObject;
import maf.base.UserRoles;
import maf.context.support.LocaleUtil;
import maf.logger.Trace;
import maf.mdb.DBResource;
import maf.web.context.MafConfig;
import maf.web.util.HTMLUtil;
import modules.common.jason.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.regexp.RE;


/**
 * 2005/11/17 chache ����߰�

 * @author bluevlad
 *
 */
public abstract class BaseTreeMenu extends BaseObject{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final String BUFFER_KEY_LOGIN  = "HTML_NAVI_ON";
	private final String BUFFER_KEY_LOGOFF  = "HTML_NAVI_OFF";
	private final String BUFFER_KEY_PGID  = "PGID";
	public  final String TREE_MENU_HOME_ID  = "HOME";
	
	private ArrayList aMenu = null;
//	private Map menuMap = null;
	private Map bufferMap = null;
	private Map authMap = null;
	private String site;
//	private int callCount = 0;
	private final String servlet_path = MafConfig.getContextPath() + MafProperties.MVC_SERVLET_PATH;
	
	Log logger = LogFactory.getLog(BaseTreeMenu.class);

	// URI���� http://�� /�� �����ϴ°��� ���丮�� Ÿ�� ����.
	private static final RE reg_OUTER = new RE("^http://|^/|^javascript:", RE.MATCH_CASEINDEPENDENT);

	private static final RE reg_LASTSLASH = new RE("\\/$");

	
	protected BaseTreeMenu(String vSite) {
		this.aMenu = new ArrayList();
		this.site = vSite;
//		reg_OUTER.setMatchFlags(RE.MATCH_CASEINDEPENDENT);
		bufferMap = new HashMap();
		init();
	}
	
	/**
	 * @return template_path�� �����մϴ�.
	 */
	public String getServlet_path() {
		return servlet_path;
	}

	/**
	 * DB���� Data�� ���� ������
	 * 
	 */
	public void DataReset() {
		init();
	}

	/**
	 * PGID�� ��ġ�� ArrayList������ Index�� ã���� 
	 * �ܺο����� aMenu�� �˻� ������ DataReset �� init������ tempArray���� ã��
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
	 * PGID�� �̿� Index �� ���� �´�.
	 * 
	 * @param vPGID
	 * @return
	 */
	private int getIndex(String vPGID) {
		return getIndex(vPGID, aMenu);
	}

	/**
	 * Menu ������Ʈ�� �Ѱ���
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
	 * PGID�� �ش��ϴ� �޴��� �����´�.
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
	 * ��ü Menu�� �� aMenu�� length�� �Ѱ���
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
	 * URI �������� index���� �Ѱ��� ���� ������ ������ 
	 * Directory �������� �ϳ��� Parent�� �ö� ���� ���� �´�. 
	 * ���ų� ������ -1�� ������
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
		
		// Menu Buffer �̿� ����
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
			// ������ ���丮 ���� �ϳ��� �ö� 
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
	 * URI �������� ���� PGID�� ����.
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
	 * FindNode ���ο� ������� ã��
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
	 * �׺� ���̼��� �Ѱ���(HTML)
	 * 
	 * @param oT :
	 *            ���� �޴�
	 * @return
	 */
	public String genHtmlNavigator(MenuItem oT, HttpServletRequest req) {
		
		String buffer_key = null;
		String rv = null;
		if(isLogin(req)) {
			buffer_key= BUFFER_KEY_LOGIN;
		} else {
			buffer_key= BUFFER_KEY_LOGOFF;
		}
		Map nodeBuffer = getBufferMap(buffer_key);
		rv = (String) nodeBuffer.get(getLang(oT,req));
		if (rv == null) {
			if (oT == null) {
				return "<a href='/' >HOME</a> &gt; ";
			}
			StringBuffer sNavi = new StringBuffer();
			// if(Root == null) Root = "";
			MenuItem[] aParents = getParents(oT);
			if (aParents != null) {
				for (int i = 0; i < aParents.length - 1; i++) {
					sNavi.append(genMenuHref(aParents[i], "N", req));
					sNavi.append(" &gt; ");
				}
				sNavi.append("<strong>" + oT.getTitle(req) + "</strong>");
			}
			rv =sNavi.toString();
			nodeBuffer.put(getLang(oT,req), rv);
			//logger.debug(buffer_key + " no hit ");
		}
		return rv;
	}
	/**
	 * �׺� ���̼��� �Ѱ���(items)
	 * 
	 * @param oT :
	 *            ���� �޴�
	 * @return
	 */
	public MenuItem[] genHtmlNavigatorItems(MenuItem oT, HttpServletRequest req) {
		
		MenuItem[] aParents = null;
		String buffer_key = null;
		
		
		if(isLogin(req)) {
			buffer_key= BUFFER_KEY_LOGIN;
		} else {
			buffer_key= BUFFER_KEY_LOGOFF;
		}
		Map nodeBuffer = getBufferMap(buffer_key );
		aParents = (MenuItem[]) nodeBuffer.get(getLang(oT,req));
		
		if (aParents == null) {
			if (oT == null) {
				return null;
			}

			aParents = getParents(oT);

			nodeBuffer.put(getLang(oT,req), aParents);

		}
		return aParents;
	}
	/**
	 * �׺� ���̼��� �Ѱ���
	 * 
	 * @param oT :
	 *            ���� �޴�
	 * @return
	 */
	public String genHtmlNavigatorNoHref(MenuItem oT, HttpServletRequest req) {
		
		String buffer_key = null;
		String rv = null;

			buffer_key= BUFFER_KEY_LOGOFF;

		Map nodeBuffer = getBufferMap(buffer_key);
		rv = (String) nodeBuffer.get(getLang(oT,req) );
		if (rv == null) {

			StringBuffer sNavi = new StringBuffer();
			// if(Root == null) Root = "";
			MenuItem[] aParents = getParents(oT);
			if (aParents != null) {
				for (int i = 0; i < aParents.length - 1; i++) {
					if(! TREE_MENU_HOME_ID.equals(aParents[i].getPgid() )) {
						sNavi.append(aParents[i].getTitle(req));
						sNavi.append(" &gt; ");
					}
				}
				sNavi.append("<strong>" + oT.getTitle(req) + "</strong>");
			}
			rv =sNavi.toString();
			nodeBuffer.put(getLang(oT,req), rv);
			//logger.debug(buffer_key + " no hit ");
		}
		return rv;
	}
	
	/**
	 * ��� �޴��� HTML�� ����
	 * @param PGID
	 * @param islogin
	 * @return
	 */
	public String genHtmlTopMenu(String PGID, HttpServletRequest req) {
		
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
				sNavi.append(genMenuHref(oT, "T", req));
				q++;
			}
		}
		return sNavi.toString();
	}

	
	/**
	 * oT�޴��� ������ aHref�� ����
	 * 
	 * @param oT
	 * @param mType :
	 *            T:Top�޴���, L:LMenu��, N: Navigation ��
	 * @return
	 */
	public String genMenuHref(MenuItem oT, String mType, HttpServletRequest req) {
		final String BUFFER_KEY_LOGIN  = "HTML_MHREF_ON_";
		final String BUFFER_KEY_LOGOFF  = "HTML_MHREF_OFF_";
		
		String buffer_key = null;
		String rv = null;
		if(isLogin(req)) {
			buffer_key= BUFFER_KEY_LOGIN+mType;
		} else {
			buffer_key= BUFFER_KEY_LOGOFF+mType;;
		}
		Map nodeBuffer = getBufferMap(buffer_key);
		rv = (String) nodeBuffer.get(getLang(oT,req));
		if (rv == null) {
			rv =  _genMenuHref(oT, "", mType, null, req);
			nodeBuffer.put(getLang(oT,req),rv);
		}
		return rv;
	}
	
	public String genMenuHref(MenuItem oT, String mType, String target, HttpServletRequest req) {
		final String BUFFER_KEY_LOGIN  = "HTML_MHREF_ON_";
		final String BUFFER_KEY_LOGOFF  = "HTML_MHREF_OFF_";
		
		if(oT == null) {
			return "";
		} else {
			String buffer_key = null;
			String buffer_key2 = null;
			String rv = null;
			if(isLogin(req)) {
				buffer_key= BUFFER_KEY_LOGIN+mType;
			} else {
				buffer_key= BUFFER_KEY_LOGOFF+mType;;
			}
			Map nodeBuffer = getBufferMap(buffer_key);
			buffer_key2 = oT.getPgid() +"|" + mType +"|"+ target + "," + getLang2(req) ;
			rv = (String) nodeBuffer.get(buffer_key);
			if (rv == null) {
				rv =  _genMenuHref(oT, "", mType, target, req);
				nodeBuffer.put(buffer_key2,rv);
			}
			return rv;
		}
	}

	private String _genMenuHref(MenuItem oT, String Root, String mType, String starget, HttpServletRequest req) {
		StringBuffer sH = new StringBuffer(20);
		String target = null;
		String uri = null;
		if (oT == null)
			return "";
		if (("T".equals(mType) && oT.isTopMenu()) || ("L".equals(mType) && oT.isLeftMenu()) || ("N".equals(mType))) {
			if (oT.isLink()) {
				if (isLogin(req) || !oT.isSession_chk()) {
					if (oT.isServlet()) {
						uri = MafConfig.getContextPath() + oT.getURL();
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

				return HTMLUtil.getHtmlAtag(sH.toString(), oT.getTitle(req), target);
			} else {
				return oT.getTitle(req);
			}
		} else {
			return "";
		}
	}
	
	public String genMenuUri(MenuItem oT,  HttpServletRequest req) {
		StringBuffer sH = new StringBuffer(20);

		String uri = null;
		if (oT == null)
			return "";
			if (oT.isLink()) {
				if (isLogin(req) || !oT.isSession_chk()) {
					if (oT.isServlet()) {
						uri = MafConfig.getContextPath() + oT.getURL();
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
				return oT.getTitle(req);
			}
		} else {
			return "";
		}
	}
	/**
	 * ���� ������ ���� �迭�� �Ѱ���(�Ҿƹ���, �ƹ���, ��)
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
	 * ���� ������ ���� �迭�� �Ѱ���(�Ҿƹ���, �ƹ���, ��)
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
	 * �����ͺ��̽����� �����͸� ������ aMenu�� �ִ´�. �����ÿ��� �������� �״�� ������ ����
	 * 
	 */
	private synchronized void init() {
		boolean chk = false;
		ArrayList tempMenu = new ArrayList();
		final String sql = "SELECT SITE, PGID, title,  " +
		"       PAGE, SEQ, PNODEID, " +
		"       ISUSE, TARGET, DIR, LEVEL as \"LVL\", ISLINK, IS_TMENU, IS_LMENU, " +
		"       SESSION_CHK, ADMIN_USN, STYLE, isservlet, is_sitemap, " +
		"       contact_email, contact, help_file, LAYOUT, messagekey " +
		" FROM maf_menu  " +
		" where ISUSE = 'T'  " +
		" START WITH PGID = '"+TREE_MENU_HOME_ID+"' and site = :site " +
		" CONNECT BY PRIOR PGID = PNODEID and prior site = site " +
		" order siblings by seq, PGID ";

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
					oM.setMessagekey(rs.getString("messagekey"));
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
				this.authMap = AAMManager.getInstance().getSiteMenuAuth(site);

				this.aMenu = tempMenu;
			}
			cleareBufferMap();
			
			logger.info(this.site + " Menu Init Ok!!");
		} else {
			logger.error(this.site + "Menu Init Failure!!!");
		}
	}

	/**
	 * ����(�б�) ���ɿ��� Ȯ�� 
	 * @param pgid
	 * @param utype
	 * @return
	 */
	public boolean chkAuth_READ(String pgid, BaseHttpSession session) {
		if (pgid == null) {
			return true;
		}
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
				//logger.debug("role chk");
				UserRoles uroles = session.getUserRole();
				if (uroles != null) {
					List t = uroles.getRoleList();
					if (t != null) {
						for (int i = 0; i < t.size(); i++) {
							MafRole r = (MafRole) t.get(i);
//							logger.debug(i + "." + r.getName() + ", pgid :" + pgid +  " , roleid:"+ r.getId());
							AAMBean oa = (AAMBean) authMap.get(pgid + r.getId());
							if (oa != null) {
								if (oa.isAuth_r()) {
//									logger.debug(i + "." + r.getName() + ", pgid :" + pgid +  " , roleid:"+ r.getId() + " T");
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
	 * pgid �Ϻο� ����(�б�) ���ɿ��� Ȯ�� 
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
	 *���� �޴� �� ������ �б� ������ ������ True
	 * @param oMenu
	 * @param session
	 * @return
	 */
	private boolean _chkAuth_Menu(MenuItem oMenu, BaseHttpSession session) {
		boolean chk = false;
		String pgid = null;
		if (oMenu != null) {
			pgid = oMenu.getPgid();
			chk = this.chkAuth_READ(pgid, session);
			if (!chk && oMenu.hasChild()) {
				MenuItem[] aMenu = oMenu.getChilds();
				for(int i = 0; i < aMenu.length; i++) {
					if(this._chkAuth_Menu(aMenu[i], session)) {
						return true;// �б� ������ �ϳ��� ������ return;
					}
				}
			}
		}
		return chk;
	}
	
	/**
	 * ���� ������ ������ �����´�.
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
			// ���۰����� ROOT�� ��� �������� ������ ���� 
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
	 * ��� ������ �޾� �޴���ü�� ������ tempMenu�� �߰� �Ѵ�.
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


			// ParentNode�� Child ���
			ParentNode.putChild(newNode);
			tempMenu.set(PnodeIndex, ParentNode);
		}
		tempMenu.add(newNode);
	}

	/**
	 * ���� ����� ���� ��带 ���Ѵ�.
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

//	private MenuItem[] getSibiling(int curIndex) {
//		MenuItem oT;
//		int ParentIndex = -1;
//		if (curIndex < 0) {
//			return null;
//		}
//
//		oT = this.getMenu(curIndex);
//
//		if (oT != null) {
//			ParentIndex = oT.getPrevNodeIndex();
//			oT = this.getMenu(ParentIndex);
//			if (oT != null) {
//				return oT.getChilds();
//			}
//		}
//		return null;
//	}

	/**
	 * ���� ����� �ڽ� ��带 ���Ѵ�.
	 * 
	 * @param curIndex
	 * @return ������ null;
	 */
	public MenuItem[] getChilds(MenuItem oMenu) {
		if (oMenu != null) {
			return oMenu.getChilds();
		} 
		return null;
	}

//	private MenuItem[] getChilds(int ParentIndex) {
//		MenuItem oT = this.getMenu(ParentIndex);
//		if (oT != null) {
//			return (oT.getChilds());
//		} else {
//			return null;
//		}
//	}

	/**
	 * �б� ������ �ִ� �ڽ� ������ ����
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
	 * TopMenu�� �����Ѵ�.
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
	
	public String getTopMenuJson() {
		
		return this.getTopMenuJson(null,null);
	}
	
	public String getTopMenuJson(BaseHttpSession session, HttpServletRequest req) {
		JSONArray json = new JSONArray();
		MenuItem home = this.getMenu(TREE_MENU_HOME_ID);
		
		MenuItem[] childs = null;
		if(session == null) {
			childs = home.getChilds();
		} else {
			 childs = this.getChilds(home,session);
		}
		for (int i = 0 ; i < childs.length; i++) {
			json.add(childs[i].getJson(req));
		}
		
		return json.toString();
	}
	public MenuItem[] getTopMenus(BaseHttpSession session, HttpServletRequest req) {
		
		MenuItem home = this.getMenu(TREE_MENU_HOME_ID);
		
		MenuItem[] childs = null;
		childs = this.getChilds(home,session);
		return childs;
	}
	
	/**
	 * mMenu Path ����
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
				// HTTP:// �� /�� ���� �ϸ�.
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
	 * ������ Cache�� Map�� ���� �ش�.
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
	
	/**
	 * ���� ĳ�� ���� 
	 *
	 */
	public void cleareBufferMap() {
		bufferMap.clear();
	}
	
	/**
	 * requst ���� lang ���� �����´�.
	 * @param req
	 * @return
	 */
	private String getLang(MenuItem oT, HttpServletRequest req) {
		return oT +"," + LocaleUtil.getLocaleString(req);
	}
	
	private String getLang2( HttpServletRequest req) {
		return  LocaleUtil.getLocaleString(req);
	}
	/**
	 * request ������ �̿� login ���� Ȯ�� 
	 * @param req
	 * @return
	 */
	private boolean isLogin(HttpServletRequest req) {
		return false;
	}
}
