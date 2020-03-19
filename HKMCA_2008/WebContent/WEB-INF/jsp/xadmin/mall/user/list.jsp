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
	function doExcel(){
		var frm = getObject("myform");
			frm.cmd.value = "excel";
			frm.submit();
	}
//-->
</SCRIPT>
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return doSearch(this,'list');return false;">
<mf:input type="hidden" size="200" name="ListOp" value="${LISTOP.serializeUrl}"/>
<mf:input type="hidden" name="miv_page" value="1"/>
<mf:input type="hidden" name="cmd" value="list"/>
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
                    <th><mf:label name="userid"/></th>
                    <td><mf:input type="text" name="s_userid" size="12" value="${LISTOP.ht.s_userid}"/></td>                   
                    <th><mf:label name="nm"/></th>
                    <td><mf:input type="text" name="s_nm" size="12" value="${LISTOP.ht.s_nm}" /></td>
                </tr>
                <tr>
                    <th><mf:label name="reg_dt"/></th>
                    <td>
                        <mf:input type="text" name="s_sch_sdt" size="12" value="${LISTOP.ht.s_sch_sdt}" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');" /> ~
                        <mf:input type="text" name="s_sch_edt" size="12" value="${LISTOP.ht.s_sch_edt}" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');" />
                    </td>
                    <th><mf:label name="active_yn"/></th>
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
		            <th><mf:header name="userid" sort="true"/></th>
		            <th><mf:header name="nm" sort="true"/></th>
		            <th><mf:header name="reg_dt" sort="true"/></th>
		            <th><mf:header name="active_yn" sort="true"/></th>
			    </tr>
		    </thead>
		    <tbody>
			    <c:forEach var="item" items="${navigator.list}" varStatus="status">
				<tr>
					<td class="center"><mf:input type="checkbox" name="v_usn" value="${item.usn}##${item.lec_cd}"/></td>
					<td class="center"><mh:listseq navigator="${navigator}" count="${status.count}" /></td>
					<td class="center"><mh:out value="${item.userid}" td="true"/></td>
					<td class="center"><mh:out value="${item.nm}" td="true"/></td>
					<td class="center"><mh:out value="${item.reg_dt}" td="true"/></td>
					<td class="center"><mh:out value="${item.active_yn}" codeGroup="ACTIVE_YN"/></td>
				</tr>
			    </c:forEach>
		    </tbody>
		    </table>
		    </div>
	    </td>
    </tr>
</table>
</mf:form> 
<table width="100%" border="0" cellspacing="0" cellpadding="2" class="viewBtn">
	<tr>
	    <td align="right">
	    	<mf:button bundle="button" key="excel.down" onclick="doExcel()"/>
	    </td>
    </tr>
</table>
<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/>