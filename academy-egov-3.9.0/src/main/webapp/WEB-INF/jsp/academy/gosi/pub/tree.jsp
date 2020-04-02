<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<html>
<head>
<title></title>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.8.1.min.js" />"></script>
<link rel="StyleSheet" type="text/css" href="<c:url value="/resources/libs/dtree/dtree.css" />">
<script type="text/javascript" src="<c:url value="/resources/libs/dtree/dtree.js" />"></script>
<script type="text/javascript">
 	function edit(idx){
		$(parent.document).find("#frmEdit").attr("src" , '<c:url value="/pub/pub_detail.frm"/>?IDX='+idx+'&TOP_MENU_ID=<c:out value="${params.TOP_MENU_ID}"/>&MENU_ID=<c:out value="${params.MENU_ID}"/>&MENUTYPE=<c:out value="${params.MENUTYPE}"/>&L_MENU_NM=<c:out value="${params.L_MENU_NM}"/>');
	}	
</script>
</head>

<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td bgcolor="#D6D3CE">
    		<table width=100% border=0 cellspacing=0>
    			<tr>
    				<td>Folder &nbsp;&nbsp;</td>
    				<td align=right><a href="javascript: d.openAll();"><img src="<c:url value="/resources/libs/dtree/img/open_all.gif" />" border=0 alt="open all" align="absmiddle"></a>
    			 		<a href="javascript: d.closeAll();"><img src="<c:url value="/resources/libs/dtree/img/close_all.gif" />" border=0 alt="close all" align="absmiddle"></a>
    			 		<a href="javascript:location.reload();"><img src="<c:url value="/resources/libs/dtree/img/refresh.gif" />" border=0 alt="page refresh" align="absmiddle"></a>
    			 		&nbsp;&nbsp;
    			 	</td>
    			</tr>
    		</table>
        </td>
    </tr>
    <tr>
    	<td>
    	<div class="dtree">
			<script type="text/javascript">
			 	d = new dTree('d');

				<c:forEach var="list" items="${list}" varStatus="status">
					d.add("${list.IDX}","${list.P_IDX}","[${list.IDX}]${list.NM}" ,"javascript:edit('${list.IDX}');");
				</c:forEach>
				document.write(d);
			</script>
		</div>
    	</td>
    </tr>
</table>
</body>
</html>