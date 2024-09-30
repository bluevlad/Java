<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>

<html>
<head>
<meta name="decorator" content="index">

</script>
</head>

<form name="frm" id="frm" method="post" action="">
<input type="hidden" name="TCLASS" value="1" />
<input type="hidden" name="userid" value="${userid }" />
    <!--content -->
    <div>
    
    <h2>● 강의관리 > <strong>강의구매이벤트 등록</strong></h2>
    
	<table class="table04">
		<col width="40%"/>
		<col width="30%"/>
		<col width="20%"/>
		<col width="30%"/>
		<tr>
			<th>온라인,모의고사</th>
			<td class="tdLeft" colspan="3">
				<input type="radio" name="ADD_FLAG" value="O" checked="checked"/><span class="txt03">온라인</span>			
				<input type="radio" name="ADD_FLAG" value="M" /><span class="txt03">모의고사</span>	
			</td>
		</tr>
		<tr>
			<th width="160">쿠폰이름</th>
        	<td colspan="3"><input type="text" id="CNAME" name="CNAME" value="" size="120" maxlength="333" /></td>
		</tr>
		<tr>
			<th width="160">쿠폰내용</th>
        	<td colspan="3"><textarea id="CCONTENT" name="CCONTENT" ROWS="5" style="width:99%;"></textarea></td>
		</tr>
		<!-- <tr>
			<th width="160">오프라인 쿠폰 구분</th>
        	<td colspan="3"><input type="text" id="PUB_COUPON_GUBUN" name="PUB_COUPON_GUBUN" value="" size="2" maxlength="2" style="background:#FFECEC;"/></td>
		</tr> -->
		<!-- <tr>
			<th>강의종류</th>
			<td class="tdLeft">
				<select id="DAN_JONG" name="DAN_JONG">
					<option value="" selected="selected">없음</option>
  					<option value="D">단과</option>
  					<option value="J">종합반</option>
				</select>	
			</td>
			<th>학습형태</th>
			<td class="tdLeft">
				<select id="DAN_MENU" name="DAN_MENU">
  					<option value="이론" selected="selected">활성</option>
  					<option value="문제">문제</option>
  					<option value="특강">특강</option>
				</select>	
			</td>
		</tr> -->
		<tr>
			<th>쿠폰사용시작일</th>
			<td class="tdLeft">
				<input type="text" id="EXPDATES" name="EXPDATES" value=""  readonly="readonly"/>
			</td>
			<th class="tdLeft">쿠폰사용종료일</th>
			<td class="tdLeft">
				<input type="text" id="EXPDATEE" name="EXPDATEE" value=""  readonly="readonly"/>
			</td>
		</tr>
		<tr>
			<th>쿠폰사용기간(일)</th>
			<td class="tdLeft">
				<input type="text" id="EXPDAY" name="EXPDAY" value="" onKeyDown="onOnlyNumber(this);" maxlength="3"/>
			</td>
		</tr>
		<tr>
			<th>중복여부</th>
			<td class="tdLeft" colspan="3">
				<input type="radio" name="TCLASSCAT" value="255" checked="checked"/><span class="txt03">중복가능</span>			
				<input type="radio" name="TCLASSCAT" value="" /><span class="txt03">중복불가능</span>	
			</td>
		</tr>
		<tr>
			<th>쿠폰타입</th>
			<td class="tdLeft">
				<input type="radio" name="REGTYPE" value="C" /><span class="txt03">쿠폰(%)</span>			
				<input type="radio" name="REGTYPE" value="P" /><span class="txt03">포인트(원)</span>			
			</td>
			<th>금액</th>
			<td class="tdLeft">
				<input type="text" id="REGPRICE" name="REGPRICE" value="" onKeyDown="onOnlyNumber(this);"/>%(원)
			</td>
		</tr>
		
		<!-- 만들기(컬럼만)는 했으나 안쓴다함. 
		<tr>
			<th>쿠폰타입</th>
			<td class="tdLeft">
				<input type="radio" name="DAN_MENU" value="C" /><span class="txt03">쿠폰</span>			
				<input type="radio" name="DAN_MENU" value="P" /><span class="txt03">포인트</span>			
			</td>
			<th>중복여부</th>
			<td class="tdLeft">
				<input type="radio" name="TCLASS" value="1" /><span class="txt03">중복제외</span>			
				<input type="radio" name="TCLASSCAT" value="255" /><span class="txt03">중복가능</span>	
			</td>
		</tr>
		<tr>
			<th>과목등록</th>
			<td class="tdLeft">
				<input type="text" name="SUBJECT_NM" id="SUBJECT_NM" value="" />  <input type="hidden" name="SUBJECT" id="SUBJECT" value="" /><input type="button" value="과목등록" onclick="javascript:Subject_add()"> 
			</td>
			<th>교수등록</th>
			<td  class="tdLeft">
				<input type="text" name="TEACHER_NM" id="TEACHER_NM" value="" /><input type="hidden" name="TEACHER" id="TEACHER" value="" />  <input type="button" value="교수등록" onclick="javascript:Teacher_add()"> 
			</td>
		</tr>
		<tr>
			<th>쿠폰사용제한</th>
			<td colspan="3" class="tdLeft">
				<input type="radio" name="TERM" value="A" checked/><span class="txt03">제한없음</span>			
				<input type="radio" name="TERM" value="NA" /><span class="txt03">제한</span>		
			</td>
		</tr> -->
    </table>
	
    <p>&nbsp; </p>

    <!--버튼-->
	<ul class="boardBtns">
   		<li><a href="javascript:fn_Submit();">등록</a></li>
		<li><a href="javascript:fn_List();">목록</a></li>
    </ul>
    <!--//버튼-->
