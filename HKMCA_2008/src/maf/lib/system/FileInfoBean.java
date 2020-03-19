/*
 * FileInfoBean.java, @ 2005-03-14
 * 
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.lib.system;

/**
 * @author bluevlad
 *
 */
public class FileInfoBean {

    /**
	* MIME TYPE
	*/	
	protected String mime_type = null;
	
	/**
	* ���� ���ϸ�
	*/	
	protected String filename = null;

	protected String fid = null;
	
    /**
     * @return filename�� �����մϴ�.
     */
    public String getFilename() {
        return filename;
    }
    /**
     * @param filename �����Ϸ��� filename.
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }
    /**
     * @return mime_type�� �����մϴ�.
     */
    public String getMime_type() {
        return mime_type;
    }
    /**
     * @param mime_type �����Ϸ��� mime_type.
     */
    public void setMime_type(String mime_type) {
        this.mime_type = mime_type;
    }
    
    /**
     * @return fid�� �����մϴ�.
     */
    public String getFid() {
        return fid;
    }
    /**
     * @param fid �����Ϸ��� fid.
     */
    public void setFid(String fid) {
        this.fid = fid;
    }
}
