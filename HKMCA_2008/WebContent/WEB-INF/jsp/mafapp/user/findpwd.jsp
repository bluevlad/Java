<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<script language="javascript">
<!--
window.resizeTo("350","250");

function doSubmit(){
    var frm = getObject("findId");
    if(frm) {
        if (validate(frm)) {
	        frm.cmd.value="resultid";
	        frm.submit();
        }
     } else {
        alert(" i can't found form [findAddr] ");
     }
}
//-->
</script>

<table width="100%" border="0" cellspacing="0" cellpadding="0" class="no_border">
    <tr>
        <td width="25">
            <img src="<mh:out value="${CPATH}"/>/maf_images/zipcode/bullet_popup.gif" width="15" height="13">&nbsp;
            <b><mfmt:message bundle="common.login" key="btn.findpwd" /></b>
        </td>
    </tr>
</table>
<!-- TITLE END -->

<!-- CONENTS START -->
<mf:form name="findId" id="findId" method="post" action="${control_action}">
<mf:input type="hidden" name="cmd" value=""/>
<TABLE width=100% border="0" cellspacing="0" cellpadding="0" class="no_border">
    <TR>
        <TD>
            <table width="100%" border="1" cellpadding="0" cellspacing="0" class="view">
                <col width="30%">
                <col width="30%">
                <col width="*">
                <tr>
                    <td align="right"><mfmt:message bundle="common.login" key="pin" /></td>
                    <td colspan="2">
                       <mf:input name="pin1" type="text" cssStyle="width:50px" required="true" maxlength="6" value=""/> -
                       <mf:input name="pin2" type="text" cssStyle="width:50px" required="true" maxlength="7" value=""/>
                    </td>
                </tr>
                <tr>
                    <td align="right"><mfmt:message bundle="common.login" key="nm" /></td>
                    <td><mf:input type="text" name="nm" cssStyle="width:98%" required="true" value="" onkeypress="if (13 == event.keyCode) return doSubmit();"/></td>
                    <td><mf:button bundle="button" key="search" onclick="doSubmit()" icon="icon_search"/></td>
                </tr>
            </table>
        </TD>
    </TR>
</TABLE>
</mf:form>
<!-- CONENTS END -->