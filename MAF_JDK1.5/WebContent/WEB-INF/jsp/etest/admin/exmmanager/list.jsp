
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
<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
        <td>
        <div class="searchContainer">
        <h1><mfmt:message bundle="common" key="searchtitle" /></h1>
        <mf:form action="${control_action}" method="post" name="myform" id="myform"
            onSubmit="return frmSubmit(this,'list');return false; ">
            <mf:input type="hidden" name="LISTOP" value="${LISTOP.serializeUrl}" />
            <input type="image" id="dummy" width="0" height="0" border="0" class="hidden" />
            <input type="hidden" name="miv_page" value="1" />
            <input type="hidden" name="cmd" value="list" />
            <input type='hidden' name='exmid' value=''>
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
                <tr>
                    <th><mf:label name="exmid" for="s_exmid" /></th>
                    <td><mf:input type="text" name="s_exmid" value="${LISTOP.ht.s_exmid}" /></td>

                    <th><mf:label name="exmtitle" for="s_exmtitle" /></th>
                    <td><mf:input type="text" name="s_exmtitle" value="${LISTOP.ht.s_exmtitle}" /></td>
                </tr>

            </table>
            <table border="0" cellspacing="0" cellpadding="2" class="searchBtn">
                <tr>
                    <td><mf:button onclick="frmSubmit('myform','list')" bundle="button" key="search" /> <mf:button
                        onclick="frmReset('myform');" bundle="button" key="reset" /></td>
                </tr>
            </table>
        </mf:form></div>
        </td>
    </tr>
    <tr>
        <td>
        <div class="listContainer">
        
        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true"
            rowAlternateClass="alternateRow">
            <thead>
                <tr>
                    <th>#</th>
                    <th nowrap><mf:header name="exmid" sort="true" /></th>
                    <th nowrap><mf:header name="exmtitle" sort="true" /></th>
                    <th nowrap><mf:header name="exm_sdat_t1" sort="true" /></th>
                    <th nowrap><mf:header name="exm_edat_t1" sort="true" /></th>
                    <th nowrap><mf:header name="update_dt" sort="true" /></th>
                    <th nowrap><mf:header name="active_yn"  /></th>
                    
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${navigator.list}" varStatus="status">
                    <tr>
                        <td align="center"><mh:listseq navigator="${navigator}" count="${status.count}" /></td>
                        <td align="center"><a href="javaScript:doView('<c:out value="${item.exmid}"/>')" /><mh:out value="${item.exmid}" td="true" /></a></td>
                        <td align="center"><a href="javaScript:doView('<c:out value="${item.exmid}"/>')" /><mh:out value="${item.exmtitle}" td="true" /></a></td>
                        <td align="center"><mh:out value="${item.exm_sdat_t1}" format="fulldate" td="true" /></td>
                        <td align="center"><mh:out value="${item.exm_edat_t1}" format="fulldate"  td="true" /></td>
                        <td align="center"><mh:out value="${item.update_dt}" format="fulldate"  td="true" /></td>
                        <td align="center"><mh:out value="${item.active_yn}" codeGroup="ACTIVE_YN" td="true" /></td>
                        
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        </div>
        </td>
    </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
	<tr>
	    <td align="right"><mf:button bundle="button" key="add" onclick="doWrite()" icon="icon_add" /></td>
    </tr>
	<tr>
    	<td align="center"><jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/></td>
    </tr>
</table>