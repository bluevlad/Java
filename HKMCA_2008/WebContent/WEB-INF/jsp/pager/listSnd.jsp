<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<SCRIPT LANGUAGE="JavaScript">
<!--
function doDelete(){
    var frm = getObject("myform");
    if(frm) {
        var chkcnt = getSelectedCount("myform","id_chk");
        if(chkcnt == 0 ) {
            alert('선택된 쪽지가 없습니다');
        } else if (confirm(chkcnt+'개의 쪽지를 삭제하시겟습니까?')){
            frm.cmd.value = "deleteRcv"; 
            frm.submit();
        }
    }
}   
function doView(msgid){
    var frm = getObject("myform");
    if(frm) {
        frm.cmd.value = "msgViewSnd";
        frm.msg_no.value = msgid;
        frm.submit();
    }
}
function doSearch() {
    var frm = getObject("myform");
    if(frm) {
        frm.cmd.value = "listRcv";
        frm.miv_page.value = 1;
        frm.submit();
    }     
}
//-->
</SCRIPT>

<mf:form action="${control_action}" method="post" name="myform" id="myform">
<mf:input type="hidden" name="LISTOP" value="${LISTOP.serializeUrl}"/>
<mf:input type="hidden" name="miv_page" value="1"/>
<mf:input type="hidden" name="cmd" value="listRcv"/>
<mf:input type="hidden" name="msg_no" value=""/>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
        <td>
            <table width="100%" border="0" cellspacing="0" cellpadding="2">
               <col width="15%">
               <col width="25%">
               <col width="15%">
               <col width="25%">
               <col width="10%">
                <tr>
                    <th><mf:select name="search_type" codeGroup="PGR.SCH_TYPE" curValue="${LISTOP.ht.search_type}"/></th>
                    <td><mf:input type="text" name="search_txt" value="${LISTOP.ht.search_txt}"/></td>
                    <th><mf:header name="msg_title"/></th>
                    <td><mf:input type="text" name="msg_title" value="${LISTOP.ht.msg_title}"/></td>
                    <td><mf:button onclick="doSearch()" bundle="button" key="search" icon="icon_search" /></td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td>
            <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list">
                <col width="10"/>
                <col width="30"/>
                <col width="150"/>
                <col width="*"/>
                <col width="100"/>
                <col width="100"/>
                <tr>
                    <th><input type="checkbox" name="checkboxAll" value="allcode"  class="checkbox" onclick="allChekboxToggle(this,'myform','id_chk')"></th>
                    <th>No</th>
                    <th><mf:header name="msg_rcv_usn" sort="true"/></th>
                    <th><mf:header name="msg_title" sort="true"/></th>
                    <th><mf:header name="file_name"/></th>
                    <th><mf:header name="msg_dt" sort="true"/></th>
                </tr>
                <c:forEach var="item" items="${navigator.list}" varStatus="status">
                <tr>
                    <td align="center"><input type="checkbox" name="id_chk" value="${item.msg_no}" class="checkbox"></td>
                    <td align="center"><a href="javaScript:doView('<mh:out value="${item.msg_no}"/>')"><mh:listseq navigator="${navigator}" count="${status.count}" /></a></td>
                    <td align="center"><mh:out value="${item.msg_snd_nm}"/></td>
                    <td align="center"><img src='/maf_images/msg/icon_<mh:out value="${item.msg_open}"/>mail.gif'>&nbsp;<a href="javaScript:doView('<mh:out value="${item.msg_no}"/>')"><mh:out value="${item.msg_title}"/></a></td>
                    <td align="center"><mh:out value="${item.file_name}" td="true"/></td>
                    <td align="center"><mh:out value="${item.msg_dt}" format="fulldate"/></td>
                </tr>
                </c:forEach>
            </table>
        </td>
    </tr>
    <tr>
        <td align="right"><a href="javascript:doDelete()"><mf:button bundle="button" key="pager.delete"/></a></td>
    </tr>
</table>
</mf:form>
<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/>