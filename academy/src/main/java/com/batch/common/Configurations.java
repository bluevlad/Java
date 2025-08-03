package com.batch.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties; 

public class Configurations {

	public Map configMap = new HashMap();
	private Properties props;

	public void getConfig() {
		try {
			String path = System.getProperty("user.dir"); 
			InputStream is = new FileInputStream(new File(path+"/jobConfig/config.properties"));
			Properties props = new Properties();

			props.load(is); 

			Enumeration<Object> keys = props.keys();
			HashMap<String, String> map = new HashMap<String, String>();
			List<String> messaeKeyList = new ArrayList<String>();
			while (keys.hasMoreElements()) {
				String key = keys.nextElement().toString();
				String value = props.getProperty(key);
				configMap.put(key, value);
			}
			/*
			 * //docbase login info configMap.put("edms.docbase",
			 * props.getProperty("edms.docbase")); configMap.put("edms.user",
			 * props.getProperty("edms.user")); configMap.put("edms.password",
			 * props.getProperty("edms.password"));
			 * 
			 * //db connection info configMap.put("db.user", props.getProperty("db.user"));
			 * configMap.put("db.password", props.getProperty("db.password"));
			 * configMap.put("db.url", props.getProperty("db.url"));
			 * configMap.put("db.driver", props.getProperty("db.driver"));
			 * 
			 * configMap.put("mig.tempdir", props.getProperty("mig.tempdir"));
			 * 
			 * // economic api server configMap.put("api.economic.base_url",
			 * props.getProperty("api.economic.base_url"));
			 * configMap.put("api.economic.client_key",
			 * props.getProperty("api.economic.client_key"));
			 */
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException:" + e.toString());
		} catch (IOException e) {
			System.out.println("IOException:" + e.toString());
		}

	}

	public String getProperty(String sName) {

		String sValue = "";

		try {

			String path = System.getProperty("user.dir");
			System.out.println(path);
			InputStream is = new FileInputStream(new File(path+"/jobConfig/config.properties"));
			Properties props = new Properties();

			props.load(is); 
			/*
			 * String fileEncoding = System.getProperty("file.encoding"); InputStream is =
			 * getClass().getResourceAsStream("config.properties"); props.load(is);
			 */
			sValue = props.getProperty(sName);
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException:" + e.toString());
		} catch (IOException e) {
			System.out.println("IOException:" + e.toString());
		}

		return sValue;
	}
}
