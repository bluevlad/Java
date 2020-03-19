<%@ page contentType="text/html; charset=euc-kr"%>
<script language="javascript"   src="${CPATH}/js/lib.validate.js"></script>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<form action="${control_action}" method="post" name="myform" id="myform" >
    <input type="hidden" size="200" name="${mrt:mvcListOp()}" value="${LISTOP.serializeUrl}">
    <input type="hidden" name="miv_page" value="1">
    <input type="hidden" name="${mrt:mvcCmd()}" value="list">
    <input type="hidden" name="userid" value="">
    <input type="hidden" name="leccode" value="${mhtml:nvl(leccode,'')}">
    <tr>
        <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
            <tr>
                <th class="search" width=10%><mfmt:message bundle="table.Wlbchpprg" key="wlb_chp_prg.req_stat"/></th>
                <td class="search" align=left colspan=4><select name="req_stat" hname='<mfmt:message bundle="common" key="search"/>'>
                    <option value=""><mfmt:message bundle="common" key="search.all"/></option>
                    <mform:HtmlCodeOptions groupCd="REQ_STAT1" value="${LISTOP.ht.req_stat}"/>
                    </select></td>
                <th class="search" width=10%><mfmt:message bundle="table.Wlbchpprg" key="wlb_chp_prg.rstflag"/></th>
                <td class="search" align=left colspan=4><select name="rstflag" hname='<mfmt:message bundle="common" key="search"/>'>
                    <option value=""><mfmt:message bundle="common" key="search.all"/></option>
                    <mform:HtmlCodeOptions groupCd="GRADING_YN" value="${LISTOP.ht.rstflag}"/>
                    </select></td>
            </tr>
            <tr>
                <th class="search" width=10%><mfmt:message bundle="common" key="search"/></th>
                <td class="search" align=left colspan=9><select name="schtype" hname='<mfmt:message bundle="common" key="search"/>' >
                    <option value=""><mfmt:message bundle="common" key="search.all"/></option>
                    <option value="usrid" ${(LISTOP.ht.schtype=='usrid')?'selected':''}><mfmt:message bundle="table.Wlbchpprg" key="wlb_chp_prg.userid"/></option>
                    <option value="usernm" ${(LISTOP.ht.schtype=='usernm')?'selected':''}><mfmt:message bundle="table.Wlbchpprg" key="wlb_chp_prg.usernm"/></option>
                    </select><input type="text" name="keyword" value="${LISTOP.ht.keyword}" ></td>
             </tr>
             <tr>
                <td colspan="10" align="right">
                <a href="javascript:doSearch()"><mfmt:button bundle="button" key="button.search"/></a>
                </td>
             </tr>
        </table>
        </td>
    </tr>
    <tr>
        <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list">
        <thead>
        <tr>
          <th class="list BL" width="20"><input type="checkbox" name="checkboxAll" 
                                value="allcode"  class="checkbox" 
                                onclick="allChekboxToggle(this,'myform','userid_chk')"></th>
          <th class="list"><mfmt:message bundle="table.Wlbchpprg" key="wlb_chp_prg.usernm"/><mform:THSort id='usernm'/></th>
          <th class="list"><mfmt:message bundle="table.Wlbchpprg" key="wlb_chp_prg.userid"/><mform:THSort id='userid'/></th>
          <th class="list"><mfmt:message bundle="table.Wlbchpprg" key="wlb_chp_prg.lecnumb"/><mform:THSort id='lecnumb'/></th>
          <th class="list"><mfmt:message bundle="table.Wlaratmst" key="wla_rat_mst.ratadd"/><mform:THSort id='etcscore'/></th>
          <th class="list BR">
          <mfmt:message bundle="table.Wlbchpprg" key="wlb_chp_prg.mail"/><input type="checkbox" name="allMailbox" value="stdcodes" onClick="allSelect(document.myform,'email',allMailbox);">
          <mfmt:message bundle="table.Wlbchpprg" key="wlb_chp_prg.msg"/><input type="checkbox" name="allMsglbox" value="stdcodes" onClick="allSelect(document.myform,'pager_send_id',allMsglbox);"></th>
        </tr>
        </thead>
        <tbody>
     <c:forEach var="item" items="${navigator.list}" varStatus="status">
        <tr >
           <td class="list" align="center"><input type="checkbox" name="userid_chk" value="${item.userid}#${item.lecnumb}" class="checkbox"  hname="<mfmt:message bundle="common.scripts" key="script.alert.userid"/>"></td>
           <td class="list" align=center>${mhtml:null2nbsp(item.usernm)}</td>
           <td class="list" align=center>${mhtml:null2nbsp(item.usrid)}</td>
           <td class="list" align=center>${mhtml:null2nbsp(item.lecnumb)}</td>
           <td class="list" align=center><input type="text" name="${item.userid}_${item.lecnumb}_etcscore" value="${item.etcscore}" hname="<mfmt:message bundle="common.scripts" key="script.alert.info1"/>" size=5 option='number'></td>
           <td class="list" align=center>
           <input class="box1" type=checkbox name=email value="${mhtml:nvl(item.usermail,'')}#${mhtml:nvl(item.userid,'')}#${mhtml:nvl(item.usernm,'')}"> &nbsp; 
           <input class="box1" type=checkbox name=pager_send_id value="${mhtml:nvl(item.userid,'')}#${mhtml:nvl(item.usernm,'')}">
           </td>
         </tr>
    </c:forEach>
        </tbody>
        </table></td>
    </tr>
