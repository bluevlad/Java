<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<SCRIPT LANGUAGE="JavaScript">
<!--    
    function doView(leccode, reqnumb, userid){
        var frm = getObject("myform");        
        if(frm) {
            frm.cmd.value = "view";            
            frm.leccode.value=leccode;            
            frm.reqnumb.value=reqnumb;            
            frm.userid.value=userid;            
            frm.submit();
        }
    }
    
    function doSearch() {
        var frm = getObject("myform");        
        if(frm) {
            frm.cmd.value = "list";
            frm.miv_page.value = 1;
            frm.submit();
        }     
    }
    
    function updateScore() {
        var frm = getObject("myform");
        gogogo(frm, 1);
        //frm.submit();
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

    if(sel==1){
        if(form.userid_chk==undefined){
            //alert("<mfmt:message bundle="common.scripts" key="script.report.marking.notfound"/>");
            maf.alert('common.scripts','script.report.marking.notfound');
        }else if(CheckboxCheck(form.userid_chk) == true){
            //alert("<mfmt:message bundle="common.scripts" key="script.report.marking.notselect"/>");
            maf.alert('common.scripts','script.report.marking.notselect');
        }else{
            form.cmd.value = "updateScores";
            form.submit();
        }
    }
}

function CheckboxCheck(f){
    var i, flag=true;
    
    //
    if(f.length!=undefined){
        for (i=0; i<f.length; i++) {
            if (f[i].checked == true) {
                flag = false;
            }
        }
    //
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

function containsCharsOnly(input,chars) {
    for (var inx = 0; inx < input.value.length; inx++) {
       if (chars.indexOf(input.value.charAt(inx)) == -1)
           return false;
    }
    return true;
}

/**
 * 
 */
function isNumber(input) {
    var chars = "0123456789";
    return containsCharsOnly(input,chars);
}

/**
 *
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

function sort(orderfield, orderby){
    myform.orderfield.value = orderfield;
    myform.orderby.value = orderby;
    myform.submit();   
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

function feedbac_how(form){
    var frm = getObject("myform");
    if(frm.feedback[0].checked==true){
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

<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
        <td><div class="searchContainer">
            <h1><mfmt:message bundle="common" key="searchtitle"/></h1>
            <mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this,'list');return false; ">
            <mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}"/>
            <input type="image" id="dummy" width="0" height="0" border="0" class="hidden"/>
            <input type="hidden" name="miv_page" value="1"/>
            <input type="hidden" name="cmd" value="list"/>
        
            <mf:input type="hidden" name="leccode" value="${leccode}"/>
            <input type='hidden' name='reqnumb' value=''>
            <input type='hidden' name='userid' value=''> 
            
                <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">                            
                <col width="15%"/>
                <col width="35%"/>
                <col width="15%"/>
                <col width="35%"/>

            <tr>
                <th ><mfmt:message bundle="common" key="table.name"/></th>
                <td ><mf:input type="text" name="sch_usernm" value="${LISTOP.ht.sch_usernm}"/></td>
                <th ><mfmt:message bundle="common" key="table.id"/></th>
                <td ><mf:input type="text" name="sch_userid" value="${LISTOP.ht.sch_userid}"/></td>
            </tr>                
            <tr>
                <th><mfmt:message bundle="common" key="search.report.marking"/></th>
                <td colspan="3"><select name="rstflag" >
                    <option value=""></option>
                    <mf:option value="Y" curValue="${LISTOP.ht.rstflag}">Y</mf:option>
                    <mf:option value="N" curValue="${LISTOP.ht.rstflag}">N</mf:option>
                    </select></td>                 
            </tr>
            </table>            
            <table border="0" cellspacing="0" cellpadding="2" class="searchBtn">
                <tr><td>
                    <mf:button onclick="doSearch()" bundle="button" key="search" icon="icon_search"/>
                </td></tr>
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
                                value="allcode"
                                onclick="allChekboxToggle(this,'myform','userid_chk')"></th>
            <th><mf:header name="userid"/> </th>
            <th><mf:header name="nm"/> </th>
            <th><mf:header name="lecnumb"/> </th>
            <th><mf:header name="tot_score"/> </th>
            <th><mf:header name="score10"/> </th>
            <th><mfmt:message bundle="table.wce_fnl_grd" key="final_score"/> </th>
            <th><mfmt:message bundle="table.wcc_lec_req" key="is_grad"/> </th>                        
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${navigator.list}" varStatus="status">
        <tr >
                <td align="center" ><mf:input type="checkbox" name="userid_chk" value="${item.userid}#${item.lecnumb}" /></td>
                <td ><a href="javaScript:doView(
                        '<mh:out value="${item.leccode}" td="true" />', 
                        '<mh:out value="${item.lecnumb}" td="true" />', 
                        '<mh:out value="${item.userid}" td="true" />')"/>
                        <mh:out value="${item.userid}" td="true" /></a></td>
                <td ><mh:out value="${item.usernm}" td="true" /></td>
                <td align="center" ><mh:out value="${item.lecnumb}" td="true" /></td>
                <td align="center" ><mh:out value="${item.sum_score}" td="true" /></td>
                <td align="center" ><mf:input size="4" maxlength="4" name="${item.userid}_${item.lecnumb}_addscore" value="${item.score10}" /></td>
                <td align="center" ><mh:out value="${item.tot_score}" td="true" /></td>
                <td align="center" >
                <c:if test="${item.tot_cnt != '0'}">
                <mh:out value="${item.is_grad}" td="true" />
               </c:if>
               <c:if test="${item.tot_cnt == '0'}">
               <mfmt:message bundle="common" key="table.notscoreing"/>
               </c:if>
                </td>
        </tr>
        </c:forEach>
        </tbody>
        </table></div>
        </td>
    </mf:form>    
    </tr>
    <tr>
        <td align="center">
            <mf:button bundle="button" key="button.final.score.marking" onclick="updateScore()" icon="icon_add"/><br/>
            <jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/></td>
    </tr>
</table>

