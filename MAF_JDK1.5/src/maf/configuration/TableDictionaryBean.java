/*
 * Created on 2006. 4. 11.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.configuration;

public class TableDictionaryBean {
	private String key = null;
	private String site = null;
	private String tableName = null;
	private String columnName = null;
	private String title = null;
	private String bigo = null;
	/**
	 * @return Returns the bigo.
	 */
	public String getBigo() {
		return bigo;
	}
	/**
	 * @param bigo The bigo to set.
	 */
	public void setBigo(String bigo) {
		this.bigo = bigo;
	}
	/**
	 * @return Returns the columnName.
	 */
	public String getColumnName() {
		return columnName;
	}
	/**
	 * @param columnName The columnName to set.
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
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
	 * @return Returns the tableName.
	 */
	public String getTableName() {
		return tableName;
	}
	/**
	 * @param tableName The tableName to set.
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	/**
	 * @return Returns the title.
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return Returns the site.
	 */
	public String getSite() {
		return site;
	}
	/**
	 * @param site The site to set.
	 */
	public void setSite(String site) {
		this.site = site;
	}
	
	public String toString() {
		return this.title;
	}
	
}

