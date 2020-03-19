package maf.lib;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import maf.exception.MafSetterException;
import maf.lib.calendar.MDate;
import maf.util.StringUtils;


/**
 * 
 * Class Name : Setter
 * File Name : Setter.java
 * <br>
 * 1. JSP ������ <jsp:setProperty name="beanId" property="fieldName"/><br>
 *   <jsp:getProperty name="beanId" property="fieldName"/> �±��� ����� �ϴ� Ŭ����<br>
 * 2. ��뿹 (�������� Bean�� ��� �Ķ������ ���� ������ ����ϸ� ������ ����)<br>
 *     // bean  instance ����<br>
 *     JavaBeans bean = new JavaBeans();<br>
 *     Setter set = new Setter(bean, req);<br>
 *     // 1) HttpServletRequest�� ���� ��� �Ķ���ͷ� ������<br>
 *     set.setProperty("*");<br>
 *     // 2) Ư���� �Ķ���͸� ������<br>
 *     set.setProperty("id");<br>
 *     // 3) Ư���� �Ķ���Ϳ� ������ ���� ������<br>
 *     set.setProperty("id", "sim11");<br>
 * 3. ����Ҽ� �ִ� ������ Ÿ�� : String, int, float, double, long<br>
 * 
 * @inheritdoc 
 * @author Sim Jea Jin
 * @version 1.0
 * @modifydate 2004. 5. 20.
 */
public class Setter{
	public static final String SETTER_PARAM_ALL = "*";
	
	Log logger = LogFactory.getLog(getClass());
	private Class cls;
	private Object obj;
	private HttpServletRequest req;
    
	private Field fields [];
    
	public Setter(Object obj, HttpServletRequest req)  {
		
		this.cls = obj.getClass();
		this.obj = obj;
		this.req = req;
		init();

	}
    
	/**
	 * 
	 * Beans Ŭ������ ���ǵǾ� �ִ� ��� FIELD�� ������ �ε��Ѵ�.<br>
	 * �ܺο��� Setter ��ü�� �����Ǿ� ���� �ѹ� ȣ��ȴ�.<br>
	 * 
	 * @author Jin Young Sug
	 * @version 1.0
	 * @modifydate 2004. 5. 20.
	 * 
	 *
	 */    
	private void init(){
		fields = this.cls.getDeclaredFields();
	}
    
	public void setProperty(String property) throws MafSetterException {
		this.setProperty(property, null);
	}

	/**
	 * 
	 * Beans Ŭ������ ������ ���� �����ϴ� �޼ҵ�.<br>
	 * NoSuchFieldException : �ش��ϴ� property�� BeansŬ������ FIELD�� �������� ������ ������<br>
	 * NoSuchMethodException : �ش��ϴ� FIELD�� ���� SETTER Method�� �������� ������ ������<br>
	 * 
	 * @author Sim Jea Jin
	 * @version 1.0
	 * @modifydate 2004. 5. 20.
	 * 
	 * @param property
	 * @param value
	 * @throws NoSuchFieldException
	 * @throws NoSuchMethodException
	 */    
	public void setProperty(String property, String value) throws MafSetterException {        
        
		boolean all = SETTER_PARAM_ALL.equals(property) ? true:false;
		
		if(!all){
			Field field = null;
			Method method = null;

				field = getDeclaredField(property);
				method = getDeclaredMethod(field);

			Object [] args = null;
			if(value == null){                
				args = getArgs(field.getType(), this.req.getParameter(property));
			}else{
				args = getArgs(field.getType(), value);
			}
			try{
				method.invoke(this.obj, args);
			}catch(Exception _ignored){}  
		}else{        
		    //logger.debug("Length = " + fields.length);
//			for(int i=0; i<fields.length; i++){
//				String fieldName = fields[i].getName();
//				System.out.println(i+". " + fieldName );
//			}
			for(int i=0; i<fields.length; i++){
				String fieldName = fields[i].getName();
					Method method = null;
					
					try{
//						method = this.cls.getDeclaredMethod("set"+fieldName.substring(0,1).toUpperCase() + fieldName.substring(1, fieldName.length()), 
//															  new Class[]{fields[i].getType()});
						method = this.cls.getDeclaredMethod("set"+StringUtils.capitalize(fieldName), 
															  new Class[]{fields[i].getType()});
	
					}catch(Exception e){ // NoSuchMethodException, SecurityException
						logger.error(e.getMessage() + " / property >> ["  + fieldName +"]");
						continue;    
					}
	                
					logger.debug(fieldName);
					Object [] args = getArgs(fields[i].getType(), this.req.getParameter(fieldName));
					
					if(args != null){
						try{
							method.invoke(this.obj, args);
						}catch(Exception e){} // IllegalAccessException, IllegalArgumentException, InvocationTargetException
						
					}
			}
		}
	}

