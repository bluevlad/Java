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
<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
        <td>
        <div class="searchContainer">
        <h1><mfmt:message bundle="common" key="searchtitle" /></h1>
	        <mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this,'list');return false; ">
            <fm:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}" />
            <input type="image" id="dummy" width="0" height="0" border="0" class="hidden" />
            <input type="hidden" name="miv_page" value="1" />
            <input type="hidden" name="cmd" value="list" />
            <input type='hidden' name='setid' value=''>
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
				<col width="15%"/>
				<col width="85%"/>
                <tr>
                    <th><mf:label name="settitle" for="s_settitle" /></th>
                    <td><mf:input type="text" name="s_settitle" size="80" value="${LISTOP.ht.s_settitle}" /></td>
                </tr>
            </table>
            <table border="0" cellspacing="0" cellpadding="2" class="searchBtn">
                <tr>
                    <td><mf:button onclick="frmSubmit('myform','list')" bundle="button" key="search" /></td>
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
            <col width="50"/>
            <col width="*"/>
            <col width="100"/>
            <col width="100"/>
            <col width="100"/>
            <col width="100"/>
            <thead>
                <tr>
                    <th>#</th>
                    <th><mf:header name="settitle" sort="true" /></th>
                    <th><mf:header name="active_yn" sort="true" /></th>
                    <th><mf:header name="update_dt" sort="true" /></th>
                    <th><mf:header name="update_usn" sort="true" /></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${navigator.list}" varStatus="status">
                    <tr>
                        <td class="center"><mh:listseq navigator="${navigator}" count="${status.count}" /></td>
                        <td><a href="javaScript:doView(
                        '<c:out value="${item.setid}"/>')" /> <mh:out
                            value="${item.settitle}" td="true" /></a></td>
                        <td align="center"><mh:out value="${item.active_yn}" td="true" codeGroup="ACTIVE_YN" /></td>
                        <td align="center"><mh:out value="${item.update_dt}" format="fulldate" td="true" /></td>
                        <td align="center"><mh:out value="${item.update_usn}" td="true" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        </div>
        </td>
    </tr>
    <tr>
        <td align="right"><mf:button bundle="button" key="add" onclick="doWrite()" icon="icon_add" /></td>
    </tr>
    <tr>
        <td align="center"><jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true" /></td>
    </tr>
</table>
