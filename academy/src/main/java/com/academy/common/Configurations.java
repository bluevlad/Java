package com.academy.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties; 

public class Configurations {

	public Map configMap = new HashMap();
	private Properties props;

	public String getProperty(String sName) {

		String sValue = "";

		try {

			String path = System.getProperty("user.dir");
			System.out.println(path);
			InputStream is = new FileInputStream(new File(path+"/Config/config.properties"));
			Properties props = new Properties();

			props.load(is); 
			sValue = props.getProperty(sName);
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException:" + e.toString());
		} catch (IOException e) {
			System.out.println("IOException:" + e.toString());
		}

		return sValue;
	}
}
