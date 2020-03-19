<%@ page contentType="text/html; charset=euc-kr"%>
<script language="javascript" src="${CPATH}/js/lib.validate.js"></script>
<script language="javascript" src="${CPATH}/js/lib.calendar.js"></script>
<script language="javascript" >
<!--
    function goList() {
        <c:url var="urlList" value="${control_action}">
            <c:param name="${mrt:mvcListOp()}" value="${LISTOP.serializeUrl}"/>
            <c:param name="${mrt:mvcCmd()}" value="list"/>
        </c:url>
        document.location = "${urlList}";
    }        

    function frmSubmit(frm) {
        if (validate(frm)) {
            frm.cmd.value="${(param.cmd=='edit')?'update':'insert'}";
            return true;
        } else {
            return false;
        }
    }

// subject name set
function setSjtname(frm) {
    if(frm.sjtcode.selectedIndex > 0)
        frm.sjtname.value = frm.sjtcode[frm.sjtcode.selectedIndex].text;
    else
        frm.sjtname.value = '';
}

//-->
</script>
<table border="0" cellpadding="2" cellspacing="1" class="view" width="100%"> 
<form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this)">
    <input type="hidden" name="${mrt:mvcCmd()}" value="">
    <input type="hidden" name="htmcode" value="${item.htmcode}">
        <tr>
            <th colspan=4 width=100% height="28" class="view" align="left"><mfmt:message bundle="table.Wracourse" key="wra_course.msg1"/><br><mfmt:message bundle="table.Wracourse" key="wra_course.msg2"/></th>
        </tr>
        <tr>
<c:choose>
	<c:when test="${param.cmd == 'add'}">
            <th width=20% height="28" class="view"><mfmt:message bundle="table.Wracourse" key="wra_course.sjt_cd"/><span id='lbl_required'></span></th>
            <td colspan=3 width=80% bgcolor=#ffffff class="view">
                <select name="sjt_cd" hname="<mfmt:message bundle="table.Wracourse" key="wra_course.sjt_cd"/>" required onChange="setSjtname(this.form);">
                 <option value=""><mfmt:message bundle="table.Wracourse" key="wra_course.select"/></option>
                <c:forEach var="sbjlist" items="${slist}" varStatus="status">
                  ${mhtml:HtmlOption(sbjlist.prtcode, sbjlist.name, item.prtcode)}
                </c:forEach>
                </select>
            </td>
	</c:when>
	<c:when test="${param.cmd == 'edit'}">
            <th width=20% height="28" class="view" nowrap><mfmt:message bundle="table.Wracourse" key="wra_course.sjt_cd"/><span id='lbl_required'>*</span></th>
            <td colspan=3 width=80% bgcolor=#ffffff class="view">${mhtml:null2nbsp(item.name)}(${mhtml:null2nbsp(item.prtcode)})</td>
            <input type="hidden" name="sjt_cd" value="${item.prtcode}">
	</c:when>
</c:choose>
        </tr>
        <tr>
            <th width=20% height="28" class="view"><mfmt:message bundle="table.Wracourse" key="wra_course.crs_id"/></th>
            <td colspan=3 bgcolor=#ffffff class="view"><input type="text" name="crs_id" size=100 value="${item.crs_id}" hname="<mfmt:message bundle="table.Wracourse" key="wra_course.crs_id"/>" required >
            </td>
        </tr>
        <tr>
            <th width=20% height="28" class="view"><mfmt:message bundle="table.Wracourse" key="wra_course.crs_name"/></th>
            <td colspan=3 bgcolor=#ffffff class="view"><input type="text" name="crs_name" size=100 value="${item.crs_name}" hname="<mfmt:message bundle="table.Wracourse" key="wra_course.crs_name"/>" required></td>
        </tr>
        <tr>
            <th colspan=4 width=100% height="28" class="view" align="left"><mfmt:message bundle="table.Wracourse" key="wra_course.msg3"/></th>
        </tr>
        <tr>
            <th width=20% height="28" class="view"><mfmt:message bundle="table.Wracourse" key="wra_course.folder"/></th>
            <td colspan=3 bgcolor=#ffffff class="view"><input type="text" name="folder" size=100 value="${item.folder}" hname="<mfmt:message bundle="table.Wracourse" key="wra_course.folder"/>"></td>
        </tr>
    <tr>
        <td colspan="4" align="center">
        <input type="submit" value="<mfmt:message bundle="button" key="button.save"/>">
        <input type="reset" value="<mfmt:message bundle="button" key="button.reset"/>">
        <a href="javascript:goList()"><mfmt:message bundle="button" key="goList"/></a> 
        </td>
    </tr>
</form>
</table>
