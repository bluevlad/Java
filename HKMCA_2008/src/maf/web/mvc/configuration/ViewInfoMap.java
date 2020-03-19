package maf.web.mvc.configuration;

import java.util.Map;


import maf.web.mvc.exception.ViewNotFoundException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Command 객체에 전달되는 URI 맵 정보
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
     * 이름이 viewName인 ViewInfoConfig를 리턴한다.
     * command-view에 포함된 ViewInfoConfig 목록을 먼저 찾고
     * 존재하지 않을 경우 global-view와 관련된 ViewInfoConfig 중에서 찾는다.
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