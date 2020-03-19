/*
 * @(#) FileBean.java 1.0, 2004. 9. 20.
 * 
 * Copyright (c) 2004 (¡÷)πÃ∑°≥› All rights reserved.
 */
package miraenet.app.webfolder.beans;

import java.util.Date;

/**
 * File Name : FileBean.java
 * <br>
 * 
 * @author ±Ë¿±√∂
 * @version 1.0
 * @modifydate 2004. 9. 20.
 */
public class FileBean {
	private String filename;
	private long size;
	private Date date;
	private boolean isDirectory;
	private String function;
	
	
	/**
	 * @return
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @return
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @return
	 */
	public long getSize() {
		return size;
	}

	public void setDate(long dateLong) {
		//Format f = new SimpleDateFormat("yyyy-MM-dd a hh:mm");
		date = new Date();
		date.setTime(dateLong);
	}


	public void setFilename(String string) {
		filename = string;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public boolean isDirectory() {
		return isDirectory;
	}

	public void setDate(Date string) {
		date = string;
	}

	public void setDirectory(boolean b) {
		isDirectory = b;
	}



	public String getFunction() {
		return function;
	}

	public void setFunction(String string) {
		function = string;
	}

}