	/**
	 * 
	 * �ܺο��� ��û�� property�� �ش��ϴ� FIELD�� BeansŬ�������� ã�� �޼ҵ�<br>
	 * 
	 * @author Sim Jea Jin
	 * @version 1.0
	 * @modifydate 2004. 5. 20.
	 * 
	 * @param fieldName
	 * @return
	 * @throws NoSuchFieldException
	 */    
	private Field getDeclaredField(String fieldName) throws MafSetterException{
		Field field = null;
		for(int i=0; i<fields.length; i++ ){
			if((fields[i].getName()).equals(fieldName)){ 
				field = fields[i];    
				break;
			}
		}
		if(field == null) {
			throw new MafSetterException("No Such Field Exception !!! Field Name : "+ fieldName);
		}
		return field;
	}

	/**
	 * 
	 * Ư�� FIELD�� �ش��ϴ� Setter Method�� BeansŬ�������� ã�� �޼ҵ�<br>
	 * 
	 * @author Sim Jea Jin
	 * @version 1.0
	 * @modifydate 2004. 5. 20.
	 * 
	 * @param field
	 * @return
	 * @throws NoSuchMethodException
	 */
	private Method getDeclaredMethod(Field field) throws MafSetterException{
		Method method = null;
		String fieldName = field.getName();
		try{
//			method = this.cls.getDeclaredMethod("set"+fieldName.substring(0,1).toUpperCase() + fieldName.substring(1, fieldName.length()), 
//												  new Class[]{field.getType()});            
			method = this.cls.getDeclaredMethod("set"+StringUtils.capitalize(fieldName), 
												  new Class[]{field.getType()});            
		}catch(Exception e){
			throw new MafSetterException("No Such Method Exception !!! Method Name : "+ "set"+fieldName.substring(0,1).toUpperCase() 
														+ fieldName.substring(1, fieldName.length()));

		}
		return method;
	}

	/**
	 * 
	 * Ư�� FILED�� ������ Ÿ���� �м��Ͽ� ������ setter method�� call�� ��� <br>
	 * Argument�� ������ Ÿ�Կ� �°� Object�� �迭�� �����Ѵ�.
	 * 
	 * @author Sim Jea Jin
	 * @version 1.0
	 * @modifydate 2004. 5. 20.
	 * 
	 * @param fieldType
	 * @param initValue
	 * @return
	 */    
	private Object[] getArgs(Class fieldType, String initValue){
		logger.debug("type : " + fieldType +  ", value : " + initValue);
		if(initValue != null){
			try{
				if(fieldType == Integer.TYPE){
					return new Object[]{new Integer(initValue)};
				}else if(fieldType == Long.TYPE){
					return new Object[]{new Long(initValue)};
				}else if(fieldType == Float.TYPE){
					return new Object[]{new Float(initValue)};
				}else if(fieldType == Double.TYPE){
					return new Object[]{new Double(initValue)};
				}else if(fieldType == java.util.Date.class){
					MDate m = new MDate(initValue);
//					logger.debug(">>>>>>>>>>>>" + m.getDateString("yyyy-MM-dd"));
					return new Object[]{m.getDate()};
				}else{
					return new Object[]{initValue};
				}
			}catch(Exception ne){
					logger.error(ne.getMessage());
			}               
		}
		return null;
	}
    
//	/**
//	 * 
//	 * @param key
//	 * @return
//	 */
//	private String getParameter(String key){
//		String str = null;
//
//		str = this.req.getParameter(key);
//		return str;
//		
//	}
}