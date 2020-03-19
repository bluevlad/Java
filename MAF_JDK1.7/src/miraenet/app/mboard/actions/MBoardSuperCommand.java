/*
 * 작성된 날짜: 2005-02-16
 *
 */
package miraenet.app.mboard.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import maf.mdb.Mdb;
import maf.mdb.drivers.MdbDriver;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.MafCommand;
import maf.web.mvc.configuration.ActionConfig;
import maf.web.mvc.configuration.ViewInfoConfig;
import maf.web.mvc.configuration.ViewInfoMap;
import maf.web.mvc.exception.ViewNotFoundException;
import maf.web.session.MySession;
import miraenet.app.mboard.exception.MBoardException;
import modules.common.beans.SessionBean;

/**
 * @author goindole, 2005-02-16
 *
 */
public class MBoardSuperCommand  extends MafCommand  {
    protected MdbDriver oDb = null;
    protected SessionBean sessionBean = null;

    public MBoardSuperCommand(){
//    	this.creationTime = System.currentTimeMillis();
    }
    
    public  synchronized ViewInfoConfig execute(ActionConfig cmfcfg, ViewInfoMap viewInfoMap, HttpServletRequest _req, HttpServletResponse _res)
        throws Exception {
        
        ViewInfoConfig forward = null;
        try {
            // Servlet에서 사용할 DB object를 넘겨줌
            // 실제 Connection은 사용할때만 일어 남
    		this.oDb = Mdb.getMdbDriver();
            if (this.oDb == null) {
                new MBoardException("Connection Fail!!!");
            }
            this.sessionBean = (SessionBean) MySession.getSessionBean(_req, _res);

//            SupportLib.commonAttribute(_req, _res);
            
            MyHttpServletRequest mreq = new MyHttpServletRequest(_req);
            forward = this.process(viewInfoMap, mreq, _res);
            
        } finally {
			if (this.oDb != null) {try {this.oDb.close();} catch (Exception e) {};}
			this.oDb = null;
        }
        return forward;
    }

    /**
     * 모든 하위 Class에서 구성해야할 프로세스
     * 
     * @param request
     * @param response
     * @return
     * @throws Throwable
     */
    protected ViewInfoConfig process(ViewInfoMap viewInfoMap, MyHttpServletRequest _req, HttpServletResponse _res)
        throws Exception {
        return viewInfoMap.getViewInfoConfig(null);
    }
    
    protected ViewInfoConfig errorPage(ViewInfoMap viewInfoMap, MyHttpServletRequest request, String message) 
    throws ViewNotFoundException{
        request.setAttribute("message", message);
        return viewInfoMap.getViewInfoConfig("error");
    }
    

}
