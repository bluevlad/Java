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
<input type="hidden" id="SEARCHKIND" name="SEARCHFORM" value="${params.SEARCHFORM}"/>
<input type="hidden" id="SEARCHYEAR" name="SEARCHYEAR" value="${params.SEARCHYEAR}"/>
<input type="hidden" id="SEARCHTYPE" name="SEARCHTYPE" value="${params.SEARCHTYPE}"/>
<input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}"/>
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}"/>
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}"/>
<input type="hidden" id="LEC_TYPE_CHOICE" name="LEC_TYPE_CHOICE" value="${params.LEC_TYPE_CHOICE}">
<input type="hidden" id="BRIDGE_MSTCODE" name="BRIDGE_MSTCODE" value="${params.BRIDGE_MSTCODE}"">
<input type="text" id="MSTCODE" name="MSTCODE" value="${view[0].MSTCODE}"/>
<input type="hidden" id="SUBJECT_TEACHER" name="SUBJECT_TEACHER" value="${view[0].SUBJECT_TEACHER}""/>
<input type="hidden" id="SUBJECT_SJT_CD" name="SUBJECT_SJT_CD" value="${view[0].SUBJECT_SJT_CD}""/>
<input type="hidden" id="SUBJECT_OPTION" name="SUBJECT_OPTION" value="${view[0].SUBJECT_OPTION}""/>
<input type="hidden" id="SUBJECT_OFF_OPEN_YEAR" name="SUBJECT_OFF_OPEN_YEAR" value="${view[0].SUBJECT_OFF_OPEN_YEAR}""/> 
<input type="hidden" id="SUBJECT_OFF_OPEN_MONTH" name="SUBJECT_OFF_OPEN_MONTH" value="${view[0].SUBJECT_OFF_OPEN_MONTH}""/> 
<input type="hidden" id="SUBJECT_OFF_OPEN_DAY" name="SUBJECT_OFF_OPEN_DAY" value="${view[0].SUBJECT_OFF_OPEN_DAY}""/>
<input type="hidden" id="BRIDGE_LEC" name="BRIDGE_LEC" value=""/>
<input type="hidden" id="UPDATE_FLAG" name="UPDATE_FLAG" value=""/>

<c:set var="SOACHECKSET1" value="" />					
<c:set var="SOACHECKSET2" value="" />
<c:set var="SOACHECKSET3" value="" />
<c:set var="SOACHECKSET4" value="" />
<c:set var="SOACHECKSET5" value="" />
<c:if test="${!empty view[0].SUBJECT_OPTION}">
	<c:set var="SOA" value="${fn:split(view[0].SUBJECT_OPTION,',')}"/>
	<c:forEach items="${SOA}" var="item" varStatus="loop">
		<c:if test="${item eq '1'}"><c:set var="SOACHECKSET1" value="checked='checked'" /></c:if>
		<c:if test="${item eq '4'}"><c:set var="SOACHECKSET1" value="checked='checked'" /></c:if>
		<c:if test="${item eq 'AA'}"><c:set var="SOACHECKSET2" value="checked='checked'" /></c:if>
		<c:if test="${item eq '2'}"><c:set var="SOACHECKSET3" value="checked='checked'" /></c:if>
		<c:if test="${item eq '3'}"><c:set var="SOACHECKSET4" value="checked='checked'" /></c:if>
		<c:if test="${item eq 'BB'}"><c:set var="SOACHECKSET5" value="checked='checked'" /></c:if>
	</c:forEach>        			
