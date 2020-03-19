<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<script>

    function doSelect(setid) {
        var frm = getObject('<c:out value="${param.frm_id}"/>',opener.document);
        frm.<c:out value="${param.elm_id}"/>.value=setid;
        self.close();
    }
    
    function goList() {
            <c:url var="urlList" value="${control_action}">
                <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
                <c:param name="cmd" value="list"/>
                <c:param name="frm_id" value="${param.frm_id}"/>
                <c:param name="elm_id" value="${param.elm_id}"/>
            </c:url>
            document.location = '<mh:out value="${urlList}"/>';
        }
</script>
<mf:form action="${control_action}" method="post" name="myform" id="myform" >
    <input type="hidden" name="cmd" value="edit">
    <mf:input type='hidden' name='setid' value="${item.setid}"/>
    <mf:input type="hidden" name="frm_id" value="${param.frm_id}"/>
    <mf:input type="hidden" name="elm_id" value="${param.elm_id}"/>

<div class="viewContainer">
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
        <td colspan="3"><mh:out value="${item.settitle}" /></td>
    </tr>
    <tr>
        <th nowrap><mf:header name="setdesc" /></th>
        <td colspan="3"><mh:out value="${item.setdesc}" /></td>
    </tr>
    <tr>
        <th nowrap><mf:header name="update_usn" /></th>
        <td><mh:out value="${item.update_usn}" /> , <mh:out value="${item.update_dt}" /></td>
        <th nowrap><mf:header name="reg_usn" /></th>
        <td><mh:out value="${item.reg_usn}" />, <mh:out value="${item.reg_dt}" /></td>
    </tr>
</mf:form>
</table>


<table border="0" cellpadding="2" cellspacing="0" class="viewBtn" width="100%">
    <tr>
        <td colspan="2" align="center">
        	<mf:button bundle="button" key="button.select" onclick="doSelect('${item.setid}')" /> 
        	<mf:button bundle="button" key="goList" onclick="goList();" /></td>
    </tr>
</table>

    <div class="listContainer">
        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" >
            <thead>
                <tr>
                <th>#</th>
                <th><mf:header name="quetitle" /></th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="si" items="${setitems}" varStatus="status">
                <tr>
                    <td align="center"><mh:out value="${status.count}" /></td>
                    <td><mh:out value="${si.quetitle}" /></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
