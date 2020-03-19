<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<c:set var="m" value="table.maf_menu" />
<c:choose>
	<c:when test="${param.cmd == 'edit'}">
		<c:set var="acttype" value="update"/>
	</c:when>
	<c:otherwise>
		<c:set var="acttype" value="insert"/>
	</c:otherwise>
</c:choose>

<mf:form action='${control_action}' method="post" name="frmEdit" id="frmEdit">
<mf:input type="hidden" name="lang_title" value="${item.lang_title}"/>
<table width="99%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="view">
    <col width="20%"/>
    <col width="30%"/>
    <col width="20%"/>
    <col width="30%"/>
  <tr>
		<th><mfmt:message bundle="${m}" key="pgid" /></th>
		<td><mf:input type="text" name="pgid" cssStyle="width:95%" value='${pgid}' required="true" hname="PGID" option="alphanum" readonly='${item.pgid == "HOME"}'/></td>
		<th><mfmt:message bundle="${m}" key="isuse" /></th>
		<td>
            <mf:input type="radio" name="isuse" value="T" curValue='${item.isuse}' /> : <mfmt:message bundle="common" key="true"/>
            <mf:input type="radio" name="isuse" value="F" curValue='${item.isuse}' /> : <mfmt:message bundle="common" key="false"/>
	    </td>		
	</tr>
	<tr >
		<th><mfmt:message bundle="${m}" key="title" /></th>
		<td colspan="3"><mf:input type="text" name="title" cssStyle="width:98%" value="${item.lang_title}" required="true" hname="TITLE" /></td>
	</tr>
	<tr>
		<th><mfmt:message bundle="${m}" key="page" /></th>
		<td colspan="3"><mf:input type="text" name="page" cssStyle="width:98%" value="${item.page}" /></td>
	</tr>
	<tr>
		<th><mfmt:message bundle="${m}" key="pnodeid" /></th>
		<td><mf:input type="text" name="pnodeid" cssStyle="width:95%" value="${item.pnodeid}" readonly='${item.pgid == "HOME"}' /></td>
		<th><mfmt:message bundle="${m}" key="seq" /></th>
		<td><mf:input type="text" name="seq" value="${item.seq}" cssStyle="width:95%" readonly='${item.pgid == "HOME"}' /></td>
	</tr>
	<tr>
	    <th><mfmt:message bundle="${m}" key="islink" /></th>
	    <td>
            <mf:input type="radio" name="islink" value="T" curValue="${item.islink}" cssClass="frm_checkbox"/> : <mfmt:message bundle="common" key="true"/>
            <mf:input type="radio" name="islink" value="F" curValue="${item.islink}" cssClass="frm_checkbox" /> : <mfmt:message bundle="common" key="false"/>
        </td>
		<th><mfmt:message bundle="${m}" key="is_sitemap" /></th>
		<td>
            <mf:input type="radio" name="is_sitemap" value="T" curValue="${item.is_sitemap}" /> : <mfmt:message bundle="common" key="true"/>
            <mf:input type="radio" name="is_sitemap" value="F" curValue="${item.is_sitemap}" /> : <mfmt:message bundle="common" key="false"/>
	    </td>
	</tr>	
	<tr>
        <th><mfmt:message bundle="${m}" key="messagekey" /></th>
        <td><mf:input type="text" name="messagekey" cssStyle="width:95%" value="${item.messagekey}" hname="MESSAGEKEY"/></td>
		<th><mfmt:message bundle="${m}" key="target" /></th>
		<td><mf:input type="text" name="target" cssStyle="width:95%" value="${item.target}" readonly='${item.pgid == "HOME"}' required="true" hname="TARGET"/></td>
	</tr>
	<tr>
	    <th><mfmt:message bundle="${m}" key="is_tmenu" /></th>
	    <td>
			<mf:input type="radio" name="is_tmenu" value="T" curValue="${item.is_tmenu}" /> : <mfmt:message bundle="common" key="true"/>
			<mf:input type="radio" name="is_tmenu" value="F" curValue="${item.is_tmenu}" /> : <mfmt:message bundle="common" key="false"/>		
	    </td>
	    <th><mfmt:message bundle="${m}" key="is_lmenu" /></th>
	    <td>
			<mf:input type="radio" name="is_lmenu" value="T" curValue="${item.is_lmenu}"  /> : <mfmt:message bundle="common" key="true"/>
			<mf:input type="radio" name="is_lmenu" value="F" curValue="${item.is_lmenu}"  /> : <mfmt:message bundle="common" key="false"/>		
	    </td>
	</tr>		
	<tr>
	    <th><mfmt:message bundle="${m}" key="contact" /></th>
	    <td><mf:input type="text" name="contact" cssStyle="width:95%" value="${item.contact}" /></td>
	    <th><mfmt:message bundle="${m}" key="contact_email" /></th>
	    <td><mf:input type="text" name="contact_email" cssStyle="width:95%" value="${item.contact_email}" /></td>
	</tr>
	<tr>
	    <th><mfmt:message bundle="${m}" key="help_file" /></th>
	    <td><mf:input type="text" name="help_file" cssStyle="width:95%" value="${item.help_file}" /></td>
	    <th><mfmt:message bundle="${m}" key="short_desc" /></th>
	    <td><mf:input type="text" name="short_desc" cssStyle="width:95%" value="${item.short_desc}" /></td>
	</tr>	
	<tr>
		<th><mfmt:message bundle="${m}" key="isservlet" /></th>
		<td>
		   <mf:input type="radio" name="isservlet" value="T" curValue="${item.isservlet}"  /> : <mfmt:message bundle="common" key="true"/>
		   <mf:input type="radio" name="isservlet" value="F" curValue="${item.isservlet}"  /> : <mfmt:message bundle="common" key="false"/>
		</td>
		<th><mfmt:message bundle="${m}" key="session_chk" /></th>
		<td>
			<mf:input type="radio" name="session_chk" value="T" curValue="${item.session_chk}"  /> : <mfmt:message bundle="common" key="true"/>
     		<mf:input type="radio" name="session_chk" value="F"  curValue="${item.session_chk}"  /> : <mfmt:message bundle="common" key="false"/>
    	</td>
	</tr>
	<mf:input type="hidden" name="cmd" value="${acttype}"/>
	<mf:input type="hidden" name="site" value="${site}"/>
	<mf:input type="hidden" name="current_pgid" value="${pgid}"/>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="viewBtn">
