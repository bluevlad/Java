<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript"   src="<mh:out value="${CPATH}"/>/js/lib.validate.js"></script>
<script language="javascript" src="<mh:out value="${CPATH}"/>/js/lib.calendar.js"></script>
<script language="javascript"   >
<!--
function goList()   {
    <c:url var="urlList" value="${control_action}">
        <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
        <c:param name="cmd" value="list"/>
    </c:url>
    document.location = "<mh:out value="${urlList}"/>";
}

function frmSubmit(frm) {
    if(validate(frm)) {
<c:choose>
    <c:when test="${param.cmd == 'add'}">
                frm.cmd.value="insert";
    </c:when>
    <c:when test="${param.cmd == 'edit'}">
                frm.cmd.value="update";
    </c:when>
</c:choose>
        frm.submit();
    }   else {
        return;
    }
}

function doWrite(){
    var frm = getObject("myform");
    if(validate(frm))   {
        
            frm.cmd.value = "updateScore";
            frm.submit();
        
    } else {
        return;
    }
}

function doView(viw){
    var frm = getObject("myform");
    if(frm) {
        frm.cmd.value = viw;
        frm.submit();
    }
}
//-->
</script>
<script language="javascript">
<!--
function containsCharsOnly(input,chars) {
    for (var inx = 0; inx < input.value.length; inx++) {
       if (chars.indexOf(input.value.charAt(inx)) == -1)
           return false;
    }
    return true;
}

/**
 * 입력값에 숫자만 있는지 체크
 */
function isNumber(input) {
    var chars = "0123456789";
    return containsCharsOnly(input,chars);
}

/**
 * 입력값을 모두 지운다
 */
function setEmpty() {
    var objEv = event.srcElement;
    objEv.value="";
}

function checkNumber(input) {
    if(!isNumber(input)) {
        //alert("<mfmt:message bundle="common.scripts" key="script.alert.only.number"/>");
        maf.alert('common.scripts','script.alert.only.number');
        setEmpty();
    }
}

function checkNumber2(input) {
    if((parseInt(input.value)<0 || parseInt(input.value)>100)){
        //alert("<mfmt:message bundle="common.scripts" key="script.alert.score.limit.fail"/>");
        maf.alert('common.scripts','script.alert.score.limit.fail');
        setEmpty();                
    }
}

function feedbac_how(form){
    if(form.feedback[0].checked==true){
        title.style.display ='';
        desc.style.display ='';
        file.style.display ='';
    }else{
        title.style.display ='none';
        desc.style.display ='none';
        file.style.display ='none';
    }
}
-->
</script>

<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">    
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this);return false;" enctype="multipart/form-data" >
<mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}" />
<input type="image" id="dummy" width="0" height="0" border="0" class="hidden"/>
<input type="hidden" name="cmd" value="">
<mf:input type="hidden" name="leccode" value="${item.leccode}" />
<mf:input type="hidden" name="userid" value="${userid}" />
<mf:input type="hidden" name="reqnumb" value="${reqnumb}" />
    <tr height="25">
        <th width=15% align="center"><mfmt:message bundle="table.wla_rat_mst" key="item"/></th>
        <th width=17% align="center"><mfmt:message bundle="common" key="table.score"/> (<mfmt:message bundle="common" key="table.score.title"/>)</th>
        <th width=18% align="center"><mfmt:message bundle="table.wla_rat_mst" key="condition"/>(%)</th>
        <th width=15% align="center"><mfmt:message bundle="table.wla_rat_mst" key="item"/></th>
        <th width=17% align="center"><mfmt:message bundle="common" key="table.score"/> (<mfmt:message bundle="common" key="table.score.title"/>)</th>
        <th width=18% align="center"><mfmt:message bundle="table.wla_rat_mst" key="condition"/>(Score)</th>
    </tr>
    <tr height="25">
        <th align="center"><mfmt:message bundle="table.wla_rat_mst" key="jindo_time"/></th>
        <td bgcolor="#FFFFFF" align="right"><a href="javascript:doView('jindo_view');">
        <mh:out value="${item.jindo_time_score}" td="true" /></a> 
        </td>
        <td bgcolor="#FFFFFF" align="right"><mh:out value="${item.jindo_time}" td="true" /> </td>
        <th align="center"><mfmt:message bundle="table.wla_rat_mst" key="ratexam"/></th>
        <td bgcolor="#FFFFFF" align="right"><a href="javascript:doView('exm_view');"><mh:out value="${item.ratexam_score}" td="true" /></a> </td>
        <td bgcolor="#FFFFFF" align="right"><mh:out value="${item.ratexam}" td="true" /> </td>
    </tr>
    <tr height="25">
        <th align="center"><mfmt:message bundle="table.wla_rat_mst" key="jindo_page"/></th>
        <td bgcolor="#FFFFFF" align="right"><a href="javascript:doView('jindo_view');">
        <mh:out value="${item.jindo_page_score}" td="true" /></a>
        </td>
        <td bgcolor="#FFFFFF" align="right"><mh:out value="${item.jindo_page}" td="true" /></td>
        <th align="center"><mfmt:message bundle="table.wla_rat_mst" key="ratoffline"/></th>
        <td bgcolor="#FFFFFF" align="right"><a href="javascript:doView('off_view');"><mh:out value="${item.ratoffline_score}" td="true" /></a></td>
        <td bgcolor="#FFFFFF" align="right"><mh:out value="${item.ratoffline}" td="true" /></td>
    </tr>
</table>
<br/>
<table width=100% border=0 cellpadding="0" cellspacing=1 class="view">
    <tr height="25">
        <th width=15% align="center"><mfmt:message bundle="table.wce_fnl_grd" key="tot_score"/></th>
        <td width=35% bgcolor="#FFFFFF"><mh:out value="${item.sum_score}" td="true" /></td>
        <th width=15% align="center"><mfmt:message bundle="table.wce_fnl_grd" key="score10"/></th>
        <td width=35% bgcolor="#FFFFFF">
        <mf:input size="4" maxlength="4" name="addscore" value="${item.score10}" />
        </td>
    </tr>
    <tr height="25">
        <th align="center"><mfmt:message bundle="table.wce_fnl_grd" key="final_score"/></th>
        <td bgcolor="#FFFFFF"><mh:out value="${item.tot_score}" td="true" /></td>
        <th align="center"><mfmt:message bundle="table.wcc_lec_req" key="is_grad"/></th>
        <td bgcolor="#FFFFFF">
           <c:if test="${item.tot_cnt != '0'}">
           <mh:out value="${item.is_grad}" td="true" />
           </c:if>
           <c:if test="${item.tot_cnt == '0'}">
           <mfmt:message bundle="common" key="table.notscoreing"/>
           </c:if>
        </td>
    </tr>
</table>
<br/>
<table border="0" cellpadding="2" cellspacing="1" width="100%">
    <tr>
        <td colspan="4" align="center">
        <mf:button bundle="button" key="button.final.score.marking" onclick="doWrite()" icon="icon_add"/>
        <mf:button bundle="button" key="goList" onclick="goList()" icon="icon_list"/>
        </td>
    </tr>
</table>
</mf:form>    

