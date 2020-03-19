/*
 * Created on 2006. 6. 20.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.context.i18n;

import maf.base.BaseObject;
import maf.util.StringUtils;

public class MafResourceLocale extends BaseObject{
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
	private String type = RL_TYPE_TEXT;
	private String imagePath = null;
	
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
	public String getValue() {
		return value;
	}
	/**
	 * @param value The value to set.
	 */
	public void setValue(String value) {
		this.value = value;
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
}

