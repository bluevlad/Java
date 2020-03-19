<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<SCRIPT LANGUAGE="JavaScript">
<!--

    
    function goList() {
	    <c:url var="urlList" value="${control_action}">
	        <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
	    	<c:param name="cmd" value="list"/>
	    </c:url>
		document.location = '<mh:out value="${urlList}"/>';
    }
    
function updateScore(frm) {
    var frm = getObject("myform");
    //frm.cmd.value='update';
    //frm.submit();
    gogogo(frm, 1);
}
    
//-->
</SCRIPT>
<script language="javascript">
<!--
/**
* Checkbox  all Selection, non-all Selection
* frm : form name,  obj : object name, allobj : all selection checkbox name
* <input type=checkbox name="allPagerBox" onClick="allSelect(this.form, 'pager_send_id', allPagerBox)">
* <input type=checkbox name="pager_send_id" value="user01"> 
*
*/
    function allSelect(frm, obj, allobj)
    {
        var trk=0;
        for (var i=0;i<frm.elements.length;i++)
        {
            var e = frm.elements[i];
            if(e.name != 'checkbox' && e.name == obj) {
                e.checked = allobj.checked;
            }
        }
    }

    function gogogo(form, sel){
        var msg = "";
  
            if(form.seq_no==undefined){
                //alert("<mfmt:message bundle="common.scripts" key="script.report.marking.notfound"/>");
                maf.alert('common.scripts','script.report.marking.notfound');
            }else if(CheckboxCheck(form.seq_no) == true){
                //alert("<mfmt:message bundle="common.scripts" key="script.report.marking.notselect"/>");
                maf.alert('common.scripts','script.report.marking.notselect');
            }else{
                form.cmd.value='update';
                form.submit();
            }
        
    }
    
    function CheckboxCheck(f){
        var i, flag=true;
        

        if(f.length!=undefined){
            for (i=0; i<f.length; i++) {
                if (f[i].checked == true) {
                    flag = false;
                }
            }

        }else if(f.value!=undefined){
            if(f.checked==true){
                flag = false;
            }
        }
        
        if (flag) {
            return true;
        } else {
            return false;
        }
    }

    function checkForm(form) {
        sel_idx = form.liketype.selectedIndex;
        if(sel_idx > 0 && form.keyword.value=='') {
            //alert("<mfmt:message bundle="common.scripts" key="script.alert.sel0"/>");
            maf.alert('common.scripts','script.alert.sel0');
            form.keyword.focus();
            return;
        }
        form.submit();
    }

    function jsAllchk(chk) {
        if (checkform.checkboxAll.checked == true) {
            for (var i=0; i<chk.length;i++)
                if (chk[i].type == "checkbox" && chk[i].checked == false)
                    chk[i].checked = true;    
        }else {
            for (var i=0; i<chk.length;i++)
                if (chk[i].type == "checkbox" && chk[i].checked == true)
                    chk[i].checked = false;
        }
    }
    

   
-->
</script>

<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
        <td><div class="searchContainer">
            <h1><mfmt:message bundle="common" key="searchtitle"/></h1>
            <mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this,'list');return false; ">
            <mf:input type="hidden" name="LISTOP" value="${LISTOP.serializeUrl}"/>
            <input type="image" id="dummy" width="0" height="0" border="0" class="hidden"/>
            <input type="hidden" name="cmd" value="list"/>      
            <mf:input type="hidden" name="leccode" value="${leccode}" />
            <mf:input type="hidden" name="itm_id" value="${item.itm_id}" />
                <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
                <tr>
                    <th width="20%"><mfmt:message bundle="table.wlb_lec_chp" key="itm_title"/></th>
                    <td width="80%" bgcolor="#ffffff"><mh:out value="${item.itm_title}" td="true" /></td>
                 </tr>
                <tr>
                    <th width="20%"><mfmt:message bundle="table.wlb_lec_chp" key="off_date_info"/></th>
                    <td width="80%" bgcolor="#ffffff"><mh:out value="${item.off_date_info}" td="true" /></td>
                 </tr>
                <tr>
                    <th width="20%"><mfmt:message bundle="table.wlb_lec_chp" key="off_place"/></th>
                    <td width="80%" bgcolor="#ffffff"><mh:out value="${item.off_place}" td="true" /></td>
                 </tr>
               </table>
            </div>   
        </td>
    </tr>
    <tr>
        <td><div class="listContainer">
        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
        <thead>
        <tr>
            <th><input type="checkbox" name="checkboxAll" 
                                value="allcode"  class="checkbox" 
                                onclick="allChekboxToggle(this,'myform','seq_no')"></th>
            <th># </th>
            <th><mf:header name="userid"/></th>
            <th><mfmt:message bundle="table.wlb_off_req" key="usernm"/></th>
            <th><mf:header name="lecnumb"/></th>
            
            <th><mf:header name="off_score"/></th>
            <th><mf:header name="req_stat"/></th>                     
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${list}" varStatus="status">
        <tr >
                <td align="center" ><mf:input type="checkbox" name="seq_no" value="${item.userid}#${item.lecnumb}" /></td>
                <td align="center" ><mh:out value="${status.count}"/></td>
                <td ><mh:out value="${item.userid}" td="true" /></td>
                <td ><mh:out value="${item.nm}" td="true" /></td>
                <td align="center" ><mh:out value="${item.lecnumb}" td="true" /></td>
                
                <td align="center" ><mf:input type="text" name="off_score" value="${item.off_score}" size="5" option="number" /></td>
                <td align="center" ><mh:out value="${item.req_stat}" codeGroup="REQ_STAT" td="true"/></td>
        </tr>
        </c:forEach>
        </tbody>
        </table></div>
        </td>
    </mf:form>    
    </tr>
    <tr>
        <td align="center">
            <mf:button bundle="table.wlb_off_req" key="btn.score_insert" onclick="updateScore()" icon="icon_add"/>
            <mf:button bundle="button" key="goList" onclick="goList()" icon="icon_list"/><br>
    </tr>
</table>
