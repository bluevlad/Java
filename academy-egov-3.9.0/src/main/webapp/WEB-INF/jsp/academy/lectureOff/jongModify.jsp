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
	 editor.config.editorHeight = '250px';     // 에디터 세로폭입니다.
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
<input type="hidden" id="SEARCHKIND" name="SEARCHFORM" value="${params.SEARCHFORM}"/>
<input type="hidden" id="SEARCHYEAR" name="SEARCHYEAR" value="${params.SEARCHYEAR}"/>
<input type="hidden" id="SEARCHTYPE" name="SEARCHTYPE" value="${params.SEARCHTYPE}"/>
<input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}"/>
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}"/>
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}"/>
<input type="hidden" id="LEC_TYPE_CHOICE" name="LEC_TYPE_CHOICE" value="${params.LEC_TYPE_CHOICE}">
<input type="hidden" id="LECCODE" name="LECCODE" value="${view[0].LECCODE}"/>
<input type="hidden" id="SUBJECT_TEACHER" name="SUBJECT_TEACHER" value="${view[0].SUBJECT_TEACHER}""/>
<input type="hidden" id="SUBJECT_SJT_CD" name="SUBJECT_SJT_CD" value="${view[0].SUBJECT_SJT_CD}""/>
<input type="hidden" id="SEQ" name="SEQ" value="${params.SEQ}"/>

	<h2>● 강의관리 > <strong>강의제작</strong></h2>
	
	
   <!-- 테이블-->
    <table class="table02">
		<tr>
	        <th width="80">직종</th>
	        <th width="75">학습형태</th>
	        <th>강의명</th>
	        <th width="80">등록일</th>
	        <th width="60">개설여부</th>
		</tr>
		<c:forEach items="${viewjonglist}" var="item" varStatus="loop">
			<tr <c:if test="${item.LECCODE eq view[0].LECCODE}">style='background:#FFECEC;'</c:if>>
		        <td>${item.CATEGORY_NM}</td>
		        <td>${item.LEARNING_NM}</td>
		        <td style="text-align:left;"><a href="javascript:fn_Modify('${item.LECCODE}','${params.SEQ}');">${item.SUBJECT_TITLE}</a></td>
		        <td><fmt:formatDate value="${item.REG_DT}" pattern="yyyy-MM-dd"/></td>
		        <c:set var="USESTATUS" value="" />
		        <c:set var="USESTATUSSTYLE" value="" />
				<c:if test="${item.SUBJECT_ISUSE eq 'Y' }">
					<c:set var="USESTATUS" value="개설" />
					<c:set var="USESTATUSSTYLE" value="style='color:green;'" />
				</c:if>
				<c:if test="${item.SUBJECT_ISUSE ne 'Y' }">
					<c:set var="USESTATUS" value="폐강" />
					<c:set var="USESTATUSSTYLE" value="style='color:#999999;'" />				
				</c:if>
		        <td class="txt03" ${USESTATUSSTYLE}>${USESTATUS}</td>
			</tr>
		</c:forEach>		 	
	</table>      
    <!-- //테이블-->	
	
	<p>&nbsp; </p>
    
	<table class="table01">
		<tr>
        	<th width="25%">직종</th>
			<th width="25%">학습형태</th>
			<th width="50%">
				<c:if test="${params.LEC_TYPE_CHOICE ne 'N'}">과목</c:if>
				<c:if test="${params.LEC_TYPE_CHOICE eq 'N'}">필수과목</c:if>
			</th>
			
		</tr>
        <tr>
			<td>
				<ul class="lecSerial">
				<c:forEach items="${kindlist}" var="item" varStatus="loop">
					<c:set var="CHECKSET" value="" />					
					<c:if test="${item.CODE eq view[0].CATEGORY_CD}"><c:set var="CHECKSET" value="checked='checked'" /></c:if>				
					<li>${item.NAME} <span><input name="CATEGORY_CD" type="radio" value="${item.CODE}" ${CHECKSET}/></span></li>					
				</c:forEach>
          		</ul>          
			</td>
			<td>
				<ul class="lecSerial">
				<c:forEach items="${formlist}" var="item" varStatus="loop">
					<c:set var="CHECKSET" value="" />
					<c:if test="${item.CODE eq view[0].LEARNING_CD}"><c:set var="CHECKSET" value="checked='checked'" /></c:if>
					<li>${item.NAME} <span><input name="LEARNING_CD" type="radio" value="${item.CODE}"  ${CHECKSET}/></span></li>
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
				                <th width="15%">강사</th>
				                <th width="10%">삭제</th>
							</tr>
							<c:forEach items="${viewleccodelist}" var="item" varStatus="loop">
								<c:if test="${item.GUBUN eq '1'}">
									<tr>				
										<td>${item.CATEGORY_NM}</td>
										<td>${item.LEARNING_NM}</td>
										<td>${item.SUBJECT_TITLE}</td>
										<c:if test="${params.LEC_TYPE_CHOICE eq 'N'}">
											<td><input type="text" name="MST_LECCODE_SORT" value="${item.SORT}" size="8" maxlength="333" style="background:#FFECEC;"/></td>
										</c:if>
										<td>${item.SUBJECT_TEACHER_NM}</td>
										<td><input name="BTN_BOOKDEL" type="button" value="삭제"/><input type="hidden" name="MST_LECCODE" value="${item.LECCODE}" /></td>
									</tr>
								</c:if>
							</c:forEach>
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
        	<th>선택과목<br/><input id="lecturebtn2" name="lecturebtn2" type="button" value="단과불러오기" onClick="fn_SubjectPop('ADDAREA2');"/></th>
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
					<c:forEach items="${viewleccodelist}" var="item" varStatus="loop">
						<c:if test="${item.GUBUN eq '2'}">
							<tr>				
								<td>${item.CATEGORY_NM}</td>
								<td>${item.LEARNING_NM}</td>
								<td>${item.SUBJECT_TITLE}</td>
								<c:if test="${params.LEC_TYPE_CHOICE eq 'N'}">
									<td><input type="text" name="MST_LECCODE2_SORT" value="${item.SORT}" size="8" maxlength="333" style="background:#FFECEC;"/></td>
								</c:if>
								<td>${item.SUBJECT_TEACHER_NM}</td>
								<td><input name="BTN_BOOKDEL" type="button" value="삭제"/><input type="hidden" name="MST_LECCODE2" value="${item.LECCODE}" /></td>
							</tr>
						</c:if>
					</c:forEach>
            	</table>
			</td>
		</tr>	
		</c:if>	
		<tr>
        	<th>수강형태</th>
        	<td>
        		<input type="radio" name="SUBJECT_TYPE" value="1" <c:if test="${view[0].SUBJECT_TYPE eq '1' }">checked="checked"</c:if> /><span class="txt03">실강</span>
          		<input type="radio" name="SUBJECT_TYPE" value="2" <c:if test="${view[0].SUBJECT_TYPE eq '2' }">checked="checked"</c:if> /><span class="txt04">실시간 영상</span>
          	</td>
		</tr>
		<tr>
        	<th>수강신청구분</th>
        	<td>
        		<input type="radio" name="SUBJECT_GUBUN" value="1" <c:if test="${view[0].SUBJECT_GUBUN eq '1' }">checked="checked"</c:if> /><span class="txt03">방문접수</span>
          		<input type="radio" name="SUBJECT_GUBUN" value="2" <c:if test="${view[0].SUBJECT_GUBUN eq '2' }">checked="checked"</c:if> /><span class="txt04">온라인접수</span>
          		<input type="radio" name="SUBJECT_GUBUN" value="3" <c:if test="${view[0].SUBJECT_GUBUN eq '3' }">checked="checked"</c:if> /><span class="txt04">방문접수+온라인접수</span>
          	</td>
		</tr>
		<tr>
			<th>수강인원</th>
			<td><input type="text" id="SUBJECT_MEMBER_CNT" name="SUBJECT_MEMBER_CNT" value="${view[0].SUBJECT_MEMBER_CNT}" size="10" maxlength="4" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/> 명</td>
		</tr>
		<tr>
			<th width="160">강의명</th>
        	<td><input type="text" id="SUBJECT_TITLE" name="SUBJECT_TITLE" value="${view[0].SUBJECT_TITLE}" size="60" maxlength="333" style="background:#FFECEC;"/> "<strong class="txt02">강의명</strong>"에 특수문자 5개는 입력하시수 없습니다.[<span class="txt04"> : + % \ ' </span>]</td>
		</tr>
		<tr>
        	<th>강의소개</th>
        	<td><textarea id="SUBJECT_DESC" name="SUBJECT_DESC" cols="100" rows="5" maxlength="1500" style="background:#FFECEC;">${view[0].SUBJECT_DESC}</textarea></td>
		</tr>
		<tr>
			<th>키워드</th>
        	<td><input type="text" id="SUBJECT_KEYWORD" name="SUBJECT_KEYWORD" value="${view[0].SUBJECT_KEYWORD}" size="120" maxlength="200" style="background:#FFECEC;"/></td>
		</tr>
		<c:if test="${params.LEC_TYPE_CHOICE eq 'N'}">
		<tr>
			<th>선택과목(선택갯수)</th>
			<td><input type="text" id="NO" name="NO" value="${view[0].NO}" size="10" maxlength="3" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/> 개</td>
		</tr>		
		</c:if>		
		<tr>
        	<th>원가</th>
        	<td><input type="text" id="SUBJECT_PRICE" name="SUBJECT_PRICE" value="${view[0].SUBJECT_PRICE}" size="10" maxlength="7" onKeyUp="fn_DcNum();" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/> 원</td>
		</tr>
		<tr>
			<th>할인율</th>
			<td><input type="text" id="SUBJECT_DISCOUNT" name="SUBJECT_DISCOUNT" value="${view[0].SUBJECT_DISCOUNT}" size="10" maxlength="3" onKeyUp="fn_DcNum();" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/> %</td>
		</tr>		
		<tr>
			<th>실강료</th>
			<td><input type="text" id="SUBJECT_REAL_PRICE" name="SUBJECT_REAL_PRICE" value="${view[0].SUBJECT_REAL_PRICE}" size="10" maxlength="3" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/> 원</td>
		</tr>
		<tr>
			<th>사진</th>
			<td>
				<input type="file" id="SUBJECT_SUMNAIL" name="SUBJECT_SUMNAIL" value="" size="30"  />
				<c:if test="${!empty view[0].SUBJECT_SUMNAIL}" >
					<span>기존파일삭제 : </span>
					<span style="inline-block;vertical-align:top;"><input type="checkbox" value="Y" id="SUBJECT_SUMNAIL_DEL" name="SUBJECT_SUMNAIL_DEL"/></span>
					<input type="hidden" id="SUBJECT_SUMNAIL_DELNM" name="SUBJECT_SUMNAIL_DELNM" value="${view[0].SUBJECT_SUMNAIL}"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<th>시간표</th>
			<td>
				<textarea id="PLAN" name="PLAN" cols="50" rows="20" class="i_text" title="시간표" style="width:96%;">${view[0].PLAN}</textarea>
			</td>
		</tr>
		<tr>
        	<th>강의개설</th>
        	<td>
        		<input type="radio" name="SUBJECT_ISUSE" value="Y" <c:if test="${view[0].SUBJECT_ISUSE eq 'Y' }">checked="checked"</c:if> /><span class="txt03">개설</span>
          		<input type="radio" name="SUBJECT_ISUSE" value="N" <c:if test="${view[0].SUBJECT_ISUSE eq 'N' }">checked="checked"</c:if> /><span class="txt04">개설안함</span>
          	</td>
      </tr>
      <tr>
        	<th>'<span class="txt03">신규강좌여부</th>
        	<td>
        		<input type="radio" name="LEC_GUBUN" value="Y" <c:if test="${view[0].LEC_GUBUN eq 'Y' }">checked="checked"</c:if> /><span class="txt03">예</span>
          		<input type="radio" name="LEC_GUBUN" value="N" <c:if test="${view[0].LEC_GUBUN eq 'N' }">checked="checked"</c:if> /><span class="txt04">아니오</span>
          	</td>
      </tr>	
      
    </table>
    
    <!--버튼-->
	<ul class="boardBtns">
   		<li><a href="javascript:fn_Submit();">수정</a></li>
		<li><a href="javascript:fn_Delete();">삭제</a></li>
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

// 수정폼
function fn_Modify(val, val2){
	$("#LECCODE").val(val);
	//$("#SEQ").val(val2);
	$("#frm").attr("action", "<c:url value='/lectureOff/jongModify.do' />");
	$("#frm").submit();
}

// 목록으로
function fn_List(){
	$("#frm").attr("action","<c:url value='/lectureOff/jongList.do' />");
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
//	$("#SUBJECT_REAL_PRICE").val(Math.floor(realprice/100)*100);		
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

// 단과팝업
function fn_SubjectPop(type){
	window.open('<c:url value="/lectureOff/subjectList.pop"/>?ADDAREA=' + type + '&LEC_TYPE_CHOICE=${params.LEC_TYPE_CHOICE}', '_blank', 'scrollbars=no,toolbar=no,resizable=yes,width=1040,height=670');
}

//강의명 문자체크
function fn_InputCheckSpecial(obj){
	var strobj = obj;
	re = /[:+\\']/gi;
	if(re.test(strobj.value)){
		alert("특수문자는  :+%\\' 는 입력할수 없습니다.");
		strobj.value=strobj.value.replace(re,"");
		return;
	}
}

// 수정
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
	if(confirm("강의를 수정하시겠습니까?")){
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
		$("#frm").attr("action","<c:url value='/lectureOff/jongUpdate.do' />");
		$("#frm").submit();
	}
}

// 삭제
function fn_Delete(){
	if("${lectureOrderCount}"!="0"){
		if(confirm("이 강의를 주문한 내역이 존재합니다. 정말 삭제하시겠습니까?")){
			$("#frm").attr("action","<c:url value='/lectureOff/jongDelete.do' />");
			$("#frm").submit();
		}		
	}else{
		if(confirm("정말 삭제하시겠습니까?")){
			$("#frm").attr("action","<c:url value='/lectureOff/jongDelete.do' />");
			$("#frm").submit();
		}		
	}	
}
</script>
</body>
</html>