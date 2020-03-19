/*
 * Created on 2006. 5. 16.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.beans;

import java.io.File;
import java.util.Date;

import maf.base.BaseObject;

public class FileInfo extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String filename = null;

	private String fullFileName = null;

	private long size = -1;

	private long lastModified = -1;

	private boolean isDirectory = false;;

	public FileInfo(File file) {
		this.lastModified = file.lastModified();
		this.size = file.length();
		this.filename = file.getName();
		this.fullFileName = file.getAbsolutePath();
		if (file.isDirectory()) {
			this.isDirectory = true;
		}
	}

	public Date getLastModifiedDaTe() {
		return new Date(this.lastModified);
	}
	/**
	 * @return
	 */
	public long getLastModified() {
		return this.lastModified;
	}

	/**
	 * 경로없이 파일명만 돌려 준다.
	 * 
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


	public boolean isDirectory() {
		return isDirectory;
	}

	/**
	 * 경로를 포함한 파일명을 졸려준다.
	 * 
	 * @return Returns the pathname.
	 */
	public String getFullFileName() {
		return fullFileName;
	}

}
