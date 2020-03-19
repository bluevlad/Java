<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript" src="<mh:out value="${CPATH}"/>/js/lib.calendar.js"></script>
<script language="javascript" src="<mh:out value="${CPATH}"/>/js/lib.validate.js"></script>
<script language="javascript" >
    function doUpdate() {
        var frm = getObject("myform");
        if(frm) {
            if (validate(frm)) {
                frm.cmd.value="update";
                frm.submit();
               }
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
    function exmSetChoose(frm_id, elm_id) {
        var urlname = CPATH + "/etest.admin/setchoose.do?frm_id="+frm_id+"&elm_id="+elm_id;
        var oWin = window.open(urlname,
            "setChooseWin",
            "top=100px,left=100px,status=yes,resizable=no,menubar=no,scrollbars=yes,width=100,height=100");
        windows_focus(oWin);
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
        <td><mh:out value="${item.leccode}"/></td>
        <th><mf:label name="alias_cd"/></th> 
        <td><mf:input type="text" name="alias_cd" value="${item.alias_cd}"/></td>
    </tr>
    <tr>
        <th><mf:label name="lec_year"/></th> 
        <td><mh:out value="${item.lec_year}"/></td>
        <th><mf:label name="lecnumb"/></th> 
        <td><mh:out value="${item.lecnumb}"/></td>
    </tr>
    <tr>
        <th><mf:label name="lecname"/></th> 
        <td colspan="3"><mf:input type="text" name="lecname" size="120" value="${item.lecname}" required="true"/></td>
    </tr>
    <tr>
        <th><mf:label name="lec_price"/></th> 
        <td><mf:input type="text" name="lec_price" value="${item.lec_price}" option="number" /> <mf:label name="currency"/></td>
       <th><mf:label name="place"/></th> 
        <td><mf:input type="text" name="place" value="${item.place}"  /></td>           
    </tr>
    <tr>
        <th><mf:label name="lecstat"/></th> 
        <td><mf:select name="lecstat" codeGroup="LECSTAT" curValue="${item.lecstat}"/></td>

        <th><mf:label name="ltype"/></th> 
        <td><mf:select name="ltype" codeGroup="LTYPE" curValue="${item.ltype}"/></td>
    </tr>
    <tr>
        <th><mf:label name="otype"/></th> 
        <td><mf:select name="otype" codeGroup="OTYPE" curValue="${item.otype}"/></td>
        <th><mf:label name="lec_type"/></th> 
        <td><mf:select name="lec_type" codeGroup="LEC_TYPE" curValue="${item.lec_type}" onchange="showhide();"/></td>
    </tr>

    <tr>
        <th><mf:label name="learn_day_cnt"/></th> 
        <td><mf:input type="text" name="learn_day_cnt" value="${item.learn_day_cnt}" option="number" required="true"/> <mfmt:message bundle="common" key="table.day"/></td>
        <th><mf:label name="learn_time"/></th> 
        <td><mf:input type="text" name="learn_time" value="${item.learn_time}" option="number"/> <mfmt:message bundle="common" key="table.hour"/></td>
    </tr>
    <tr>
        <th><mf:label name="lec_capacity"/></th> 
        <td><mf:input type="text" name="lec_capacity" value="${item.lec_capacity}" option="number"/><br>
            <div id="div_target_count" > (target : <c:out value="${tgtcnt}"/>)</div> </td>
        <th><mf:label name="lec_min_capacity"/></th> 
        <td><mf:input type="text" name="lec_min_capacity" value="${item.lec_min_capacity}" option="number"/></td>
     </tr>
     <tr>
        <th><mf:label name="finished_score"/></th> 
        <td><mf:input type="text" name="finished_score" value="${item.finished_score}" required="true" option="number"/>(0~100)</td>
        <th><mf:label name="limit_study_time"/></th> 
        <td><mf:input type="text" name="limit_study_time" value="${item.limit_study_time}" required="true" option="number"/>  <mfmt:message bundle="common" key="table.min"/></td>

    </tr>
    <tr>
        <th><mf:label name="is_approval"/></th> 
        <td><mf:select  name="is_approval" curValue="${item.is_approval}" codeGroup="WLC_APPROVAL"/></td>
        <th><mf:label name="adminid"/></th> 
        <td><mf:input type="text" name="adminid" value="${item.adminid}"/></td>
    </tr>
    <tr>
        <th><mf:label name="attachment"/></th> 
        <td colspan="3"><c:forEach var="fi" items="${files}">
                <mf:input name="delfiles" type="checkbox" value="${fi.fileid}"/>
                <a href='<c:url value="/wdownload">
                    <c:param name="filename" value="${fi.filename}"/>
                    <c:param name="type" value="lecture_file"/></c:url>'><c:out value="${fi.org_filename}"/></a> (<c:out value="${fi.file_size}"/>)KB : <br>
            </c:forEach>
            <br><jsp:include page="/WEB-INF/jsp/common/fileAttach/fileAttach.jsp" flush="true">
           <jsp:param name="FILEID" value="attachfile" />     
         </jsp:include> </td>
    </tr>



<mf:input type="hidden" name="prg_type" value="${item.prg_type}"/>
<mf:input type="hidden" name="lec_weck" value="${item.lec_weck}"/>
<mf:input type="hidden" name="lec_datm" value="${item.lec_datm}"/>
<mf:input type="hidden" name="lec_wetm" value="${item.lec_wetm}"/>
<mf:input type="hidden" name="exm_condition" value="${item.exm_condition}"/>
</table>
<br>
    <span  style="display:none" id="sub_G">
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
                <mf:input type="text" name="lec_sdate" size="20" maxlength="8" onclick="popUpCalendar(this, this, 'yyyymmdd');" value="${item.lec_sdate}" cssClass="dropdown"/> -
                <mf:input type="text" name="lec_edate" size="20" maxlength="8" onclick="popUpCalendar(this, this, 'yyyymmdd');" value="${item.lec_edate}" cssClass="dropdown"/> 
            </td>
        </tr>        
        <tr>
            <th><mf:label name="relearn_day_cnt"/></th> 
            <td align="center"><mf:input name="relearn_day_cnt" size="20" maxlength="15" value="${item.relearn_day_cnt}" option="number" />
                <mfmt:message bundle="common" key="table.day"/></td>
        </tr>
    </table>    
    </span>
    
    <span  style="display:none" id="sub_R">
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
            <mf:input type="text" name="sch_sdt_c" size="20" maxlength="8" onclick="popUpCalendar(this, this, 'yyyymmdd');" value="${scheduleitem.sch_sdt}" cssClass="dropdown"/> -
            <mf:input type="text" name="sch_edt_c" size="20" maxlength="8" onclick="popUpCalendar(this, this, 'yyyymmdd');" value="${scheduleitem.sch_edt}" cssClass="dropdown"/> 
        </td>
    </tr>        
    <tr>
        <th class="view" nowrap><mfmt:message bundle="table.bas_class_schedule" key="sch_type_f"/></th> 
        <td class="view"  align="center">
            <mf:input type="text" name="sch_sdt_f" size="20" maxlength="8" onclick="popUpCalendar(this, this, 'yyyymmdd');" value="${scheduleitem.sch_sdt}" cssClass="dropdown"/> -
            <mf:input type="text" name="sch_edt_f" size="20" maxlength="8" onclick="popUpCalendar(this, this, 'yyyymmdd');" value="${scheduleitem.sch_edt}" cssClass="dropdown"/> 
        </td>
    </tr>        
        
    </table>    
    </span>

<table border="0" cellpadding="2" cellspacing="0" width="100%"> 
    <tr>
        <td align="right">
        <mf:button bundle="button" key="save" onclick="javascript:doUpdate()"/>
        <mf:button bundle="button" key="delete" onclick="doDel()"/>
        <mf:button bundle="button" key="goList" onclick="javascript:goList()"/></td>
    </tr>
</table>
</mf:form>
</div>
<script language="javascript" >
    function showhide_onoff()
    {
        var frm = getObject("myform");
        var what = frm.ltype.value;
        var what1 = frm.otype.value;
        var what2 = frm.lec_type.value;

        if("LECT" == what ) {
            if("G" == what2) {
                document.all["sub_G"].style.display ='';
                document.all["sub_R"].style.display ='none';
            }else{
                document.all["sub_G"].style.display ='none';
                document.all["sub_R"].style.display ='';
            }
        }else{
            document.all["sub_G"].style.display ='none';
            document.all["sub_R"].style.display ='none';
        }   
    }
    
    function showhide()
    {
        var frm = getObject("myform");
        var what = frm.lec_type.value;
        if("G" != what) {
            document.all["sub_G"].style.display ='none';
        } else if("R" != what) {
            document.all["sub_R"].style.display ='none';
        }
        layer_nm = eval("sub_"+what);
        if (layer_nm.style.display=='none') {
            layer_nm.style.display='';
        } else {
            layer_nm.style.display='none';
        }    
    }
</script>
<script>

showhide();
showhide_onoff();
</script>
