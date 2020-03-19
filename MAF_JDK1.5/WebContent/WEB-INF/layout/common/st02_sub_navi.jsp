<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="maf.web.mvc.*"%>
<%@ page import="maf.web.mvc.beans.*"%>
<%@page import="maf.menu.*"%>
<%
	MvcInfo content = (MvcInfo) request.getAttribute("MAF_INFO");
	TreeMenu oTM = TreeMenu.getInstance(content.getSite());
%>
<div class="navi">&nbsp;<%
	try{
		if(oTM != null) {
			out.print(oTM.genHtmlNavigator(oTM.getMenu(content.getPgid()), request) );
		}
	} catch(Exception e) {
	}
%></div>

