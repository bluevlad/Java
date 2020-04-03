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

	<h2>● 강의관리 > <strong>강의마스터</strong></h2>
		
    
	<table class="table01">
		<tr>
			<th width="50%">과목(강사)&nbsp;&nbsp;&nbsp;
			검색어 : <input type="text" id="TEACHER_NM" name="TEACHER_NM" value="${params.SEARCHTEXT}" size="40" title="검색어" onkeypress="fn_subject_teacher_search()">
			<input type="button" onclick="fn_subject_teacher_search()" value="검색" />
			</th>
		</tr>
        <tr>
          	<td>
				<ul class="lecSerial">
				<div id="div_subject_teacher_search">
				</div>
          		</ul>
          	</td>
        </tr>
        <!--  
        <tr>
          <td class="tdCenter"><input name="" type="button" value="직급추가"></td>
          <td class="tdCenter"><input name="" type="button" value="형태추가"></td>
          <td class="tdCenter"><input name="" type="button" value="과목추가"></td>
        </tr>
        -->
    </table>
	
    <p>&nbsp; </p>
    
	<table class="table01">
		<tr>
			<th width="160">강의명</th>
        	<td><input type="text" id="SUBJECT_TITLE" name="SUBJECT_TITLE" value="" size="60" maxlength="333" style="background:#FFECEC;"/> "<strong class="txt02">강의명</strong>"에 특수문자 5개는 입력하시수 없습니다.[<span class="txt04"> : + % \ ' </span>]</td>
		</tr>
		<tr>
			<th>강의예정회차</th>
			<td>총 <input type="text" id="LEC_SCHEDULE" name="LEC_SCHEDULE" value="" size="10" maxlength="3" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/> 회 강의입니다.</td>
		</tr>
		<tr>
        	<th>강의요약</th>
        	<td><textarea id="SUBJECT_MEMO" name="SUBJECT_MEMO" cols="100" rows="5" maxlength="1500" style="background:#FFECEC;"></textarea></td>
		</tr>
		<tr>
        	<th>강의개요</th>
        	<td><textarea id="SUBJECT_DESC" name="SUBJECT_DESC" cols="100" rows="5" maxlength="1500" style="background:#FFECEC;"></textarea></td>
		</tr>
		<tr>
			<th>기간</th>
			<td>
				<input type="text" id="SUBJECT_PERIOD" name="SUBJECT_PERIOD" value="" size="10" maxlength="4" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/>일 
					&nbsp;&nbsp;&nbsp;&nbsp;
					시작일 <input type="text" id="SUBJECT_OFF_OPEN_CAL" name="SUBJECT_OFF_OPEN_CAL" value="" style="background:#FFECEC;" readonly="readonly"/>
          	</td>
		</tr>
		<tr>
        	<th>원가</th>
        	<td><input type="text" id="SUBJECT_PRICE" name="SUBJECT_PRICE" value="" size="10" maxlength="7" onKeyUp="fn_DcNum();" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/> 원</td>
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
        	<th><span class="txt05">동영상 기본경로(500k)</span> </th>
        	<!-- <td><input type="text" id="SUBJECT_VOD_DEFAULT_PATH" name="SUBJECT_VOD_DEFAULT_PATH" value="mms://willbes.gscdn.com/" size="100" class="txt05" maxlength="400"/></td> -->
        	<td><input type="text" id="SUBJECT_VOD_DEFAULT_PATH" name="SUBJECT_VOD_DEFAULT_PATH" value="http://hd.willbes.gscdn.com/" size="100" class="txt05" maxlength="400"/></td>
      	</tr>
      	<tr>
        	<th><span class="txt06">MP4 기본경로</span></th>
        	<td><input type="text" id="SUBJECT_MP4_DEFAULT_PATH" name="SUBJECT_MP4_DEFAULT_PATH" value="http://hd.willbes.gscdn.com/" size="100" class="txt06" maxlength="400"/></td>
      	</tr>
      	<tr>
    	    <th><span class="txt07">PMP 기본경로</span></th>
	        <td><input type="text" id="SUBJECT_PMP_DEFAULT_PATH" name="SUBJECT_PMP_DEFAULT_PATH" value="mms://1.224.163.230/" size="100" class="txt07" maxlength="400"/></td>
      	</tr>
		<tr>
        	<th>옵션</th>
        	<td>
	        	<input name="SUBJECT_OPTION_ARR" type="checkbox" value="1"/><span class="txt05">동영상개설(500k)</span>
				<input name="SUBJECT_OPTION_ARR" type="checkbox" value="AA"/><span class="txt06">MP4</span>
				<input name="SUBJECT_OPTION_ARR" type="checkbox" value="2"/><span class="txt07">PMP개설</span>
				<input name="SUBJECT_OPTION_ARR" type="checkbox" value="3"/><span class="txt05">동영상</span>+<span class="txt07">PMP</span>
	          	<input name="SUBJECT_OPTION_ARR" type="checkbox" value="BB"/><span class="txt05">동영상</span>+<span class="txt02">모바일</span>
			</td>
      	</tr>
		<tr>
			<th>사용여부</th>
			<td>
				<input type="radio" name="MST_USE_YN" value="Y" checked /><span class="txt03">활성</span>
				<input type="radio" name="MST_USE_YN" value="N" /><span class="txt04">비활성</span>
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
	setDateFickerImageUrl("<c:url value='/resources/img/common/icon_calendar01.png'/>");
	initDateFicker2("SUBJECT_OFF_OPEN_CAL");	
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');


	
	// 교재삭제
	$(document).on("click","input[name='BTN_BOOKDEL']",function(){
		$(this).parent().parent().remove();
	});
	
	fn_subject_teacher_search();
});

