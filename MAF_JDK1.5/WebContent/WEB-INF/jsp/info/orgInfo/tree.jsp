<%@ page language="java" contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="mh" uri="http://maf.miraenet.com/jsp/tld/mi-html.tld"%>
<%@ taglib prefix="mform" uri="http://maf.miraenet.com/jsp/tld/mi-form.tld"%>
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
	<td><div style="overflow: auto; width: 100%; height: 490;">
		<script type="text/javascript">
			<!--
			d = new dTree('d');

                    
			<c:forEach var="item" items="${trees}">
			d.add('<c:out value="${item.cat_id}" />',
					'<c:out value="${item.p_cat_id}" />',
					'<c:out value="${item.cat_name} [${item.active_yn},${item.cat_id}]" />',
					'<c:out value="${control_action}?cmd=edit&cat_id=${item.cat_id}" />',
					'<c:out value="${item.cat_id}" />',
					'frmMenuEdit');
			</c:forEach>
			document.write(d);
			//-->
		</script></div>
	</td>
</tr>
<!--tr>
	<td>&nbsp;	<a href="javascript: d.openAll();">open all</a> | <a href="javascript: d.closeAll();">close all</a></td>
</tr-->
</table>