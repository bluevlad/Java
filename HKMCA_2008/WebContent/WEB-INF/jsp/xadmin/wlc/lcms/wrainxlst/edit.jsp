<%@ page contentType="text/html; charset=utf-8"%>     
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<%@ page import="jmf.*" %>

<script language="javascript" >
<!--
function frmSubmit(frmName) {
    var frm = getObject(frmName);
    if(frm) {
        if (validate(frm)) {
    <c:choose>
    <c:when test="${param.cmd == 'add'}">
        frm.cmd.value="insert";
    </c:when>
    <c:when test="${param.cmd == 'edit'}">
        frm.cmd.value="update";
    </c:when>
    </c:choose>
        frm.submit();
        }
    
    }
}
    
function frmDelete() {
    var frm = getObject("myform");
        frm.cmd.value="delete";
        frm.submit();
}

function goList() {
    <c:url var="urlList" value="${control_action}">
        <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
        <c:param name="cmd" value="list"/>
    </c:url>
    document.location = '<mh:out value="${urlList}"/>';
}

// subject name set
function setSjtname(frm) {
    if(frm.sjt_cd.selectedIndex > 0)
        frm.daecode.value = frm.sjt_cd[frm.sjt_cd.selectedIndex].value;
    else
        frm.daecode.value = '';
}
//-->
</script>

<div class="viewContainer">
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit=" frmSubmit('myform');return false;">
<mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}"/>
<mf:input type="hidden" name="cmd" value=""/>    
<mf:input type="hidden" name="htmcode" value="${item.htmcode}"/>  
<table width="100%" border="0" cellpadding="2" cellspacing="0" class="view">    
    <col width="20%"/>
    <col width="30%"/>    
    <col width="20%"/>
    <col width="30%"/>    
    <tr>
    <c:choose>
    <c:when test="${param.cmd == 'add'}">
        <th nowrap><mf:label name="sjt_cd"/></th> 
        <td colspan="3">
	        <select name="sjt_cd" required onChange="setSjtname(this.form);">
	        <option value=""><mfmt:message bundle="table.wlc_inx_lst" key="select_subject"/></option>
	        <c:forEach var="sbjlist" items="${slist}" varStatus="status">
	        <mf:option text="[${sbjlist.sjt_cd}]${sbjlist.sjt_nm}" value="${sbjlist.sjt_cd}"/>
	        </c:forEach>
	        </select>
        </td>
    </c:when>
    <c:when test="${param.cmd == 'edit'}">
        <th><mf:label name="sjt_cd"/></th> 
        <td><mh:out value="${item.sjt_cd}" td="true" /></td>
        <mf:input type="hidden" name="sjt_cd" value="${item.sjt_cd}"/>
        <th><mf:label name="htmcode"/></th> 
        <td><mh:out value="${item.htmcode}" td="true"/></td>
    </c:when>
    </c:choose>
    </tr>
    <tr>
        <th><mf:label name="daecode"/></th> 
        <td><mf:input type="text" name="daecode" cssStyle="width:95%" value="${item.daecode}"/></td>
        <th><mf:label name="daename"/></th> 
        <td><mf:input type="text" name="daename" cssStyle="width:95%" value="${item.daename}"/></td>
    </tr>    
    <tr>
        <th><mf:label name="cenname"/></th> 
        <td><mf:input type="text" name="cenname" cssStyle="width:95%" value="${item.cenname}"/></td>
        <th><mf:label name="soname"/></th> 
        <td><mf:input type="text" name="soname" cssStyle="width:95%" value="${item.soname}"/></td>
    </tr>    
    <tr>
        <th><mf:label name="htmurl"/></th> 
        <td colspan=3>
            <mf:input type="text" name="htmurl" cssStyle="width:400px" value="${item.htmurl}" required="true"/> 
            <mf:button key="inx_lst.select" bundle="button" onclick="openContents('htmurl')"/>
            <mf:button key="preview" bundle="button" onclick="previewLec('htmurl')"/>
        </td>
    </tr>
    <tr>
        <th><mf:label name="totpage"/></th> 
        <td><mf:input type="text" name="totpage" cssStyle="width:50px" option="number" value="${item.totpage}"/></td>
        <th><mf:label name="lrntime"/></th> 
        <td><mf:input type="text" name="lrntime" cssStyle="width:50px" option="number" value="${item.lrntime}"/></td>
    </tr>    
    <tr>
        <th><mf:label name="popup_size"/></th> 
        <td>
            <mf:label name="cnt_width"/>
            <mf:input type="text" name="cnt_width" cssStyle="width:40px" value="${item.cnt_width}" option="number"/> X
            <mf:label name="cnt_height"/>
            <mf:input type="text" name="cnt_height" cssStyle="width:40px" value="${item.cnt_height}" option="number"/>
        </td>
        <th><mf:label name="cnt_type"/></th> 
        <td><mf:select name="cnt_type" codeGroup="LEC.CNT_TYPE" curValue="${item.cnt_type}"/></td>
    </tr>    
</table>
<table width="100%" border="0" cellpadding="2" cellspacing="0" class="viewBtn">     
    <tr>
        <td align="right">
	        <mf:button bundle="button" key="save" onclick="frmSubmit('myform')" icon="icon_save"/>   
            <mf:button bundle="button" key="delete" onclick="frmDelete()" icon="icon_delete"/>   
	        <mf:button bundle="button" key="list" onclick="goList()" icon="icon_list"/>
        </td>
    </tr>
</table>
</mf:form>
</div>

<script>
// 강의 보기 
function previewLec(nm){
    var frm = getObject("myform");
    var urlname = frm[nm].value;
    var width=frm.cnt_width.value;
    var height=frm.cnt_height.value;
    if(width == "") {
        width = 800;
    }
    if(height == "") {
        height = 600;
    }
    browsing_window = window.open(urlname, "imagewin","top=200px,left=200px,status=no,resizable=yes,menubar=no,scrollbars=yes,width="+width+",height="+height);
    browsing_window.focus();
}   
function openContents(elname) {
    popupWindow('<c:url value="/wlc.admin/ContentsFinder.do"/>?elname='+elname);
}
</script>