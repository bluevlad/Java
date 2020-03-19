<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<div class="viewContainer">
<table width="100%" border="0" cellpadding="2" cellspacing="0" class="view">    
    <col width="20%"/>
    <col width="80%"/>
    <tr>
        <th><mf:label name="lec_nm"/></th> 
        <td>[<mh:out value="${item.lec_cd}" td="true"/>] <mh:out value="${item.lec_nm}" td="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="lec_obj"/></th> 
        <td><mh:out value="${item.lec_obj}" td="true" nl2br="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="lec_usn"/></th> 
        <td><mh:out value="${item.lec_usn}" td="true" nl2br="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="lec_summary"/></th> 
        <td><mh:out value="${item.lec_summary}" td="true" nl2br="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="lec_inf"/></th> 
        <td><mh:out value="${item.lec_inf}" td="true" nl2br="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="lec_book"/></th> 
        <td><mh:out value="${item.lec_book}" td="true" nl2br="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="lec_etc"/></th> 
        <td><mh:out value="${item.lec_etc}" td="true" nl2br="true"/></td>
    </tr>
</table>
</div>