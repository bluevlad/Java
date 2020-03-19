/*
 * MyHttpSessionListener.java, @ 2005-03-11
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.listener;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import maf.MafProperties;
import maf.base.BaseHttpSession;
import maf.web.session.MyHttpSession;
import maf.web.session.SessionPool;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * @author goindole
 *
 */
public class MyHttpSessionListener implements HttpSessionListener , HttpSessionAttributeListener, HttpSessionBindingListener  {
	Log logger = LogFactory.getLog(getClass());
    
    /**
     *  
     */
    public MyHttpSessionListener() {
        super();
    }

    /**
     * Sessino이 생성 되었을때
     */
    public void sessionCreated(HttpSessionEvent arg0) {

    }

    /**
     * Sessino이 없어졋을때.
     * session pool에서 해당 session정보도 제거 
     */
    public void sessionDestroyed(HttpSessionEvent arg0) {
    	HttpSession s = arg0.getSession();
		Object x = s.getAttribute(MyHttpSession.HTTP_SESSION_KEY);
		if ( x == null) {
	    	if(MafProperties.SESSION_POOL_YN) {
		        SessionPool.remove(s);
	    	}
	        logger.debug( "<<<<<<<<<<< Session & mSession Destroied, " + new Date(s.getCreationTime()) + "," + (s.getLastAccessedTime()- s.getCreationTime()));
		}
    }

    /**
     * session에 mSession이 추가됨 
     * login 했을경우
     *  
     */
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		HttpSession s = arg0.getSession();
		Object x = s.getAttribute(MyHttpSession.HTTP_SESSION_KEY);

		if(x instanceof BaseHttpSession) {
	        if(MafProperties.SESSION_POOL_YN) {
	        	SessionPool.add(s, (BaseHttpSession) x );
	        }
	        
	        logger.debug( ">>>>>>>>>>> attribute mSession Added " + new Date(s.getCreationTime()) + ", " + s.getMaxInactiveInterval() );
		}
		
	}

	/**
	 * sessoin에서 mSession이 제거됨 
	 * logout의 경우 
	 */
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		HttpSession s = arg0.getSession();
		Object x = s.getAttribute(MyHttpSession.HTTP_SESSION_KEY);
		if ( x == null) {
	    	if(MafProperties.SESSION_POOL_YN) {
		        SessionPool.remove(s);
	    	}
	        logger.debug( "<<<<<<<<<<< attribute mSession Removed, " + new Date(s.getCreationTime()) + "," + (s.getLastAccessedTime()- s.getCreationTime()));
		}
	}

	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		
		
	}

	public void valueBound(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
//		logger.debug("valueBound");
		
	}

	public void valueUnbound(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
//		logger.debug("valueUnbound");
		
	}
    
}