<tr>
    <td align="right">
            <mf:button onclick="javascript:m_update()" bundle="button" key="save" />
        <c:if test="${acttype == 'update'}">
            <mf:button onclick="javascript:m_add()" bundle="button" key="add" />
            <mf:button onclick="javascript:m_delete()" bundle="button" key="delete" />
        </c:if>
    </td>
</tr>
</table>
<c:if test="${acttype == 'update'}">
<table width="100%" cellpadding="1" cellspacing="0" border="0" class="view">
    <col width="*"/>
    <col width="10%"/>
    <col width="10%"/>
    <col width="10%"/>
    <col width="10%"/>
    <col width="10%"/>
    <tr>
        <th><mfmt:message bundle="common" key="role.name"/></th>
        <th><mfmt:message bundle="common" key="check.all"/></th>
        <th><mfmt:message bundle="common" key="create"/></th>
        <th><mfmt:message bundle="common" key="read"/></th>
        <th><mfmt:message bundle="common" key="update"/></th>
        <th><mfmt:message bundle="common" key="delete"/></th>
    </tr>
    <c:forEach var="item" items="${pgAuth}">
    <c:set var="ctype" value="C"/>  
    <c:if test="${item.root!='1'}">
    <c:if test="${item.pgid != '' && item.pgid != null}"><c:set var="ctype" value="U"/></c:if>
    <mf:input type="hidden" name="ctype_${item.role}" value="${ctype}"/>
    <mf:input type="hidden" name="vrole" value="${item.role}"/>
    <tr>
        <td align="center"><mh:out value="${item.role}(${item.name})" td="true"/></td>
        <td align="center"><mf:input type="checkbox" name="check_all_${item.role}" value="Y" onclick="McheckAll(this,'${item.role}')"/></td>
        <td align="center"><mf:input type="checkbox" name="auth_c_${item.role}" value="Y" curValue="${item.auth_c}"/></td>
        <td align="center"><mf:input type="checkbox" name="auth_r_${item.role}" value="Y" curValue="${item.auth_r}"/></td>
        <td align="center"><mf:input type="checkbox" name="auth_u_${item.role}" value="Y" curValue="${item.auth_u}"/></td>
        <td align="center"><mf:input type="checkbox" name="auth_d_${item.role}" value="Y" curValue="${item.auth_d}"/></td>
    </tr>
    </c:if>
    </c:forEach>
</table>
</c:if>
        </td>
    </tr>
</table>
</mf:form>

<script language="javascript" >
<!--
var vfrm = document.frmEdit;
function m_delete(){
    if (confirm('<mfmt:message bundle="common.scripts" key="alert.delete"/>')) {
        vfrm.cmd.value="delete";
        vfrm.submit();
    }
}
function m_update(){
    if (validate(vfrm)) {      
        if (confirm('<mfmt:message bundle="common.scripts" key="alert.save"/>')) {
            vfrm.submit();
        }
    }   
}
function m_add(){
    <c:url var="t" value="${control_action}">
        <c:param name="cmd" value="add"/>
        <c:param name="site" value="${site}"/>
    </c:url>
    if (confirm('<mfmt:message bundle="common.scripts" key="menu.add"/>')) {
        document.location.href = '<c:out value="${t}" escapeXml="false"/>&pnodeid=' + vfrm.pgid.value;
    }
}
function McheckAll(obj, nm) {
    var frm = getObject('frmEdit');
    if(frm) {
        var nm_c = "auth_c_" + nm;
        var nm_r = "auth_r_" + nm;
        var nm_u = "auth_u_" + nm;
        var nm_d = "auth_d_" + nm;
        for (var i=0; i<frm.elements.length;i++) {
            if (frm.elements[i].type == "checkbox" && frm.elements[i].name == nm_c) {
                frm.elements[i].checked = obj.checked;
            }
            if (frm.elements[i].type == "checkbox" && frm.elements[i].name == nm_r) {
                frm.elements[i].checked = obj.checked;
            }
            if (frm.elements[i].type == "checkbox" && frm.elements[i].name == nm_u) {
                frm.elements[i].checked = obj.checked;
            }
            if (frm.elements[i].type == "checkbox" && frm.elements[i].name == nm_d) {
                frm.elements[i].checked = obj.checked;
            }
        }
    }
}

<c:if test="${param.reload =='T'}">
    parent.frmTree.location.reload();
</c:if>
//-->
</script>