<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/WEB-INF/views/common/topInclude.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head></head>
<body>
<!--content -->
<div id="content">
<form name="frm" id="frm" method="post" action="" enctype="multipart/form-data">
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
<input type="hidden" id="SUBJECT_TEACHER" name="SUBJECT_TEACHER" value=""/>
<input type="hidden" id="SUBJECT_SJT_CD" name="SUBJECT_SJT_CD" value=""/>
<input type="hidden" id="SUBJECT_OPTION" name="SUBJECT_OPTION" value=""/>
<input type="hidden" id="ICON_GUBUN" name="ICON_GUBUN" value=""/>
<input type="hidden" id="SUBJECT_OFF_OPEN_YEAR" name="SUBJECT_OFF_OPEN_YEAR" value=""/> 
<input type="hidden" id="SUBJECT_OFF_OPEN_MONTH" name="SUBJECT_OFF_OPEN_MONTH" value=""/> 
<input type="hidden" id="SUBJECT_OFF_OPEN_DAY" name="SUBJECT_OFF_OPEN_DAY" value=""/>

  <h2>● 강의관리 > <strong>공개강의개설</strong></h2>
 
  <table class="table01">
    <tr>
      <th width="160">직종</th>
          <td>
          	<select name="CATEGORY_CD" id="CATEGORY_CD">
				<option value="">--직종검색--</option>
				<c:forEach items="${kindlist}" var="item" varStatus="loop">
					<option value="${item.CODE}" <c:if test="${params.CATEGORY_CD == item.CATEGORY_CD }">selected="selected"</c:if>>${item.NAME}</option>					
				</c:forEach>
            </select>
          </td>
    </tr>
    <tr>
      <th width="160">분류</th>
          <td>
          	<select name="OPENBUNRU" id="OPENBUNRU">
				<option value="">--분류검색--</option>
				<c:forEach items="${open_code_commoncode}" var="item" varStatus="loop">
					<option value="${item.CODE_VAL}" <c:if test="${params.CODE_VAL == item.CODE_VAL }">selected="selected"</c:if>>${item.CODE_NM}</option>					
				</c:forEach>
            </select>
          </td>
    </tr>
    <tr>
      <th width="160">강의명</th>
          <td><input type="text" id="OPEN_TITLE" name="OPEN_TITLE" value="" size="60" maxlength="333" style="background:#FFECEC;"/> "<strong class="txt02">강의명</strong>"에 특수문자 5개는 입력하시수 없습니다.[<span class="txt04"> : + % \ ' </span>]</td>
    </tr>
    <tr>
      <th width="160">과목선택</th>
          <td>
          	<select name="OPEN_SJT_CD" id="OPEN_SJT_CD">
				<option value="">--과목검색--</option>
				<c:forEach items="${subjectlist}" var="item" varStatus="loop">
					<option value="${item.SUBJECT_CD}" <c:if test="${params.OPEN_SJT_CD == item.SUBJECT_CD }">selected="selected"</c:if>>${item.SUBJECT_NM}</option>					
				</c:forEach>
				<option value="etc" <c:if test="${params.OPEN_SJT_CD == 'etc' }">selected="selected"</c:if>>기타</option>		
            </select>
          </td>
    </tr>
    <tr>
      <th width="160">강사선택</th>
          <td>
          	<select name="OPEN_TEACHER" id="OPEN_TEACHER">
				<option value="">--강사검색--</option>
				<c:forEach items="${teacherlist}" var="item" varStatus="loop">
					<option value="${item.USER_ID}" <c:if test="${params.USER_ID == item.USER_ID }">selected="selected"</c:if>>${item.USER_NM}</option>	
				</c:forEach>
				<option value="etc" <c:if test="${params.USER_ID == 'etc' }">selected="selected"</c:if>>기타</option>
            </select>
          </td>
    </tr>
    <tr>
          <th>강의요약</th>
          <td><textarea id="OPEN_MEMO" name="OPEN_MEMO" cols="100" rows="5" maxlength="1500" style="background:#FFECEC;"></textarea></td>
    </tr>
    <tr>
          <th>강의개요</th>
          <td><textarea id="OPEN_DESC" name="OPEN_DESC" cols="100" rows="5" maxlength="1500" style="background:#FFECEC;"></textarea></td>
    </tr>
    <tr>
      <th>동영상등록</th>
          <td>고화질 : <input type="text" id="OPEN_HIMOVIE_PATH" name="OPEN_HIMOVIE_PATH" value="" size="60" maxlength="200"/><br>
          일&nbsp;&nbsp;&nbsp;반 : <input type="text" id="OPEN_NOMALMOVIE_PATH" name="OPEN_NOMALMOVIE_PATH" value="" size="60" maxlength="200"/></td>
    </tr>
    <tr>
      <th>첨부파일 등록</th>
      <td>
        <input type="file" id="OPEN_FILE" name="OPEN_FILE" value="" size="30" maxlength="4"  />파일크기는 5M를 초과아여서는 안됩니다
            </td>
    </tr>
    <tr>
        <th>노출여부</th>
        <td>
          <input type="radio" name="OPEN_ISUSE" value="Y" checked /><span class="txt03">노출</span>
            <input type="radio" name="OPEN_ISUSE" value="N" /><span class="txt04">비노출</span>
          </td>
    </tr>
    <tr>
    	<th>비밀번호</th>
      	<td><input type="text" id="OPEN_PASSWORD" name="OPEN_PASSWORD" value="" size="50" maxlength="50" /></td>
    </tr>
    <tr>
    	<th>주요강의 설정</th>
      	<td><input type="checkbox" id="OPEN_POINT" name="OPEN_POINT" value="1" size="50" maxlength="50" style="IME-MODE:disabled;"/>주요강의</td>
    </tr>
    <tr>
    	<th>조회수</th>
      	<td><input type="text" id="OPEN_HIT" name="OPEN_HIT" value="0" size="50" maxlength="50"  style="background:#FFECEC;" onKeyDown="fn_OnlyNumber(this);"/></td>
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
  
  
});


