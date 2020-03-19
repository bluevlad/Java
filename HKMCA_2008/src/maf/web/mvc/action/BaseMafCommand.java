/*
 * 작성된 날짜: 2005-02-16
 *  
 */
package maf.web.mvc.action;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import maf.base.BaseHttpSession;
import maf.exception.MafException;
import maf.mdb.Mdb;
import maf.mdb.drivers.MdbDriver;
import maf.mdb.exceptions.MdbException;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.beans.SimpleResult;
import maf.web.mvc.configuration.ActionConfig;
import maf.web.mvc.configuration.ViewInfoConfig;
import maf.web.mvc.configuration.ViewInfoMap;
import maf.web.session.MySession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 
 * @author bluevlad, 2005-02-16
 *  maf 초기 방식용 
 *  각 action단에서 process 이후 부터 사용 
 *  cmd를 분리하기 위해서는 MafAction을 이용 바람 
 */
public abstract class BaseMafCommand extends MafCommand {
    public final static String DEFAULT_VIEW = "default";
    protected MdbDriver oDb = null;
    protected BaseHttpSession sessionBean = null;
    protected String  control_action = null;
    protected SimpleResult result = null;
//    protected AAMBean auth = null;
    Log logger = LogFactory.getLog(getClass());
    


    public synchronized ViewInfoConfig execute(ActionConfig cmfcfg, ViewInfoMap viewInfoMap, HttpServletRequest _req, HttpServletResponse _res)
            throws MafException {
    	
        try {
            // Servlet에서 사용할 DB object를 넘겨줌
            // 실제 Connection은 사용할때만 일어 남
            this.oDb = Mdb.getMdbDriver();
            if (this.oDb == null) {
                throw new MdbException( "Database Connection  Fail!!!" );
            }
            this.sessionBean = (BaseHttpSession) MySession.getSessionBean( _req, _res );

            MyHttpServletRequest mreq = new MyHttpServletRequest( _req );
            
            this.result = new SimpleResult();
            
            this.control_action = _req.getRequestURL().toString();
            this.process( mreq, _res );
            
            if(this.result.isSuccess()) {
            	Set set = result.entrySet();
            	if (set != null ) {
	            	Iterator i = set.iterator();
	            	if( i!= null) {
		            	while(i.hasNext()) {
			            	Map.Entry me = (Map.Entry)i.next();
			            	_req.setAttribute((String) me.getKey(),me.getValue());
		            	}
	            	}
	            	i = null;
            	}
            	
            	set = null;
            	if (this.result.getMessage(_req) != null) {
            		_req.setAttribute("message", this.result.getMessage(_req));
            	}
            	
            	if(this.result.getNext() != null) {
            		_req.setAttribute("next", this.result.getNext());
            	}
            	
            	if(this.result.getTarget() != null) {
            		_req.setAttribute("target", this.result.getTarget());
            	}
            	
            } else {
            	_req.setAttribute("message", this.result.getMessage(_req));
            	this.result.destroy();
            }
        } finally {
            if (this.oDb != null) try {this.oDb.close();} catch (Exception ex) {}
            this.oDb = null;
        }
        
        ViewInfoConfig vc = viewInfoMap.getViewInfoConfig( this.result.getForward() );
        
        if (vc == null) {
    		
            logger.error( this.result.getForward() + " not found" );
        }
        return vc;
    }

    /**
     * 모든 하위 Class에서 구성해야할 프로세스
     * 
     * @param request
     * @param response
     * @return
     * @throws Throwable
     */
    protected abstract void process(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException;

}