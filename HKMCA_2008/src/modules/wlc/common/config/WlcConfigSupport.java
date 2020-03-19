package modules.wlc.common.config;

import java.io.InputStream;
import java.util.Properties;

import maf.util.StringUtils;
import miraenet.AppConfig;

public class WlcConfigSupport{
    private static final String WSTRING_FILE ="/wlc.properties";
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
             is = new AppConfig().getClass().getResourceAsStream(WSTRING_FILE);
            props = new Properties();
            props.load(is);

        }
        catch (Exception e) {
            System.err.println("Can't read the properties file.");
            System.err.println("Make sure "+WSTRING_FILE+" is in the CLASSPATH");
            e.printStackTrace();
            return "";
        } finally {
            try {is.close();} catch (Exception e) {};

        }
        //aProperties.put(key, props.getProperty(key, defValue));
        System.out.println(key + " = " + props.getProperty(key, defValue) );
        return props.getProperty(key, defValue);
    }

    public static boolean getBooleanProperty(String key, String defValue){
        return StringUtils.toBoolean(getProperty(key, defValue));
    }

}
