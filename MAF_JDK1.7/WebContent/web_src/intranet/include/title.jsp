<%@ page contentType="text/html; charset=euc-kr"%>
<%@ page import="maf.web.mvc.*"%>
<%@ page import="maf.web.mvc.beans.*"%>
<%@page import="maf.web.mvc.menu.*"%>

<%
	MvcInfo content = (MvcInfo) request.getAttribute("MAF_INFO");
	TreeMenu oTM = (TreeMenu) content.getTreeMenu();
%>
<table  border="0" cellspacing="0" cellpadding="0">
	<TR>
		<td class="title">&nbsp;<%
				try{
					if(oTM != null) {
                        out.print(oTM.genHtmlNavigatorNoHref(oTM.getMenu(content.getPgid())) );
					}
				} catch(Exception e) {
				}
			%>&nbsp;</td>
	</TR>
</TABLE>

