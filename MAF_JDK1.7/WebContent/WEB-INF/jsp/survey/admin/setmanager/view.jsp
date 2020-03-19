
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>
<script language="javascript" src='<c:url value="/js/lib.validate.js"/>'></script>
<script>
	function frmSubmit(frmName) {
        var frm = getObject(frmName);
        if(frm) {
            if (validate(frm)) {
                // 사용자 조건 추가.
                frm.cmd.value="update";
                frm.submit();
            }
        } else {
            alert ("form[" + frmName + "] is invalid");
        }
    }
    
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
    <mf:input type='hidden' name='setid' value="${item.setid}"/>

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
        <td colspan="3"><mf:input type="text" name="settitle" size="100" maxlength="100" value="${item.settitle}" /></td>
    </tr>
    <tr>
        <th nowrap><mf:header name="setdesc" /></th>
        <td colspan="3"><mf:input type="text" name="setdesc" size="100" maxlength="100" value="${item.setdesc}" /></td>
    </tr>
    <tr>
        <th nowrap><mf:header name="update_usn" /></th>
        <td><mh:out value="${item.update_usn}" /> , <mh:out value="${item.update_dt}" /></td>
        <th nowrap><mf:header name="reg_usn" /></th>
        <td><mh:out value="${item.reg_usn}" />, <mh:out value="${item.reg_dt}" /></td>
    </tr>

    <tr>
        <th nowrap><mf:label name="active_yn" /></th>
        <td colspan="3">
        	<mf:input type="radio" value="Y" name="active_yn" curValue="${item.active_yn}" /><mfmt:message bundle="common" key="select.use"/>
   			<mf:input type="radio" value="N" name="active_yn" curValue="${item.active_yn}" /><mfmt:message bundle="common" key="select.notuse"/>
        </td>
       </tr>
</mf:form>
</table>


<table border="0" cellpadding="2" cellspacing="0" class="viewBtn" width="100%">
    <tr>
        <td colspan="2" align="center">
        	<mf:button bundle="button" key="save" onclick="frmSubmit('myform')" /> 
        	<mf:button bundle="button" key="edit" onclick="doEdit();" /> 
        	<mf:button bundle="button" key="goList" onclick="goList();" /></td>
    </tr>
</table>

    <div class="listContainer">
        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
            <thead>
                <tr>
	               <th>#</th>
	               <th><mf:header name="quetitle" /></th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="si" items="${setitems}" varStatus="status">
                <tr>
                    <td align="center"><mh:out value="${si.qseq}" td="true"/></td>
                    <td><a href='<c:url value="bank.do">
                    	<c:param name="cmd" value="edit"/>
                    	<c:param name="from" value="set"/>
                    	<c:param name="setid" value="${item.setid}"/>
                    	<c:param name="queid" value="${si.queid}"/>
                    	</c:url>'><mh:out value="${si.quetitle}" /></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
