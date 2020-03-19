<%@ page contentType="application/vnd.ms-excel; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<div class="viewContainer">
<table border="1" cellpadding="2" cellspacing="0" class="view" width="100%">
	<col width="15%" />
    <col width="35%" />
    <col width="15%" />
    <col width="35%" />
    <tr>
        <th><mf:header name="surveyid" /></th>
        <td><mh:out value="${item.surveyid}" td="true"/></td>
        <th><mf:header name="surveyowner" /></th>
        <td><mh:out value="${item.surveyowner}" td="true"/></td>
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
        <th><mf:header name="survey_sdat_t1" /></th>
        <td><mh:out value="${item.survey_sdat_t1}" td="true"/></td>
        <th><mf:header name="survey_edat_t1" /></th>
        <td><mh:out value="${item.survey_edat_t1}" td="true"/></td>
    </tr>
</table>

<table border="1" cellpadding="2" cellspacing="0" class="view" width="100%">
	<c:forEach var="set" items="${rstlist}">
    <tr>
        <th><mh:out value="${set.quetitle}" td="true"/></th>
    </tr>
    <tr>
        <td>
			<table border="1" cellpadding="2" cellspacing="0" class="view" width="100%">
				<col width="*" />
			    <col width="30" />
			    <col width="30"/>
			    <c:if test="${set.quetype == 's' || set.quetype == 'm'}">
					<c:forEach var="i" begin="1" end="${set.quecount}">
			    <tr>
			        <c:choose>
			            <c:when test="${i == 1}">
				        	<td><c:out value="${set.queviw1}"/></td>
				        	<td><c:out value="${set.a1}"/></td>
				        	<td><fmt:formatNumber type="percent" value="${set.a1/set.asum}"/></td>
			            </c:when>
			            <c:when test="${i == 2}">
				        	<td><c:out value="${set.queviw2}"/></td>
				        	<td><c:out value="${set.a2}"/></td>
				        	<td><fmt:formatNumber type="percent" value="${set.a2/set.asum}"/></td>
			            </c:when>
			            <c:when test="${i == 3}">
				        	<td><c:out value="${set.queviw3}"/></td>
				        	<td><c:out value="${set.a3}"/></td>
				        	<td><fmt:formatNumber type="percent" value="${set.a3/set.asum}"/></td>
			            </c:when>
			            <c:when test="${i == 4}">
				        	<td><c:out value="${set.queviw4}"/></td>
				        	<td><c:out value="${set.a4}"/></td>
				        	<td><fmt:formatNumber type="percent" value="${set.a4/set.asum}"/></td>
			            </c:when>
			            <c:when test="${i == 5}">
				        	<td><c:out value="${set.queviw5}"/></td>
				        	<td><c:out value="${set.a5}"/></td>
				        	<td><fmt:formatNumber type="percent" value="${set.a5/set.asum}"/></td>
			            </c:when>
			            <c:when test="${i == 6}">
				        	<td><c:out value="${set.queviw6}"/></td>
				        	<td><c:out value="${set.a6}"/></td>
				        	<td><fmt:formatNumber type="percent" value="${set.a6/set.asum}"/></td>
			            </c:when>
			            <c:when test="${i == 7}">
				        	<td><c:out value="${set.queviw7}"/></td>
				        	<td><c:out value="${set.a7}"/></td>
				        	<td><fmt:formatNumber type="percent" value="${set.a7/set.asum}"/></td>
			            </c:when>
			            <c:when test="${i == 8}">
				        	<td><c:out value="${set.queviw8}"/></td>
				        	<td><c:out value="${set.a8}"/></td>
				        	<td><fmt:formatNumber type="percent" value="${set.a8/set.asum}"/></td>
			            </c:when>
			            <c:when test="${i == 9}">
				        	<td><c:out value="${set.queviw9}"/></td>
				        	<td><c:out value="${set.a9}"/></td>
				        	<td><fmt:formatNumber type="percent" value="${set.a9/set.asum}"/></td>
			            </c:when>
			            <c:when test="${i == 10}">
				        	<td><c:out value="${set.queviw10}"/></td>
				        	<td><c:out value="${set.a10}"/></td>
				        	<td><fmt:formatNumber type="percent" value="${set.a10/set.asum}"/></td>
			            </c:when>
			        </c:choose>
			    </tr>
				    </c:forEach>
			    </c:if>
			    <c:if test="${set.quetype == 't' || set.quetype == 'd'}">
					<c:forEach var="item" items="${anslist}">
					    <c:if test="${set.queid == item.queid}">
			    <tr>
		        	<td colspan="3">[<c:out value="${item.usn}"/>]&nbsp;&nbsp;
		        	<c:out value="${item.usransw}"/></td>
				</tr>
					    </c:if>
					</c:forEach>
				</c:if>
			</table>
        	<table border="1" cellpadding="2" cellspacing="0" class="view" width="100%">
    			<tr>
			    <col width="100" />
			    <col width="100"/>
			    <col width="200" />
			    <col width="50"/>
			    <col width="100"/>
				<col width="*" />
    				<th><mf:header name="region" /></th>
    				<th><mf:header name="org_type" /></th>
    				<th><mf:header name="org_name" /></th>
    				<th><mf:header name="userid" /></th>
    				<th><mf:header name="nm" /></th>
    				<th>#</th>
    			</tr>
				<c:forEach var="item" items="${anslist}">
		    		<c:if test="${set.queid == item.queid}">
	    			<tr>
	       				<td class="center"><mh:out value="${item.region}" codeGroup="REGION" td="true"/></td>
					    <td class="center"><mh:out value="${item.org_type}" codeGroup="ORG_TYPE" td="true"/></td>
	       				<td class="center"><mh:out value="${item.org_name}" td="true"/></td>
	       				<td class="center"><mh:out value="${item.usn}" td="true"/></td>
	       				<td class="center"><mh:out value="${item.nm}" td="true"/></td>
	       				<td class="center"><mh:out value="${item.usransw}" td="true"/></td>
					</tr>
		    		</c:if>
				</c:forEach>
			</table>
        </td>
    </tr>
    <tr>
    	<td height="25">&nbsp;</td>
    </tr>
    </c:forEach>
</table>
</div>