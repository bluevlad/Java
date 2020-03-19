<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<SCRIPT LANGUAGE="JavaScript">
<!--
function doWrite(){
    var frm = getObject("myform");
    if(frm) {
        frm.cmd.value = "add";
        frm.submit();
    }
}

function doView(setid){
    var frm = getObject("myform");
    
    if(frm) {
        frm.cmd.value = "edit";
        frm.setid.value=setid;
        frm.submit();
    }
}
function doSearch(frm) {
    if(frm) {
        frm.cmd.value = "list";
        frm.miv_page.value = 1;
        return true;
    }     
}
//-->
</SCRIPT>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
        <td>
	        <div class="searchContainer">
	        <mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this,'list');return false; ">
            <mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}" />
            <input type="hidden" name="miv_page" value="1" />
            <input type="hidden" name="cmd" value="list" />
            <input type='hidden' name='setid' value=''>
            <table width="100%" border="0" cellspacing="0" cellpadding="2">
                <col width="15%">
                <col width="35%">
                <col width="15%">
                <col width="35%">
                <tr>
                    <th><mf:label name="settitle" for="s_settitle" /></th>
                    <td colspan="3"><mf:input type="text" size="100" name="s_settitle" value="${LISTOP.ht.s_settitle}" /></td>
                </tr>
            </table>
            <table border="0" cellspacing="0" cellpadding="2" class="searchBtn">
                <tr>
                    <td><mf:button onclick="frmSubmit('myform','list')" bundle="button" key="search" /></td>
                </tr>
            </table>
            </mf:form>
            </div>
        </td>
    </tr>
    <tr>
        <td>
        <div class="listContainer">
        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
            <col width="50"/>
            <col width="*"/>
            <col width="100"/>
            <col width="100"/>
            <thead>
                <tr>
                    <th>#</th>
                    <th><mf:header name="settitle" sort="true" /></th>
                    <th><mf:header name="questions"  /></th>
                    <th><mf:header name="upt_dt" sort="true" /></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${navigator.list}" varStatus="status">
                    <tr>
                        <td class="center"><mh:listseq navigator="${navigator}" count="${status.count}" /></td>
                        <td><a href="javaScript:doView('<c:out value="${item.setid}"/>')"/><mh:out value="${item.settitle}" td="true" /></a></td>
                        <td align="center"><mh:out value="${item.exmcnt1+item.exmcnt2+item.exmcnt3+item.exmcnt4+item.exmcnt5}" td="true" /></td>
                        <td align="center"><mh:out value="${item.upt_dt}" format="fulldate" td="true" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        </div>
        </td>
    </tr>
</table>
<table border="0" cellpadding="0" cellspacing="2" class="viewBtn" width="100%">
    <tr>
        <td align="right"><mf:button bundle="button" key="add" onclick="doWrite()" icon="icon_add" /></td>
    </tr>
</table>
<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true" />