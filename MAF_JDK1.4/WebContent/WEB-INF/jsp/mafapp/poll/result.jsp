<%@ page contentType="text/html; charset=euc-kr"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<jsp:include  page="/layout/common/commonHead.jsp" flush="true"/> 
	<script language="javascript"	src="${CPATH}/js/fusionChart.js"></script>
	<SCRIPT LANGUAGE="JavaScript">
		window.resizeTo(750, 650);
	</SCRIPT>
</head>

<body  topmargin="0" rightmargin="0" bottommargin="0" marginwidth="0">
<table border="0" cellspacing="0" cellpadding="2">
	<tr>
		<td><img src="${CPATH}/maf_images/poll/ico_q.gif" alt="" width="14" height="15" border="0" align="absmiddle"> ${poll.title}</td>
	<tr>
		<td>
			<table border="0" cellspacing="0" cellpadding="2">
				<col width="200"/>
				<col width="400"/>
				<col width="200"/>
			<c:set var="total" value="0"/>
			<c:forEach var="item" items="${items}" varStatus="status">
				<c:set var="total" value="${total + item.hit}"/>
				<tr>
					<th class="left" height="30">${item.question}</th>
					<td class="left"><img src="${CPATH}/maf_images/poll/poll_bar01.gif" height="12" width="${item.perc*400}"></td>
					<td class="right">${mfmt:number_format(item.hit,0)}표 (${mfmt:number_format(item.perc*100,2)}%)</td>
				</tr>
			</c:forEach>
			</table>
		</td>
	</tr>
	<tr>
		<td>기간: ${mfmt:shortDate(poll.start_dt)} ~ ${mfmt:shortDate(poll.end_dt)}&nbsp;&nbsp;
			응답자수 : ${mfmt:number_format(total,0)}명 </td>
	</tr>
</body>
</html>
