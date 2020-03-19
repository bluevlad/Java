/*
 * Created on 2006. 11. 10
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.mvc.view.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maf.util.StringUtils;

public class ViewerInfoBean {
	private String filename = null;
	private String title = null;
	private List keyList = null;
	private Map fields = null;
	
	public ViewerInfoBean() {
		keyList = new ArrayList();
		fields = new HashMap();
	}
	public void addField(ViewerFieldBean b) {

		if(b != null) {
			String key = "k" + keyList.size();
			fields.put(key, b);
			keyList.add(key);
		}
	}
	
	public String[] getFieldKeySet() {
		if (fields == null) {
			return null;
		} else {
			return StringUtils.toStringArray(keyList);
		}
	}
	
	public ViewerFieldBean getField(String fieldid) {
		if (fields == null) {
			return null;
		} else {
			return (ViewerFieldBean) fields.get(fieldid);
		}
		
	}
	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}


	
}

