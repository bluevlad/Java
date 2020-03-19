package maf.web.mvc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import maf.base.BasePoolableObject;
import maf.web.mvc.configuration.ActionConfig;
import maf.web.mvc.configuration.ViewInfoConfig;
import maf.web.mvc.configuration.ViewInfoMap;
;

/**
 * ��� Ŀ�ǵ� Ŭ������ �����ؾ� �ϴ� �������̽�
 * BasePoolableObject 
 */
public abstract class MafCommand extends BasePoolableObject {

    
    /**
     * ���� ��� ����� 
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