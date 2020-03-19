package modules.wlc.lcms;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import maf.MafConstant;
import maf.MafUtil;
import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.MafAction;
import maf.web.util.CookieUtil;
import maf.web.util.SerializeHashtable;
import miraenet.AppConfig;
import miraenet.app.webfolder.JSTree;
import miraenet.app.webfolder.WebFolderLib;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ContentsFinderAction extends MafAction {
	private Log logger = LogFactory.getLog(this.getClass());
	SerializeHashtable listOp = null;
	
	private final String MESSAGE_BUNDLENAME = "common.message";

	public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res)
	        throws MafException {
		super.chkLogin();
		this.listOp = new SerializeHashtable(_req.getParameter(MafConstant.LIST_OP_NAME));
	}

	public void postProcess(MyHttpServletRequest _req, HttpServletResponse _res)
	        throws MafException {
		result.setAttribute(MafConstant.LIST_OP_NAME, listOp);
	}
	
	/**
	 * Contents 찾기 default
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdDefault(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		result.setForward("default");
	}

	/**
	 * Folder Tree 가져 오기 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdTree(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		String file_root = _req.getRealPath(AppConfig.DEFAULT_CONTENTS_FILE_PATH);
		
		List nodes = new ArrayList();
		JSTree ot = new JSTree();
		_req.setAttribute("tree", ot.getTree(nodes, file_root, "folderRight"));
		result.setForward("tree");
	}

	/**
	 * 파일 목록 가져오기 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res)  throws MafException {
		final String SEP = "/" ; // File.separator;
		String file_root = _req.getRealPath(AppConfig.DEFAULT_CONTENTS_FILE_PATH);
		String absPath = _req.getParameter("absPath");
		
		if(MafUtil.empty(absPath) ) {
        	absPath = CookieUtil.getValue(_req, "contents_root");
        	if(MafUtil.empty(absPath)) {
        		absPath = SEP;
        	}
        }

        CookieUtil.setCookieValue(_res,"contents_root", absPath);
        
        if(absPath.indexOf("..") >0 ) {
            throw new MafException("???");
        }
        
        absPath= absPath.replaceAll("//",SEP);
        if(!SEP.equals(absPath.substring(0,1))) {
            absPath = SEP + absPath;
        }
        if(absPath.lastIndexOf(SEP)+1 != absPath.length()) {
            absPath += SEP;
        }
		String realPath = file_root + absPath;
		logger.debug("real path : " + realPath);
		List list = WebFolderLib.getList(realPath); // FileBean List
		String upPath = null;
		String stripPath = absPath.substring(0, absPath.length() - 1);
		int i = stripPath.lastIndexOf(SEP);
        if(!SEP.equals(absPath)) {
            upPath = (i < 1) ? SEP : absPath.substring(0, i);
        }
        _req.setAttribute("lists", list);
        _req.setAttribute("upPath", upPath);
        _req.setAttribute("absPath", absPath);
		
		result.setForward("list");
	}
}
