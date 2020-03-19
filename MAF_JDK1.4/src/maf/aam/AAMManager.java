/*
 * Created on 2006. 08. 31
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.aam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import maf.MafProperties;
import maf.aam.bean.AAMBean;
import maf.aam.bean.AAMPgidRoles;
import maf.logger.Trace;
import maf.mdb.Mdb;
import maf.mdb.drivers.MdbDriver;
import maf.web.filter.RequestMonFilter;
import maf.web.mvc.beans.SiteInfo;
import maf.web.mvc.configuration.CmdConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AAMManager {
	private Log logger = LogFactory.getLog(AAMManager.class);
	private static Log  slogger = LogFactory.getLog(AAMManager.class);
	private static AAMManager  instance;
	/**
	 * SUPER관리자
	 */ 
	public static final String ROLE_KEY_ROOT = "ROOT";
	/**
	 * 로그인하면 가지는 기본 권한 
	 */
	public static final String ROLE_KEY_USER = "USER";
	/**
	 * SESSION없을때 가지는 권한 
	 */
	public static final String ROLE_KEY_GUEST = "GUEST";
	
	/**
	 * 권한을 넘겨 주는 키값 
	 */
//	public static final String AUTH_INFO_KEY = AAMManager.class + ".REQ_AAM";
	public static final String AUTH_INFO_KEY = "MAF_AAM";
	
	/**
	 * 읽기권한 코드
	 */
	public static final String AUTH_TYPE_READ = "R";
	/**
	 * 등록권한 코드
	 */
	public static final String AUTH_TYPE_CREATE = "C";
	/**
	 * 수정
	 */
	public static final String AUTH_TYPE_UPDATE = "U";
	/**
	 * 삭제 
	 */
	public static final String AUTH_TYPE_DELETE = "D";
	
	private AAMManager() {

	}

	public static synchronized AAMManager  getInstance() {
		if (instance == null) instance = new AAMManager();
		return instance;
	}
	/**
	 * Map of Pgid()+ob.getRole(), AAMBean 
	 * @param site
	 * @return
	 */
	public synchronized Map getSiteMenuAuth(String site) {

		AAMBean ob = null;
//		Map tmp= null;
		Map tmp_authMap = new HashMap();

		try {
			System.out.println("### " + this.getClass() + " menu initAuth start ### ");

//			List utypes = MstMenuDB.getUtype(oDb);
			AAMLoader aaml =MafProperties.getAAMLoader();
			List auth = null;
			if (aaml != null) {
				auth = aaml.getAuthAll( site);
			}
			if (auth != null) {
				String pgid_utype = null;
				for(int i = 0; i < auth.size(); i++ ) {
					ob = (AAMBean) auth.get(i);

					AAMPgidRoles prole = (AAMPgidRoles) tmp_authMap.get(ob.getPgid());
					if(prole == null) {
						prole = new AAMPgidRoles(ob.getPgid());
					}
					
					pgid_utype = ob.getPgid()+ob.getRole();
					tmp_authMap.put(pgid_utype, ob);
				}
			}

			System.out.println("### " + this.getClass() + " menu initAuth finish ### ");
		} catch (Exception e) {
			logger.error(Trace.getStackTrace(e));

		}

		return tmp_authMap;
	}
	
	/**
	 * 현재 request의 권한 정보를 가져온다. (null 없음)
	 * @param req
	 * @return
	 */
	public final static AAMBean getAAM(HttpServletRequest req) {
		AAMBean t = null;
		try {
			t = (AAMBean) req.getAttribute(AAMManager.AUTH_INFO_KEY);
		} catch( Exception ignore) {}
		
		if(t == null) {

			t = new AAMBean();
			SiteInfo site = (SiteInfo) req.getAttribute(RequestMonFilter.KEY_SITEINFO);
			t.setSite(site.toString());
		}
		return t;
		
	}
	
	public static boolean chkCmdAuth(HttpServletRequest req, CmdConfig method) {
		AAMBean authInfo = AAMManager.getAAM(req);
		return AAMManager.chkCmdAuth(authInfo, method);
		
	}
	
	public static boolean chkCmdAuth(AAMBean authInfo, CmdConfig method) {
		boolean chk = false;
		if(method == null) {
			chk = true;
		} else {
			// C,R,U,D / default R
			String authType = method.getAuthtype();
//			slogger.debug("authtype: " + authType + " authInfo :" + authInfo.toString() );
			if(AAMManager.AUTH_TYPE_CREATE.equals(authType)) {
				chk = authInfo.isAuth_c();
			} else if(AAMManager.AUTH_TYPE_UPDATE.equals(authType)) {
				chk = authInfo.isAuth_u();
			} else if(AAMManager.AUTH_TYPE_DELETE.equals(authType)) {
				chk = authInfo.isAuth_d();
			} else if(AAMManager.AUTH_TYPE_READ.equals(authType)) {
				chk = authInfo.isAuth_r();				
			} else {
				chk = true;
			}
		}
		
		return chk;
	}
}

