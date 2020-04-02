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
<input type="hidden" id="SEARCHTYPE" name="SEARCHTYPE" value="${params.SEARCHTYPE}"/>
<input type="hidden" id="SEARCHTEXT" name="SEARCHTEXT" value="${params.SEARCHTEXT}"/>
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}"/>
<input type="hidden" id="pageRow" name="pageRow" value="${params.pageRow}"/>

	<h2>● 교재 관리 > <strong>교재관리</strong></h2>
		
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
					<li>${item.NAME} <span><input name="LEARNING_CD" type="radio" value="${item.CODE}"/></span></li>
				</c:forEach>
          		</ul>
          	</td>
          	<td>
				<ul class="lecSerial">
				<c:forEach items="${subjectteacherlist}" var="item" varStatus="loop">
					<li>${item.SUBJECT_NM}(${item.USER_NM}) <span><input name="SUBJECT_SJT_CD" type="checkbox" value="${item.SUBJECT_CD}#${item.USER_ID}"/></span></li>
				</c:forEach>
          		</ul>
          	</td>
        </tr>
    </table>
	
    <p>&nbsp; </p>
	
   	<table class="table01">
   		<tr>
   			<th>도서명</th>
  			<td>
	   			<input type="text" id="BOOK_NM" name="BOOK_NM" value="" size="20" maxlength="1000" title="도서명" style="width:28%;background:#FFECEC;"/>
  			</td>
  		</tr>
   		<tr>
   			<th>도서상세설명</th>
  			<td>
	   			<textarea id="BOOK_INFO" name="BOOK_INFO" ROWS="5" style="width:68%;"></textarea>
  			</td>
  		</tr>
   		<tr>
   			<th>키워드</th>
  			<td>
	   			<input type="text" id="BOOK_KEYWORD" name="BOOK_KEYWORD" value="" size="20" maxlength="1333" title="키워드" style="width:28%;"/>
  			</td>
  		</tr>
		<tr>
   			<th>기간(이벤트)</th>
			<td>종료일 <input type="text" id="ISSUE_DATE" name="ISSUE_DATE" value="${view[0].ISSUE_DATE}" style="width:60;" maxlength="8" readonly="readonly"/></td>
  		</tr>
		<tr>
   			<th>상품상태</th>
  			<td>
	   			<select id="COVER_TYPE" name="COVER_TYPE">
	   				<option value="A">주문가능</option>
	   				<option value="S">품절</option>
	   				<option value="O">절판</option>
	   				<option value="E">이벤트</option>
	   				<option value="N">신규</option>
	   			</select>
  			</td>
  		</tr>
   		<tr>
   			<th>목차</th>
  			<td>
	   			<textarea id="BOOK_CONTENTS" name="BOOK_CONTENTS" ROWS="5" maxlength="1333" style="width:68%;"></textarea>
  			</td>
  		</tr>  		
   		<tr>
   			<th>메모(목차2)</th>
  			<td>
	   			<textarea id="BOOK_MEMO" name="BOOK_MEMO" ROWS="5" maxlength="1333" style="width:68%;"></textarea>
  			</td>
  		</tr>		
		<tr>
   			<th>도서가격</th>
  			<td>
  				<div style="padding-bottom:5px;">
	   				<input type="text" id="PRICE" name="PRICE" value="" size="20"  maxlength="10" title="도서가격" style="width:18%;background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/> 원  [ 무료배송 <input type="checkbox" id="FREE_POST" name="FREE_POST" value="Y"/> ]
	   			</div>
	   			<div>
	   				할인율 : <input type="text" id="DISCOUNT" name="DISCOUNT" value="0" size="20"  maxlength="3" title="할인율" style="width:18%;background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);" onkeyup="fn_DcNum();"/> 
	   				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	   				할인가 : <input type="text" id="DISCOUNT_PRICE" name="DISCOUNT_PRICE" value="0" size="20"  maxlength="100" title="할인가" style="width:18%;background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/>
	   				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	   				포인트 : <input type="text" id="POINT" name="POINT" value="0" size="20"  maxlength="100" title="포인트" style="width:18%;background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/>	   				
	   			</div>
  			</td>
  		</tr>  		    		
		<tr>
   			<th>도서형태</th>
  			<td>
  				<div style="padding-bottom:5px;">
	   				<span style="display:inline-block;width:80px">출&nbsp;판&nbsp;사&nbsp;: </span><input type="text" id="BOOK_PUBLISHERS" name="BOOK_PUBLISHERS" value="" size="20"  maxlength="33" title="출판사" style="width:28%;background:#FFECEC;"/>    <input type="checkbox" id="BOOK_SUPPLEMENTDATA" name="BOOK_SUPPLEMENTDATA" value="Y"/> 보충자료    <input type="checkbox" id="BOOK_PRINTINGDATE" name="BOOK_PRINTINGDATE" value="Y"/> 프린트자료
	   			</div>
  				<div style="padding-bottom:5px;">
	   				<span style="display:inline-block;width:80px">저&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;자 : </span><input type="text" id="BOOK_AUTHOR" name="BOOK_AUTHOR" value="" size="20"  maxlength="16" title="저자" style="width:28%;background:#FFECEC;"/>    <input type="checkbox" id="BOOK_MAIN" name="BOOK_MAIN" value="Y"/> 주교재    <input type="checkbox" id="BOOK_SUB" name="BOOK_SUB" value="Y"/> 부교재    <input type="checkbox" id="BOOK_STUDENTBOOK" name="BOOK_STUDENTBOOK" value="Y"/> 수강생교재
	   			</div>
  				<div style="padding-bottom:5px;">
	   				<span style="display:inline-block;width:80px">페이지수 : </span><input type="text" id="BOOK_PAGE" name="BOOK_PAGE" value="" maxlength="5" title="페이지수" style="width:50px;background:#FFECEC;"/> 페이지
	   			</div>
  				<div style="padding-bottom:5px;">
	   				<span style="display:inline-block;width:80px">판&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;형 : </span><input type="text" id="BOOK_FORMAT" name="BOOK_FORMAT" value="" title="판형" style="width:250px;background:#FFECEC;"/>
	   			</div>
	   			<div style="padding-bottom:5px;">
	   				<span style="display:inline-block;width:80px">발&nbsp;행&nbsp;일&nbsp;: </span>
	   				<input type="text" id="BOOK_DATE" name="BOOK_DATE" value="${view[0].BOOK_DATE}" style="width:60;" maxlength="8" readonly="readonly"/>	   				
	   			</div>
				<div style="padding-bottom:5px;">
					<span style="display:inline-block;width:80px">첨부파일 : </span><input type="file" id="ATTACH_FILE" name="ATTACH_FILE" title="첨부파일" />
				</div>
  			</td>
  		</tr>
   		<tr>
   			<th>도서이미지</th>
  			<td>
  				<div><font color="red">(이미지 파일명은 영문으로 작성해 주세요!)</font></div>
   				<div>도서이미지(대) : <input type="file" id="ATTACH_IMG_L" name="ATTACH_IMG_L" title="이미지(L)" /></div>
   				<div style="padding-top:3px;">도서이미지(중) : <input type="file" id="ATTACH_IMG_M" name="ATTACH_IMG_M" title="이미지(M)" /></div>
   				<div style="padding-top:3px;">도서이미지(소) : <input type="file" id="ATTACH_IMG_S" name="ATTACH_IMG_S" title="이미지(S)" /></div>
   				<div style="padding-top:3px;">도서이미지(이벤트) : <input type="file" id="ATTACH_DETAIL_INFO" name="ATTACH_DETAIL_INFO" title="이미지(D)" /></div>
  			</td>
  		</tr>
		<tr>
   			<th>도서재고</th>
  			<td>
	   			<input type="text" id="BOOK_STOCK" name="BOOK_STOCK" value="1000" size="20"  maxlength="8" title="도서재고" style="width:28%;background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/>
  			</td>
  		</tr>		
		<tr>
   			<th>상태</th>
  			<td>
	   			<select id="USE_YN" name="USE_YN">
	   				<option value="Y">활성</option>
	   				<option value="N">비활성</option>
	   			</select>
  			</td>
  		</tr>
		<tr>
   			<th>메인_반영</th>
  			<td>
	   			<select id="MAIN_VIEW" name="MAIN_VIEW">
	   				<option value="Y">반영</option>
	   				<option value="N">미반영</option>
	   			</select>
  			</td>
  		</tr>
		<tr>
   			<th>신간_반영</th>
  			<td>
	   			<select id="NEW_BOOK" name="NEW_BOOK">
	   				<option value="Y">반영</option>
	   				<option value="N">미반영</option>
	   			</select>
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
	initDateFicker2("ISSUE_DATE");	
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
	initDateFicker2("BOOK_DATE");
	$('img.ui-datepicker-trigger').attr('style','margin-left:5px; vertical-align:middle; cursor:pointer;');
});

