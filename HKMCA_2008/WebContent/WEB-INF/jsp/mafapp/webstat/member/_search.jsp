<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<SCRIPT LANGUAGE="JavaScript">
<!--
function doSearch() {
    var frm = getObject("myform");
    if(frm)   {
    	frm.cmd.value = frm.src_type.value;
		frm.submit();
    }
}
//-->
</SCRIPT>
            <mf:form action="${control_action}" method="post" name="myform" id="myform">
            <div class="searchContainer">
            <input type="hidden" name="cmd" value=""/>
            <table width="100%" border="0" cellspacing="0" cellpadding="2">
                <tr>
                    <td><mf:select name="src_type" codeGroup="WS.SRC_TYPE" curValue="${src_type}" onchange="doSearch()"/></td>
                </tr>
            </table>
            </div>
            </mf:form>