<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>

<html>
<head>
<meta name="decorator" content="index">
<script type="text/javascript">
//숫자 입력 폼
function chk(obj){
	var val = obj.value;
	if (val) {
		if (val.match(/^\d+$/gi) == null) {
			return;
		}       
	}
}

//수강증복원
function insertOrder() {
	if ($('#orderno').val() == "") {
		alert('주문번호를 입력해주세요.');
		$('#orderno').focus();
		return;
	}
	if ($('#user_id').val() == "") {
		alert('수강생아이디를 입력해주세요.');
		$('#user_id').focus();
		return;
	}
	if ($('#leccode').val() == "") {
		alert('단과강의코드를 입력해주세요.');
		$('#leccode').focus();
		return;
	}
	if ($('#price').val() == "") {
		alert('결제금액을 입력해주세요.');
		$('#price').focus();
		return;
	}
	if ($('#tid').val() == "") {
		alert('이니시스에서 부여된 TID를 입력해주세요.');
		$('#tid').focus();
		return;
	}
	
	$.ajax({
		type : "POST",
		url: '<c:url value="/productOrder/insertMissOrder.json"/>?',
		data: $("#orderFrm").serialize(),
		dataType : "text",
		async : false,
		success : function(RES){
			if($.trim(RES) == "Y") {
				alert("주문이 등록되었습니다.");
				window.close();
			}
		},
		error: function(d, json){
			alert("error: "+d.status);
		}
	});
}
</script>
</head>

<!--content -->
<div>
<form id="orderFrm" name="orderFrm" method="post">
<table style="width:100%;">
	<tr>
		<td width="2%"></td>
		<td>
			<table>
      			<tr>
        			<td align="left" bgcolor="#FFFFFF"><h2>▣ 누락주문건 등록 (단과)</h2></td>
      			</tr>
    		</table>
    		<font color="red">*단과강의 주문 누락건만 등록해주세요. 패키지나 프리패스 누락건은 개발팀에 문의해 주세요.</font>
    		<!--테이블-->
			<table class="table04">
				<tr>
		          	<th>주문번호</th>
					<td><input id="orderno" name="orderno" type="text" style="width:150px;" title="주문번호" value=""/></td>
				</tr>
				<tr>
		          	<th>수강생아이디</th>
					<td><input id="user_id" name="user_id" type="text" style="width:150px;" title="수강생아이디" value=""/></td>
				</tr>
				<tr>
		          	<th>단과강의코드</th>
					<td><input id="leccode" name="leccode" type="text" style="width:150px;" title="단과강의코드" value=""/></td>
				</tr>
				<tr>
		          	<th>결제금액</th>
					<td><input id="price" name="price" type="text" style="width:100px;" title="결제금액" value="" onclick="javascript:chk(this)"/></td>
				</tr>
				<tr>
		          	<th>결제수단</th>
					<td>
						<input id="pay_type" name="pay_type" type="radio" title="결제수단" value="PAY110" checked/>카드결제&nbsp;
						<input id="pay_type" name="pay_type" type="radio" title="결제수단" value="PAY120"/>가상계좌&nbsp;
						<input id="pay_type" name="pay_type" type="radio" title="결제수단" value="PAY130"/>실시간계좌이체
					</td>
				</tr>
				<tr>
		          	<th>TID</th>
					<td><input id="tid" name="tid" type="text" style="width:300px;" title="TID" value=""/></td>
				</tr>
			</table>
     		<!--//테이블-->
		    <!--버튼-->    
		    <ul class="boardBtns">
		    	<li><a href="javascript:insertOrder();">주문등록</a></li>
		        <li><a href="javascript:self.close();">닫기</a></li>
		    </ul>
		    <!--//버튼-->
		</td>
		<td width="2%"></td>
	</tr>
</table>
</form>
</div>
<!--//content --> 

</html>