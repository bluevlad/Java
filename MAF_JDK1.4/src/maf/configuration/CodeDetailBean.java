/*
 * Created on 2006. 4. 6.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.configuration;

import java.util.HashMap;
import java.util.Map;
import maf.MafUtil;

public class CodeDetailBean extends CodeDetailLangBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map langMap = null;
	
	/**
	 * 언어별 메시지를 뿌려줌 
	 * @param locale
	 * @param localeString
	 */
	public void addLocaleString(String locale, String localeString) {
		if(! MafUtil.empty(localeString)) {
			if(langMap == null ) {
				langMap = new HashMap();
			}
			langMap.put(locale, localeString);
		}
	}
	
	/** 
	 * 언어별 Code Name 을 읽어옴 
	 * @param locale
	 * @return
	 */
	public String getCodeName(String locale) {
		
		if(langMap != null && langMap.containsKey(locale)) {
			return (String) langMap.get(locale);
		} else {
			return this.getCodeName();
		}
	}
	
	
}

