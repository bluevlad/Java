/*
 * Created on 2006. 7. 6.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.context.support;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import maf.web.context.MafConfig;

public class LocaleUtil {
	public static final String UNDEFINED_KEY = "???";

	public static final String UNDEFINED_BUNDLE = "????";
	
	/**
	 * 언어 부문 2자리만 돌려준다.
	 * @param locale
	 * @return
	 */
	public static String locale2String(Locale locale) {
		if(locale != null) {
			return locale.getLanguage();
		} else {
			return null;
		}
	}
	
	public static String getNoBundleErrorString(String bundleName) {
		return UNDEFINED_BUNDLE + bundleName + UNDEFINED_BUNDLE;
	}
	
	public static String getNoMessageErrorString(String bundleName, String key) {
		return UNDEFINED_KEY + bundleName + "|" + key + UNDEFINED_KEY;
	}
	
	public static String getNoMessageErrorString(String bundleName, String key, String locale) {
		return UNDEFINED_KEY + bundleName + "|" + key + "|" + locale + UNDEFINED_KEY;
	}
	
	public static String getNoMessageErrorString(String bundleName, String key, Locale locale) {
		return UNDEFINED_KEY + bundleName + "|" + key + "|" + locale + UNDEFINED_KEY;
	}
	/**
	 * request 의 Locale 을 돌려 준다.
	 * @param req
	 * @return
	 */
	public static Locale getLocale(HttpServletRequest req) {
		return MafConfig.getLocaleResolver().resolveLocale(req);
	}
	/**
	 * request 의 Locale 을 언어 부문 2자리만으로 돌려 준다.
	 * @param req
	 * @return
	 */
	public static String getLocaleString(HttpServletRequest req) {
		return locale2String(getLocale(req));		
	}
}

