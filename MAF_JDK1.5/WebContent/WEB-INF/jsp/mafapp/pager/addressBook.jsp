<%@ page contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="mhtml" uri="/WEB-INF/tld/mi-html-util.tld" %>

<table width="100%" border="0" cellspacing="0" cellpadding="2">
	<form action="${control_action}" method="post" name="myform" id="myform">
	<input type="hidden" name="${mrt:mvcListOp()}" value="${LISTOP.serializeUrl}">
	<input type="hidden" name="miv_page" value="1">
	<input type="hidden" name="${mrt:mvcCmd()}" value="addressBook">
	
	<tr>
        <td>
	        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
	            <tr>
	                <th class="search"><mfmt:message bundle="mafpager" key="form.msg_grp_nm"/></th>
	                <td class="search"><select name="msg_grp_id" hname="그룹" >
	                    <option value="">전체</option>
	                    <c:forEach var="item" items="${groupList}" varStatus="status">
	                    	<option value="${item.msg_grp_id}" ${(LISTOP.ht.msg_grp_id==item.msg_grp_id)?'selected':''}>${item.msg_grp_nm}</option>
	                    </c:forEach>
	                    </select></td>
	                <th class="search"><select name="search_type" >
	                    <option value="user_nm" ${LISTOP.ht.search_type=='user_nm'?'selected':''}>사용자이름</option>
	                    <option value="user_id" ${LISTOP.ht.search_type=='user_id'?'selected':''}>사용자ID</option>
	                </SELECT></th>
	                <td class="search"><input type="text" name="search_txt" value="${LISTOP.ht.search_txt}"></td>
	                <td class="search center" colspan="3"><mform:submit bundle="button" key="search"/></td>
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
		<col width="1*"/>
		<col width="100"/>
		<col width="100"/>
	    <tr>
	    	<th class="list BL" ><input type="checkbox" name="checkboxAll" 
    							value="allcode"  class="checkbox" 
    							onclick="allChekboxToggle(this,'myform','id_chk')"></th>
			<th class="list ">No</th>
			<th class="list" nowrap><mform:header name="msg_rc_nm"/><mform:THSort id='msg_rc_nm'/></th>
			<th class="list" nowrap><mform:header name="msg_title"/><mform:THSort id='msg_title'/></th>
			<th class="list" nowrap><mform:header name="msg_dt"/><mform:THSort id='msg_dt'/></th>
			<th class="list BR" nowrap><mform:header name="msg_read_dt"/><mform:THSort id='msg_read_dt'/></th>
	    </tr>
	    <c:forEach var="item" items="${navigator.list}" varStatus="status">
		<tr onMouseOver="tr_over(this)" onMouseOut="tr_out(this)">
			<td class="list BL"><input type="checkbox" name="id_chk" value="${item.msg_no}" class="checkbox"></td>
			<td class="list  center"><a href="javaScript:doView( '${item.msg_no}')">${mrt:getListSeq(navigator, status.count)}</a></td>
			<td class="list center">${mhtml:null2nbsp(item.msg_rc_nm)}&lt;${item.msg_rc_id}&gt;</td>
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
	    	<a href="javascript:doWrite()"><mfmt:button bundle="mafpager" key="btn.reply"/></a>
	    	<a href="javascript:doDelete()"><mfmt:button bundle="mafpager" key="btn.delete"/></a> <br>
	    	<jsp:include page="/layout/lib/navigator.jsp" flush="true"/>
    	</td>
    </tr>
</table>

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
			    frm.${mrt:mvcCmd()}.value = "sndDelete"; 
			    frm.submit();
			}
		}
	}		
	function doView(msgid){
	    var frm = getObject("myform");
		if(frm) {
   		    frm.${mrt:mvcCmd()}.value = "msgView";
   		    frm.msg_no.value=msgid;
   		    frm.submit();
		}
	}
    function doSearch() {
	    var frm = getObject("myform");
			if(frm) {
    		    frm.${mrt:mvcCmd()}.value = "rcvList";
                frm.miv_page.value = 1;
    		    frm.submit();
			}     
    }
//-->
</SCRIPT>

		