<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<SCRIPT LANGUAGE="JavaScript">
<!--
function selectionContents(val) {
    var words = val.split("##");
    window.opener.myform.sjt_cd.value = words[0];   // sjt_cd
    window.opener.myform.itm_id.value = words[1];   // daecode
    window.opener.myform.htmcode.value = words[2];   // htmcode
    window.opener.myform.itm_title.value = words[3];   // cenname
    window.opener.myform.launch_data.value = words[4];   // htmurl
//    window.opener.myform.cnt_width.value = words[5];   // window width
//    window.opener.myform.cnt_height.value = words[6];   // windows height
//    window.opener.myform.totpage.value = words[7];   // totpage
//    window.opener.myform.itm_maxtimeallowed.value = words[8];   // lrntime
    window.self.close();
}
//-->
</SCRIPT>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
        <td>
            <div class="searchContainer">
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
                <tr>
                    <th width="20%"><mf:label name="itm_title"/></th>
                    <td width="80%" colspan="3"><mf:input type="text" name="daename" size="50" value="${LISTOP.ht.daename}"/></td>
                 </tr>
            </table>
            <table border="0" cellspacing="0" cellpadding="2" class="searchBtn">
                <tr>
                    <td><mf:button onclick="frmSubmit('cntform','selist')" bundle="button" key="search" icon="icon_search"/></td>
                </tr>
            </table>
            </div>
        </td>
    </tr>
    <tr>
        <td><div class="listContainer">
        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
	        <thead>
	        <tr>
                <th>#</th>
	            <th><mfmt:message bundle="table.wlc_inx_lst" key="htmcode"/></th>
	            <th><mfmt:message bundle="table.wlc_inx_lst" key="daename"/></th>
	            <th><mfmt:message bundle="table.wlc_inx_lst" key="cenname"/></th>
	            <th><mfmt:message bundle="common" key="active_yn"/></th>
	        </tr>
	        </thead>
	        <tbody>
	        <c:forEach var="item" items="${navigator.list}" varStatus="status">
	        <tr>
                <td align="center"><a href="javascript:selectionContents('<mh:out value="${item.sjt_cd}##${item.daecode}##${item.htmcode}##${item.cenname}##${item.htmurl}##${item.cnt_width}##${item.cnt_height}##${item.totpage}##${item.lrntime}"/>')"><mh:out value="${item.htmcode}" td="true" /></a></td>
	            <td align="center"><a href="javascript:selectionContents('<mh:out value="${item.sjt_cd}##${item.daecode}##${item.htmcode}##${item.cenname}##${item.htmurl}##${item.cnt_width}##${item.cnt_height}##${item.totpage}##${item.lrntime}"/>')"><mh:out value="${item.daecode}" td="true" /></a></td>
                <td><a href="javascript:selectionContents('<mh:out value="${item.sjt_cd}##${item.daecode}##${item.htmcode}##${item.cenname}##${item.htmurl}##${item.cnt_width}##${item.cnt_height}##${item.totpage}##${item.lrntime}"/>')"><mh:out value="${item.daename}" td="true" /></a></td>
                <td><a href="javascript:selectionContents('<mh:out value="${item.sjt_cd}##${item.daecode}##${item.htmcode}##${item.cenname}##${item.htmurl}##${item.cnt_width}##${item.cnt_height}##${item.totpage}##${item.lrntime}"/>')"><mh:out value="${item.cenname}" td="true" /></a></td>
	            <td align="center"><mh:out value="${item.active_yn}" codeGroup="ACTIVE_YN" td="true"/></td>
	        </tr>
	        </c:forEach>
	        </tbody>
        </table>
        </div>
        </td>
    </tr>
</table>