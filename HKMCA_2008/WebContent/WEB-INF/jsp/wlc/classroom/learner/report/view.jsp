<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<script language="javascript">
function frmSubmit() {
    var frm = getObject("myform");
    if(frm) {
        if (validate(frm)) {
            <c:choose>
                <c:when test="${item.usn != null}">
                    frm.cmd.value = "update";
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
</script>
<mf:form action="${control_action}" method="post" enctype="multipart/form-data" name="myform" id="myform">
<mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}" />
<mf:input type="hidden" name="cmd" value=""/>
<mf:input type="hidden" name="rptcode" value="${item.rptcode}" />
<mf:input type="hidden" name="newfilename" value="${gfile.new_filename}" />
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="view">
    <col width="20%">
    <col width="30%">
    <col width="20%">
    <col width="30%">
    <tr>
        <th><mf:label name="rpttitle"/></th>
        <td colspan="3"><mh:out value="${item.rpttitle}" td="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="rptdesc"/></th>
        <td colspan="3"><mh:out value="${item.rptdesc}" td="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="rpt_sdat"/></th>
        <td><mh:out value="${item.rpt_sdat}" td="true"/></td>
        <th><mf:label name="rpt_edat"/></th>
        <td><mh:out value="${item.rpt_edat}" td="true" /></td>
    </tr>
    <tr>
        <th><mf:label name="rptrate"/></th>
        <td><mh:out value="${item.rptrate}" td="true"/></td>
        <th><mf:label name="rptopen"/></th>
        <td><mh:out value="${item.rptopen}" codeGroup="YESORNO"/></td>
    </tr>
    <tr>
        <th><mf:label name="givtitle"/></th>
        <td colspan="3"><mf:input type="text" name="givtitle" cssStyle="width:98%" value="${item.givtitle}"/></td>
    </tr>
    <tr>
        <th><mf:label name="givdesc"/></th>
        <td colspan="3"><mf:textarea name="givdesc" cssStyle="width:98%" rows="3" value="${item.givdesc}"/></td>
    </tr>
    <tr>
        <th><mf:label name="givfile"/></th>
        <td colspan="3"><br>
                        <input type="file" name="givfile" size="60" value=""/><br><br>
			            <mh:getIcon value="${gfile.org_filename}"/>
			            <a href='<c:url value="/pds/report/${gfile.new_filename}?cmd=save">
			            <c:param name="file" value="/pds/report/${gfile.new_filename}"/>
			            <c:param name="pds_cd" value="${gfile.pds_cd}"/>
			            <c:param name="main_cd" value="${gfile.main_cd}"/>
			            <c:param name="sub_cd" value="${gfile.sub_cd}"/>
			            <c:param name="file_id" value="${gfile.file_id}"/>
			            </c:url>' target="_blank" >
			            <mh:out value="${gfile.org_filename}"/></a>
        </td>
    </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="viewBtn">
    <tr>
        <td align="right">
        	<mf:button bundle="button" key="save" onclick="frmSubmit()" icon="icon_save" />
        	<mf:button bundle="button" key="list" onclick="goList()" icon="icon_list" />
        </td>
    </tr>
</table>
</mf:form>
<br>
<div class="listContainer">
<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
    <col width="*" />
    <col width="20%" />
    <thead>
    <tr>
        <th nowrap><mf:header name="quetitle" /></th>
        <th nowrap><mf:header name="quefile" /></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="fi" items="${list}">
    </c:forEach>

    <c:forEach var="list" items="${list}" varStatus="status">
    <tr>
        <td class="center"><mh:out value="${list.quetitle}" td="true" /></td>
        <td>
            <mh:getIcon value="${list.org_filename}"/>
		    <a href='<c:url value="/pds/report/${list.new_filename}?cmd=save">
		    <c:param name="file" value="/pds/report/${list.new_filename}"/>
		    <c:param name="pds_cd" value="${list.pds_cd}"/>
		    <c:param name="main_cd" value="${list.main_cd}"/>
		    <c:param name="sub_cd" value="${list.sub_cd}"/>
		    <c:param name="file_id" value="${list.file_id}"/>
		    </c:url>' target="_blank" >
		    <mh:out value="${list.org_filename}"/></a>
        </td>
    </tr>
    </c:forEach>
    </tbody>
</table>
</div>