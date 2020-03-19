<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<script>
    function doTest(exmid,lecnumb) {
        document.location = CPATH+'/etest/examlist.do?cmd=view&exmid=' + exmid +"&lecnumb="+lecnumb;
    }
    
   
</script>
<table width="95%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true"
    rowAlternateClass="alternateRow">
    <thead>
        <tr>
            <th nowrap><mfmt:message key="exmtitle" bundle="table.exm_mst"/></th>
            <th nowrap><mfmt:message key="exm_sdat_t1" bundle="table.exm_mst"/>~<mfmt:message key="exm_edat_t1" bundle="table.exm_mst"/></th>
            <th nowrap><mfmt:message key="rst_status" bundle="table.exm_rst"/></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="item" items="${etestlist}" varStatus="status">
            <tr>
                <td align="center"><a href="javaScript:doTest(<c:out value="'${item.exmid}','${item.lecnumb}'"/>)" /><mh:out
                    value="${item.exmtitle}" td="true" /></a></td>
                <td align="center"><mh:out value="${item.exm_sdat_t1}" format="fulldate" td="true" />~<mh:out value="${item.exm_edat_t1}" format="fulldate" td="true" /></td>
                <td align="center"><mh:out value="${item.rst_status}" td="true" codeGroup="ETEST.RST_STATUS" /></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
