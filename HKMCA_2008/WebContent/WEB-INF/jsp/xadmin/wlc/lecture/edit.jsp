<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript" >
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
    document.location = '<mh:out value="${urlList}"/>';
}
function makeLecCD() {
    var frm = getObject("myform");
    var leccd = frm.os_sjt.value + "-" + frm.lec_type(0).value + "-" + frm.lec_year.value + "-" +  frm.lec_num.value;
	frm.lec_cd.value=leccd;
}
</script>

<div class="viewContainer">
<mf:form action="${control_action}"  method="post" name="myform" id="myform">
<mf:input type="hidden" name="cmd" value=""/>
<mf:input type="hidden" name="prg_type" value="${item.prg_type}"/>
<mf:input type="hidden" name="lec_weck" value="${item.lec_weck}"/>
<mf:input type="hidden" name="lec_datm" value="${item.lec_datm}"/>
<mf:input type="hidden" name="lec_wetm" value="${item.lec_wetm}"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="view">    
    <col width="15%"/>
    <col width="35%"/>
    <col width="15%"/>
    <col width="35%"/>
    <c:if test="${param.cmd == 'add'}">
    <tr>
        <th><mf:label name="sjt_cd"/></th> 
        <td colspan="3">
            <jsp:include page="/WEB-INF/jsp/common/sel_sjt.jsp" flush="true">
                <jsp:param name="os_crs" value='<%=request.getParameter("os_crs")%>'/>
                <jsp:param name="os_sjt" value='<%=request.getParameter("os_sjt")%>'/>
                <jsp:param name="destination" value='<%=request.getAttribute("destination")%>'/>
            </jsp:include>
        </td>
    </tr>
    </c:if>
    <c:if test="${param.cmd == 'edit'}">
        <mf:input type="hidden" name="sjt_cd" value="${item.sjt_cd}"/>
    </c:if>
    <tr>
        <th><mf:label name="lec_year"/></th> 
        <td><mf:input type="text" name="lec_year" value="${item.lec_year}" required="true" option="number" cssStyle="width:50px"/></td>
        <th><mf:label name="lec_num"/></th> 
        <td><mf:input type="text" name="lec_num" value="${item.lec_num}" required="true" option="number" cssStyle="width:30px"/></td>
    </tr>
    <tr>
        <th><mf:label name="lec_type"/></th> 
        <td><mf:select name="lec_type" codeGroup="LEC.LEC_TYPE" curValue="${item.lec_type}"/></td>
        <th><mf:label name="lec_cd"/></th> 
        <td>
            <c:if test="${param.cmd == 'add'}">
            <mf:input type="text" name="lec_cd" value="${item.lec_cd}" readonly="true" cssStyle="width:200px" onclick="makeLecCD()"/>
            </c:if>
            <c:if test="${param.cmd == 'edit'}">
            <mf:input type="hidden" name="lec_cd" value="${item.lec_cd}"/><mh:out value="${item.lec_cd}"/>
            </c:if>
        </td>
    </tr>
    <tr>
        <th><mf:label name="lec_nm"/></th> 
        <td colspan="3"><mf:input type="text" name="lec_nm" value="${item.lec_nm}" required="true" cssStyle="width:98%"/></td>
    </tr>
    <tr>
        <th><mf:label name="lec_stat"/></th> 
        <td><mf:select name="lec_stat" codeGroup="LEC.LEC_STAT" curValue="${item.lec_stat}"/></td>
        <th><mf:label name="otype"/></th> 
        <td><mf:select name="otype" codeGroup="LEC.OTYPE" curValue="${item.otype}"/></td>
    </tr>
    <tr>
        <th><mf:label name="lec_target"/></th> 
        <td colspan="3">
            <c:forEach var="ta" items="${target}">
                <mf:input type="checkbox" name="lec_target" value="${ta.code_no}" curValue="${ta.chk}"/>
                <mh:out value="${ta.code_name}"/>&nbsp;
            </c:forEach>
        </td>
    </tr>
    <tr>
        <th><mf:label name="lec_price"/></th> 
        <td><mf:input type="text" name="lec_price" value="${item.lec_price}" option="number" cssStyle="width:100px" />&nbsp;<mfmt:message bundle="common" key="money"/></td>
        <th><mf:label name="lec_capacity"/></th> 
        <td><mf:input type="text" name="lec_capacity" value="${item.lec_capacity}" option="number" cssStyle="width:50px"/>&nbsp;<mfmt:message bundle="common" key="user_cnt"/></td>
    </tr>
    <tr>
        <th><mf:label name="ltype"/></th> 
        <td><mf:select name="ltype" codeGroup="LEC.LTYPE" curValue="${item.ltype}"/></td>
        <th><mf:label name="lec_type"/></th> 
        <td><mf:select name="lec_type" codeGroup="LEC.LEC_TYPE" curValue="${item.lec_type}"/></td>
    </tr>

    <tr>
        <th><mf:label name="learn_day_cnt"/></th> 
        <td><mf:input type="text" name="learn_day_cnt" value="${item.learn_day_cnt}" option="number" cssStyle="width:50px"/>&nbsp;<mfmt:message bundle="common" key="time.day"/></td>
        <th><mf:label name="relearn_day_cnt"/></th> 
        <td><mf:input name="relearn_day_cnt" value="${item.relearn_day_cnt}" option="number" cssStyle="width:50px"/>&nbsp;<mfmt:message bundle="common" key="time.day"/></td>
    </tr>
    <tr>
        <th><mf:label name="learn_time"/></th> 
        <td><mf:input type="text" name="learn_time" value="${item.learn_time}" option="number" cssStyle="width:50px"/>&nbsp;<mfmt:message bundle="common" key="time.hour.per"/></td>
        <th><mf:label name="limit_study_time"/></th> 
        <td><mf:input type="text" name="limit_study_time" value="${item.limit_study_time}" option="number" cssStyle="width:50px"/>&nbsp;<mfmt:message bundle="common" key="time.min"/></td>
     </tr>
     <tr>
        <th><mf:label name="place"/></th> 
        <td><mf:input type="text" name="place" value="${item.place}" cssStyle="width:95%"/></td>           
        <th><mf:label name="finished_score"/></th> 
        <td><mf:input type="text" name="finished_score" value="${item.finished_score}" option="number" cssStyle="width:50px"/></td>
    </tr>
    <tr>
        <th><mf:label name="is_approval"/></th> 
        <td><mf:select  name="is_approval" curValue="${item.is_approval}" codeGroup="LEC.IS_APPROVAL"/></td>
        <th><mf:label name="adminid"/></th> 
        <td><mf:input type="text" name="adminid" value="${item.adminid}" cssStyle="width:95%"/></td>
    </tr>
