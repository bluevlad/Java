
package maf.web.mvc.menu;

import maf.web.mvc.menu.configure.XMLMenuConfig;

/**
 * @author goindole
 *
 */
public class XMLMenu {
    private static XMLMenu instance = new XMLMenu(); 
    private XMLMenuConfig config = null;

    public XMLMenu(){
        
    }
    
    public void setConfig(XMLMenuConfig newConfig) {
        config= newConfig;
    } 

    public static synchronized XMLMenu getInstance() {
        return instance;
    }
    
    public XMLMenuBean getXMLMenu(String key) {
        if(config != null) {
            return config.getMenus(key);
        } else {
            return null;
        }
    }
    
    public XMLMenuConfig getMenuConfig() {
    	return config;
    }
}
