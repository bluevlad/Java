<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>
<c:set var="dtreeUrl" value="${CPATH}/js/dtree" />
<link rel="StyleSheet" href='<c:url value="/js/dtree/dtree.css"/>'
	type="text/css" />
<SCRIPT src='<c:url value="/js/dtree.js"/>'></SCRIPT>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td bgcolor="#D6D3CE">
		<table width=100% border=0 cellspacing=0>
			<tr>
				<td>Folder &nbsp;&nbsp;</td>
				<td align=right><a href="javascript: d.openAll();"><img
					src="<c:out value="${dtreeUrl}"/>/img/open_all.gif" border=0
					alt="open all" align="absmiddle"></a> <a
					href="javascript: d.closeAll();"><img
					src="<c:out value="${dtreeUrl}"/>/img/close_all.gif" border=0
					alt="close all" align="absmiddle"></a> <a
					href="javascript:location.reload();"><img
					src="<c:out value="${dtreeUrl}"/>/img/refresh.gif" border=0
					alt="page refresh" align="absmiddle"></a> &nbsp;&nbsp;</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<div style="overflow: auto; width: 100%; height: 490;"><script
			type="text/javascript">
				<!--
				d = new dTree('d');
				//d.add(id, pid, name, url, title, target);
				<c:url var="t" value="${control_action}"><c:param name="cmd" value="gadd"/></c:url>
				d.add('ROOT', '-1', 'ROOT', '<c:out value="${t}"/>', 'ROOT', 'frmEdit');
				<c:forEach var="item" items="${glist}">
				<c:url var="t" value="${control_action}"><c:param name="cmd" value="gedit"/><c:param name="group_cd" value="${item.group_cd}"/></c:url>
				d.add('<c:out value="${item.group_cd}"/>','ROOT','<c:out value="${item.group_name}[${item.group_cd}]"/>','<c:out value="${t}"/>','<c:out value="${item.group_cd}"/>','frmEdit');
				</c:forEach>
				<c:forEach var="item" items="${clist}">
				<c:url var="t" value="${control_action}"><c:param name="cmd" value="edit"/><c:param name="group_cd" value="${item.group_cd}"/><c:param name="code_no" value="${item.code_no}"/></c:url>
				d.add('<c:out value="${item.group_cd}${item.code_no}"/>','<c:out value="${item.group_cd}"/>','<c:out value="${item.code_name} [${item.code_no}]"/>',
					'<c:out value="${t}"/>','<c:out value="${item.group_cd}${item.code_no}"/>','frmEdit');
				</c:forEach>
				document.write(d);
				//-->
			</script></div>
		</td>
	</tr>
</table>
