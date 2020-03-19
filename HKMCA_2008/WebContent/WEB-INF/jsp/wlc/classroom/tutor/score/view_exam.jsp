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

<mf:form action="${control_action}" method="post" name="myform" id="myform" >
<mf:input type="hidden" name="cmd" value="view"/>
<mf:input type="hidden" name="usn" value="${usn}"/>
<div class="listContainer">
<table border="0" cellpadding="2" cellspacing="1" class="list" width="100%"> 
    <col width="*">
    <col width="15%">
    <col width="10%">
    <col width="15%">
    <col width="10%">
    <thead>
    <tr>
        <th><mfmt:message bundle="table.exm_mst" key="exmtitle" /></th>
        <th><mfmt:message bundle="table.exm_mst" key="rst_sdt" /></th>
        <th><mfmt:message bundle="table.exm_mst" key="usrscore" /></th>
        <th><mfmt:message bundle="etest.common" key="result.rstscore100" /></th>
        <th><mfmt:message bundle="table.exm_mst" key="passing_yn" /></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="list" items="${list}">
    <tr>
        <td><mh:out value="${list.exmtitle}" td="true"/></td>
        <td align="center"><mh:out value="${list.rst_sdt}" format="fulldate" td="true"/></td>
        <td align="center"><mh:out value="${list.rstscore}" td="true"/></td>
        <td align="center"><mh:out value="${list.rstscore100}" td="true"/></td>
        <td align="center"><mh:out value="${list.passing_yn}" td="true"/></td>
    </tr>
    </c:forEach>
    </tbody>
</table>
</div>
   
<table border="0" cellpadding="2" cellspacing="1" width="100%" class="viewBtn">
    <tr>
        <td align="right"><mf:button bundle="button" key="list" onclick="goBack()" icon="icon_list"/></td>
    </tr>
</table>
</mf:form>