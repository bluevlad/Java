<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<script language="javascript">
<!--
window.resizeTo("350","250");

    function doSubmit(){
        var form = getObject("findId");
        if(form) {
            if(form.suserid.value.length == 0){
                alert('<mfmt:message bundle="common.scripts" key="iduse.input"/>');
            }else{
                form.cmd.value="idchk";
                form.submit();
            }
         } else {
             alert('<mfmt:message bundle="common.scripts" key="zipcode.form.notfound"/>');
         }
    }
//-->
</script>

<table width="100%"  border="0" cellspacing="0" cellpadding="0" class="no_border">
    <tr>
		<td>
			<img src="<jh:out value="${CPATH}"/>/maf_images/zipcode/bullet_popup.gif" width="15" height="13">&nbsp;
			<b><mfmt:message bundle="common" key="id" /></b>
		</td>
    </tr>
</table>

<!-- CONENTS START -->
<mf:form name="findId" id="findId" method="post" action="${control_action}">
<mf:input type="hidden" name="cmd" value=""/>
<mf:input type="hidden" name="form" value="${form}"/>
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" class="no_border">
    <TR>
        <TD>
	        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="view">
	            <tr>
	                <td width="50%" align="right"><mf:input name="suserid" type="text" cssStyle="width:100px" value="${userid}" onkeypress="if (13 == event.keyCode) return doSubmit();"/></td>
	                <td><mf:button bundle="button" key="search" onclick="doSubmit()" icon="icon_search"/></td>
	            </tr>
	        </table>
        </TD>
    </TR>
</TABLE>
</mf:form>
<!-- CONENTS END -->