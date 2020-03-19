<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<SCRIPT LANGUAGE="JavaScript">
<!--
	function doView(lec_cd){
		var frm = getObject("myform");
		if(frm) {
			frm.cmd.value = "view";
			frm.lec_cd.value = lec_cd; 
			frm.submit();
		}
	}
    function doSearch() {
		var frm = getObject("myform");
		if(frm) {
   			frm.cmd.value = "list";
			frm.submit();
		}     
    }
    function doApprove(stat) {
        var frm = getObject("myform");
        frm.cmd.value= "complete";
        frm.req_stat.value = stat;
        frm.submit();
    }
	function doExcel(){
		var frm = getObject("myform");
			frm.cmd.value = "excel";
			frm.submit();
	}
	function doUpload(){
		var frm = getObject("myform");
			frm.cmd.value = "upload";
			frm.submit();
	}
//-->
</SCRIPT>

<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return doSearch(this,'list');return false;">
<mf:input type="hidden" size="200" name="ListOp" value="${LISTOP.serializeUrl}"/>
<mf:input type="hidden" name="miv_page" value="1"/>
<mf:input type="hidden" name="cmd" value="list"/>
<mf:input type="hidden" name="destination" value="${destination}"/> 
<mf:input type="hidden" name="lec_cd" value=""/> 
<mf:input type="hidden" name="req_stat" value=""/>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
	<tr>
        <td>
            <div class="searchContainer">
	        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
				<col width="15%"/>
				<col width="35%"/>
				<col width="15%"/>
				<col width="35%"/>
                <tr>
                    <th><mf:label name="lec_nm"/></th>
                    <td colspan="3">
                        <jsp:include page="/WEB-INF/jsp/common/sel_lec.jsp" flush="true">
                            <jsp:param name="os_crs" value='<%=request.getParameter("os_crs")%>'/>
                            <jsp:param name="os_sjt" value='<%=request.getParameter("os_sjt")%>'/>
                            <jsp:param name="os_lec" value='<%=request.getParameter("os_lec")%>'/>
                            <jsp:param name="destination" value='<%=request.getAttribute("destination")%>'/>
                        </jsp:include>
                    </td>
                </tr>
                <tr>
                    <th><mf:label name="userid"/></th>
                    <td><mf:input type="text" name="s_userid" size="12" value="${LISTOP.ht.s_userid}"/></td>                   
                    <th><mf:label name="nm"/></th>
                    <td><mf:input type="text" name="s_nm" size="12" value="${LISTOP.ht.s_nm}" /></td>
                </tr>
                <tr>
                    <th><mf:label name="req_dt"/></th>
                    <td>
                        <mf:input type="text" name="s_sch_sdt" size="12" value="${LISTOP.ht.s_sch_sdt}" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');" /> ~
                        <mf:input type="text" name="s_sch_edt" size="12" value="${LISTOP.ht.s_sch_edt}" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');" />
                    </td>
                    <th><mf:label name="req_stat"/></th>
                    <td>
                        <mf:select name="s_reqstat" codeGroup="LEC.REQ_STAT" curValue="${LISTOP.ht.s_reqstat}">
                        <option value="">-</option>
                        </mf:select>
                    </td>                   
                </tr>
	        </table>
	        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="searchBtn">
	        	<tr>
	        		<td align="right">
		                <mf:button onclick="doSearch()" bundle="button" key="search" icon="icon_search"/>
	                </td>
	        	</tr>
	        </table>
	        </div>    
        </td>
    </tr>
	<tr>
	    <td>
            <div class="listContainer">
			<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
			<thead>
			    <tr>
			    	<th><mf:input type="checkbox" name="checkboxAll" value="allcode" onclick="allChekboxToggle(this,'myform','v_usn')"/></th>
			    	<th>#</th>
		            <th><mf:header name="lec_cd" sort="true"/></th>
		            <th><mf:header name="lec_nm" sort="true"/></th>
		            <th><mf:header name="lec_year" sort="true"/></th>
		            <th><mf:header name="userid" sort="true"/></th>
		            <th><mf:header name="nm" sort="true"/></th>
		            <th><mf:header name="req_dt" sort="true"/></th>
		            <th><mf:header name="req_stat" sort="true"/></th>
			    </tr>
		    </thead>
		    <tbody>
			    <c:forEach var="item" items="${navigator.list}" varStatus="status">
				<tr>
					<td class="center">
                        <c:choose>
	   						<c:when test="${item.req_stat != 'LE'}">
	   						   <mf:input type="checkbox" name="v_usn" value="${item.usn}##${item.lec_cd}"/>
	   						</c:when>
	                        <c:otherwise>
	                            &nbsp;
	                        </c:otherwise>
                        </c:choose>
					</td>
					<td class="center"><mh:listseq navigator="${navigator}" count="${status.count}" /></td>
					<td class="center"><mh:out value="${item.lec_cd}" td="true"/></td>
					<td class="center"><mh:out value="${item.lec_nm}" td="true"/></td>
					<td class="center"><mh:out value="${item.lec_year}" td="true"/></td>
					<td class="center"><mh:out value="${item.userid}" td="true"/></td>
					<td class="center"><mh:out value="${item.nm}" td="true"/></td>
					<td class="center"><mh:out value="${item.req_dt}" td="true"/></td>
					<td class="center"><mh:out value="${item.req_stat}" codeGroup="LEC.REQ_STAT"/></td>
				</tr>
			    </c:forEach>
		    </tbody>
		    </table>
			<table width="100%" border="0" cellspacing="0" cellpadding="2" class="viewBtn">
			    <tr>
			        <td align="right">
			            <mf:button bundle="button" key="lec.approve" onclick="doApprove('LP')"/>
			            <mf:button bundle="button" key="lec.complete" onclick="doApprove('LE')"/>
			            <mf:button bundle="button" key="lec.request" onclick="doApprove('LR')"/>
			            <mf:button bundle="button" key="lec.req.cancelled" onclick="doApprove('CA')"/><br><br>
			            <mf:button bundle="button" key="excel.down" onclick="doExcel()"/>
			            <mf:button bundle="button" key="excel.up" onclick="doUpload()"/>
			        </td>
			    </tr>
			</table>
		    </div>
	    </td>
    </tr>
</table>
</mf:form> 
<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/>