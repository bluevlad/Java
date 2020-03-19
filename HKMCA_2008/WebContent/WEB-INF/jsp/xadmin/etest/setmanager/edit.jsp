<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<script language="javascript">
function frmSubmit(frmName) {
    var frm = getObject(frmName);
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

function frmDelete() {
    var frm = getObject("myform");
    frm.cmd.value = "delete";
    frm.submit();
}

function goList() {
    <c:url var="urlList" value="${control_action}">
        <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
        <c:param name="cmd" value="list"/>
    </c:url>
    document.location = '<mh:out value="${urlList}"/>';
}

function frmEdit() {
    var frm = getObject("myform");
    frm.cmd.value = "edit";
    frm.submit();
}

    
function frmAdd(setid) {
    var urlname = CPATH + "question.do?setid="+setid;
    var oWin = window.open(urlname,
        "queWin",
        "top=100px,left=100px,status=yes,resizable=no,menubar=no,scrollbars=yes,width=100,height=100");
    windows_focus(oWin);
}
</script>

<div class="viewContainer">
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit=" frmSubmit('myform');return false;">
<mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}" />
<mf:input type="hidden" name="cmd" value="edit"/>
<mf:input type="hidden" name="setid" value="${item.setid}" />
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
    <col width="15%" />
    <col width="35%" />
    <col width="15%" />
    <col width="35%" />
    <tr>
        <th><mf:label name="settitle" /></th>
        <td colspan="3"><mf:input type="text" name="settitle" cssStyle="width:98%" value="${item.settitle}" required="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="setdesc" /></th>
        <td colspan="3"><mf:textarea name="setdesc" cssStyle="width:98%" rows="3" value="${item.setdesc}"/></td>
    </tr>
    <tr>
        <th><mf:label name="queowner"/></th>
        <td colspan="3"><mh:out value="${item.sjt_nm}"/></td>
    </tr>
    <tr>
        <th><mf:label name="exmtime" /></th>
        <td><mf:input type="text" name="exmtime" cssStyle="width:25px" maxlength="4" value="${item.exmtime}" option="number" required="true"/></td>
        <th><mf:label name="passing_score" /></th>
        <td><mf:input type="text" name="passing_score" cssStyle="width:25px" maxlength="4" value="${item.passing_score}" option="number" required="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="exmtype"/></th> 
        <td><mf:select name="exmtype" codeGroup="ETEST.EXMTYPE" curValue="${item.exmtype}"></mf:select></td>
        <th><mf:label name="viewrand"/></th> 
        <td><mf:select name="viewrand" codeGroup="ACTIVE_YN" curValue="${item.viewrand}"></mf:select></td>
    </tr>        
    <tr>
        <th><mf:label name="questions" /></th>
        <td colspan="3">
            <mf:label name="exmcnt1" /> : 
            <select name="exmcnt1">
            <c:forEach var="i" begin="0" end="${bnk.exmcnt1}">
                <mf:option value="${i}" curValue="${item.exmcnt1}"><mh:out value="${i}"/></mf:option>
            </c:forEach>
            </select> 
            &nbsp;&nbsp;
            <mf:label name="exmcnt2" /> : 
            <select name="exmcnt2">
            <c:forEach var="i" begin="0" end="${bnk.exmcnt2}">
                <mf:option value="${i}" curValue="${item.exmcnt2}"><mh:out value="${i}"/></mf:option>
            </c:forEach>
            </select> 
            &nbsp;&nbsp;
            <mf:label name="exmcnt3" /> : 
            <select name="exmcnt3">
            <c:forEach var="i" begin="0" end="${bnk.exmcnt3}">
                <mf:option value="${i}" curValue="${item.exmcnt3}"><mh:out value="${i}"/></mf:option>
            </c:forEach>
            </select> 
            &nbsp;&nbsp;
            <mf:label name="exmcnt4" /> : 
            <select name="exmcnt4">
            <c:forEach var="i" begin="0" end="${bnk.exmcnt4}">
                <mf:option value="${i}" curValue="${item.exmcnt4}"><mh:out value="${i}"/></mf:option>
            </c:forEach>
            </select> 
            &nbsp;&nbsp;
            <mf:label name="exmcnt5" /> : 
            <select name="exmcnt5">
            <c:forEach var="i" begin="0" end="${bnk.exmcnt5}">
                <mf:option value="${i}" curValue="${item.exmcnt5}"><mh:out value="${i}"/></mf:option>
            </c:forEach>
            </select> 
            &nbsp;&nbsp;
        </td>
    </tr>
</table>
<table border="0" cellpadding="2" cellspacing="0" class="viewBtn" width="100%">
    <tr>
        <td align="right">
        	<mf:button bundle="button" key="save" onclick="frmSubmit('myform')"  icon="icon_save"/> 
            <mf:button bundle="button" key="delete" onclick="frmDelete('myform')" icon="icon_delete" /> 
        	<mf:button bundle="button" key="list" onclick="goList('myform')" icon="icon_list" />
        </td>
    </tr>
</table>
<br>    
<div class="listContainer">
<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" >
	<col width="70"/>
	<col width="50"/>
	<col width="70"/>
	<col width="50"/>
	<col width="50"/>
	<col width="*"/>
    <thead>
    <tr>
        <th><mf:header name="qseq" /></th>
        <th><mf:input type="checkbox" name="checkboxAll" value="allcode" onclick="allChekboxToggle(this,'myform','v_queids');"/></th>
		<th><mf:header name="quelevel" /></th>
		<th><mf:header name="quescore" /></th>
		<th><mf:header name="lang" /></th>
		<th><mf:header name="quetitle" /></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="si" items="${list}" varStatus="status">
    <tr>
        <mf:input type="hidden" name="a_queids" value="${si.queid}"/>
		<mh:iif var="cur" test="${si.chk =='Y'}" trueValue="${si.queid}" falseValue=""/>
        <td align="center"><mf:input type="text" name="qseq_${si.queid}" cssStyle="width:25px" value="${status.count}" /></td>
		<td class="center"><mf:input type="checkbox" name="v_queids" value="${si.queid}" curValue="${cur}"/></td>
		<td align="center"><mh:out value="${si.quelevel}" codeGroup="ETEST.QLEVEL"/></td>
		<td align="center"><mh:out value="${si.quescore}" /></td>
		<td align="center"><mh:out value="${si.quelang}" /></td>
		<td><mh:out value="${si.quetitle}" /></td>
    </tr>
    </c:forEach>
    </tbody>
</table>
</div>
<table border="0" cellpadding="2" cellspacing="0" class="viewBtn" width="100%">
    <tr>
        <td align="right">
            <mf:button bundle="button" key="etest.admin.add" onclick="frmAdd('${item.setid}')" icon="icon_save"/> 
        </td>
    </tr>
</table>
</mf:form>
</div>

<script>
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
		t.innerHTML = cnt + "ea";
	}
//	Event.observe(window, 'load', l_setPage);
</script>