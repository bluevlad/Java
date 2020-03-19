<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<script language="javascript" >
    function doEdit() {
        var frm = getObject("myform");
        if(frm) {
            frm.cmd.value="edit";
            frm.submit();
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
    function doEnroll(){
      var frm = getObject("myform");
      if(frm) {
         
          frm.cmd.value = "enroll";
          frm.submit();
      }
    }
    function doEnrollManual(){
      var frm = getObject("myform");
      if(frm) {
         
          frm.cmd.value = "enrollManual";
          frm.submit();
      }
    }

</script>

<div class="viewContainer">
<mf:form action="${control_action}"  method="post" name="myform" id="myform" enctype="multipart/form-data">
<mf:input type="hidden" name="cmd" value=""/>
<mf:input type="hidden" name="leccode" value="${item.leccode}"/>
<mf:input type="hidden" name="orgn_cd" value="${item.orgn_cd}"/>
<mf:input type="hidden" name="dlr_cd" value="${item.dlr_cd}"/>
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">    
    <col width="15%"/>
    <col width="35%"/>
    <col width="15%"/>
    <col width="35%"/>
        <th><mf:label name="leccode"/></th> 
        <td><mh:out value="${item.leccode}" td="true"/></td>
        <th><mf:label name="alias_cd"/></th> 
        <td><mh:out value="${item.alias_cd}" td="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="lec_year"/></th> 
        <td><mh:out value="${item.lec_year}" td="true"/></td>
        <th><mf:label name="lecnumb"/></th> 
        <td><mh:out value="${item.lecnumb}" td="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="lecname"/></th> 
        <td colspan="3"><mh:out value="${item.lecname}" td="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="lec_price"/></th> 
        <td  ><mh:out value="${item.lec_price}" default="N/A"/> <mf:label name="currency"/></td>
       <th><mf:label name="place"/></th> 
        <td><mh:out value="${item.place}" td="true"/></td>        
    </tr>
    <tr>
        <th><mf:label name="lecstat"/></th> 
        <td><mh:out td="true"  codeGroup="LECSTAT" value="${item.lecstat}"/> <a href='<c:url value="/wlc.admin/lecreq.do">
            <c:param name="leccode" value="${item.leccode}"/>
            <c:param name="cmd" value="view"/>
            </c:url>'>(<mh:out value="${item.cnt_lr}"/> / <mh:out value="${item.cnt_lp}"/> / <mh:out value="${item.cnt_le}"/> )</a> </td>

        <th><mf:label name="ltype"/></th> 
        <td><mh:out td="true"  codeGroup="LTYPE" value="${item.ltype}"/></td>
    </tr>
    <tr>
        <th><mf:label name="otype"/></th> 
        <td><mh:out td="true"  codeGroup="OTYPE" value="${item.otype}"/></td>
        <th><mf:label name="lec_type"/></th> 
        <td><mh:out td="true"  codeGroup="LEC_TYPE" value="${item.lec_type}" /></td>
    </tr>

    <tr>
        <th><mf:label name="learn_day_cnt"/></th> 
        <td><mh:out td="true"  value="${item.learn_day_cnt}" /> <mfmt:message bundle="common" key="table.day"/></td>
        <th><mf:label name="learn_time"/></th> 
        <td><mh:out td="true"  value="${item.learn_time}" /> <mfmt:message bundle="common" key="table.hour"/></td>
    </tr>
    <tr>
        <th><mf:label name="lec_capacity"/></th> 
        <td><mh:out td="true" value="${item.lec_capacity}" /></td>
        <th><mf:label name="lec_min_capacity"/></th> 
        <td><mh:out td="true" value="${item.lec_min_capacity}"/></td>
     </tr>
     <tr>
        <th><mf:label name="finished_score"/></th> 
        <td><mh:out td="true"  value="${item.finished_score}" /></td>
        <th><mf:label name="limit_study_time"/></th> 
        <td><mh:out td="true"  value="${item.limit_study_time}" />  <mfmt:message bundle="common" key="table.min"/></td>

    </tr>
    <tr>
        <th><mf:label name="is_approval"/></th> 
        <td><mh:out td="true" value="${item.is_approval}" codeGroup="WLC_APPROVAL"/></td>
        <th><mf:label name="adminid"/></th> 
        <td><mh:out td="true" value="${item.adminid}"/></td>
    </tr>
    <tr>
        <th><mf:label name="attachment"/></th> 
        <td colspan="3"><c:forEach var="fi" items="${files}">
                <a href='<c:url value="/wdownload">
                    <c:param name="filename" value="${fi.filename}"/>
                    <c:param name="type" value="lecture_file"/></c:url>'><c:out value="${fi.org_filename}"/></a> (<c:out value="${fi.file_size}"/>)KB : <br>
            </c:forEach>&nbsp;</td>
    </tr>



<mf:input type="hidden" name="prg_type" value="${item.prg_type}"/>
<mf:input type="hidden" name="lec_weck" value="${item.lec_weck}"/>
<mf:input type="hidden" name="lec_datm" value="${item.lec_datm}"/>
<mf:input type="hidden" name="lec_wetm" value="${item.lec_wetm}"/>
<mf:input type="hidden" name="exm_condition" value="${item.exm_condition}"/>
</table>
<br>
    <c:if test="${item.lec_type == 'G' }">
    <table width="100%" border="0" cellpadding="2" cellspacing="0" class="view">
    <col width="20%"/>
    <col width="80%"/>
             <thead>
            <tr>
                <th>&nbsp;</th>
                <th><mfmt:message bundle="table.bas_class_schedule" key="sch_sdt"/>
                    - <mfmt:message bundle="table.bas_class_schedule" key="sch_edt"/><br>
                    (YYYYMMDD)</th>
            </tr>
         </thead>
        <tr>
            <th nowrap><mf:label name="lec_sdate"/></th> 
            <td align="center">
                <mh:out td="true"  value="${item.lec_sdate}" /> -
                <mh:out td="true"  value="${item.lec_edate}" /> 
            </td>
        </tr>        
        <tr>
            <th><mf:label name="relearn_day_cnt"/></th> 
            <td align="center"><mh:out td="true"  value="${item.relearn_day_cnt}"  />
                <mfmt:message bundle="common" key="table.day"/></td>
        </tr>
    </table>    
    </c:if>
    
    <c:if test="${item.lec_type == 'R' }">
    <table width="100%" border="0" cellpadding="2" cellspacing="0" class="view">
    <col width="20%"/>
    <col width="80%"/>
         <thead>
            <tr>
                <th>&nbsp;</th>
                <th><mfmt:message bundle="table.bas_class_schedule" key="sch_sdt"/>
                    - <mfmt:message bundle="table.bas_class_schedule" key="sch_edt"/><br>
                    (YYYYMMDD)</th>
            </tr>
         </thead>
    <tr>
        <th  nowrap><mfmt:message bundle="table.bas_class_schedule" key="sch_type_a"/></th> 
        <td align="center">
            <mf:input type="text" name="sch_sdt_a" size="20" maxlength="8" onclick="popUpCalendar(this, this, 'yyyymmdd');" value="${scheduleitem.sch_sdt}" cssClass="dropdown"/> -
            <mf:input type="text" name="sch_edt_a" size="20" maxlength="8" onclick="popUpCalendar(this, this, 'yyyymmdd');" value="${scheduleitem.sch_edt}" cssClass="dropdown"/> 
        </td>
    </tr>
    <tr>
        <th nowrap><mfmt:message bundle="table.bas_class_schedule" key="sch_type_c"/></th> 
        <td align="center" >
            <mh:out td="true"  value="${scheduleitem.sch_sdt}" /> -
            <mh:out td="true"  value="${scheduleitem.sch_edt}" /> 
        </td>
    </tr>        
    <tr>
        <th class="view" nowrap><mfmt:message bundle="table.bas_class_schedule" key="sch_type_f"/></th> 
        <td class="view"  align="center">
            <mh:out td="true" value="${scheduleitem.sch_sdt}" /> -
            <mh:out td="true"  value="${scheduleitem.sch_edt}" /> 
        </td>
    </tr>        
        
    </table>    
    </c:if>

<table border="0" cellpadding="2" cellspacing="0" width="100%"> 
    <tr>
        <td align="right">
        <c:if test="${item.orgn_cd==org_cd}">
        <mf:button bundle="button" key="edit" onclick="doEdit()"/>
        <mf:button bundle="button" key="delete" onclick="doDel()"/>
        &nbsp;
        <mf:button bundle="button" key="button.lecmng" onclick="doEnroll()"/>
        <mf:button bundle="button" key="button.lecmngManual" onclick="doEnrollManual()"/>
        &nbsp;
        </c:if>
        <mf:button bundle="button" key="goList" onclick="goList()"/>

        </td>
    </tr>
</table>
</mf:form>
</div>
