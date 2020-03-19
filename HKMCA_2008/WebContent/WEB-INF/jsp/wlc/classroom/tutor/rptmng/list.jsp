<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<script language="javascript">
function doWrite(){
    var frm = getObject("myform");
    if(frm) {
        frm.cmd.value = "add";
        frm.submit();
    }
}

function doView(rptcode){
    var frm = getObject("myform");
    if(frm) {
        frm.rptcode.value = rptcode;
        frm.cmd.value = "edit";
        frm.submit();
    }
}

function doStd(rptcode){
    var frm = getObject("myform");
    if(frm) {
        frm.rptcode.value = rptcode;
        frm.cmd.value = "std";
        frm.submit();
    }
}
</script>
<mf:form action="${control_action}" method="post" name="myform" id="myform" >
<mf:input type="hidden" name="cmd" value=""/>
<mf:input type="hidden" name="rptcode" value=""/>
<div class="listContainer">
<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
    <col width="5%" />
    <col width="*" />
    <col width="15%" />
    <col width="15%" />
    <col width="10%" />
    <col width="10%" />
    <thead>
    <tr>
        <th>#</th>
        <th><mf:header name="rpttitle" /></th>
        <th><mf:header name="rpt_sdat" /></th>
        <th><mf:header name="rpt_edat" /></th>
        <th><mf:header name="rptrate" /></th>
        <th><mfmt:message bundle="common" key="submit"/>/<mfmt:message bundle="common" key="reqnumber"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="list" items="${list}" varStatus="status">
    <tr>
        <td class="center"><mh:out value="${status.count}" /></td>
        <td class="center"><a href='javascript:doView(<c:out value="${list.rptcode}"/>)'><mh:out value="${list.rpttitle}" td="true" /></a></td>
        <td class="center"><mh:out value="${list.rpt_sdat}" td="true" /></td>
        <td class="center"><mh:out value="${list.rpt_edat}" td="true" /></td>
        <td class="center"><mh:out value="${list.rptrate}" td="true" />%</td>
        <td class="center"><a href='javascript:doStd(<c:out value="${list.rptcode}"/>)'>[ <mh:out value="${list.giv}"/>/<mh:out value="${list.cnt}"/> ]</a></td>
    </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<table width="100%" border="0" cellpadding="2" cellspacing="0" class="viewBtn">
    <tr>
        <td align="right"><mf:button bundle="button" key="add" onclick="doWrite()" icon="icon_add" /></td>
    </tr>
</table>
</div>
</mf:form>