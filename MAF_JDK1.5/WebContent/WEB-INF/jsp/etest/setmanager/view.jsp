
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>
<script language="javascript" src='<c:url value="/js/lib.validate.js"/>'></script>
<script>
    function doEdit() {
        var frm = getObject("myform");
        
            frm.setid.value = '<c:out value="${item.setid}"/>';
                
        frm.submit();
    }
    function goList() {
            <c:url var="urlList" value="${control_action}">
                <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
                <c:param name="cmd" value="list"/>
            </c:url>
            document.location = '<mh:out value="${urlList}"/>';
        }
</script>
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return doEdit(); ">
    <input type="hidden" name="cmd" value="edit">
    <input type='hidden' name='setid' value="">
</mf:form>
<div class="viewContainer">
<h1>기본사항</h1>
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
    <col width="15%" />
    <col width="35%" />
    <col width="15%" />
    <col width="35%" />
    <tr>
        <th nowrap><mf:header name="setid" /></th>
        <td><mh:out value="${item.setid}" /></td>
        <th nowrap><mf:header name="setowner" /></th>
        <td><mh:out value="${item.setowner}" /></td>
    </tr>
    <tr>
        <th nowrap><mf:header name="settitle" /></th>
        <td><mh:out value="${item.settitle}" /></td>
        <th nowrap><mf:header name="setdesc" /></th>
        <td><mh:out value="${item.setdesc}" /></td>
    </tr>
    <tr>
        <th nowrap><mf:header name="exmtime" /></th>
        <td><mh:out value="${item.exmtime}" /></td>
    </tr>
    <tr>
        <th nowrap><mf:header name="reg_usn" /></th>
        <td><mh:out value="${item.reg_usn}" /></td>
        <th nowrap><mf:header name="reg_dt" /></th>
        <td><mh:out value="${item.reg_dt}" /></td>
    </tr>
    <tr>
        <th nowrap><mf:header name="update_dt" /></th>
        <td><mh:out value="${item.update_dt}" /></td>
        <th nowrap><mf:header name="update_usn" /></th>
        <td><mh:out value="${item.update_usn}" /></td>
    </tr>
    <tr>
        <th nowrap><mf:header name="active_yn" /></th>
        <td><mh:out value="${item.active_yn}" codeGroup="ACTIVE_YN" /></td>
    </tr>
</table>
<table border="0" cellpadding="2" cellspacing="0" class="viewBtn" width="100%">
    <tr>
        <td colspan="2" align="center"><mf:button bundle="button" key="edit" onclick="doEdit();" /> <mf:button bundle="button"
            key="goList" onclick="goList();" /></td>
    </tr>
</table>
</div>
<div class="viewContainer">
<h1>문제</h1>
<table border="0" cellpadding="2" cellspacing="0" class="list" width="100%">
    <col width="10%"/>
    <col width="70%"/>
    <col width="10%"/>
    <col width="10%"/>
    <thead>
        <tr>
            <th>#</th>
            <th><mf:header name="quetitle"/></th>
            <th><mf:header name="quelevel"/></th>
            <th><mf:header name="quescore"/></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="sitem" items="${setitems}" varStatus="status">
            <tr>
                <td align="center"><c:out value="${status.count }"/></td>
                <td><mh:out value="${sitem.quetitle}" td="true"/></td>
                <td align="center"><mh:out value="${sitem.quelevel}" td="true"/></td>
                <td align="center"><mh:out value="${sitem.quescore}" td="true"/></td>

            </tr>
        </c:forEach>
    </tbody>
</table>
</div>
