/*
 * Created on 2006. 6. 20.
 *
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
 */
package maf.context.i18n;

import java.util.HashMap;
import java.util.Map;

import maf.base.BaseObject;
import maf.context.NoSuchMessageException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * MafResouce�� �޽��� ����ü�� ������ bundle-key-locale:value�� ������ ���� 
 * @author goindole
 *
 */
public class MafResource extends BaseObject  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final Log logger = LogFactory.getLog(this.getClass());
	private String key = null;
	private String desc=null;
	private MafResourceLocale rl = null;
	// ���� �Ǵ� comment

	
	public MafResource() {
		this.rl = new MafResourceLocale();
	}
	
	private Map localeValueMap = null;
	/**
	 * @return Returns the key.
	 */
	public final String getKey() {
		return key;
	}
	/**
	 * @param key The key to set.
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.rl.setType(type);
	}
	/**
	 * @param type The type to set.
	 */
	public final String getType() {
		return this.rl.getType();
	}
	/**
	 * @param value The value to set.
	 */
	public void setValue(String value) {
		rl.setValue(value);

	}
	/**
	 * @param type The type to set.
	 */
	public final String getValue() {
		return this.rl.getValue();
	}	
	/**
	 * @return Returns the desc.
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * @param desc The desc to set.
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	/**
	 * @param filename The filename to set.
	 */
	public void setFilename(String filename) {
		this.rl.setFilename(filename);
	}
	public String getFilename() {
		return rl.getFilename();
	}
	
	/**
	 * @@deprecated
	 * Staus�� 
	 * @return
	 */
	public final Map getLocaleValueMap() {
		return this.localeValueMap;
	}
	
	public void addResourceLocale(MafResourceLocale rl) {
		if(localeValueMap == null) {
			localeValueMap = new HashMap();
		}
		localeValueMap.put(rl.getLocale(), rl);
	}
	
	
	public final MafResourceLocale getMessage(String locale) throws NoSuchMessageException {

		if(localeValueMap != null && localeValueMap.containsKey(locale)) {
			return (MafResourceLocale)localeValueMap.get(locale);
		} else {
			return this.rl;
		}

	}
	


	

}

