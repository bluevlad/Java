<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<link href="<c:out value="${MBOARD.PATH}"/>/css/board_m00.css" rel="stylesheet" type="text/css"></link>
<script language="JavaScript" src="<c:out value="${MBOARD.PATH}"/>/js/view.js" type="text/javascript"></script>
<script language="JavaScript">
function mboard_popup(bid, c_index, seq) {
	var urlname = "<mh:CPATH/>/mboard/viewimage.do?bid=" + bid + "&c_index=" + c_index +	"&seq="+seq ;
	browsing_window = window.open(urlname, "imagewin","top=10px,left=10px,status=yes,resizable=yes,location=false, menubar=no,status=no,scrollbars=no,width=10,height=10");
	browsing_window.focus();
}
</script>
<table width="95%" border="0" cellspacing="0" cellpadding="0" >
	<tr>
		<td>
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="view">
				<col width="10%"/>
				<col width="30%"/>
				<col width="10%"/>
				<col width="20%"/>
                <col width="10%"/>
                <col width="20%"/>
				<tr>
					<th><mfmt:message bundle="board" key="label.title"/></th>
					<td colspan="5">&nbsp;
						<mh:capsule value="${data.c_category}" left="[" right="]"/> 
						<mh:out value="${data.c_subject}"/>
					</td>
				</tr>
				<tr>
					<th><mfmt:message bundle="board" key="label.writer"/></th>
					<td>&nbsp;<mh:out value="${data.wname}" td="true"/></td>
                    <th><mfmt:message bundle="board" key="label.view"/></th>
                    <td>&nbsp;<mh:out value="${data.c_visit}"/></td>
                    <th><mfmt:message bundle="board" key="label.regdt"/></th>
                    <td>&nbsp;<mh:out value="${data.c_date}" format="fulldatetime" /></td>
                </tr>
				<tr>
					<td colspan="6"><mh:out value="${data.c_content}" escapeXml="false"/></td>
				</tr>
			</table>
		</td>
	</tr>
<!-- 첨부파일 -->	
<c:if test="${!empty(attItems)}">
	<c:choose>
		<c:when test="${MBOARD.board.fl_board_type=='data'}">
			<c:set var="jsp" value="${MBOARD.PATH}/inc/attach_data.jsp"/>
		</c:when>
		<c:otherwise>
			<c:set var="jsp" value="${MBOARD.PATH}/inc/attach.jsp"/>
		</c:otherwise>		
	</c:choose>
	<tr>
        <td>
            <table width=100% cellspacing="0" cellpadding="2" border="0">
                <tr>
                    <td><c:import url="${jsp}"/></td>
                </tr>
            </table>
        </td>
    </tr>
</c:if>
<!--  버튼 -->	
	<tr>
		<td align="right" style="padding-right:20px; padding-top:10px">
            <table width=100% cellspacing="0" cellpadding="2" border="0">
                <tr>
                    <td align="right">
                        <mh:import url="${MBOARD.PATH}/inc/viewBtn.jsp"/>
                        <mf:button onclick="go_list()" bundle="button" key="list" icon="icon_list" />
                    </td>
                </tr>
            </table>
        </td>
	</tr>
    <tr height="10">
        <td></td>
    </tr>
<c:if test="${MBOARD.board.fl_comment == 'T'}">
	<tr>
        <td><mh:import url="${MBOARD.PATH}/inc/cmt.jsp"/></td>
    </tr>
</c:if>
	<tr height="30">
        <td></td>
    </tr>
	<tr>
		<td>
			<img src='<c:out value="${MBOARD.PATH}"/>/images/bullet/view_dot_01.gif' alt="" border="0" align="absmiddle">&nbsp;<strong><mfmt:message bundle="board" key="title.board.prenext" /> </strong>
			<TABLE width="100%" border="0" cellspacing="0" cellpadding="2" class="list">
            <col width="60"/>
            <col width="350"/>
            <col width="*"/>
            <col width="*"/>
            <thead>
				<tr >
					<th align="center">#</th>
					<th align="center"><mfmt:message bundle="board" key="label.title"/></th>
					<th align="center"><mfmt:message bundle="board" key="label.writer"/></th>
					<th align="center"><mfmt:message bundle="board" key="label.regdt"/></th>
				</tr>
             </thead>
             <tbody>
				<c:forEach items="${refList}" var="data">
				<c:url var="url" value="view.do">
					<c:param name="c_index" value="${data.c_index}" />
					<c:param name="LISTOP" value="${MBOARD.listOpStr}" />
					<c:if test="${!empty(course)}">
						<c:param name="extleccode" value="${course.extleccode}" />
					</c:if>
				</c:url>
				<c:set var="st" value="bbs_td_cmt"/>
				<c:if test="${data.prevnext == 'C'}">
					<c:set var="st" value="bbs_td_cur"/>
				</c:if>
				
				<tr>
					<td class='<c:out value="${st}"/>' align="center"><c:choose>
						<c:when test="${data.prevnext=='N'}"><mfmt:message bundle="board" key="title.board.next"/></c:when>
						<c:when test="${data.prevnext=='C'}"><mfmt:message bundle="board" key="title.board.current"/></c:when>
						<c:when test="${data.prevnext=='P'}"><mfmt:message bundle="board" key="title.board.pre"/></c:when>
						</c:choose>
						<!--${data.c_index}--></td>
					<td class='<c:out value="${st}"/>' >
						<c:forEach var="i" begin="1" end="${data.c_level}">&nbsp;&nbsp;</c:forEach>
						<a href='<c:out value="${url}"/>'>
                        <mh:capsule value="${data.c_category}" left="[" right="]"/> 
						<mh:out value="${data.c_subject}"/>
						<c:if test="${MBOARD.board.fl_comment == 'T' && data.c_ccnt > 0}">
							<mh:out value="(${data.c_ccnt})"/>
						</c:if></a></td>
					<td '<c:out value="${st}"/>' align="center"><mh:out value="${data.wname}" td="true"/></td>
					<td '<c:out value="${st}"/>' align="center"><mh:out value="${data.c_date}" format="fulldatetime" /></td>
				</tr>
				</c:forEach>
               </tbody>
		      </table>
            </td>
	</tr>
</table>
<mh:import url="${MBOARD.PATH}/inc/frmBBS.jsp" />