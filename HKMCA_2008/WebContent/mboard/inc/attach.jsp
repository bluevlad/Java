<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<c:set var="etcCnt" value="0"/>
<c:set var="imgCnt" value="0"/>
<c:forEach items="${attItems}" var="data">	
    <mh:indexOf var="idx" value="${data.content_type}" needle="image"/>
	<c:choose>
		<c:when test="${idx <0}">
			<c:set var="etcCnt" value="${etcCnt+1}"/>
		</c:when>
		<c:otherwise>
			<c:set var="imgCnt" value="${imgCnt+1}"/>
		</c:otherwise>
	</c:choose>
</c:forEach>

<c:if test="${imgCnt>0}">	
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td class="td">
            <div style="width:650px;height:100px;overflow:auto;">
            <table border=0 cellspacing=0 cellpadding=1>
                <tr>
                <c:forEach items="${attItems}" var="data">	
                <mh:indexOf var="idx" value="${data.content_type}" needle="image"/>
				<c:if test="${idx >-1}">
					<c:url var="imgUrl" value="/mbbsfile/${MBOARD.bid}/thumb/${data.real_filename}"/>
		            <td width="100"><img src='<c:out value="${imgUrl}"/>' border="0" width="80" style="cursor: pointer;" onClick="mboard_popup(<c:out value="'${MBOARD.bid}', '${data.c_index}', '${data.seq}'"/>)"></td>
				</c:if>
                </c:forEach>
                </tr>
            </table>
            </div>
        </td>
    </tr>
</table>
</c:if>

<c:if test="${etcCnt>0}">	
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <strong><mfmt:message bundle="board" key="title.board.uploadfile"/></strong>
		<td class="td">
            <table border="0" cellspacing="0" cellpadding="2">
				<c:forEach items="${attItems}" var="data">	
	            <mh:indexOf var="idx" value="${data.content_type}" needle="image"/>
				<c:if test="${idx <0}">
				<c:url var="imgUrl" value="/mbbsfile/${MBOARD.bid}/${data.real_filename}"/>
                <tr>
                    <td>&nbsp;<a href="<c:out value="${imgUrl}"/>?cmd=save" target="_blank"><img src='<c:out value="${MBOARD.PATH}"/>/images/paperclip.gif' alt="" border="0"></a></td>
                    <td><a href='<c:out value="${imgUrl}"/>' target="_blank"><c:out value="${data.ori_filename}"/>&nbsp;&nbsp[<c:out value="${data.file_size}"/>]KB</a></td>
                </tr>
				</c:if>
				</c:forEach>
            </table>
        </td>
    </tr>
</table>
</c:if>