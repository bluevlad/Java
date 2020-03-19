<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<c:set var="dtreeUrl" value="${CPATH}/js/dtree"/>
<link rel="StyleSheet" href='<c:url value="${dtreeUrl}/dtree.css"/>' type="text/css" />
<SCRIPT src="<mh:out value="${CPATH}"/>/js/dtree.js"></SCRIPT>

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
	function setSjtcd(sjt_cd) {
        if (typeof sjt_cd == "undefined") {
            sjt_cd = "";
        }
        var frm = getObject("myform");
        frm.sjt_cd.value=sjt_cd;
        CategorySelector.outSel();
        CategorySelector.hideList();
        return;
    }
//-->
</SCRIPT>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
	<tr>
        <td><div class="searchContainer">
			<mf:form action="${control_action}" method="post" name="myform" id="myform">
	        <input type="image" value="test" width="0" height="0" border="0" class="hidden"/>
			<mf:input type="hidden" size="200" name="ListOp" value="${LISTOP.serializeUrl}"/>
			<mf:input type="hidden" name="miv_page" value="1"/>
			<mf:input type="hidden" name="cmd" value="list"/>
			<mf:input type="hidden" name="sjt_cd" value=""/> 
			<mf:input type="hidden" name="lec_cd" value=""/> 
	        <mf:input type="hidden" name="to" value=""/>
	        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
				<col width="15%"/>
				<col width="35%"/>
				<col width="15%"/>
				<col width="35%"/>
				<tr>
					<th><mf:label name="lec_stat"/></th>
					<td><mf:select name="lec_stat" codeGroup="LECSTAT" curValue="${LISTOP.ht.lec_stat}"><option value=""><mfmt:message bundle="common" key="search.all"/></option></mf:select></td>					
				</tr>
				<tr>
					<th><mf:label name="lec_date"/></th>
					<td>
						<mf:input type="date" name="lec_sdate" size="10" value="${LISTOP.ht.lec_sdate}" onclick="popUpCalendar(this, this, 'yyyymmdd');"/> ~
						<mf:input type="date" name="lec_edate" size="10" value="${LISTOP.ht.lec_edate}" onclick="popUpCalendar(this, this, 'yyyymmdd');"/>
					</td>
				</tr>
				<tr>
					<th><mf:label name="lec_nm"/></th>
					<td><mf:input type="text" name="s_lec_nm" value="${LISTOP.ht.s_lec_nm}" cssStyle="width:150px"/></td>					
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
			    	<th>#</th>
		            <th><mf:header name="lec_cd" sort="true"/></th>
		            <th><mf:header name="lec_nm" sort="true"/></th>
		            <th><mf:header name="lec_stat" sort="true"/></th>
		            <th><mf:header name="otype" sort="true"/></th>
			    </tr>
		    </thead>
		    <tbody>
			    <c:forEach var="item" items="${navigator.list}" varStatus="status">
				<tr>
					<td class="center"><mh:listseq navigator="${navigator}" count="${status.count}"/></td>
					<td class="center"><mh:out value="${item.lec_cd}" td="true"/></td>
					<td class="center"><a href='javascript:doView("<c:out value="${item.lec_cd}"/>");'><mh:out value="${item.lec_nm}" td="true"/></a></td>
					<td class="center"><mh:out value="${item.lec_stat}" codeGroup="LECSTAT" td="true"/></td>
					<td class="center"><mh:out value="${item.otype}" codeGroup="OTYPE" td="true"/></td>
				</tr>
			    </c:forEach>
		    </tbody>
		    </table>
	        
		    </div>
	    </td>
    </tr>
	<tr>
	    <td align="right">
	    	<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/>
	    </td>
    </tr>
</table>
