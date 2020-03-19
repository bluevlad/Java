<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <strong><mfmt:message bundle="board" key="title.board.uploadfile"/></strong>
		<td class="td">
            <table border="0" cellspacing="0" cellpadding="2">
                <c:forEach items="${attItems}" var="data">	
                <c:url var="imgUrl" value="/mbbsfile/${MBOARD.bid}/${data.real_filename}"/>
				<tr>
                    <td>&nbsp;<a href='<mh:out value="${imgUrl}"/>?cmd=save' target="_blank"><img src="${MBOARD.PATH}/images/paperclip.gif" alt=""  border="0"></a></td>
                    <td><a href='<mh:out value="${imgUrl}"/>' target="_blank"><mh:out value="${data.ori_filename}"/></a></td>
				</tr>
                </c:forEach>
            </table>
        </td>
    </tr>
</table>