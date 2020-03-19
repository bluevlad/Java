/*
 * Created on 2006. 6. 20.
 *
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
 */
package maf.context.i18n;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.DocType;
import org.jdom.Document;
import org.jdom.Element;
import maf.configuration.support.ConfigFileInfo;
import maf.context.exceptions.NoSuchLocaleMessageException;
import maf.context.exceptions.NoSuchMessageException;
import maf.context.support.LocaleUtil;
import maf.lib.xml.XmlWriter;
import maf.util.StringUtils;
import maf.web.util.JavaScriptUtils;

public class MafResourceBundle extends ConfigFileInfo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Log logger = LogFactory.getLog(MafResourceBundle.class);
	public static final String FMT_BUNDLE = MafResourceBundle.class.getName()
	        + ".bundlename";
	//	private Log logger = LogFactory.getLog(this.getClass());
	private String bundleName = null;
	private String defaultLocale = null;
	// image type�� url �������� "/" ���� 
	private String imagePath = null;
	// ���� �Ǵ� comment
	private String desc = null;
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

	/**
	 * bundle ���� locale key�� �´� �޽����� ã�� ���� 
	 * locale�� �´� ���� ������ default locale ���� ����
	 * @param key
	 * @param locale
	 * @return
	 * @throws NoSuchMessageException
	 */
	public String getMessage(String key, Locale locale) throws NoSuchMessageException, NoSuchLocaleMessageException {
		String localeString = LocaleUtil.locale2String(locale);
		String lo = (localeString == null) ? this.defaultLocale : localeString;
		MafResource resource = (MafResource) resourceMap.get(key);
		if (resource != null) {
			MafResourceLocale rl = resource.getResourceLocale(lo);
			if (rl != null) {
				//				if (rl.getValue() == null) {
				//					rl = resource.getMessage(defaultLocale);
				//				}
				if (MafResourceLocale.RL_TYPE_TEXT.equals(rl.getType())) {
					
					return rl.getValue();

				} else {
					if (rl.isFilenameSpecified()) {
						return "<img src='"
						        + this.imagePath
						        + rl.getFilename()
						        + "' border='0' valign='middle' alt='"
						        + JavaScriptUtils.javaScriptEscape(rl.getValue())
						        + "' />";
					} else {
						return "<img src='"
						        + this.imagePath
						        + key
						        + "_"
						        + lo
						        + "."
						        + rl.getType()
						        + "' border='0' align='absmiddle' alt='"
						        + JavaScriptUtils.javaScriptEscape(rl.getValue())
						        + "' />";
					}
				}
			} else {
				throw new NoSuchMessageException("NoSuchMessageException : bundleName="
				        + this.bundleName
				        + " key = "
				        + key);
			}
		} else {
			throw new NoSuchMessageException("NoSuchMessageException : bundleName="
			        + this.bundleName
			        + " key = "
			        + key);
		}
	}

	/**
	 * key�� local�� �ش��ϴ� Resource�� ������
	 * locale�� �ش��ϴ� resource�� ���� ��� default locale�� resource�� ������
	 * ���� default�������ٸ� NoSuchMessageException ���� �߻� 
	 * @param key
	 * @param locale
	 * @return
	 * @throws NoSuchMessageException
	 */
	public MafResourceLocale getMafResourceLocale(String key, Locale locale)
	        throws NoSuchMessageException {
		String localeString = LocaleUtil.locale2String(locale);
		String lo = (localeString == null) ? this.defaultLocale : localeString;
		MafResource resource = (MafResource) resourceMap.get(key);
		if (resource != null) {
			MafResourceLocale mrl = resource.getResourceLocale(lo);
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
		if (resourceMap == null) {
			resourceMap = new HashMap();
		}
		if (r != null) {
			r.setDefaultLocale(this.defaultLocale);
		}
		resourceMap.put(r.getKey(), r);
	}

	public final Map getResource() {
		SortedMap sMap = new TreeMap(this.resourceMap);
		return sMap;
	}

	public String getXml() {
//		<?xml version="1.0" encoding="UTF-8"?>
//		<!DOCTYPE maf-command PUBLIC
//		         "-//Miranet//DTD maf-command config XML V1.0//EN"
//		         "resourceBundle.dtd">
//		defaultLocale (ko|en) #REQUIRED 
//		imagePath CDATA #IMPLIED 
//		desc 	CDATA #IMPLIED 
		StringBuffer sb = new StringBuffer(100);
		XmlWriter xw = new XmlWriter(sb);
		try {
			xw.setEncoding("UTF-8");
			xw.setDocType(" maf-command PUBLIC \"-//Miranet//DTD maf-command config XML V1.0//EN\"  \"resourceBundle.dtd\" ");
			xw.startTag("bundle");
			xw.setOptionalAttributeValue("defaultLocale", this.defaultLocale);
			xw.setOptionalAttributeValue("imagePath", this.imagePath);
			xw.setOptionalAttributeValue("desc", this.desc);
			SortedMap sm = new TreeMap(resourceMap);
			if (sm != null) {

				Set set = sm.entrySet();
				Iterator i = set.iterator();
				while (i.hasNext()) {
					Map.Entry me = (Map.Entry) i.next();
					MafResource rl = (MafResource) me.getValue();
					xw.appendValue(rl.getXml(),false);
				}
			}
			xw.endTag();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return sb.toString();
	}
	
	public Document getDocoment() {
		Document doc = new Document();
		//maf-command PUBLIC
//        "-//Miranet//DTD maf-command config XML V1.0//EN"
//        "resourceBundle.dtd"
		DocType  docType = new DocType("maf-resource","resourceBundle.dtd");
		docType.setPublicID("-//Miranet//DTD maf-command config XML V1.0//EN");
		
		doc.setDocType(docType);
		Element elm = new Element("bundle");
		Util.setOptionalAttribute(elm,"defaultLocale", this.defaultLocale);
		Util.setOptionalAttribute(elm,"imagePath", this.imagePath);
		Util.setOptionalAttribute(elm,"desc", this.desc);
		SortedMap sm = new TreeMap(resourceMap);
		if (sm != null) {
			Set set = sm.entrySet();
			Iterator i = set.iterator();
			while (i.hasNext()) {
				Map.Entry me = (Map.Entry) i.next();
				MafResource rl = (MafResource) me.getValue();
				elm.addContent(rl.getElemtnt());
			}
		}
		doc.setRootElement(elm);
		return doc;
	}
	/**
	 * �ϳ��� message �� update �Ѵ�. 
	 * key�� �´� ���� ������ ���� 
	 * @param key
	 * @param locale
	 * @param message
	 * @return
	 */
	public boolean updateMessage(String key, String locale, String message) {
		boolean chk = false;
		MafResource r = (MafResource) resourceMap.get(key);
		if(r!= null) {
			chk = r.updateOne(locale, message);
			resourceMap.put(key,r);
		} else {
			logger.debug("resource [" + key + "] is null");
		}
		return chk;
	}

}