</form>    
    <tr>
        <td align="center"><a href="javascript:updateScore(document.myform);"><mfmt:button bundle="button" key="button.app.etcscore"/></a>
        <a href="javascript:callMail(document.myform);"><mfmt:button bundle="button" key="button.email.send"/></a>
        <a href="javascript:callPager(document.myform);"><mfmt:button bundle="button" key="button.pager.send"/></a><br>
            <jsp:include page="/layout/lib/navigator.jsp" flush="true"/>
        </td>
    </tr>
</table>
<SCRIPT LANGUAGE="JavaScript">
<!--
function doView(id){
    var frm = getObject("myform");
    if(frm) {
        frm.${mrt:mvcCmd()}.value = "eview";
        frm.reg_no.value = id;
        frm.submit();
    }
}

function doSearch() {
    var frm = getObject("myform");
    if(frm) {
        frm.${mrt:mvcCmd()}.value = "elist";
        frm.miv_page.value = 1;
        frm.submit();
    }     
}

function updateScore(frm) {
    var frm = getObject("myform");
    if(frm.userid_chk==undefined){
        alert("<mfmt:message bundle="common.scripts" key="script.report.marking.notfound"/>");
    }else if(CheckboxCheck(frm.userid_chk) == true){
        alert("<mfmt:message bundle="common.scripts" key="script.report.marking.notselect"/>");
    }else{
        frm.${mrt:mvcCmd()}.value='eupdate';
        frm.submit();
    }
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

function CheckboxCheck(f){
    var i, flag=true;
    
    // 여러개 체크했을 경우
    if(f.length!=undefined){
        for (i=0; i<f.length; i++) {
            if (f[i].checked == true) {
                flag = false;
            }
        }
    // 1개만 체크 했을 경우
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
        alert("<mfmt:message bundle="common.scripts" key="script.alert.sel0"/>");
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
 * 입력값에 숫자만 있는지 체크
 */
function isNumber(input) {
    var chars = "0123456789";
    return containsCharsOnly(input,chars);
}

/**
 * 입력값을 모두 지운다
 */
function setEmpty() {
    var objEv = event.srcElement;
    objEv.value="";
}

function checkNumber(input) {
    if(!isNumber(input)) {
        alert("<mfmt:message bundle="common.scripts" key="script.alert.only.number"/>");
        setEmpty();
    }
}

function checkFloat(input) {
    if((parseFloat(input.value)<0.0 || parseFloat(input.value)>100.0)){
        alert("<mfmt:message bundle="common.scripts" key="script.alert.score.limit.fail"/>");
        setEmpty();                
    }
}

function checkNumber2(input) {
    if(!isNumber(input)) {
        alert("<mfmt:message bundle="common.scripts" key="script.alert.only.number"/>");
        setEmpty();
    }else if((parseInt(input.value)<0 || parseInt(input.value)>100)){
        alert("<mfmt:message bundle="common.scripts" key="script.alert.score.limit.fail"/>");
        setEmpty();                
    }
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

// 쪽지발송선택확인
function callPager(form) {
    var retVal=0;
    for(i=0;i<form.elements.length;i++)   {
         if(form.elements[i].checked==true) {
            retVal=1;
            break;
        }
    }
    if(retVal==0)  {
        alert("<mfmt:message bundle="common.scripts" key="script.alert.not.select"/>");
    } else {
        var win = window.open("about:blank","Pager", "width=670,height=400,left=300,top=100, menubar=no,directories=no,resizable=no,status=no,scrollbars=yes");
        if(win != null) {
            form.action ="${CPATH}/pager/default.do";
            form.target = 'Pager';
            form.cmd.value="grpMsgWrite";
            form.submit();
        }
    }
}   

// 메일발송선택확인
function callMail(form) {
    var retVal=0;
    for(i=0;i<form.elements.length;i++)   {
         if(form.elements[i].name=="email" && form.elements[i].checked==true) {
            retVal=1;
            break;
        }
    }
    if(retVal==0)  {
        alert("<mfmt:message bundle="common.scripts" key="script.alert.not.select"/>");
    } else {
        var win = window.open("about:blank","mail_win", "resizable=yes,width=800,height=600,top=0,left=0,scrollbars=no,location=no,status=yes");
        if(win != null) {
            form.action ="${CPATH}/wlc.common/mailsend.do";
            form.target = 'mail_win';
            form.cmd.value="add";
            //document.myform.enctype = "application/x-www-form-urlencoded";
            form.submit();
        }
    }
}
-->
</script>
