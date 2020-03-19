/*
 * Created on 2006. 10. 17
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.http;

import java.util.ArrayList;
import java.util.List;

import maf.base.BaseObject;
import maf.lib.bean.PairBean;

public class UrlAddress extends BaseObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List param = null;
	private StringBuffer baseUrl = new StringBuffer(20);
	private boolean isHref = false;
	public UrlAddress(String url){
		this.baseUrl.append(url);
	}
	
	public String getHref() {
		if(!isHref) {
			makeFullUrl();
		}
		return baseUrl.toString();
	}
	
	public void addParam(String key, String value) {
		if(param == null) {
			param = new ArrayList();
		}
		param.add(new PairBean(key, value));
		isHref = false;
	}
	
	private void makeFullUrl() {
		if(param != null && param.size() > 0 ) {
			if(baseUrl.indexOf("?")<1) {
				baseUrl.append("?");
			}
			for(int i = 0 ; i < param.size(); i ++) {
				baseUrl.append("&");
				baseUrl.append(makeHref((PairBean)param.get(i)));
			}
		}
		isHref = true;
	}
	private String makeHref(PairBean param) {
		return param.getKey() + "=" + param.getValue();
	}
	
}

