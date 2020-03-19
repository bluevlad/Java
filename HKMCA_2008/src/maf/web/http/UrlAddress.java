/*
 * Created on 2006. 10. 17
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.http;

import java.util.ArrayList;
import java.util.List;

import maf.MafUtil;
import maf.base.BaseObject;
import maf.lib.bean.PairBean;


/** 
 * URL을 입력 하기 위한 Object 
 * @author bluevlad
 *
 */
public class UrlAddress extends BaseObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List param = null;
	private StringBuffer baseUrl = new StringBuffer(20);
	private boolean isHrefed = false;
	
	
	public UrlAddress(String url){
		this.baseUrl.append(url);
		
	}
	/**
	 * link를 위한 url String을 만듬 
	 * - 한번만든 url을 다시 만들지 않기 처리 
	 *
	 */
	public String getHref() {
		if(!isHrefed) {
			makeFullUrl();
		}
		return baseUrl.toString();
	}
	
	/**
	 * parameter 값 추가 
	 * @param key
	 * @param value
	 */
	public void addParam(String key, Object value) {
		if(param == null) {
			param = new ArrayList();
		}
		param.add(new PairBean(key, MafUtil.getString(value)));
		isHrefed = false;
	}
	
	/**
	 * link를 위한 url String을 만듬 
	 * - 한번만든 url을 다시 만들지 않기 위함 
	 *
	 */
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
		isHrefed = true;
	}
	private String makeHref(PairBean param) {
		return param.getKey() + "=" + param.getValue();
	}
	
}

