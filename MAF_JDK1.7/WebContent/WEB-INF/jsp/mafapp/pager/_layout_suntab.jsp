<%@ page contentType="text/html; charset=euc-kr"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<jsp:include  page="/layout/common/commonHead.jsp" flush="true"/> 
	<style type="text/css">
		td.suntabactive a{color:#ffffff;}
		td.suntabactive{background:#507c9a;}
		td.suntabactive{background:#507c9a url("/ehrd/css/sun/im/bg_titlebar.gif") no-repeat left top;border-right:1px solid #FFF;padding:0px}
		td.suntabactive div{background:url("/ehrd/css/sun/im/a2_corner_tr.gif") no-repeat right top;padding:9px 7px 5px 7px}
		td.suntab{background:#CCDAE3 url("/ehrd/css/sun/im/d7_tab_bg.gif") repeat-x;border-right:1px solid #FFF;border-top:3px solid #FFF}
		td.suntab div{background:url("/ehrd/css/sun/im/corner_navblue_tr.gif") no-repeat right top;padding:9px 7px 5px 7px}
		td.suntab div{padding:6px 7px 0px 7px}
		td.suntab div a:visited{color:#35556B}
	</style>
</head>
<body leftmargin="0" topmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" >
<br><br> 
<table width="90%" border="0" cellpadding="0" cellspacing="0" align="center">
	<tr>
		<td nowrap="nowrap" class="suntab${(tabIndex=="listRcv")?'active':''} center" valign="top"><div><a href="${control_action}?cmd=listRcv"><mfmt:message bundle="mafpager" key="tab.rcvbox"/></a></div></td>
		<td nowrap="nowrap" class="suntab${(tabIndex=="listSnd")?'active':''} center" valign="top"><div><a href="${control_action}?cmd=listSnd"><mfmt:message bundle="mafpager" key="tab.sndbox"/></a></div></td>
		<td nowrap="nowrap" class="suntab${(tabIndex=="msgWrite")?'active':''} center" valign="top"><div><a href="${control_action}?cmd=msgWrite"><mfmt:message bundle="mafpager" key="tab.writemsg"/></a></div></td>
		<td nowrap="nowrap" class="suntab${(tabIndex=="addressBook")?'active':''} center" valign="top"><div><a href="${control_action}?cmd=addressBook"><mfmt:message bundle="mafpager" key="tab.contacts"/></a></div></td>

	</tr> 
</table>
<table width="90%" border="0" cellspacing="0" cellpadding="0" align="center">
    <tr>
        <td>
    <tr>
        <td align="center"><jsp:include page="${MAF_INFO.file}" flush="true"/> <td>
    </tr>
    <tr>
        <th> footer </th>
    </tr>
</table>    

</body>
</html>
<jsp:include  page="/layout/common/commonTailInfo.jsp" flush="true"/> 