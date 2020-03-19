/*
 * Created on 2006. 4. 6.
 *
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
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
	 * �� �޽����� �ѷ��� 
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
	 * �� Code Name �� �о�� 
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

