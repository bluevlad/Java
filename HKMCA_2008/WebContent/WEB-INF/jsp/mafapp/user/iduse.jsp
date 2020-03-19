<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<script language="javascript">
<!--
window.resizeTo("350","300");

function doSubmit(){
    var form = getObject("findId");
    if(form) {
        if(form.userid.value.length == 0){
            alert('<mfmt:message bundle="common.scripts" key="iduse.input"/>');
        }else{
            form.cmd.value="idchk";
            form.submit();
        }
     } else {
         alert('<mfmt:message bundle="common.scripts" key="zipcode.form.notfound"/>');
     }
}

function useId(){
    var frm = getObject("<mh:out value='${form}'/>",opener.document);
    if(frm) {
        frm.userid.value = document.findId.userid.value;
        self.close();
    } else {
        alert('<mfmt:message bundle="common.scripts" key="zipcode.form.notfound"/>');
    }
}
//-->
</script>

<table width="100%"  border="0" cellspacing="0" cellpadding="0" class="no_border">
    <tr>
        <td>
            <img src="<mh:out value="${CPATH}"/>/maf_images/zipcode/bullet_popup.gif" width="15" height="13">&nbsp;
            <b><mfmt:message bundle="common" key="id" /></b>
        </td>
    </tr>
</table>

<!-- CONENTS START -->
<mf:form name="findId" id="findId" method="post" action="${control_action}">
<mf:input type="hidden" name="userid" value="${userid}"/>
<mf:input type="hidden" name="form" value="${form}"/>
<mf:input type="hidden" name="cmd" value=""/>
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" class="no_border">
    <TR>
        <TD>
            <!-- 주소찾기 시작 -->         
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="view">
				<tr>
					<td width="50%" align="right"><mf:input name="suserid" type="text" cssStyle="width:100px" value="${userid}" onkeypress="if (13 == event.keyCode) return doSubmit();"/></td>
					<td><mf:button bundle="button" key="search" onclick="doSubmit()" icon="icon_search"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><b><mh:out value='${userid}'/></b></td>
				</tr>
                <tr>
                    <td colspan="2" align="center"><mf:button bundle="button" key="id.use" onclick="useId()"/></td>
                </tr>
			</table>
            <!-- 완료 끝-->
        </TD>
    </TR>
</TABLE>
</mf:form>
<!-- CONENTS END -->