<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<table width="100%" border="0" cellspacing="0" cellpadding="2">
	<tr>
        <td>
	        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
	        <form action="${control_action}" method="post" name="myform" id="myform">
			<input type="hidden" size="200" name="${listOp}" value="${LISTOP.serializeUrl}">
			<input type="hidden" name="miv_page" value="1">
			<input type="hidden" name="cmd" value="rcvList">
			
			<input type="hidden" name="msgid" value="">
	            <tr>
	                <th class="search"><mform:header name="title"/></th>
	                <td class="search"><input type="text" name="title" value="${LISTOP.ht.title}"></td>
	             </tr>
	            <tr>
	                <th class="search"><mform:header name="title2"/></th>
	                <td class="search"><input type="text" name="title" value="${LISTOP.ht.title2}"></td>
	             </tr>
	             <tr>
	                <td colspan="10"><mform:submit bundle="button" key="search"/></td>
	             </tr>
	        </form>     
	        </table>
        </td>
    </tr>
    <tr>
	    <td>
		<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list">

	    <tr>
	    	<th class="list" ># </th>

			<th class="list"><mform:header name="nm_snd"/><mform:THSort id='nm_snd'/></th>
			<th class="list"><mform:header name="title"/><mform:THSort id='title'/></th>
			<th class="list"><mform:header name="reg_dt"/><mform:THSort id='reg_dt'/></th>
			<th class="list"><mform:header name="rcv_dt"/><mform:THSort id='rcv_dt'/></th>
	    </tr>
	    <c:forEach var="item" items="${navigator.list}" varStatus="status">
		<tr onMouseOver="tr_over(this)" onMouseOut="tr_out(this)">
			<td class="list" >${mrt:getListSeq(navigator, status.count)} </td>
			<td class="list">${mhu:null2nbsp(item.nm_snd)}</td>
			<td class="list"><a href="javaScript:doView( '${item.msgid}')">${mhu:null2nbsp(item.title)}</a></td>
			<td class="list">${mfmt:shortDate(item.reg_dt)}</td>
			<td class="list">${mfmt:shortDate(item.rcv_dt)}</td>
		</tr>
	    </c:forEach>

	    </table>
	    </td>
    </tr>
	<tr>
    <td align="center">
    	<a href="javascript:doWrite()"><mfmt:button bundle="button" key="button.add"/></a><br>
    	<jsp:include page="/layout/lib/navigator.jsp" flush="true"/></td>
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
	function doView(msgid){
	    var frm = getObject("myform");
			if(frm) {
    		    frm.${mrt:mvcCmd()}.value = "msgView";
    		    frm.msgid.value=msgid;
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

		