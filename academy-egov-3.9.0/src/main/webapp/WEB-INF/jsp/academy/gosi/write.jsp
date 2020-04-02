<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head></head>
<body>

<!--content -->
<div id="content">
<form name="frm" id="frm" method="post" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}">
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}">
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}">
<input type="hidden" id="SEARCHTYPE" name="SEARCHTYPE" value="${params.SEARCHTYPE}"/>
<input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}"/>
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}">

	<h2>● 국가 고시 > <strong>샘플아이디 등록</strong></h2>	
   	<table class="table01">
   		<tr>
   			<th>모집구분1</th>
  			<td colspan="3">
	   			<input type="text" id="AREA_01" name="AREA_01" value="" size="20"  maxlength="150" title="모집구분1" style="width:50%;background:#FFECEC;"/>
  			</td>			
  		</tr>
  		<tr>
  			<th>모집구분2</th>
  			<td colspan="3">
	   			<input type="text" id="AREA_02" name="AREA_02" value="" size="20"  maxlength="150" title="모집구분2" style="width:50%;background:#FFECEC;"/>
  			</td>  		
  		</tr>

		<tr>
	   			<th>응시번호</th>
	  			<td colspan="3">
		   			<input type="text" id="RST_NO" name="RST_NO" value="" size="8"  maxlength="150" title="응시번호" style="width:50%;background:#FFECEC;" onKeyDown="onOnlyNumber(this);"/>
	  			</td>	  			
	  	</tr>
	  	
		<tr>
					
  			<th>성명</th>
  			<td>
	   			<input type="text" id="USER_NM" name="USER_NM" value="" size="20"  maxlength="150" title="성명" style="width:50%;background:#FFECEC;"/>
  			</td>
  			
  			<th>나이</th>
  			<td>
	   			<input type="text" id="USER_AGE" name="USER_AGE" value="" size="20"  maxlength="150" title="나이" style="width:50%;background:#FFECEC;" onKeyDown="onOnlyNumber(this);"/>
  			</td>
  		</tr>
  		
  		<tr>
   			<th>준비기간</th>
  			<td>
	   			<input type="text" id="STUDY_WAIT" name="STUDY_WAIT" value="" size="20"  maxlength="150" title="준비기간" style="width:50%;background:#FFECEC;"/>
  			</td>
  			
  			<th>학습형태</th>
  			<td>
	   			<input type="text" id="STUDY_TYPE" name="STUDY_TYPE" value="" size="20"  maxlength="150" title="학습형태" style="width:50%;background:#FFECEC;"/>
  			</td>			 			
  		</tr>
  		
  		<tr>
  			<th>가산점</th>
  			<td>
	   			<input type="text" id="ADD_POINT" name="ADD_POINT" value="" size="20"  maxlength="150" title="가산점" style="width:50%;background:#FFECEC;" onKeyDown="onOnlyNumber(this);"/>%
  			</td>
  			
  			<th>합격률</th>
  			<td>
	   			<input type="text" id="EXAM_STAT" name="EXAM_STAT" value="" size="20"  maxlength="150" title="합격률" style="width:50%;background:#FFECEC;" onKeyDown="onOnlyNumber(this);"/>%
  			</td>
  		</tr>
  		
  		<tr>
   			<th>선택과목1</th>
  			<td colspan="3">
	   			<input type="text" id="SEL_SBJ_01" name="SEL_SBJ_01" value="" size="20"  maxlength="150" title="선택과목1" style="width:50%;background:#FFECEC;"/>
  			</td>		
  		</tr>
  		<tr>
  			<th>선택과목2</th>
  			<td colspan="3">
	   			<input type="text" id="SEL_SBJ_02" name="SEL_SBJ_02" value="" size="20"  maxlength="150" title="선택과목2" style="width:50%;background:#FFECEC;"/>
  			</td>
  		</tr>
		
		<tr>
  			<th>국가직 국어</th>
  			<td>
	   			<input type="text" id="SBJ_01" name="SBJ_01" value="" size="20"  maxlength="150" title="국가직 국어" style="width:50%;background:#FFECEC;" onKeyDown="onOnlyNumber(this);"/>
  			</td>
  			
  			<th>모의고사 국어</th>
  			<td>
	   			<input type="text" id="SBJ_MO_01" name="SBJ_MO_01" value="" size="20"  maxlength="150" title="모의고사 국어" style="width:50%;background:#FFECEC;" onKeyDown="onOnlyNumber(this);"/>
  			</td>
  		</tr>
  		
  		<tr>
  			<th>국가직 영어</th>
  			<td>
	   			<input type="text" id="SBJ_02" name="SBJ_02" value="" size="20"  maxlength="150" title="국가직 영어" style="width:50%;background:#FFECEC;" onKeyDown="onOnlyNumber(this);"/>
  			</td>
  			
  			<th>모의고사 영어</th>
  			<td>
	   			<input type="text" id="SBJ_MO_02" name="SBJ_MO_02" value="" size="20"  maxlength="150" title="모의고사 영어" style="width:50%;background:#FFECEC;" onKeyDown="onOnlyNumber(this);"/>
  			</td>
  		</tr>
  		
  		<tr>
  			<th>국가직 한국사</th>
  			<td>
	   			<input type="text" id="SBJ_03" name="SBJ_03" value="" size="20"  maxlength="150" title="국가직 한국사" style="width:50%;background:#FFECEC;" onKeyDown="onOnlyNumber(this);"/>
  			</td>
  			
  			<th>모의고사 한국사</th>
  			<td>
	   			<input type="text" id="SBJ_MO_03" name="SBJ_MO_03" value="" size="20"  maxlength="150" title="모의고사 한국사" style="width:50%;background:#FFECEC;" onKeyDown="onOnlyNumber(this);"/>
  			</td>
  		</tr>
  		
  		<tr>
  			<th>국가직 선택과목1</th>
  			<td>
	   			<input type="text" id="SBJ_04" name="SBJ_04" value="" size="20"  maxlength="150" title="국가직 선택과목1" style="width:50%;background:#FFECEC;" onKeyDown="onOnlyNumber(this);"/>
  			</td>
  			
  			<th>모의고사 선택과목1</th>
  			<td>
	   			<input type="text" id="SBJ_MO_04" name="SBJ_MO_04" value="" size="20"  maxlength="150" title="모의고사 선택과목1" style="width:50%;background:#FFECEC;" onKeyDown="onOnlyNumber(this);"/>
  			</td>
  		</tr>
  		
  		<tr>
  			<th>국가직 선택과목2</th>
  			<td>
	   			<input type="text" id="SBJ_05" name="SBJ_05" value="" size="20"  maxlength="150" title="국가직 선택과목2" style="width:50%;background:#FFECEC;" onKeyDown="onOnlyNumber(this);"/>
  			</td>
  			
  			<th>모의고사 선택과목2</th>
  			<td>
	   			<input type="text" id="SBJ_MO_05" name="SBJ_MO_05" value="" size="20"  maxlength="150" title="모의고사 선택과목2" style="width:50%;background:#FFECEC;" onKeyDown="onOnlyNumber(this);"/>
  			</td>
  		</tr>
  		
  		<tr>
  			<th>활성 / 비활성</th>
  			<td colspan="3">
	   			<input type="radio" id="ISUSE" name="ISUSE"  value="Y" /> 활성
	   			<input type="radio" id="ISUSE" name="ISUSE"  value="N" /> 비활성
  			</td>
  		</tr>
  		
  		<tr>
  			<th>성별</th>
  			<td colspan="3">
	   			<input type="radio" id="ISUSE" name="USER_SEX"  value="M" />남자
	   			<input type="radio" id="ISUSE" name="USER_SEX"  value="F" /> 여자
  			</td>
  		</tr>
	</table>
    
    <!--버튼-->
	<ul class="boardBtns">
   		<li><a href="javascript:fn_Submit();">등록</a></li>
		<li><a href="javascript:fn_List();">목록</a></li>
    </ul>
    <!--//버튼-->
     
