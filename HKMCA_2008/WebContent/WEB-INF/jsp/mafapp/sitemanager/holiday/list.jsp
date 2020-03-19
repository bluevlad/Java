<%@ page contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="mhtml" uri="/WEB-INF/tld/mi-html-util.tld" %>
<script language="javascript" src="${CPATH}/js/calendar.js"></script>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
	<tr>
        <td>
	        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
	        <form action="${control_action}" method="post" name="myform" id="myform">
			<input type="hidden" size="200" name="${mrt:mvcListOp()}" value="${LISTOP.serializeUrl}">
			<input type="hidden" name="miv_page" value="1">
			<input type="hidden" name="${mrt:mvcCmd()}" value="list">
			<input type="hidden" name="ymd" value=""> 
	            <tr>
	                <th class="search">일자</th>
	                <td class="search">
			        <input type="text" name="symd" size="10" readonly value="${LISTOP.ht.symd}" OnClick="popUpCalendar(this, this, 'yyyy-mm-dd');" hname="시작일자">
			        ~ <input type="text" name="eymd" size="10" readonly value="${LISTOP.ht.eymd}" OnClick="popUpCalendar(this, this, 'yyyy-mm-dd');" hname="종료일자">
	                </td>
	             </tr>
				     			
	            <tr>
	                <th class="search">휴일정보</th>
	                <td class="search"><input type="text" name="bigo" value="${LISTOP.ht.bigo}"></td>
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
		    	<th class="list BL" ># </th>
				<th class="list">일자<mform:THSort id='ymd2'/></th>
				<th class="list">금융계공휴일<mform:THSort id='bank_work'/></th>
				<th class="list">근무일<mform:THSort id='work_day'/></th>
				<th class="list BR">휴일정보<mform:THSort id='bigo'/></th>
		    </tr>
		    <c:forEach var="item" items="${navigator.list}" varStatus="status">
			<tr onMouseOver="tr_over(this)" onMouseOut="tr_out(this)">
				<td class="list center">${status.count+(navigator.currentPage-1)*navigator.pageSize}</td>
				<td class="list center"><a href="javaScript:doView('${item.ymd}')">${mhtml:null2nbsp(item.ymd2)}</a></td>
				<td class="list center">${item.bank_work == '1' ? '근무일' : '휴일'}</td>
				<td class="list center">${item.work_day == '1' ? '근무일' : '휴일'}</td>
				<td class="list center">${mhtml:null2nbsp(item.bigo)}</td>
			</tr>
		    </c:forEach>
		    </form>
		    </table>
	    </td>
    </tr>
	<tr>
    <td align="center">
    	<a href="javascript:doWrite()"><mfmt:message bundle="button" key="button.add"/></a><br>
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
    		    frm.${mrt:mvcCmd()}.value = "edit";
    		    frm.ymd.value = id;
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
//-->
</SCRIPT>		