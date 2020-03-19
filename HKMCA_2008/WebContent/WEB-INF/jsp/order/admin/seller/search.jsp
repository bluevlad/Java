<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<SCRIPT LANGUAGE="JavaScript">
<!--
	function doSelect(sell_cd) {
	    var frm = getObject('<c:out value="${param.frm_id}"/>',opener.document);
	    frm.<c:out value="${elm_id}"/>.value=sell_cd;
	    self.close();
	}

    function doSearch(frm) {
        if(frm) {
            frm.cmd.value = "search";
            frm.miv_page.value = 1;
            return true;
        }
    }
//-->
</SCRIPT>

<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return doSearch(this,'search');return false; ">
<mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}"/>
<mf:input type="hidden" name="miv_page" value="1"/>
<mf:input type="hidden" name="cmd" value="search"/>
<mf:input type="hidden" name="sell_cd" value=""/>
<mf:input type="hidden" name="frm_id" value="${frm_id}"/>
<mf:input type="hidden" name="elm_id" value="${elm_id}"/>
<table width="100%" border="0" cellspacing="0" cellpadding="2" class="no_border">
    <tr>
        <td>
            <div class="searchContainer">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
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
	        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="list">
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
                    <td align="center"><a href="javaScript:doSelect('<mh:out value="${item.sell_cd}"/>')"/><mh:out value="${item.sell_nm}" td="true"/></a></td>
		            <td align="center"><mh:out value="${item.sell_phone}" td="true" /></td>
		            <td align="center"><mh:out value="${item.sell_fax}" td="true" /></td>
		        </tr>
		        </c:forEach>
		        </tbody>
	        </table>
        </td>
    </tr>
</table>
</mf:form>
<jsp:include page="/WEB-INF/layout/lib/navigator_noform.jsp" flush="true"/>