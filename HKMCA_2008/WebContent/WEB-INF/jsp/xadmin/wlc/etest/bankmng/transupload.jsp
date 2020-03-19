<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="ubq.app.resource.*"%>
<%@ page import="maf.mdb.*"%>
<%@ page import="maf.mdb.drivers.*"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>
<%
	MdbDriver oDb = null;
	try {
		oDb = Mdb.getMdbDriver();
		request.setAttribute("langList", ResourceManagerDB.getLangList(oDb));
	} finally {
		if (oDb != null) try {
			oDb.close();
		} catch (Exception ex) {
		}
		oDb = null;
	}
%>
<jsp:include page="_category.jsp" flush="true" />

<script language="javascript">
    function doUploadOk() {
        var frm = getObject("myform");
		if(frm) {
	        frm.cmd.value="uploadTransSource";
	        frm.submit();
        }
    }
    function getSource(){
        var frm = getObject("myform");
		if(frm) {
	        frm.cmd.value="getTransSource";
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
</script>

<div class="viewContainer">
<mf:form action="${control_action}" method="post" enctype="multipart/form-data" name="myform" id="myform">
    <mf:input type="hidden" name="cmd" value="" />
    <mf:input type="hidden" name="cat_id" value="${param.cat_id}" />
    <table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
        <col width="15%" />
        <col width="35%" />
        <col width="15%" />
        <col width="35%" />
        <tr>
        <tr>
            <th><mfmt:message key="cat_name" bundle="table.exm_category"/></th>
            <td>
                <mf:input name="cat_name" type="text" value="${LISTOP.ht.cat_name}" onclick="CategorySelector.showList('cat_name')" cssClass="dropdown" />
                <div id="catTreeDiv"></div>
            </td>
            <th>Language</th>
            <td colspan="3">
	            <mf:select name="lang" items="${langList}" keyValue="code" keyText="allnames" curValue="${curLang}" />
	            <mf:button onclick="getSource()" bundle="button" key="exceldown" />
            </td>
        </tr>
        <tr>
            <th><mh:out value="Upload File" /></th>
            <td><input type="file" name="certifile"></td>
            <td align="right" colspan="3" align="center">
                <mf:button bundle="button" key="excelupload" onclick="javascript:doUploadOk()" />
                <mf:button bundle="button" key="goList" onclick="javascript:goList()" />
            </td>
        </tr>
    </table>
</mf:form>
<jsp:include page="/WEB-INF/jsp/common/excelupload/uploadfileinfo.jsp"/>
</div>