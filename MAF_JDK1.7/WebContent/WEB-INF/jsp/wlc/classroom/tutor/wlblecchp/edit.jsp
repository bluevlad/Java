<%@ page contentType="text/html; charset=utf-8"%>     
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>    

<script language="javascript" src='<c:url value="/js/lib.validate.js"/>'></script>
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
    window.open("<mh:out value="${control_action}" td="true"/>?cmd=selist&sjtcode=<mh:out value="${sjt_cd}" td="true"/>","selCnt_win", "resizable=yes,width=800,height=600,top=0,left=0,status=yes,scrollbars=yes,location=no");
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
        //alert("<mfmt:message bundle="common.scripts" key="lb.lec_chp.script.msg1"/>");
        maf.alert('common.scripts','lb.lec_chp.script.msg1');
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
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit=" frmSubmit('myform');return false;">
  <input type="image" value="test" width="0" height="0" border="0" class="hidden">
  <mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}"/>
  <input type="hidden" name="cmd" value="">  
  <mf:input type="hidden" name="leccode" value="${leccode}"/>  
  <input type=hidden name="version" value="non">
<c:choose>
  <c:when test="${param.cmd == 'edit'}">
    <mf:input type="hidden" name="itm_id" value="${item.itm_id}" />
    <mf:input type="hidden" name="itm_sequence" value="${item.itm_sequence}"/>
  </c:when>
</c:choose>
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">  
  <col width="20%"/>
  <col width="30%"/>  
  <col width="20%"/>
  <col width="30%"/>  
  <tr>
    <th colspan="4" align="left"><mfmt:message bundle="table.wlb_lec_chp" key="msg1"/></th> 
  </tr>  
  <tr>
    <th ><mfmt:message bundle="table.wlb_lec_chp" key="title1"/></th> 
    <td colspan="3">
        <mf:input type="text" name="htmcode" size="15" maxlength="15" value="${item.htmcode}" />
        <mf:button bundle="table.wlb_lec_chp" key="title10" onclick="contentSelect()"/>
        <mf:button bundle="table.wlb_lec_chp" key="title11" onclick="contentClear('myform')"/>
    </td>
  </tr>  
</table>
<br/>
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">  
  <col width="20%"/>
  <col width="30%"/>  
  <col width="20%"/>
  <col width="30%"/>  
  <tr>
    <th nowrap><mf:label name="itm_title"/></th> 
    <td colspan="3" ><mf:input type="text" name="itm_title" size="100" maxlength="100" required="true"  value="${item.itm_title}"/></td>
  </tr>  
<!--   <tr>
    <th colspan="4" align=left nowrap><mfmt:message bundle="table.wlb_lec_chp" key="msg2"/>xxxx</th> 
  </tr>  -->
  <tr>
    <th nowrap><mfmt:message bundle="table.wlb_lec_chp" key="title2"/></th> 
    <td colspan="3" ><mf:input type="text" name="launch_data" size="80" maxlength="200" value="${item.launch_data}"/>
                <mf:button key="button.inx_lst.select" bundle="button" onclick="openContents('launch_data')"/>
             <mf:button key="preview" bundle="button" onclick="previewLec('launch_data')"/>
             </td>
  </tr>  
  <tr>
    <th nowrap><mf:label name="place_type"/></th> 
    <td >
    <mf:select name="place_type" codeGroup="PLACE_TYPE" curValue="${item.place_type}"/>
    </td>
    <th nowrap><mf:label name="totpage"/></th> 
    <td ><mf:input type="text" name="totpage" size="10" maxlength="3" value="${item.totpage}" required="true" option="number"/></td>
  </tr>  
  <tr>
    <th nowrap><mfmt:message bundle="table.wlb_lec_chp" key="popup_size"/></th> 
    <td ><mf:input type="text" name="cnt_width" size="4" maxlength="4"  readonly="true" value="${item.cnt_width}"/> X
        <mf:input type="text" name="cnt_height" size="4" maxlength="4"  readonly="true" value="${item.cnt_height}"/><br/>
        (<span class="msginfo6"><mfmt:message bundle="table.wra_inx_lst" key="script.msg7"/></span>)
    </td>
    
    <th nowrap><mfmt:message bundle="table.wlb_lec_chp" key="itm_maxtimeallowed"/></th> 
    <td ><mf:input type="text" name="itm_maxtimeallowed" size="10" maxlength="3" required="true"  option="number" value="${item.itm_maxtimeallowed}"/> 
        <mfmt:message bundle="table.wlb_lec_chp" key="title7"/></td>
   
  </tr>  
  <tr>
    <th nowrap><mfmt:message bundle="table.wlb_lec_chp" key="title4"/></th> 
    <td >
            <select name="itm_lvl" class="in_basic">
        <% for(int i=1; i < 6; i++) { %>
            <option value="<%=i%>"> <%=i %> </option>
        <% } %>
            </select> <mfmt:message bundle="table.wlb_lec_chp" key="title8"/>
    </td>
    <th nowrap><mf:label name="off_place"/></th> 
    <td ><mf:input type="text" name="off_place" size="20" maxlength="50" value="${item.off_place}"/></td>
  </tr>  


</table>
<table border="0" cellpadding="2" cellspacing="0" class="viewBtn" width="100%">   
  <tr>
    <td colspan="2" align="center">
    <mf:button bundle="button" key="save" onclick="frmSubmit('myform')"/>  
    <c:if test="${param.cmd == 'edit'}"> 
    <mf:button bundle="button" key="delete" onclick="goDelete();"/>   
    </c:if>
    <mf:button bundle = "button"  key="goList" onclick="goList()"/></td>
  </tr>
</table>
</mf:form>
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
