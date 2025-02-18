package com.academy.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource; 

public class Configurations {

	private Properties props;

	public String getProperty(String sName) {

		String sValue = "";
		try {
			ClassPathResource resource = new ClassPathResource("application.properties");
			BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
			String s = br.readLine();
			InputStream is = new FileInputStream(new File(s));
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
