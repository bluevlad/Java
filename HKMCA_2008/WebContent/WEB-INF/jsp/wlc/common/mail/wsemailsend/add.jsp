<%@ page contentType="text/html; charset=utf-8"%>     
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>    

<%@ page language="java" %> 
<%@ page import="java.util.*" %>

<%
   ArrayList ar = (ArrayList)request.getAttribute("ar");
%>

<script language="javascript" src='<c:url value="/js/jmfAjax.js"/>'></script>
<jf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit=" frmSubmit('myform');return false;" enctype="multipart/form-data">
<jf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}"/>
<jf:input type="hidden" name="cmd" value="add"/>
<jf:input type="hidden" name="lec_cd" value="${lec_cd}" />
<jf:input type="hidden" name="tmplt_id" value=""/>
<!-- 
<table border="0" cellpadding="2" cellspacing="0" class="view" width="90%"> 
    <tr>
        <th class="view" align="left" colspan=4><mfmt:message bundle="common.message"  key="noti.mail_tmplt.msg1"/></th>
    </tr>
</table>
<br>    
-->
<table width="100%" border="0" cellpadding="2" cellspacing="0" class="view"> 
    <col width="20%">
    <col width="30%">
    <col width="20%">
    <col width="30%">
    <tr>
        <th align="center"><jfmt:message bundle="table.mail_send"  key="send"/><span id='lbl_required'>*</span></th>
        <td colspan=3><jf:input type="text" name="from_id" required="true" value="${email}" /></td>
    </tr>
    <tr style="display:''" id="sub_1">
        <th align="center"><jfmt:message bundle="table.mail_send"  key="receive"/><span id='lbl_required'>*</span></th>
        <td colspan=3><textarea name="e" style=width:100% rows="2" readonly><jh:out value="${userinfo}" td="true" /></textarea></td>
    </tr>
    <tr>
        <th align="center"><jfmt:message bundle="table.mail_send" key="mail_cc"/></th>
        <td colspan=3><jf:input type="text" name="mail_cc" value=""/></td>
    </tr>
    <tr>
        <th><jfmt:message bundle="table.mail_send"  key="mail_bcc"/></th>
        <td colspan=3><jf:input type="text" name="mail_bcc" value="" /></td>
    </tr>
    <tr>
        <th><jfmt:message bundle="table.mail_send"  key="mail_title"/><span id='lbl_required'>*</span></th>
        <td colspan=3><jf:input type="text" name="mail_title" required="true" value=""/></td>
    </tr>
    <tr>
        <th><jfmt:message bundle="table.mail_send"  key="mail_format"/></th>
        <td>
            <input style='border:none;' type='radio' name='mail_format' value='H'><jfmt:message bundle="common" key="sel.mail_send.html"/>
            <input style='border:none;' type='radio' name='mail_format' value='T' checked> <jfmt:message bundle="common" key="sel.mail_send.text"/>
        </td>
        <th><jfmt:message bundle="table.mail_send"  key="mail_type"/></th>
        <td>
            <input style='border:none;' type='radio' name='mail_type' value='A'><jfmt:message bundle="table.mail_history" key="select.wse_mail_history.mail_type.alert"/>
            <input style='border:none;' type='radio' name='mail_type' value='I' checked ><jfmt:message bundle="table.mail_history" key="select.wse_mail_history.mail_type.info"/>
        </td>
    </tr>    
    <tr>
        <td colspan=4>
	        <jfmt:message bundle="common.message" key="noti.mail.member_nm"/><br>
	        <jfmt:message bundle="common.message" key="noti.mail.member_id"/><br>
        </td>
    </tr>
    <tr>
        <th><jfmt:message bundle="table.mail_send" key="mail_text"/><span id='lbl_required'>*</span></th>
        <td colspan=3><textarea name="mail_text" style=width:100% rows="10" required></textarea></td>
    </tr>
        <%
            if(ar != null && ar.size() > 0) {
                for(int i=0; i< ar.size(); i++) {
                    String[] info = (String[])ar.get(i);
        %>
            <input type=hidden name="usermail" value="<%=info[0]%>#<%=info[1]%>#<%=info[2]%>" />
        <%
                }
            }
        %>

</table>
</jf:form>
<br>  
<table width="100%" border="0" cellpadding="0" cellspacing="0"> 
    <tr>
        <td align="right"><jf:button bundle="button" key="sendmail" onclick="doWrite()"/></td>
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
