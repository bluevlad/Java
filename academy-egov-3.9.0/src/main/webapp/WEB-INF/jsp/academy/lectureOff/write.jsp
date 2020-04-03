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
<form name="frm" id="frm" method="post" enctype="multipart/form-data" action="">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}"/>
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}"/>
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}"/>
<input type="hidden" id="SEARCHKIND" name="SEARCHKIND" value="${params.SEARCHKIND}"/>
<input type="hidden" id="SEARCHFORM" name="SEARCHFORM" value="${params.SEARCHFORM}"/>
<input type="hidden" id="SEARCHYEAR" name="SEARCHYEAR" value="${params.SEARCHYEAR}"/>
<input type="hidden" id="SEARCHTYPE" name="SEARCHTYPE" value="${params.SEARCHTYPE}"/>
<input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}"/>
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}"/>
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}"/>
<input type="hidden" id="LEC_TYPE_CHOICE" name="LEC_TYPE_CHOICE" value="${params.LEC_TYPE_CHOICE}">
<input type="hidden" id="SUBJECT_TEACHER" name="SUBJECT_TEACHER" value=""/>
<input type="hidden" id="SUBJECT_SJT_CD" name="SUBJECT_SJT_CD" value=""/>
<input type="hidden" id="SUBJECT_OPTION" name="SUBJECT_OPTION" value=""/>
<input type="hidden" id="SUBJECT_OFF_OPEN_YEAR" name="SUBJECT_OFF_OPEN_YEAR" value=""/> 
<input type="hidden" id="SUBJECT_OFF_OPEN_MONTH" name="SUBJECT_OFF_OPEN_MONTH" value=""/> 
<input type="hidden" id="SUBJECT_OFF_OPEN_DAY" name="SUBJECT_OFF_OPEN_DAY" value=""/>

<input type="hidden" id="SUBJECT_GUBN" name="SUBJECT_GUBN" value=""/>

