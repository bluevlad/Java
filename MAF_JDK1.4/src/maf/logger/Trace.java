/*
 * @(#) Trace.java 1.0, 2004. 9. 16.
 * 
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
 */
package maf.logger;

/**
 * File Name : Trace.java
 * <br>
 * ���ܰ� �߻������� ������ �ڼ��� ������ Trace�ϴ� Class
 *  
 * @author ����ö
 * @version 1.0
 * @modifydate 2004. 9. 16.
 */
public class Trace {
	/**
	 * throwable e�� ���� trace�� String���� ���� �ش�.
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
