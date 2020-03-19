<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<c:set var="dtreeUrl" value="${CPATH}/js/dtree"/>
<link rel="StyleSheet" href='<c:url value="${dtreeUrl}/dtree.css"/>' type="text/css" />
<SCRIPT src="<mh:out value="${CPATH}"/>/js/dtree.js"></SCRIPT>

<script language="javascript" src="<mh:out value="${CPATH}"/>/js/calendar.js"></script>
<script language="javascript" src="<mh:out value="${CPATH}"/>/js/lib.validate.js"></script>
<script language="javascript">
    function doCopyOk() {
        var frm = getObject("myform");
		if(frm) {
            if (validate(frm)) {
		        frm.cmd.value="copy_ok";
		        frm.submit();
	        }
        }
    }
    function goList() {
	    <c:url var="urlList" value="${control_action}">
	        <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
	    	<c:param name="cmd" value="list"/>
	    </c:url>
		document.location = '<mh:out value="${urlList}"/>';
	}
</script>
<div class="viewContainer">
<mf:form action="${control_action}"  method="post" name="myform" id="myform">
<mf:input type="hidden" name="cmd" value=""/>
<mf:input type="hidden" name="orgn_cd" value="${org.org_cd}"/>
<mf:input type="hidden" name="dlr_cd" value="${org.deal_no}"/>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
	<tr>
	    <td><div class="listContainer">
			<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
			<thead>
			    <tr>
			    	<th>#</th>
		            <th><mf:header name="section"/></th>
		            <th><mf:header name="field"/></th>
		            <th><mf:header name="cert_lvl"/></th>
		            <th><mf:header name="leccode"/></th>
		            <th><mf:header name="lecname"/></th>
		            <th><mf:header name="lecstat"/></th>
		            <th><mf:header name="otype"/></th>
			    </tr>
		    </thead>
		    <tbody>
			    <c:forEach var="item" items="${navigator.list}" varStatus="status">
				<tr>
					<td class="center"><mf:input type="radio" name="leccode" value="${item.sjt_cd}:${item.leccode}" required="true"/></td>
					<td class="center"><mh:out value="${item.section}" codeGroup="SECTION"/></td>
					<td class="center"><mh:out value="${item.field}" codeGroup="FIELD"/></td>
					<td class="center"><mh:out value="${item.cert_lvl}" codeGroup="CERT_LVL"/></td>
					<td class="center"><mh:out value="${item.leccode}" td="true"/></td>
					<td class="center"><mh:out value="${item.lecname}" td="true"/></td>
					<td class="center"><mh:out value="${item.lecstat}" codeGroup="LECSTAT"/></td>
					<td class="center"><mh:out value="${item.otype}" codeGroup="OTYPE"/></td>
				</tr>
			    </c:forEach>
		    </tbody>
		    </table>
		    </div>
	    </td>
    </tr>
</table>
<br/>
<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">	
	<col width="15%"/>
	<col width="35%"/>
	<col width="15%"/>
	<col width="35%"/>
	<tr>
	    <th><mf:label name="lec_year"/></th> 
	    <td><mf:input type="text" name="lec_year" value="" required="true"/></td>
	    <th><mf:label name="lecnumb"/></th> 
	    <td><mf:input type="text" name="lecnumb" value="" required="true"/></td>
 	</tr>
	<tr>
	    <th><mf:label name="lecname"/></th> 
	    <td colspan="3"><mf:input type="text" name="lecname" size="120" value="" required="true"/></td>
 	</tr>
	<tr>
	    <th><mf:label name="lec_sdate"/></th> 
	    <td><mf:input type="date" name="lec_sdate" value="" onclick="popUpCalendar(this, this, 'yyyymmdd');"/></td>
	    <th><mf:label name="lec_edate"/></th> 
	    <td><mf:input type="date" name="lec_edate" value="" onclick="popUpCalendar(this, this, 'yyyymmdd');"/></td>
 	</tr>
	<tr>
	    <th><mf:label name="lec_reg_edate"/></th> 
	    <td><mf:input type="date" name="lec_reg_edate" value="" onclick="popUpCalendar(this, this, 'yyyymmdd');"/></td>
	    <th><mf:label name="adminid"/></th> 
	    <td><mf:input type="text" name="adminid" value=""/></td>
 	</tr>
</table>
<table border="0" cellpadding="2" cellspacing="0" width="100%">	
	<tr>
		<td align="right">
		<mf:button bundle="button" key="save" onclick="javascript:doCopyOk()"/>
		<mf:button bundle="button" key="goList" onclick="javascript:goList()"/></td>
	</tr>
</table>
</mf:form>
</div>