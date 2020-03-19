/*
 * ProcInfo.java, @ 2005-03-24
 * 
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package miraenet.app.dbadmin.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;


import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.beans.ResultMessage;

import org.apache.regexp.RE;

/**
 * @author Rainend
 *
 */
public class ProcInfo extends DbAdminAction {
    
    public void doWork(MyHttpServletRequest _req, HttpServletResponse response)
     {

        result.setForward("procInfo");
        
        try {
	        String type = _req.getP("type");
	        String name = _req.getP("name");
	        List text = super.oa.getProcSource(super.oDb, owner, type, name);
	        
	        result.setAttribute("source",text);
	        
	        StringBuffer x = new StringBuffer(100);
	        for(int i = 0 ; i < text.size(); i++) {
	        	x.append(((Map)text.get(i)).get("text"));
	        }
	
	        RE re =  new RE("((PROCEDURE|FUNCTION) ([^;].)+;)");
	        String s = x.toString().replaceAll("(\n|\r|\f)","");
			re.setMatchFlags(RE.MATCH_CASEINDEPENDENT+RE.MATCH_MULTILINE);
	        List where = null;
	        where = new ArrayList();
	
	        _req.setAttribute("s", x.toString());
	        while (re.match(s)) {
	        	s = re.subst(s, "?", RE.REPLACE_FIRSTONLY);
	        	String key = re.getParen(0).substring(0);
				where.add(key);
	        }
	
	        result.setAttribute("procs", where);
        } catch (Exception e) {
        	result.setSuccess(false, new ResultMessage(e.getMessage()));
        }

    }
}