</div>
</form>
<!--//content --> 
<script type="text/javascript">
$(document).ready(function(){	
	setDateFickerImageUrl("<c:url value='/resources/img/common/icon_calendar01.png'/>");
	initDateFicker2("EXPDATES");	
	initDateFicker2("EXPDATEE");	
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
});

function onOnlyNumber(obj) {
	 for (var i = 0; i < obj.value.length ; i++){
	  chr = obj.value.substr(i,1);  
	  chr = escape(chr);
	  key_eg = chr.charAt(1);
	  if (key_eg == "u"){
	   key_num = chr.substr(i,(chr.length-1));   
	   if((key_num < "AC00") || (key_num > "D7A3")) { 
	    event.returnValue = false;
	   }    
	  }
	 }
	 if ((event.keyCode >= 48 && event.keyCode <= 57) || (event.keyCode >= 96 && event.keyCode <= 105) || event.keyCode == 8 || event.keyCode == 9) {
	 } else {
	  event.returnValue = false;
	 }
}

//신규쿠폰등록 버튼
function Subject_add()
{
	 window.open('<c:url value="/productOrder/TmCouponSubjectList.pop"/>', '_blank', 'width=830,height=500,top=0,left=0,location=no,resizable=no,scrollbars=yes');	 
}
function Teacher_add()
{
	 window.open('<c:url value="/productOrder/TmCouponTeacherList.pop"/>', '_blank', 'width=830,height=500,top=0,left=0,location=no,resizable=no,scrollbars=yes');	 
}

function fn_Submit(){
	if($.trim($("#CNAME").val())==""){
		alert("쿠폰 이름을 입력해주세요");
		$("#CNAME").focus();
		return;
	}
	if($.trim($("#CCONTENT").val())==""){
		alert("쿠폰내용을 입력해주세요");
		$("#CCONTENT").focus();
		return;
	}
	if($.trim($("#EXPDATES").val())==""){
		alert("쿠폰사용시작을 입력해주세요");
		$("#EXPDATES").focus();
		return;
	}
	if($.trim($("#EXPDATEE").val())==""){
		alert("쿠폰사용종료일을 입력해주세요");
		$("#EXPDATEE").focus();
		return;
	}
	if($.trim($("#EXPDAY").val())==""){
		alert("사용기간을 입력해주세요");
		$("#EXPDAY").focus();
		return;
	}
	
	if($.trim($("#REGPRICE").val())==""){
		alert("포인트및 할인금액을 입력해주세요");
		$("#REGPRICE").focus();
		return;
	}
	
	
	
	$("#frm").attr("action","<c:url value='/productOrder/TmCouponInsert.pop' />");
	$("#frm").submit();
	//self.close();
}

function fn_List(){
	$("#frm").attr("action", "<c:url value='/productOrder/TmCoupon.pop?userid=${userid}' />");
	$("#frm").submit();
}

</script>

</html>