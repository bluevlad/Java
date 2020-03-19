<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript"   >
<!--
function goBack()   {
    var frm = getObject("myform");
    frm.submit();    
}
//-->
</script>

<mf:form action="${control_action}" method="post" name="myform" id="myform">
<mf:input type="hidden" name="cmd" value="view"/>
<mf:input type="hidden" name="usn" value="${usn}" />
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="view">
    <col width="75">
    <thead>
    <tr>
		<th><mfmt:message bundle="table.wlc_lec_chp" key="itm_sequence"/></th>
		<th><mfmt:message bundle="table.wlc_lec_chp" key="itm_title"/></th>
		<th><mfmt:message bundle="table.wlc_rat_mst" key="jindo_page"/></th>
		<th><mfmt:message bundle="table.wlc_rat_mst" key="jindo_time"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="sitem" items="${navigator.list}" varStatus="status">
    <c:set var="avg_page" value=""/>
    <c:choose>
    <c:when test="${sitem.t_page > 0}">
    <fmt:formatNumber var="avg_page" value="${(sitem.j_page*100.0)/(sitem.t_page)}" pattern="###.#"/>
    </c:when>
    <c:otherwise>
    <c:set var="avg_page" value="0"/>
    </c:otherwise>
    </c:choose>
    <c:set var="avg_time" value=""/>
    <c:choose>
    <c:when test="${sitem.t_time > 0}">
    <fmt:formatNumber var="avg_time" value="${(sitem.j_time*100.0)/(sitem.t_time)}" pattern="###.#"/>
    </c:when>
    <c:otherwise>
    <c:set var="avg_time" value="100"/>
    </c:otherwise>
    </c:choose>
    <tr>
		<td align="center"><mh:out value="${sitem.itm_sequence}" td="true" /></td>
		<td align="left"><mh:out value="${sitem.itm_title}" td="true" /></td>
        <c:choose>
        <c:when test="${avg_page > 100}">
        <td align=left>
            <img src='<c:url value="/jmf_images/graph/graph10.gif"/>' width="100" height="12"/>100.0% 
            (<mh:out value="${sitem.t_page}" td="true" />P/<mh:out value="${sitem.t_page}" td="true" />P)
        </td>
		</c:when>
		<c:otherwise>
        <td align=left>
            <c:if test="${avg_page > 0.0}">
            <img src='<c:url value="/jmf_images/graph/graph10.gif"/>' width="<mh:out value="${avg_page}" />" height="12"/>
            </c:if>
            <mh:out value="${avg_page}" td="true" />% (<mh:out value="${sitem.j_page}" td="true" />P/<mh:out value="${sitem.t_page}" td="true" />P)
        </td>
        </c:otherwise>
        </c:choose>  
        <td align=left>
            <c:if test="${avg_time > 0.0}">
            <img src='<c:url value="/jmf_images/graph/graph10.gif"/>' width="<mh:out value="${avg_time}" />" height="12"/>
            </c:if>
            <mh:out value="${avg_time}" td="true" />% (<fmt:formatNumber var="s_avg_time" value="${(sitem.s_time/60)}" pattern="###"/><mh:out value="${s_avg_time}" td="true" /><mfmt:message bundle="common" key="time.min"/>)
        </td>
    </tr>
    </c:forEach>
    </tbody>
</table>
<br/>
<table width="100%" border="0" cellpadding="2" cellspacing="0" class="view">
    <tr>
        <th width=35% align="center"><mfmt:message bundle="common" key="total.list"/><mfmt:message bundle="common" key="wlc_chp_prg.total_time"/></th>
        <td width=15% align="right" bgcolor="#FFFFFF"><mh:out value="${totaltime}" td="true" /><mfmt:message bundle="common" key="time.min"/></td>
        <th width=35% align=center><mfmt:message bundle="common" key="wlc_chp_prg.limit_study_time"/></th>
        <td width=15% align=right bgcolor="#FFFFFF"><mh:out value="${limit_study_time}" td="true" /><mfmt:message bundle="common" key="time.min"/></td>
    </tr>
</table>
<br>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="viewBtn">   
    <tr>
        <td align="right"><mf:button bundle="button"  key="list" onclick="goBack()" icon="icon_list"/></td>
    </tr>
</table>
</mf:form>