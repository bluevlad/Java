<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<script language="javascript">
function goList() {
	var frm = getObject("myform");
	if(frm) {
	    frm.cmd.value = "list";
	    frm.submit();
	}
 }

function frmSubmit(){
    var frm = getObject("myform");
    if(frm) {
        frm.cmd.value = "std";
        frm.submit();
    }
}

function doScore(){
    var frm = getObject("myform");
    if(frm) {
        frm.cmd.value = "markScore";
        frm.submit();
    }
}
</script>
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this,'std');return false; ">
<mf:input type="hidden" name="LISTOP" value="${LISTOP.serializeUrl}" />
<mf:input type="hidden" name="miv_page" value="1" />
<mf:input type="hidden" name="cmd" value="std" />
<mf:input type="hidden" name="rptcode" value="${LISTOP.ht.rptcode}"/>
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
			        <th><mf:header name="rpttitle" /></th>
			        <td colspan="3"><mh:out value="${item.rpttitle}" /></td>
			    </tr>
                <tr>
                    <th><mf:header name="rptdesc" /></th>
                    <td colspan="3"><mh:out value="${item.rptdesc}" /></td>
                </tr>
			    <tr>
			        <th><mf:header name="rpt_sdat" /></th>
			        <td><mh:out value="${item.rpt_sdat}" /></td>
			        <th><mf:header name="rpt_edat" /></th>
			        <td><mh:out value="${item.rpt_edat}" /></td>
			    </tr>
			</table>
            </div>
        </td>
    </tr>
    <tr>
        <td>
        <div class="searchContainer">
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
            </div>
        </td>
    </tr>
    <tr>
        <td>
			<div class="listContainer">
			<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
			    <col width="5%" />
                <col width="5%" />
			    <col width="10%" />
			    <col width="10%" />
			    <col width="10%" />
			    <col width="10%" />
                <col width="10%" />
			    <thead>
			    <tr>
			        <th><mf:input type="checkbox" name="checkboxAll" value="allcode" onclick="allChekboxToggle(this,'myform','vusn')"/></th>
                    <th>#</th>
			        <th><mf:header name="userid" sort="true"/></th>
                    <th><mf:header name="nm" sort="true"/></th>
                    <th><mf:header name="givfile" sort="true"/></th>
			        <th><mf:header name="givdat" sort="true"/></th>
                    <th><mf:header name="usrscore" sort="true"/></th>
			    </tr>
			    </thead>
			    <tbody>
			    <c:forEach var="list" items="${navigator.list}" varStatus="status">
			    <tr>
                    <td class="center"><mf:input type="checkbox" name="vusn" value="${list.usn}"/></td>
			        <td class="center"><mh:listseq navigator="${navigator}" count="${status.count}" /></td>
                    <td class="center"><mh:out value="${list.userid}" td="true" /></td>
			        <td class="center"><mh:out value="${list.nm}" td="true" /></td>
                    <td class="center">
                        <mh:out value="${list.givfile}" td="true" />
                        <mh:getIcon value="${list.orgfile}"/>
                        <a href='<c:url value="/pds/report/${list.givfile}?cmd=save">
                        <c:param name="file" value="/pds/report/${list.givfile}"/>
                        <c:param name="pds_cd" value="${fi.pds_cd}"/>
                        <c:param name="main_cd" value="${fi.main_cd}"/>
                        <c:param name="sub_cd" value="${fi.sub_cd}"/>
                        <c:param name="file_id" value="${fi.file_id}"/>
                        </c:url>' target="_blank" >
                        <mh:out value="${fi.orgfile}"/></a><br>
                    </td>
			        <td class="center"><mh:out value="${list.givdat}" format="fulldatetime" td="true" /></td>
                    <td class="center"><mf:input name="usrscore_${list.usn}" value="${list.usrscore}" size="4" /></td>
			    </tr>
			    </c:forEach>
			    </tbody>
			</table>
			</div>
        </td>
    </tr>
</table>
<table border="0" cellpadding="2" cellspacing="0" class="viewBtn" width="100%">
    <tr>
        <td align="right"><mf:button bundle="button" key="button.report.marking" onclick="doScore()" /></td>
    </tr>
</table>
</mf:form>
<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/>