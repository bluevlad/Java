<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<SCRIPT LANGUAGE="JavaScript">
<!--
	function doView(std_usn) {
	    var urlname = CPATH + "/wlc.tutor/attend.do?cmd=view&std_usn="+std_usn;
	    var oWin = window.open(urlname,
	        "Attend",
	        "top=100px,left=100px,status=yes,resizable=no,menubar=no,scrollbars=yes,width=100,height=100");
	    windows_focus(oWin);
	}

    function doSearch() {
        var frm = getObject("myform");
        if(frm) {
            frm.cmd.value = "list";
            frm.miv_page.value = 1;
            frm.submit();
        }     
    }

    function frmUpdate() {
        var frm = getObject("myform");
        frm.cmd.value='update';
        frm.submit();
    }
//-->
</SCRIPT>

<mf:form action="${control_action}" method="get" name="myform" id="myform" onSubmit="return frmSubmit(this,'list');return false;">
<mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}" />
<mf:input type="hidden" name="miv_page" value="1" />
<mf:input type="hidden" name="cmd" value=""/>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
        <td>
        <div class="searchContainer">
            <table width="100%" border="0" cellspacing="0" cellpadding="2">
                <tr>
                    <th><mf:label name="userid" for="s_userid" /></th>
                    <td><mf:input type="text" name="s_userid" value="${LISTOP.ht.s_userid}" /></td>
                    <th><mf:label name="nm" for="s_nm" /></th>
                    <td><mf:input type="text" name="s_nm" value="${LISTOP.ht.s_nm}" /></td>
                </tr>
            </table>
            <table border="0" cellspacing="0" cellpadding="2" class="searchBtn">
                <tr>
                    <td><mf:button onclick="frmSubmit('myform','default')" bundle="button" key="search" icon="icon_search" /></td>
                </tr>
            </table>
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
                    <th><mf:header name="userid" sort="true"/></th>
                    <th><mf:header name="nm" sort="true" /></th>
                    <th><mf:header name="att_cnt" sort="true" /></th>
                    <th><mf:header name="att_score" sort="true" /></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${navigator.list}" varStatus="status">
                    <mf:input type="hidden" name="usn" value="${item.usn}" />
                    <tr>
                        <td align="center"><mh:listseq navigator="${navigator}" count="${status.count}" /></td>
                        <td align="center"><a href="javaScript:doView('<c:out value="${item.usn}"/>')" /><mh:out value="${item.userid}" td="true" /></a></td>
                        <td align="center"><a href="javaScript:doView('<c:out value="${item.usn}"/>')" /><mh:out value="${item.nm}" td="true" /></a></td>
                        <td align="center"><mh:out value="${item.att_cnt}" /></td>
                        <td align="center"><mf:input name="att_score" value="${item.att_score}" cssStyle="width:30px" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
		<table width="100%" border="0" cellpadding="2" cellspacing="2" class="viewBtn">   
		    <tr>
		        <td align="right"><mf:button bundle="button" key="save" onclick="frmUpdate()" icon="icon_save"/></td>
		    </tr>
		</table>
        </div>
        </td>
    </tr>
</table>
</mf:form>
<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/>