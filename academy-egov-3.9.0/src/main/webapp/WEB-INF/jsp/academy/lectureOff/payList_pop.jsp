<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<html>
<head>
</head>
<body>
<!--content -->
<div id="content_pop" style="padding-left:10px;">
<form id="frm" name="frm" method="post" action="">
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}"/>
<input type="hidden" id="ADDBOOKAREA" name="ADDBOOKAREA" value="${params.ADDBOOKAREA}"/>
<input type="hidden" id="BRIDGE_LECCODE" name="BRIDGE_LECCODE" value="${params.BRIDGE_LECCODE}"/>

	<input type="hidden" id="MESSAGEID" name="MESSAGEID">
	<input type="hidden" id="MESSAGENM" name="MESSAGENM">
	<h2>● 강의제작 > <strong>수강생명단</strong></h2>

    <!--버튼-->    
    <ul class="boardBtns">
    	<li><a href="javascript:fn_excel();">Excel(전체)</a></li>
   	    <li><a href="javascript:smssend_pop();">SMS보내기</a></li>
   	    <li><a href="javascript:fn_message();">쪽지발송</a></li>
    </ul>
    <!--//버튼-->

    <!-- 총건수 -->
    	<p class="pInto01">&nbsp;<span>총${fn:length(list)}건</span></p>         
    <!-- //총건수 -->        
          
    <!-- 테이블-->
    <table class="table02">
		<tr>
			<th><input id="allCheck"  value="" type="checkbox" onclick="checkAll('allCheck', 'smsname')" /></th>
	        <th width="85">수강자</th>
	        <th>아이디</th>
	        <th>연락처</th>
	        <th>과목명</th>
	        <th>수강금액</th>
	        <th>주문번호</th>
	        <th>주문구분</th>
	        <th>결제일</th>
	        <th>강의시작일</th>
	        <th>강의종료일</th>
	        <th>메모</th>
		</tr>
        <tbody>
	        <c:forEach items="${list}" var="item" varStatus="loop">
				<tr>
					<td><input type="checkbox" name="smsname" id="${item.USERID}" value="${item.USER_NM}"
						<c:if test="${ item.USERID eq null || item.STATUSCODE eq 'DLV230'}">style="display: none;"</c:if>>
					</td>
			    	<td>${item.USER_NM} 
			    		<input type="hidden" name="userphone${loop.index}" id="userphone${loop.index}" value="${fn:replace(item.PHONE_NO,'-', '')}">
			    	</td>
					<td>${item.USERID}</td>
					<td>${item.PHONE_NO}</td>
					<td>${item.SUBJECT_TITLE}</td>
					<td><fmt:formatNumber value="${item.REALPRICE}" /></td>
					<td>${item.ORDERNO}</td>
					<td>${item.PAYNM}</td>
					<td>${item.ISCONFIRM}</td>
					<td>${item.SUBJECT_OPEN_DATE}</td>
					<td>${item.SUBJECT_END_DATE}</td>
					<td>${item.MEMO}</td>
				</tr>
			</c:forEach>
			<c:if test="${empty list}">
				<tr bgColor=#ffffff align=center> 
					<td colspan="8">데이터가 존재하지 않습니다.</td>
				</tr>
			</c:if>	 
        </tbody>
	</table>      
    <!-- //테이블-->
    <!--버튼-->
	<ul class="boardBtns">
	    <li><a href="javascript:self.close();">닫기</a></li>
	</ul>
    <!--//버튼--> 	
	
</form>	
</div>
<!--//content --> 

<script type="text/javascript">
//SMS보내기 
function smssend_pop() {
	
	var tmp = "";
	var userphone = "";
	$("input[name=smsname]").each(function(index){
		if ($(this).is(':checked')) {
			if(tmp == null || tmp == ""){
				tmp = $(this).val();
				userphone = $("#userphone"+index).val();
			}else{
				tmp = tmp + "/" + $(this).val();
				userphone = userphone + "/" + $("#userphone"+index).val();
			}
		}
	});
	
	if(tmp == null || tmp == "" || tmp == undefined){
		alert("받을사람을 선택해 주세요.");
		return;
	}
	/* alert("tmp:"+tmp +"\n"+
			"userphone:"+userphone); */
	window.open('<c:url value="/productOrder/add.pop"/>?userphone=' + userphone + '&smsname=' + escape(encodeURIComponent(tmp))
			 , '_blank', 'location=no,resizable=no,width=1005,height=400,top=0,left=0,scrollbars=no,location=no,scrollbars=no');
}

//쪽지
function fn_message() {
	var tmp ="";
	var tmp_nm="";
	
	$("input[name=smsname]:checked").each(function (index){
		tmp += $(this).attr('id') + ",";
		tmp_nm += $(this).val() + ",";
		//alert(tmp + " : " + tmp_nm);
	});
	if(tmp == null || tmp == "" || tmp == undefined){
		alert("대상이 선택되지 않았습니다.");
		return;
	}
	if(confirm("쪽지를 보내시겠습니까?")) {		
		var last = tmp.lastIndexOf(',');
		tmp = tmp.substr(0,last);
		var last_nm = tmp_nm.lastIndexOf(',');
		tmp_nm = tmp_nm.substr(0,last_nm);
		
		$("#MESSAGEID").val(tmp);
		$("#MESSAGENM").val(tmp_nm);
		window.open('', 'message', 'scrollbars=no,toolbar=no,resizable=yes,width=1027,height=300 ');
		$('#frm').attr('target' ,'message');
		$('#frm').attr('action','<c:url value="/memberManagement/memberCheckMessage.pop"/>').submit();
	}
}

function fn_excel() { //ck 추가 

	$('#frm').attr('action','<c:url value="/lectureOff/payList_excel.do"/>').submit();
}
</script>
</body>
</html>