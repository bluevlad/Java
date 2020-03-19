/*
 * Created on 2005. 6. 29.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.BaseMafCommand;

/**
 * @author goindole
 *
 */
public class AdodbTest extends BaseMafCommand {
	 public void process(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
	 	String forward = "default";
	 	oDb.setDebug(true);
	 	List list = null;
	 	Map param = new HashMap();
	 	param.put("a1","qqq");
	 	String sql = " select :a1 a1, :a1 a2, :a1 a3 from dual"  ;
	 	list = oDb.selector(Map.class, sql, param);
	 	_req.setAttribute("list",list);
	 	
	 	super.result.setForward(forward);
	 }

}

