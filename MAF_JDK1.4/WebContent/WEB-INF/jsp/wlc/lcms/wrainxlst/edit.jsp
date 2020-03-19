<%@ page contentType="text/html; charset=utf-8"%>     
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<%@ page import="maf.*" %>      
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
        
        }
    }
    

    function goList() {
        <c:url var="urlList" value="${control_action}">
            <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
            <c:param name="cmd" value="list"/>
        </c:url>
        document.location = '<mh:out value="${urlList}"/>';
    }
    
    // subject name set
    function setSjtname(frm) {
        if(frm.sjtcode.selectedIndex > 0)
            frm.sjtname.value = frm.sjtcode[frm.sjtcode.selectedIndex].text;
        else
            frm.sjtname.value = '';
    }
    function choose() {
    }
//-->
</script>
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit=" frmSubmit('myform');return false;">
    <input type="image" value="test" width="0" height="0" border="0" class="hidden">
    <mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}"/>
    <input type="hidden" name="cmd" value="">    
    <mf:input type="hidden" name="htmcode" value="${item.htmcode}"/>  
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">    
    <col width="20%"/>
    <col width="30%"/>    
    <col width="20%"/>
    <col width="30%"/>    
    <tr>
<c:choose>
    <c:when test="${param.cmd == 'add'}">
        <th nowrap><mf:label name="sjtcode"/></th> 
        <td >
        <select name="sjtcode" required onChange="setSjtname(this.form);">
         <option value=""><mfmt:message bundle="table.wra_inx_lst" key="select_subject"/></option>
        <c:forEach var="sbjlist" items="${slist}" varStatus="status">
          <mf:option text="${sbjlist.subject_nm}" value="${sbjlist.sjt_cd}"/>
        </c:forEach>
        </select>
        </td>
    </c:when>
    <c:when test="${param.cmd == 'edit'}">
        <th nowrap><mf:label name="sjtcode"/></th> 
        <td ><mh:out value="${item.sjtcode}" td="true" /></td>
        <mf:input type="hidden" name="sjtcode" value="${item.sjtcode}"/>
    </c:when>
</c:choose>
        <th nowrap><mf:label name="sjtname"/></th> 
        <td ><mf:input type="text" name="sjtname" size="30" maxlength="50" value="${item.sjtname}" required="true"/></td>
    </tr>    
    <tr>
        <th nowrap><mf:label name="daecode"/></th> 
        <td colspan=3 ><mf:input type="text" name="daecode" size="20" maxlength="50" value="${item.daecode}" required="true"/>
        (<span class="msginfo6"><mfmt:message bundle="common.scripts" key="script.alert.only.alphanumdash"/></span>)
        </td>
    </tr>
    <tr>
        <th nowrap><mf:label name="daename"/></th> 
        <td colspan=3 ><mf:input type="text" name="daename" size="60" maxlength="50" value="${item.daename}" required="true"/></td>
    </tr>    
  
    <tr>
        <th nowrap><mf:label name="htmurl"/></th> 
        <td colspan=3 ><mf:input type="text" name="htmurl" size="60" maxlength="255" value="${item.htmurl}" required="true"/> 
             <mf:button key="button.inx_lst.select" bundle="button" onclick="openContents('htmurl')"/>
             <mf:button key="preview" bundle="button" onclick="previewLec('htmurl')"/></td>
    </tr>    
    <tr>
        <th nowrap><mf:label name="totpage"/></th> 
        <td ><mf:input type="text" name="totpage" size="20" option="number" required="true" maxlength="50" value="${item.totpage}"/></td>
        <th nowrap><mf:label name="isuse"/></th> 
        <td ><mf:select name="isuse" codeGroup="ISUSE" curValue="${item.isuse}"></mf:select></td>
    </tr>    
    <tr>
        <th nowrap><mfmt:message bundle="table.wra_inx_lst" key="popup_size"/></th> 
        <td colspan=3 ><mf:input type="text" name="cnt_width" size="20" maxlength="50" value="${item.cnt_width}" option="number" required="true"/> X
        <mf:input type="text" name="cnt_height" size="20" maxlength="50" value="${item.cnt_height}" option="number" required="true"/>
        (<span class="msginfo6"><mfmt:message bundle="table.wra_inx_lst" key="script.msg7"/></span>)
        </td>
    </tr>    
    <tr>
        <th nowrap><mfmt:message bundle="table.wlb_lec_chp" key="itm_maxtimeallowed"/></th> 
        <td  ><mf:input type="text" name="lrntime" option="number" required="true" size="20" maxlength="50" value="${item.lrntime}"/> <mfmt:message bundle="table.wlb_lec_chp" key="title7"/></td>
        <th nowrap><mf:label name="orgn_cd"/></th> 
        <td  >
    <c:choose>
        <c:when test="${isroot}">
            <mf:input type="text" name="orgn_cd" size="20" maxlength="50" value="${item.orgn_cd}"/>
        </c:when>
        <c:otherwise>
            
            <mf:input type="hidden" name="orgn_cd"  value="${item.orgn_cd}"/><c:out value="${item.orgn_cd}"/>
        </c:otherwise>
    </c:choose>
    </td>
    </tr>    
</table>
<table border="0" cellpadding="2" cellspacing="0" class="viewBtn" width="100%">     
    <tr>
        <td colspan="2" align="center">
        <mf:button bundle="button" key="save" onclick="frmSubmit('myform')"/>   
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
    if(width == "") {
        width = 800;
    }
    if(height == "") {
        height = 600;
    }
    browsing_window = window.open(urlname, "imagewin","top=200px,left=200px,status=no,resizable=yes,menubar=no,scrollbars=yes,width="+width+",height="+height);
    browsing_window.focus();
}   
function openContents(elname) {
    popupWindow('<c:url value="/wlc.lcms/ContentsFinder.do"/>?elname='+elname);
}
</script>
        
        
        