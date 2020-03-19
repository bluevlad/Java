/*
 * @(#) HtmlPublisher.java 1.0, 2004. 10. 12.
 * 
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * File Name : HtmlPublisher.java
 * <br>
 * 
 * HttpURLConnection으로 부터 스트림을 받아 html파일을 생성한다.
 * 
 * @author 김윤철
 * @version 1.0
 * @modifydate 2004. 10. 12.
 */
public class HtmlPublisher {


	private String urlString;
	private String fileName;
	
	
	public HtmlPublisher(String urlString, String fileName) {
	
		this.urlString = urlString;
		this.fileName  = fileName;
	}


	public void publish() throws Exception {
		

		try {
			HttpURLConnection urlConn = null;
			BufferedReader reader = null;

			
			URL absUrl = new URL(urlString);
			urlConn = (HttpURLConnection)absUrl.openConnection();

	
			reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "MS949"));

		//	FileWriter writer = new FileWriter(new File(fileName) );
			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(new File(fileName)),"MS949");
			
			String temp = "";
			if (fileName.endsWith(".jsp")) {
				temp = "<%@ page contentType=\"text/html; charset=MS949\"%>\n";
			}
			
			writer.write(temp);

			
			while( (temp = reader.readLine()) != null ) {
				writer.write(temp + "\n");
			}
	
			writer.close();
			reader.close();
		} catch(Exception e) {
			System.out.println(e);
			throw new Exception();
		}

	}

}
