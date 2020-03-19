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
     * @return key을 리턴합니다.
     */
    public String getKey() {
        return key;
    }
   
    /**
     * @return value을 리턴합니다.
     */
    public Object getValue() {
        return value;
    }
    
    
    /**
     * @return value1을 리턴합니다.
     */
    public Object getValue1() {
        return value1;
    }
    /**
     * @param value1 설정하려는 value1.
     */
    public void setValue1(Object value1) {
        this.value1 = value1;
    }
    /**
     * @return value2을 리턴합니다.
     */
    public Object getValue2() {
        return value2;
    }
    /**
     * @param value2 설정하려는 value2.
     */
    public void setValue2(Object value2) {
        this.value2 = value2;
    }
    /**
     * @return value3을 리턴합니다.
     */
    public Object getValue3() {
        return value3;
    }
    /**
     * @param value3 설정하려는 value3.
     */
    public void setValue3(Object value3) {
        this.value3 = value3;
    }
}
