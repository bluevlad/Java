/*
 * Created on 2005. 5. 27.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.mdb.drivers;

import java.lang.reflect.Method;
import java.util.Map;

import maf.mdb.MdbParameters;
import maf.util.StringUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.regexp.RE;

/**
 * @author goindole
 *  사용 안함 
 * @ x deprecated
 */
public class MdbSetter {
	
	Log logger = LogFactory.getLog(getClass());
	private Class cls;
//	private Object bean;
	
	public MdbParameters setCall(String sql, Object bean) {
		this.cls = bean.getClass();
		
		MdbParameters p = null;
		if (sql != null) {
			p = new MdbParameters();
			RE re = new RE("(:[\\w*]+)|(:[@\\w*]+)");
//			where = new ArrayList();
			while (re.match(sql)) {
				sql = re.subst(sql, "?", RE.REPLACE_FIRSTONLY);
				String key = re.getParen(0).substring(1);
				Object obj = null;
				
				if ("@String".equals (key)) {
					
				} else {
					if(this.cls.equals(Map.class)){
						obj = ((Map) bean).get(key);
					} else {
						try {
							Method method = null;
							try{
								method = this.cls.getDeclaredMethod("get"+StringUtils.capitalize(key),null);            
							}catch(Exception e){
								logger.error(e.getMessage());
								throw new NoSuchMethodException("No Such Method Name : "+ "get"+StringUtils.capitalize(key)  );
							}
							Object [] args = null;
							obj = method.invoke(bean, args);
						} catch ( Exception e ) {
							logger.error(" invoke Error !!!" + e.getMessage());
						}
					}
				}
				p.add(key, obj);
				if(obj != null) {
					logger.debug(key + ":" + obj.toString());
				} else {
					logger.debug(key + ": null");
					
				}
			}
		}
		return p;
	}
	
	/**
	 * 
	 * 특정 FIELD에 해당하는 Setter Method를 Beans클래스에서 찾는 메소드<br>
	 * 
	 * @author Sim Jea Jin
	 * @version 1.0
	 * @modifydate 2004. 5. 20.
	 * 
	 * @param field
	 * @return
	 * @throws NoSuchMethodException
	 */
	private Method getDeclaredMethod(String fieldName) throws NoSuchMethodException{
		Method method = null;

		try{
			method = this.cls.getDeclaredMethod("get"+StringUtils.capitalize(fieldName),null);            
		}catch(Exception e){
			logger.error(e.getMessage());
			throw new NoSuchMethodException("No Such Method Name : "+ "get"+StringUtils.capitalize(fieldName)  );
		}
		return method;
	}
}