</form>
</div>
<!--//content --> 

<script type="text/javascript">
$(document).ready(function(){
	$("#AREA_01").focus();
});


//목록
function fn_List(){
	$("#frm").attr("action","<c:url value='/gosi/Gosi_sample_user_list.do' />");
	$("#frm").submit();
}

//등록
function fn_Submit(){
	
	if($.trim($("#AREA_01").val())==""){
		alert("모집구분1을 입력해주세요");
		$("#AREA_01").focus();
		return;
	}
	
	if($.trim($("#AREA_02").val())==""){
		alert("모집구분2를 입력해주세요");
		$("#AREA_02").focus();
		return;
	}
	
	if($.trim($("#RST_NO").val())==""){
		alert("응시번호를 입력해주세요");
		$("#RST_NO").focus();
		return;
	}
	
	if($.trim($("#USER_NM").val())==""){
		alert("이름을 입력해주세요");
		$("#USER_NM").focus();
		return;
	}
	
	if($.trim($("#USER_AGE").val())==""){
		alert("나이를 입력해주세요");
		$("#USER_AGE").focus();
		return;
	}
	
	if($.trim($("#STUDY_WAIT").val())==""){
		alert("준비기간을 입력해주세요");
		$("#STUDY_WAIT").focus();
		return;
	}
	
	if($.trim($("#STUDY_TYPE").val())==""){
		alert("학습형태를 입력해주세요");
		$("#STUDY_TYPE").focus();
		return;
	}
	
	if($.trim($("#ADD_POINT").val())==""){
		alert("가산점을 입력해주세요");
		$("#ADD_POINT").focus();
		return;
	}
	
	if($.trim($("#EXAM_STAT").val())==""){
		alert("합격률을 입력해주세요");
		$("#EXAM_STAT").focus();
		return;
	}
	
	if($.trim($("#SEL_SBJ_01").val())==""){
		alert("선택과목1를 입력해주세요");
		$("#SEL_SBJ_01").focus();
		return;
	}
	
	if($.trim($("#SEL_SBJ_02").val())==""){
		alert("선택과목2를 입력해주세요");
		$("#SEL_SBJ_02").focus();
		return;
	}
	
	if($.trim($("#SBJ_01").val())==""){
		alert("국가직 국어 점수를 입력해주세요");
		$("#SBJ_01").focus();
		return;
	}
	
	if($.trim($("#SBJ_MO_01").val())==""){
		alert("모의고사 국어 점수를 입력해주세요");
		$("#SBJ_MO_01").focus();
		return;
	}
	
	if($.trim($("#SBJ_02").val())==""){
		alert("국가직 영어 점수를 입력해주세요");
		$("#SBJ_02").focus();
		return;
	}
	
	if($.trim($("#SBJ_MO_02").val())==""){
		alert("모의고사 영어 점수를 입력해주세요");
		$("#SBJ_MO_02").focus();
		return;
	}
	
	if($.trim($("#SBJ_03").val())==""){
		alert("국가직 한국사 점수를 입력해주세요");
		$("#SBJ_03").focus();
		return;
	}
	
	if($.trim($("#SBJ_MO_03").val())==""){
		alert("모의고사 한국사 점수를 입력해주세요");
		$("#SBJ_MO_03").focus();
		return;
	}
	
	if($.trim($("#SBJ_04").val())==""){
		alert("국가직 선택과목1 점수를 입력해주세요");
		$("#SBJ_04").focus();
		return;
	}
	
	if($.trim($("#SBJ_MO_04").val())==""){
		alert("모의고사 선택과목1 점수를 입력해주세요");
		$("#SBJ_MO_04").focus();
		return;
	}
	
	if($.trim($("#SBJ_05").val())==""){
		alert("국가직 선택과목2 점수를 입력해주세요");
		$("#SBJ_05").focus();
		return;
	}
	
	if($.trim($("#SBJ_MO_05").val())==""){
		alert("모의고사 선택과목2 점수를 입력해주세요");
		$("#SBJ_MO_05").focus();
		return;
	}
	
	if($.trim($("#SBJ_MO_05").val())==""){
		alert("모의고사 선택과목2 점수를 입력해주세요");
		$("#SBJ_MO_05").focus();
		return;
	}
	
	if($("input[name=ISUSE]:radio:checked").length == 0){
		alert("활성 / 비활성을 체크해주세요");
		return;
	}
	
	if($("input[name=USER_SEX]:radio:checked").length == 0){
		alert("성별을 체크해주세요");
		return;
	}
	
	var dataString = $("#frm").serialize(); // frm은 form 이름

	$.ajax({
	     
	     type: "POST", 
	     url : '<c:url value="/gosi/Gosi_save.do"/>?',
	     data: dataString,
	     dataType: "text",  
	     async : false,
	     success: function(RES) {
	      if($.trim(RES)=="Y"){           
	        alert("샘플 아이디가 등록 되었습니다.");
	        $("#frm").attr("action","<c:url value='/gosi/Gosi_sample_user_list.do' />");
	        $("#frm").submit();
	        return;
	      }
	     },error: function(){
	      alert("등록 실패");
	      return;
	     }
	  });
}

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


</script>
</body>
</html>