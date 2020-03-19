package maf.web.mvc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import maf.base.BasePoolableObject;
import maf.web.mvc.configuration.ActionConfig;
import maf.web.mvc.configuration.ViewInfoConfig;
import maf.web.mvc.configuration.ViewInfoMap;
;

/**
 * 모든 커맨드 클래스가 구현해야 하는 인터페이스
 * BasePoolableObject 
 */
public abstract class MafCommand extends BasePoolableObject {

    
    /**
     * 실제 명령 수행부 
     * @param viewInfoMap
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public abstract ViewInfoConfig  execute(ActionConfig cmfcfg, 
            				ViewInfoMap viewInfoMap,
                              HttpServletRequest request, 
                              HttpServletResponse response)
    throws Exception;
    
   
}