/*
 * Created on 2006. 7. 3.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.mvc.beans;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import maf.context.exceptions.NoSuchBundleException;
import maf.context.exceptions.NoSuchLocaleMessageException;
import maf.context.exceptions.NoSuchMessageException;
import maf.context.i18n.MafResourceStore;
import maf.context.support.LocaleUtil;
import maf.web.context.MafConfig;

/**
 * mvc 모델에서 control수행 후 view 단에 미시지를 전달할 메시지의 구조체 
 * @author goindole
 *
 */
public class ResultMessage {
	private String bundleName = null;
	private String key = null;
	private String message = null;
	private Object[] params = null;
	private final Log logger = LogFactory.getLog(ResultMessage.class);
	
	public ResultMessage() {
	}
	
	/**
	 * 단순 메시지.
	 * @param message
	 */
	public ResultMessage(String message) {
		this.message = message;
	}	
	public ResultMessage(String bundleName, String key) {
		this.key = key;
		this.bundleName = bundleName;

	}
	public ResultMessage(String bundleName, String key, Object[] params) {
		this.key = key;
		this.bundleName = bundleName;
		this.params= params;
	}
	public ResultMessage(String bundleName, String key, Object param) {
		this.key = key;
		this.bundleName = bundleName;
		List rv = new ArrayList();
		rv.add(param);
		this.params= (Object[]) rv.toArray(new Object[0]);
		
	}
	
	public ResultMessage(String bundleName, String key, Collection collection) {
		this.key = key;
		this.bundleName = bundleName;
		this.params = collection.toArray();
	}	
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
	 * @return Returns the key.
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key The key to set.
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return Returns the param.
	 */
	public Object[] getParams() {
		return params;
	}
	/**
	 * @param param The param to set.
	 */
	public void setParams(Object[] params) {
		this.params = params;
	}
	
	public String getMessage(HttpServletRequest request) {
		if(this.message != null) {
			return this.message;
		} else {
			
			
			Locale locale = MafConfig.resolveLocale(request);
			String bundleMessage = null;
			try{
				bundleMessage =  MafResourceStore.getMessage(this.bundleName, locale, this.key);
				if(bundleMessage != null) {
					if(params!=null) {
						MessageFormat formatter = new MessageFormat("");
						if (locale != null) {
							formatter.setLocale(locale);
						}
						formatter.applyPattern(bundleMessage);
						bundleMessage = formatter.format(params);
					}
				}
			} catch (NoSuchBundleException e) {
				bundleMessage = LocaleUtil.getNoBundleErrorString(this.bundleName);
			} catch (NoSuchMessageException e) {
				bundleMessage = LocaleUtil.getNoMessageErrorString(this.bundleName,this.key);
			} catch (NoSuchLocaleMessageException el) {
				bundleMessage = LocaleUtil.getNoMessageErrorString(this.bundleName,this.key,locale);
			}

//			logger.debug("bundleName : " + this.bundleName + ", locale : " + locale + ", key : " + this.key + " =" + bundleMessage);
			return bundleMessage;
		}
	}
}