// 목록으로
function fn_List(){
  $("#frm").attr("action","<c:url value='/openlecture/list.do' />");
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


// 등록
function fn_Submit(){
  /* if(1 > $("input[name='CATEGORY_CD']:selected").size()){
    alert("직종 항목을 선택하세요");
    $("input[name='CATEGORY_CD']")[0].focus();
    return;
  } */
  if($.trim($("#CATEGORY_CD").val()) == ""){
    alert("직종 항목을 선택하세요");
    $("#CATEGORY_CD").focus();
        return;
  }
  if($.trim($("#OPENBUNRU").val()) == ""){
    alert("분류 항목을 선택하세요");
    $("#OPENBUNRU").focus();
        return;
  }
/*   if(1 > $("input[name='OPENBUNRU']:checked").size()){
    alert("분류 항목을 선택하세요");
    $("input[name='OPENBUNRU']")[0].focus();
    return;
  }  */
  if($.trim($("#OPEN_TITLE").val()) == ""){
	    alert("강의명을 입력하세요");
	    $("#OPEN_TITLE").focus();
	        return;
	  }
  if($.trim($("#OPEN_SJT_CD").val()) == ""){
    alert("과목 항목을 선택하세요");
    $("#OPEN_SJT_CD").focus();
        return;
  }
/*   if(1 > $("input[name='OPEN_SJT_CD']:checked").size()){
    alert("과목 항목을 선택하세요");
    $("input[name='OPEN_SJT_CD']")[0].focus();
    return;
  } */
  if($.trim($("#OPENBUNRU").val()) == ""){
	    alert("분류 항목을 선택하세요");
	    $("#OPENBUNRU").focus();
	        return;
	  }
/*   if(1 > $("input[name='OPEN_TEACHER']:checked").size()){
    alert("강사 항목을 선택하세요");
    $("input[name='OPEN_TEACHER']")[0].focus();
    return;
  } */
  if($.trim($("#OPEN_TEACHER").val()) == ""){
    alert("강사 항목을 선택하세요");
    $("#OPEN_TEACHER").focus();
        return;
  }
  if($.trim($("#OPEN_MEMO").val()) == ""){
    alert("강의요약을 입력하세요");
    $("#OPEN_MEMO").focus();
        return;
  }
  if($.trim($("#OPEN_DESC").val()) == ""){
    alert("강의개요 입력하세요");
    $("#OPEN_DESC").focus();
        return;
  }     
  
  

  if(confirm("강의를 등록하시겠습니까?")){
    $("#frm").attr("action","<c:url value='/openlecture/save.do' />");
    $("#frm").submit();
  }
}

function fn_goLecType(val){
  $("#LEC_TYPE_CHOICE").val(val);
  if(val=="D"){
    $("#frm").attr("action", "<c:url value='/lecture/write.do' />");
  }else{
    $("#frm").attr("action", "<c:url value='/lecture/jongWrite.do' />");
  }
  $("#frm").submit(); 
}
</script>
</body>
</html>