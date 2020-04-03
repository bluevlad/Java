<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/libs/cheditor/cheditor.js" /></script>
<script type="text/javascript">
var editor = null;
$(document).ready(function(){	 
	 editor = new cheditor();              // 에디터 개체를 생성합니다.
	 editor.config.editorHeight = '400px';     // 에디터 세로폭입니다.
	 editor.config.editorWidth = '96%';        // 에디터 가로폭입니다.
	 editor.inputForm = 'PLAN';             // textarea의 id 이름입니다. 주의: name 속성 이름이 아닙니다.
	 editor.run();  
});
</script>
</head>
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
<input type="hidden" id="SUBJECT_TEACHER_PAYMENT" name="SUBJECT_TEACHER_PAYMENT" value=""/>
<input type="hidden" id="SUBJECT_SJT_CD" name="SUBJECT_SJT_CD" value=""/>
<input type="hidden" id="SUBJECT_OPTION" name="SUBJECT_OPTION" value=""/>
<input type="hidden" id="SUBJECT_OFF_OPEN_YEAR" name="SUBJECT_OFF_OPEN_YEAR" value=""/> 
<input type="hidden" id="SUBJECT_OFF_OPEN_MONTH" name="SUBJECT_OFF_OPEN_MONTH" value=""/> 
<input type="hidden" id="SUBJECT_OFF_OPEN_DAY" name="SUBJECT_OFF_OPEN_DAY" value=""/>

	<h2>● 강의관리 > <strong>강의제작</strong></h2>
		
    <ul class="lecWritheTab">
    	<li><a href="javascript:fn_goLecType('D');" <c:if test="${params.LEC_TYPE_CHOICE eq 'D'}">class="active"</c:if>>단과</a></li>
        <li><a href="javascript:fn_goLecType('J');" <c:if test="${params.LEC_TYPE_CHOICE eq 'J'}">class="active"</c:if>>종합반</a></li>
        <li><a href="javascript:fn_goLecType('P');" <c:if test="${params.LEC_TYPE_CHOICE eq 'P'}">class="active"</c:if>>패키지</a></li>
        <li><a href="javascript:fn_goLecType('Y');" <c:if test="${params.LEC_TYPE_CHOICE eq 'Y'}">class="active"</c:if>>연회원패키지</a></li>
        <li><a href="javascript:fn_goLecType('F');" <c:if test="${params.LEC_TYPE_CHOICE eq 'F'}">class="active"</c:if>>무료강좌</a></li>
    </ul>    

	<c:if test="${params.LEC_TYPE_CHOICE eq 'J' or params.LEC_TYPE_CHOICE eq 'N'}">
	<table class="table02">
		<tr>
        	<th width="15%">구분</th>
			<th width="85%" class="thLeft"><input type="radio" name="golectype" value="J" onchange="javascript:fn_goLecType('J')"  <c:if test="${params.LEC_TYPE_CHOICE eq 'J'}">checked</c:if>>종합반 <input type="radio" name="golectype" value="N" onchange="javascript:fn_goLecType('N')" <c:if test="${params.LEC_TYPE_CHOICE eq 'N'}">checked</c:if>>선택형 종합반</th>
		</tr>
    </table> 
    </c:if>  
    <p>&nbsp; </p> 
    
	<table class="table01">
		<tr>
        	<th width="25%">직종</th>
			<th width="25%">학습형태</th>
			<th width="50%">
				<c:if test="${params.LEC_TYPE_CHOICE ne 'N'}">필수과목</c:if>
				<c:if test="${params.LEC_TYPE_CHOICE eq 'N'}">필수과목</c:if>
			</th>
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
					<li>
						<table class="tdTable" id="ADDAREA">
				        	<tr>
				            	<th width="15%">직종</th>
				                <th width="15%">학습형태</th>
				                <th>강의명</th>
				                <c:if test="${params.LEC_TYPE_CHOICE eq 'N'}">
				                <th width="10%">순서</th>
				                </c:if>			
				                <th>강사</th>	                
				                <th width="10%">삭제</th>
							</tr>
						</table>
		            </li>
          		</ul>
          	</td>
        </tr>
        <tr>
          <td class="tdCenter">&nbsp;</td>
          <td class="tdCenter">&nbsp;</td>
          <td class="tdCenter"><input id="lecturebtn" name="lecturebtn" type="button" value="단과불러오기" onClick="fn_SubjectPop('ADDAREA');"/></td>
        </tr>        
    </table>
		
    <p>&nbsp; </p>
    
	<table class="table01">
		<c:if test="${params.LEC_TYPE_CHOICE eq 'N'}">
      	<tr>
        	<th>선택과목<br/><input name="lecturebtn" type="button" value="단과불러오기" onClick="fn_SubjectPop('ADDAREA2');"/></th>
			<td>
        		<table class="tdTable" id="ADDAREA2">
					<tr>
		            	<th width="15%">직종</th>
		                <th width="15%">학습형태</th>
		                <th>강의명</th>
		                <c:if test="${params.LEC_TYPE_CHOICE eq 'N'}">
		                <th width="10%">순서</th>
		                </c:if>		  
		                <th>강사</th>    		                
		                <th width="10%">삭제</th>
              		</tr>
              		<tr class="basic_space">
              			<c:if test="${params.LEC_TYPE_CHOICE ne 'N'}">
              				<td colspan="4">&nbsp;</td>
              			</c:if>
              			<c:if test="${params.LEC_TYPE_CHOICE eq 'N'}">
              				<td colspan="5">&nbsp;</td>
              			</c:if>
                		
              		</tr>
            	</table>
			</td>
		</tr>	
		</c:if>
		<tr>
			<th width="160">강의명</th>
        	<td><input type="text" id="SUBJECT_TITLE" name="SUBJECT_TITLE" value="" size="60" maxlength="333" style="background:#FFECEC;" onkeyup="javascript:fn_InputCheckSpecial(this);" /> "<strong class="txt02">강의명</strong>"에 특수문자 5개는 입력하시수 없습니다.[<span class="txt04"> : + % \ ' </span>]</td>
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
			<th>키워드</th>
        	<td><input type="text" id="SUBJECT_KEYWORD" name="SUBJECT_KEYWORD" value="" size="60" maxlength="200" style="background:#FFECEC;"/></td>
		</tr>
		<tr>
			<th>기간</th>
			<td>
				<input type="text" id="SUBJECT_PERIOD" name="SUBJECT_PERIOD" value="" size="10" maxlength="4" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/>일 
					&nbsp;&nbsp;&nbsp;&nbsp;
					시작일 <input type="text" id="SUBJECT_OFF_OPEN_CAL" name="SUBJECT_OFF_OPEN_CAL" value="" style="background:#FFECEC;" readonly="readonly"/>
          	</td>
		</tr>
		<c:if test="${params.LEC_TYPE_CHOICE eq 'N'}">
		<tr>
			<th>선택과목(선택갯수)</th>
			<td><input type="text" id="NO" name="NO" value="" size="8" maxlength="3" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/> 개</td>
		</tr>		
		</c:if>
		<tr>
        	<th>원가</th>
        	<td><input type="text" id="SUBJECT_PRICE" name="SUBJECT_PRICE" value="" size="10" maxlength="7" onKeyUp="fn_DcNum();" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/> 원</td>
		</tr>
		<tr>
			<th>할인율</th>
			<td><input type="text" id="SUBJECT_DISCOUNT" name="SUBJECT_DISCOUNT" value="" size="10" maxlength="3" onKeyUp="fn_DcNum();" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/> %</td>
		</tr>
		<tr>
        	<th>수강료</th>
        	<td>
        		동영상 : <input type="text" id="SUBJECT_MOVIE" name="SUBJECT_MOVIE" value="0" size="12" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/> 
        		PMP : <input type="text" id="SUBJECT_PMP" name="SUBJECT_PMP" value="0" size="12" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/>
        		동영상+PMP : <input type="text" id="SUBJECT_MOVIE_PMP" name="SUBJECT_MOVIE_PMP" value="0" size="12" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/>
        		모바일 : <input type="text" id="SUBJECT_MOVIE_MP4" name="SUBJECT_MOVIE_MP4" value="0" size="12" maxlength="7"/>
        		동영상+모바일 : <input type="text" id="SUBJECT_MOVIE_VOD_MP4" name="SUBJECT_MOVIE_VOD_MP4" value="0" size="12" maxlength="7"/>
        	</td>
		</tr>
		<tr>
        	<th>옵셥</th>
        	<td>
	        	<!-- <input name="SUBJECT_OPTION_ARR" type="checkbox" value="1"/><span class="txt05">동영상개설(500k)</span> -->
	        	<input name="SUBJECT_OPTION_ARR" type="checkbox" value="4"/><span class="txt05">동영상개설(와이드)</span>
				<input name="SUBJECT_OPTION_ARR" type="checkbox" value="AA"/><span class="txt06">MP4</span>
				<input name="SUBJECT_OPTION_ARR" type="checkbox" value="2"/><span class="txt07">PMP개설</span>
				<input name="SUBJECT_OPTION_ARR" type="checkbox" value="3"/><span class="txt05">동영상</span>+<span class="txt07">PMP</span>
	          	<input name="SUBJECT_OPTION_ARR" type="checkbox" value="BB"/><span class="txt05">동영상</span>+<span class="txt02">모바일</span>
			</td>
      	</tr>
		<tr>
			<th>시간표</th>
			<td>
				<textarea id="PLAN" name="PLAN" cols="50" rows="20" class="i_text" title="시간표" style="width:96%;"></textarea>
			</td>
		</tr>
		<tr>
        	<th>개설여부</th>
        	<td>
        		<input type="radio" name="SUBJECT_ISUSE" value="Y" checked /><span class="txt03">활성</span>
          		<input type="radio" name="SUBJECT_ISUSE" value="N" /><span class="txt04">비활성</span>
          	</td>
      </tr>
      <tr>
        	<th>'<span class="txt03">재수강</span>' 신청가능여부</th>
        	<td>
        		<input type="radio" name="RE_COURSE" value="Y" checked /><span class="txt03">가능</span>
          		<input type="radio" name="RE_COURSE" value="N" /><span class="txt04">불가능</span>
          	</td>
      </tr>
      <tr>
        	<th>장바구니할인에서 제외</th>
        	<td>
        		<input type="radio" name="SUBJECT_JANG" value="Y" checked /><span class="txt03">미제외(N)</span>
          		<input type="radio" name="SUBJECT_JANG" value="N" /><span class="txt04">제외(Y)</span>
          	</td>
      </tr>
      <tr>
			<th>보강비밀번호</th>
			<td><input type="text" id="SUBJECT_PASS" name="SUBJECT_PASS" value="" size="50" maxlength="50" style="IME-MODE:disabled;"/></td>
      </tr>
      <tr valign='middle'>
            <th>필수과목 교수진 이미지<br/></th>
            <td style="vertical-align:middle;">
                <div class="item">
                    <div id="row1">이미지 : <input type="file" id="MUST_PRF_IMG" name="MUST_PRF_IMG" title="필수과목 교수진 이미지" /></div>
                </div>
            </td>
            
        </tr>
        <c:if test="${params.LEC_TYPE_CHOICE == 'N'}">
	        <tr valign='middle'>
	            <th>선택과목 교수진 이미지<br/></th>
	            <td style="vertical-align:middle;">
	                <div class="item">
	                    <div id="row1">이미지 : <input type="file" id="SEL_PRF_IMG" name="SEL_PRF_IMG" title="선택과목 교수진 이미지" /></div>
	                </div>
	            </td>
	        </tr>
        </c:if>
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

	// 과목선택시 값 SETTING
	$("input[name='SUBJECT_INFO_ARR']").click(function(){
		$("#SUBJECT_SJT_CD").val($("input[name='SUBJECT_INFO_ARR']:checked").val().split("#")[0]);
		$("#SUBJECT_TEACHER").val($("input[name='SUBJECT_INFO_ARR']:checked").val().split("#")[1]);
		$("#SUBJECT_TEACHER_PAYMENT").val($("input[name='SUBJECT_INFO_ARR']:checked").val().split("#")[2]);
	});
	
	// 강의삭제
	$(document).on("click","input[name='BTN_BOOKDEL']",function(){
		$(this).parent().parent().remove();
	});
});

