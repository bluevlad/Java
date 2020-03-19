<%@ page contentType="text/html; charset=euc-kr"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<jsp:include  page="/layout/common/commonHead.jsp" flush="true"/> 

</head>
<body leftmargin="0" topmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" >

<table width="90%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td>
			<ul class="tab menu compact">
				<li class="${(tabIndex=="listRcv")?'on':''}"><a href="${control_action}?cmd=listRcv"><mfmt:message bundle="mafpager" key="tab.rcvbox"/></a></li>
				<li class="${(tabIndex=="listSnd")?'on':''}"><a href="${control_action}?cmd=listSnd"><mfmt:message bundle="mafpager" key="tab.sndbox"/></a></div></li>
				<li class="${(tabIndex=="msgWrite")?'on':''}"><a href="${control_action}?cmd=msgWrite"><mfmt:message bundle="mafpager" key="tab.writemsg"/></a></li>
				<li class="${(tabIndex=="addressBook")?'on':''}"><a href="${control_action}?cmd=addressBook"><mfmt:message bundle="mafpager" key="tab.contacts"/></a></li>
			</ul>
		</td>
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