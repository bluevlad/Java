<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<div id="sitemap">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td height="23"></td>
	</tr>
	<tr>
		<td height="23" valign="top"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
            <col width="33%"/>
            
            <col width="33%"/>
            <col width="33%"/>
        	<tr>
        		<td valign="top">
        			<jsp:include page="pr_menu.jsp" flush="true" >
					<jsp:param name="PGID" value="M10"/>
					</jsp:include></td>
        		
        		<td  valign="top">
        		<jsp:include page="pr_menu.jsp" flush="true" >
					<jsp:param name="PGID" value="M20"/>
					</jsp:include></td>
        		
        		<td valign="top">
        		<jsp:include page="pr_menu.jsp" flush="true" >
					<jsp:param name="PGID" value="M80"/>
					</jsp:include></td>
        		</tr>
        	</table>
			<br>
	   </td>
    </tr>
    <tr>
        <td height="23" valign="top"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
            <col width="33%"/>
            <col width="33%"/>
            <col width="33%"/>        
            <tr>
                <td valign="top">
                    <jsp:include page="pr_menu.jsp" flush="true" >
                    <jsp:param name="PGID" value="M50"/>
                    </jsp:include></td>
                
                <td valign="top">
                <jsp:include page="pr_menu.jsp" flush="true" >
                    <jsp:param name="PGID" value="M60"/>
                    </jsp:include></td>
                
                <td valign="top">
                <jsp:include page="pr_menu.jsp" flush="true" >
                    <jsp:param name="PGID" value="M30"/>
                    </jsp:include></td>
                </tr>
            </table>
            <br>
       </td>
    </tr>
</table>
</div>