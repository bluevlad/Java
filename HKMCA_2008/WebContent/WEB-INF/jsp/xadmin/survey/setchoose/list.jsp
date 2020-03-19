<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<SCRIPT LANGUAGE="JavaScript">
<!--
    window.resizeTo(880, 480);
    function doView(setid){
        var frm = getObject("myform");
        if(frm) {
            frm.cmd.value = "view";
            frm.setid.value=setid;
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
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="no_border">
    <tr>
        <td>
	        <div class="searchContainer">
	        <mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this,'list');return false; ">
	        <mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}" />
	        <mf:input type="hidden" name="miv_page" value="1" />
	        <mf:input type="hidden" name="cmd" value="list" />
	        <mf:input type='hidden' name='setid' value=''/>
	        <mf:input type="hidden" name="frm_id" value="${frm_id}"/>
	        <mf:input type="hidden" name="elm_id" value="${elm_id}"/>
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
            <col width="15%"/>
            <col width="85%"/>
                <tr>
                    <th><mf:label name="settitle" for="s_settitle" /></th>
                    <td><mf:input type="text" name="s_settitle" size="100" value="${LISTOP.ht.s_settitle}" /></td>
                </tr>
            </table>
            <table border="0" cellspacing="0" cellpadding="2" class="searchBtn">
                <tr>
                    <td><mf:button onclick="frmSubmit('myform','list')" bundle="button" key="search" /></td>
                </tr>
            </table>
	        </mf:form>
	        </div>
        </td>
    </tr>
    <tr>
        <td>
	        <div class="listContainer">
	        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
	            <col width="50"/>
	            <col width="*"/>
	            <col width="100"/>
	            <thead>
	            <tr>
	                <th>#</th>
	                <th><mf:header name="settitle" sort="true" /></th>
	                <th><mf:header name="active_yn" sort="true" /></th>
	            </tr>
	            </thead>
	            <tbody>
	            <c:forEach var="item" items="${navigator.list}" varStatus="status">
	            <tr>
	                <td class="center"><mh:listseq navigator="${navigator}" count="${status.count}" /></td>
	                <td><a href="javaScript:doView('<c:out value="${item.setid}"/>')" /><mh:out value="${item.settitle}" td="true" /></a></td>
	                <td align="center"><mh:out value="${item.active_yn}" td="true" codeGroup="ACTIVE_YN" /></td>
	            </tr>
	            </c:forEach>
	            </tbody>
	        </table>
	        </div>
        </td>
    </tr>
</table>
<jsp:include page="/WEB-INF/layout/lib/navigator_noform.jsp" flush="true" /></td>
