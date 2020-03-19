<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>
<script language="javascript" src='<c:url value="/js/lib.validate.js"/>'></script>
<script language="javascript" src='<c:url value="/js/calendar.js"/>'></script>
<script language="javascript">
	function frmSubmit(frmName) {
		var frm = getObject(frmName);
		if(frm) {
			if (validate(frm)) {
				frm.cmd.value="maketest";
				frm.submit();
			}
		} else {
			alert ("form[" + frmName + "] is invalid");
		}
	}

</script>
<c:choose>
<c:when test="${empty item }">
    <br><br>
    <table border="0" cellpadding="2" cellspacing="0" class="view" width="70%" align="Center">
    <tr>
        <td align="center" height="100">이 Lecture에는 설정된 E-Test set이 없습니다. Subject 관리에서 시험을 선택해 주시기 바랍니다.</td>
       </tr>
       </table>
</c:when>
<c:otherwise>
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit=" frmSubmit('myform');return false;">
    <input type="image" value="test" width="0" height="0" border="0" class="hidden">
    
    <input type="hidden" name="cmd" value="">
    <table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
        <col width="15%" />
        <col width="35%" />
        <col width="15%" />
        <col width="35%" />

        <tr>
            <th nowrap><mf:label name="exmtitle" /></th>
            <td colspan="3"><mh:out value="${item.settitle }" td="true"/></td>
        </tr>
        <tr>
            <th nowrap><mf:label name="exmdesc" /></th>
            <td colspan="3"><mh:out value="${item.setdesc }" td="true"/></td>
        </tr>

        <tr>
            <th nowrap><mf:label name="exmtime" /></th>
            <td><mh:out value="${item.exmtime }" td="true"/></td>

            <th nowrap><mf:label name="exmpage" /></th>
            <td><mf:input type="text" name="exmpage" size="20" maxlength="50" value="1" required="true" option="number"/></td>
        </tr>
        <tr>
            <th nowrap><mf:header name="questions" /></th>
            <td colspan="3"><table  border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
                <col width="20%"/>
                <col width="16%"/>
                <col width="16%"/>
                <col width="16%"/>
                <col width="16%"/>
                <col width="16%"/>
                <thead>
                <tr>
                    <th>Total</th>
                    <th><mfmt:message bundle="table.exm_mst" key="exmcnt1"/></th>
                    <th><mfmt:message bundle="table.exm_mst" key="exmcnt2"/></th>
                    <th><mfmt:message bundle="table.exm_mst" key="exmcnt3"/></th>
                    <th><mfmt:message bundle="table.exm_mst" key="exmcnt4"/></th>
                    <th><mfmt:message bundle="table.exm_mst" key="exmcnt5"/></th>
                 </tr>
                 </thead>
                 <tbody>
                 <tr>
                    <th  align="center"><strong><mh:out value="${item.exmcnt1+item.exmcnt2+item.exmcnt3+item.exmcnt4+item.exmcnt5}"/></strong></th>
                    <td align="center"><mh:out value="${item.exmcnt1}"/></td>
                    <td align="center"><mh:out value="${item.exmcnt2}"/></td>
                    <td align="center"><mh:out value="${item.exmcnt3}"/></td>
                    <td align="center"><mh:out value="${item.exmcnt4}"/></td>
                    <td align="center"><mh:out value="${item.exmcnt5}"/></td>
                 </tr>
                 </tbody>
                </table></td>
        </tr>
    </table>
	
    <table border="0" cellpadding="2" cellspacing="0" class="viewBtn" width="100%">
        <tr>
            <td colspan="2" align="center">
             <mf:button bundle="etest.button" key="table.exm.create" onclick="frmSubmit('myform')" />
            </td>
        </tr>
    </table>
</mf:form>
</c:otherwise>
</c:choose>