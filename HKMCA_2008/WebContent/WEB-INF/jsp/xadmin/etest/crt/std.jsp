<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<script language="javascript" src='<c:url value="/FusionCharts/JSClass/FusionCharts.js"/>'></script>
<script language="javascript">
<!--
function goList() {
	var frm = getObject("myform");
	if(frm) {
	    frm.cmd.value = "list";
	    frm.submit();
	}
 }

function goMakeLank(){
    var frm = getObject("myform");
    if(frm) {
        frm.cmd.value = "make_lank";
        frm.submit();
    }
}

function goMakeCrtVar(){
    var frm = getObject("myform");
    if(frm) {
        frm.cmd.value = "make_crt_var";
        frm.submit();
    }
}

function goMakeRst(){
    var frm = getObject("myform");
    if(frm) {
        frm.cmd.value = "make_rst";
        frm.submit();
    }
}
 
function showDetailScore(usn) {
    var frm = getObject("myform");
    if(frm) {
        frm.action = "/xadmin.etest/exmmanager.do";
        frm.cmd.value = "showDetailScore";
        frm.usn.value =  usn;
        frm.submit();
    }
}
//-->
</script>
<mf:form action="${control_action}" method="post" name="myform" id="myform" >
<mf:input type="hidden" name="cmd" value=""/>
<mf:input type="hidden" name="exmid" value="${exmid}"/>
<mf:input type="hidden" name="usn" value=""/>
<div class="listContainer">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="list">
    <col width="20" />
    <col width="*" />
    <col width="150" />
    <col width="150" />
    <col width="100" />
    <col width="120" />
    <thead>
        <tr>
            <th>#</th>
            <th><mf:header name="nm" sort="true"/></th>
            <th><mf:header name="rst_sdt" sort="true" /></th>
            <th><mf:header name="rst_edt" sort="true" /></th>
            <th><mf:header name="rst_status" sort="true" /></th>
            <th><mf:header name="rstscore100" sort="true" /></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="ri" items="${navigator.list}" varStatus="status">
            <tr>
                <td class="center"><mh:out value="${status.count}" /></td>
                <td class="center"><a href='javascript:showDetailScore(<c:out value="'${ri.usn}'"/>)'><mh:out value="${ri.nm}(${ri.userid})" td="true" /></a></td>
                <td class="center"><mh:out value="${ri.rst_sdt}" td="true" /></td>
                <td class="center"><mh:out value="${ri.rst_edt}" td="true" /></td>
                <td class="center"><mh:out value="${ri.rst_status}" td="true" codeGroup="ETEST.RST_STATUS" /></td>
                <td class="center"><mh:out value="${ri.rstscore100}" td="true" /></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<table border="0" cellpadding="2" cellspacing="0" class="viewBtn" width="100%">
    <tr>
        <td align="right">
            <mf:button bundle="button" key="list" onclick="goList();" icon="icon_list" />
            <mf:button bundle="button" key="etest.makeLank" onclick="goMakeLank();" /> 
            <mf:button bundle="button" key="etest.makeCrtVar" onclick="goMakeCrtVar();" /> 
            <mf:button bundle="button" key="etest.makeRst" onclick="goMakeRst();" /> 
        </td>
    </tr>
</table>
</div>
</mf:form>
<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/>