<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<%@page language="java"%>
<%@page import="maf.web.mvc.*"%>
<%@page import="maf.menu.*"%>
<%@page import="maf.web.mvc.beans.*"%>
<%@page import="maf.base.BaseHttpSession"%>
<%@page import="maf.web.session.MySession"%>
<%
    BaseHttpSession sessionBean =  MySession.getSessionBean( request, response );
	String PATH_CPATH = request.getContextPath();
	TreeMenu oTM	= TreeMenu.getInstance("www"); 
	String PGID = request.getParameter("PGID");
	MenuItem oMenu = oTM.getMenu(PGID);
	StringBuffer submenu_ids = new StringBuffer ();	
    out.print("<h1>" + oMenu.getTitle(request) + "</h1>");
		for(int i = 0; i < oMenu.getChildCount(); i++ ) {
			MenuItem oT = oMenu.getChild(i);
			if (oT != null && oT.isLeftMenu()) {
				if (submenu_ids.length() > 0 ) {
					submenu_ids.append(",");
				}
				submenu_ids.append("'");
				submenu_ids.append(oT.getPgid());
				submenu_ids.append("'");			
%>
<table width="180"  border="0" cellspacing="0" cellpadding="0" >
	<tr >
		<td valign="middle" bgcolor="#FFFFFF" class="menu_l3"  ><%
			if(oT.hasChild()) {
				out.print(oT.getTitle(request));
			} else {
				out.print(oTM.genMenuHref(oT,"L", request));
			}
		%></td>
	</tr>
	<tr><td height="1" bgcolor="#D9D9D9"></td></tr>		
</table>

<%
				if(oT.hasChild()) {
%>
<table width="180" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF">
<%				
					for(int j = 0; j < oT.getChildCount(); j++ ) {
						MenuItem oT2 = oT.getChild(j);
						if (oT2 != null) { 
							%> 
							<tr><td class="menu_l4"><%=oTM.genMenuHref(oT2,"L", request)%></td></tr>
							<%
						}
						if(oT2.hasChild()) {
							out.print(printSub(oT2, PATH_CPATH, oTM, request, sessionBean));
						}
					}
%><tr><td height="1" bgcolor="#D9D9D9"></td></tr>
</table>

<%					
				}
			}
		}

%>

<%!
private String printSub(MenuItem oT, String PATH_CPATH, TreeMenu oTM, HttpServletRequest request, BaseHttpSession sessionBean) {
	StringBuffer sRv = new StringBuffer();
	StringBuffer sRv2 = new StringBuffer();
	MenuItem oT2;
	for(int j = 0; j < oT.getChildCount(); j++ ) {
		oT2 = oT.getChild(j);
		if (oT2 != null) { 
			String Href=null;
			StringBuffer sHref = new StringBuffer();
			if(oT2.isLink() && oT2.isLeftMenu()) {
				sRv.append("<tr><td class='menu_l5'> .");
				sRv.append(oTM.genMenuHref(oT2,"L", request));
				sRv.append("</td></tr>");
			}
		}
	}
	if (sRv.length() > 0 ) {
		sRv2.append("<tr><td><table border=0 cellspacing=0 cellpadding=0>");
		sRv2.append(sRv);
		sRv2.append("</table></td></tr>");
	}
	return sRv2.toString();
}
%>