<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<script language="javascript" src='<c:url value="/js/lib.validate.js"/>'></script>
<SCRIPT LANGUAGE="JavaScript">
<!--
function doView(id, req){
    var frm = getObject("myform");
    if(frm) {
        frm.cmd.value = "view";
        frm.userid.value = id;
        frm.reqnumb.value = req;
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
        
        maf.alert('common.scripts','script.alert.sel0');
        form.keyword.focus();
        return;
    }

    form.submit();
}

function checkNumber(input) {
    if(!isNumber(input)) {
        
        maf.alert('common.scripts','script.alert.only.number');
        setEmpty();
    }
}

function checkNumber2(input) {
    if(!isNumber(input)) {
        
        maf.alert('common.scripts','script.alert.only.number');
        setEmpty();
    }else if((parseInt(input.value)<0 || parseInt(input.value)>100)){
        
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

//
function callMail(form) {
    var retVal=0;
    for(i=0;i<form.elements.length;i++)   {
         if(form.elements[i].name=="email" && form.elements[i].checked==true) {
            retVal=1;
            break;
        }
    }
    if(retVal==0)  {
        
        maf.alert('common.scripts','script.alert.not.select');
    } else {
        var win = window.open("about:blank","mail_win", "resizable=yes,width=800,height=600,top=0,left=0,scrollbars=no,location=no,status=yes");
        if(win != null) {
            form.action ="<c:url value="/wlc.common/mailsend.do" />";
            form.target = 'mail_win';
            form.cmd.value="add";
            
            form.submit();
        }
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
        <input type="hidden" name="userid" value="">
        <input type="hidden" name="reqnumb" value="">
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
                <th width="10%">#</th>
                <th width="10%"><mf:header name="userid"/></th>
                <th width="15%"><mfmt:message bundle="table.maf_user" key="nm"/></th>
                <th width="10%"><mf:header name="lecnumb"/></th>
                <th ><mfmt:message bundle="table.wla_rat_mst" key="jindo_page"/></th>
                <th ><mfmt:message bundle="table.wla_rat_mst" key="jindo_time"/></th>
                <th ><mfmt:message bundle="common" key="table.common.email"/><input type="checkbox" name="allMailbox" value="stdcodes" onClick="allSelect(document.myform,'email',allMailbox);"></th>
            </tr>
            </thead>
            <tbody>
     <c:forEach var="item" items="${navigator.list}" varStatus="status">
            <tr >
                <td align="center"><mh:listseq navigator="${navigator}" count="${status.count}"/></td>
                <td align=center><a href="javaScript:doView('<mh:out value="${item.userid}" td="true" />', '<mh:out value="${item.lecnumb}" td="true" />');"><mh:out value="${item.userid}" td="true" /></a></td>
                <td align=center><mh:out value="${item.usernm}" td="true" /></td>
                <td align=center><mh:out value="${item.lecnumb}" td="true" /></td>
                <td align=left>
                <c:if test="${item.jindo_page > 0.0}">
                    <img src="<mh:out value="${CPATH}"/>/maf_images/graph/graph10.gif" width="<mh:out value="${item.jindo_page}" td="true" />" height="12"/>
                </c:if><mh:out value="${item.jindo_page}" td="true" />%</td>
                <td align=left>
                <c:if test="${item.jindo_time > 0.0}">
                    <img src="<mh:out value="${CPATH}"/>/maf_images/graph/graph10.gif" width="<mh:out value="${item.jindo_time}" td="true" />" height="12"/>
                </c:if><mh:out value="${item.jindo_time}" td="true" />%</td>
                <td align=center>
                <mf:input type="checkbox" name="email" value="${item.usermail}#${item.userid}#${item.usernm}" />
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
        <mf:button bundle="button" key="button.email.send" onclick="callMail(document.myform)" icon="icon_add"/><br>
        <jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/></td>
    </tr>
</table>

