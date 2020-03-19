<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<SCRIPT LANGUAGE="JavaScript">
<!--
    function doSearch(frm) {
		if(frm) {
   			frm.cmd.value = "default";
			frm.miv_page.value = 1;
			return true;
		}     
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
                    <th><mf:header name="email" sort="true" /></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${navigator.list}" varStatus="status">
                    <tr>
                        <td align="center"><mh:listseq navigator="${navigator}" count="${status.count}" /></td>
                        <td align="center"><mh:out value="${item.userid}" td="true" /></td>
                        <td align="center"><mh:out value="${item.nm}" td="true" /></td>
                        <td align="center"><mh:out value="${item.email}" td="true" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        </div>
        </td>
    </tr>
</table>
</mf:form>
<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/>