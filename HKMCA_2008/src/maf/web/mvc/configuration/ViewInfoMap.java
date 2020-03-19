package maf.web.mvc.configuration;

import java.util.Map;


import maf.web.mvc.exception.ViewNotFoundException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Command ��ü�� ���޵Ǵ� URI �� ����
 */
public class ViewInfoMap {
    static  Log logger = LogFactory.getLog(ViewInfoMap.class);
    private Map rootViewMap = null;
    private Map globalViewMap = null;
    private Map commandViewMap = null;
    
    public ViewInfoMap(Map rootViewMap, Map globalViewMap, Map commandViewMap) {
        this.rootViewMap = rootViewMap;
        this.globalViewMap = globalViewMap;
        this.commandViewMap = commandViewMap;
    }
    
    /**
     * �̸��� viewName�� ViewInfoConfig�� �����Ѵ�.
     * command-view�� ���Ե� ViewInfoConfig ����� ���� ã��
     * �������� ���� ��� global-view�� ���õ� ViewInfoConfig �߿��� ã�´�.
     */
    public final ViewInfoConfig  getViewInfoConfig(String viewName) throws ViewNotFoundException {
        ViewInfoConfig view = (ViewInfoConfig)commandViewMap.get(viewName);
        if (view == null) {
            view = (ViewInfoConfig) globalViewMap.get(viewName);
            
            if (view == null ) {
                view = (ViewInfoConfig) rootViewMap.get(viewName);
//                logger.debug("root:" + view);
            } 
//        } else {
//        	logger.debug("view:" + view);
        }
        if( view == null ) {
        	throw new ViewNotFoundException("View info [" + viewName + "] not found!!! check forward info ");
        }
        try {
        	return (ViewInfoConfig) view.clone();	
        } catch (CloneNotSupportedException e) {
        	throw new ViewNotFoundException("CloneNotSupportedException");
		}
        
        
    }
}