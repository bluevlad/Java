<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>

<head>

<script type="text/javascript">
//쪽지 전송
function fn_Submit(){
	/* if($.trim($("#USER_NM").val())==""){
		alert("수신인을 입력해주세요");
		$("#USER_NM").focus();
		return;
	} */
	if($.trim($("#CONT").val())==""){
		alert("쪽지 내용을 입력해주세요");
		$("#CONT").focus();
		return;
	}
	if(confirm("쪽지를 전송하시겠습니까?")){

		var dataString = $("#frm").serialize(); 
		$.ajax({
			type : 'POST' , 
			url : "<c:url value='/memberAdminManagement/memberAdminSendMessage.do' />",
			data : dataString ,
			async : false,
			success : function(result){
				
				if($.trim(result)=="Y") {
					alert("전송 되었습니다");
					self.close();
				} else {
					alert("전송 된 내역이 없습니다.");
				}
			},error: function(){
				alert("전송 실패");
				return;
			}
		});
	}
}
</script>
</head>


<!--content -->
<div id="content_pop">
<form name="frm" id="frm" class="form form-horizontal"  method="post" action="">
<input type="hidden" name="USER_ID" id="USER_ID" value="${params.USER_ID}" />
	<h2><strong>쪽지</strong></h2>	
    	<table class="tdTable">
   		<tr>
   			<th>수신인</th>
  			<td style="text-align: left;">
  				<input type="text" id="USER_NM" name="USER_NM" value="${params.USER_NM}"  readonly="readonly" size="60"   title="수신인" style="width:90%;background:#FFECEC;"/>
  			</td>
  		</tr>  		
   		<tr>
   			<th>내용</th>
  			<td style="text-align: left;">
	   			<textarea id="CONT" name="CONT" ROWS="3" maxlength="4000" style="width:98%;ime-mode:active;"></textarea>
  			</td>
  		</tr>
	</table>
	<!--//테이블--> 
    
    <!--버튼-->
    <ul class="boardBtns" >
   	  <li><a href="javascript:fn_Submit();">보내기</a></li>
      <li><a href="javascript:self.close();">닫기</a></li>
    </ul>
    <!--//버튼--> 
</form>
</div>
<!--//content --> 
