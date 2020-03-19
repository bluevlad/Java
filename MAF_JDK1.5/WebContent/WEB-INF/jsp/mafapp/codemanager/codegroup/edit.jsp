<%@ page contentType="text/html; charset=utf-8"%>     
<%@ include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>   	
<script language="javascript" src='<c:url value="/js/lib.validate.js"/>'></script>

<c:set var="mcmd" value="insert"/>
<c:if var="mcmd" test="${param.cmd == 'edit'}"><c:set var="mcmd" value="update"/></c:if>


<script language="javascript" >
	function frmSubmit() {
        var  frm = document.getElementById("myform");
        if( frm) {
            if (validate(frm)) {
             frm.submit()
          }
        } else {
            alert("invalid form id");
        }
  }

	function frmReset() {
        var  frm = document.getElementById("myform");
        if( frm) {
            frm.reset();
        }
  }
    function goReturn() {
        <c:url var="urlList" value="${control_action}">
            <c:param name="LISTOP" value="${LISTOP.serializeUrl}"/>
            <c:param name="cmd" value="${mcmd}"/>
            <c:if test="${param.cmd == 'edit'}">
            	<c:param name="group_cd" value="${item.group_cd}" />
            </c:if>
        </c:url>
        document.location = '<mh:out value="${urlList}"/>';
    } 		
</script>	
<mf:form action="${control_action}" method="post" name="myform" id="myform" onSubmit="return frmSubmit(this,'list');return false; ">
<table border="0" cellpadding="2" cellspacing="1" class="view">
  <mf:input type="hidden" name="LISTOP" value="${LISTOP.serializeUrl}"/>
  <mf:input type="hidden" name="ori_group_cd" value="${item.group_cd }"/>
  <mf:input type="hidden" name="cmd" value=""/>
  <tr>
    <th class="list" nowrap><span id='lbl_required'>*</span>그룹코드</th> 
    <td class="list"><mf:input type="text" name="group_cd" size="10" maxlength="10" value="${item.group_cd}" hname="그룹코드" required="true" />
	</td>
  </tr>
  <tr>
    <th class="list" nowrap><span id='lbl_required'>*</span>그룹명</th> 
    <td class="list"><mf:input type="text" name="group_name" size="20" maxlength="100" value="${item.group_name}" hname="그룹명" required="true" />
    </td>
  </tr>
  <tr>
    <th class="list" nowrap>비고</th> 
    <td class="list"><mf:input type="text" name="bigo" size="20" maxlength="100" value="${item.bigo}"/>
    </td>
  </tr>  
  <tr>
    <th class="list" nowrap>수정가능여부</th> 
    <td class="list"><mf:select name="fixed_yn" codeGroup="SYSTEM_YN" curValue="${item.fixed_yn}"/></td>
  </tr>
  
	<tr>
		<td colspan="2" align="center">
			<mf:button bundle="button" key="save" onclick="frmSubmit()"/>
			<mf:button bundle="button" key="reset" onclick="frmReset()" />
			<mf:button bundle="button" key="goList" onclick="goReturn()"/></td>
	</tr>
</table>
</mf:form>