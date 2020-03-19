<%@ page contentType="text/html; charset=utf-8"%>     
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>   	
<script language="javascript" src='<c:url value="/js/lib.validate.js"/>'></script>
<script>
    function doEdit() {
        var frm = getObject("myform");
		frm.code.value = '<c:out value="${item.code}"/>';
        frm.submit();
    }
    function goList() {
	        <c:url var="urlList" value="${control_action}">
	            <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
	            <c:param name="cmd" value="list"/>
	        </c:url>
	        document.location = '<mh:out value="${urlList}"/>';
	    }
</script>

<mf:form action="${control_action}" method="post" name="myform" id="myform" >
  <mf:input type="hidden" name="group_cd" value="${item.group_cd}"/>
  <mf:input type="hidden" name="cd_chk" value="${item.group_cd}"/>
  <mf:input type="hidden" name="code" value="${item.code}"/>
  <mf:input type="hidden" name="LISTOP" value="${LISTOP.serializeUrl}"/>
  <mf:input type="hidden" name="cmd" value="edit"/>
</mf:form>  	
<table border="0" cellpadding="2" cellspacing="1" class="view">
  <tr>
    <th  nowrap>그룹코드</th> 
    <td ><mh:out value="${item.group_cd }" td="true"/></td>
  </tr>
  <tr>
    <th  nowrap>그룹명</th> 
    <td ><mh:out value="${item.group_name}" td="true"/></td>
  </tr>
  <tr>
    <th nowrap>비고</th> 
    <td ><mh:out value="${item.bigo}" td="true"/></td>
  </tr>
  <tr>
    <th nowrap>수정가능여부</th> 
    <td ><mh:out value="${item.fixed_yn}" codeGroup ="SYSTEM_YN"/></td>
  </tr>  
	<tr>
		<td colspan="2" align="center"><mf:button bundle="button" key="edit" onclick="doEdit()"/>
									   <mf:button bundle="button" key="delete" onclick="doDelete()"/> 
									   <mf:button bundle="button" key="goList" onclick="goList()"/>
		</td>
	</tr>
</table>