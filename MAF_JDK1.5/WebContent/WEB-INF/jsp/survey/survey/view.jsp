<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>
<script>
    function doEdit(){
        var frm = getObject("myform");
        if(frm) {
            frm.cmd.value = "survey";
            frm.submit();
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
<mf:form action="${control_action}" method="post" name="myform" id="myform" >
<mf:input type="hidden" name="cmd" value=""/>
<mf:input type="hidden" name="surveyid" value="${item.surveyid}"/>
<mf:input type="hidden" name="qseq" value="${item.setid}"/>
<div class="viewContainer">
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
	<col width="15%" />
    <col width="35%" />
    <col width="15%" />
    <col width="35%" />
    <tr>
    	<th nowrap><mf:header name="surveytitle" /></th>
        <td colspan="3"><mh:out value="${item.surveytitle}" td="true"/></td>
    </tr>
    <tr>
    	<th nowrap><mf:header name="survey_sdat_t1" /></th>
        <td><mh:out value="${item.survey_sdat_t1}" td="true"/></td>
        <th nowrap><mf:header name="survey_edat_t1" /></th>
        <td><mh:out value="${item.survey_edat_t1}" td="true"/></td>
    </tr>
</table>
<br>
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
				<col width="50" />
				<col width="*" />
			    <col width="30" />
			    <col width="30"/>
			    <col width="250"/>
		        <c:choose>
		            <c:when test="${set.quetype == 's'}"><c:set var="itype" value="radio"/></c:when>
		            <c:when test="${set.quetype == 'm'}"><c:set var="itype" value="checkbox"/></c:when>
		        </c:choose>
			    <tr>
		        <c:choose>
		            <c:when test="${i == 1}">
			        	<td><input type='<c:out value="${itype}"/>' name='<c:out value="${set.queid}"/>' value='1' /></td>
			        	<td><c:out value="${set.queviw1}"/></td>
			        	<td><c:out value="${set.a1}"/></td>
			        	<td><fmt:formatNumber type="percent" value="${set.a1/set.asum}"/></td>
						<td><img src="<mh:out value="${CPATH}"/>/maf_images/graph/graph<c:out value="${i}"/>.gif" width="<c:out value="${(set.a1/set.asum)*200}"/>" height="25"/></td>
		            </c:when>
		            <c:when test="${i == 2}">
			        	<td><input type='<c:out value="${itype}"/>' name='<c:out value="${set.queid}"/>' value='2' /></td>
			        	<td><c:out value="${set.queviw2}"/></td>
			        	<td><c:out value="${set.a2}"/></td>
			        	<td><fmt:formatNumber type="percent" value="${set.a2/set.asum}"/></td>
						<td><img src="<mh:out value="${CPATH}"/>/maf_images/graph/graph<c:out value="${i}"/>.gif" width="<c:out value="${(set.a2/set.asum)*200}"/>" height="25"/></td>
		            </c:when>
		            <c:when test="${i == 3}">
			        	<td><input type='<c:out value="${itype}"/>' name='<c:out value="${set.queid}"/>' value='3' /></td>
			        	<td><c:out value="${set.queviw3}"/></td>
			        	<td><c:out value="${set.a3}"/></td>
			        	<td><fmt:formatNumber type="percent" value="${set.a3/set.asum}"/></td>
						<td><img src="<mh:out value="${CPATH}"/>/maf_images/graph/graph<c:out value="${i}"/>.gif" width="<c:out value="${(set.a3/set.asum)*200}"/>" height="25"/></td>
		            </c:when>
		            <c:when test="${i == 4}">
			        	<td><input type='<c:out value="${itype}"/>' name='<c:out value="${set.queid}"/>' value='4' /></td>
			        	<td><c:out value="${set.queviw4}"/></td>
			        	<td><c:out value="${set.a4}"/></td>
			        	<td><fmt:formatNumber type="percent" value="${set.a4/set.asum}"/></td>
						<td><img src="<mh:out value="${CPATH}"/>/maf_images/graph/graph<c:out value="${i}"/>.gif" width="<c:out value="${(set.a4/set.asum)*200}"/>" height="25"/></td>
		            </c:when>
		            <c:when test="${i == 5}">
			        	<td><input type='<c:out value="${itype}"/>' name='<c:out value="${set.queid}"/>' value='5' /></td>
			        	<td><c:out value="${set.queviw5}"/></td>
			        	<td><c:out value="${set.a5}"/></td>
			        	<td><fmt:formatNumber type="percent" value="${set.a5/set.asum}"/></td>
						<td><img src="<mh:out value="${CPATH}"/>/maf_images/graph/graph<c:out value="${i}"/>.gif" width="<c:out value="${(set.a5/set.asum)*200}"/>" height="25"/></td>
		            </c:when>
		            <c:when test="${i == 6}">
			        	<td><input type='<c:out value="${itype}"/>' name='<c:out value="${set.queid}"/>' value='6' /></td>
			        	<td><c:out value="${set.queviw6}"/></td>
			        	<td><c:out value="${set.a6}"/></td>
			        	<td><fmt:formatNumber type="percent" value="${set.a6/set.asum}"/></td>
						<td><img src="<mh:out value="${CPATH}"/>/maf_images/graph/graph<c:out value="${i}"/>.gif" width="<c:out value="${(set.a6/set.asum)*200}"/>" height="25"/></td>
		            </c:when>
		            <c:when test="${i == 7}">
			        	<td><input type='<c:out value="${itype}"/>' name='<c:out value="${set.queid}"/>' value='7' /></td>
			        	<td><c:out value="${set.queviw7}"/></td>
			        	<td><c:out value="${set.a7}"/></td>
			        	<td><fmt:formatNumber type="percent" value="${set.a7/set.asum}"/></td>
						<td><img src="<mh:out value="${CPATH}"/>/maf_images/graph/graph<c:out value="${i}"/>.gif" width="<c:out value="${(set.a7/set.asum)*200}"/>" height="25"/></td>
		            </c:when>
		            <c:when test="${i == 8}">
			        	<td><input type='<c:out value="${itype}"/>' name='<c:out value="${set.queid}"/>' value='8' /></td>
			        	<td><c:out value="${set.queviw8}"/></td>
			        	<td><c:out value="${set.a8}"/></td>
			        	<td><fmt:formatNumber type="percent" value="${set.a8/set.asum}"/></td>
						<td><img src="<mh:out value="${CPATH}"/>/maf_images/graph/graph<c:out value="${i}"/>.gif" width="<c:out value="${(set.a8/set.asum)*200}"/>" height="25"/></td>
		            </c:when>
		            <c:when test="${i == 9}">
			        	<td><input type='<c:out value="${itype}"/>' name='<c:out value="${set.queid}"/>' value='9' /></td>
			        	<td><c:out value="${set.queviw9}"/></td>
			        	<td><c:out value="${set.a9}"/></td>
			        	<td><fmt:formatNumber type="percent" value="${set.a9/set.asum}"/></td>
						<td><img src="<mh:out value="${CPATH}"/>/maf_images/graph/graph<c:out value="${i}"/>.gif" width="<c:out value="${(set.a9/set.asum)*200}"/>" height="25"/></td>
		            </c:when>
		            <c:when test="${i == 10}">
			        	<td><input type='<c:out value="${itype}"/>' name='<c:out value="${set.queid}"/>' value='10' /></td>
			        	<td><c:out value="${set.queviw10}"/></td>
			        	<td><c:out value="${set.a10}"/></td>
			        	<td><fmt:formatNumber type="percent" value="${set.a10/set.asum}"/></td>
						<td><img src="<mh:out value="${CPATH}"/>/maf_images/graph/graph<c:out value="${i}"/>.gif" width="<c:out value="${(set.a10/set.asum)*200}"/>" height="25"/></td>
		            </c:when>
		        </c:choose>
			    </tr>
			    </c:forEach>
			    </c:if>
			    <c:if test="${set.quetype == 't' || set.quetype == 'd'}">
			    <tr>
		        	<td><textarea name='<c:out value="${set.queid}"/>' style="width:100%" rows='3' required><c:out value="${set.usransw}"/></textarea></td>
				</tr>
			    </c:if>
			</table>
		</td>
    </tr>
    <tr>
        <td height="10">&nbsp;</td>
    </tr>
    </c:forEach>
</table>
<table border="0" cellpadding="2" cellspacing="0" class="viewBtn" width="100%">
    <tr>
    	<td align="center">
			<c:if test="${item.cnt - item.is_ok == 1}">
	    		<mf:button bundle="button" key="button.select" onclick="doEdit();" />
    		</c:if>
             <mf:button bundle="button" key="goList" onclick="goList()" />
    	</td>
    </tr>
</table>
</mf:form>
</div>