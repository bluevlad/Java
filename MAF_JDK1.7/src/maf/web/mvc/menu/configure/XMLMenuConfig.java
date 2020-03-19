package maf.web.mvc.menu.configure;

import java.util.HashMap;

import maf.web.mvc.menu.XMLMenuBean;


public class XMLMenuConfig {
    private HashMap menus = new java.util.HashMap();
    
    public XMLMenuConfig(){
        
    }
    
    public void addMenu(XMLMenuBean oH) {
        menus.put(oH.getId(), oH);
        System.out.println("       MenuID : [" + oH.getId() + "]");
    }
    
    public XMLMenuBean getMenus(String key) {
        return (XMLMenuBean) menus.get(key);        
    }
}
