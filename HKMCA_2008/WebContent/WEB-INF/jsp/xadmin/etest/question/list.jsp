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
    function doSearch(frm) {
		if(frm) {
   			frm.cmd.value = "list";
			frm.miv_page.value = 1;
			return true;
		}     
    }
    function doWrite(){
        var frm = getObject("myform");
        frm.cmd.value = "insert";
        frm.submit();
    }
//-->
</SCRIPT>

<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this,'list');return false; ">
<mf:input type="hidden" name="LISTOP" value="${LISTOP.serializeUrl}" />
<mf:input type="hidden" name="miv_page" value="1" />
<mf:input type="hidden" name="cmd" value="list" />
<mf:input type="hidden" name="setid" value="${LISTOP.ht.setid}" />
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="no_border">
    <tr>
        <td>
            <div class="searchContainer">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <col width="15%">
                <col width="85%">
                <tr>
                    <th><mf:label name="quetitle" /></th>
                    <td><mf:input name="s_quetitle" type="text" cssStyle="width:98%" value="${LISTOP.ht.s_quetitle}" /></td>
                </tr>
            </table>
            <table border="0" cellspacing="0" cellpadding="0" class="searchBtn">
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
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="list">
            <thead>
                <tr>
                    <th><mf:input type="checkbox" name="checkboxAll" value="allcode" onclick="allChekboxToggle(this,'myform','v_queids');"/></th>
                    <th><mf:header name="quetitle" sort="true"/></th>
                    <th><mf:header name="quetype" sort="true"/></th>
                    <th><mf:header name="quelevel" sort="true"/></th>
                    <th><mf:header name="quescore" sort="true"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${navigator.list}" varStatus="status">
                    <tr>
                        <td class='center'><mf:input type="checkbox" name="v_queids" value="${item.queid}"/></td>
                        <td align="left" ><mh:out value="${item.quetitle}" td="true" bytes="50"/></td>
                        <td class='center'><mh:out value="${item.quetype}" td="true" codeGroup="ETEST.QTYPE" /></td>
                        <td align="center"><mh:out value="${item.quelevel}" td="true" codeGroup="ETEST.QLEVEL" /></td>
                        <td align="center"><mh:out value="${item.quescore}" td="true" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        </div>
        </td>
    </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="viewBtn">
    <tr>
        <td align="right"><mf:button bundle="button" key="add" onclick="doWrite()" icon="icon_add" /></td>
    </tr>
</table>
</mf:form>
<jsp:include page="/WEB-INF/layout/lib/navigator_noform.jsp" flush="true" />