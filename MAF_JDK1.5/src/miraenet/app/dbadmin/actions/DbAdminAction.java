/*
 * 작성된 날짜: 2005-02-02
 *  
 */
package miraenet.app.dbadmin.actions;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.util.StringUtils;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.MafAction;
import maf.web.util.CookieUtil;
import miraenet.app.dbadmin.DbAdminManager;
import miraenet.app.dbadmin.dao.DbAdmin;
import modules._exceptions.EcampusException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author goindole, 2005-02-02
 *  
 */
public class DbAdminAction extends MafAction {
    private  Log logger = LogFactory.getLog(DbAdminAction.class);
    protected DbAdmin oa = null;
    protected String owner = null;
    protected String tabsname = null;
    protected String packageName = null; 
    protected String className = null; 
	
    public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res)
        throws MafException {
    	
   	
	        oa = DbAdminManager.getDbAdmin();
	
	        if (oa == null) {
	            new EcampusException("Manager init Fail!!!");
	        }
	        this.owner = _req.getParameter("owner", "SMIS");
	        this.tabsname = _req.getParameter("tabsname", "");

	        // 실제 Servlet 수행
//	        doWork(_req, response);
	        _req.setAttribute("owner", owner);
	        _req.setAttribute("tabsname", tabsname);
			this.packageName = _req.getP("packageName", CookieUtil.getValue(_req, "packageName"));
			this.className = _req.getP("className", StringUtils.capitalize(tabsname));
			CookieUtil.setCookieValue(_res, "packageName", this.packageName);
			CookieUtil.setCookieValue(_res, "className", this.className);
			_req.setAttribute("packageName", this.packageName);
			_req.setAttribute("className", this.className);
    }



}