// 목록으로
function fn_List(){
	$("#frm").attr("action","<c:url value='/book/list.do' />");
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
		var dc = parseFloat($("#DISCOUNT").val()) / 100;
		$("#DISCOUNT_PRICE").val($("#PRICE").val()-$("#PRICE").val() * dc);		
		if($("#DISCOUNT_PRICE").val() == "NaN")
			$("#DISCOUNT_PRICE").val("0");
		// Point 10 % 적용시
		$("#POINT").val(Math.floor($("#DISCOUNT_PRICE").val() * 0.05)); //도서포인트 5%적립
}

// 등록
function fn_Submit(){
	if(1 > $("input[name='CATEGORY_CD']:checked").size()){
		alert("직종 항목을 선택하세요");
		$("input[name='CATEGORY_CD']")[0].focus();
		return;
	}
	if(1 != $("input[name='LEARNING_CD']:checked").size()){
		alert("학습형태 항목을 한개 선택하세요");
		$("input[name='LEARNING_CD']")[0].focus();
		return;
	}	
	if(1 > $("input[name='SUBJECT_SJT_CD']:checked").size()){
		alert("과목 항목을 선택하세요");
		$("input[name='SUBJECT_SJT_CD']")[0].focus();
		return;
	}
 	if($.trim($("#BOOK_NM").val()) == ""){
 		alert("도서명을 입력하세요");
 		$("#BOOK_NM").focus();
        return;
 	}
 	if($.trim($("#PRICE").val()) == ""){
 		alert("도서가격을 입력하세요");
 		$("#PRICE").focus();
        return;
 	}
 	if($.trim($("#BOOK_PUBLISHERS").val()) == ""){
 		alert("출판사를 입력하세요");
 		$("#BOOK_PUBLISHERS").focus();
        return;
 	}
 	if($.trim($("#BOOK_AUTHOR").val()) == ""){
 		alert("저자를 입력하세요");
 		$("#BOOK_AUTHOR").focus();
        return;
 	}	
 	if($.trim($("#BOOK_PAGE").val()) == ""){
 		alert("페이지를 입력하세요");
 		$("#BOOK_PAGE").focus();
        return;
 	}	
 	if($.trim($("#BOOK_FORMAT").val()) == ""){
 		alert("판형을 입력하세요");
 		$("#BOOK_FORMAT").focus();
        return;
 	}	
	if(confirm("교재를 등록하시겠습니까?")){
		$("#frm").attr("action","<c:url value='/book/save.do' />");
		$("#frm").submit();
	}
}
</script>
</body>
</html>