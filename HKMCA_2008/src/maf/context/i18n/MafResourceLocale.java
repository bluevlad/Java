/*
 * Created on 2006. 6. 20.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.context.i18n;

import maf.MafUtil;
import maf.base.BaseObject;
import maf.context.exceptions.NoSuchLocaleMessageException;
import maf.lib.xml.XmlWriter;
import maf.util.StringUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Element;
/**
 * 다국어 지원 관련 메시지정보를 가짐
 * 
 * @author bluevlad
 *
 */
public class MafResourceLocale extends BaseObject{
	private Log logger = LogFactory.getLog(MafResourceLocale.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static String RL_TYPE_TEXT = "text";
	public final static String RL_TYPE_GIF = "gif";
	public final static String RL_TYPE_JPEG = "jpeg";

	private String filename = null;
	private String locale = null;
	private String value = null;
//	private boolean valueSpecified = false;
	private String type = RL_TYPE_TEXT;
	private String imagePath = null;
	private String icon = null;
	private String desc = null;
	
	private boolean filenameSpecified = false;
	/**
	 * @return Returns the locale.
	 */
	public String getLocale() {
		return locale;
	}
	/**
	 * @param locale The locale to set.
	 */
	public void setLocale(String locale) {
		this.locale = StringUtils.toLowerCase(locale);
	}
	/**
	 * @return Returns the value.
	 */
	public String getValue() throws NoSuchLocaleMessageException{
		if(value == null) {
			throw new NoSuchLocaleMessageException(this.locale);
		} else {
			return value;
		}
	}
	/**
	 * @param value The value to set.
	 */
	public void setValue(String value) {
//		if (!valueSpecified) {
//			this.value = value;
//			valueSpecified = true;
//		}
		if (!MafUtil.empty(value)) {
			this.value = value;
			
		}
	}
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * @return Returns the filename.
	 */
	public String getFilename() {
		if(filenameSpecified) {
			return filename;
		} else {
			return "";
		}
	}
	/**
	 * @param filename The filename to set.
	 */
	public void setFilename(String filename) {
		if(filename!=null) {
			this.filename = filename;
			filenameSpecified = true;
		}
	}
	public String toString() {
		if (RL_TYPE_TEXT.equals(this.type)) {
			return this.value;
		} else {
			return "&lt;image&gt;";
		}
	}
	/**
	 * @return Returns the filenameSpecified.
	 */
	public boolean isFilenameSpecified() {
		return filenameSpecified;
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
	public String getIcon() {
		return this.icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public String getDesc() {
    	return desc;
    }
	public void setDesc(String desc) {
    	this.desc = desc;
    }
	
	/**
	 * XML을 구함 
	 * @return
	 */
	public String getXml() {
		StringBuffer sb = new StringBuffer(100);
		
		XmlWriter xw = new XmlWriter(sb);
		try{
			xw.startTag("message");
			xw.setOptionalAttributeValue("locale", this.locale);
			xw.setOptionalAttributeValue("value", this.value);
			xw.setOptionalAttributeValue("desc", this.desc);
			xw.setOptionalAttributeValue("filename", this.filename);
			xw.setOptionalAttributeValue("icon", this.icon);
			xw.endTag();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return sb.toString();
//		logger.error(getElemtnt().getTextNormalize());
//		return getElemtnt().getTextNormalize();
	}
	
	public Element getElemtnt() {
		Element elm = new Element("message");
		Util.setOptionalAttribute(elm, "locale", this.locale);
		Util.setOptionalAttribute(elm, "value", this.value);
		Util.setOptionalAttribute(elm, "desc", this.desc);
		Util.setOptionalAttribute(elm, "filename", this.filename);

		return elm;
	}
	
	
}