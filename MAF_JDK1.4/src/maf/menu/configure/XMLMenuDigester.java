package maf.menu.configure;

import java.io.File;

import maf.lib.calendar.configure.HolidayDigesterException;
import maf.menu.XMLMenuBean;

import org.apache.commons.digester.Digester;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author goindole
 *
 */
public class XMLMenuDigester {
    static  Log logger = LogFactory.getLog(XMLMenuDigester.class);
    
    public static XMLMenuConfig digest(String filePath)
    throws HolidayDigesterException {
        Digester digester = new Digester();
        digester.setValidating(false);
        XMLMenuConfig configuration = null;
        
        digester.addObjectCreate("menuset", XMLMenuConfig.class);
        
        digester.addObjectCreate("menuset/menu", XMLMenuBean.class);
        digester.addSetProperties("menuset/menu", "nm", "nm");
        digester.addSetProperties("menuset/menu", "nm_eng", "nm_eng");
        digester.addSetProperties("menuset/menu", "url", "url");
        digester.addObjectCreate("menuset/menu/item", XMLMenuBean.class);
        digester.addSetProperties("menuset/menu/item", "id", "id");
        digester.addSetProperties("menuset/menu/item", "seq", "seq");
        digester.addSetProperties("menuset/menu/item", "nm", "nm");
        digester.addSetProperties("menuset/menu/item", "nm_eng", "nm_eng");
        digester.addSetProperties("menuset/menu/item", "url", "url");
        digester.addSetNext("menuset/menu/item", "addMenu");
        digester.addSetNext("menuset/menu", "addMenu");
                
        
        try {
            File configurationFile = new File(filePath);
            configuration = (XMLMenuConfig) digester.parse(configurationFile);
            
        } catch (Throwable ex) {
            logger.error("ConfigurationDigester [" + filePath + "] Error!!!\n"  );
            //throw new HolidayDigesterException(ex);
        }
        return configuration;

    }
}
