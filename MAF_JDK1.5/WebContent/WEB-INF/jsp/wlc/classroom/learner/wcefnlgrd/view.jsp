<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>


<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">	
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this);return false;" enctype="multipart/form-data" >
<input type="image" id="dummy" width="0" height="0" border="0" class="hidden"/>
<input type="hidden" name="cmd" value="">
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
        <td align="center"><mh:out value="${item.jindo_time_score}" td="true" /></td>
        <td align="center"><mh:out value="${item.jindo_time}" td="true" /></td>
        <th align="center"><mfmt:message bundle="table.wla_rat_mst" key="ratexam"/></th>
        <td align="center"><mh:out value="${item.ratexam_score}" td="true" /></a></td>
        <td align="center"><mh:out value="${item.ratexam}" td="true" /></td>
    </tr>
    <tr height="25">
        <th align="center"><mfmt:message bundle="table.wla_rat_mst" key="jindo_page"/></th>
        <td align="center"><mh:out value="${item.jindo_page_score}" td="true" /></td>
        <td align="center"><mh:out value="${item.jindo_page}" td="true" /></td>
        <th align="center"><mfmt:message bundle="table.wla_rat_mst" key="ratoffline"/></th>
        <td align="center"><mh:out value="${item.ratoffline_score}" td="true" /></td>
        <td align="center"><mh:out value="${item.ratoffline}" td="true" /></td>
    </tr>
</table>
<br/>
<table width=100% border=0 cellpadding="0" cellspacing=1 class="view">
    <col width="15%"/>
    <col width="35%"/>
    <col width="15%"/>
    <col width="35%"/>
    <tr height="25">
        <th align="center"><mfmt:message bundle="table.wce_fnl_grd" key="tot_score"/></th>
        <td ><mh:out value="${item.sum_score}" td="true" /></td>
        <th align="center"><mfmt:message bundle="table.wce_fnl_grd" key="score10"/></th>
        <td ><mh:out value="${item.score10}" td="true" /></td>
    </tr>
    <tr height="25">
        <th align="center"><mfmt:message bundle="table.wce_fnl_grd" key="final_score"/></th>
        <td><mh:out value="${item.tot_score}" td="true" /></td>
        <th align="center"><mfmt:message bundle="table.wcc_lec_req" key="is_grad"/></th>
        <td >
           <c:if test="${item.tot_cnt != '0'}">
           <mh:out value="${item.is_grad}" td="true" />
           </c:if>
           <c:if test="${item.tot_cnt == '0'}">
           <mfmt:message bundle="common" key="table.notscoreing"/>
           </c:if>
        </td>
    </tr>
</table>
</mf:form>    

