<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>
<div class="viewContainer">
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
    <col width="20%" />
    <col width="30%" />
    <col width="20%" />
    <col width="30%" />
    <tbody>
        <tr>
            <th nowrap><mf:header name="userid" /></th>
            <td><mh:out value="${user.userid}" td="true"/></td>
            <th nowrap><mf:header name="nm" /></th>
            <td><mh:out value="${user.nm}" td="true"/></td>
        </tr>
        <tr>
            <th nowrap><mf:header name="org_name" /></th>
            <td><mh:out value="${user.org_name}" td="true"/></td>
            <th nowrap><mf:header name="section" /></th>
            <td><mh:out value="${user.mst_section}" codeGroup="SECTION" /></td>
        </tr>
        <tr>
            <th nowrap><mf:header name="tel" /></th>
            <td><mh:out value="${user.tel}" td="true"/></td>
            <th nowrap><mf:header name="email" /></th>
            <td><mh:out value="${user.email}" td="true"/></td>
        </tr>
        <tr>
            <th nowrap><mf:header name="add" /></th>
            <td colspan="3">[<mh:out value="${user.zip}" td="true"/>]&nbsp;<mh:out value="${user.addr1}" td="true"/>&nbsp;<mh:out value="${user.addr2}" td="true"/></td>
        </tr>
    </tbody>
</table>


   
   
<c:catch var="error">

    <msql:query var="entries">
        SELECT cl.lecname, lr.is_grad, lr.req_sdat, lr.req_edat, cl.lec_year, lr.lecnumb
  FROM wcc_lec_req lr, bas_class cl
 WHERE lr.userid = ?
   AND lr.leccode = cl.leccode
   order by req_sdat desc, lecname
        <msql:param value="${sessionScope.msession.userid}"/>
    </msql:query>
</c:catch>  

<br><strong>Traning History </strong>
<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
    <thead>
    <tr>
        <th># </th>
        <th><mfmt:message bundle="table.bas_class" key="lecname"/></th>            
        <th><mfmt:message bundle="table.bas_class" key="lec_year"/></th>
        <th><mfmt:message bundle="table.bas_class" key="lecnumb"/></th>
        <th><mfmt:message bundle="table.wcc_lec_req" key="is_grad"/></th>
        <th><mfmt:message bundle="table.wcc_lec_req" key="req_sdat"/></th>
        <th><mfmt:message bundle="table.wcc_lec_req" key="req_edat"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="row" items="${entries.rows}" varStatus="status">
    <tr >
        <td class="center"><mh:out value="${status.count}"/>
        <td class="center"><mh:out value="${row.lecname}" td="true"/></td>
        <td class="center"><mh:out value="${row.lec_year}" td="true"/></td>
        <td class="center"><mh:out value="${row.lecnumb}" td="true"/></td>
        <td class="center"><mh:out value="${row.is_grad}" codeGroup="IS_GRADE" td="true"/></td>
        <td class="center"><mh:out value="${row.req_sdat}" format="fulldate" td="true"/></td>
        <td class="center"><mh:out value="${row.req_edat}" format="fulldate" td="true"/></td>
    </tr>
    </c:forEach>
    </tbody>
</table>