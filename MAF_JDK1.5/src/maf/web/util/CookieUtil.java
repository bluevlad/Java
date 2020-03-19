package maf.web.util;

/**
 * 
 * CookieUtil.java
 * 
 * ��Ű ��ü�� ��ȯ/��Ű�� ���� ��ȯ
 *  @ author : �̷��� @ create-date : 2001-08-21 @ modify-date : 2001-08-21
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
 * Cookie ���� �Լ� ���� 
 * @author goindole
 *
 */
public class CookieUtil {
    
	/**
	 * Cookie�� ���� String ���� �޾� ��
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
     * Cookie�� ������ 
     * java.net.URLDecoder.decode(cookies[i].getValue(), req.getCharacterEncoding()); ���� ���� ��ȯ�� ���
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
     * ��ȣȭ�� ��Ű�� ��ȣȭ�ؼ� ����
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
     * ��Ű�� ��ȣȭ�ؼ� ����
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
     * ��Ű�� ���� �����Ѵ�.
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
     * ��Ű�� ���� �����Ѵ�.
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
     * Ư�� Key ���� ��Ű�� ����
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
     * ��� ��Ű�� ����
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