// 교재팝업
function fn_BookPop(type){
	window.open('<c:url value="/lecture/bookList.pop"/>?ADDBOOKAREA=' + type, '_blank', 'scrollbars=no,toolbar=no,resizable=yes,width=1040,height=670');
}

// 목록으로
function fn_List(){
	$("#frm").attr("action","<c:url value='/lecture/list.do' />");
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
		$("#SUBJECT_MOVIE").val($("#SUBJECT_PRICE").val() - $("#SUBJECT_PRICE").val() * dc);
		$("#SUBJECT_PMP").val($("#SUBJECT_PRICE").val() - $("#SUBJECT_PRICE").val() * dc);
		$("#SUBJECT_MOVIE_PMP").val( Math.floor((parseInt($("#SUBJECT_MOVIE").val()) + parseInt($("#SUBJECT_PMP").val())) * 0.8));
		
		if($("#SUBJECT_MOVIE").val() == "NaN"){
			$("#SUBJECT_MOVIE").val(0);
		}
		if($("#SUBJECT_PMP").val() == "NaN"){
			$("#SUBJECT_PMP").val(0);
		}		
}

// 등록
function fn_Submit(){
	if(1 != $("input[name='SUBJECT_INFO_ARR']:checked").size()){
		alert("과목 항목을 한개 선택하세요");
		$("input[name='SUBJECT_INFO_ARR']")[0].focus();
		return;
	}
 	if($.trim($("#SUBJECT_TITLE").val()) == ""){
 		alert("강의명을 입력하세요");
 		$("#SUBJECT_TITLE").focus();
        return;
 	}
 	if($.trim($("#LEC_SCHEDULE").val()) == ""){
 		alert("강의예정회차를 입력하세요");
 		$("#LEC_SCHEDULE").focus();
        return;
 	}
 	if($.trim($("#SUBJECT_MEMO").val()) == ""){
 		alert("강의요약을 입력하세요");
 		$("#SUBJECT_MEMO").focus();
        return;
 	} 	
 	if($.trim($("#SUBJECT_DESC").val()) == ""){
 		alert("강의개요를 입력하세요");
 		$("#SUBJECT_DESC").focus();
        return;
 	}
 	if($.trim($("#SUBJECT_PERIOD").val()) == ""){
 		alert("기간을 입력하세요");
 		$("#SUBJECT_PERIOD").focus();
        return;
 	}	
 	if($.trim($("#SUBJECT_OFF_OPEN_CAL").val()) == ""){
 		alert("시작일을 입력하세요");
 		$("#SUBJECT_OFF_OPEN_CAL").focus();
        return;
 	} 	
 	if($.trim($("#SUBJECT_PRICE").val()) == ""){
 		alert("원가를 입력하세요");
 		$("#SUBJECT_PRICE").focus();
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
		var subjectoption = "";
		$("input[name='SUBJECT_OPTION_ARR']:checked").each(function(idx,el){
			if(subjectoption!="")	
				subjectoption += ",";
			subjectoption += $(this).val();
		});		
		$("#SUBJECT_OPTION").val(subjectoption);
		if($.trim($("#SUBJECT_OFF_OPEN_CAL").val()) != ""){
			$("#SUBJECT_OFF_OPEN_YEAR").val($("#SUBJECT_OFF_OPEN_CAL").val().substr(0,4));
			$("#SUBJECT_OFF_OPEN_MONTH").val($("#SUBJECT_OFF_OPEN_CAL").val().substr(4,2));
			$("#SUBJECT_OFF_OPEN_DAY").val($("#SUBJECT_OFF_OPEN_CAL").val().substr(6,2));
		}
		
		$("#frm").attr("action","<c:url value='/lecturemst/save.do' />");
		$("#frm").submit();
	}
}

function fn_Enter(){
	$("#SEARCHTEXT").keyup(function(e)  {
		if(e.keyCode == 13) 
			fn_subject_teacher_search();
	});
}

function fn_subject_teacher_search(){
	var param =  "&TEACHER_NM=" + $('#TEACHER_NM').val();
	  $.ajax({
		    type : "POST"
		    ,url : "/lecturemst/subject_teacher_search.pop?"
		    ,data :  param
		    ,success : function (data){
		     	$('#div_subject_teacher_search').html(data);
		     	
		    	// 과목선택시 값 SETTING
		    	$("input[name='SUBJECT_INFO_ARR']").click(function(){
		    		$("#SUBJECT_SJT_CD").val($("input[name='SUBJECT_INFO_ARR']:checked").val().split("#")[0]);
		    		$("#SUBJECT_TEACHER").val($("input[name='SUBJECT_INFO_ARR']:checked").val().split("#")[1]);
		    		//$("#SUBJECT_TEACHER_PAYMENT").val($("input[name='SUBJECT_INFO_ARR']:checked").val().split("#")[2]);
		    	});
		    }
		});	
}

</script>
</body>
</html>