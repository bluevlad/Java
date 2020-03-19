<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<script language="javascript" src='<c:url value="/js/lib.validate.js"/>'></script>
<script language="javascript">
    function frmSubmit(frmName) {
        var frm = getObject(frmName);
        if(frm) {
            if (validate(frm)) {
                // 사용자 조건 추가.
                frm.cmd.value="updateItems";
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

<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit=" frmSubmit('myform');return false;">
<mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}" />
<mf:input type="hidden" name="cmd" value=""/>
<mf:input type="hidden" name="setid" value="${item.setid}" />
    <div class="viewContainer">
    <table width="100%" border="0" cellpadding="2" cellspacing="0" class="view">
        <col width="15%" />
        <col width="35%" />
        <col width="15%" />
        <col width="35%" />
        <tr>
            <th nowrap><mf:label name="settitle" /></th>
            <td colspan="3"><mf:input type="text" name="settitle" cssStyle="width:98%" value="${item.settitle}" /></td>
        </tr>
        <tr>
            <th nowrap><mf:label name="setdesc" /></th>
            <td colspan="3"><mf:input type="text" name="setdesc" cssStyle="width:98%" value="${item.setdesc}" /></td>
        </tr>
        <tr>
	        <th nowrap><mf:header name="active_yn" /></th>
            <td colspan="3"><mf:select name="active_yn" codeGroup="ACTIVE_YN" curValue="${item.active_yn}"/></td>
        </tr>
    </table>
    <table width="100%" border="0" cellpadding="2" cellspacing="0" class="viewBtn">
        <tr>
            <td align="right">
            	<mf:button bundle="button" key="save" onclick="frmSubmit('myform')" icon="icon_save" /> 
            	<mf:button bundle="button" key="list" onclick="goList()" icon="icon_list" />
            </td>
        </tr>
    </table>
    </div>
    <br><br>
     <div class="listContainer">
        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" >
            <thead>
			    <col width="50" />
			    <col width="50" />
			    <col width="*" />
                <tr>
                <th><mf:input type="checkbox" name="checkboxAll" value="allcode" onclick="allChekboxToggle(this,'myform','v_queids');"/></th>
                <th>#</th>
                <th><mf:header name="quetitle" /></th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="si" items="${setitems}" varStatus="status">
                <tr>
                	<mh:iif var="cur" test="${si.chk =='Y'}" trueValue="${si.queid}" falseValue=""/>
                	<td class="center"><mf:input type="checkbox" name="v_queids" value="${si.queid}" curValue="${cur}"/></td>
                    <td align="center"><mf:input type="text" name="v_qseq" cssStyle="width:30px" maxlength="2" value="${si.qseq}" /></td>
                    <td><mh:out value="${si.quetitle}" /></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</mf:form>