/*
 * Created on 2006. 6. 20.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.context.i18n;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Element;
import maf.MafUtil;
import maf.base.BaseObject;
import maf.context.exceptions.NoSuchLocaleMessageException;
import maf.context.exceptions.NoSuchMessageException;
import maf.lib.xml.XmlWriter;

/**
 * MafResouce는 메시지 구조체를 가지며 bundle-key-locale:value의 구조름 가짐 
 * @author goindole
 *
 */
public class MafResource extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Log logger = LogFactory.getLog(this.getClass());
	private String key = null;
	private String desc = null;
	private MafResourceLocale rl = null;
	private String defaultLocale = null;
	private Map localeValueMap = null;

	// 설명 또는 comment
	public MafResource() {
		this.rl = new MafResourceLocale();
		localeValueMap = new HashMap();
	}

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
	public final String getValue() throws NoSuchLocaleMessageException{
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
	 * Staus용 
	 * @return
	 */
	public final Map getLocaleValueMap() {
		return this.localeValueMap;
	}

	/**
	 * 언어별 resource를 등록 한다
	 * 
	 * @param rl
	 */
	public void addResourceLocale(MafResourceLocale rl) {
		if (localeValueMap == null) {
			localeValueMap = new HashMap();
		}
		if (MafUtil.empty(rl.getIcon())) {
			rl.setIcon(this.rl.getIcon());
		}
		localeValueMap.put(rl.getLocale(), rl);
	}

	/**
	 * 해당 언어에 값을 가져온다.
	 * 만약 언어에 값이 없다면 default 값을 가져옴
	 * @param locale
	 * @return
	 * @throws NoSuchMessageException
	 */
	public final MafResourceLocale getResourceLocale(String locale)
	        throws NoSuchMessageException {
		return getResourceLocale(locale, true);
	}
	/**
	 * readDefault true 면 현재 locale언어가 없으면 default값 가져옴 
	 * @param locale
	 * @param readDefault
	 * @return
	 * @throws NoSuchMessageException
	 */
	public final MafResourceLocale getResourceLocale(String locale, boolean readDefault)
	    throws NoSuchMessageException {
		
		if (localeValueMap != null && localeValueMap.containsKey(locale)) {
			return (MafResourceLocale) localeValueMap.get(locale);
		} else {
			if(readDefault) {
				return this.rl;
			} else {
				return null;
			}
		}
	}
	/**
	 * 해당 언어에 값을 가져온다.
	 * 만약 언어에 값이 없다면 default 값을 가져옴
	 * @param locale
	 * @return
	 * @throws NoSuchMessageException
	 */
	public final MafResourceLocale getMessage(String locale)
	        throws NoSuchMessageException {
		return getMessage(locale, true);
	}
	/**
	 * readDefault true 면 현재 locale언어가 없으면 default값 가져옴 
	 * @param locale
	 * @param readDefault
	 * @return
	 * @throws NoSuchMessageException
	 */
	public final MafResourceLocale getMessage(String locale, boolean readDefault)
	    throws NoSuchMessageException {
		
		if (localeValueMap != null && localeValueMap.containsKey(locale)) {
			return (MafResourceLocale) localeValueMap.get(locale);
		} else {
			if(readDefault) {
				return this.rl;
			} else {
				return null;
			}
		}
	}
	public String getDefaultLocale() {
		return defaultLocale;
	}

	public String getIcon() {
		return this.rl.getIcon();
	}

	public void setIcon(String icon) {
		this.rl.setIcon(icon);
	}

	public void setDefaultLocale(String defaultLocale) {
		String tV = null;
		if (defaultLocale != null) {
			this.defaultLocale = defaultLocale;
			this.rl.setLocale(defaultLocale);
			MafResourceLocale t = (MafResourceLocale) localeValueMap.get(defaultLocale);
			try {
				if(t != null) {
					tV = t.getValue();
				}
			} catch (Exception e) {
				logger.debug("Message Key : " + this.key + "." +e.getMessage());
			}
			if (t != null && !MafUtil.empty(tV)) {
				this.rl.setValue(tV);
				this.rl.setFilename(t.getFilename());
				this.rl.setImagePath(t.getImagePath());
				this.rl.setType(t.getType());
				this.rl.setIcon(t.getIcon());
			} else {
				addResourceLocale(rl);
			}
		}
	}

	public String getXml() {
		//		key 	ID  #REQUIRED 
		//		value 	CDATA #IMPLIED 
		//		type	(text|gif|jpeg) #IMPLIED
		//		desc 	CDATA #IMPLIED 
		//		filename CDATA #IMPLIED
		//		icon CDATA #IMPLIED
		StringBuffer sb = new StringBuffer(100);
		XmlWriter xw = new XmlWriter(sb);
		try {
			xw.startTag("resource");
			xw.setOptionalAttributeValue("key", this.key);
			xw.setOptionalAttributeValue("desc", this.desc);
			xw.setOptionalAttributeValue("type", this.rl.getType());
			xw.setOptionalAttributeValue("icon", this.rl.getIcon());
			xw.setOptionalAttributeValue("filename", this.rl.getFilename());
			SortedMap sm = new TreeMap(localeValueMap);
			if (sm != null) {
				Set set = sm.entrySet();
				Iterator i = set.iterator();
				while (i.hasNext()) {
					Map.Entry me = (Map.Entry) i.next();
					MafResourceLocale rl = (MafResourceLocale) me.getValue();
					xw.appendValue(rl.getXml(), false);
				}
			}
			xw.endTag();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return sb.toString();
	}
	
	
	public Element getElemtnt() {
		Element elm = new Element("resource");
		Util.setOptionalAttribute(elm, "key", this.key);
		Util.setOptionalAttribute(elm, "desc", this.desc);
		Util.setOptionalAttribute(elm, "type", this.rl.getType());
		Util.setOptionalAttribute(elm, "icon", this.rl.getIcon());
		Util.setOptionalAttribute(elm, "filename", this.rl.getFilename());
		
		SortedMap sm = new TreeMap(localeValueMap);
		if (sm != null) {
			Set set = sm.entrySet();
			Iterator i = set.iterator();
			while (i.hasNext()) {
				Map.Entry me = (Map.Entry) i.next();
				MafResourceLocale rl = (MafResourceLocale) me.getValue();
				elm.addContent(rl.getElemtnt());
			}
		}
		return elm;
	}
	
	/**
	 * 하나의 메시지 update
	 * @param locale
	 * @param message
	 * @return
	 */
	public boolean updateOne(String locale, String message) {
		boolean chk =false;
		MafResourceLocale mrl = (MafResourceLocale) localeValueMap.get(locale);
		if(mrl != null) {
			mrl.setValue(message);
			localeValueMap.put(locale, mrl);
			chk = true;
		} else {
			mrl = new MafResourceLocale();
			mrl.setLocale(locale);
			mrl.setValue(message);
			localeValueMap.put(locale, mrl);
			chk = true;
		}
		return chk;
	}
}