</table>
<br>
<table width="100%" border="0" cellpadding="2" cellspacing="0" class="view">
	<col width="15%"/>
	<col width="35%"/>
    <col width="15%"/>
    <col width="35%"/>
    <thead>
    <tr>
        <th colspan="4"><mfmt:message bundle="table.bas_lec" key="sch_type"/></th>
    </tr>
    </thead>
<c:choose>
    <c:when test="${!empty(schedule_list)}">
	<c:set value="2" var="cols"/>
	<c:set value="1" var="cnt"/>
    <tr>
    <c:forEach items="${schedule_list}" var="scheduleitem" varStatus="status">
        <th nowrap><mfmt:message bundle="table.bas_lec" key="sch_type_${scheduleitem.sch_type}"/></th> 
        <td align="center">
            <mf:input type="text" name="sch_sdt_${scheduleitem.sch_type}" size="12" maxlength="10" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');" value="${scheduleitem.sch_sdt}" cssClass="dropdown"/> -
            <mf:input type="text" name="sch_edt_${scheduleitem.sch_type}" size="12" maxlength="10" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');" value="${scheduleitem.sch_edt}" cssClass="dropdown"/> 
        </td>
    <c:if test="${cnt%cols == 0}">
    </tr>
    <tr>
    </c:if>
    <c:set value="${cnt+1}" var="cnt"/>
    </c:forEach>
    </tr>
    </c:when>
    <c:otherwise>
    <tr>
        <th><mfmt:message bundle="table.bas_lec" key="sch_type_a"/></th> 
        <td align="center">
            <mf:input type="text" name="sch_sdt_a" size="12" maxlength="10" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');" value="" cssClass="dropdown"/> -
            <mf:input type="text" name="sch_edt_a" size="12" maxlength="10" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');" value="" cssClass="dropdown"/> 
        </td>
        <th><mfmt:message bundle="table.bas_lec" key="sch_type_b"/></th> 
        <td align="center">
            <mf:input type="text" name="sch_sdt_b" size="12" maxlength="10" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');" value="" cssClass="dropdown"/> -
            <mf:input type="text" name="sch_edt_b" size="12" maxlength="10" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');" value="" cssClass="dropdown"/> 
        </td>
    </tr>
    <tr>
        <th><mfmt:message bundle="table.bas_lec" key="sch_type_c"/></th> 
        <td align="center">
            <mf:input type="text" name="sch_sdt_c" size="12" maxlength="10" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');" value="" cssClass="dropdown"/> -
            <mf:input type="text" name="sch_edt_c" size="12" maxlength="10" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');" value="" cssClass="dropdown"/> 
        </td>
        <th><mfmt:message bundle="table.bas_lec" key="sch_type_d"/></th> 
        <td align="center">
            <mf:input type="text" name="sch_sdt_d" size="12" maxlength="10" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');" value="" cssClass="dropdown"/> -
            <mf:input type="text" name="sch_edt_d" size="12" maxlength="10" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');" value="" cssClass="dropdown"/> 
        </td>
    </tr>
    <tr>
        <th><mfmt:message bundle="table.bas_lec" key="sch_type_e"/></th> 
        <td align="center">
            <mf:input type="text" name="sch_sdt_e" size="12" maxlength="10" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');" value="" cssClass="dropdown"/> -
            <mf:input type="text" name="sch_edt_e" size="12" maxlength="10" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');" value="" cssClass="dropdown"/> 
        </td>
        <th><mfmt:message bundle="table.bas_lec" key="sch_type_f"/></th> 
        <td align="center">
            <mf:input type="text" name="sch_sdt_f" size="12" maxlength="10" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');" value="" cssClass="dropdown"/> -
            <mf:input type="text" name="sch_edt_f" size="12" maxlength="10" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');" value="" cssClass="dropdown"/> 
        </td>
    </tr>
    </c:otherwise>
</c:choose>
</table>
<table border="0" cellpadding="2" cellspacing="0" width="100%" class="viewBtn"> 
    <tr>
        <td align="right">
            <mf:button bundle="button" key="save" onclick="javascript:doUpdate()" icon="icon_save"/>
            <mf:button bundle="button" key="delete" onclick="doDel()" icon="icon_delete"/>
            <mf:button bundle="button" key="list" onclick="javascript:goList()" icon="icon_list"/>
        </td>
    </tr>
</table>
</mf:form>
</div>