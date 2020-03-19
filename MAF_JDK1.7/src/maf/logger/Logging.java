/*
 * @(#) Loggin.java 2005-02-20
 * 
 * Copyright (c) 2005 (주)미래넷 All rights reserved.
 */

package maf.logger;

import miraenet.MiConfig;

/**
 * Log에 남겨야할 오류는 Logger를 사용 하나 잠시 확인용은 이 Method 사용 
 * @author goindole
 * @version 1.0
 * @modifydate 2005-02-20
 */
public class Logging {
    public  static void log(Class cls, String msg) {
        if ( MiConfig.DEBUG ) {
            System.out.println("[" + cls.getName() + "] " + msg);
        }
    }
    
    public  static void trace(Throwable e) {
        if ( MiConfig.DEBUG ) {
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
}
