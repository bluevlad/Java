/*
 * PairBean.java, @ 2005-03-10
 * 
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.lib.bean;

/**
 * @author bluevlad
 *
 * 
 */
public class PairBean {

	 private String key = null;
	 private Object value = null;
	 private Object value1 = null;
	 private Object value2 = null;
	 private Object value3 = null;

	 public PairBean(String key, Object value ) {
	     this.key = key;
	     this.value = value;
	 }
    /**
     * @return key�� �����մϴ�.
     */
    public String getKey() {
        return key;
    }
   
    /**
     * @return value�� �����մϴ�.
     */
    public Object getValue() {
        return value;
    }
    
    
    /**
     * @return value1�� �����մϴ�.
     */
    public Object getValue1() {
        return value1;
    }
    /**
     * @param value1 �����Ϸ��� value1.
     */
    public void setValue1(Object value1) {
        this.value1 = value1;
    }
    /**
     * @return value2�� �����մϴ�.
     */
    public Object getValue2() {
        return value2;
    }
    /**
     * @param value2 �����Ϸ��� value2.
     */
    public void setValue2(Object value2) {
        this.value2 = value2;
    }
    /**
     * @return value3�� �����մϴ�.
     */
    public Object getValue3() {
        return value3;
    }
    /**
     * @param value3 �����Ϸ��� value3.
     */
    public void setValue3(Object value3) {
        this.value3 = value3;
    }
}
