<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<c:set var="grp_img_path" value="${CPATH}/maf_images/graph"/>

<script>
    function goList() {
	    <c:url var="urlList" value="${control_action}">
	        <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
	    	<c:param name="cmd" value="list"/>
	    </c:url>
		document.location = '<mh:out value="${urlList}"/>';
	}
</script>

<div class="viewContainer">
<mf:form action="${control_action}" method="post" name="myform" id="myform" >
<mf:input type="hidden" name="cmd" value=""/>
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
	<c:forEach var="set" items="${set}">
	<mf:input type="hidden" name="v_queid" value="${set.queid}"/>
	<mf:input type="hidden" name="v_quetype" value="${set.quetype}"/>
    <tr>
        <th><mh:out value="${set.quetitle}" td="true"/></th>
    </tr>
    <tr>
        <td>
			<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
			    <c:if test="${set.quetype == 's' || set.quetype == 'm'}">
				<c:forEach var="i" begin="1" end="${set.quecount}">
				<col width="*" />
			    <col width="30" />
			    <col width="30"/>
			    <col width="250"/>
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
                <c:forEach var="ans" items="${ans}">
                <c:if test="${set.queid == ans.queid}">
			    <tr>
		        	<td colspan="4"><mh:out value="${ans.usransw}"/></td>
				</tr>
                </c:if>
                </c:forEach>
			    </c:if>
			</table>
		</td>
    </tr>
    <tr>
        <td height="30">&nbsp;</td>
    </tr>
    </c:forEach>
</table>
<table width="100%" border="0" cellpadding="2" cellspacing="0" class="viewBtn">
    <tr>
    	<td align="right"><mf:button bundle="button" key="list" onclick="goList()" icon="icon_list" /></td>
    </tr>
</table>
</mf:form>
</div>