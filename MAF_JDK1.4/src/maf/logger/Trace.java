/*
 * @(#) Trace.java 1.0, 2004. 9. 16.
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.logger;

/**
 * File Name : Trace.java
 * <br>
 * 예외가 발생했을때 예외의 자세한 사항을 Trace하는 Class
 *  
 * @author 김윤철
 * @version 1.0
 * @modifydate 2004. 9. 16.
 */
public class Trace {
	/**
	 * throwable e에 대한 trace를 String으로 돌려 준다.
	 * @param e
	 * @return
	 */
	public static String getStackTrace(Throwable e) {
		if(e!=null) {
			java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream();
			java.io.PrintWriter writer = new java.io.PrintWriter(bos);
			e.printStackTrace(writer);
			writer.flush();
			return bos.toString();
		} else {
			return null;
		}
		
	}        
}
