/*
 * Created on 2006. 7. 6.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.context.support;

import java.util.Locale;

public class LocaleUtil {
	public static final String UNDEFINED_KEY = "???";

	public static final String UNDEFINED_BUNDLE = "????";
	
	public static String locale2String(Locale locale) {
		return locale.getLanguage();
	}
	
	public static String getNoBundleErrorString(String bundleName) {
		return UNDEFINED_BUNDLE + bundleName + UNDEFINED_BUNDLE;
	}
	public static String getNoMessageErrorString(String key) {
		return UNDEFINED_KEY + key + UNDEFINED_KEY;
	}
}

