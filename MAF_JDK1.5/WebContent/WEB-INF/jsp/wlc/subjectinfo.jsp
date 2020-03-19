<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<msql:query var="entries">
    SELECT sjt_cd, crs_cd, subject_nm, name_eng, isuse, stype, otype, price, term, OUTLINE, target,
           precondition, CONTENTS, level_type, ad_new, ad_recommend, reg_dt, update_dt, update_id,
           test_type, division, orgn_cd, dlr_cd, sbj_type
      FROM bas_subject_cd 
      WHERE sjt_cd = ?
       <msql:param value="${param.sjt_cd}"/>
</msql:query>
<c:catch var="error">    
    <c:forEach var="row" items="${entries.rows}">
        <c:set var="item" value="${row}"/>
    </c:forEach>
</c:catch>
<c:if test="${empty item }">
<script>
    alert("ivalid subject code");
    self.close();
</script>
</c:if>
<msql:query var="entries">
    SELECT   sjt_cd, filename, org_filename, reg_dt, reg_usn, downcnt, file_size, attach_type
        FROM bas_subject_attach
    WHERE sjt_cd = :sjt_cd
    ORDER BY org_filename
    <msql:param value="${param.sjt_cd}"/>
</msql:query>
<c:catch var="error">    
        <c:set var="files" value="${entries.rows}"/>
</c:catch>
<table>
    <tr>
        <td>Subject Info</td>
    </tr>
</table>
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">    
    <col width="15%"/>
    <col width="35%"/>
    <col width="15%"/>
    <col width="35%"/>
    <tr>

        <th><mfmt:message bundle="table.bas_subject_cd" key="sjt_cd"/></th> 
        <td><mh:out value="${item.sjt_cd}"/></td>
        <th><mfmt:message bundle="table.bas_subject_cd" key="subject_nm"/></th> 
        <td><mh:out value="${item.subject_nm}" td="true"/></td>
    </tr>
    <tr>
        <th><mfmt:message bundle="table.bas_subject_cd" key="outline"/></th> 
        <td colspan="3"><mh:out value="${item.outline}" td="true" nl2br="true"/></td>
    </tr>
    <tr>
        <th><mfmt:message bundle="table.bas_subject_cd" key="target"/></th> 
        <td colspan="3"><mh:out value="${item.target}" td="true" nl2br="true"/></td>
    </tr>
    <tr>
        <th><mfmt:message bundle="table.bas_subject_cd" key="precondition"/></th> 
        <td colspan="3"><mh:out value="${item.precondition}" td="true" nl2br="true"/></td>
    </tr>
        <tr>
        <th><mfmt:message bundle="table.bas_subject_cd" key="contents"/></th> 
        <td colspan="3"><mh:out value="${item.contents}" td="true" nl2br="true"/></td>
    </tr>
    

    <tr>
        <th><mfmt:message bundle="table.bas_subject_cd" key="attachment"/></th> 
        <td colspan="3"> 
            <c:forEach var="fi" items="${files}">
                <a href='<c:url value="/wdownload">
                    <c:param name="type" value="subject_file"/>
                    <c:param name="filename" value="${fi.filename}"/></c:url>' target="_blank"><mh:out value="${fi.org_filename }"/>(<mh:out value="${fi.file_size}"/> KB)</a><br>
           </c:forEach>&nbsp;
           </td>
    </tr>
</table>
</c:if>
