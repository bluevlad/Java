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

	function doEdit(sell_cd){
	    var frm = getObject("myform");
	    if(frm) {
	        frm.cmd.value = "edit";
            frm.sell_cd.value = sell_cd;
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

<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return doSearch(this,'list');return false; ">
<mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}"/>
<mf:input type="hidden" name="miv_page" value="1"/>
<mf:input type="hidden" name="cmd" value="list"/>
<mf:input type="hidden" name="sell_cd" value=""/>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
        <td>
            <div class="searchContainer">
            <table width="100%" border="0" cellspacing="0" cellpadding="2">
	            <col width="15%">
	            <col width="35%">
	            <col width="15%">
	            <col width="35%">
	            <tr>
	                <th><mf:label name="sell_nm"/></th>
	                <td colspan="3"><mf:input type="text" name="s_sell_nm" value="${LISTOP.ht.s_sell_nm}" cssStyle="width:250px"/></td>
	             </tr>
            </table>
            <table border="0" cellspacing="0" cellpadding="2" class="searchBtn">
                <tr>
                    <td><mf:button onclick="frmSubmit('myform','list')" bundle="button" key="search" icon="icon_search"/></td>
                </tr>
            </table>
            </div>
        </td>
    </tr>
    <tr>
        <td>
	        <div class="listContainer">
	        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
                <col width="5%"/>
                <col width="*"/>
                <col width="25%"/>
                <col width="25%"/>
		        <thead>
		        <tr>
		            <th>#</th>
                    <th><mf:header name="sell_nm" sort="true"/></th>
		            <th><mf:header name="sell_phone" sort="true"/></th>
		            <th><mf:header name="sell_fax" sort="true"/></th>
		        </tr>
		        </thead>
		        <tbody>
		        <c:forEach var="item" items="${navigator.list}" varStatus="status">
		        <tr >
		            <td align="center"><mh:listseq navigator="${navigator}" count="${status.count}"/></td>
                    <td align="center"><a href="javaScript:doEdit('<mh:out value="${item.sell_cd}"/>')"/><mh:out value="${item.sell_nm}" td="true"/></a></td>
		            <td align="center"><mh:out value="${item.sell_phone}" td="true" /></td>
		            <td align="center"><mh:out value="${item.sell_fax}" td="true" /></td>
		        </tr>
		        </c:forEach>
		        </tbody>
	        </table>
	        </div>
        </td>
    </tr>
</table>
</mf:form>
<table width="100%" border="0" cellspacing="0" cellpadding="2" class="viewBtn">
    <tr>
        <td align="right">
            <mf:button bundle="button" key="add" onclick="doWrite()" icon="icon_add"/>
        </td>
    </tr>
</table>
<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/>