<input type="hidden" id="COPENDATE" name="COPENDATE" value=""/>
<input type="hidden" id="CLECS" name="CLECS" value=""/>
<input type="hidden" id="CDATE" name="CDATE" value=""/>

	<h2>● 강의관리 > <strong>강의제작</strong></h2>
		
    <ul class="lecWritheTab">
    	<li><a href="javascript:fn_goLecType('D');" <c:if test="${params.LEC_TYPE_CHOICE eq 'D'}">class="active"</c:if>>단과</a></li>
        <li><a href="javascript:fn_goLecType('J');" <c:if test="${params.LEC_TYPE_CHOICE eq 'J'}">class="active"</c:if>>종합반</a></li>
        <li><a href="javascript:fn_goLecType('N');" <c:if test="${params.LEC_TYPE_CHOICE eq 'N'}">class="active"</c:if>>선택형 종합반</a></li>
        <li><a href="javascript:fn_goLecType('P');" <c:if test="${params.LEC_TYPE_CHOICE eq 'P'}">class="active"</c:if>>패키지</a></li>
    </ul>        
    
	<table class="table01">
		<tr>
        	<th width="25%">직종</th>
			<th width="25%">학습형태</th>
			<th width="50%">과목(강사)</th>
		</tr>
        <tr>
			<td>
				<ul class="lecSerial">
				<c:forEach items="${kindlist}" var="item" varStatus="loop">
					<li>${item.NAME} <span><input name="CATEGORY_CD" type="checkbox" value="${item.CODE}"/></span></li>					
				</c:forEach>
          		</ul>          
			</td>
			<td>
				<ul class="lecSerial">
				<c:forEach items="${formlist}" var="item" varStatus="loop">
					<li>${item.NAME} <span><input name="LEARNING_CD" type="checkbox" value="${item.CODE}"/></span></li>
				</c:forEach>
          		</ul>
          	</td>
          	<td>
				<ul class="lecSerial">
				<c:forEach items="${subjectteacherlist}" var="item" varStatus="loop">
					<li>${item.SUBJECT_NM}(${item.USER_NM}) <span><input name="SUBJECT_INFO_ARR" type="radio" value="${item.SUBJECT_CD}#${item.USER_ID}#${item.OFF_PAYMENT}"/></span></li>
				</c:forEach>
          		</ul>
          	</td>
        </tr>
    </table>
	
    <p>&nbsp; </p>
    
	<table class="table01">
		<tr>
        	<th>수강형태</th>
        	<td>
        		<input type="radio" name="SUBJECT_TYPE" value="1" checked="checked" /><span class="txt03">실강</span>
          		<input type="radio" name="SUBJECT_TYPE" value="2" /><span class="txt04">실시간 영상</span>
          	</td>
		</tr>
		<tr>
        	<th>수강신청구분</th>
        	<td>
        		<input type="radio" name="SUBJECT_GUBUN" value="1" checked="checked" /><span class="txt03">방문접수</span>
          		<input type="radio" name="SUBJECT_GUBUN" value="2" /><span class="txt04">온라인접수</span>
          		<input type="radio" name="SUBJECT_GUBUN" value="3" /><span class="txt04">방문접수+온라인접수</span>
          	</td>
		</tr>
		<tr>
			<th>수강인원</th>
			<td><input type="text" id="SUBJECT_MEMBER_CNT" name="SUBJECT_MEMBER_CNT" value="" size="10" maxlength="4" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/> 명</td>
		</tr>
		<tr>
			<th width="160">강의명</th>
        	<td><input type="text" id="SUBJECT_TITLE" name="SUBJECT_TITLE" value="" size="60" maxlength="333" style="background:#FFECEC;"/> "<strong class="txt02">강의명</strong>"에 특수문자 5개는 입력하시수 없습니다.[<span class="txt04"> : + % \ ' </span>]</td>
		</tr>
		<tr>
        	<th>강사료지급률</th>
        	<td><input type="text" id="SUBJECT_TEACHER_PAYMENT" name="SUBJECT_TEACHER_PAYMENT" value="" size="10" maxlength="7" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/> %</td>
		</tr>		
		<tr>
        	<th>강의소개</th>
        	<td><textarea id="SUBJECT_DESC" name="SUBJECT_DESC" cols="100" rows="5" maxlength="1500" style="background:#FFECEC;"></textarea></td>
		</tr>
		<tr>
			<th>키워드</th>
        	<td><input type="text" id="SUBJECT_KEYWORD" name="SUBJECT_KEYWORD" value="" size="60" maxlength="200" style="background:#FFECEC;"/></td>
		</tr>
		
      	<tr>
        	<th>주교재<br/><input name="bookbtn" type="button" value="선택" onClick="fn_BookPop('bookJuArea');"></th>
			<td>
        		<table class="tdTable" id="bookJuArea">
					<tr>
                		<th width="15%">직급</th>
                		<th width="15%">학습형태</th>
                		<th>교재명</th>
                		<th width="10%">삭제</th>
              		</tr>
              		<tr class="basic_space">
                		<td colspan="4">&nbsp;</td>
              		</tr>
            	</table>
			</td>
		</tr>
		
      	<tr>
        	<th>부교재<br/><input name="bookbtn" type="button" value="선택" onClick="fn_BookPop('bookBuArea');"/></th>
			<td>
        		<table class="tdTable" id="bookBuArea">
					<tr>
                		<th width="15%">직급</th>
                		<th width="15%">학습형태</th>
                		<th>교재명</th>
                		<th width="10%">삭제</th>
              		</tr>
              		<tr class="basic_space">
                		<td colspan="4">&nbsp;</td>
              		</tr>
            	</table>
			</td>
		</tr>
		
      	<tr>
        	<th>수강생교재<br/><input name="bookbtn" type="button" value="선택" onClick="fn_BookPop('bookSuArea');"></th>
			<td>
        		<table class="tdTable" id="bookSuArea">
					<tr>
                		<th width="15%">직급</th>
                		<th width="15%">학습형태</th>
                		<th>교재명</th>
                		<th width="10%">삭제</th>
              		</tr>
              		<tr class="basic_space">
                		<td colspan="4">&nbsp;</td>
              		</tr>
            	</table>
			</td>
		</tr>
		<tr>
        	<th>원가</th>
        	<td><input type="text" id="SUBJECT_PRICE" name="SUBJECT_PRICE" value="" size="10" maxlength="7" onKeyUp="fn_DcNum();" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/> 원</td>
		</tr>
		<tr>
			<th>할인율</th>
			<td><input type="text" id="SUBJECT_DISCOUNT" name="SUBJECT_DISCOUNT" value="" size="10" maxlength="3" onKeyUp="fn_DcNum();" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/> %</td>
		</tr>		
		<tr>
			<th>실강료</th>
			<td><input type="text" id="SUBJECT_REAL_PRICE" name="SUBJECT_REAL_PRICE" value="" size="10" maxlength="3" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/> 원</td>
		</tr>		
		<tr>
			<th>사진</th>
			<td><input type="file" id="SUBJECT_SUMNAIL" name="SUBJECT_SUMNAIL" value="" size="30"  /></td>
		</tr>
		<tr>
			<th>회차</th>
			<td>총 <input type="text" id="LEC_SCHEDULE" name="LEC_SCHEDULE" value="" size="10" maxlength="3" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/> 회 강의입니다.</td>
		</tr>
		<tr>
			<th>강의기간</th>
			<td>
				개시일자 <input type="text" id="SUBJECT_OPEN_DATE" name="SUBJECT_OPEN_DATE" value="" style="background:#FFECEC;" readonly="readonly"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="checkbox" name="WEEK1" value="Y" class="weekcls" />일&nbsp;&nbsp;
				<input type="checkbox" name="WEEK2" value="Y" class="weekcls" />월&nbsp;&nbsp;
				<input type="checkbox" name="WEEK3" value="Y" class="weekcls" />화&nbsp;&nbsp;
				<input type="checkbox" name="WEEK4" value="Y" class="weekcls" />수&nbsp;&nbsp;
				<input type="checkbox" name="WEEK5" value="Y" class="weekcls" />목&nbsp;&nbsp; 
				<input type="checkbox" name="WEEK6" value="Y" class="weekcls" />금&nbsp;&nbsp;
				<input type="checkbox" name="WEEK7" value="Y" class="weekcls" />토&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input name="" type="button" value="적용" onClick="javascript:setLecDate();">
				<div id="cal_vw" style="width:800px;height:300px;margin-left:8px;display:none;margin-bottom:3px; overflow-x: scroll; "></div>
          	</td>
		</tr>
		<tr>
        	<th>강의개설</th>
        	<td>
        		<input type="radio" name="SUBJECT_ISUSE" value="Y" checked /><span class="txt03">개설</span>
          		<input type="radio" name="SUBJECT_ISUSE" value="N" /><span class="txt04">폐강</span>
          	</td>
      </tr>
      <tr>
        	<th><span class="txt03">신규강좌여부</th>
        	<td>
        		<input type="radio" name="LEC_GUBUN" value="Y" /><span class="txt03">예</span>
          		<input type="radio" name="LEC_GUBUN" value="N" checked /><span class="txt04">아니오</span>
          	</td>
      </tr>
      <tr>
        	<th><span class="txt03">추천강좌여부</th>
        	<td>
        		<input type="radio" name="REC_GUBUN" value="Y" /><span class="txt03">예</span>
          		<input type="radio" name="REC_GUBUN" value="N" checked /><span class="txt04">아니오</span>
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
var total;
var karr;

function setLecDate() {
	$("#COPENDATE").val($("#SUBJECT_OPEN_DATE").val());
	$("#CLECS").val($("#LEC_SCHEDULE").val());
	
	var WEEKCHK = "";
	$(".weekcls").each(function(idx,el){		
		if($(this).attr("checked")=="checked"){
			WEEKCHK += $(this).val();
		}
		if($(this).attr("name") != "WEEK7"){
			WEEKCHK += ",";
		}
	});
	$("#CDATE").val(WEEKCHK);
	
	var result = "";
	total = 0;
	karr = 0;
	
	var f = document.frm;
	
	if($("#LEC_SCHEDULE").val() == ""){
		alert("먼저 강의회차를 입력하세요");
		return;
	}           
	if($("#SUBJECT_OPEN_DATE").val() == ""){
		alert("먼저 개강일자를 입력하세요");
		return;
	}

	var t = $(".weekcls:checked").length; 
	
	if(t<1) {
		alert('요일을 입력하세요.');
	} else {
		
		var Months = new Array(12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
		
		//lec_start_date 강의시작일
		var syy = $("#SUBJECT_OPEN_DATE").val().substr(0,4); //연도추출
		var smm = $("#SUBJECT_OPEN_DATE").val().substr(4,2); // 월추출
		
		var yy = parseInt(syy,10); // 연도2자리
		var mm = parseInt(smm,10); // 월2자리

		result += "<table border='0' cellpadding='0' cellspacing='0'><tr>";
		
		var ty = 0;
		var tm = 0;
		var i = 0;
		
		//lecture_amount 강의총회차
		while( parseInt($("#LEC_SCHEDULE").val(),10) > total) {
			ty = yy + parseInt((mm+i-1)/12);
			tm = Months[((mm+i)%12)];
			
			result += "<td style='padding:5px;' valign='top'>";
			result += Calendar(ty,tm);
			result += "</td>";
			i++;
		}
		
		result += "</tr></table>";
		
		var ele = document.getElementById("cal_vw");
		ele.innerHTML = result;
		ele.style.display = "block";
	}
}

function Calendar( Year, Month ) {
	var days = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
	var weekDay = new Array("일", "월", "화", "수", "목", "금", "토");

	firstDay = new Date(Year,Month-1,1).getDay();
	
	days[1] = (((Year % 4 == 0) && (Year % 100 != 0)) || (Year % 400 == 0)) ? 29 : 28;
		
	var output_string = "";
	output_string += "<table border='0' cellpadding='0' cellspacing='0' width='246' class='tdTable' >";
	output_string += "<tr><td align='center' style='text-align:center;'><b>";
	output_string += Year + " / " + Month;
	output_string += "</b></td></tr>";
	output_string += "</table>";
	
	output_string += "<table border='0' cellpadding='0' cellspacing='1' bgcolor='#999999' class='tdTable' >";
	output_string += "<tr align='center' bgcolor='#dadada' height='25'> ";
	for (var dayNum= 0; dayNum <= 6; dayNum++) {
		output_string += "<td width='34'>" + weekDay[dayNum] + "</td>";
	}
	output_string += "</tr>";

	var kMonth = (Month < 10) ? "0"+Month : Month;
		
	var nDay = 1;
	
	for(var i=0; i<6; i++) {
		output_string += "<tr align='center'>";
		for(var j=0; j<7; j++) {
			tarr = i*7+j;
			
			if(firstDay <= tarr && days[Month-1] >= nDay ) {
				var kDay = (nDay < 10) ? "0"+nDay : nDay;
				
				var bg_color = "";
				var frm_value = "";
				if (
					$(".weekcls")[j].checked && 
					parseInt($("#LEC_SCHEDULE").val(),10) > total &&
					parseInt((Year +""+ kMonth +""+ kDay),10)  >= parseInt($("#SUBJECT_OPEN_DATE").val(),10)
					) {
					bg_color ="yellow";
					frm_value = Year +""+ kMonth +""+ kDay;					
					total++;
					
				} else {
					bg_color ="#ffffff";
					frm_value = "";
				}
				output_string += "<td id='"+ (Year +""+ kMonth +""+ kDay) +"' style='background-color:"+ bg_color +";cursor:hand;cursor:pointer;' width='24' height='20' ";
				output_string += " onClick=\"javascript:chkDay('"+ parseInt((Year +""+ kMonth +""+ kDay),10)  +"', "+ karr +");\">";
				output_string += nDay
				output_string += "<input type='hidden' name='SAVDAY' value='"+ frm_value +"'>";
				output_string += "</td>";
				
				nDay++;
				karr++;
			} else {
				output_string += "<td bgcolor='#ffffff' width='24' height='20'>"+ "&nbsp;" +"</td>";
			}
		}
	}
	
	output_string += "</tr>";
	output_string += "</table>";
	
	return output_string;
	
}

function chkDay(clkDay, arr) {
	var f = document.frm;
	var ele = document.getElementById(clkDay);
	if( f.SAVDAY[arr].value.length == 0 ) {
		ele.style.backgroundColor = "yellow";
		f.SAVDAY[arr].value = clkDay;
		
	} else {
		ele.style.backgroundColor = "#ffffff";
		f.SAVDAY[arr].value = "";
	}
	
	var t = 0;
	for(var i=0; i<f.SAVDAY.length; i++) {
		if( f.SAVDAY[i].value.length > 0 ) {
			t++;
		}
	}
	$("#LEC_SCHEDULE").val(t);
}
</script>

 
<script type="text/javascript">
$(document).ready(function(){	
	setDateFickerImageUrl("<c:url value='/resources/img/common/icon_calendar01.png'/>");
	initDateFicker2("SUBJECT_OPEN_DATE");	
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');

	// 과목선택시 값 SETTING
	$("input[name='SUBJECT_INFO_ARR']").click(function(){
		$("#SUBJECT_SJT_CD").val($("input[name='SUBJECT_INFO_ARR']:checked").val().split("#")[0]);
		$("#SUBJECT_TEACHER").val($("input[name='SUBJECT_INFO_ARR']:checked").val().split("#")[1]);
		$("#SUBJECT_TEACHER_PAYMENT").val($("input[name='SUBJECT_INFO_ARR']:checked").val().split("#")[2]);
	});
	
	// 교재삭제
	$(document).on("click","input[name='BTN_BOOKDEL']",function(){
		$(this).parent().parent().remove();
	});
});

// 교재팝업
function fn_BookPop(type){
	window.open('<c:url value="/lectureOff/bookList.pop"/>?ADDBOOKAREA=' + type, '_blank', 'scrollbars=no,toolbar=no,resizable=yes,width=1040,height=670');
}

// 목록으로
function fn_List(){
	$("#frm").attr("action","<c:url value='/lectureOff/list.do' />");
	$("#frm").submit();
}

// 숫자만입력
function fn_OnlyNumber(obj) {
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
	}else{
		event.returnValue = false;
	}
}

// 할인율 적용
function fn_DcNum() {
		var dc = parseFloat($("#SUBJECT_DISCOUNT").val()) / 100;
		var realprice = $("#SUBJECT_PRICE").val() - $("#SUBJECT_PRICE").val() * dc;
//		$("#SUBJECT_REAL_PRICE").val(Math.floor(realprice/10)*10);		
		$("#SUBJECT_REAL_PRICE").val(Math.floor(realprice));		
		if($("#SUBJECT_DISCOUNT").val() == "NaN"){
			$("#SUBJECT_DISCOUNT").val(0);
		}
		if($("#SUBJECT_PRICE").val() == "NaN"){
			$("#SUBJECT_PRICE").val(0);
		}
		if($("#SUBJECT_REAL_PRICE").val() == "NaN"){
			$("#SUBJECT_REAL_PRICE").val(0);
		}			
}

// 등록
function fn_Submit(){
	if(1 > $("input[name='CATEGORY_CD']:checked").size()){
		alert("직종 항목을 선택하세요");
		$("input[name='CATEGORY_CD']")[0].focus();
		return;
	}
	if(1 > $("input[name='LEARNING_CD']:checked").size()){
		alert("학습형태 항목을 선택하세요");
		$("input[name='LEARNING_CD']")[0].focus();
		return;
	}	
	if(1 != $("input[name='SUBJECT_INFO_ARR']:checked").size()){
		alert("과목 항목을 한개 선택하세요");
		$("input[name='SUBJECT_INFO_ARR']")[0].focus();
		return;
	}
 	if($.trim($("#SUBJECT_MEMBER_CNT").val()) == ""){
 		alert("수강인원을 입력하세요");
 		$("#SUBJECT_MEMBER_CNT").focus();
        return;
 	} 	
 	if($.trim($("#SUBJECT_TITLE").val()) == ""){
 		alert("강의명을 입력하세요");
 		$("#SUBJECT_TITLE").focus();
        return;
 	}
 	if($.trim($("#SUBJECT_TEACHER_PAYMENT").val()) == ""){
 		alert("강사료지급률을 입력하세요");
 		$("#SUBJECT_TEACHER_PAYMENT").focus();
        return;
 	}	 	 	
 	if($.trim($("#SUBJECT_DESC").val()) == ""){
 		alert("강의소개를 입력하세요");
 		$("#SUBJECT_DESC").focus();
        return;
 	} 	
 	if($.trim($("#SUBJECT_KEYWORD").val()) == ""){
 		alert("키워드를 입력하세요");
 		$("#SUBJECT_KEYWORD").focus();
        return;
 	} 	
 	if($.trim($("#SUBJECT_PRICE").val()) == ""){
 		alert("원가를 입력하세요");
 		$("#SUBJECT_PRICE").focus();
        return;
 	} 	
 	if($.trim($("#SUBJECT_DISCOUNT").val()) == ""){
 		alert("할인율을 입력하세요");
 		$("#SUBJECT_DISCOUNT").focus();
        return;
 	} 	
 	if($.trim($("#SUBJECT_REAL_PRICE").val()) == ""){
 		alert("실강료를 입력하세요");
 		$("#SUBJECT_REAL_PRICE").focus();
        return;
 	} 	
 	if($.trim($("#LEC_SCHEDULE").val()) == ""){
 		alert("회차를 입력하세요");
 		$("#LEC_SCHEDULE").focus();
        return;
 	}
 	if($.trim($("#SUBJECT_OPEN_DATE").val()) == ""){
 		alert("게시일자를 입력하세요");
 		$("#SUBJECT_OPEN_DATE").focus();
        return;
 	}

	var WEEKCHK = "";
	$(".weekcls").each(function(idx,el){		
		if($(this).attr("checked")=="checked"){
			WEEKCHK += $(this).val();
		}
		if($(this).attr("name") != "WEEK7"){
			WEEKCHK += ",";
		}
	});
 	
 	if( $("#COPENDATE").val() != $("#SUBJECT_OPEN_DATE").val()   ||  $("#CLECS").val() != $("#LEC_SCHEDULE").val()   ||  $("#CDATE").val() != WEEKCHK  ){
 		alert("회차 , 시작일자 , 강의요일을 정확히 입력 후 적용버튼을 눌러 날짜를 확인해주세요");
 		return;
 	}
 	 	
	if(confirm("강의를 등록하시겠습니까?")){
		$("#bookJuArea").find("input[name='RSC_ID']").each(function(idx,el){
			$(this).attr("name","JU_RSC_ID");
		});		
		$("#bookBuArea").find("input[name='RSC_ID']").each(function(idx,el){
			$(this).attr("name","BU_RSC_ID");
		});
		$("#bookSuArea").find("input[name='RSC_ID']").each(function(idx,el){
			$(this).attr("name","SU_RSC_ID");
		});
		$("#frm").attr("enctype","multipart/form-data");
		$("#frm").attr("action","<c:url value='/lectureOff/save.do' />");
		$("#frm").submit();
	}
}

function fn_goLecType(val){
	$("#LEC_TYPE_CHOICE").val(val);
	if(val=="D"){
		$("#frm").attr("action", "<c:url value='/lectureOff/write.do' />");
	}else{
		$("#frm").attr("action", "<c:url value='/lectureOff/jongWrite.do' />");
	}
	$("#frm").submit();	
}
</script>
</body>
</html>