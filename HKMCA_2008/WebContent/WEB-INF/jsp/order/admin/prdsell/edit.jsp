<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript">
    function doUpdate() {
        var frm = getObject("myform");
        if(frm) {
            if (validate(frm)) {
                <c:choose>
                    <c:when test="${param.cmd == 'edit'}">
                        frm.cmd.value = "update";
                    </c:when>
                    <c:when test="${param.cmd == 'add'}">
                        frm.cmd.value = "insert";
                    </c:when>
                    <c:otherwise>
                        frm.cmd.value = "insert";
                    </c:otherwise>
                </c:choose>
                frm.submit();
            }
        } else {
            alert ("form[" + frmName + "] is invalid");
        }
    }
    function doDel(){
        var frm = getObject("myform");
        if(frm) {
            frm.cmd.value = "delete";
            frm.submit();
        }
    }
    function goList() {
        <c:url var="urlList" value="${control_action}">
            <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
            <c:param name="cmd" value="list"/>
        </c:url>
        document.location = '<jh:out value="${urlList}"/>';
    }
</script>
<mf:form action="${control_action}"  method="post" name="myform" id="myform">
<mf:input type="hidden" name="cmd" value=""/>
<mf:input type="hidden" name="psell_cd" value="${item.psell_cd}"/>
<div class="viewContainer">
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">    
    <col width="15%"/>
    <col width="35%"/>
    <col width="15%"/>
    <col width="35%"/>
    <tr>
        <th><mf:label name="prd_cd"/></th> 
        <td>
            <mf:input type="text" name="prd_cd" readonly="true" cssStyle="width:50px" value="${item.prd_cd}"/>
            <mf:input type="text" name="prd_nm" readonly="true" cssStyle="width:170px" value="${item.prd_nm}" onclick="doPrdSearch('myform','prd_cd')"/>
        </td>
        <th><mf:label name="psell_price"/></th> 
        <td><mf:input type="text" name="psell_price" cssStyle="width:150px" option="number" value="${item.psell_price}"/></td>
    </tr>
    <tr>
        <th><mf:label name="sell_cd"/></th> 
        <td>
            <mf:input type="text" name="sell_cd" readonly="true" cssStyle="width:50px" value="${item.sell_cd}"/>
            <mf:input type="text" name="sell_nm" readonly="true" cssStyle="width:170px" value="${item.sell_nm}"/>
        </td>
        <th><mf:label name="active_yn" /></th> 
        <td><mf:select name="active_yn" codeGroup="ACTIVE_YN" curValue="${item.active_yn}" /></td>
    </tr>
    <tr>
        <th><mf:label name="psell_desc"/></th> 
        <td colspan="3"><textarea style="width:95%" rows=3 name="psell_desc"><jh:out value="${item.psell_desc}"/></textarea></td>
    </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="2" class="viewBtn">
    <tr>
        <td align="right">
            <mf:button bundle="button" key="save" onclick="doUpdate()" icon="icon_save"/>
            <mf:button bundle="button" key="list" onclick="goList()" icon="icon_list"/>
        </td>
    </tr>
</table>
</div>
</mf:form>