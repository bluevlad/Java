<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>
<script language="javascript" src='<c:url value="/js/lib.validate.js"/>'></script>
<script language="javascript">
<!--
	function frmSubmit() {
		var frm = getObject("myform");	
		if (validate(frm)) {
			<c:choose>
				<c:when test="${param.cmd == 'edit'}">
					frm.cmd.value = "update";
				</c:when>
				<c:when test="${param.cmd == 'add'}">
					frm.cmd.value = "insert";
				</c:when>
				<c:when test="${param.cmd == 'gedit'}">
					frm.cmd.value = "gupdate";
				</c:when>		
				<c:otherwise>
					frm.cmd.value = "ginsert";
				</c:otherwise>
			</c:choose>
			frm.submit();
		
		}
	}
	function doDelete() {
		var frm = getObject("myform");
        if(confirm("Are you delete?")){	
    		<c:choose>
    			<c:when test="${param.cmd == 'edit'}">
    				frm.cmd.value = "delete";
    			</c:when>
    			<c:otherwise>
    				frm.cmd.value = "gdelete";
    			</c:otherwise>
    		</c:choose>
    		
    		frm.submit();
        }
	}
	function doAdd(cmd) {
		var frm = getObject("myform");
		frm.cmd.value = cmd;
		frm.submit();
	}
	function frmReset() {
		var frm = getObject("myform");
		frm.reset();
	}
<c:if test="${param.reload =='T'}">
	parent.frmList.location.reload();
</c:if>	
//-->		
</script>
<form action="<mh:ACTION/>" method="post" name="myform" id="myform">
        <mf:input type="hidden" name="ori_group_cd" value="${item.group_cd }" />
        <mf:input type="hidden" name="ori_site" value="${item.site}"/>
        <mf:input type="hidden" name="ori_code_no" value="${item.code_no}"/>
        <mf:input type="hidden" name="cmd" value=""/>
	<c:choose>
	<c:when test="${param.cmd == 'edit'}">
		<c:set var="current_cmd" value="코드수정" />
	</c:when>
	<c:when test="${param.cmd == 'add'}">
		<c:set var="current_cmd" value="코드추가" />
	</c:when>
	<c:when test="${param.cmd == 'gedit'}">
		<c:set var="current_cmd" value="코드그룹수정" />
	</c:when>
	<c:otherwise>
		<c:set var="current_cmd" value="코드그룹추가" />
	</c:otherwise>
</c:choose> * CURRENT : <c:out value="${current_cmd}"/> 
	
	<table border="0" cellpadding="2" cellspacing="1" class="view"width="80%">
        <col width="20%"/>
        <col width="30%"/>
        <col width="20%"/>
        <col width="30%"/>

		<tr>
			<th class="view" nowrap><span id='lbl_required'>*</span>그룹코드</th>
			<td class="view" colspan="3"><select name="group_cd">
				<c:forEach var="grplist" items="${glist}" varStatus="status">
           			<mf:option value="${grplist.group_cd}" curValue="${item.group_cd}"><c:out value="${grplist.group_name}"/></mf:option>
           		</c:forEach>
			</select> : <c:out value="${item.group_cd}"/></td>
		</tr>
		<tr>
			<th class="view" nowrap><span id='lbl_required'>*</span>코드번호</th>
			<td class="view"><mf:input type="text" name="code_no" size="25"
				maxlength="25" value="${item.code_no}" hname="코드번호" required="true"/></td>

			<th class="view" nowrap><span id='lbl_required'>*</span>코드명</th>
			<td class="view"><mf:input type="text" name="code_name" size="25"
				maxlength="100" value="${item.code_name}" hname="코드명" required="true"/></td>
		</tr>
		<tr>
			<th class="view" nowrap>비고</th>
			<td class="view"><mf:input type="text" name="bigo" size="20"
				maxlength="100" value="${item.bigo}"/></td>

			<th class="view" nowrap>순서</th>
			<td class="view"><mf:input type="text" name="seq" size="10"
				maxlength="10" value="${item.seq}"/></td>
		</tr>
	</table>


    <table border="0" cellpadding="2" cellspacing="1" class="list"width="80%">
        <col width="20%"/>
        <col width="30%"/>
        <thead>
            <tr>
                <th>Locale</th>
                <th>Message</th>
                
            </tr>
        </thead>
        <tbody>
        <c:forEach var="it" items="${langList}">
        <mf:input type="hidden" value="${it.lang}" name="cnames" />
        <tr>
            <th><mh:out value="${it.allnames}" td="true"/></th>
            <td><mf:input name="cname_${it.lang}" value="${it.code_name}"/></td>
        </tr>
        </:c:forEach>
        </c:forEach>
       </table>

<table border="0" cellpadding="2" cellspacing="1">
	<tr>
		<td colspan="2" align="center"><mf:button bundle="button" onclick="doAdd('gadd');" key="groupAdd" />
			<mf:button bundle="button" key="codeAdd" onclick="doAdd('add');"/>
			<mf:button bundle="button" key="save" onclick="frmSubmit()"/>
			<mf:button bundle="button" key="reset" onclick="frmReset()" />
		<mh:indexOf var="idx" value="${param.cmd}" needle="dit"/>
		<c:if test="${idx > 0}">
			<mf:button bundle="button" key="delete" onclick="doDelete()"/>
		</c:if></td>
	</tr>
</table>
<!--button end--></form>
