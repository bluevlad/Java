<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

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
<table width="100%" border="0" cellspacing="0" cellpadding="2">
	<tr>
	    <td><div class="listContainer">
			<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list" enableAlternateRows="true" rowAlternateClass="alternateRow">
			<thead>
			    <tr>
			    	<th>#</th>
		            <th><mf:header name="lec_cd"/></th>
		            <th><mf:header name="lec_nm"/></th>
		            <th><mf:header name="lec_stat"/></th>
		            <th><mf:header name="otype"/></th>
			    </tr>
		    </thead>
		    <tbody>
			    <c:forEach var="item" items="${navigator.list}" varStatus="status">
				<tr>
					<td class="center"><mf:input type="radio" name="lec_cd" value="${item.lec_cd}" required="true"/></td>
					<td class="center"><mh:out value="${item.lec_cd}" td="true"/></td>
					<td class="center"><mh:out value="${item.lec_nm}" td="true"/></td>
					<td class="center"><mh:out value="${item.lec_stat}" codeGroup="LEC.LEC_STAT"/></td>
					<td class="center"><mh:out value="${item.otype}" codeGroup="LEC.OTYPE"/></td>
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
	    <th><mf:label name="lec_num"/></th> 
	    <td><mf:input type="text" name="lec_num" value="" required="true"/></td>
 	</tr>
	<tr>
	    <th><mf:label name="lec_nm"/></th> 
	    <td colspan="3"><mf:input type="text" name="lec_nm" size="120" value="" required="true"/></td>
 	</tr>
</table>
<table border="0" cellpadding="2" cellspacing="0" width="100%" class="viewBtn">	
	<tr>
		<td align="right">
			<mf:button bundle="button" key="save" onclick="javascript:doCopyOk()" icon="icon_save"/>
			<mf:button bundle="button" key="list" onclick="javascript:goList()" icon="icon_list"/>
		</td>
	</tr>
</table>
</mf:form>
</div>