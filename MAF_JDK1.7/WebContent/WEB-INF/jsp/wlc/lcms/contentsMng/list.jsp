<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<SCRIPT LANGUAGE="JavaScript">
<!--
    function doWrite(){
        var frm = getObject("myform");
        if(frm) {
            frm.cmd.value = "add";
            frm.submit();
        }
    }

    function doView(sjt_cd){
        var frm = getObject("myform");

        if(frm) {
            frm.cmd.value = "edit";
            frm.sjt_cd.value=sjt_cd;
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

    function doDelete() {
        var frm = getObject("myform");
        if((frm)) {
            frm.cmd.value = "delete";
            frm.submit();
        }
    }

    function doExcelUp() {
        var frm = getObject("myform");
        if(frm) {
            frm.cmd.value = "add_excel";
            frm.submit();
        }
    }
//-->
</SCRIPT>

<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
        <td><div class="searchContainer">
            <h1><mfmt:message bundle="common" key="searchtitle"/></h1>
            <mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this,'list');return false; ">
            <mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}"/>
            <input type="image" id="dummy" width="0" height="0" border="0" class="hidden"/>
            <input type="hidden" name="miv_page" value="1"/>
            <input type="hidden" name="cmd" value="list"/>
            <input type='hidden' name='sjt_cd' value=''>
                <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
                <tr>
                    <th width="15%" ><mf:label name="sjt_cd"/></th>
                    <td width="15%" ><mf:input type="text" name="s_sjt_cd" value="${LISTOP.ht.s_sjt_cd}"/></td>
                    <th width="15%" ><mf:label name="subject_nm"/></th>
                    <td width="20%" ><mf:input type="text" name="subject_nm" value="${LISTOP.ht.subject_nm}"/></td>
                 </tr>
               </table>
                <table border="0" cellspacing="0" cellpadding="2" class="searchBtn">
                    <tr><td>
                    <mf:button onclick="frmSubmit('myform','list')" bundle="button" key="search" icon="icon_search"/>
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
            <th><mf:header name="sjt_cd" sort="true"/></th>
            <th><mf:header name="subject_nm"/></th>
            <th><mf:header name="orgn_cd"/></th>
            <th><mf:header name="count"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${navigator.list}" varStatus="status">
        <tr >

            <td><mh:listseq navigator="${navigator}" count="${status.count}"/></td>
            <td ><mh:out value="${item.sjt_cd}" td="true" /></td>
            <td ><a href="javaScript:doView('<mh:out value="${item.sjt_cd}" td="true" />')"/>
                    <mh:out value="${item.subject_nm}" td="true" /></a></td>
            <td align="center"><mh:out value="${item.orgn_cd}" td="true" /></td>
            <td align="center"><mh:out value="${item.cnt}" td="true" /></td>
        </tr>
        </c:forEach>
        </tbody>
        </table></div>
        </td>
    </mf:form>
    </tr>
    <tr>
        <td align="center">
            <mf:button bundle="button" key="add" onclick="doWrite()" icon="icon_add"/>
            <mf:button onclick="doDelete()" bundle="button" key="delete" icon="icon_delete"/>
            <mf:button onclick="doExcelUp()" bundle="button" key="button.excel.up" icon="icon_search"/><br>
            <jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/></td>
    </tr>
</table>
