<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<c:set var="grp_img_path" value="${CPATH}/maf_images/graph"/>
<SCRIPT LANGUAGE="JavaScript">
    window.resizeTo(750, 650);
</script>

<script language="javascript">
    function frmSubmit(frmName) {
        var frm = getObject(frmName);
        if(frm) {
            if (validate(frm)) {
                // 사용자 조건 추가.
                frm.cmd.value="update";
                frm.submit();
            }
        } else {
            alert ("form[" + frmName + "] is invalid");
        }
    }

    function goList() {
        <c:url var="urlList" value="${control_action}">
            <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
            <c:param name="cmd" value="list"/>
        </c:url>
        document.location = '<mh:out value="${urlList}"/>';
    }

</script>
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit=" frmSubmit('myform');return false;">
<mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}" />
<mf:input type="hidden" name="cmd" value=""/>
<div class="viewContainer">
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
	<col width="15%" />
    <col width="35%" />
    <col width="15%" />
    <col width="35%" />
    <tr>
        <th><mf:header name="surveyid" /></th>
        <td><mh:out value="${item.surveyid}" td="true"/></td>
        <th><mf:header name="survey_target" /></th>
        <td><mh:out value="${item.survey_target}" td="true"/></td>
    </tr>
    <tr>
        <th><mf:header name="surveytitle" /></th>
        <td colspan="3"><mh:out value="${item.surveytitle}" td="true"/></td>
    </tr>
    <tr>
        <th><mf:header name="surveydesc" /></th>
        <td colspan="3"><mh:out value="${item.surveydesc}" td="true"/></td>
    </tr>
    <tr>
        <th><mf:header name="survey_sdat" /></th>
        <td><mh:out value="${item.survey_sdat}" td="true"/></td>
        <th><mf:header name="survey_edat" /></th>
        <td><mh:out value="${item.survey_edat}" td="true"/></td>
    </tr>
