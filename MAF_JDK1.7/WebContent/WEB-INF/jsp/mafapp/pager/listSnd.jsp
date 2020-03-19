<%@ page contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="mhtml" uri="/WEB-INF/tld/mi-html-util.tld" %>

<SCRIPT LANGUAGE="JavaScript">
<!--
	function doWrite(){
	    var frm = getObject("myform");
		if(frm) {
		    frm.${mrt:mvcCmd()}.value = "add";
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
			    frm.${mrt:mvcCmd()}.value = "deleteSnd"; 
			    frm.submit();
			}
		}
	}		
	function doView(msgid){
	    var frm = getObject("myform");
		if(frm) {
   		    frm.${mrt:mvcCmd()}.value = "msgViewSnd";
   		    frm.msg_no.value=msgid;
   		    frm.submit();
		}
	}
    function doSearch() {
	    var frm = getObject("myform");
			if(frm) {
    		    frm.${mrt:mvcCmd()}.value = "listSnd";
                frm.miv_page.value = 1;
    		    frm.submit();
			}     
    }
//-->
</SCRIPT>

<table width="100%" border="0" cellspacing="0" cellpadding="2">
	<form action="${control_action}" method="post" name="myform" id="myform">
	<input type="hidden" size="200" name="${mrt:mvcListOp()}" value="${LISTOP.serializeUrl}">
	<input type="hidden" name="miv_page" value="1">
	<input type="hidden" name="${mrt:mvcCmd()}" value="listSnd">
	<input type="hidden" name="msg_no" value="">
	
	<tr>
        <td>
	        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
	            <tr>
	                <th class="search"><select name="search_type" >
	                    <option value="nm" ${LISTOP.ht.search_type=='nm'?'selected':''}>사용자이름</option>
	                    <option value="userid" ${LISTOP.ht.search_type=='userid'?'selected':''}>사용자ID</option>
	                </SELECT></th>
	                <td class="search"><input type="text" name="search_txt" value="${LISTOP.ht.search_txt}"></td>
					<td class="search center" rowspan="2"><mform:submit bundle="button" key="search"/></td>
	             </tr>
	            <tr>
	                <th class="search"><mform:header name="msg_title"/></th>
	                <td class="search" ><input type="text" name="msg_title" value="${LISTOP.ht.msg_title}"></td>
					
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
		<col width="1*"/>
		<col width="100"/>
		<col width="100"/>
	    <tr>
	    	<th class="list BL" ><input type="checkbox" name="checkboxAll" 
    							value="allcode"  class="checkbox" 
    							onclick="allChekboxToggle(this,'myform','id_chk')"></th>
			<th class="list ">No</th>
			<th class="list" nowrap><mform:header name="msg_rcv_nm"/><mform:THSort id='msg_rcv_nm'/></th>
			<th class="list" nowrap><mform:header name="msg_title"/><mform:THSort id='msg_title'/></th>
			<th class="list" nowrap><mform:header name="msg_dt"/><mform:THSort id='msg_dt'/></th>
			<th class="list BR" nowrap><mform:header name="msg_read_dt"/><mform:THSort id='msg_read_dt'/></th>
	    </tr>
	    <c:forEach var="item" items="${navigator.list}" varStatus="status">
		<tr onMouseOver="tr_over(this)" onMouseOut="tr_out(this)">
			<td class="list BL"><input type="checkbox" name="id_chk" value="${item.msg_no}" class="checkbox"></td>
			<td class="list  center"><a href="javaScript:doView( '${item.msg_no}')">${mrt:getListSeq(navigator, status.count)}</a></td>
			<td class="list center">${mhtml:null2nbsp(item.msg_rcv_nm)}&lt;${item.msg_rcv_userid}&gt;</td>
			<td class="list"><img src="${CPATH}/maf_images/msg/icon_${(empty item.msg_read_dt)?'close':'open'}mail.gif" border="0"><a href="javaScript:doView( '${item.msg_no}')">${mhtml:null2nbsp(item.msg_title)}</a></td>
			<td class="list center">${mfmt:autoDateTime(item.msg_dt)}</td>
			<td class="list BR center">${mfmt:autoDateTime(item.msg_read_dt)}</td>

		</tr>
	    </c:forEach>

	    </table>
	    </td>
    </tr>
    </form>
	<tr>
	    <td align="center">
	    	<a href="javascript:doDelete()"><mfmt:button bundle="mafpager" key="btn.delete"/></a> <br>
	    	<jsp:include page="/layout/lib/navigator.jsp" flush="true"/>
    	</td>
    </tr>
</table>


		