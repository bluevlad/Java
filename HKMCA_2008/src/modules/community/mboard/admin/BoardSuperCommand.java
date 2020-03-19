/*
 * 작성된 날짜: 2005-02-16
 *
 */
package modules.community.mboard.admin;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import maf.base.BaseHttpSession;
import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.MafActionSupport;
import maf.web.mvc.action.MafCommand;
import maf.web.mvc.beans.SimpleResult;
import maf.web.mvc.configuration.ActionConfig;
import maf.web.mvc.configuration.ViewInfoConfig;
import maf.web.mvc.configuration.ViewInfoMap;
import maf.web.mvc.exception.ViewNotFoundException;
import maf.web.session.MySession;

/**
 * @author bluevlad, 2005-02-16
 *
 */
public class BoardSuperCommand extends MafCommand {
	//    protected MdbDriver oDb = null;
	protected BaseHttpSession sessionBean = null;
	private final static Log logger = LogFactory.getLog(BoardSuperCommand.class);

	public BoardSuperCommand() {
		//    	this.creationTime = System.currentTimeMillis();
	}

	public synchronized ViewInfoConfig execute(ActionConfig cmfcfg, ViewInfoMap viewInfoMap, HttpServletRequest _req, HttpServletResponse _res)
           throws Exception {
		
		ViewInfoConfig forward = null;
		MyHttpServletRequest mreq = new MyHttpServletRequest(_req, 5000*1024*1024);
		forward = this.process(viewInfoMap, mreq, _res);
		
        if( cmfcfg.isDebug() ) {
        	maf.logger.Logging.printRequest(_req);
        }

		this.sessionBean = MySession.getSessionBean(_req, _res);

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
	protected ViewInfoConfig process(ViewInfoMap viewInfoMap, MyHttpServletRequest _req,
	        HttpServletResponse _res) throws Exception {
		return viewInfoMap.getViewInfoConfig(null);
	}

	protected ViewInfoConfig errorPage(ViewInfoMap viewInfoMap,
	        MyHttpServletRequest request, String message) throws ViewNotFoundException {
		request.setAttribute("message", message);
		return viewInfoMap.getViewInfoConfig("error");
	}
}
