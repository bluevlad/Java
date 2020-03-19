<%@ page contentType="text/html; charset=utf-8"%>     
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>    

<script language="javascript" >
<!--
  function frmSubmit(frmName) {
    var frm = getObject(frmName);
    if(frm) {
      if (validate(frm)) {
     
<c:choose>
    <c:when test="${param.cmd == 'add'}">
                frm.cmd.value="insert";
    </c:when>
    <c:when test="${param.cmd == 'edit'}">
                frm.cmd.value="update";
    </c:when>
</c:choose>
        frm.submit();
      }
    } else {
      alert ("form[" + frmName + "] is invalid");
    }
  }
  
  function goDelete() {
    var frm = getObject("myform");
    if(frm) {
            frm.cmd.value="delete";
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

// 
function contentSelect(){
    window.open('<mh:out value="${control_action}"/>?cmd=selist&sjt_cd=<mh:out value="${sjt_cd}"/>',"selCnt_win", "resizable=yes,width=850,height=600,top=0,left=0,status=yes,scrollbars=yes,location=no");
}
//
function contentClear(frmName)
{
    var frm = getObject(frmName);
    frm.htmcode.value = '';
    frm.itm_title.value = '';
    frm.launch_data.value = '';
    frm.itm_maxtimeallowed.value = '';
    frm.cnt_width.value = '';
    frm.cnt_height.value = '';
    frm.totpage.value = '';
}

function add(form)
{
    if(form.itm_title.value == '') {
        alert('<mfmt:message bundle="common.scripts" key="lb.lec_chp.script.msg1"/>');
        form.itm_title.focus();
    } else {
        form.cmd.value='insert';
        var win = window.open("about:blank","lecture_add_message", "resizable=yes,width=400,height=300,top=0,left=0,scrollbars=no,location=no");
        if(win != null) {
            form.target = 'lecture_add_message';
            form.submit();
        }
    }                
}  

// item level change
function changeItm_lvl(form) {
    var words = form.itm_pid[form.itm_pid.selectedIndex].value.split("#");
    if(words != null && words[1] != '') {
        for(i=0; i < form.itm_lvl.length; i++) {
            if(words[1] == form.itm_lvl[i].value) {
                form.itm_lvl.selectedIndex=(i);
                break;
            }
        }
    }
}
//-->
</script>

<div class="viewContainer">
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit=" frmSubmit('myform');return false;">
<mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}"/>
<mf:input type="hidden" name="cmd" value=""/>  
<mf:input type="hidden" name="lec_cd" value="${lec_cd}"/>  
<mf:input type="hidden" name="sjt_cd" value=""/>  
<mf:input type="hidden" name="itm_id" value="${item.itm_id}" />
<mf:input type="hidden" name="cnt_width" value="${item.cnt_width}"/>
<mf:input type="hidden" name="cnt_height" value="${item.cnt_height}"/>
<c:choose>
<c:when test="${param.cmd == 'edit'}">
    <mf:input type="hidden" name="itm_sequence" value="${item.itm_sequence}"/>
</c:when>
</c:choose>
<table width="100%" border="0" cellpadding="2" cellspacing="0" class="view">  
    <col width="20%"/>
    <col width="30%"/>  
    <col width="20%"/>
    <col width="30%"/>  
    <tr>
        <th colspan="4" align="left"><mfmt:message bundle="common.lec" key="content.info"/></th> 
    </tr>  
    <tr>
        <th><mfmt:message bundle="common.lec" key="content.select"/></th> 
        <td colspan="3">
	        <mf:input type="text" name="htmcode" cssStyle="width:50px" value="${item.htmcode}" readonly="true" required="true" />
	        <mf:button bundle="button" key="lec_chp.cnt.sel" onclick="contentSelect()"/>
	        <mf:button bundle="button" key="lec_chp.cnt.delete" onclick="contentClear('myform')"/>
        </td>
    </tr>  
</table>
<br/>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="view">  
    <col width="20%"/>
    <col width="30%"/>  
    <col width="20%"/>
    <col width="30%"/>  
    <tr>
        <th><mf:label name="itm_title"/></th> 
        <td colspan="3"><mf:input type="text" name="itm_title" cssStyle="width:98%" readonly="true" required="true" value="${item.itm_title}"/></td>
    </tr>  
    <tr>
        <th><mf:label name="launch_data"/></th> 
        <td colspan="3">
            <mf:input type="text" name="launch_data" cssStyle="width:500px" value="${item.htmurl}" readonly="true" />
            <mf:button key="preview" bundle="button" onclick="previewLec('launch_data')"/>
        </td>
    </tr>  
    <tr>
        <th><mf:label name="totpage"/></th>
        <td><mf:input type="text" name="totpage" cssStyle="width:50px" value="${item.totpage}" option="number"/></td>
        <th><mf:label name="itm_maxtimeallowed"/></th> 
        <td>
            <mf:input type="text" name="itm_maxtimeallowed" cssStyle="width:50px" option="number" value="${item.itm_maxtimeallowed}"/> 
            <mfmt:message bundle="common" key="time.min"/>
        </td>
    </tr>  
    <!--tr>
        <th><mf:label name="cnt_size"/></th> 
        <td>
            <mf:input type="text" name="cnt_width" size="4" maxlength="4" value="${item.cnt_width}"/> X
            <mf:input type="text" name="cnt_height" size="4" maxlength="4" value="${item.cnt_height}"/>
        </td>
        <th><mf:label name="week"/>-<mf:label name="chpnumb"/></th> 
        <td>
            <mf:input type="text" name="week" size="3" maxlength="3" option="number" value="${item.week}"/><mf:label name="week"/>
            <mf:input type="text" name="chpnumb" size="3" maxlength="3" option="number" value="${item.chpnumb}"/><mf:label name="chpnumb"/>
        </td>
    </tr-->
    <tr>
        <th><mf:label name="chp_sdat" /></th>
        <td><mf:input type="date" name="chp_sdat" cssStyle="width:90px" value="${item.chp_sdat}" readonly="true" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');"/></td>
        <th><mf:label name="chp_edat" /></th>
        <td><mf:input type="date" name="chp_edat" cssStyle="width:90px" value="${item.chp_edat}" readonly="true" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');"/></td>
    </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="viewBtn">   
    <tr>
        <td align="right">
			<mf:button bundle="button" key="save" onclick="frmSubmit('myform')" icon="icon_save"/>  
			<c:if test="${param.cmd == 'edit'}"> 
			<mf:button bundle="button" key="delete" onclick="goDelete();" icon="icon_delete"/>
			</c:if>
			<mf:button bundle="button" key="list" onclick="goList()" icon="icon_list"/>
        </td>
    </tr>
</table>
</mf:form>
</div>
<script>
// 강의 보기 
function previewLec(nm){
    var frm = getObject("myform");
    var urlname = frm[nm].value;
    var width=frm.cnt_width.value;
    var height=frm.cnt_height.value;
    browsing_window = window.open(urlname, "imagewin","top=200px,left=200px,status=no,resizable=yes,menubar=no,scrollbars=yes,width="+width+",height="+height);
    browsing_window.focus();
} 
  
function openContents(elname) {
    popupWindow('<c:url value="/wlc.lcms/ContentsFinder.do"/>?elname='+elname);
}
</script>