<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<c:set var="dtreeUrl" value="/js/dtree"/>
<link rel="StyleSheet" href='<c:url value="${dtreeUrl}/dtree.css"/>' type="text/css" />
<SCRIPT src='<c:url value="/js/dtree.js"/>' ></SCRIPT>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
	<td  bgcolor="#D6D3CE">
		<table width=100% border=0 cellspacing=0>
			<tr>
				<td>Folder &nbsp;&nbsp;</td>
				<td align=right>
                    <a href="javascript: d.openAll();"><img src='<c:url value="${dtreeUrl}/img/open_all.gif"/>' border=0 alt="open all" align="absmiddle"></a>
			 		<a href="javascript:d.closeAll();"><img src='<c:url value="${dtreeUrl}/img/close_all.gif"/>' border=0 alt="close all" align="absmiddle"></a>
			 		<a href="javascript:location.reload();"><img src='<c:url value="${dtreeUrl}/img/refresh.gif"/>' border=0 alt="page refresh" align="absmiddle"></a>
			 		&nbsp;&nbsp;
			 	</td>
			</tr>
		</table>
	</td>
</tr>
<tr>
	<td>
        <div style="overflow: auto; width: 100%; height: 500;pading:0;margin:0;">
		<script type="text/javascript">
			<!--
			d = new dTree('d');
            d.add('UBQ', '-1', 'UBQ', '<c:out value="${t}"/>', 'UBQ', 'frmEdit');
			<c:forEach var="item" items="${org}">
			d.add('<c:out value="${item.org_cd}" />',
					'<c:out value="${item.p_org_cd}" />',
					'<c:out value="${item.org_name}[${item.org_cd}]" />',
					'<c:out value="${control_action}?cmd=edit&org_cd=${item.org_cd}" />',
					'<c:out value="${item.org_cd}" />',
					'frmOrgEdit');
			</c:forEach>
			document.write(d);
			//-->
		</script>
        </div>
	</td>
</tr>
</table>