</c:if>

	<h2>● 강의관리 > <strong>강의제작</strong></h2>
		
    <%-- <!-- 테이블-->
    <table class="table02">
		<tr>
	        <th width="80">직종</th>
	        <th width="80">과목</th>
	        <th width="75">강의코드</th>
	        <th width="55">강사명</th>
	        <th>강의명</th>
	        <th width="75">학습형태</th>
        	<th width="100">VOD / PMP / V+P</th>
        	<th width="70">시작일</th>
        	<th width="110">동영상 / MP4 / PMP</th>
	        <th width="80">등록일</th>
	        <th width="60">개설여부</th>
		</tr>
		<c:forEach items="${viewlist}" var="item" varStatus="loop">
			<tr <c:if test="${item.MSTCODE eq view[0].MSTCODE}">style='background:#FFECEC;'</c:if>> 
		        <td><a href="javascript:fn_DataView('${item.BRIDGE_MSTCODE}','${item.MSTCODE}');">${item.CATEGORY_NM}</a></td>
		        <td>${item.SUBJECT_NM}</td>
		        <td>${item.MSTCODE}<br><span name="qq" id="qq">${item.BRIDGE_MSTCODE}</span></td>
		        <td>${item.SUBJECT_TEACHER_NM}</td>
		        <td style="text-align:left;"><a href="javascript:fn_Modify('${item.BRIDGE_MSTCODE}','${item.MSTCODE}');">${item.SUBJECT_TITLE}</a></td>
		        <td>${item.LEARNING_NM}</td>
		        <td><a href="javascript:fn_PayListPop('Y','VOD','${item.MSTCODE}');">${item.VODY}</a>/<a href="javascript:fn_PayListPop('N','VOD','${item.MSTCODE}');">${item.VODN}</a> | <a href="javascript:fn_PayListPop('Y','PMP','${item.MSTCODE}');">${item.PMPY}</a>/<a href="javascript:fn_PayListPop('N','PMP','${item.MSTCODE}');">${item.PMPN}</a> | <a href="javascript:fn_PayListPop('Y','VOD+PMP','${item.MSTCODE}');">${item.VODPMPY}</a>/<a href="javascript:fn_PayListPop('N','VOD+PMP','${item.MSTCODE}');">${item.VODPMPN}</a></td>
		        <td>${item.SUBJECT_OFF_OPEN_YEAR}-${item.SUBJECT_OFF_OPEN_MONTH}-${item.SUBJECT_OFF_OPEN_DAY}</td>
		        <td>
		        	<c:set var="OPTIONSTR" value="" />
					<c:if test="${!empty item.SUBJECT_OPTION}">
						<c:set var="SOA" value="${fn:split(item.SUBJECT_OPTION,',')}"/>
						<c:forEach items="${SOA}" var="item2" varStatus="loop2">
							<c:if test="${item2 eq 'AA'}">
								<c:if test="${!empty OPTIONSTR}"><c:set var="OPTIONSTR" value="${OPTIONSTR} / " /></c:if>
								<c:set var="OPTIONSTR" value="${OPTIONSTR}PMP" />
							</c:if>
							<c:if test="${item2 eq '2'}">
								<c:if test="${!empty OPTIONSTR}"><c:set var="OPTIONSTR" value="${OPTIONSTR} / " /></c:if>
								<c:set var="OPTIONSTR" value="${OPTIONSTR}MP4" />							
							</c:if>						
							<c:if test="${item2 eq '1'}">
								<c:if test="${!empty OPTIONSTR}"><c:set var="OPTIONSTR" value="${OPTIONSTR} / " /></c:if>
								<c:set var="OPTIONSTR" value="${OPTIONSTR}동영상" />							
							</c:if>
							<c:if test="${item2 eq '4'}">
								<c:if test="${!empty OPTIONSTR}"><c:set var="OPTIONSTR" value="${OPTIONSTR} / " /></c:if>
								<c:set var="OPTIONSTR" value="${OPTIONSTR}동영상" />							
							</c:if>
						</c:forEach>        			
					</c:if>	        	
		        	<a href="javascript:fn_MovieDataViewPop('${item.BRIDGE_MSTCODE}','${item.MSTCODE}','PMP');">${OPTIONSTR}</a>
		        </td>		        
		        <td><fmt:formatDate value="${item.REG_DT}" pattern="yyyy-MM-dd"/></td>
		        <td class="txt03"><c:if test="${item.SUBJECT_ISUSE eq 'Y' }">활성</c:if><c:if test="${item.SUBJECT_ISUSE ne 'Y' }">비활성</c:if></td>
		        <td class="txt03"> <select onchange="javascript:Lec_ON_OFF('${item.MSTCODE}','${item.SUBJECT_TITLE}');" id="ON_OFF_${item.MSTCODE}"><option value="ON" id="ON_${item.MSTCODE}"<c:if test="${item.SUBJECT_ISUSE eq 'Y' }">selected="selected"</c:if>>활성</option> <option value="OFF" id="OFF_${item.MSTCODE}"<c:if test="${item.SUBJECT_ISUSE ne 'Y' }">selected="selected"</c:if>>비활성</option></select> </td>
		        <input type="text" name="SUBJECT_ISUSE" id="SUBJECT_ISUSE" value="${item.SUBJECT_ISUSE}"></input>
			</tr>
		</c:forEach>		 	
	</table>      
    <!-- //테이블--> --%>
    
	<p>&nbsp; </p>
    
	<table class="table01">
		<tr>
			<th width="50%">과목(강사)&nbsp;&nbsp;&nbsp;
			검색어 : <input type="text" id="TEACHER_NM" name="TEACHER_NM" value="" size="40" title="검색어" onkeypress="fn_subject_teacher_search()">
			<input type="button" onclick="fn_subject_teacher_search()" value="검색" />
			</th>
		</tr>
        <%-- <tr>
          	<td>
				<ul class="lecSerial">
				<c:forEach items="${subjectteacherlist}" var="item" varStatus="loop">
					<c:set var="CHECKSET" value="" />
					<c:if test="${item.SUBJECT_CD eq view[0].SUBJECT_SJT_CD and item.USER_ID eq view[0].SUBJECT_TEACHER}"><c:set var="CHECKSET" value="checked='checked'" /></c:if>				
					<li>${item.SUBJECT_NM}(${item.USER_NM}) <span><input name="SUBJECT_INFO_ARR" type="radio" value="${item.SUBJECT_CD}#${item.USER_ID}#${item.PAYMENT}" ${CHECKSET}/></span></li>
				</c:forEach>
          		</ul>
          	</td>
        </tr> --%>
        <tr>
          	<td>
				<ul class="lecSerial">
				<div id="div_subject_teacher_search">
				</div>
          		</ul>
          	</td>
        </tr>
    </table>
	
    <p>&nbsp; </p>
    
	<table class="table01">
		<tr>
			<th width="160">강의명</th>
        	<td><input type="text" id="SUBJECT_TITLE" name="SUBJECT_TITLE" value="${view[0].SUBJECT_TITLE}" size="60" maxlength="333" style="background:#FFECEC;"/> "<strong class="txt02">강의명</strong>"에 특수문자 5개는 입력하시수 없습니다.[<span class="txt04"> : + % \ ' </span>]</td>
		</tr>
		<tr>
			<th>강의예정회차</th>
			<td>총 <input type="text" id="LEC_SCHEDULE" name="LEC_SCHEDULE" value="${view[0].LEC_SCHEDULE}" size="10" maxlength="3" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/> 회 강의입니다.</td>
		</tr>
		<tr>
        	<th>강의요약</th>
        	<td><textarea id="SUBJECT_MEMO" name="SUBJECT_MEMO" cols="100" rows="5" maxlength="1500" style="background:#FFECEC;">${view[0].SUBJECT_MEMO}</textarea></td>
		</tr>
		<tr>
        	<th>강의개요</th>
        	<td><textarea id="SUBJECT_DESC" name="SUBJECT_DESC" cols="100" rows="5" maxlength="1500" style="background:#FFECEC;">${view[0].SUBJECT_DESC}</textarea></td>
		</tr>
		<tr>
			<th>기간</th>
			<td>
				<input type="text" id="SUBJECT_PERIOD" name="SUBJECT_PERIOD" value="${view[0].SUBJECT_PERIOD}" size="10" maxlength="4" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/>일 
					&nbsp;&nbsp;&nbsp;&nbsp;
					시작일 <input type="text" id="SUBJECT_OFF_OPEN_CAL" name="SUBJECT_OFF_OPEN_CAL" value="${view[0].SUBJECT_OFF_OPEN_YEAR}${view[0].SUBJECT_OFF_OPEN_MONTH}${view[0].SUBJECT_OFF_OPEN_DAY}" style="background:#FFECEC;" readonly="readonly"/>
          	</td>
		</tr>
        	<th>원가</th>
        	<td><input type="text" id="SUBJECT_PRICE" name="SUBJECT_PRICE" value="${view[0].SUBJECT_PRICE}" size="10" maxlength="7" onKeyUp="fn_DcNum();" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/> 원</td>
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
              		<c:set var="CHECKBOOKSET" value="N" />
					<c:forEach items="${viewbooklist}" var="item" varStatus="loop">
						<c:if test="${item.FLAG eq 'J'}">
						<c:set var="CHECKBOOKSET" value="Y" />
							<tr>
								<td>${item.CATEGORY_NM}</td>
								<td>${item.LEARNING_NM}</td>
								<td>${item.BOOK_NM}</td>
								<td><input name="BTN_BOOKDEL" type="button" value="삭제"/><input type="hidden" name="RSC_ID" value="${item.RSC_ID}" /></td>
							</tr>
						</c:if>
					</c:forEach>
					<c:if test="${CHECKBOOKSET eq 'N'}">
						<tr class="basic_space">
	                		<td colspan="4">&nbsp;</td>
	              		</tr>
					</c:if>
            	</table>
			</td>
		</tr>
		
      	<tr>
        	<th>부교재<br/><input name="bookbtn" type="button" value="선택" onClick="fn_BookPop('bookBuArea');"></th>
			<td>
        		<table class="tdTable" id="bookBuArea">
					<tr>
                		<th width="15%">직급</th>
                		<th width="15%">학습형태</th>
                		<th>교재명</th>
                		<th width="10%">삭제</th>
              		</tr>
              		<c:set var="CHECKBOOKSET" value="N" />
					<c:forEach items="${viewbooklist}" var="item" varStatus="loop">
						<c:if test="${item.FLAG eq 'B'}">
						<c:set var="CHECKBOOKSET" value="Y" />
							<tr>
								<td>${item.CATEGORY_NM}</td>
								<td>${item.LEARNING_NM}</td>
								<td>${item.BOOK_NM}</td>
								<td><input name="BTN_BOOKDEL" type="button" value="삭제"/><input type="hidden" name="RSC_ID" value="${item.RSC_ID}" /></td>
							</tr>
						</c:if>
					</c:forEach>
					<c:if test="${CHECKBOOKSET eq 'N'}">
						<tr class="basic_space">
	                		<td colspan="4">&nbsp;</td>
	              		</tr>
					</c:if>
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
              		<c:set var="CHECKBOOKSET" value="N" />
					<c:forEach items="${viewbooklist}" var="item" varStatus="loop">
						<c:if test="${item.FLAG eq 'S'}">
						<c:set var="CHECKBOOKSET" value="Y" />
							<tr>
								<td>${item.CATEGORY_NM}</td>
								<td>${item.LEARNING_NM}</td>
								<td>${item.BOOK_NM}</td>
								<td><input name="BTN_BOOKDEL" type="button" value="삭제"/><input type="hidden" name="RSC_ID" value="${item.RSC_ID}" /></td>
							</tr>
						</c:if>
					</c:forEach>
					<c:if test="${CHECKBOOKSET eq 'N'}">
						<tr class="basic_space">
	                		<td colspan="4">&nbsp;</td>
	              		</tr>
					</c:if>
            	</table>
			</td>
		</tr>				
		
		<tr>
        	<th><span class="txt05">동영상 기본경로(500k)</span> </th>
        	<td>
        		<c:set var="SUBJECT_VOD_DEFAULT_PATH" value="mms://willbes.gscdn.com/" />
        		<c:if test="${!empty view[0].SUBJECT_VOD_DEFAULT_PATH}"><c:set var="SUBJECT_VOD_DEFAULT_PATH" value="${view[0].SUBJECT_VOD_DEFAULT_PATH}" /></c:if>        		
        		<input type="text" id="SUBJECT_VOD_DEFAULT_PATH" name="SUBJECT_VOD_DEFAULT_PATH" value="${SUBJECT_VOD_DEFAULT_PATH}" size="100" class="txt05" maxlength="400"/>
        	</td>
      	</tr>
      	<tr>
        	<th><span class="txt06">MP4 기본경로</span></th>
        	<td>
        		<c:set var="SUBJECT_MP4_DEFAULT_PATH" value="http://hd.willbes.gscdn.com/" />
        		<c:if test="${!empty view[0].SUBJECT_MP4_DEFAULT_PATH}"><c:set var="SUBJECT_MP4_DEFAULT_PATH" value="${view[0].SUBJECT_MP4_DEFAULT_PATH}" /></c:if>        	
        		<input type="text" id="SUBJECT_MP4_DEFAULT_PATH" name="SUBJECT_MP4_DEFAULT_PATH" value="${SUBJECT_MP4_DEFAULT_PATH}" size="100" class="txt06" maxlength="400"/>
        	</td>
      	</tr>
      	<tr>
    	    <th><span class="txt07">PMP 기본경로</span></th>
	        <td>
        		<c:set var="SUBJECT_PMP_DEFAULT_PATH" value="mms://1.224.163.230" />
        		<c:if test="${!empty view[0].SUBJECT_PMP_DEFAULT_PATH}"><c:set var="SUBJECT_PMP_DEFAULT_PATH" value="${view[0].SUBJECT_PMP_DEFAULT_PATH}" /></c:if>   	        
	        	<input type="text" id="SUBJECT_PMP_DEFAULT_PATH" name="SUBJECT_PMP_DEFAULT_PATH" value="${SUBJECT_PMP_DEFAULT_PATH}" size="100" class="txt07" maxlength="400"/>
	        </td>
      	</tr>
		<tr>
        	<th>옵션</th>
        	<td>
        	<c:if test="${item.CODE eq view[0].CATEGORY_CD}"><c:set var="CHECKSET" value="checked='checked'" /></c:if>			
	        	<input name="SUBJECT_OPTION_ARR" type="checkbox" value="1" ${SOACHECKSET1} /><span class="txt05">동영상개설(500k)</span>
				<input name="SUBJECT_OPTION_ARR" type="checkbox" value="AA" ${SOACHECKSET2} /><span class="txt06">MP4</span>
				<input name="SUBJECT_OPTION_ARR" type="checkbox" value="2" ${SOACHECKSET3} /><span class="txt07">PMP개설</span>
				<input name="SUBJECT_OPTION_ARR" type="checkbox" value="3" ${SOACHECKSET4} /><span class="txt05">동영상</span>+<span class="txt07">PMP</span>
	          	<input name="SUBJECT_OPTION_ARR" type="checkbox" value="BB" ${SOACHECKSET5} /><span class="txt05">동영상</span>+<span class="txt02">모바일</span>
			</td>
      	</tr>
  <%--<tr>
        	<th>개설여부</th>
        	<td>
        		<input type="radio" name="SUBJECT_ISUSE" value="Y" <c:if test="${view[0].SUBJECT_ISUSE eq 'Y' }">checked="checked"</c:if> /><span class="txt03">활성</span>
          		<input type="radio" name="SUBJECT_ISUSE" value="N" <c:if test="${view[0].SUBJECT_ISUSE eq 'N' }">checked="checked"</c:if> /><span class="txt04">비활성</span>
          	</td>
      </tr>--%>
      	<tr>
			<th>사용여부</th>
			<td>
				<input type="radio" name="MST_USE_YN" value="Y" <c:if test="${view[0].MST_USE_YN eq 'Y' }">checked="checked"</c:if> /><span class="txt03">활성</span>
				<input type="radio" name="MST_USE_YN" value="N" <c:if test="${view[0].MST_USE_YN eq 'N' }">checked="checked"</c:if> /><span class="txt04">비활성</span>
			</td>
		</tr>
    </table>
    
    <!--버튼-->
	<ul class="boardBtns">
   		<li><a href="javascript:fn_Submit('One');">수정</a></li>
		<li><a href="javascript:fn_Delete();">삭제</a></li>
		<li><a href="javascript:fn_List();">목록</a></li>
		<!-- <li><a href="javascript:fn_NewCopy();">신규복제</a></li> -->
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

// VOD,PMP,동영상 유/무 팝업
function fn_PayListPop(gubn, type, mstcode){
	window.open('<c:url value="/lecture/payList.pop"/>?SEARCHPAYYN=' + gubn + '&SEARCHPAYTYPE=' + type + '&LECCODE=' + mstcode , '_blank', 'scrollbars=yes,toolbar=no,resizable=yes,width=1040,height=670');
}

// VOD,PMP,동영상 클릭시 팝업
function fn_MovieDataViewPop(val1, val2, val3){
	//window.open('<c:url value="/lecture/dataMovieViewList.pop"/>?MSTCODE=' + val2 + '&BRIDGE_MSTCODE=' + val1 + '&SEARCHTYPE=' + val3, '_blank', 'scrollbars=yes,toolbar=no,resizable=yes,width=1734,height=1032');
	window.open('<c:url value="/lecture/dataMovieViewList.pop"/>?MSTCODE=' + val2 + '&BRIDGE_MSTCODE=' + val1, '_blank', 'scrollbars=yes,toolbar=no,resizable=yes,width=1734,height=1032');
}

// 교재팝업
function fn_BookPop(type){
	window.open('<c:url value="/lecture/bookList.pop"/>?ADDBOOKAREA=' + type, '_blank', 'scrollbars=no,toolbar=no,resizable=yes,width=1040,height=670');
}

// 수정폼
function fn_Modify(val1, val2){
	$("#BRIDGE_MSTCODE").val(val1);
	$("#MSTCODE").val(val2);
	$("#frm").attr("action", "<c:url value='/lecture/modify.do' />");
	$("#frm").submit();
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

//강의명 부터 강사료지급률까지 모든직종 강의 일괄수정
/* function fn_Submit2(){
	
	var BRIDGE_LEC = $("#BRIDGE_MSTCODE").val();

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
 		alert("원가를 입력하세요");
 		$("#SUBJECT_PRICE").focus();
        return;
 	} 	
 	if($.trim($("#SUBJECT_DISCOUNT").val()) == ""){
 		alert("할인율을 입력하세요");
 		$("#SUBJECT_DISCOUNT").focus();
        return;
 	}
 	if(confirm("모든 직렬 강의를 수정하시겠습니까?")){
 		$("#BRIDGE_LEC").val(BRIDGE_LEC);
 		$("#frm").attr("action","<c:url value='/lecture/update2.do' />");
		$("#frm").submit();
 	}
} */

// 수정
function fn_Submit(flag){
	var FLAG = flag;
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
 	
	var MSG = "강의를 수정하시겠습니까?";
	var BRIDGE_LEC = $("#BRIDGE_MSTCODE").val();
	$("#BRIDGE_LEC").val(BRIDGE_LEC);
		
		if(confirm(MSG)){
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
		
		$("#frm").attr("action","<c:url value='/lecturemst/update.do' />");
		$("#frm").submit();
	}
}

// 삭제
function fn_Delete(){
/* 	if("${lectureOrderCount}"!="0"){
		if(confirm("이 강의를 주문한 내역이 존재합니다. 정말 삭제하시겠습니까?")){
			$("#frm").attr("action","<c:url value='/lecture/delete.do' />");
			$("#frm").submit();
		}		
	}else{ */
		if(confirm("정말 삭제하시겠습니까?")){
			$("#frm").attr("action","<c:url value='/lecturemst/delete.do' />");
			$("#frm").submit();
		}
	//}
}

// 복제
function fn_Copy(){
	$("#frm").attr("action","<c:url value='/lecturemst/copy.do' />");
	$("#frm").submit();
}

// 신규복제
function fn_NewCopy(){
	$("#frm").attr("action","<c:url value='/lecturemst/newCopy.do' />");
	$("#frm").submit();
}

//직종클릭시 팝업
function fn_DataView(val1, val2){
	window.open('<c:url value="/lecturemst/dataViewList.pop"/>?MSTCODE=' + val2 + '&BRIDGE_MSTCODE=' + val1, '_blank', 'scrollbars=no,toolbar=no,resizable=yes,width=1040,height=670');
}



function fn_Enter(){
	$("#SEARCHTEXT").keyup(function(e)  {
		if(e.keyCode == 13) 
			fn_subject_teacher_search();
	});
}

function fn_subject_teacher_search(){
	var param =  "&TEACHER_NM=" + $('#TEACHER_NM').val()+"&SUBJECT_TEACHER=${view[0].SUBJECT_TEACHER}"+"&SUBJECT_SJT_CD=${view[0].SUBJECT_SJT_CD}";
	  $.ajax({
		    type : "POST"
		    ,url : "/lecturemst/subject_teacher_search_modify.pop?"
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