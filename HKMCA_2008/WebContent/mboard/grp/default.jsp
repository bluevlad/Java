<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<script language="javascript">
<!--
    function frmSubmit() {
        var frm = getObject("myform");  
        if (validate(frm)) {
            frm.cmd.value = "update";
            frm.submit();
        }
    }

    function frmAdd() {
        var frm = getObject("myform");  
        frm.cmd.value = "insert";
        frm.submit();
    }
//-->       
</script>

<div class="viewContainer">
<mf:form action="${control_action}" method="post" name="myform" id="myform">
<mf:input type="hidden" name="cmd" value=""/>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="list">
    <col width="10%"/>
    <col width="20%"/>
    <col width="*"/>
    <col width="10%"/>
    <thead>
    <tr>
        <th><mfmt:message bundle="common" key="no"/></th>    
        <th><mfmt:message bundle="common" key="code" /></th>
        <th><mfmt:message bundle="common" key="code_nm" /></th>
        <th><mfmt:message bundle="button" key="delete" /></th>      
    </tr>
    </thead>
    <tbody>
    <c:forEach var="list" items="${list}" varStatus="status">
    <tr>
        <td align="center"><mf:input type="text" name="seq_${list.grp}" cssStyle="width:95%" value="${list.seq}"/></td>
        <td align="center"><mf:input type="text" name="i_grp" cssStyle="width:95%" value="${list.grp}" readonly="true"/></td>
        <td align="center"><mf:input type="text" name="desc_${list.grp}" cssStyle="width:95%" value="${list.grp_desc}"/></td>
        <td align="center"><mf:input type="radio" name="del_${list.grp}" value="Y"/></td> 
    </tr>
    </c:forEach>
    </tbody>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="viewBtn">
    <tr>
        <td align="right">
            <mf:button bundle="button" key="save" onclick="frmSubmit()" icon="icon_save"/>
        </td>
    </tr>
</table>
<br>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="list">
    <col width="10%"/>
    <col width="20%"/>
    <col width="*"/>
    <col width="10%"/>
    <tr>
        <th><mfmt:message bundle="common" key="no"/></th>    
        <th><mfmt:message bundle="common" key="code" /></th>
        <th><mfmt:message bundle="common" key="code_nm" /></th>
        <th>#</th>  
    </tr>
    <tr>
        <td align="center"><mf:input name="seq" cssStyle="width:95%" value=""/></td>
        <td align="center"><mf:input name="grp" cssStyle="width:95%" value=""/></td>
        <td align="center"><mf:input name="grp_desc" cssStyle="width:95%" value=""/></td>
        <td align="center"><mf:button bundle="button" key="add" onclick="frmAdd()" icon="icon_save"/></td>
    </tr>
</table>
</mf:form>
</div>