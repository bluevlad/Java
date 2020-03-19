<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript" src='<c:url value="/js/lib.validate.js"/>'></script>
<SCRIPT LANGUAGE="JavaScript">
<!--
/**
* return Object length
* 
*/
function getLength(obj){
    if(obj ==  "undefined" || obj != "[object]"){
        return 0;
    }else if(toString(obj.length) == "[object]" && obj.length > 1){
        return obj.length;
    }else{
        return 1;
    }
}
    
function selectionContents(form) {
    var words="";
    var chk=0;
    var cnt = getLength(form.selcode);
    if(cnt <= 1) {
        words = form.selcode.value.split("##");
        chk=1;
    } else {
        for(i=0;i<form.selcode.length;i++)   {
            if(form.selcode[i].checked) {
                words = form.selcode[i].value.split("##");
                chk=1;
                break;
            }
        }
    }
    if(chk == 1) {
        window.opener.myform.htmcode.value = words[0];   // htmcode
        window.opener.myform.itm_title.value = words[1];   // cenname
        window.opener.myform.launch_data.value = words[2];   // htmurl
        window.opener.myform.itm_maxtimeallowed.value = words[6];   // itm_maxtimeallowed
        window.opener.myform.cnt_width.value = words[3];   // window width
        window.opener.myform.cnt_height.value = words[4];   // windows height
        window.opener.myform.totpage.value = words[5];   // totpage
        window.self.close();
    } else {
    }
}    
//-->
</SCRIPT>
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this,'selist');return false; ">
    <mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}"/>
    <input type="image" id="dummy" width="0" height="0" border="0" class="hidden"/>
    <input type="hidden" name="miv_page" value="1"/>
    <input type="hidden" name="cmd" value="selist"/>
    <mf:input type="hidden" name="sjtcode" value="${sjtcode}" />
<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
        <td><div class="searchContainer">
            <h1><mfmt:message bundle="common" key="searchtitle"/></h1>
                <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
                <tr>
                    <th width="20%" ><mf:label name="itm_title"/></th>
                    <td width="80%" colspan="3"><mf:input type="text" name="daename" value="${LISTOP.ht.daename}"/></td>
                 </tr>
               </table>
                <table border="0" cellspacing="0" cellpadding="2" class="searchBtn">
                    <tr><td>
                    <mf:button onclick="frmSubmit('myform','selist')" bundle="button" key="search" icon="icon_search"/>
                    <mf:button onclick="selectionContents(document.myform)" bundle="table.wlb_lec_chp" key="title12" icon="icon_search"/>
                    </td></tr>
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
            <th><mfmt:message bundle="table.wra_inx_lst" key="sjtcode"/></th>
            <th><mfmt:message bundle="table.wra_inx_lst" key="sjtname"/></th>
            <th><mfmt:message bundle="table.wra_inx_lst" key="htmcode"/></th>
            <th><mfmt:message bundle="table.wra_inx_lst" key="daename"/></th>
            <th><mfmt:message bundle="table.wra_inx_lst" key="impdat"/></th>
            <th><mfmt:message bundle="table.wra_inx_lst" key="isuse"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${navigator.list}" varStatus="status">
        <tr >
            <td><mf:input type="radio" name="selcode" value="${item.htmcode}##${item.daename}##${item.htmurl}##${item.cnt_width}##${item.cnt_height}##${item.totpage}##${item.lrntime}" /></td>
            <td ><mh:out value="${item.sjtcode}" td="true" /></td>
            <td ><mh:out value="${item.sjtname}" td="true" /></td>
            <td ><mh:out value="${item.htmcode}" td="true" /></td>
            <td ><mh:out value="${item.daename}" td="true" /></td>
            <td ><fmt:formatDate value="${item.impdat}" pattern="yyyy-MM-dd"/></td>
            <td ><mh:out value="${item.isuse}" codeGroup="ISUSE" td="true"/></td>
        </tr>
        </c:forEach>
        </tbody>
        </table></div>
        </td>
    </tr>
</table>    
</mf:form>
<table width="100%" border="0" cellspacing="0" cellpadding="2">    
    <tr>
        <td align="center">
        <mf:button onclick="selectionContents(document.myform)" bundle="table.wlb_lec_chp" key="title12" icon="icon_search"/>
        <mf:button onclick="self.close()" bundle="button" key="close" icon="icon_close"/></td>
    </tr>
</table>

