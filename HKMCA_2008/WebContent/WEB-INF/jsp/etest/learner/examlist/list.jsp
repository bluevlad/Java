<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<SCRIPT LANGUAGE="JavaScript">
<!--
	function doView(exmid){
		var frm = getObject("myform");
		if(frm) {
			frm.cmd.value = "view";
			frm.exmid.value=exmid;
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
	        <jf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this,'list');return false; ">
            <jf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}" />
            <input type="hidden" name="miv_page" value="1" />
            <input type="hidden" name="cmd" value="list" />
            <input type='hidden' name='exmid' value=''>
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
                <tr>
                    <th><jf:label name="exmid" for="s_exmid" /></th>
                    <td><jf:input type="text" name="s_exmid" value="${LISTOP.ht.s_exmid}" /></td>
                    <th><jf:label name="exmtitle" for="s_exmtitle" /></th>
                    <td><jf:input type="text" name="s_exmtitle" value="${LISTOP.ht.s_exmtitle}" /></td>
                </tr>
                <tr>
                    <th><jf:label name="exm_sdat" for="s_exm_sdat" /></th>
                    <td><jf:input type="text" name="s_exm_sdat" value="${LISTOP.ht.s_exm_sdat}" /></td>
                    <th><jf:label name="exm_edat" for="s_exm_edat" /></th>
                    <td><jf:input type="text" name="s_exm_edat" value="${LISTOP.ht.s_exm_edat}" /></td>
                </tr>

            </table>
            <table border="0" cellspacing="0" cellpadding="2" class="searchBtn">
                <tr>
                    <td>
                        <jf:button onclick="frmSubmit('myform','list')" bundle="button" key="search" />
                        <jf:button onclick="frmReset('myform');" bundle="button" key="reset" />
                    </td>
                </tr>
            </table>
            </jf:form>
            </div>
        </td>
    </tr>
    <tr>
        <td>
	        <div class="listContainer">
	        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
	            <thead>
	                <tr>
	                    <th>#</th>
	                    <th><jf:header name="exmid" sort="true" /></th>
	                    <th><jf:header name="exmtitle" sort="true" /></th>
	                    <th><jf:header name="exm_sdat" sort="true" /></th>
	                    <th><jf:header name="exm_edat" sort="true" /></th>
	                    <th><jf:header name="rst_status" sort="true" /></th>
	                </tr>
	            </thead>
	            <tbody>
	                <c:forEach var="item" items="${navigator.list}" varStatus="status">
	                    <tr>
	                        <td class="center"><jh:listseq navigator="${navigator}" count="${status.count}" /></td>
	                        <td align="center"><a href="javaScript:doView(<c:out value='${item.exmid}'/>)" /><jh:out value="${item.exmid}" td="true" /></a></td>
	                        <td align="center"><a href="javaScript:doView(<c:out value='${item.exmid}'/>)" /><jh:out value="${item.exmtitle}" td="true" /></a></td>
	                        <td align="center"><jh:out value="${item.exm_sdat}" td="true" /></td>
	                        <td align="center"><jh:out value="${item.exm_edat}" td="true" /></td>
	                        <td align="center"><jh:out value="${item.rst_status}" td="true" codeGroup="ETEST.RST_STATUS"/></td>
	                    </tr>
	                </c:forEach>
	            </tbody>
	        </table>
	        </div>
        </td>
    </tr>
</table>
<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true" />