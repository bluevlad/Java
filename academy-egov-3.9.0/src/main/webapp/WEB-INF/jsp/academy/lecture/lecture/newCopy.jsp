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
<input type="hidden" id="SEARCHTYPE" name="SEARCHTYPE" value="${params.SEARCHTYPE}"/>
<input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}"/>
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}"/>
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}"/>
<input type="hidden" id="LEC_TYPE_CHOICE" name="LEC_TYPE_CHOICE" value="${params.LEC_TYPE_CHOICE}">
<input type="hidden" id="SEQ" name="SEQ" value="${view[0].BSEQ}"/>
<input type="hidden" id="BRIDGE_LECCODE" name="BRIDGE_LECCODE" value="${view[0].BRIDGE_LECCODE}"/>
<input type="hidden" id="LECCODE" name="LECCODE" value="${view[0].LECCODE}"/>
<input type="hidden" id="ORG_LECCODE" name="ORG_LECCODE" value="${view[0].LECCODE}"/>
<input type="hidden" id="SUBJECT_TEACHER" name="SUBJECT_TEACHER" value="${view[0].SUBJECT_TEACHER}"/>
<input type="hidden" id="SUBJECT_TEACHER_PAYMENT" name="SUBJECT_TEACHER_PAYMENT" value="${view[0].SUBJECT_TEACHER_PAYMENT}"/>
<input type="hidden" id="SUBJECT_SJT_CD" name="SUBJECT_SJT_CD" value="${view[0].SUBJECT_SJT_CD}"/>
<input type="hidden" id="SUBJECT_OPTION" name="SUBJECT_OPTION" value="${view[0].SUBJECT_OPTION}"/>
<input type="hidden" id="SUBJECT_OFF_OPEN_YEAR" name="SUBJECT_OFF_OPEN_YEAR" value="${view[0].SUBJECT_OFF_OPEN_YEAR}"/> 
<input type="hidden" id="SUBJECT_OFF_OPEN_MONTH" name="SUBJECT_OFF_OPEN_MONTH" value="${view[0].SUBJECT_OFF_OPEN_MONTH}"/> 
<input type="hidden" id="SUBJECT_OFF_OPEN_DAY" name="SUBJECT_OFF_OPEN_DAY" value="${view[0].SUBJECT_OFF_OPEN_DAY}"/>
<input type="hidden" id="ICON_GUBUN" name="ICON_GUBUN" value="${view[0].ICON_GUBUN}"/>

	<h2>● 강의관리 > <strong>강의제작</strong></h2>
    
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
					<c:set var="CHECKSET" value="" />					
					<c:if test="${item.CODE eq view[0].CATEGORY_CD}"><c:set var="CHECKSET" value="checked='checked'" /></c:if>				
					<li>${item.NAME} <span><input name="CATEGORY_CD" type="checkbox" value="${item.CODE}" ${CHECKSET} /></span></li>					
				</c:forEach>
          		</ul>          
			</td>
			<td>
				<ul class="lecSerial">
				<c:choose>
					<c:when test="${params.LEC_TYPE_CHOICE eq 'F'}">
						<li>입문특강 <span><input name="LEARNING_CD" type="checkbox" value="TAB_001" <c:if test="${view[0].FREE_TAB eq 'TAB_001'}">checked='checked'</c:if>></span></li>
						<li>테마별특강 <span><input name="LEARNING_CD" type="checkbox" value="TAB_002" <c:if test="${view[0].FREE_TAB eq 'TAB_002'}">checked='checked'</c:if>></span></li>
						<li>마무리특강 <span><input name="LEARNING_CD" type="checkbox" value="TAB_003" <c:if test="${view[0].FREE_TAB eq 'TAB_003'}">checked='checked'</c:if>></span></li>
						<li>모의고사해설 <span><input name="LEARNING_CD" type="checkbox" value="TAB_004" <c:if test="${view[0].FREE_TAB eq 'TAB_004'}">checked='checked'</c:if>></span></li>
						<li>기출해설특강 <span><input name="LEARNING_CD" type="checkbox" value="TAB_005" <c:if test="${view[0].FREE_TAB eq 'TAB_005'}">checked='checked'</c:if>></span></li>
						<li>합격설명회 <span><input name="LEARNING_CD" type="checkbox" value="TAB_006" <c:if test="${view[0].FREE_TAB eq 'TAB_006'}">checked='checked'</c:if>></span></li>
						<li>보강동영상 <span><input name="LEARNING_CD" type="checkbox" value="TAB_007" <c:if test="${view[0].FREE_TAB eq 'TAB_007'}">checked='checked'</c:if>></span></li>
					</c:when>
					<c:otherwise>
						<c:forEach items="${formlist}" var="item" varStatus="loop">
							<c:set var="CHECKSET" value="" />
							<c:if test="${item.CODE eq view[0].LEARNING_CD}"><c:set var="CHECKSET" value="checked='checked'" /></c:if>
							<li>${item.NAME} <span><input name="LEARNING_CD" type="checkbox" value="${item.CODE}" ${CHECKSET} /></span></li>
						</c:forEach>					
					</c:otherwise>
				</c:choose>

          		</ul>
          	</td>
          	<td>
				<ul class="lecSerial">
				<c:forEach items="${subjectteacherlist}" var="item" varStatus="loop">
					<c:set var="CHECKSET" value="" />
					<c:if test="${item.SUBJECT_CD eq view[0].SUBJECT_SJT_CD and item.USER_ID eq view[0].SUBJECT_TEACHER}"><c:set var="CHECKSET" value="checked='checked'" /></c:if>				
					<li>${item.SUBJECT_NM}(${item.USER_NM}) <span><input name="SUBJECT_INFO_ARR" type="radio" value="${item.SUBJECT_CD}#${item.USER_ID}#${item.PAYMENT}" ${CHECKSET}/></span></li>
				</c:forEach>
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
			<td>총&nbsp;<input type="text" id="LEC_SCHEDULE" name="LEC_SCHEDULE" value="${view[0].LEC_SCHEDULE}" size="10" maxlength="3" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/> 회
				   전체&nbsp;<input type="text" id="LEC_COUNT" name="LEC_COUNT" value="${view[0].LEC_COUNT}" size="10" maxlength="3" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/> 강
			</td>
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
			<th>키워드</th>
        	<td><input type="text" id="SUBJECT_KEYWORD" name="SUBJECT_KEYWORD" value="${view[0].SUBJECT_KEYWORD}" size="60" maxlength="200" style="background:#FFECEC;"/></td>
		</tr>
		<tr>
			<th>기간</th>
			<td>
				<input type="text" id="SUBJECT_PERIOD" name="SUBJECT_PERIOD" value="${view[0].SUBJECT_PERIOD}" size="10" maxlength="4" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/>일 
					&nbsp;&nbsp;&nbsp;&nbsp;
					시작일<input type="text" id="SUBJECT_OFF_OPEN_CAL" name="SUBJECT_OFF_OPEN_CAL" value="${view[0].SUBJECT_OFF_OPEN_YEAR}${view[0].SUBJECT_OFF_OPEN_MONTH}${view[0].SUBJECT_OFF_OPEN_DAY}" readonly="readonly"/>
          	</td>
		</tr>
		<tr>
        	<th>원가</th>
        	<td><input type="text" id="SUBJECT_PRICE" name="SUBJECT_PRICE" value="${view[0].SUBJECT_PRICE}" size="10" maxlength="7" onKeyUp="fn_DcNum();" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/> 원</td>
		</tr>
		<tr>
			<th>할인율</th>
			<td><input type="text" id="SUBJECT_DISCOUNT" name="SUBJECT_DISCOUNT" value="${view[0].SUBJECT_DISCOUNT}" size="10" maxlength="3" onKeyUp="fn_DcNum();" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/> %</td>
		</tr>
		<tr>
        	<th>수강료</th>
        	<td>
        		동영상 : <input type="text" id="SUBJECT_MOVIE" name="SUBJECT_MOVIE" value="${view[0].SUBJECT_MOVIE}" size="12" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/> 
        		PMP : <input type="text" id="SUBJECT_PMP" name="SUBJECT_PMP" value="${view[0].SUBJECT_PMP}" size="12" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/>
        		동영상+PMP : <input type="text" id="SUBJECT_MOVIE_PMP" name="SUBJECT_MOVIE_PMP" value="${view[0].SUBJECT_MOVIE_PMP}" size="12" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/>
        		모바일 : <input type="text" id="SUBJECT_MOVIE_MP4" name="SUBJECT_MOVIE_MP4" value="${view[0].SUBJECT_MOVIE_MP4}" size="12" maxlength="7"/>
        		동영상+모바일 : <input type="text" id="SUBJECT_MOVIE_VOD_MP4" name="SUBJECT_MOVIE_VOD_MP4" value="${view[0].SUBJECT_MOVIE_VOD_MP4}" size="12" maxlength="7"/>
        	</td>
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
        	<th><span class="txt05">동영상 기본경로(와이드)</span> </th>
        	<td>
        		<c:set var="SUBJECT_WIDE_DEFAULT_PATH" value="mms://willbes.gscdn.com/" />
        		<c:if test="${!empty view[0].SUBJECT_WIDE_DEFAULT_PATH}"><c:set var="SUBJECT_WIDE_DEFAULT_PATH" value="${view[0].SUBJECT_WIDE_DEFAULT_PATH}" /></c:if>        		
        		<input type="text" id="SUBJECT_WIDE_DEFAULT_PATH" name="SUBJECT_WIDE_DEFAULT_PATH" value="${SUBJECT_WIDE_DEFAULT_PATH}" size="100" class="txt05" maxlength="400"/>
        		<input type="hidden" id="SUBJECT_VOD_DEFAULT_PATH" name="SUBJECT_VOD_DEFAULT_PATH" value="">
        	</td>
      	</tr>
