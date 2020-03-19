<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<SCRIPT LANGUAGE="JavaScript">
<!--
	function doSelect(prd_cd, prd_nm, prd_price) {
	    var frm = getObject('<c:out value="${param.frm_id}"/>',opener.document);
	    frm.prd_cd.value=prd_cd;
        frm.prd_nm.value=prd_nm;
        frm.psell_price.value=prd_price;
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
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this,'search');return false;">
<mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}" />
<mf:input type="hidden" name="miv_page" value="1" />
<mf:input type="hidden" name="cmd" value="search" />
<mf:input type='hidden' name='prd_cd' value=''/>
<mf:input type="hidden" name="frm_id" value="${frm_id}"/>
<mf:input type="hidden" name="elm_id" value="${elm_id}"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="no_border">
    <tr>
        <td>
        <div class="searchContainer">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <th><mf:label name="prd_cd" for="s_prd_cd" /></th>
                    <td><mf:input type="text" name="s_prd_cd" value="${LISTOP.ht.s_prd_cd}" /></td>
                    <th><mf:label name="prd_nm" for="s_prd_nm" /></th>
                    <td><mf:input type="text" name="s_prd_nm" value="${LISTOP.ht.s_prd_nm}" /></td>
                </tr>
            </table>
            <table border="0" cellspacing="0" cellpadding="2" class="searchBtn">
                <tr>
                    <td><mf:button onclick="frmSubmit('myform','list')" bundle="button" key="search" icon="icon_search" /></td>
                </tr>
            </table>
        </div>
        </td>
    </tr>
    <tr>
        <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list">
            <thead>
                <tr>
                    <th>#</th>
                    <th><mf:header name="prd_cd" sort="true"/></th>
                    <th><mf:header name="prd_nm" sort="true" /></th>
                    <th><mf:header name="org_price" sort="true" /></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${navigator.list}" varStatus="status">
                    <tr>
                        <td align="center"><mh:listseq navigator="${navigator}" count="${status.count}" /></td>
                        <td align="center"><mh:out value="${item.prd_cd}" td="true" /></td>
                        <td align="center"><a href="javascript:doSelect('<mh:out value="${item.prd_cd}"/>','<mh:out value="${item.prd_nm}"/>','<mh:out value="${item.org_price}"/>')"><mh:out value="${item.prd_nm}" td="true" /></a></td>
                        <td align="center"><mh:out value="${item.org_price}" format="currency" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        </td>
    </tr>
</table>
</mf:form>
<jsp:include page="/WEB-INF/layout/lib/navigator_noform.jsp" flush="true"/>