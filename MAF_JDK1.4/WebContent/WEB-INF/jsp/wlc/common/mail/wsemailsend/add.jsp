<%@ page contentType="text/html; charset=utf-8"%>     
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>    

<%@ page language="java" %> 
<%@ page import="java.util.*" %>
<%
   ArrayList ar = (ArrayList)request.getAttribute("ar");
%>
<script language="javascript" src='<c:url value="/js/lib.validate.js"/>'></script>
<script language="javascript" src='<c:url value="/js/mafAjax.js"/>'></script>
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit=" frmSubmit('myform');return false;" enctype="multipart/form-data">
  <input type="image" value="test" width="0" height="0" border="0" class="hidden">
  <mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}"/>
  <input type="hidden" name="cmd" value="add">
  <mf:input type="hidden" name="leccode" value="${leccode}" />
  <input type="hidden" name="tmplt_id" value="">
<!-- 
<table border="0" cellpadding="2" cellspacing="0" class="view" width="90%"> 
    <tr>
        <th class="view" align="left" colspan=4><mfmt:message bundle="common.message"  key="noti.mail_tmplt.msg1"/></th>
    </tr>
</table>
<br>    
-->
<table border="0" cellpadding="2" cellspacing="0" class="view" width="90%" style="padding-left:10px;padding-right:10px"> 
    <tr>
        <th class="view" align="center"><mfmt:message bundle="table.wse_mail_send"  key="send"/><span id='lbl_required'>*</span></th>
        <td class="view" bgcolor="#FFFFFF" colspan=3>
        <mf:input type="text" name="from_id" required="true" value="${email}" />
        </td>
    </tr>
    <tr style="display:''" id="sub_1">
        <th class="view" align="center"><mfmt:message bundle="table.wse_mail_send"  key="receive"/><span id='lbl_required'>*</span></th>
        <td class="view" bgcolor="#FFFFFF" colspan=3>
        <textarea name="e" style=width:100% rows="2" readonly><mh:out value="${userinfo}" td="true" /></textarea>
        </td>
    </tr>
    <tr>
        <th class="view" align="center"><mfmt:message bundle="table.wse_mail_send"  key="mail_cc"/></th>
        <td class="view" bgcolor="#FFFFFF" colspan=3>
        <input type="text" name="mail_cc" style=width:100% >
        </td>
    </tr>
    <tr>
        <th class="view" align="center"><mfmt:message bundle="table.wse_mail_send"  key="mail_bcc"/></th>
        <td class="view" bgcolor="#FFFFFF" colspan=3>
        <input type="text" name="mail_bcc" style=width:100% >
        </td>
    </tr>
    <tr>
        <th class="view" align="center"><mfmt:message bundle="table.wse_mail_send"  key="mail_title"/><span id='lbl_required'>*</span></th>
        <td class="view" bgcolor="#FFFFFF" colspan=3>
        <input type="text" name="mail_title" style=width:100% required>
        </td>
    </tr>
    <tr>
        <th class="view" align="center" width="20%"><mfmt:message bundle="table.wse_mail_send"  key="mail_format"/></th>
        <td class="view" bgcolor="#FFFFFF" width="30%">
            <input style='border:none;' type='radio' name='mail_format' value='H'> <mfmt:message bundle="common" key="sel.mail_send.html"/>
            <input style='border:none;' type='radio' name='mail_format' value='T' checked> <mfmt:message bundle="common" key="sel.mail_send.text"/>
        </td>
        <th class="view" align="center" width="20%"><mfmt:message bundle="table.wse_mail_send"  key="mail_type"/></th>
        <td class="view" bgcolor="#FFFFFF" width="30%">
            <input style='border:none;' type='radio' name='mail_type' value='A'> <mfmt:message bundle="table.wse_mail_history" key="select.wse_mail_history.mail_type.alert"/>
            <input style='border:none;' type='radio' name='mail_type' value='I' checked > <mfmt:message bundle="table.wse_mail_history" key="select.wse_mail_history.mail_type.info"/>
        </td>
    </tr>    
    <tr>
        <td class="view" align="left" colspan=4 align="left">
        
        <mfmt:message bundle="common.message" key="noti.mail.member_nm"/><br>
        <mfmt:message bundle="common.message" key="noti.mail.member_id"/><br>
        <mfmt:message bundle="common.message" key="noti.mail.lecname"/><br>
        <mfmt:message bundle="common.message" key="noti.mail.leccode"/>
        </td>
    </tr>
    <tr>
        <th class="view" align="center"><mfmt:message bundle="table.wse_mail_send"  key="mail_text"/><span id='lbl_required'>*</span></th>
        <td class="view" bgcolor="#FFFFFF" colspan=3>
        <textarea name="mail_text" style=width:100% rows="10" required></textarea>
        </td>
    </tr>
        <%
            if(ar != null && ar.size() > 0) {
                for(int i=0; i< ar.size(); i++) {
                    String[] info = (String[])ar.get(i);
        %>
            <input type=hidden name="usermail" value="<%=info[0]%>#<%=info[1]%>#<%=info[2]%>">
        <%
                }
            }
        %>

</table>
</mf:form>
<br>  
<table border="0" cellpadding="0" cellspacing="0" width="90%" style="padding-left:10px;padding-right:10px"> 
    <tr>
        <td colspan="4" align="right">
        <mf:button bundle="button" key="sendmail" onclick="doWrite()"/>   
        <mf:button bundle="button" key="close" onclick="javascript:self.close()"/></td>
        </td>
    </tr>
</table>
<script language="javascript"   >
<!--
function frmSubmit(frm) {
    if(validate(frm)) {
        frm.cmd.value="sendmail";
        return true;
    }   else {
        return false;
    }
}

function doWrite(){
    var frm = getObject("myform");
    if(validate(frm))   {
        frm.cmd.value = "sendmail";
        frm.submit();
    } else {
        return;
    }
}
    
// templete file search
function changeTmplt(form) {
    if( form.tmplt_id.value != '') {
        var pageName = '<mh:out value="${control_action}" td="true" />';
        var paramStr = 'tmplt_id=' + form.tmplt_id.value + '&cmd=change';
        var ret = CallServer(pageName, paramStr);
        
        if(ret != '') {
            ret = ret.substring(ret.indexOf("<html"), ret.length);
            
            form.mail_text.value = ret;
            form.mail_text.focus();
        }
    }    
}        

function showhide(what, onOff)
{
    if(onOff == 'on')
        document.all["sub_1"].style.display ='';
    else
        document.all["sub_1"].style.display ='none';
}
//-->
</script>
<script language="javascript"   >
<!--
var xmlHttp;
function CallServer(pageName, paramStr) {
    xmlHttp = newXMLHttpRequest();
    xmlHttp.onreadystatechange = handleStateChange;
    xmlHttp.open("POST", pageName, false);
    xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xmlHttp.send(paramStr);

    var txtStr = xmlHttp.responseText;
    return txtStr;
}
    
function handleStateChange() {
    if(xmlHttp.readyState == 4) {
        if(xmlHttp.status == 200) {
            //alert("The server replied with: " + xmlHttp.responseText);
        }
    }
}
//-->
</script>
