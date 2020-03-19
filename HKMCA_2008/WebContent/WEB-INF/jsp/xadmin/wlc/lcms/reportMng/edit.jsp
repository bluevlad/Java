<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>
<%@page import="maf.mafUtil"%>

<script language="javascript">
<!--
function frmSubmit() {
    var frm = getObject("myform");
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

function frmDelete(){
    var frm = getObject("myform");
    if(frm) {
        frm.cmd.value = "delete";
        frm.submit();
    }
}

function goList() {
    <c:url var="urlList" value="${control_action}">
        <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
        <c:param name="cmd" value="list"/>
    </c:url>
    document.location = '<mh:out value="${urlList}"/>';
}

//-->
</script>
<mf:form action="${control_action}" enctype="multipart/form-data" method="post" name="myform" id="myform" onSubmit=" frmSubmit();return false;">
<mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}" />
<mf:input type="hidden" name="cmd" value=""/>
<mf:input type="hidden" name="quecode" value="${item.quecode}" />
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
	<col width="15%" />
	<col width="75%" />
    <tr>
	   <th><mf:label name="sjt_cd"/></th> 
	   <td>
		  <select name="sjt_cd">
		  <c:forEach var="sbjlist" items="${slist}" varStatus="status">
    		  <mf:option text="${sbjlist.sjt_nm}" value="${sbjlist.sjt_cd}" curValue="${item.sjt_cd}" />
		  </c:forEach>
		  </select>
        </td>
    </tr>
    <tr>
        <th><mf:label name="quetitle"/></th> 
        <td><mf:input type="text" name="quetitle" size="80" maxlength="100" value="${item.quetitle}"/></td>
    </tr>
    <tr>
        <th><mf:label name="quefile" /></th>
        <td>
            <table border="0" cellpadding="2" cellspacing="0" width="100%">
                <tr>
                    <td>
			            <jsp:include page="/WEB-INF/jsp/common/fileAttach/fileAttach.jsp" flush="true">
			            <jsp:param name="FILEID" value="attach_file" />     
			            </jsp:include><br>
                    </td>
                </tr>
                <c:if test="${!empty(flist)}">
                <tr>
                    <th>삭제할 파일을 선택하세요</th>
                </tr>
                <tr>
                    <td>
			            <c:forEach var="fi" items="${flist}">
			            <mf:input name="delfiles" type="checkbox" value="${fi.file_id}"/>
			            <mh:getIcon value="${fi.org_filename}"/>
			            <a href='<c:url value="/pds/report/${fi.new_filename}?cmd=save">
			            <c:param name="file" value="/pds/report/${fi.new_filename}"/>
			            <c:param name="pds_cd" value="${fi.pds_cd}"/>
			            <c:param name="main_cd" value="${fi.main_cd}"/>
			            <c:param name="sub_cd" value="${fi.sub_cd}"/>
			            <c:param name="file_id" value="${fi.file_id}"/>
			            </c:url>' target="_blank" >
			            <mh:out value="${fi.org_filename}"/></a><br>
			            </c:forEach>
                    </td>   
                </tr>
                </c:if>
            </table>
        </td>
    </tr>
    <tr>
        <th><mf:label name="quedesc" /></th>
        <td><mf:textarea name="quedesc" cols="110" rows="3" value="${item.quedesc}"/></td>
    </tr>
</table>
<table width="100%" border="0" cellpadding="2" cellspacing="0" class="viewBtn">
    <tr>
        <td align="right">
            <mf:button bundle="button" key="save" onclick="frmSubmit()" icon="icon_save" />
            <c:if test="${param.cmd == 'edit'}">
            <mf:button bundle="button" key="delete" onclick="frmDelete()" icon="icon_delete" />
            </c:if>
            <mf:button bundle="button" key="list" onclick="goList()" icon="icon_list" />
        </td>
    </tr>
</table>
</mf:form>