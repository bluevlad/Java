<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>
<table width="100%" border="0" cellspacing="0" cellpadding="2">
    <tr>
        <td><div class="listContainer">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
            	<tr>
                    <td><mf:form action="${control_action}" method="post" name="myform" id="myform">
            	        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="list " 
                        enableAlternateRows="true" rowAlternateClass="alternateRow">
            			<input type="hidden" name="miv_page" value="1">
            			<input type="hidden" name="cmd" value="list">
            			<input type="hidden" name="role_id" value=""> 
                        <div class="listContainer">
                    
                    <thead>
            			    <tr>
            			    	<th >No.</th>
            			    	<th >Role_id</th>
            			    	<th >Role_name</th>
            			    	<th >슈퍼관리자여부</th>
            			    	<th >Guest여부</th>
            			    	<th >게시판권한</th>
            			    </tr>
                           </thead>
                           <tbody>
            			    <c:forEach var="item" items="${navigator.list}" varStatus="status">
            				<tr>
            					<td class=" center"><mh:listseq navigator="${navigator}" count="${status.count}"/></td>
            					<td class=" center"><a href="javaScript:doView('<c:out value="${item.role_id}"/>')" ><mh:out value="${item.role_id}" td="true"/></a></td>
            					<td class=" center"><a href="javaScript:doView('<c:out value="${item.role_id}"/>')" ><mh:out value="${item.role_name}"  td="true"/></a></td>
            					<td class=" center"><mh:iif test="${item.is_super == '1'}" trueValue="Y" falseValue="N"/></td>
            					<td class=" center"><mh:iif test="${item.is_nologin == 'Y'}" trueValue="Y" falseValue="N"/></td>
            					<td class=" center"><mh:out value="${item.bbs_level}" td="true"/></td>
            				</tr>
            			    </c:forEach>
                            </tbody>
            			    
            		    </table>
                        </mf:form>
            	    </td>
                </tr>

            </table></div>

        </td>
    </tr>
    <tr>
        <td align="center">
            <mf:button bundle="button" key="add" onclick="doWrite()" icon="icon_add"/><br>
            <jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/></td>
    </tr>
</table>

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
				frm.role_id.value = id;
    		    frm.cmd.value = "edit";
    		    frm.submit();
			}
	}
//-->
</SCRIPT>

		