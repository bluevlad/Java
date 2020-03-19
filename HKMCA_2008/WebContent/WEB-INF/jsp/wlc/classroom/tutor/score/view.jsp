<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript">
<!--
function goList()   {
    <c:url var="urlList" value="${control_action}">
        <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
        <c:param name="cmd" value="list"/>
    </c:url>
    document.location = "<mh:out value="${urlList}"/>";
}

function doWrite(){
    var frm = getObject("myform");
    if(validate(frm))   {
        frm.cmd.value = "updateScores";
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

<div class="viewContainer">
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this);return false;" enctype="multipart/form-data" >
<mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}" />
<mf:input type="hidden" name="cmd" value=""/>
<mf:input type="hidden" name="usn" value="${item.usn}" />
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">    
    <col width="15%">
    <col width="17%">
    <col width="18%">
    <col width="15%">
    <col width="17%">
    <col width="18%">
    <tr>
        <th><mfmt:message bundle="table.wlc_rat_mst" key="item"/></th>
        <th><mfmt:message bundle="common" key="score"/> (<mfmt:message bundle="common" key="etest.score"/>)</th>
        <th><mfmt:message bundle="table.wlc_rat_mst" key="condition"/>(%)</th>
        <th><mfmt:message bundle="table.wlc_rat_mst" key="item"/></th>
        <th><mfmt:message bundle="common" key="score"/> (<mfmt:message bundle="common" key="etest.score"/>)</th>
        <th><mfmt:message bundle="table.wlc_rat_mst" key="condition"/>(%)</th>
    </tr>
    <tr>
        <th><mfmt:message bundle="table.wlc_fnl_grd" key="score1"/></th>
        <td align="center"><a href="javascript:doView('jindo_view');"><mh:out value="${item.jindo_time_score}" td="true" /></a></td>
        <td align="center"><mh:out value="${item.jindo_time}" td="true" /> </td>
        <th><mfmt:message bundle="table.wlc_fnl_grd" key="score2"/></th>
        <td align="center"><a href="javascript:doView('jindo_view');"><mh:out value="${item.jindo_page_score}" td="true" /></a></td>
        <td align="center"><mh:out value="${item.jindo_page}" td="true" /></td>
    </tr>
    <tr>
        <th><mfmt:message bundle="table.wlc_fnl_grd" key="score3"/></th>
        <td align="center"><a href="javascript:doView('exm_view');"><mh:out value="${item.ratexam_score}" td="true" /></a> </td>
        <td align="center"><mh:out value="${item.ratexam}" td="true" /> </td>
        <th><mfmt:message bundle="table.wlc_fnl_grd" key="score4"/></th>
        <td align="center"><a href="javascript:doView('rpt_view');"><mh:out value="${item.ratreport_score}" td="true" /></a></td>
        <td align="center"><mh:out value="${item.ratreport}" td="true" /></td>
    </tr>
    <tr>
        <th><mfmt:message bundle="table.wlc_fnl_grd" key="score5"/></th>
        <td align="center"><a href="javascript:doView('off_view');"><mh:out value="${item.ratoffline_score}" td="true" /></a></td>
        <td align="center"><mh:out value="${item.ratoffline}" td="true" /></td>
        <th><mfmt:message bundle="table.wlc_fnl_grd" key="score6"/></th>
        <td align="center"><a href="javascript:doView('frm_view');"><mh:out value="${item.ratforum_score}" td="true" /></a> </td>
        <td align="center"><mh:out value="${item.ratforum}" td="true" /> </td>
    </tr>
    <tr>
        <th><mfmt:message bundle="table.wlc_fnl_grd" key="score7"/></th>
        <td align="center"><a href="javascript:doView('project_view');"><mh:out value="${item.ratproject_score}" td="true" /></a></td>
        <td align="center"><mh:out value="${item.ratproject}" td="true" /></td>
        <th><mfmt:message bundle="table.wlc_fnl_grd" key="score8"/></th>
        <td align="center"><a href="javascript:doView('discuss_view');"><mh:out value="${item.ratdiscuss_score}" td="true" /></a> </td>
        <td align="center"><mh:out value="${item.ratdiscuss}" td="true" /> </td>
    </tr>
    <tr>
        <th><mfmt:message bundle="table.wlc_fnl_grd" key="score9"/></th>
        <td align="center"><a href="javascript:doView('etc_view');"><mh:out value="${item.ratetc_score}" td="true" /></a></td>
        <td align="center"><mh:out value="${item.ratetc}" td="true" /></td>
        <th><mfmt:message bundle="table.wlc_fnl_grd" key="score10"/></th>
        <td align="center"><a href="javascript:doView('add_view');"><mh:out value="${item.ratadd_score}" td="true" /></a> </td>
        <td align="center"><mh:out value="${item.ratadd}" td="true" /> </td>
    </tr>
</table>
<br/>
<table width=100% border=0 cellpadding="0" cellspacing=1 class="view">
    <col width="10%">
    <col width="20%">
    <col width="10%">
    <col width="20%">
    <col width="20%">
    <col width="20%">
    <tr>
        <th><mfmt:message bundle="table.wlc_fnl_grd" key="tot_score"/></th>
        <td align="center"><mh:out value="${item.sum_score}" td="true" /></td>
        <th><mfmt:message bundle="table.wlc_fnl_grd" key="final_score"/></th>
        <td align="center"><mh:out value="${item.tot_score}" td="true" /></td>
        <th><mfmt:message bundle="table.wlc_lec_req" key="is_grad"/></th>
        <td align="center">
           <c:if test="${item.tot_cnt != '0'}">
           <mh:out value="${item.is_grad}" td="true" />
           </c:if>
           <c:if test="${item.tot_cnt == '0'}">
           <mfmt:message bundle="common" key="notscoreing"/>
           </c:if>
        </td>
    </tr>
</table>
<table width="100%" border="0" cellpadding="2" cellspacing="1" class="viewBtn">
    <tr>
        <td align="right">
	        <mf:button bundle="button" key="final.score.marking" onclick="doWrite()" icon="icon_add"/>
	        <mf:button bundle="button" key="list" onclick="goList()" icon="icon_list"/>
        </td>
    </tr>
</table>
</mf:form>
</div>