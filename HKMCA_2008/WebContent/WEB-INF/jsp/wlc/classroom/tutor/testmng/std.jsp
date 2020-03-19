<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<script language="javascript" src='<c:url value="/FusionCharts/JSClass/FusionCharts.js"/>'></script>
<script language="javascript">
function goList() {
	var frm = getObject("myform");
	if(frm) {
	    frm.cmd.value = "list";
	    frm.submit();
	}
 }

function doEdit(){
    var frm = getObject("myform");
    if(frm) {
        frm.cmd.value = "edit";
        frm.submit();
    }
}
 
function showDetailScore(usn, lec_num) {
    var frm = getObject("myform");
    if(frm) {
        frm.cmd.value = "showDetailScore";
        frm.usn.value = usn;
        frm.submit();
    }
}
</script>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
        <td>
			<div class="viewContainer">
			<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
			    <col width="15%" />
			    <col width="35%" />
			    <col width="15%" />
			    <col width="35%" />
			    <tr>
			        <th><mf:header name="exmtitle" /></th>
			        <td colspan="3"><mh:out value="${item.exmtitle}" /></td>
			    </tr>
			    <tr>
			        <th><mf:header name="exm_sdat" /></th>
			        <td><mh:out value="${item.exm_sdat}" /></td>
			        <th><mf:header name="exm_edat" /></th>
			        <td><mh:out value="${item.exm_edat}" /></td>
			    </tr>
			    <tr>
			        <th><mfmt:message key="upt_dt" bundle="common"/></th>
			        <td><mh:out value="${item.upt_dt}" format="fulldate"/></td>
			        <th><mf:header name="setid" /></th>
			        <td><mh:out value="${item.setid}" /></td>
			    </tr>
			</table>
			<br>
			<br>
        </div>
        </td>
    </tr>
    <tr>
        <td>
        <div class="searchContainer">
            <mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this,'std');return false; ">
            <mf:input type="hidden" name="LISTOP" value="${LISTOP.serializeUrl}" />
            <mf:input type="hidden" name="miv_page" value="1" />
            <mf:input type="hidden" name="cmd" value="std" />
            <mf:input type="hidden" name="usn" value=""/>
            <mf:input type="hidden" name="exmid" value="${item.exmid}"/>
            <mf:input type="hidden" name="LISTOP" value="${LISTOP.serializeUrl}" />
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
                <col width="15%">
                <col width="35%">
                <col width="15%">
                <col width="35%">
                <tr>
                    <th><mfmt:message key="userid" bundle="table.maf_user"/></th>
                    <td><mf:input type="text" name="s_userid" value="${LISTOP.ht.s_userid}" /></td>
                    <th><mfmt:message key="nm" bundle="table.maf_user"/></th>
                    <td><mf:input type="text" name="s_nm" value="${LISTOP.ht.s_nm}" /></td>
                </tr>
            </table>
            <table border="0" cellspacing="0" cellpadding="2" class="searchBtn">
                <tr>
                    <td>
                        <mf:button onclick="frmSubmit('myform','std')" bundle="button" key="search" />
                        <mf:button onclick="goList();" bundle="button" key="list" />
                    </td>
                </tr>
            </table>
            </mf:form>
            </div>
        </td>
    </tr>
    <tr>
        <td>
			<div class="listContainer">
			<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
			    <col width="5%" />
			    <col width="15%" />
			    <col width="25%" />
			    <col width="25%" />
			    <col width="15%" />
			    <col width="15%" />
			    <thead>
			    <tr>
			        <th nowrap>#</th>
			        <th nowrap><mf:header name="nm" sort="true"/></th>
			        <th nowrap><mf:header name="rst_sdt" sort="true"/></th>
			        <th nowrap><mf:header name="rst_edt" sort="true"/></th>
			        <th nowrap><mf:header name="rst_status" sort="true"/></th>
			        <th nowrap><mf:header name="rstscore100" sort="true" /></th>
			    </tr>
			    </thead>
			    <tbody>
			    <c:forEach var="ri" items="${navigator.list}" varStatus="status">
			    <tr>
			        <td class="center"><mh:out value="${status.count}" /></td>
			        <td class="center"><a href='javascript:showDetailScore(<c:out value="'${ri.usn}'"/>)'><mh:out value="${ri.nm}(${ri.userid})" td="true" /></a></td>
			        <td class="center"><mh:out value="${ri.rst_sdt}" format="fulldatetime" td="true" /></td>
			        <td class="center"><mh:out value="${ri.rst_edt}" format="fulldatetime" td="true" /></td>
			        <td class="center"><mh:out value="${ri.rst_status}" td="true" codeGroup="ETEST.RST_STATUS" /></td>
			        <td class="center"><mh:out value="${ri.rstscore100}" td="true" /></td>
			    </tr>
			    </c:forEach>
			    </tbody>
			</table>
			</div>
        </td>
    </tr>
</table>
<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/>