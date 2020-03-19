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
	
	function doView(usn){
		var frm = getObject("myform");
	    
		if(frm) {
			frm.cmd.value = "edit";
			frm.usn.value=usn;
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
<mf:form action="${control_action}" method="get" name="myform" id="myform" onSubmit="return frmSubmit(this,'list');return false;">
<mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}" />
<mf:input type="hidden" name="miv_page" value="1" />
<mf:input type="hidden" name="cmd" value="list" />
<mf:input type="hidden" name="usn" value="" />
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td>
            <div class="searchContainer">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="search">
                <col width="20%">
                <col width="30%">
                <col width="20%">
                <col width="30%">
                <tr>
                    <th><mf:label name="userid" for="s_userid" /></th>
                    <td><mf:input type="text" name="s_userid" cssStyle="width:98%" value="${LISTOP.ht.s_userid}" /></td>
                    <th><mf:label name="nm" for="s_nm" /></th>
                    <td><mf:input type="text" name="s_nm" cssStyle="width:98%" value="${LISTOP.ht.s_nm}" /></td>
                </tr>
            </table>
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="searchBtn">
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
                    <th><mf:header name="userid" sort="true"/></th>
                    <th><mf:header name="nm" sort="true" /></th>
                    <th><mf:header name="reg_dt" sort="true" /></th>
                    <th><mf:header name="active_yn" sort="true" /></th>
                </tr>
	            </thead>
	            <tbody>
                <c:forEach var="item" items="${navigator.list}" varStatus="status">
                <tr>
					<td align="center"><mh:listseq navigator="${navigator}" count="${status.count}" /></td>
					<td align="center"><a href="javaScript:doView('<c:out value="${item.usn}"/>')" /><mh:out value="${item.userid}" td="true" /></a></td>
					<td align="center"><a href="javaScript:doView('<c:out value="${item.usn}"/>')" /><mh:out value="${item.nm}" td="true" /></a></td>
					<td align="center"><mh:out value="${item.reg_dt}" format="fulldate" td="true" /></td>
					<td align="center"><mh:out value="${item.active_yn}" codeGroup="ACTIVE_YN" td="true" /></td>
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