/*
 * Created on 2006. 4. 11.
 * tableName.Column
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.configuration;

import java.util.HashMap;
import java.util.Map;

import miraenet.MiConfig;

public class TableDictionary {
	Map siteMap = new HashMap();
	
	public void addSite(String site, Map codeMap){
		if (site != null) {
			siteMap.put(site, codeMap);
		}
	}
	
	public  Map getCodeMap (String site) {
		return (Map) siteMap.get(site);
	}
	
	public  TableDictionaryBean getInfo (String site, String key) {
		Map tmpMap = null;
//		Logging.log(this.getClass(), "site = "+ site + ", key = " + key);
		if(siteMap.containsKey(site)) {
			tmpMap = (Map) siteMap.get(site);
			if(tmpMap != null) {
				if(!tmpMap.containsKey(key)) {
					tmpMap = null;
				}
			}
		}
		if(tmpMap == null) {
			tmpMap = (Map) siteMap.get(MiConfig.DEFAULT_SITE);
		}
		if (tmpMap != null) {
			return (TableDictionaryBean) tmpMap.get(key);
		} else {
			return null;
		}
	}
	
	public boolean containsKey(String site) {
		return siteMap.containsKey(site);
	}
}

