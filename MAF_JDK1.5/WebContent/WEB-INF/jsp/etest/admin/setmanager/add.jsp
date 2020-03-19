<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>
<script language="javascript" src='<c:url value="/js/lib.validate.js"/>'></script>
<script language="javascript">
    function frmSubmit(frmName) {
        var frm = getObject(frmName);
        if(frm) {
            if (validate(frm)) {
                // 사용자 조건 추가.
                frm.cmd.value="insert";
                frm.submit();
            }
        } else {
            alert ("form[" + frmName + "] is invalid");
        }
    }
    function goList() {
        <c:url var="urlList" value="${control_action}">
            <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
            <c:param name="cmd" value="list"/>
        </c:url>
        document.location = '<mh:out value="${urlList}"/>';
    }

</script>
<jsp:include page="../bankmanager/_category.jsp" flush="true">
    <jsp:param name="target" value="list"/>
</jsp:include>
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit=" frmSubmit('myform');return false;">
    <input type="image" value="test" width="0" height="0" border="0" class="hidden">
    <mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}" />
    <input type="hidden" name="cmd" value="">
    <mf:input type="hidden" name="setid" value="${setid}" />
    <mf:input type="hidden" name="cat_id" value="${item.cat_id}"/>
    <table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
        <col width="15%" />
        <col width="35%" />
        <col width="15%" />
        <col width="35%" />
        <tr>
            <th nowrap><mf:label name="cat_name" /></th>
            <td colspan="3"><mf:input type="text" name="cat_name"  value="${item.cat_name}" 
                onclick="CategorySelector.showList('cat_name')" cssClass="dropdown" required="true"/><div id="catTreeDiv" ></div>
        </tr>
        <tr>
            <th nowrap><mf:label name="settitle" /></th>
            <td colspan="3"><mf:input type="text" name="settitle" size="50" maxlength="50" value="${item.settitle}" required="true"/></td>
        </tr>
        <tr>
            <th nowrap><mf:label name="setdesc" /></th>
            <td colspan="3"><mf:input type="text" name="setdesc" size="50" maxlength="50" value="${item.setdesc}" /></td>
        </tr>
    
        <tr>
            <th nowrap><mf:label name="exmtime" /></th>
            <td><mf:input type="text" name="exmtime" size="20" maxlength="50" value="${item.exmtime}" option="number" required="true"/></td>
            <th nowrap><mf:label name="passing_score" /></th>
            <td><mf:input type="text" name="passing_score" size="20" maxlength="50" value="${item.passing_score}" option="number" required="true"/></td>
        </tr>
 <tr>
        <th><mf:label name="certi_id"/></th> 
        <td >
        <mf:select name="certi_id">
            <option value="">--</option>
            <c:forEach var="i" items="${CertiList}">
                <mf:option value="${i.certi_id}" text="${i.certi_name }" />
                
            </c:forEach>
        </mf:select>
        </td>
        <th><mf:label name="exmtype"/></th> 
        <td ><mf:select name="exmtype" codeGroup="ETEST.EXMTYPE" curValue="${item.exmtype}"></mf:select>
    </tr>        
    </table>
    <table border="0" cellpadding="2" cellspacing="0" class="viewBtn" width="100%">
        <tr>
            <td colspan="2" align="center"><mf:button bundle="button" key="save" onclick="frmSubmit('myform')" /> <mf:button
                bundle="button" key="goList" onclick="goList()" /></td>
        </tr>
    </table>
</mf:form>
