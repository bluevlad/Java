<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<c:set var="dtreeUrl" value="/js/dtree"/>
<link rel="StyleSheet" href='<c:url value="${dtreeUrl}/dtree.css"/>' type="text/css" />
<SCRIPT src='<c:url value="/js/dtree.js"/>' ></SCRIPT>



<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
	<td  bgcolor="#D6D3CE">
		<table width=100% border=0 cellspacing=0>
			<tr>
				<td>Folder &nbsp;&nbsp;</td>
				<td align=right><a href="javascript: d.openAll();"><img src='<c:url value="${dtreeUrl}/img/open_all.gif"/>' border=0 alt="open all" align="absmiddle"></a>
			 		<a href="javascript: d.closeAll();"><img src='<c:url value="${dtreeUrl}/img/close_all.gif"/>' border=0 alt="close all" align="absmiddle"></a>
			 		<a href="javascript:location.reload();"><img src='<c:url value="${dtreeUrl}/img/refresh.gif"/>' border=0 alt="page refresh" align="absmiddle"></a>
			 		&nbsp;&nbsp;
			 	</td>
			</tr>
		</table>
	</td>
</tr>
<tr>
	<td>
		<div id="oTree" style="overflow: auto; width: 190px; height: 460px;"">
		<script type="text/javascript">
            <!--
            d = new dTree('d');
            d.add(0,-1,'Root','<c:out value="${control_action}?cmd=list"/>','Root', 'folderRight');
                    
                            
            <c:forEach var="item" items="${tree}">
            <c:url var="url" value="${control_action}">
                    <c:param name="cmd" value="list"/>
                    <c:param name="absPath" value="${item.url}"/>
            </c:url>

            d.add('<c:out value="${item.id}" />',
                    '<c:out value="${item.pid}" />',
                    '<mh:out value="${item.nm}" escapeJS="true"/>',
                    '<c:out value="${url}"/>',
                    '<mh:out value="${item.title}" escapeJS="true"/>',
                    '<c:out value="${item.target}" />');
            </c:forEach>
            document.write(d);
            //-->
		</script>
		</div>
	</td>
</tr>

</table>