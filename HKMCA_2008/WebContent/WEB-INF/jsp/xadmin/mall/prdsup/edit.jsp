<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript">
    function doUpdate() {
        var frm = getObject("myform");
        if(frm) {
            frm.cmd.value = "insert";
            frm.submit();
        } else {
            alert ("form[" + frmName + "] is invalid");
        }
    }
    function goList() {
        <c:url var="urlList" value="${control_action}">
            <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
            <c:param name="cmd" value="list"/>
        </c:url>
        document.location = '<mh:out value="${urlList}"/>';
    }
</script>
<mf:form action="${control_action}"  method="post" name="myform" id="myform">
<mf:input type="hidden" name="cmd" value=""/>
<mf:input type="hidden" name="prd_cd" value="${item.prd_cd}"/>
<div class="viewContainer">
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">    
    <col width="15%"/>
    <col width="35%"/>
    <col width="15%"/>
    <col width="35%"/>
    <tr>
        <th><mf:label name="prd_cd"/></th> 
        <td><mh:out value="${item.prd_cd}"/></td>
        <th><mf:label name="prd_nm"/></th> 
        <td><mh:out value="${item.prd_nm}"/></td>
    </tr>
    <tr>
        <th><mf:label name="org_price"/></th> 
        <td><mh:out value="${item.org_price}" format="currency"/></td>
        <th><mf:label name="prd_type" /></th> 
        <td><mh:out value="${item.prd_type}" codeGroup="PRD.PRD_TYPE" /></td>
    </tr>
    <tr>
        <th><mf:header name="reg_dt" sort="true"/></th>
        <td><mf:input type="date" name="reg_dt" readonly="true" cssStyle="width:90px" value="${item.reg_dt}" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');" cssClass="dropdown"/></td>
        <th><mf:header name="reg_price" sort="true" /></th>
        <td><mf:input type="text" name="reg_price" cssStyle="width:100px" option="number" value="${item.prd_price}"/></td>
    </tr>
</table>
</div>
<table width="100%" border="0" cellspacing="0" cellpadding="2" class="viewBtn">
    <tr>
        <td align="right">
            <mf:button bundle="button" key="save" onclick="doUpdate()" icon="icon_save"/>
            <mf:button bundle="button" key="list" onclick="goList()" icon="icon_list"/>
        </td>
    </tr>
</table>
</mf:form>
<br>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="list">
    <tr>
        <th>#</th>
        <th><mf:header name="reg_dt" sort="true"/></th>
        <th><mf:header name="reg_price" sort="true" /></th>
    </tr>
    <c:forEach var="list" items="${list}" varStatus="status">
    <tr>
        <td align="center"><mh:out value="${status.count}" /></td>
        <td align="center"><mh:out value="${list.reg_dt}" td="true" /></td>
        <td align="center"><mh:out value="${list.reg_price}" td="true" /></td>
    </tr>
    </c:forEach>
</table>