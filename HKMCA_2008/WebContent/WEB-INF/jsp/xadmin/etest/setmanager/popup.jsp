<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<script language="javascript">
function frmSubmit(frm) {
    if(frm) {
        if (validate(frm)) {
            <c:choose>
                <c:when test="${param.cmd == 'edit'}">
                    frm.cmd.value = "update";
                </c:when>
                <c:when test="${param.cmd == 'add'}">
                    frm.cmd.value = "insert";
                </c:when>
                <c:otherwise>
                    frm.cmd.value = "insert";
                </c:otherwise>
            </c:choose>
            frm.submit();
        }
    } else {
        alert ("form[" + frmName + "] is invalid");
    }
}

function frmSubmit(frmName) {
    var frm = getObject(frmName);
    if(frm) {
        if (validate(frm)) {
            <c:choose>
                <c:when test="${param.cmd == 'edit'}">
                    frm.cmd.value = "updateItems";
                </c:when>
                <c:when test="${param.cmd == 'add'}">
                    frm.cmd.value = "insert";
                </c:when>
                <c:otherwise>
                    frm.cmd.value = "insert";
                </c:otherwise>
            </c:choose>
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

function l_setPage() {
    update_que_cnt();       
}
function update_que_cnt() {
    var frm = getObject("myform");
    var cnt = 0;
    if(frm) {
        for (var i=0; i<frm.elements.length;i++) {
        
            if (frm.elements[i].type == "checkbox" && frm.elements[i].name == "v_queids") {
                if(frm.elements[i].checked) {
                    cnt ++;
                }
            }
        }
    }
    var t = getObject("que_cnt");
    t.innerHTML = cnt + " ea";
}
Event.observe(window, 'load', l_setPage);
//-->
</script>

<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit=" frmSubmit('myform');return false;">
<mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}" />
<input type="hidden" name="cmd" value="">
<mf:input type="hidden" name="setid" value="${item.setid}" />
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
<col width="15%" />
<col width="35%" />
<col width="15%" />
<col width="35%" />
    <tr>
        <th><mf:label name="settitle" /></th>
        <td colspan="3"><mh:out value="${item.settitle}" /></td>
    </tr>
    <tr>
        <th><mf:label name="setdesc" /></th>
        <td colspan="3"><mh:out value="${item.setdesc}" td="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="exmtime" /></th>
        <td><mh:out value="${item.exmtime}" td="true" /></td>
        <th><mf:label name="passing_score" /></th>
        <td><mh:out value="${item.passing_score}" td="true" /></td>
    </tr>
    <tr>
        <th><mf:header name="active_yn" /></th>
        <td><mh:out value="${item.active_yn}" codeGroup="ACTIVE_YN"/></td>
    	<th>total</th>
        <td><div id="que_cnt"/>0 ea</div></td>
    </tr>
</table>
<table border="0" cellpadding="2" cellspacing="0" class="viewBtn" width="100%">
    <tr>
        <td align="right">
        	<mf:button bundle="button" key="save" onclick="frmSubmit('myform')" /> 
        	<mf:button bundle="button" key="goList" onclick="goList()" />
        </td>
    </tr>
</table>
<br>    
<div class="listContainer">
<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" >
	<col width="40"/>
	<col width="40"/>
	<col width="40"/>
	<col width="40"/>
	<col width="40"/>
	<col width="*"/>
    <thead>
    <tr>
        <th>#</th>
        <th><mf:input type="checkbox" name="checkboxAll" value="allcode" onclick="allChekboxToggle(this,'myform','v_queids');update_que_cnt();"/></th>
		<th><mf:header name="qseq" /></th>
		<th><mf:header name="quelevel" /></th>
		<th><mf:header name="quescore" /></th>
		<th><mf:header name="lang" /></th>
		<th><mf:header name="quetitle" /></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="si" items="${navigator.list}" varStatus="status">
    <tr>
		<mh:iif var="cur" test="${si.chk =='Y' }" trueValue="${si.queid}" falseValue=""/>
		<td align="center"><mh:out value="${status.count}" /></td>
		<td class="center"><mf:input type="checkbox" name="v_queids" value="${si.queid}" curValue="${cur}" onchange="update_que_cnt();"/></td>
		<td align="center"><mf:input type="text" name="qseq_${si.queid}" size="3" value="${si.qseq}" /></td>
		<td align="center"><mh:out value="${si.quelevel}" codeGroup="ETEST.QLEVEL"/></td>
		<td align="center"><mh:out value="${si.quescore}" /></td>
		<td align="center"><mh:out value="${si.quelang}" /></td>
		<td><mh:out value="${si.quetitle}" /></td>
    </tr>
    </c:forEach>
    </tbody>
</table>
</div>
</mf:form>
<br>
<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true" />