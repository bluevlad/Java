<%@ page contentType="text/html; charset=euc-kr"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<jsp:include  page="/layout/common/commonHead.jsp" flush="true"/> 
	<style type="text/css">
		td.tabactive a{color:#ffffff;}
		td.tabactive{background:#507c9a;}
		td.tabactive{background:#507c9a url("/ehrd/css/sun/im/bg_titlebar.gif") no-repeat left top;border-right:1px solid #FFF;padding:0px}
		td.tabactive div{background:url("/ehrd/css/sun/im/a2_corner_tr.gif") no-repeat right top;padding:9px 7px 5px 7px}
		td.tab{background:#CCDAE3 url("/ehrd/css/sun/im/d7_tab_bg.gif") repeat-x;border-right:1px solid #FFF;border-top:3px solid #FFF}
		td.tab div{background:url("/ehrd/css/sun/im/corner_navblue_tr.gif") no-repeat right top;padding:9px 7px 5px 7px}
		td.tab div{padding:6px 7px 0px 7px}
		td.tab div a:visited{color:#35556B}
	</style>
</head>
<body leftmargin="0" topmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" >
<br><br> 
<table border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td nowrap="nowrap" class="tabactive" valign="top"><div><a href="${control_action}?cmd=rcvList">받은쪽지</a></div></td>
		<td nowrap="nowrap" class="tab" valign="top"><div><a href="${control_action}?cmd=sndList">보낸쪽지</a></div></td>
		<td nowrap="nowrap" class="tab" valign="top"><div><a href="${control_action}?cmd=msgWrite">쪽지쓰기</a></div></td>
		<td nowrap="nowrap" class="tab" valign="top"><div><a href="${control_action}?cmd=addressbook">주소록</a></div></td>
	</tr> 
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td>
    <tr>
        <td><jsp:include page="${MAF_INFO.file}" flush="true"/> <td>
    </tr>
    <tr>
        <th> footer </th>
    </tr>
</table>    

</body>
</html>
<jsp:include  page="/layout/common/commonTailInfo.jsp" flush="true"/> 