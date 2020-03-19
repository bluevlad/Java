<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf"%>

<script language="javascript">
<!--
window.resizeTo("350","300");

function useId(){
    var frm = getObject("<mh:out value='${form}'/>",opener.document);
    if(frm) {
        frm.userid.value = document.findId.userid.value;
        self.close();
    } else {
        alert ("<mh:out value="${form}"/>을 찾을 수 없습니다.");
    }
}
//-->
</script>


<!-- TITLE START -->
<table width="100%"  border="0" cellspacing="0" cellpadding="0" class="no_border">
    <tr>
        <td>
            <img src="<mh:out value="${CPATH}"/>/maf_images/zipcode/bullet_popup.gif" width="15" height="13">&nbsp;
            <b><mfmt:message bundle="common" key="id" /></b>
        </td>
    </tr>
</table>
<!-- TITLE END -->

<!-- CONENTS START -->
<mf:form name="findId" id="findId" method="post" action="${control_action}">
<mf:input type="hidden" name="userid" value="${userid}"/>
<mf:input type="hidden" name="form" value="${form}"/>
<mf:input type="hidden" name="cmd" value=""/>
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" class="no_border">
    <TR>
        <TD align=center bgcolor="#FFFFFF">
            <c:choose>
            <c:when test="${item == null}">
            <mfmt:message bundle="common.login" key="msg.invalidFindUserID" />
            </c:when>
            <c:otherwise>
            <mfmt:message bundle="common.login" key="msg.findUserID" param="${item.userid}"/>
            </c:otherwise>
            </c:choose>
        </TD>
    </TR>
</TABLE>
</mf:form>