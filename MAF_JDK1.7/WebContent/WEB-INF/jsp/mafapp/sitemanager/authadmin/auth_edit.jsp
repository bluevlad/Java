<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="mh" uri="http://maf.miraenet.com/jsp/tld/mi-html.tld"%>
<%@ taglib prefix="mf" uri="http://maf.miraenet.com/jsp/tld/mi-form.tld"%>
<%@ taglib prefix="mfmt" uri="http://maf.miraenet.com/jsp/tld/mi-fmt.tld"%>
<SCRIPT LANGUAGE="JavaScript">
<!--
	function frmSubmit() {
		var frm = getObject("frmEdit");	
		if (confirm("저장하시겠습니까?")) {
			frm.submit();
		}
	}
//-->
</SCRIPT>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
<mf:form action="${control_action}" method="post" name="frmEdit" id="frmEdit">
	<tr>
		<td>
		<table width="100%" height="30" cellpadding="2" cellspacing="2" border="0">
			<tr>
				<th class="view">Site</th>
				<td class="view"><mh:out value="${site}" td="true"/></td>
				<th class="view">PGID</th>
				<td class="view"><mh:out value="${pgid}" td="true"/></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td><table width="100%" cellpadding="2" cellspacing="0" border="0" class="view">
			<col width="50%"/>
			<col width="10%"/>
            <col width="10%"/>
			<col width="10%"/>
			<col width="10%"/>
			<col width="10%"/>
			<tr>
				<th>Role Name</th>
                <th>CheckAll</th>
				<th>Create</th>
				<th>Read</th>
				<th>Update</th>
				<th>Delete</th>
			</tr>
			<c:forEach var="item" items="${pgAuth}">
			<c:set var="ctype" value="C"/>	
			<c:if test="${item.root!='1'}">
    			<c:if test="${item.pgid != '' && item.pgid != null}"><c:set var="ctype" value="U"/></c:if>
    			<mf:input type="hidden" name="ctype_${item.role}" value="${ctype}"/>
    			<mf:input type="hidden" name="vrole" value="${item.role}"/>
    			<tr>
    				<td align="center"><mh:out value="${item.role}(${item.name})" td="true"/></th>
                    <td align="center"><mf:input type="checkbox" name="check_all_${item.role}" value="Y" 
                                     onclick="McheckAll(this,'${item.role}')"/></td>
    				<td align="center"><mf:input type="checkbox" name="auth_c_${item.role}" value="Y" curValue="${item.auth_c}"/></td>
    				<td align="center"><mf:input type="checkbox" name="auth_r_${item.role}" value="Y" curValue="${item.auth_r}"/></td>
    				<td align="center"><mf:input type="checkbox" name="auth_u_${item.role}" value="Y" curValue="${item.auth_u}"/></td>
    				<td align="center"><mf:input type="checkbox" name="auth_d_${item.role}" value="Y" curValue="${item.auth_d}"/></td>
    			</tr>
			</c:if>
			</c:forEach>
		</table>
		</td>
	</tr>
	<input type="hidden" name="cmd" value="update">
	<mf:input type="hidden" name="site" value="${site}"/>
	<mf:input type="hidden" name="pgid" value="${pgid}"/>
</mf:form>
</table>
<table width="100%" border="0" cellspacing="1" cellpadding="0">
<tr>
    <td align="center" class="view">
        <img src='<c:url value="/xadmin/images/button/save.gif"/>'  alt="" border="0" class="IMG_BTN" onClick="javascript:frmSubmit();">
    </td>
</tr>
</table>
<script>
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
</script>
<c:if test="${param.reload =='T'}">
	parent.frmTree.location.reload();
</c:if>
</script>