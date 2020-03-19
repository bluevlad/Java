<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript">
<!--
function goList()   {
    <c:url var="urlList" value="${control_action}">
        <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
        <c:param name="cmd" value="list"/>
    </c:url>
    document.location = "<mh:out value="${urlList}"/>";
}                
//-->
</script>

<div class="viewContainer">
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit=" frmSubmit('myform');return false;">
<mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}"/>
<mf:input type="hidden" name="cmd" value="list"/>  
<mf:input type="hidden" name="lec_cd" value="${lec_cd}"/>  
<table border="0" cellpadding="2" cellspacing="0" width="100%">  
    <tr>
        <td>
            <div class="listContainer">
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list">
                <col width="10%">
                <col width="40%">
                <col width="25%">
                <col width="25%">
                <thead>
                <tr>
                    <th><mfmt:message bundle="table.wlc_lec_chp" key="itm_sequence"/></th>
                    <th><mfmt:message bundle="table.wlc_lec_chp" key="itm_title"/></th>
                    <th><mfmt:message bundle="table.wlc_rat_mst" key="jindo_page"/></th>
                    <th><mfmt:message bundle="table.wlc_rat_mst" key="jindo_time"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="sitem" items="${item}" varStatus="status">
                <c:set var="avg_page" value=""/>
                <c:choose>
                <c:when test="${sitem.t_page > 0}">
                    <fmt:formatNumber var="avg_page" value="${(sitem.j_page*100.0)/(sitem.t_page)}" pattern="###"/>
                </c:when>
                <c:otherwise>
                    <c:set var="avg_page" value="0"/>
                </c:otherwise>
                </c:choose>
                <c:set var="avg_time" value=""/>
                <c:choose>
                <c:when test="${sitem.t_time > 0}">
                    <fmt:formatNumber var="avg_time" value="${(sitem.j_time*100.0)/(sitem.t_time)}" pattern="###"/>
                </c:when>
                <c:otherwise>
                    <c:set var="avg_time" value="100"/>
                </c:otherwise>
                </c:choose>
                <tr>
                    <td align="center"><mh:out value="${sitem.itm_sequence}" td="true" /></td>
                    <td align="left"><mh:out value="${sitem.itm_title}" td="true" /></td>
                    <c:choose>
                    <c:when test="${avg_page >= 100}">
                    <td align=left>
                        <img src='<c:url value="/maf_images/graph/graph10.gif"/>' width='100' height='12'/>100.0% 
                        (<mh:out value="${sitem.t_page}" td="true" />P / <mh:out value="${sitem.t_page}" td="true" />P)
                    </td>
                    </c:when>
                    <c:otherwise>
                    <td align=center>
                        <c:if test="${avg_page > 0.0}">
                        <img src="<mh:out value="${CPATH}"/>/maf_images/graph/graph10.gif" width="<jm:out value="${avg_page}" />" height="12"/>
                        </c:if>
                        <mh:out value="${avg_page}" td="true" />% (<mh:out value="${sitem.j_page}" td="true" />P/<mh:out value="${sitem.t_page}" td="true" />P)
                    </td>
                    </c:otherwise>
                    </c:choose>  
                    <td align=center>
                        <c:if test="${avg_time > 0.0}">
                        <img src="<mh:out value="${CPATH}"/>/maf_images/graph/graph10.gif" width="<mh:out value="${avg_time}"  />" height="12"/>
                        </c:if>
                        <mh:out value="${avg_time}" td="true" />% (<fmt:formatNumber var="s_avg_time" value="${(sitem.s_time/60)}" pattern="###"/><mh:out value="${s_avg_time}" td="true" /><mfmt:message bundle="common" key="time.min"/>)
                    </td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
            </div>
        </td>
    </tr>
</table>
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
    <tr>
        <th width=35% align="center"><mfmt:message bundle="common" key="total.list"/><mfmt:message bundle="common" key="wlc_chp_prg.total_time"/></th>
        <td width=15% align="right" bgcolor="#FFFFFF"><mh:out value="${totaltime}" td="true" /><mfmt:message bundle="common" key="time.min"/></td>
        <th width=35% align=center><mfmt:message bundle="common" key="wlc_chp_prg.limit_study_time"/></th>
        <td width=15% align=right bgcolor="#FFFFFF"><mh:out value="${limit_study_time}" td="true" /><mfmt:message bundle="common" key="time.min"/></td>
    </tr>
</table>
<table width="100%" border="0" cellpadding="2" cellspacing="0" class="viewBtn" class="viewBtn">   
    <tr>
        <td align="right"><mf:button bundle="button" key="list" onclick="goList()" icon="icon_add"/></td>
    </tr>
</table>
</mf:form>
</div>