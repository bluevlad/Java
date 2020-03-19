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

function doView(exmid){
    var frm = getObject("myform");
    if(frm) {
        frm.exmid.value = exmid;
        frm.cmd.value = "view";
        frm.submit();
    }
}

function doStd(exmid){
    var frm = getObject("myform");
    if(frm) {
        frm.exmid.value = exmid;
        frm.cmd.value = "std";
        frm.submit();
    }
}
</script>
<mf:form action="${control_action}" method="post" name="myform" id="myform" >
<mf:input type="hidden" name="cmd" value=""/>
<mf:input type="hidden" name="exmid" value=""/>
<div class="listContainer">
<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
    <col width="5%" />
    <col width="*" />
    <col width="15%" />
    <col width="15%" />
    <thead>
    <tr>
        <th nowrap>#</th>
        <th nowrap><mf:header name="exmtitle" /></th>
        <th nowrap><mf:header name="exm_sdat" /></th>
        <th nowrap><mf:header name="exm_edat" /></th>
        <th nowrap><mfmt:message bundle="etest.common" key="result.rstcnt"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="list" items="${list}" varStatus="status">
    <tr>
        <td class="center"><mh:out value="${status.count}" /></td>
        <td class="center"><a href='javascript:doView(<c:out value="${list.exmid}"/>)'><mh:out value="${list.exmtitle}" td="true" /></a></td>
        <td class="center"><mh:out value="${list.exm_sdat}" td="true" /></td>
        <td class="center"><mh:out value="${list.exm_edat}" td="true" /></td>
        <td class="center"><a href='javascript:doStd(<c:out value="${list.exmid}"/>)'>[ <mh:out value="${list.cnt}" td="true" /> ]</a></td>
    </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<table width="100%" border="0" cellpadding="2" cellspacing="0" class="viewBtn">
    <tr>
        <td align="right"><mf:button bundle="button" key="etest.create" onclick="doWrite()" icon="icon_add" /></td>
    </tr>
</table>
</div>
</mf:form>