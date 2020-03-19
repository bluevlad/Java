<%@ page contentType = "text/html; charset=euc-kr"%>
<%@ taglib uri="/WEB-INF/tld/struts-tiles.tld" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<META HTTP-EQUIV="Content-type" CONTENT="text/html;charset=ks_c_5601-1987">
    <meta http-equiv="Cache-Control" content="No-Cache">
    <meta http-equiv="Pragma" content="No-Cache">
	<link href="${CPATH}/xadmin/css/admin.css" rel="stylesheet" type="text/css"></link>
	<script language="javascript" src="${CPATH}/js/sub.common.js"></script>
	<title>Untitled</title>
</head>
<body leftmargin="0" topmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" marginheight="0">
<link rel="StyleSheet" href="${CPATH}/xadmin/menu/dtree.css" type="text/css" />
<SCRIPT src="${CPATH}/mboard/adminData/dtree.js"></SCRIPT>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<form action="${control_action}" method="post" name="myForm" id="myForm">
	<input type="hidden" name="cmd" value="tree">
	<tr>
		<td align="center">최근 : <select name="dur">
<c:forTokens items="1,2,3,4,5,6,7" delims="," var="item">
	<option value="${item}"  ${(param.dur==item)?'SELECTED':''}>${item}</option>
</c:forTokens>
</select>일 <input type="submit" value="조회">
	</td>
	</tr>
</form>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
	<td  bgcolor="#D6D3CE">
		<table width=100% border=0 cellspacing=0>
			<tr>
				<td>Folder &nbsp;&nbsp;</td>
				<td align=right><a href="javascript: d.openAll();"><img src="${CPATH}/mboard/adminData/img/open_all.gif" border=0 alt="open all" align="absmiddle"></a>
			 		<a href="javascript: d.closeAll();"><img src="${CPATH}/mboard/adminData/img/close_all.gif" border=0 alt="close all" align="absmiddle"></a>
			 		<a href="javascript:location.reload();"><img src="${CPATH}/mboard/adminData/img/refresh.gif" border=0 alt="page refresh" align="absmiddle"></a>
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
			d.add('HOME','-1','HOME[HOME]','menu_admin.do?cmd=edit&site=smis&pgid=HOME','HOME','frmMenuEdit');
			<c:set var="old_grp" />
			<c:set var="old_clubid" />
			<c:set var="old_leccode" />

			<c:forEach var="item" items="${menus}">
				
				<c:if test="${old_grp != item.grp}">
					<c:set var="old_grp" value="${item.grp}"/>
					d.add('${old_grp}','HOME','${old_grp}[${item.s_ncr}]','','HOME','');
					<c:set var="p_code" value="${item.grp}" />
				</c:if>
				
				<c:if test="${old_clubid != item.club_id}">
					<c:set var="old_clubid" value="${item.club_id}"/>
					d.add('CLUB_${old_clubid}','${item.grp}','${old_clubid}[${item.s_ncr}]','','HOME','');
					<c:set var="p_code" value="CLUB_${item.club_id}" />
				</c:if>
				
				<c:if test="${old_leccode != item.leccode}">
					<c:set var="old_leccode" value="${item.leccode}"/>
					d.add('LEC_${old_leccode}','${item.grp}','${item.leccode}[${item.s_ncr}]','','HOME','');
					<c:set var="p_code" value="LEC_${item.leccode}" />
				</c:if>				
								
				d.add('${item.bid}','${p_code}','${fn:escapeXml(item.subject)}[${item.ncr}]','/smis/board/list.do?bid=${item.bid}&adminmode=T','${item.pgid}','content');
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

</body>
</html>
