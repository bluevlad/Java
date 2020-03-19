package miraenet;

import java.io.InputStream;
import java.util.Properties;

import maf.MafProperties;
import maf.base.BaseObject;
import maf.util.PropertySupport;
import maf.util.StringUtils;

public class Project extends BaseObject{
	/**
     * 
     */
    private static final long serialVersionUID = 1L;

	private static final String LSTRING_FILE ="/Project.properties";
	
//	 menu 및 jsp의 template 관리용 default 사이트
	public static final String COMPANY = getProperty("COMPANY","maf");
	
	public static final String RLOGIN_PARAM = getProperty("RLOGIN_PARAM","ccc");
	// HEAD OFFICE Typt
	public static final String HEAD_OFFICE_TYPE = "H";
	/**
	 * LSTRING_FILE에서 Propertiy를 읽어 온다. 없을경우 defValue
	 * @param key
	 * @param defValue
	 * @return
	 */
    public static String getProperty (String key, String defValue){
        Properties props = null;
        InputStream is = null;
        try {
             is = new MafProperties().getClass().getResourceAsStream(LSTRING_FILE);
            props = new Properties();
            props.load(is);

        }
        catch (Exception e) {
            System.err.println("Can't read the properties file.");
            System.err.println("Make sure "+LSTRING_FILE+" is in the CLASSPATH");
            e.printStackTrace();
            return "";
        } finally {
        	try {is.close();} catch (Exception e) {};
        	
        }
        //aProperties.put(key, props.getProperty(key, defValue));
        System.out.println( key + " = " + props.getProperty(key, defValue) );
        return props.getProperty(key, defValue);
    }
    
    public static boolean getBooleanProperty(String key, String defValue){
    	return StringUtils.toBoolean(getProperty(key, defValue));
    }
}