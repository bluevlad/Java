/*
 * Created on 2006. 5. 16.
 *
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
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
	 * ��ξ��� ���ϸ� ���� �ش�.
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
	 * ��θ� ������ ���ϸ��� �����ش�.
	 * 
	 * @return Returns the pathname.
	 */
	public String getFullFileName() {
		return fullFileName;
	}

}
