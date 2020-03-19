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
    
	function doEdit(xch_cd){
	    var frm = getObject("myform");
	    if(frm) {
	        frm.cmd.value = "edit";
            frm.xch_cd.value = xch_cd;
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
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this,'list');return false;">
<mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}" />
<mf:input type="hidden" name="miv_page" value="1" />
<mf:input type="hidden" name="cmd" value="list" />
<mf:input type='hidden' name='xch_cd' value=''/>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
        <td>
        <div class="searchContainer">
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
                <tr>
                    <th><mf:label name="xch_cd" for="s_xch_cd" /></th>
                    <td><mf:input type="text" name="s_xch_cd" value="${LISTOP.ht.s_xch_cd}" /></td>
                    <th><mf:label name="xch_nm" for="s_xch_nm" /></th>
                    <td><mf:input type="text" name="s_xch_nm" value="${LISTOP.ht.s_xch_nm}" /></td>
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
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
            <thead>
                <tr>
                    <th>#</th>
                    <th><mf:header name="xch_nm" sort="true"/></th>
                    <th><mf:header name="xch_amount" sort="true" /></th>
                    <th><mf:header name="xch_unit" sort="true" /></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${navigator.list}" varStatus="status">
                    <tr>
                        <td align="center"><mh:listseq navigator="${navigator}" count="${status.count}" /></td>
                        <td><a href="javaScript:doEdit('<c:out value="${item.xch_cd}"/>')" /><mh:out value="${item.xch_nm}" td="true" /></a></td>
                        <td align="center"><mh:out value="${item.xch_amount}" format="currency" /></td>
                        <td align="center"><mh:out value="${item.xch_unit}" /></td>
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
        <td align="right"><mf:button bundle="button" key="add" onclick="doWrite()" icon="icon_add" /></td>
    </tr>
</table>
</mf:form>
<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/>