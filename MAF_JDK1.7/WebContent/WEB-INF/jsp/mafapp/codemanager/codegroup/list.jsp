<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jspf/prelude_jsp12.jspf" %>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td><div class="searchContainer"><mf:form action="${control_action}" method="get" name="myform" id="myform" >
	<mf:input type="text" name="cmd" value="list"/>
    <mf:input type="hidden" size="200" name="LISTOP" value="${LISTOP.serializeUrl}"/>
	<mf:input type="hidden" name="miv_page" value="1"/>
    <mf:input type="hidden" name="group_cd" value=""/>
        	<h1><mfmt:message bundle="common" key="searchtitle"/></h1>
        <table width="100%" border="0" cellspacing="0" cellpadding="2" class="search">
            <tr>
            <tr>
                <td>그룹코드</td>
                <td><mf:input type="text" name="group_cd_dup" value="${LISTOP.ht.group_cd_dup}"/></td>
            </tr>
            <tr>
                <td>그룹명</td>
                <td><mf:input type="text" name="group_name" value="${LISTOP.ht.group_name}"/></td>
            </tr>   
            </table>
            <table border="0" cellspacing="0" cellpadding="2" class="searchBtn">         
             <tr>
                <td colspan="10"><mf:button bundle="button" key="search" onclick="doSearch()"/></td>
             </tr>

        </table></mf:form> </div></td>
    </tr>
    <tr>
        <td>
        <table width="100%" border="0" cellspacing="1" cellpadding="0" class="list">
        <thead>
        <tr>
          <th width="20"><input type="checkbox" name="checkboxAll" 
    							value="allcode"  class="checkbox" 
    							onclick="allChekboxToggle(this,'myform','cd_chk')"></th>
          <th >No </th>
          <th >그룹코드 <mform:THSort id='group_cd'/></th>
          <th >그룹명 <mform:THSort id='group_name'/></th>
          <th >비고<mform:THSort id='bigo'/></th>
          <th >수정가능여부<mform:THSort id='fixed_yn'/></th>
        </tr>
        </thead>
        <tbody>
     <c:forEach var="item" items="${navigator.list}" varStatus="status">
        <tr >
           <td align="center"><mf:input type="checkbox" name="cd_chk" value="${item.group_cd}" /></td>
           <td align=center><mh:listseq navigator="${navigator}" count="${status.count}"/></td>
           <td align=center><a href="javaScript:doView('<mh:out value="${item.group_cd}"/>')"><mh:out value="${item.group_cd}"/></a></td>
           <td align=center><a href="javaScript:doView('<mh:out value="${item.group_cd}"/>')"><mh:out value="${item.group_name}"/></a></td>
           <td align=center><mh:out value="${item.bigo}" td="true"/></td>
           <td align=center><mh:out value="${item.fixed_yn}" codeGroup ="SYSTEM_YN"/></td>
         </tr>
    </c:forEach>
        </tbody>
    </table></td>
    </tr>
    <tr>
    	<td align="center"><mf:button bundle="button" key="register" onclick="doWrite()"/>&nbsp;
    		<mf:button bundle="button" key="delete" onclick="doDelete()"/><br>
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
	function doView(group_cd){
	    var frm = getObject("myform");
			if(frm) {
    		    frm.cmd.value = "view";
                frm.group_cd.value = group_cd;
                
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
	function doDelete() {
		var frm = getObject("myform");
		if(frm) {
			if (checkboxCheckedCount('myform', 'cd_chk') > 0) {
				if(confirm('<mfmt:message bundle="message" key="confirm.delete"/>')) {
					frm.cmd.value = "delete" ;
					frm.submit();
				}
			} else {
				alert('<mfmt:message bundle="message" key="alert.checked"/>');
			}
		}
	}
	
	function checkboxCheckedCount(formName, checkboxName){ 
		var cnt = 0;
		var frm = getObject(formName);
		if (frm) { //form이 존재할 경우		
			var frmchk = document.getElementsByName(checkboxName);	
			if(frmchk != null){ //checkbox가 존재할 경우
				len = frmchk.length;
				if(len == null){ //single checkbox
					if(frmchk.checked)
						cnt++;
				} else { //multi checkbox
					for(i = 0; i < len; i++){
						if(frmchk[i].checked)
							cnt++;
					}
				}
			}
		}
		return cnt;
	} 		
//-->
</SCRIPT>

