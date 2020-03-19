<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<SCRIPT LANGUAGE="JavaScript">
<!--
    function doOrder(){
        var frm = getObject("myform");
        if(frm) {
            frm.action = "/order.user/order.do";
            frm.cmd.value = "order";
            frm.submit();
        }
    }
//-->
</SCRIPT>
<mf:form action="${control_action}" method="post" name="myform" id="myform">
<mf:input type="hidden" name="cmd" value="basket"/>
<mf:input type="hidden" name="psell_cd" value=""/>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
        <td>
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list">
                <col width="20"/>
                <col width="*"/>
                <col width="100"/>
                <col width="100"/>
                <col width="100"/>
                <col width="100"/>
                <col width="100"/>
                <thead>
                <tr>
                    <th>#</th>
                    <th><mfmt:message bundle="product" key="prd_nm" /></th>
                    <th><mfmt:message bundle="common" key="title.cnt" /></th>
                    <th><mfmt:message bundle="product" key="psell_price" /></th>
                    <th><mfmt:message bundle="product" key="prd_type" /></th>
                    <th><mfmt:message bundle="product" key="sell_nm" /></th>
                </tr>
                </thead>
                <tbody>
                <c:set var="tot_pay" value="0"/>
                <c:forEach var="item" items="${list}" varStatus="status">
                <tr>
                    <mf:input type="hidden" name="v_psell_cd" value="${item.psell_cd}"/>
                    <mf:input type="hidden" name="price_${item.psell_cd}" value="${item.psell_price}"/>
                    <mf:input type="hidden" name="cnt_${item.psell_cd}" cssStyle="width:30px" value="${item.cnt}" />
                    <td align="center"><mh:out value="${status.count}" /></td>
                    <td align="center"><mh:out value="${item.prd_nm}" td="true" /></td>
                    <td align="center"><mh:out value="${item.cnt}" /></td>
                    <td align="center"><mh:out value="${item.psell_price}" format="currency" /></td>
                    <td align="center"><mh:out value="${item.prd_type}" codeGroup="PRD.PRD_TYPE" /></td>
                    <td align="center"><mh:out value="${item.sell_nm}" td="true" /></td>
                </tr>
                <c:set var="tot_pay" value="${tot_pay+(item.cnt*item.psell_price)}"/>
                </c:forEach>
                </tbody>
            </table>
            <br>
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="view">
                <col width="15%"/>
                <col width="*"/>
                <col width="15%"/>
                <col width="20%"/>
                <col width="15%"/>
                <col width="20%"/>
                <tr>
                    <th><mfmt:message bundle="order" key="total_pay" /></th>
                    <td align="center"><mh:out value="${tot_pay}" format="currency" /></td>
                    <th><mfmt:message bundle="order" key="discount" /></th>
                    <td align="center"><mh:out value="${tot_pay}" format="currency" /></td>
                    <th><mfmt:message bundle="order" key="order_pay" /></th>
                    <td align="center"><mh:out value="${tot_pay}" format="currency" /></td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<c:if test="${!empty(list)}">
<table border="0" cellpadding="2" cellspacing="0" width="100%" class="viewBtn"> 
    <tr>
        <td align="right">
            <mf:button bundle="button" key="order.act" onclick="javascript:doOrder()"/>
        </td>
    </tr>
</table>
</c:if>
</mf:form>