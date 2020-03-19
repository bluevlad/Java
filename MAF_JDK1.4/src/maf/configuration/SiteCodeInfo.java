/*
 * Created on 2006. 4. 6.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maf.web.context.Sites;

public class SiteCodeInfo {
	Map allSiteListMap = new HashMap();
	Map allSiteMapMap = new HashMap();
	final public String  DEFAULT_SITE = "ROOT";
	
	public SiteCodeInfo(List codeList) {
		if (codeList != null) {


			Map listMap = null;
			List cList = null;
			

			
			// group, site 별 list 만들기.
			for (int i = 0; i < codeList.size(); i++) {
				SiteCodeDetailBean bean = (SiteCodeDetailBean) codeList.get(i);
				if (this.allSiteListMap.containsKey(bean.getGroupCD())) {
					listMap = (Map) this.allSiteListMap.get(bean.getGroupCD());
				} else {
					listMap = new HashMap();
//					System.out.println(bean.getGroupCD() + " added. ");
					this.allSiteListMap.put(bean.getGroupCD(), listMap);
				}
				if(listMap.containsKey(bean.getSite())) {
					cList = (List) listMap.get(bean.getSite());
				} else {
					cList = new ArrayList();
					listMap.put(bean.getSite(), cList);
				}
				cList.add(bean);
			}
			
			Map mapMap = null;
			Map cMap = null;			
			// group, site,  code_no 별 Map 만들기.
			for (int i = 0; i < codeList.size(); i++) {
				SiteCodeDetailBean bean = (SiteCodeDetailBean) codeList.get(i);
				if (this.allSiteMapMap.containsKey(bean.getGroupCD())) {
					mapMap = (Map) this.allSiteMapMap.get(bean.getGroupCD());
				} else {
					mapMap = new HashMap();
					this.allSiteMapMap.put(bean.getGroupCD(), mapMap);
				}
				if(mapMap.containsKey(bean.getSite())) {
					cMap = (Map) mapMap.get(bean.getSite());
				} else {
					cMap = new HashMap();
					mapMap.put(bean.getSite(), cMap);
				}
				cMap.put(bean.getCodeNo(), bean);
			}			
		}
	}
	
	/**
	 * 해당 Site 의 groupCd의 code List를 돌려준다.
	 * 만일 해당 site에 해당 groupcd가 없을 경우.
	 * @param site
	 * @param groupCd
	 * @return
	 */
	public final  List getCodeList(String groupCd , String site) {
		Map gMap = null;
		List rv = null;
		
		if (this.allSiteListMap.containsKey(groupCd)) {
			gMap = (Map) this.allSiteListMap.get(groupCd);
		}
		if (gMap != null) {
			if (gMap.containsKey(site)) {
				rv = (List) gMap.get(site);
			} else {
				rv = (List) gMap.get(Sites.ROOT_CODE_SITE);
			}
		}
		if (rv == null) {
			rv = Collections.EMPTY_LIST;
		}
		return rv;
		
	}

	/**
	 * group, site의 code의 정보를 가져온다.
	 * group에 site가 없을 경우 default site에서 가져온다.
	 * @param groupCd
	 * @param site
	 * @param codeNo
	 * @return
	 */
	public final CodeDetailBean getCodeInfo(String groupCd, String site, String codeNo) {
		Map gMap = null;
		Map cMap = null;
		
		if (allSiteMapMap.containsKey(groupCd)) {
			gMap = (Map) allSiteMapMap.get(groupCd);
		}		
		if (gMap != null) {
			if (gMap.containsKey(site)) {
				cMap = (Map) gMap.get(site);
			} else {
				cMap = (Map) gMap.get(DEFAULT_SITE);
			}
		}
		if (cMap != null) {
			return (CodeDetailBean) cMap.get(codeNo);
		} else {
			return null;
		}
	}

	public final Map getInternalMap(String gbn) {
		if ("LIST".equals(gbn)) {
			return this.allSiteListMap;
		} else {
			return this.allSiteMapMap;			
		}
	}
}