// 강의명 문자체크
function fn_InputCheckSpecial(obj){
	var strobj = obj;
	re = /[:+\\']/gi;
	if(re.test(strobj.value)){
		alert("특수문자는  :+%\\' 는 입력할수 없습니다.");
		strobj.value=strobj.value.replace(re,"");
		return;
	}
}

// 단과팝업
function fn_SubjectPop(type){
	window.open('<c:url value="/lecture/subjectList.pop"/>?ADDAREA=' + type + '&LEC_TYPE_CHOICE=${params.LEC_TYPE_CHOICE}', '_blank', 'scrollbars=yes,toolbar=no,resizable=yes,width=1040,height=670');
}

// 목록으로
function fn_List(){
	$("#frm").attr("action","<c:url value='/lecture/jongList.do' />");
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
	$("#PLAN").val(editor.outputBodyHTML());
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
	if($("input[name='MST_LECCODE']").length < 1){
		alert("강의를 선택하세요");
		$("#lecturebtn").focus();
		return;
	}
 	if($.trim($("#SUBJECT_TITLE").val()) == ""){
 		alert("강의명을 입력하세요");
 		$("#SUBJECT_TITLE").focus();
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
 	if($.trim($("#SUBJECT_KEYWORD").val()) == ""){
 		alert("키워드를 입력하세요");
 		$("#SUBJECT_KEYWORD").focus();
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
 		if ($("#LEC_TYPE_CHOICE").val() != 'N') {  // 선택형 종합반(임의대로)인 경우 원가를 입력하지 않는다.
	 		alert("원가를 입력하세요");
	 		$("#SUBJECT_PRICE").focus();
	        return;
 		}
 	} 	
 	if($.trim($("#SUBJECT_DISCOUNT").val()) == ""){
 		alert("할인율을 입력하세요");
 		$("#SUBJECT_DISCOUNT").focus();
        return;
 	} 	 	

	if(confirm("강의를 등록하시겠습니까?")){
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
		
		$("#frm").attr("action","<c:url value='/lecture/jongSave.do' />");
		$("#frm").submit();
	}
}

function fn_goLecType(val){
	$("#LEC_TYPE_CHOICE").val(val);
	if(val=="D"){
		$("#frm").attr("action", "<c:url value='/lecture/write.do' />");
	}else if(val=="Y"){
		$("#frm").attr("action", "<c:url value='/lecture/yearWrite.do' />");
	}else{
		$("#frm").attr("action", "<c:url value='/lecture/jongWrite.do' />");
	}
	$("#frm").submit();	
}
</script>
</body>
</html>