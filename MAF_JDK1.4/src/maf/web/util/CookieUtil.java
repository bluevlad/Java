package maf.web.util;

/**
 * 
 * CookieUtil.java
 * 
 * 쿠키 객체를 반환/쿠키의 값을 반환
 *  @ author : 미래넷 @ create-date : 2001-08-21 @ modify-date : 2001-08-21
 *  
 */

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.oreilly.servlet.Base64Decoder;
import com.oreilly.servlet.Base64Encoder;

/**
 * Cookie 관련 함수 모음 
 * @author goindole
 *
 */
public class CookieUtil {
    
	/**
	 * Cookie의 값을 String 으로 받아 옴
	 * @param req
	 * @param name
	 * @return
	 */
    public static String getValue(HttpServletRequest req, String name) {
        Cookie[] cookies = req.getCookies();
        if (cookies == null) {
            return "";
        }
        String value = "";
        for (int i = 0; i < cookies.length; i++) {
            //logger.debug("CookieName :"+cookies[i].getName());
            if (name.equals(cookies[i].getName())) {
            	try {
            		value = java.net.URLDecoder.decode(cookies[i].getValue(), req.getCharacterEncoding());
            	} catch ( Throwable e) {
            		Log logger = LogFactory.getLog(CookieUtil.class);
                    logger.error(e.getMessage());
            	}
            	break;
            }

        }
        return value;
    }

    /**
     * Cookie를 돌려줌 
     * java.net.URLDecoder.decode(cookies[i].getValue(), req.getCharacterEncoding()); 으로 값은 전환해 사용
     * @param req
     * @param name
     * @return
     */
    public static Cookie getCookie(HttpServletRequest req, String name) {

        Cookie[] cookies = req.getCookies();
        if (cookies == null)
            return null;
        for (int i = 0; i < cookies.length; i++) {
            //logger.debug("CookieName :"+cookies[i].getName());
            if (name.equals(cookies[i].getName()))
                return cookies[i];
        }
        return null;
    }

    /**
     * 암호화된 쿠키를 복호화해서 리턴
     * <p>
     * 
     * @author miraenet
     *             <p>
     * @since 2004.01.05
     */
    public static String getEncValue(HttpServletRequest req, String key) {
        String value = getValue(req, key);
        if (value != null) {
            try {
                value = Base64Decoder.decode(URLDecoder.decode(value, "UTF-8"));
            } catch (Exception e) {
                value = null;
            }
        }
        return value;
    }

    /**
     * 쿠키를 암호화해서 저장
     * <p>
     * 
     * @author miraenet
     *             <p>
     * @since 2004.01.05
     */
    public static void setEncValue(HttpServletResponse res, String key, String value, String path, String domain) {
        try {
            Cookie c = null;

            if (key != null && value != null) {
                c = new Cookie(key, Base64Encoder.encode(URLEncoder.encode(value, "UTF-8")));
                if (path == null) {
                    path = "/";
                }
                c.setPath(path);
                if (domain != null) {
                    c.setDomain(domain);
                }
                c.setMaxAge(-1);
                res.addCookie(c);

            }
        } catch (Exception e) {

        }
    }

    /**
     * 쿠키에 값을 저장한다.
     * 
     * @param res
     * @param key
     * @param value
     */
    public static void setCookieValue(HttpServletResponse res, String key, String value) 
    throws MafException{
    	try {
        setCookieValue(res, key, value, null, null);
    	} catch (Exception e) {
			throw new MafException("UnsupportedEncodingException !!!", e);
		}
    }

    /**
     * 쿠키에 값을 저장한다.
     * 
     * @param res
     * @param key
     * @param value
     * @param path
     * @param domain
     */
    public static void setCookieValue(HttpServletResponse res, String key, String value, String path, String domain) {

        if (key != null) {
            try {
            	value = java.net.URLEncoder.encode(value, "8859_1");
                Cookie c = new Cookie(key, value);
	            c.setMaxAge(-1);
	            if (path == null) {
	                path = "/";
	            }
	            c.setPath(path);
	            if (domain != null) {
	                c.setDomain(domain);
	            }
	            res.addCookie(c);
            } catch (Exception e) {
            	Log logger = LogFactory.getLog(CookieUtil.class);
                logger.error(e.getMessage());
            }
            //logger.debug("setValue:" + key + "|"+value+"|"+path+"|"+domain);
        }
    }

    /**
     * 특정 Key 값의 쿠키만 삭제
     * <p>
     * 
     * @param req
     * @param res
     * @param name
     */
    public static void removeCookie(HttpServletRequest req, HttpServletResponse res, String name) {
        Cookie[] cookies = req.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            Cookie thiscook = cookies[i];
            if (thiscook.getName().equals(name)) {
                thiscook.setPath("/");
                thiscook.setMaxAge(0);
                thiscook.setValue("");
                res.addCookie(thiscook);
                break;
            }
        }
    }

    /**
     * 모든 쿠키값 삭제
     * <p>
     * 
     * @param req
     * @param res
     */
    public static void removeAllCookie(HttpServletRequest req, HttpServletResponse res) {
        Cookie[] cookies = req.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            Cookie thiscook = cookies[i];
            thiscook.setPath("/");
            thiscook.setMaxAge(0);
            res.addCookie(thiscook);
        }
    }
}