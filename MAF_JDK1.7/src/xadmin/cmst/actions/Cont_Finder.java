/*
 * Created on 2005. 6. 6.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package xadmin.cmst.actions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import maf.MafUtil;
import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import maf.web.util.CookieUtil;
import miraenet.MiConfig;
import miraenet.Message;
import xadmin.cmst.JSTree;
import xadmin.cmst.WebFolderLib;
import xadmin.common.actions.AdminAction;

/**
 * @author goindole
 * 
 * 컨텐츠 목록 찾기
 */
public class Cont_Finder extends AdminAction {
	public String doWork(MyHttpServletRequest _req, HttpServletResponse _res)
			throws MafException {
		String forward = null;
		String cmd = _req.getParameter("cmd", "default");
		String file_root = MiConfig.DEFAULT_CONTENTS_FILE_PATH;
		
		String absPath = _req.getParameter("absPath");
        if(MafUtil.empty(absPath) ) {
        	absPath = CookieUtil.getValue(_req, "contents_root");
        	if(MafUtil.empty(absPath)) {
        		absPath = "/";
        	}
        }

        CookieUtil.setCookieValue(_res,"contents_root", absPath);

		
		if ("default".equals(cmd)) {
			forward = "default";
		} else if ("list".equals(cmd)) {
			
            
            if(absPath.indexOf("..") >0 ) {
                throw new MafException("???");
            }
            absPath= absPath.replaceAll("//","/");
            if(!"/".equals(absPath.substring(0,1))) {
                absPath = "/" + absPath;
            }
            if(absPath.lastIndexOf("/")+1 != absPath.length()) {
                absPath += "/";
            }
			String realPath = file_root + absPath;
			List list = WebFolderLib.getList(realPath); // FileBean List
			String upPath = null;
			String stripPath = absPath.substring(0, absPath.length() - 1);
			int i = stripPath.lastIndexOf("/");
	        if(!"/".equals(absPath)) {
	            upPath = (i < 1) ? "/" : absPath.substring(0, i);
	        }
	        _req.setAttribute("lists", list);
	        _req.setAttribute("upPath", upPath);
	        _req.setAttribute("absPath", absPath);
	        forward = "list";
		} else if ("tree".equals(cmd)) {
			List nodes = new ArrayList();
			JSTree ot = new JSTree();
			_req.setAttribute("tree", ot.getTree(nodes,
					file_root, "absPath=%PATH%/",
					"folderRight"));
			forward = "tree";
		} else {
			forward = "error";
			_req.setAttribute("message", Message.INVALID_REQUEST);
		}
		return forward;
	}
}