<%-- 		<tr>
        	<th><span class="txt05">동영상 기본경로(500k)</span> </th>
        	<td>
        		<c:set var="SUBJECT_VOD_DEFAULT_PATH" value="mms://willbes.gscdn.com/" />
        		<c:if test="${!empty view[0].SUBJECT_VOD_DEFAULT_PATH}"><c:set var="SUBJECT_VOD_DEFAULT_PATH" value="${view[0].SUBJECT_VOD_DEFAULT_PATH}" /></c:if>        		
        		<input type="text" id="SUBJECT_VOD_DEFAULT_PATH" name="SUBJECT_VOD_DEFAULT_PATH" value="${SUBJECT_VOD_DEFAULT_PATH}" size="100" class="txt05" maxlength="400"/>
        	</td>
      	</tr> --%>
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
        		<c:set var="SUBJECT_PMP_DEFAULT_PATH" value="" />
        		<c:if test="${!empty view[0].SUBJECT_PMP_DEFAULT_PATH}"><c:set var="SUBJECT_PMP_DEFAULT_PATH" value="${view[0].SUBJECT_PMP_DEFAULT_PATH}" /></c:if>   	        
	        	<input type="text" id="SUBJECT_PMP_DEFAULT_PATH" name="SUBJECT_PMP_DEFAULT_PATH" value="${SUBJECT_PMP_DEFAULT_PATH}" size="100" class="txt07" maxlength="400"/>
	        </td>
      	</tr>
		<tr>
        	<th>옵션</th>
        	<td>
        	<c:if test="${item.CODE eq view[0].CATEGORY_CD}"><c:set var="CHECKSET" value="checked='checked'" /></c:if>			
				<c:set var="SOACHECKSET1" value="" />					
        		<c:set var="SOACHECKSET2" value="" />
        		<c:set var="SOACHECKSET3" value="" />
        		<c:set var="SOACHECKSET4" value="" />
        		<c:set var="SOACHECKSET5" value="" />
        		<c:set var="SOACHECKSET6" value="" />
        		<c:if test="${!empty view[0].SUBJECT_OPTION}">
        			<c:set var="SOA" value="${fn:split(view[0].SUBJECT_OPTION,',')}"/>
					<c:forEach items="${SOA}" var="item" varStatus="loop">
						<c:if test="${item eq '1'}"><c:set var="SOACHECKSET1" value="checked='checked'" /></c:if>
						<c:if test="${item eq 'AA'}"><c:set var="SOACHECKSET2" value="checked='checked'" /></c:if>
						<c:if test="${item eq '2'}"><c:set var="SOACHECKSET3" value="checked='checked'" /></c:if>
						<c:if test="${item eq '3'}"><c:set var="SOACHECKSET4" value="checked='checked'" /></c:if>
						<c:if test="${item eq 'BB'}"><c:set var="SOACHECKSET5" value="checked='checked'" /></c:if>
						<c:if test="${item eq '4'}"><c:set var="SOACHECKSET6" value="checked='checked'" /></c:if>
					</c:forEach>        			
        		</c:if>
	        	<%-- <input name="SUBJECT_OPTION_ARR" type="checkbox" value="1" ${SOACHECKSET1} /><span class="txt05">동영상개설(500k)</span> --%>
	        	<input name="SUBJECT_OPTION_ARR" type="checkbox" value="4" ${SOACHECKSET6} /><span class="txt05">동영상개설(와이드)</span>
				<input name="SUBJECT_OPTION_ARR" type="checkbox" value="AA" ${SOACHECKSET2} /><span class="txt06">MP4</span>
				<input name="SUBJECT_OPTION_ARR" type="checkbox" value="2" ${SOACHECKSET3} /><span class="txt07">PMP개설</span>
				<input name="SUBJECT_OPTION_ARR" type="checkbox" value="3" ${SOACHECKSET4} /><span class="txt05">동영상</span>+<span class="txt07">PMP</span>
	          	<input name="SUBJECT_OPTION_ARR" type="checkbox" value="BB" ${SOACHECKSET5} /><span class="txt05">동영상</span>+<span class="txt02">모바일</span>
			</td>
      	</tr>
      	<tr>
			<th>화면확장모드*</th>
			<td colspan="3">
				<input type="radio" id="SUBJECT_MONITORMODE" name="SUBJECT_MONITORMODE" VALUE="NOMAL" <c:if test="${view[0].SUBJECT_MONITORMODE eq 'NOMAL' }">checked="checked"</c:if>/>일반	
				<input type="radio" id="SUBJECT_MONITORMODE" name="SUBJECT_MONITORMODE" VALUE="WIDE" <c:if test="${view[0].SUBJECT_MONITORMODE eq 'WIDE' }">checked="checked"</c:if>/>와이드
			</td>
		</tr>
      	
		<tr>
        	<th>개설여부</th>
        	<td>
        		<input type="radio" name="SUBJECT_ISUSE" value="Y" <c:if test="${view[0].SUBJECT_ISUSE == 'Y' }">checked="checked"</c:if> /><span class="txt03">활성</span>
          		<input type="radio" name="SUBJECT_ISUSE" value="N" <c:if test="${view[0].SUBJECT_ISUSE == 'N' }">checked="checked"</c:if> /><span class="txt04">비활성</span>
          	</td>
      </tr>
      <tr>
        	<th>'<span class="txt03">재수강</span>' 신청가능여부</th>
        	<td>
        		<input type="radio" name="RE_COURSE" value="Y" <c:if test="${view[0].RE_COURSE == 'Y' }">checked="checked"</c:if> /><span class="txt03">가능</span>
          		<input type="radio" name="RE_COURSE" value="N" <c:if test="${view[0].RE_COURSE == 'Y' }">checked="checked"</c:if> /><span class="txt04">불가능</span>
          	</td>
      </tr>
      <tr>
        	<th>장바구니할인에서 제외</th>
        	<td>
        		<input type="radio" name="SUBJECT_JANG" value="Y" <c:if test="${view[0].SUBJECT_JANG == 'Y' }">checked="checked"</c:if> /><span class="txt03">미제외(N)</span>
          		<input type="radio" name="SUBJECT_JANG" value="N" <c:if test="${view[0].SUBJECT_JANG == 'Y' }">checked="checked"</c:if> /><span class="txt04">제외(Y)</span>
          	</td>
      </tr>
      <tr>
			<th>보강비밀번호</th>
			<td><input type="text" id="SUBJECT_PASS" name="SUBJECT_PASS" value="${view[0].SUBJECT_PASS}" size="50" maxlength="50" style="IME-MODE:disabled;"/></td>
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
	window.open('<c:url value="/lecture/bookList.pop"/>?ADDBOOKAREA=' + type, '_blank', 'scrollbars=no,toolbar=no,resizable=yes,width=1040,height=670');
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
		
		$("#frm").attr("action","<c:url value='/lecture/newCopySave.do' />");
		$("#frm").submit();
	}
}

// 목록으로
function fn_List(){
	$("#frm").attr("action","<c:url value='/lecture/list.do' />");
	$("#frm").submit();
}
</script>
</body>
</html>