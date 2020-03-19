package modules.etest;

import java.io.InputStream;
import java.util.Properties;

import maf.MafProperties;
import maf.util.StringUtils;

public class ETestCommon {
	private static final String LSTRING_FILE ="/Project.properties";
	/**
	 * ���� �ڵ�
	 */
	public final static String COMMON_ID = getProperty("COMPANY_CD","UBQ");
	
	/**
	 * LSTRING_FILE���� Propertiy�� �о� �´�. ������� defValue
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
