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
	* 원래 파일명
	*/	
	protected String filename = null;

	protected String fid = null;
	
    /**
     * @return filename을 리턴합니다.
     */
    public String getFilename() {
        return filename;
    }
    /**
     * @param filename 설정하려는 filename.
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }
    /**
     * @return mime_type을 리턴합니다.
     */
    public String getMime_type() {
        return mime_type;
    }
    /**
     * @param mime_type 설정하려는 mime_type.
     */
    public void setMime_type(String mime_type) {
        this.mime_type = mime_type;
    }
    
    /**
     * @return fid을 리턴합니다.
     */
    public String getFid() {
        return fid;
    }
    /**
     * @param fid 설정하려는 fid.
     */
    public void setFid(String fid) {
        this.fid = fid;
    }
}
