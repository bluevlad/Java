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

<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this,'list');return false; ">
<mf:input type="hidden" name="LISTOP" value="${LISTOP.serializeUrl}" />
<input type="hidden" name="miv_page" value="1" />
<input type="hidden" name="cmd" value="list" />
<input type='hidden' name='exmid' value=''>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td>
        <div class="searchContainer">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="search">
                <col width="15%">
                <col width="85%">
                <tr>
                    <th><mf:label name="exmtitle" for="s_exmtitle" /></th>
                    <td><mf:input type="text" name="s_exmtitle" cssStyle="width:98%" value="${LISTOP.ht.s_exmtitle}" /></td>
                </tr>
            </table>
            <table border="0" cellspacing="0" cellpadding="0" class="searchBtn">
                <tr>
                    <td>
                        <mf:button onclick="frmSubmit('myform','list')" bundle="button" key="search" />
                        <mf:button onclick="frmReset('myform');" bundle="button" key="reset" />
                    </td>
                </tr>
            </table>
            </div>
        </td>
    </tr>
    <tr>
        <td>
	        <div class="listContainer">
	        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
                <col width="5%">
                <col width="10%">
                <col width="*">
                <col width="15%">
                <col width="15%">
                <col width="15%">
                <thead>
                <tr>
                    <th>#</th>
                    <th><mf:header name="exmid" sort="true" /></th>
                    <th><mf:header name="exmtitle" sort="true" /></th>
                    <th><mf:header name="exm_sdat" sort="true" /></th>
                    <th><mf:header name="exm_edat" sort="true" /></th>
                    <th><mfmt:message bundle="etest.common" key="result.rstcnt"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${navigator.list}" varStatus="status">
                    <tr>
                        <td align="center"><mh:listseq navigator="${navigator}" count="${status.count}" /></td>
                        <td align="center"><a href="javaScript:doView('<c:out value="${item.exmid}"/>')" /><mh:out value="${item.exmid}" td="true" /></a></td>
                        <td align="center"><a href="javaScript:doView('<c:out value="${item.exmid}"/>')" /><mh:out value="${item.exmtitle}" td="true" /></a></td>
                        <td align="center"><mh:out value="${item.exm_sdat}" td="true" /></td>
                        <td align="center"><mh:out value="${item.exm_edat}" td="true" /></td>
                        <td align="center"><mh:out value="${item.cnt}" td="true" /></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="viewBtn">
			    <tr>
			        <td align="right"><mf:button bundle="button" key="add" onclick="doWrite()" icon="icon_add" /></td>
			    </tr>
			</table>
            </div>
        </td>
    </tr>
</table>
</mf:form>
<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/>