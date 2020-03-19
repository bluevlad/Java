
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
        <td colspan="3"><mf:input type="text" name="settitle" size="100" maxlength="100" value="${item.settitle}" required="true"/></td>
    </tr>
    <tr>
        <th nowrap><mf:header name="setdesc" /></th>
        <td colspan="3"><mf:input type="text" name="setdesc" size="100" maxlength="100" value="${item.setdesc}" /></td>
    </tr>
    <tr>
        <th nowrap><mf:header name="exmtime" /></th>
        <td><mf:input type="text" name="exmtime" size="20" maxlength="50" value="${item.exmtime}" option="number" required="true"/>
            <mfmt:message bundle="common" key="table.time.min"/></td>
        <th nowrap><mf:header name="passing_score" /></th>
        <td><mf:input type="text" name="passing_score" size="20" maxlength="50" value="${item.passing_score}" option="number" required="true"/></td>
    </tr>
     <tr>
        <th><mf:label name="certi_id"/></th> 
        <td >
        <mf:select name="certi_id">
            <option value="">--</option>
            <c:forEach var="i" items="${CertiList}">
                <mf:option value="${i.certi_id}" text="${i.certi_name }" curValue="${item.certi_id}"/>
                
            </c:forEach>
        </mf:select>
        </td>
        <th><mf:label name="exmtype"/></th> 
        <td ><mf:select name="exmtype" codeGroup="ETEST.EXMTYPE" curValue="${item.exmtype}"></mf:select>
        </td>
    </tr>
	<tr>
        <th nowrap><mf:header name="exmtime" /></th>
        <td colspan="3"><label><mfmt:message bundle="table.exm_mst" key="exmcnt1"/>:<select name="exmcnt1" >
        		<c:forEach var="i" begin="0" end="${maxcnt.exmcnt1}">
        			<mf:option value="${i}" curValue="${item.exmcnt1}" text="${i}"/>
        		</c:forEach>        		
        	</select></label>
        	<label><mfmt:message bundle="table.exm_mst" key="exmcnt2"/>:<select name="exmcnt2" >
        		<c:forEach var="i" begin="0" end="${maxcnt.exmcnt2}">
        			<mf:option value="${i}" curValue="${item.exmcnt2}" text="${i}"/>
        		</c:forEach>
        	</select></label>
        	<label><mfmt:message bundle="table.exm_mst" key="exmcnt3"/>:<select name="exmcnt3" >
        		<c:forEach var="i" begin="0" end="${maxcnt.exmcnt3}">
        			<mf:option value="${i}" curValue="${item.exmcnt3}" text="${i}"/>
        		</c:forEach>
        	</select></label>
        	<label><mfmt:message bundle="table.exm_mst" key="exmcnt4"/>:<select name="exmcnt4" >
        		<c:forEach var="i" begin="0" end="${maxcnt.exmcnt4}">
        			<mf:option value="${i}" curValue="${item.exmcnt4}" text="${i}"/>
        		</c:forEach>
        	</select></label>
        	<label><mfmt:message bundle="table.exm_mst" key="exmcnt5"/>:<select name="exmcnt5" >
        		<c:forEach var="i" begin="0" end="${maxcnt.exmcnt5}">
        			<mf:option value="${i}" curValue="${item.exmcnt5}" text="${i}"/>
        		</c:forEach>
        	</select></label> </td>
    </tr>
    <tr>
        <th nowrap><mf:header name="update_usn" /></th>
        <td><mh:out value="${item.update_usn}" /> , <mh:out value="${item.update_dt}" /></td>
        <th nowrap><mf:header name="reg_usn" /></th>
        <td><mh:out value="${item.reg_usn}" />, <mh:out value="${item.reg_dt}" /></td>
    </tr>

    <tr>
        <th nowrap><mf:label name="active_yn" /></th>
        <td>
        <mf:input type="radio" value="Y" name="active_yn" curValue="${item.active_yn}" /><mfmt:message bundle="common" key="select.use"/>
		<mf:input type="radio" value="N" name="active_yn" curValue="${item.active_yn}" /><mfmt:message bundle="common" key="select.notuse"/>
        </td>

        <th nowrap><mf:label name="cat_name" /></th>
        <td><mh:out   value="${item.cat_name}" /></td>
    </tr>
     <tr>
        <th nowrap><mf:label name="lang" /></th>
        <td>
        <mh:length var="cnt" value="${setitems}"/>
        <mf:label name="questions" />: <c:out value="${cnt}"/><br/>
        <c:forEach var="i" items="${queLangs}">
            <mh:out value="${i.lang}" /> = <c:out value="${i.cnt}"/>
            <mh:iif var="chk" test="${i.chk == 'Y'}" trueValue="true" falseValue=""/>
            <c:choose>
                <c:when test="${cnt == i.cnt && i.lang != 'def.'}">
                    <mf:input type="checkbox" name="bnk_lang" value="${i.lang}" checked="${chk}"/>
                </c:when>
            </c:choose>
            <br>
        </c:forEach>
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
        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true"
            rowAlternateClass="alternateRow">
            <col width="60"/>
            <col width="100"/>
            <col width="60"/>
            <col width="60"/>
            <col width="60"/>
            <col width="*"/>
            <thead>
                <tr>
                <th>#</th>
                <th><mf:header name="qseq" /></th>
                <th><mf:header name="cat_name" /></th>
                <th><mf:header name="quelevel" /></th>
                <th><mf:header name="quescore" /></th>
                <th><mf:header name="lang" /></th>
                <th><mf:header name="quetitle" /></th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="si" items="${setitems}" varStatus="status">
                <tr>
                    <td align="center"><mh:out value="${status.count}" /></td>
                    <td align="center"><mh:out value="${si.qseq}" td="true"/></td>
                    <td align="center"><mh:out value="${si.cat_name}" /></td>
                    <td align="center"><mh:out value="${si.quelevel}" codeGroup="ETEST.QLEVEL"/></td>
                    <td align="center"><mh:out value="${si.quescore}" /></td>
                    <td align="center"><mh:out value="${si.quelang}" /></td>
                    <td><mh:out value="${si.quetitle}" /></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
