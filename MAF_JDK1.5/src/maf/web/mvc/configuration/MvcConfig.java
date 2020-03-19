/*
 * MvcConfig.java, @ 2005-03-31
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.mvc.configuration;

import maf.base.BaseObject;

/**
 * @author goindole
 *
 */
public class MvcConfig extends BaseObject implements Cloneable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//	protected String pgid = null;  // 메뉴와 연결될 ID
    protected String desc ; // 설명 
    private String title = null;	// 타이틀
    
//    public String getPgid() {
//        return pgid;
//    }
//
//    public void setPgid(String id) {
//        this.pgid = id;
//    }
    
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
	 public Object clone() throws CloneNotSupportedException {
		 Object result=(Object)super.clone();

		 return result;
	 }
}
