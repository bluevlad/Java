/*
 * @(#) Loggin.java 2005-02-20
 * 
 * Copyright (c) 2005 UBQ All rights reserved.
 */

package maf.logger;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;


import maf.MafProperties;
import maf.MafUtil;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Log�� ���ܾ��� ������ Logger�� ��� �ϳ� ��� Ȯ�ο��� �� Method ��� 
 * @author bluevlad
 * @version 1.0
 * @modifydate 2005-02-20
 */
public class Logging {
	/**
	 * Console �� log�� ���� 
	 * @param cls
	 * @param msg
	 */
    public  static void log(Class cls, String msg) {
        if ( MafProperties.DEBUG ) {
            System.out.println("[" + cls.getName() + "] " + msg);
        }
    }
    
    /**
     * Console�� trace �α׸� ���� 
     * @param e
     */
    public  static void trace(Throwable e) {
        if ( MafProperties.DEBUG ) {
        	System.out.println("==============================");
            System.out.println(Trace.getStackTrace(e));
        }
    }
    
    
    public static void showArray(Class cls, Object[] arr) {
    	if (arr != null) {
    		System.out.println("[" + cls.getName() + "] ");
    		for (int i = 0; i < arr.length; i ++) {
    			System.out.print(i + ":" + arr[i]+", ");
    		}
    		System.out.println();
    	}
    	
    }
    
    /** 
     * Request ������ ��� �Ѵ�.
     */
    public static void printRequest(HttpServletRequest req) {
    	Map map = req.getParameterMap();
    	System.out.println(ToStringBuilder.reflectionToString(map, ToStringStyle.MULTI_LINE_STYLE));
    	Set set = map.entrySet();
    	// �ݺ��ڸ� ��´� 
    	Iterator i = set.iterator();
    	//��ҵ��� ����Ѵ� // .
    	while(i.hasNext()) {
    		Map.Entry me = (Map.Entry)i.next();
    		System.out.print(me.getKey() + " : ");
    		System.out.println(ToStringBuilder.reflectionToString(me.getValue(), ToStringStyle.SIMPLE_STYLE));
    	}
    	System.out.println();
    }
}
