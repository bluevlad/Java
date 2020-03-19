<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<html>
<head>
	<script language="javascript" src="${CPATH}/js/fusionChart.js"></script>
	<SCRIPT LANGUAGE="JavaScript">
		window.resizeTo(400, 300);
	</SCRIPT>
</head>

<body topmargin="0" rightmargin="0" bottommargin="0" marginwidth="0">
<table border="0" cellspacing="0" cellpadding="2" width="100%">
    <tr>
		<td>
            <img src="<mh:out value="${CPATH}"/>/maf_images/poll/ico_q.gif" alt="" width="14" height="15" border="0">
            <mh:out value="${poll.title}"/>
		</td>
	</tr>
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
					<th class="left" height="30"><mh:out value="${item.question}"/></th>
					<td class="left">
                        <img src="<mh:out value="${CPATH}"/>/maf_images/poll/poll_bar0<mh:out value="${item.rank%3}" format="round"/>.gif" height="12" width="<mh:out value="${item.perc*400}"/>">
					</td>
					<td class="right">
                        <mh:out value="${item.hit}"/>표
					</td>
				</tr>
                </c:forEach>
			</table>
		</td>
	</tr>
	<tr>
		<td>
                            기간: <mh:out value="${poll.start_dt}" td="true"/> ~ <mh:out value="${poll.end_dt}" td="true"/>
          &nbsp;&nbsp;응답자수 : <mh:out value="${total}"/>명 
		</td>
	</tr>
</table>
</body>
</html>