</table>
<br>
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
	<c:forEach var="set" items="${rstlist}">
    <tr>
        <th><mh:out value="${set.quetitle}" td="true"/></th>
    </tr>
    <tr>
        <td>
			<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
				<col width="*" />
			    <col width="30" />
			    <col width="30"/>
			    <col width="250"/>
			    <c:if test="${set.quetype == 's' || set.quetype == 'm'}">
					<c:forEach var="i" begin="1" end="${set.quecount}">
			    <tr>
			        <c:choose>
			            <c:when test="${i == 1}">
				        	<td><c:out value="${set.queviw1}"/></td>
				        	<td><c:out value="${set.a1}"/></td>
				        	<td><fmt:formatNumber type="percent" value="${set.a1/set.asum}"/></td>
							<td><img src="<mh:out value="${grp_img_path}"/>/graph<c:out value="${i}"/>.gif" width="<c:out value="${(set.a1/set.asum)*200}"/>" height="25"/></td>
			            </c:when>
			            <c:when test="${i == 2}">
				        	<td><c:out value="${set.queviw2}"/></td>
				        	<td><c:out value="${set.a2}"/></td>
				        	<td><fmt:formatNumber type="percent" value="${set.a2/set.asum}"/></td>
							<td><img src="<mh:out value="${grp_img_path}"/>/graph<c:out value="${i}"/>.gif" width="<c:out value="${(set.a2/set.asum)*200}"/>" height="25"/></td>
			            </c:when>
			            <c:when test="${i == 3}">
				        	<td><c:out value="${set.queviw3}"/></td>
				        	<td><c:out value="${set.a3}"/></td>
				        	<td><fmt:formatNumber type="percent" value="${set.a3/set.asum}"/></td>
							<td><img src="<mh:out value="${grp_img_path}"/>/graph<c:out value="${i}"/>.gif" width="<c:out value="${(set.a3/set.asum)*200}"/>" height="25"/></td>
			            </c:when>
			            <c:when test="${i == 4}">
				        	<td><c:out value="${set.queviw4}"/></td>
				        	<td><c:out value="${set.a4}"/></td>
				        	<td><fmt:formatNumber type="percent" value="${set.a4/set.asum}"/></td>
							<td><img src="<mh:out value="${grp_img_path}"/>/graph<c:out value="${i}"/>.gif" width="<c:out value="${(set.a4/set.asum)*200}"/>" height="25"/></td>
			            </c:when>
			            <c:when test="${i == 5}">
				        	<td><c:out value="${set.queviw5}"/></td>
				        	<td><c:out value="${set.a5}"/></td>
				        	<td><fmt:formatNumber type="percent" value="${set.a5/set.asum}"/></td>
							<td><img src="<mh:out value="${grp_img_path}"/>/graph<c:out value="${i}"/>.gif" width="<c:out value="${(set.a5/set.asum)*200}"/>" height="25"/></td>
			            </c:when>
			            <c:when test="${i == 6}">
				        	<td><c:out value="${set.queviw6}"/></td>
				        	<td><c:out value="${set.a6}"/></td>
				        	<td><fmt:formatNumber type="percent" value="${set.a6/set.asum}"/></td>
							<td><img src="<mh:out value="${grp_img_path}"/>/graph<c:out value="${i}"/>.gif" width="<c:out value="${(set.a6/set.asum)*200}"/>" height="25"/></td>
			            </c:when>
			            <c:when test="${i == 7}">
				        	<td><c:out value="${set.queviw7}"/></td>
				        	<td><c:out value="${set.a7}"/></td>
				        	<td><fmt:formatNumber type="percent" value="${set.a7/set.asum}"/></td>
							<td><img src="<mh:out value="${grp_img_path}"/>/graph<c:out value="${i}"/>.gif" width="<c:out value="${(set.a7/set.asum)*200}"/>" height="25"/></td>
			            </c:when>
			            <c:when test="${i == 8}">
				        	<td><c:out value="${set.queviw8}"/></td>
				        	<td><c:out value="${set.a8}"/></td>
				        	<td><fmt:formatNumber type="percent" value="${set.a8/set.asum}"/></td>
							<td><img src="<mh:out value="${grp_img_path}"/>/graph<c:out value="${i}"/>.gif" width="<c:out value="${(set.a8/set.asum)*200}"/>" height="25"/></td>
			            </c:when>
			            <c:when test="${i == 9}">
				        	<td><c:out value="${set.queviw9}"/></td>
				        	<td><c:out value="${set.a9}"/></td>
				        	<td><fmt:formatNumber type="percent" value="${set.a9/set.asum}"/></td>
							<td><img src="<mh:out value="${grp_img_path}"/>/graph<c:out value="${i}"/>.gif" width="<c:out value="${(set.a9/set.asum)*200}"/>" height="25"/></td>
			            </c:when>
			            <c:when test="${i == 10}">
				        	<td><c:out value="${set.queviw10}"/></td>
				        	<td><c:out value="${set.a10}"/></td>
				        	<td><fmt:formatNumber type="percent" value="${set.a10/set.asum}"/></td>
							<td><img src="<mh:out value="${grp_img_path}"/>/graph<c:out value="${i}"/>.gif" width="<c:out value="${(set.a10/set.asum)*200}"/>" height="25"/></td>
			            </c:when>
			        </c:choose>
			    </tr>
				    </c:forEach>
			    </c:if>
			    <c:if test="${set.quetype == 't' || set.quetype == 'd'}">
					<c:forEach var="item" items="${anslist}">
					    <c:if test="${set.queid == item.queid}">
			    <tr>
		        	<td height="25" colspan="4">[<c:out value="${item.usn}"/>]&nbsp;&nbsp;
		        	<c:out value="${item.usransw}"/></td>
				</tr>
					    </c:if>
					</c:forEach>
				</c:if>
			</table>
        </td>
    </tr>
    </c:forEach>
</table>
</div>
<table border="0" cellpadding="2" cellspacing="0" class="viewBtn" width="100%">
    <tr>
        <td align="right"><mf:button bundle="button" key="list" onclick="goList()" icon="icon_list" /></td>
    </tr>
</table>
</mf:form>