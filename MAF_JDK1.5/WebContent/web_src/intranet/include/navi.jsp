<%@ page contentType="text/html; charset=euc-kr"%>
<%@ page import="maf.web.mvc.*"%>
<%@ page import="maf.web.mvc.beans.*"%>
<%@page import="maf.web.mvc.menu.*"%>

<%
	MvcInfo content = (MvcInfo) request.getAttribute("MAF_INFO");
	TreeMenu oTM = (TreeMenu) content.getTreeMenu();
  boolean islogin = content.isLogin();
%>
<table  border="0" cellspacing="0" cellpadding="0">
	<TR>
		<td class="navi"><%
				try{
					if(oTM != null) {
                    out.print(oTM.genHtmlNavigator(oTM.getMenu(content.getPgid()), islogin) );
					}
				} catch(Exception e) {
				}
			%>&nbsp;</td>
	</TR>
</TABLE>

