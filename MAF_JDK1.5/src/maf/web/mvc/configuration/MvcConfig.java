/*
 * MvcConfig.java, @ 2005-03-31
 * 
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
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
	
//	protected String pgid = null;  // �޴��� ����� ID
    protected String desc ; // ���� 
    private String title = null;	// Ÿ��Ʋ
    
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
