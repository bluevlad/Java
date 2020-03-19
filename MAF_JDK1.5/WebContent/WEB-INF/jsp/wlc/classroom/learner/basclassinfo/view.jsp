<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>


<div class="viewContainer">

<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">    
    <col width="15%"/>
    <col width="35%"/>
    <col width="15%"/>
    <col width="35%"/>
    <tr>

        <th><mf:label name="sjt_cd"/></th> 
        <td><mh:out value="${item.sjt_cd}"/></td>
        <th><mf:label name="leccode"/></th> 
        <td><mh:out value="${item.leccode}" td="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="subject_nm"/></th> 
        <td colspan="3"><mh:out value="${item.lecname}" td="true"/></td>
    </tr>


    <tr>
    
        <th><mf:label name="price"/></th> 
        <td><mh:out value="${item.price}" default="0"/> (<mf:label name="currency"/>)</td>

        <th><mf:label name="term"/></th> 
        <td ><mh:out value="${item.term}" default="0"/> (<mfmt:message bundle="common" key="table.day"/>)</td>
    </tr>
    <tr>
        <th><mf:label name="outline"/></th> 
        <td colspan="3"><mh:out value="${item.outline}" td="true" nl2br="true"/></td>
    </tr>
   
   
    <tr>
        <th><mf:label name="target"/></th> 
        <td colspan="3"><mh:out value="${item.target}" td="true" nl2br="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="precondition"/></th> 
        <td colspan="3"><mh:out value="${item.precondition}" td="true" nl2br="true"/></td>
    </tr>
    
    <tr>
        <th><mf:label name="contents"/></th> 
        <td colspan="3"><mh:out value="${item.contents}" td="true" nl2br="true"/></td>
    </tr>

    <tr>
        <th><mf:label name="attachment"/></th> 
        <td colspan="3"> 
            <c:forEach var="fi" items="${files}">
                <a href='<c:url value="/wdownload">
                    <c:param name="type" value="subject_file"/>
                    <c:param name="filename" value="${fi.fileid}" /></c:url>' target="_blank" ><mh:out value="${fi.org_filename }"/> (<mh:out value="${fi.file_size}"/> KB)</a><br>
           </c:forEach>&nbsp;
           </td>
    </tr>
</table>
  


</div>