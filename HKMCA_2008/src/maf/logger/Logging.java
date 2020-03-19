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
 * Log에 남겨야할 오류는 Logger를 사용 하나 잠시 확인용은 이 Method 사용 
 * @author bluevlad
 * @version 1.0
 * @modifydate 2005-02-20
 */
public class Logging {
	/**
	 * Console 에 log를 남김 
	 * @param cls
	 * @param msg
	 */
    public  static void log(Class cls, String msg) {
        if ( MafProperties.DEBUG ) {
            System.out.println("[" + cls.getName() + "] " + msg);
        }
    }
    
    /**
     * Console에 trace 로그를 남김 
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
     * Request 정보를 출력 한다.
     */
    public static void printRequest(HttpServletRequest req) {
    	Map map = req.getParameterMap();
    	System.out.println(ToStringBuilder.reflectionToString(map, ToStringStyle.MULTI_LINE_STYLE));
    	Set set = map.entrySet();
    	// 반복자를 얻는다 
    	Iterator i = set.iterator();
    	//요소들을 출력한다 // .
    	while(i.hasNext()) {
    		Map.Entry me = (Map.Entry)i.next();
    		System.out.print(me.getKey() + " : ");
    		System.out.println(ToStringBuilder.reflectionToString(me.getValue(), ToStringStyle.SIMPLE_STYLE));
    	}
    	System.out.println();
    }
}
