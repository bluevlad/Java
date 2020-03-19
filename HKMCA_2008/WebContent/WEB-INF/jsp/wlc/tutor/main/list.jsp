<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<SCRIPT LANGUAGE="JavaScript">
<!--
	function doView(sjt_cd, lec_cd){
		var frm = getObject("myform");
		if(frm) {
        frm.action = "/wlc.tutor/chapters.do";
        frm.sjt_cd.value  = sjt_cd;
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
//-->
</SCRIPT>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
	<tr>
        <td>
            <div class="searchContainer">
			<mf:form action="${control_action}" method="get" name="myform" id="myform">
			<mf:input type="hidden" size="200" name="ListOp" value="${LISTOP.serializeUrl}"/>
			<mf:input type="hidden" name="miv_page" value="1"/>
			<mf:input type="hidden" name="cmd" value="list"/>
			<mf:input type="hidden" name="sjt_cd" value=""/> 
			<mf:input type="hidden" name="lec_cd" value=""/> 
	        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
				<col width="15%"/>
				<col width="35%"/>
				<col width="15%"/>
				<col width="35%"/>
				<tr>
					<th><mf:label name="lec_stat"/></th>
					<td><mf:select name="lec_stat" codeGroup="LEC.LEC_STAT" curValue="${LISTOP.ht.lec_stat}"><option value="">-</option></mf:select></td>					
					<th><mf:label name="lec_date"/></th>
					<td>
						<mf:input type="date" name="lec_sdate" size="10" value="${LISTOP.ht.lec_sdate}" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');"/> ~
						<mf:input type="date" name="lec_edate" size="10" value="${LISTOP.ht.lec_edate}" onclick="popUpCalendar(this, this, 'yyyy-mm-dd');"/>
					</td>
				</tr>
				
	        </table>
	        <table border="0" cellspacing="0" cellpadding="2" class="searchBtn" width="100%">
	        	<tr>
	        		<td align="right">
		                <mf:button onclick="doSearch()" bundle="button" key="search" icon="icon_search"/>
	                </td>
	        	</tr>
	        </table>
			</mf:form> 
	        </div>    
        </td>
    </tr>
	<tr>
	    <td><div class="listContainer">
			<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
			<thead>
			    <tr>
		            <th><mf:header name="lec_cd" sort="true"/></th>
                    <th><mf:header name="lec_nm" sort="true"/></th>
		            <th><mf:header name="lec_stat" sort="true"/></th>
		            <th>
                        <mfmt:message bundle="common.lec" key="user_stat.request"/>/
                        <mfmt:message bundle="common.lec" key="user_stat.ing"/>/
                        <mfmt:message bundle="common.lec" key="user_stat.completed"/>
                    </th>
			    </tr>
		    </thead>
		    <tbody>
			    <c:forEach var="item" items="${navigator.list}" varStatus="status">
				<tr>
					<td align="center"><a href='javascript:doView("<c:out value="${item.sjt_cd}"/>","<c:out value="${item.lec_cd}"/>");'><mh:out value="${item.lec_cd}" td="true"/></a></td>
                    <td align="center"><a href='javascript:doView("<c:out value="${item.sjt_cd}"/>","<c:out value="${item.lec_cd}"/>");'><mh:out value="${item.lec_nm}" td="true"/></a></td>
					<td align="center"><mh:out value="${item.lec_stat}" codeGroup="LEC.LEC_STAT"/></td>
                    <td align="center"><mh:out value="${item.cnt_lr}"/> / <mh:out value="${item.cnt_lp}"/> / <mh:out value="${item.cnt_le}"/></td>
				</tr>
			    </c:forEach>
		    </tbody>
		    </table>
		    </div>
	    </td>
    </tr>
</table>
<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/>