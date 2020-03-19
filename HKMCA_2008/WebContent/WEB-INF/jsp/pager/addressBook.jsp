<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<mf:form action="${control_action}" method="post" name="myform" id="myform">
<mf:input type="hidden" name="LISTOp" value="${LISTOP.serializeUrl}"/>
<mf:input type="hidden" name="miv_page" value="1"/>
<mf:input type="hidden" name="cmd" value="addressBook"/>
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
			<col width="50"/>
			<col width="100"/>
			<col width="*"/>
			<col width="100"/>
			<col width="100"/>
		    <tr>
		    	<th><input type="checkbox" name="checkboxAll" value="allcode"  class="checkbox" onclick="allChekboxToggle(this,'myform','id_chk')"></th>
				<th>No</th>
				<th><mf:header name="msg_rc_nm" sort='true'/></th>
				<th><mf:header name="msg_title" sort='true'/></th>
				<th><mf:header name="msg_dt" sort='true'/></th>
				<th><mf:header name="msg_read_dt" sort='true'/></th>
		    </tr>
		    <c:forEach var="item" items="${navigator.list}" varStatus="status">
			<tr>
				<td><mf:input type="checkbox" name="id_chk" value="${item.msg_no}"/></td>
				<td><a href="javaScript:doView( '${item.msg_no}')">${status.count}</a></td>
				<td>${mh:null2nbsp(item.msg_rc_nm)}&lt;${item.msg_rc_id}&gt;</td>
				<td><img src="${CPATH}/maf_images/msg/icon_${(empty item.msg_read_dt)?'close':'open'}mail.gif" border="0"><a href="javaScript:doView( '${item.msg_no}')">${mh:null2nbsp(item.msg_title)}</a></td>
				<td>${item.msg_dt}</td>
				<td>${item.msg_read_dt}</td>
	
			</tr>
		    </c:forEach>
	
		    </table>
	    </td>
    </tr>
	<tr>
	    <td align="center">
	    	<a href="javascript:doWrite()"><mf:button bundle="mafpager" key="btn.reply"/></a>
	    	<a href="javascript:doDelete()"><mf:button bundle="mafpager" key="btn.delete"/></a> <br>
    	</td>
    </tr>
</table>
</mf:form>
<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/>
<SCRIPT LANGUAGE="JavaScript">
<!--
	function doWrite(){
	    var frm = getObject("myform");
		if(frm) {
		    frm.cmd.value = "add";
		    frm.submit();
		}
	}
	function doDelete(){
	    var frm = getObject("myform");
	    if(frm) {
			var chkcnt = getSelectedCount("myform","id_chk");
			if(chkcnt == 0 ) {
				alert('선택된 쪽지가 없습니다');
			} else if (confirm(chkcnt+'개의 쪽지를 삭제하시겟습니까?')){
			    frm.cmd.value = "sndDelete"; 
			    frm.submit();
			}
		}
	}		
	function doView(msgid){
	    var frm = getObject("myform");
		if(frm) {
   		    frm.cmd.value = "msgView";
   		    frm.msg_no.value=msgid;
   		    frm.submit();
		}
	}
    function doSearch() {
	    var frm = getObject("myform");
			if(frm) {
    		    frm.cmd.value = "rcvList";
                frm.miv_page.value = 1;
    		    frm.submit();
			}     
    }
//-->
</SCRIPT>