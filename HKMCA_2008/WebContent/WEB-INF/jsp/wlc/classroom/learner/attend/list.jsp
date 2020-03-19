<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<SCRIPT LANGUAGE="JavaScript">
<!--
    function frmUpdate() {
        var frm = getObject("myform");
        frm.cmd.value='update';
        frm.submit();
    }
//-->
</SCRIPT>

<mf:form action="${control_action}" method="get" name="myform" id="myform" onSubmit="return frmSubmit(this,'list');return false;">
<mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}" />
<mf:input type="hidden" name="miv_page" value="1" />
<mf:input type="hidden" name="cmd" value=""/>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
        <td>
        <div class="listContainer">
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list">
                <thead>
                <tr>
                    <th><mfmt:message key="att_dat" bundle="table.wlc_att_log"/></th>
                    <th><mfmt:message key="acs_s_tm" bundle="table.wlc_att_log"/></th>
                    <th><mfmt:message key="acs_e_tm" bundle="table.wlc_att_log"/></th>
                </tr>
                </thead>
                <c:forEach var="list" items="${list}">
                <tr>
                    <td class="center"><mh:out value="${list.att_dat}" /></td>
                    <td class="center"><mh:out value="${list.acs_s_tm}" format="fulldatetime" /></td>
                    <td class="center"><mh:out value="${list.acs_e_tm}" format="fulldatetime" /></td>
                </tr>
                </c:forEach>
            </table>
			<table width="100%" border="0" cellpadding="2" cellspacing="2" class="viewBtn">   
			    <tr>
			        <td align="right"><mf:button bundle="button" key="attend" onclick="frmUpdate()" icon="icon_save"/></td>
			    </tr>
			</table>
            </div>
        </td>
    </tr>
</table>
</mf:form>