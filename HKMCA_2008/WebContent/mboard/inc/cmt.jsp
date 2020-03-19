<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<script language="JavaScript" src='<c:out value="${MBOARD.PATH}"/>/js/cmt.js' type="text/javascript"></script>

<form action="${MBOARD.PATH}/act.do" method="post" name="frmCmt" id="frmCmt" onSubmit="return validate(this)">
<mf:input type="hidden" name="cmd" value="cmt_add"/>
<mf:input type="hidden" name="c_id" value=""/>
<mf:input type="hidden" name="LISTOP" value="${MBOARD.listOpStr}>"/>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
	<!-- 코멘트 입력하기 시작 -->
	<tr>
		<td>
			<c:if test="${!empty(sessionScope.msession)}">
				<c:import url="${MBOARD.PATH}/inc/cmt_Auth.jsp"/>
			</c:if>
		</td>
	</tr>
	<c:if test="${!empty cmtLists}">
		<tr>
			<td>
                <table width="100%" cellspacing="0" cellpadding="2" border=0 class="bbs_table">
					<tr>
						<td class="bbs_view_top_th" width="100"><mfmt:message bundle="board" key="label.writer"/></td>
						<td class="bbs_view_top_th" width="450"><mfmt:message bundle="board" key="label.title"/></td>
						<td class="bbs_view_top_th" width="120"><mfmt:message bundle="board" key="label.regdt"/></td>
					</tr>
        			<c:forEach items="${cmtLists}" var="data">
					<tr>
						<td class="bbs_view_th" width="100">
                            <img src='<c:out value="${MBOARD.PATH}/images/icon/${data.c_opnion}.gif"/>' border="0" align="absmiddle">
                            <mh:out value="${data.wname}"/>
                        </td>
						<td class="bbs_view_td"><mh:out nl2br="true" value="${data.c_memo}"/>
		                    <c:if test="${!empty(sessionScope.msession)}">
                                <c:if test="${data.usn == sessionScope.msession.usn}">
                                    <img src='<c:out value="${MBOARD.PATH}/images/button/cmt_delete.gif"/>' onClick="cmtDelete('<c:out value="${data.c_id}"/>')" align="absmiddle" border=0 style="cursor:pointer;">
                                </c:if>
		                    </c:if>
						</td>
						<td class="bbs_view_td" align="center" width="120"><mh:out value="${data.c_date}" format="fulldatetime" /></td>
					</tr>
        			</c:forEach>
				</table>
            </td>
		</tr>			
	</c:if>
</table>
</form>