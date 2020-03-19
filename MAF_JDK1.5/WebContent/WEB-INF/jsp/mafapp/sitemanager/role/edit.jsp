<%@ page contentType="text/html; charset=utf-8"%>     
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>   	
<script language="javascript" src='<c:url value="/js/lib.validate.js"/>'></script>
<script language="javascript" >
	function frmSubmit() {
		var frm = getObject("myform");	
		if (validate(frm)) {
			// 사용자 조건 추가.
		frm.cmd.value = '<mh:iif test="${param.cmd == 'edit'}" trueValue="update" falseValue="insert"/>';
			frm.submit();
		} else {
			return;
		}
	}
	function goList() {
        <c:url var="urlList" value="${control_action}">
            <c:param name="cmd" value="list"/>
        </c:url>
        document.location = '<c:out value="${urlList}"/>';
    }
	function doDelete() {
		var frm = getObject("myform");	
		frm.cmd.value = "delete";
		frm.submit();
	}
    function doSearch() {
	    var frm = getObject("myform");
			if(frm) {
    		    frm.cmd.value = "edit";
                frm.miv_page.value = 1;
    		    frm.submit();
			}     
    }
</script>
<mf:form action="${control_action}" method="post" name="myform" id="myform">
<table width="620" border="0" cellspacing="0" cellpadding="2">
	
	<input type="hidden" name="cmd" value="">
	<mf:input type="hidden" name="LISTOP" value="${LISTOP.serializeUrl}"/>
	<input type="hidden" name="miv_page" value="1">
	<tr>
        <td>
			<table border="0" cellpadding="2" cellspacing="0" class="view" width="100%">	
				<tr>
				    <th nowrap>Role ID</th> 
				    <td ><mf:input type="text" name="role_id" size="20" maxlength="50" value="${item.role_id}"/></td>
				    <th nowrap>Role Name</th> 
				    <td ><mf:input type="text" name="role_name" size="20" maxlength="50" value="${item.role_name}"/></td>
			 	</tr>
				
				<tr>
				    <th  nowrap>슈퍼관리자</th> 
				    <td >
						<mf:select name="is_super" curValue="${item.is_super}" codeGroup="YESORNO"/>
				    </td>
				    <th nowrap>로그인전 권한</th> 
				    <td ><mf:select name="is_nologin" curValue="${item.is_nologin}" codeGroup="YESORNO"/>
					</td>
			 	</tr>
				
				<tr>
				    <th class="view" nowrap>게시판권한<br>
				    	(Level(1~10), 10는 login 이전)</th> 
                        
				    <mh:iif  var="tLevel" test="${empty item.bbs_level}" trueValue="9" falseValue="${item.bbs_level}" />
				    <td class="view"><select name="bbs_level"><c:forEach var="iter" begin="1" end="10" step="1">
                            <mf:option value="${iter}" curValue="${tLevel}" text="${iter}" />
				    	</c:forEach>
				    	</select></td>
				    <th class="view" nowrap>시스템고정여부</th> 
				    <td class="view">
						<mf:select name="fixed" curValue="${item.fixed}" codeGroup="YESORNO"/>
				    </td>
			 	</tr>
			
				<tr>
				    <th class="view" nowrap>비고</th> 
				    <td class="view" colspan="3"><mf:input type="text" name="bigo" size="50" maxlength="100" value="${item.bigo}"/></td>
			 	</tr>
                <tr>
                    <th class="view" nowrap>외부사용자 그룹</th> 
                    <td class="view" colspan="3"><select name="ext_group">
                        <option value="">--</option>
                        <c:forEach var="i" items="${extlist}">
                            <mf:option value="${i.code}" curValue="${item.ext_group}" text="${i.name} - ${i.status}"/>
                        </c:forEach>
                    </select></td>
                </tr>
				
				
			</table>
			<br>
			<c:if test="${!empty(list)}">
			<table border="0" cellspacing="0" cellpadding="2" class="list" width="100%" enableAlternateRows="true" rowAlternateClass="alternateRow">
            <thead>
		    <tr>
		    	<th >No.</th>
		    	<th >User Id</th>
		    	<th >Name</th>
                <th >Org Code</th>
		    </tr>
            </thead>
            <tbody>
		    <c:forEach var="list" items="${list}" varStatus="status">
			<tr >
				<td class=" center"><mf:input type="checkbox" name="vusn" value="${list.usn}" checked="true"/></td>
				<td class=" center"><mh:out value="${list.userid}" td="true"/></td>
				<td class=" center"><mh:out value="${list.nm}" td="true"/></td>
                <td class=" center"><mh:out value="${list.org_cd}" td="true"/></td>
			</tr>
		    </c:forEach>
            </tbody>
		    </table>
		    </c:if>
			<br><br>
			<table width="100%" border="0" cellspacing="0" cellpadding="2">
			<c:if test="${param.cmd == 'edit'}">
				<tr>
			        <td>
					        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
					            <tr>
									<th class="search">Name</th>
									<td class="search"><mf:input type="text" name="nm" value="${LISTOP.ht.nm}"/> </td>
									<th class="search">User Id</th>
									<td class="search"><mf:input type="text" name="userid" value="${LISTOP.ht.userid}"/></td>
								</tr>
								<tr>
									<td colspan="10"><mf:button onclick="doSearch()" bundle="button" key="search" /></td>
								</tr>    
							</table>
			        </td>
			    </tr>
			    <tr>
				    <td>	
					<table border="0" cellspacing="0" cellpadding="2" class="list" width="100%" enableAlternateRows="true" rowAlternateClass="alternateRow">
                    <thead>
				    <tr>	      
						<th>No</th>	      
						<th>User Id</th>	 
						<th>Name</th>     
						<th>Org Code</th>
				    </tr>
                    </thead>
                    <tbody>
				    <c:forEach var="nlist" items="${navigator.list}" varStatus="status">
					<tr>	
						<td align="center"><mf:input type="checkbox" name="vusn" value="${nlist.usn}"/></td>
						<td ><mh:out value="${nlist.userid}" td="true"/></td>
						<td ><mh:out value="${nlist.nm}" td="true"/></td>
						<td ><mh:out value="${nlist.org_cd}" td="true"/></td>			
					</tr>
				    </c:forEach>
                    </tbody>
				    </table>
				    </td>
			    </tr>
			    </c:if>
				<tr>
					<td colspan="2" align="center">
                        <mf:button bundle="button" key="save" onclick="frmSubmit()" icon="icon_save"/>
					   <mf:button bundle = "button"  key="goList" onclick="goList();"/>
						<c:if test="${param.cmd == 'edit'}">
                            <mf:button bundle="button" key="delete" onclick="doDelete()" icon="icon_delete"/>
						</c:if>
					</td>
				</tr>
				    </mf:form>
				<tr>
			    <td align="center"> <jsp:include page="/WEB-INF/layout/lib/navigator.jsp" flush="true"/></td></td>
			    </tr>
			</table>
		</td>
	</tr>
</table>
		