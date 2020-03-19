<%@ page contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="mhtml" uri="/WEB-INF/tld/mi-html-util.tld" %>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
	<tr>
        <td>
	        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
	        <form action="${control_action}" method="post" name="myform" id="myform">
			<input type="hidden" size="200" name="${mrt:mvcListOp()}" value="${LISTOP.serializeUrl}">
			<input type="hidden" name="miv_page" value="1">
			<input type="hidden" name="${mrt:mvcCmd()}" value="list">
			
			<input type="hidden" name="poll_id" value=""> 
				       
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
	    	<th class="list BL" ># </th>
	      	<th class="list" ><mform:header name="poll_id"/></th>	
	      	<th class="list" ><mform:header name="title"/></th>	
	      	<th class="list" ><mform:header name="start_dt"/></th>	
			<th class="list" ><mform:header name="end_dt"/></th>		
			<th class="list BR" >응답자수</th>			
	    </tr>
	    <c:forEach var="item" items="${navigator.list}" varStatus="status">
		<tr onMouseOver="tr_over(this)" onMouseOut="tr_out(this)">
			<td class="list" >${mrt:getListSeq(navigator, status.count)} </td>
			<td class="list"><a href="javaScript:doView( '${item.poll_id}' )">${mhtml:null2nbsp(item.poll_id)}</a></td>	
			<td class="list"><a href="javaScript:doView( '${item.poll_id}' )">${mhtml:null2nbsp(item.title)}</a></td>	
			<td class="list">${mfmt:shortDate(item.start_dt)}</td>	
			<td class="list">${mfmt:shortDate(item.end_dt)}</td>	
			<td class="list right"><a href="javascript:viewResult(${item.poll_id})"> ${mfmt:currency(item.ans)}</a> 건 <a href="javascript:viewResult(${item.poll_id})">결과보기</a></td>	
		</tr>
	    </c:forEach>
	    </form>
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
	function doView(id){
	    var frm = getObject("myform");
			if(frm) {
    		    frm.${mrt:mvcCmd()}.value = "view";
    		     frm.poll_id.value = id;
    		    frm.submit();
			}
	}
    function doSearch() {
	    var frm = getObject("myform");
			if(frm) {
    		    frm.${mrt:mvcCmd()}.value = "list";
                frm.miv_page.value = 1;
    		    frm.submit();
			}     
    }
	function viewResult(pollid) {
		popupWindow("${control_action}?cmd=viewResult&poll_id="+pollid,"poll_result");
	}
//-->
</SCRIPT>

		