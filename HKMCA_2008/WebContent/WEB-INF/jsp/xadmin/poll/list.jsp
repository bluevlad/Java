<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<SCRIPT LANGUAGE="JavaScript">
<!--
    function doWrite(){
        var frm = getObject("myform");
            if(frm) {
	            frm.cmd.value = "add";
	            frm.submit();
            }
    }
    function doView(id){
        var frm = getObject("myform");
            if(frm) {
                frm.cmd.value = "edit";
                frm.poll_id.value = id;
                frm.submit();
            }
    }
    function doSearch() {
        var frm = getObject("myform");
            if(frm) {
                frm.cmd.value = "list";
                frm.miv_page.value = 1;
                frm.submit();
            }     
    }
    function viewResult(pollid) {
        popupWindow('<mh:out value="${control_action}"/>?cmd=viewResult&poll_id='+pollid,"poll_result");
    }
//-->
</SCRIPT>   

<mf:form action="${control_action}" method="post" name="myform" id="myform">
<mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}" />
<mf:input type="hidden" name="miv_page" value="1"/>
<mf:input type="hidden" name="cmd" value="list"/>
<mf:input type="hidden" name="poll_id" value=""/> 
<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
	    <td>
            <div class="listContainer">
			<table width="100%" border="0" cellspacing="0" cellpadding="2" class="list">
                <thead>
			    <tr>
			    	<th>#</th>
			      	<th><mf:header name="poll_id"/></th>	
			      	<th><mf:header name="title"/></th>	
			      	<th><mf:header name="start_dt"/></th>	
					<th><mf:header name="end_dt"/></th>		
					<th>응답자수</th>			
			    </tr>
                </thead>
			    <c:forEach var="item" items="${navigator.list}" varStatus="status">
				<tr>
					<td align="center"><mh:listseq navigator="${navigator}" count="${status.count}" /></td>
					<td align="center"><a href="javaScript:doView('<mh:out value="${item.poll_id}" />')"><mh:out value="${item.poll_id}" td="true" /></a></td>	
					<td align="center"><a href="javaScript:doView('<mh:out value="${item.poll_id}" />')"><mh:out value="${item.title}" td="true" /></a></td>	
					<td align="center"><mh:out value="${item.start_dt}" td="true" /></td>	
					<td align="center"><mh:out value="${item.end_dt}" td="true" /></td>	
					<td align="center"><mh:out value="${item.ans}"/><mfmt:message bundle="common" key="total.count" />&nbsp;<mf:button bundle="button" key="survey.result" onclick="javascript:viewResult('${item.poll_id}')" /></td>	
				</tr>
    		    </c:forEach>
		    </table>
			<table width="100%" border="0" cellspacing="0" cellpadding="2" class="viewBtn">
			    <tr>
			        <td align="right"><mf:button bundle="button" key="add" onclick="doWrite()" icon="icon_add" /></td>
			    </tr>
			</table>
            </div>
	    </td>
    </tr>
</table>
</mf:form>
<jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/>