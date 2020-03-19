<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<SCRIPT LANGUAGE="JavaScript">
<!--
    function doEdit(psell_cd){
        var frm = getObject("myform");
        if(frm) {
            frm.cmd.value = "edit";
            frm.psell_cd.value = psell_cd;
            frm.submit();
        }
    }

	function doWrite(){
	    var frm = getObject("myform");
	    if(frm) {
	        frm.cmd.value = "add";
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
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this,'list');">
<mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}" />
<mf:input type="hidden" name="miv_page" value="1" />
<mf:input type="hidden" name="cmd" value="list" />
<mf:input type="hidden" name="psell_cd" value=""/>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
        <td>
        <div class="searchContainer">
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
                <col width="15%">
                <col width="35%">
                <col width="15%">
                <col width="35%">
                <tr>
                    <th><mf:label name="prd_nm" for="s_prd_nm" /></th>
                    <td><mf:input type="text" name="s_prd_nm" value="${LISTOP.ht.s_prd_nm}" /></td>
                    <th><mf:label name="sell_nm" for="s_sell_nm" /></th>
                    <td><mf:input type="text" name="s_sell_nm" value="${LISTOP.ht.s_sell_nm}" /></td>
                </tr>
            </table>
            <table border="0" cellspacing="0" cellpadding="2" class="searchBtn">
                <tr>
                    <td><mf:button onclick="frmSubmit('myform','list')" bundle="button" key="search" icon="icon_search" /></td>
                </tr>
            </table>
        </div>
        </td>
    </tr>
    <tr>
        <td>
	        <div class="listContainer">
	        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
                <col width="5%">
                <col width="*">
                <col width="25%">
                <col width="15%">
                <col width="15%">
                <thead>
                <tr>
                    <th>#</th>
                    <th><mf:header name="prd_nm" sort="true" /></th>
                    <th><mf:header name="sell_nm" sort="true" /></th>
                    <th><mf:header name="psell_price" sort="true" /></th>
                    <th><mf:header name="reg_dt" sort="true" /></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${navigator.list}" varStatus="status">
                    <tr>
                        <td align="center"><mh:listseq navigator="${navigator}" count="${status.count}" /></td>
                        <td align="center"><a href="javaScript:doEdit('<c:out value="${item.psell_cd}"/>')" /><mh:out value="${item.prd_nm}" td="true" /></a></td>
                        <td align="center"><a href="javaScript:doEdit('<c:out value="${item.psell_cd}"/>')" /><mh:out value="${item.sell_nm}" td="true" /></a></td>
                        <td align="center"><mh:out value="${item.psell_price}" format="currency" /></td>
                        <td align="center"><mh:out value="${item.reg_dt}" format="fulldate" /></td>
                    </tr>
                </c:forEach>
            </tbody>
	        </table>
	        </div>
        </td>
    </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="2" class="viewBtn">
    <tr>
        <td align="right">
            <mf:button bundle="button" key="add" onclick="doWrite()" icon="icon_add"/>
        </td>
    </tr>
</table>
</mf:form>
<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/>