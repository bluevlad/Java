<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript" src="<mh:out value="${CPATH}"/>/js/calendar.js"></script>
<script language="javascript" src="<mh:out value="${CPATH}"/>/js/lib.validate.js"></script>
<script language="javascript">
    function doInsert() {
        var frm = getObject("myform");
        if(frm) {
            if (validate(frm)) {
                frm.cmd.value="insert";
                frm.submit();
            }
        }
    }
    function goList() {
        <c:url var="urlList" value="${control_action}">
            <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
            <c:param name="cmd" value="list"/>
        </c:url>
        document.location = '<mh:out value="${urlList}"/>';
    }
    function setSubName(obj) {
    	 var frm = getObject("myform");
         var sjt_cd = null;
    	 if(frm) {
                sjt_cd = obj[obj.selectedIndex].value
    	 		frm.lecname.value = obj[obj.selectedIndex].text;
                var url = '<%=request.getAttribute("controlaction")%>';
                new Ajax.Request(url, {
                                method: 'get', 
                                parameters: {'cmd':'getTgtCnt','sjt_cd':sjt_cd},
                                onSuccess: function(transport) {
                                    var json = transport.responseText.evalJSON();
                                    var obj = getObject("div_target_count");
                                    obj.innerHTML = "(target : " + json.tgtcnt + " ) ";
                                }
                        }
                 ); 
          } 
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
<mf:input type="hidden" name="orgn_cd" value="${org.org_cd}"/>
<mf:input type="hidden" name="dlr_cd" value="${org.deal_no}"/>
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">    
    <col width="15%"/>
    <col width="35%"/>
    <col width="15%"/>
    <col width="35%"/>
    <tr>
        <th><mf:label name="sjt_cd"/></th>
        <td><select name="sjt_cd" id="sjt_cd" onChange="setSubName(this)">
        	<c:forEach var="itm" items="${crs_sub}" >
        		<mf:option value="${itm.sjt_cd}" text="${itm.subject_nm}" />
        	</c:forEach>
        </select></td>

        <th><mf:label name="lec_year"/></th> 
        <td><mf:input type='text' name='lec_year' value='${yyyy}' required='true'/></td>
        
    </tr>
    <tr>
        <th><mf:label name="alias_cd"/></th> 
        <td><mf:input type="text" name="alias_cd" size="50" value=""/></td>
    </tr>
    <tr>        
        <th><mf:label name="lec_price"/></th> 
        <td><mf:input type="text" name="lec_price" value="${item.lec_price}" option="number" /> <mf:label name="currency"/></td>
       <th><mf:label name="place"/></th> 
        <td><mf:input type="text" name="place" value="${item.place}"  /></td>           
        
    </tr>
    <tr>
        <th><mf:label name="lecname"/></th> 
        <td colspan="3"><mf:input type="text" name="lecname" size="120" value=""/></td>
    </tr>
    <tr>
        <th><mf:label name="lecstat"/></th> 
        <td><mf:select name="lecstat" codeGroup="LECSTAT" /></td>

        <th><mf:label name="ltype"/></th> 
        <td><mf:select name="ltype" codeGroup="LTYPE" /></td>
       
    </tr>
    <tr>
        <th><mf:label name="otype"/></th> 
        <td><mf:select name="otype" codeGroup="OTYPE" /></td>
        <th><mf:label name="lec_type"/></th> 
        <td><mf:select name="lec_type" codeGroup="LEC_TYPE" onchange="showhide();"/></td>
    </tr>


    <tr>
        <th><mf:label name="learn_day_cnt"/></th> 
        <td><mf:input type="text" name="learn_day_cnt" value="" option="number" required="true"/> <mfmt:message bundle="common" key="table.day"/></td>
        <th><mf:label name="learn_time"/></th> 
        <td><mf:input type="text" name="learn_time" value="" option="number" /> <mfmt:message bundle="common" key="table.hour"/></td>
    </tr>
    <tr>        
        <th><mf:label name="lec_capacity"/></th> 
        <td><mf:input type="text" name="lec_capacity" value="" option="number"/> <br><div id="div_target_count" > (target : 0)</div> </td>

        <th><mf:label name="lec_min_capacity"/></th> 
        <td><mf:input type="text" name="lec_min_capacity" value="" option="number"/> </td>
    </tr>
    <tr>        
        <th><mf:label name="finished_score"/></th> 
        <td><mf:input type="text" name="finished_score" value="" required="true"  option="number"/>(0~100)</td>
        <th><mf:label name="limit_study_time"/></th> 
        <td><mf:input type="text" name="limit_study_time" value="" required="true" option="number"/> <mfmt:message bundle="common" key="table.min"/></td>

    </tr>
    
    <tr>
        <th><mf:label name="is_approval"/></th> 
        <td  ><mf:select  name="is_approval" curValue="${item.is_approval}" codeGroup="WLC_APPROVAL"/></td>
        <th><mf:label name="adminid"/></th> 
        <td><mf:input type="text" name="adminid" value=""/></td>
    </tr>
    <tr>
        <th><mf:label name="attachment"/></th> 
        <td colspan="3"> <jsp:include page="/WEB-INF/jsp/common/fileAttach/fileAttach.jsp" flush="true">
           <jsp:param name="FILEID" value="attachfile" />     
         </jsp:include> </td>
    </tr> 


<mf:input type="hidden" name="prg_type" value="2"/>
<mf:input type="hidden" name="lec_weck" value="N"/>
<mf:input type="hidden" name="lec_datm" value="0"/>
<mf:input type="hidden" name="lec_wetm" value="0"/>
<mf:input type="hidden" name="exm_condition" value="80"/>
</table>
<br>
    <span  style="display:'none'" id="sub_G">
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
            <td  align="center">
                <mf:input type="text" name="lec_sdate" size="20" maxlength="8" onclick="popUpCalendar(this, this, 'yyyymmdd');" value="" cssClass="dropdown"/> -
                <mf:input type="text" name="lec_edate" size="20" maxlength="8" onclick="popUpCalendar(this, this, 'yyyymmdd');" value="" cssClass="dropdown"/> 
            </td>
        </tr>        
  
        <tr>
            <th><mf:label name="relearn_day_cnt"/></th> 
            <td  align="center"><mf:input name="relearn_day_cnt" size="20" maxlength="15" value="" option="number" /> <mfmt:message bundle="common" key="table.day"/></td>
        </tr>
    </table>    
    </span>
    <span  style="display:'none'" id="sub_R">
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
            <th nowrap><mfmt:message bundle="table.bas_class_schedule" key="sch_type_a"/></th> 
            <td  align="center">
                <input type="text" name="sch_sdt_a" size="20" maxlength="8" onclick="popUpCalendar(this, this, 'yyyymmdd');" value="" Class="dropdown"/> -
                <input type="text" name="sch_edt_a" size="20" maxlength="8" OnClick="popUpCalendar(this, this, 'yyyymmdd');" value="" Class="dropdown"/> 
            </td>
        </tr>

        <tr>
            <th nowrap><mfmt:message bundle="table.bas_class_schedule" key="sch_type_c"/></th> 
            <td  align="center">
                <input type="text" name="sch_sdt_c" size="20" maxlength="8" OnClick="popUpCalendar(this, this, 'yyyymmdd');" value="" Class="dropdown"/> -
                <input type="text" name="sch_edt_c" size="20" maxlength="8" OnClick="popUpCalendar(this, this, 'yyyymmdd');" value="" Class="dropdown"> 
            </td>
        </tr>
        
        <tr>
            <th nowrap><mfmt:message bundle="table.bas_class_schedule" key="sch_type_f"/></th> 
            <td  align="center">
                <input type="text" name="sch_sdt_f" size="20" maxlength="8" OnClick="popUpCalendar(this, this, 'yyyymmdd');" value="" Class="dropdown"> -
                <input type="text" name="sch_edt_f" size="20" maxlength="8" OnClick="popUpCalendar(this, this, 'yyyymmdd');" value="" Class="dropdown"> 
            </td>
        </tr>
    </table>    
    </span>

<table border="0" cellpadding="2" cellspacing="0" width="100%"> 
    <tr>
        <td align="right">
        <mf:button bundle="button" key="save" onclick="javascript:doInsert()"/>
        <mf:button bundle="button" key="goList" onclick="javascript:goList()"/></td>
    </tr>
</table>
</mf:form>
</div>
<script language="javascript" >
	function showhide_onoff()
	{
	    var frm = getObject("myform");
	    var what = frm.ltype[frm.ltype.selectedIndex].value;
	    var what1 = frm.otype[frm.otype.selectedIndex].value;
	    var what2 = frm.lec_type[frm.lec_type.selectedIndex].value;

	    if("LECT" == what && "ON" == what1) {
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
	    var what = frm.lec_type[frm.lec_type.selectedIndex].value;
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
