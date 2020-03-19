/*
 * Created on 2006. 6. 20.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.context.i18n;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import maf.configuration.support.ConfigFileInfo;
import maf.context.NoSuchMessageException;
import maf.context.support.LocaleUtil;
import maf.util.StringUtils;
import maf.web.util.JavaScriptUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MafResourceBundle extends ConfigFileInfo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public static final String FMT_BUNDLE = MafResourceBundle.class.getName() + ".bundlename";
	
	private Log logger = LogFactory.getLog(this.getClass());
	private String bundleName = null;
	private String defaultLocale = null;

	
	// image type의 url 마지막에 "/" 포함 
	private String imagePath=null;
	// 설명 또는 comment
	private String desc=null;
	
	private Map resourceMap = null;
	/**
	 * @return Returns the bundleName.
	 */
	public String getBundleName() {
		return bundleName;
	}
	/**
	 * @param bundleName The bundleName to set.
	 */
	public void setBundleName(String bundleName) {
		this.bundleName = bundleName;
	}
	/**
	 * @return Returns the resourceMap.
	 */
	public final Map getResourceMap() {
		return resourceMap;
	}
	/**
	 * @param resourceMap The resourceMap to set.
	 */
	public void setResourceMap(Map resourceMap) {
		this.resourceMap = resourceMap;
	}
	/**
	 * @return Returns the defaultLocale.
	 */
	public String getDefaultLocale() {
		return defaultLocale;
	}
	/**
	 * @param defaultLocale The defaultLocale to set.
	 */
	public void setDefaultLocale(String defaultLocale) {
		this.defaultLocale = StringUtils.toLowerCase(defaultLocale);;
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
	 * @return Returns the imagePath.
	 */
	public String getImagePath() {
		return imagePath;
	}
	/**
	 * @param imagePath The imagePath to set.
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getMessage( String key, Locale locale ) throws NoSuchMessageException {
		String localeString = LocaleUtil.locale2String(locale);
		String lo = (localeString==null)?this.defaultLocale:localeString;

		MafResource resource =  (MafResource) resourceMap.get(key);
		if (resource != null) {
			MafResourceLocale rl =  resource.getMessage(lo);
		    if(MafResourceLocale.RL_TYPE_TEXT.equals(rl.getType())) {
		    	return rl.getValue();
		    } else {
		    	if(rl.isFilenameSpecified()) {
			    	return "<img src='"+ this.imagePath + rl.getFilename() +"' border='0' valign='middle' alt='" + JavaScriptUtils.javaScriptEscape(rl.getValue())+"' />";
		    	} else {
			    	return "<img src='"+ this.imagePath + key + "_" + lo + "." + rl.getType() +"' border='0' align='absmiddle' alt='" + JavaScriptUtils.javaScriptEscape(rl.getValue())+"' />";
		    	}
		    }
		} else {
			throw new NoSuchMessageException("NoSuchMessageException : bundleName=" + this.bundleName + " key = " + key);
		}
	}	
	

	
	public MafResourceLocale getMafResourceLocale( String key, Locale locale) throws NoSuchMessageException {
		String localeString = LocaleUtil.locale2String(locale);
		String lo = (localeString==null)?this.defaultLocale:localeString;
		MafResource resource =  (MafResource) resourceMap.get(key);
		if (resource != null) {
			MafResourceLocale mrl = resource.getMessage(lo);
			if (mrl == null) {
				throw new NoSuchMessageException(key);
			} else {
				mrl.setImagePath(this.imagePath);
				return mrl;
			}
		} else {
			throw new NoSuchMessageException(key);
		}
	}
	
	public void addMafResource(MafResource r) {
		if(resourceMap == null) {
			resourceMap = new HashMap();
		}
		resourceMap.put(r.getKey(), r);
	}
}

