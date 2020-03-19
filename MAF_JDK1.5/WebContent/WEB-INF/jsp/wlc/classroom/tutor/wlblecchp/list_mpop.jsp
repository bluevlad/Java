<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript" src='<c:url value="/js/lib.validate.js"/>'></script>
<SCRIPT LANGUAGE="JavaScript">
<!--
function doSearch() {
    var frm = getObject("myform");
    if(frm) {
        frm.cmd.value = "mselist";
        frm.miv_page.value = 1;
        frm.submit();
    }     
}

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
    var frm = getObject("myform");
    var words="";
    var chk=0;
    var cnt = getLength(frm.checkbox);
    if(cnt <= 1) {
        chk=1;
    } else {
        for(i=0;i<frm.checkbox.length;i++)   {
            if(frm.checkbox[i].checked) {
                chk=1;
                break;
            }
        }
    }
    if(chk == 1) {
        frm.cmd.value = "insert_multi";
        frm.submit();
        window.opener.document.location.reload();        
    }
}

function doClose() {
        window.opener.document.location.reload();
        self.close();        
}    
//-->
</SCRIPT>

<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this,'mselist');return false; ">
    <mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}"/>
    <input type="image" id="dummy" width="0" height="0" border="0" class="hidden"/>
    <input type="hidden" name="miv_page" value="1"/>
    <input type="hidden" name="cmd" value="mselist"/>
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
            <th><mf:input type="checkbox" name="checkboxAll" 
                                    value="allcode"
                                    onclick="allChekboxToggle(this,'myform','htmcode_chk')" /></th>
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
            <td><mf:input type="checkbox" name="htmcode_chk" value="${item.htmcode}" /></td>
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
        <mf:button onclick="doClose()" bundle="button" key="close" icon="icon_close"/>
        </a>
        </td>
    </tr>
</table>

