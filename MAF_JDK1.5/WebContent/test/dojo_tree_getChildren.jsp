<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="maf.logger.*"%>
<%@page import="java.util.*"%>
<%@page import="org.apache.commons.logging.*"%>
<%
	//Log logger = LogFactory.getLog(this.getClass());
	//logger.debug("ttt");
	//logger.debug(request.getParameterMap());
	//System.out.println(request.getParameterMap());
	Map m = request.getParameterMap();
	Set set = m.entrySet();
	//반복자를 얻는다 
	Iterator i = set.iterator();
	//요소들을 출력한다 
	while (i.hasNext()) {
		Map.Entry me = (Map.Entry) i.next();
		System.out.print(me.getKey() + ": ");
		System.out.println(request.getParameter(me.getKey().toString()));
	}
	System.out.println("-----------------------------------");
%>
[{title:"test111",isFolder:true,objectId:"myobj"},{title:"test2"}]
