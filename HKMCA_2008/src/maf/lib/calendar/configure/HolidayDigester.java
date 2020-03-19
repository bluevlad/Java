package maf.lib.calendar.configure;

import java.io.File;

import maf.lib.calendar.beans.HolidayBean;

import org.apache.commons.digester.Digester;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * XML 설정 문서로부터 Configuration 객체를 생성한다.
 */
public class HolidayDigester {
//    public static final String SYSTEM_PROPERTY_NAME = "mvc.configuration.file";
    private static Log logger = LogFactory.getLog(HolidayDigester.class);
//    public static Configuration digest()
//    throws ConfigurationDigesterException {
//        String filePath = System.getProperty(SYSTEM_PROPERTY_NAME);
//        return ConfigurationDigester.digest(filePath);
//    }
    
    public static HolidaysConfig digest(String filePath)
    throws HolidayDigesterException {
        Digester digester = new Digester();
        digester.setValidating(false);
        
        digester.addObjectCreate("holidays", HolidaysConfig.class);
        
        digester.addObjectCreate("holidays/day", HolidayBean.class);
        digester.addSetProperties("holidays/day", "name", "name");
        digester.addSetProperties("holidays/day", "date", "date");
        digester.addSetProperties("holidays/day", "lunar", "lunar");
        digester.addSetProperties("holidays/day", "prev", "prev");
        digester.addSetProperties("holidays/day", "next", "next");
        digester.addSetProperties("holidays/day", "dayoff", "dayoff");
        digester.addSetNext("holidays/day", "addHoliday");
                
        
        try {
            File configurationFile = new File(filePath);
            HolidaysConfig configuration = (HolidaysConfig) digester.parse(configurationFile);
            return configuration;
        } catch (Throwable ex) {
            logger.error("ConfigurationDigester [" + filePath + "] Error!!!\n"  );
            throw new HolidayDigesterException(ex);
        }

    }
}
