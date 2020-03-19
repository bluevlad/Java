<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript" >
function goList() {
    <c:url var="urlList" value="${control_action}">
        <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
        <c:param name="cmd" value="list"/>
    </c:url>
    document.location = '<mh:out value="${urlList}"/>';
}
</script>

<mf:form action="${control_action}" method="post" name="myform" id="myform">
<mf:input type="hidden" name="cmd" value=""/>
<mf:input type="hidden" name="ord_cd" value="${item.ord_cd}"/>
<div class="viewContainer">
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="view">    
    <col width="15%"/>
    <col width="35%"/>
    <col width="15%"/>
    <col width="35%"/>
    <tr>
        <th><mf:label name="ord_cd"/></th> 
        <td><mh:out value="${item.ord_cd}"/>
        </td>
        <th><mf:label name="ord_dt"/></th> 
        <td><mh:out value="${item.ord_dt}"/></td>
    </tr>
    <tr>
        <th><mf:label name="total_pay"/></th> 
        <td><mh:out value="${item.total_pay}" format="currency"/></td>
        <th><mf:label name="discount"/></th> 
        <td><mh:out value="${item.discount}" format="currency"/></td>
    </tr>
    <tr>
        <th><mf:label name="ord_sta"/></th> 
        <td><mh:out value="${item.ord_sta}" codeGroup="ORDER.ORD_TYPE"/></td>
        <th><mf:label name="cpn_use"/></th> 
        <td><mh:out value="${item.cpn_use}" codeGroup="ACTIVE_YN"/></td>
    </tr>
</table>
</div>
<br>
<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list">
    <col width="20"/>
    <col width="*"/>
    <col width="100"/>
    <col width="100"/>
    <col width="100"/>
    <col width="100"/>
    <col width="100"/>
    <tr>
        <th>#</th>
        <th><mfmt:message bundle="product" key="prd_nm" /></th>
        <th><mfmt:message bundle="product" key="prd_type" /></th>
        <th><mfmt:message bundle="product" key="psell_price" /></th>
        <th><mfmt:message bundle="order" key="qty" /></th>
        <th><mfmt:message bundle="order" key="cpn_use" /></th>
        <th><mfmt:message bundle="order" key="ord_det_sta" /></th>
        <th><mfmt:message bundle="order" key="snd_dt" /></th>
    </tr>
    <c:forEach var="item" items="${list}" varStatus="status">
    <tr>
        <td align="center"><mh:out value="${status.count}" /></td>
        <td align="center"><mh:out value="${item.prd_nm}" td="true" /></td>
        <td align="center"><mh:out value="${item.prd_type}" codeGroup="PRD.PRD_TYPE" /></td>
        <td align="center"><mh:out value="${item.psell_price}" format="currency" /></td>
        <td align="center"><mh:out value="${item.qty}" format="currency" /></td>
        <td align="center"><mh:out value="${item.cpn_use}" codeGroup="ACTIVE_YN" /></td>
        <td align="center"><mh:out value="${item.ord_det_sta}" codeGroup="ORDER.ORD_TYPE" /></td>
        <td align="center"><mh:out value="${item.snd_dt}" td="true" /></td>
    </tr>
    </c:forEach>
</table>
</mf:form>
<table border="0" cellpadding="2" cellspacing="0" width="100%" class="viewBtn"> 
    <tr>
        <td align="right">
            <mf:button bundle="button" key="list" onclick="javascript:goList()" icon="icon_list"/>
        </td>
    </tr>
</table>
