<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript">
<!--
function goList()   {
    var frm = getObject("myform");
    frm.cmd.value = "view";
    frm.submit();
}
//-->
</script>

<mf:form action="${control_action}" method="post" name="myform" id="myform">
<mf:input type="hidden" name="cmd" value="" />
<mf:input type="hidden" name="usn" value="${usn}" />
<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
        <td>
            <div class="viewContainer">
            <table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">
                <col width="15%" />
                <col width="35%" />
                <col width="15%" />
                <col width="35%" />
                <c:forEach var="item" items="${list}">
                <tr>
                    <th><mfmt:message key="rpttitle" bundle="table.rpt_mst"/></th>
                    <td colspan="3"><mh:out value="${item.rpttitle}" /></td>
                </tr>
                <tr>
                    <th><mfmt:message key="rptdesc" bundle="table.rpt_mst"/></th>
                    <td colspan="3"><mh:out value="${item.rptdesc}" /></td>
                </tr>
                <tr>
                    <th><mfmt:message key="rpt_sdat" bundle="table.rpt_mst"/></th>
                    <td><mh:out value="${item.rpt_sdat}" /></td>
                    <th><mfmt:message key="rpt_edat" bundle="table.rpt_mst"/></th>
                    <td><mh:out value="${item.rpt_edat}" /></td>
                </tr>
                </c:forEach>
            </table>
            </div>
            <br>
			<div class="listContainer">
			<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
			    <col width="10%" />
			    <col width="10%" />
			    <col width="10%" />
			    <col width="10%" />
			    <col width="10%" />
			    <thead>
			    <tr>
			        <th><mfmt:message key="givtitle" bundle="table.rpt_mst"/></th>
			        <th><mfmt:message key="givfile" bundle="table.rpt_mst"/></th>
			        <th><mfmt:message key="givdat" bundle="table.rpt_mst"/></th>
			        <th><mfmt:message key="usrscore" bundle="table.rpt_mst"/></th>
			    </tr>
			    </thead>
			    <tbody>
			    <c:forEach var="list" items="${rpt}">
			    <tr>
			        <td class="center"><mh:out value="${list.givtitle}" td="true" /></td>
			        <td class="center">
			            <mh:getIcon value="${list.org_filename}"/>
			            <a href='<c:url value="/pds/report/${list.new_filename}?cmd=save">
			            <c:param name="file" value="/pds/report/${list.new_filename}"/>
			            <c:param name="pds_cd" value="RPT_GIV"/>
			            <c:param name="main_cd" value="${list.lec_cd}"/>
			            <c:param name="sub_cd" value="${list.rptcode}"/>
			            <c:param name="file_id" value="${list.usn}"/>
			            </c:url>' target="_blank" >
			            <mh:out value="${list.org_filename}"/></a><br>
			        </td>
			        <td class="center"><mh:out value="${list.givdat}" format="fulldatetime" td="true" /></td>
			        <td class="center"><mh:out value="${list.usrscore}" /></td>
			    </tr>
			    </c:forEach>
			    </tbody>
			</table>
			</div>
        </td>
    </tr>
</table>
<table border="0" cellpadding="2" cellspacing="0" width="100%" class="viewBtn">
    <tr>
        <td align="right"><mf:button bundle="button" key="list" onclick="goList()" icon="icon_list"/></td>
    </tr>
</table>
</mf:form>