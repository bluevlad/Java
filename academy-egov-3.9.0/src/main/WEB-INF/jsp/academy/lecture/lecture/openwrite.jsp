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
<input type="hidden" id="ICON_GUBUN" name="ICON_GUBUN" value=""/>
<input type="hidden" id="SUBJECT_OFF_OPEN_YEAR" name="SUBJECT_OFF_OPEN_YEAR" value=""/> 
<input type="hidden" id="SUBJECT_OFF_OPEN_MONTH" name="SUBJECT_OFF_OPEN_MONTH" value=""/> 
<input type="hidden" id="SUBJECT_OFF_OPEN_DAY" name="SUBJECT_OFF_OPEN_DAY" value=""/>

  <h2>● 강의관리 > <strong>강의제작</strong></h2>
    
    <ul class="lecWritheTab">
      <li><a href="javascript:fn_goLecType('D');" <c:if test="${params.LEC_TYPE_CHOICE eq 'D'}">class="active"</c:if>>단과</a></li>
        <li><a href="javascript:fn_goLecType('J');" <c:if test="${params.LEC_TYPE_CHOICE eq 'J'}">class="active"</c:if>>종합반</a></li>
        <li><a href="javascript:fn_goLecType('P');" <c:if test="${params.LEC_TYPE_CHOICE eq 'P'}">class="active"</c:if>>패키지</a></li>
        <li><a href="javascript:fn_goLecType('Y');" <c:if test="${params.LEC_TYPE_CHOICE eq 'Y'}">class="active"</c:if>>연회원패키지</a></li>
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
          <li>${item.SUBJECT_NM}(${item.USER_NM}) <span><input name="SUBJECT_INFO_ARR" type="radio" value="${item.SUBJECT_CD}#${item.USER_ID}#${item.PAYMENT}"/></span></li>
        </c:forEach>
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
          <th>강사료지급률</th>
          <td><input type="text" id="SUBJECT_TEACHER_PAYMENT" name="SUBJECT_TEACHER_PAYMENT" value="" size="10" maxlength="7" style="background:#FFECEC;IME-MODE:disabled;" onKeyDown="fn_OnlyNumber(this);"/> %</td>
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
          <th><span class="txt05">동영상 기본경로(와이드)</span> </th>
          <td><input type="text" id="SUBJECT_WIDE_DEFAULT_PATH" name="SUBJECT_WIDE_DEFAULT_PATH" value="http://hd.willbes.gscdn.com/" size="100" class="txt05" maxlength="400"/></td>
          <input type="hidden" id="SUBJECT_VOD_DEFAULT_PATH" name="SUBJECT_VOD_DEFAULT_PATH" value="">
        </tr>
    <!-- <tr>
          <th><span class="txt05">동영상 기본경로(500k)</span> </th>
          <td><input type="text" id="SUBJECT_VOD_DEFAULT_PATH" name="SUBJECT_VOD_DEFAULT_PATH" value="http://hd.willbes.gscdn.com/" size="100" class="txt05" maxlength="400"/></td>
        </tr> -->
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
            <!-- <input name="SUBJECT_OPTION_ARR" type="checkbox" value="1"/><span class="txt05">동영상개설(500k)</span> -->
            <input name="SUBJECT_OPTION_ARR" type="checkbox" value="4"/><span class="txt05">동영상개설(와이드)</span>
        <input name="SUBJECT_OPTION_ARR" type="checkbox" value="AA"/><span class="txt06">MP4</span>
        <input name="SUBJECT_OPTION_ARR" type="checkbox" value="2"/><span class="txt07">PMP개설</span>
        <input name="SUBJECT_OPTION_ARR" type="checkbox" value="3"/><span class="txt05">동영상</span>+<span class="txt07">PMP</span>
              <input name="SUBJECT_OPTION_ARR" type="checkbox" value="BB"/><span class="txt05">동영상</span>+<span class="txt02">모바일</span>
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
            <input type="radio" name="SUBJECT_JANG" value="Y"  /><span class="txt03">할인안됨</span>
              <input type="radio" name="SUBJECT_JANG" value="N" checked /><span class="txt04">할인됨</span>
            </td>
      </tr>
      <tr>
      <th>보강비밀번호</th>
      <td><input type="text" id="SUBJECT_PASS" name="SUBJECT_PASS" value="" size="50" maxlength="50" style="IME-MODE:disabled;"/></td>
      </tr>
      <tr>
          <th><span class="txt03">신규강좌여부</span></th>
          <td>
            <input type="radio" name="LEC_GUBUN" value="Y" /><span class="txt03">예</span>
              <input type="radio" name="LEC_GUBUN" value="N" checked /><span class="txt04">아니오</span>
            </td>
      </tr>
      <tr>
          <th><span class="txt03">추천강좌여부</span></th>
          <td>
            <input type="radio" name="REC_GUBUN" value="Y" /><span class="txt03">예</span>
              <input type="radio" name="REC_GUBUN" value="N" checked /><span class="txt04">아니오</span>
            </td>
      </tr>
      <tr>
        <th>노출아이콘설정</th>
        <td>
        <c:forEach items="${ICON_GUBUNs}" var="data" varStatus="status" >
          <input type="checkbox" name="ICON_GUBUN_ARR" class="i_check" value="${data.CODE_VAL}" ><span class="txt0${status.index+3}">${data.CODE_NM}</span>
        </c:forEach>
          <%--<input name="ICON_GUBUN_ARR" type="checkbox" value="B"/><span class="txt05">BEST</span>
          <input name="ICON_GUBUN_ARR" type="checkbox" value="N"/><span class="txt06">NEW</span>
          <input name="ICON_GUBUN_ARR" type="checkbox" value="H"/><span class="txt07">HIT</span> --%>
        </td>
      </tr>
      <tr>
          <th><span class="txt03">사은품종류</span></th>
          <td>
            <input type="radio" name="GIFT_FLAG" value="C" checked/><span class="txt03">쿠폰</span>
              <input type="radio" name="GIFT_FLAG" value="D"  /><span class="txt04">강좌(단과)</span>
              <input type="radio" name="GIFT_FLAG" value="2"  /><span class="txt04">모두</span>
              <input type="radio" name="GIFT_FLAG" value="N"  /><span class="txt04">사용하지 않음</span>
            </td>
      </tr>
      <tr>
          <th><span class="txt03">사은품(강좌)</th>
          <td>
            <table class="table01">
          <tr>
                <th width="25%">
                단과명 : <input type="text" name="GIFT_NAME" id="GIFT_NAME" value="" size="150" readonly="readonly"> <input type="hidden" name="GIFT_LECCODE" id="GIFT_LECCODE" value="">
                <input type="button" onclick="div_lecture_reset()" value="초기화" />
                <BR>
                <label for="SEARCHKIND"></label>
                    <select name="SEARCHKIND" id="SEARCHKIND">
                <option value="">--직종검색--</option>
                <c:forEach items="${kindlist}" var="item" varStatus="loop">
                  <option value="${item.CODE}" <c:if test="${params.SEARCHKIND == item.CODE }">selected="selected"</c:if>>${item.NAME}</option>         
                </c:forEach>
                    </select>
                    <label for="SEARCHFORM"></label>
                    <select name="SEARCHFORM" id="SEARCHFORM">
                <option value="">--학습형태검색--</option>
                <c:forEach items="${formlist}" var="item" varStatus="loop">
                  <option value="${item.CODE}" <c:if test="${params.SEARCHFORM == item.CODE }">selected="selected"</c:if>>${item.NAME}</option>         
                </c:forEach>
                  </select>
                  
                    <select name="SEARCHTYPE" id="SEARCHTYPE">
                <option value="">--전체검색--</option>
                <option value="1" <c:if test="${params.SEARCHTYPE == '1' }">selected="selected"</c:if>>과목</option>
                <option value="2" <c:if test="${params.SEARCHTYPE == '2' }">selected="selected"</c:if>>강의명</option>
                <option value="3" <c:if test="${params.SEARCHTYPE == '3' }">selected="selected"</c:if>>강사명</option>
                <option value="4" <c:if test="${params.SEARCHTYPE == '4' }">selected="selected"</c:if>>강의코드</option>
              </select> 
              <label for="SEARCHTEXT"></label>
              <input type="text" id="SEARCHGIFT" name="SEARCHGIFT" value="${params.SEARCHTEXT}" size="40" title="검색어" onkeypress="fn_Enter()">
                  <input type="button" onclick="div_lecture_search()" value="검색" />
                </th>
          </tr>
              <tr>
            <td>
              <ul class="lecSerial">
              
                <div id="div_lecture_search">
                </div>
                    </ul>          
            </td>
              </tr>
              <tr>
                <td class="tdCenter"><input name="" type="button" value="과목추가"></td>
              </tr>
          </table>
            </td>
      </tr>
      
      <tr>
          <th><span class="txt03">사은품(쿠폰)</th>
          <td>
            <table class="table01">
          <tr>
                <th width="25%">
                쿠폰명 : <input type="text" name="COUPON_NAME" id="COUPON_NAME" value="" size="150" readonly="readonly"> <input type="hidden" name="GIFT_COUPON_CCODE" id="GIFT_COUPON_CCODE" value="">
                <input type="button" onclick="div_coupon_reset()" value="초기화" />
                <BR>
                <select name="GIFT_SEARCHTYPE" id="GIFT_SEARCHTYPE">
                <option value="">--전체검색--</option>
                <option value="1" <c:if test="${params.SEARCHTYPE == '1' }">selected="selected"</c:if>>쿠폰코드</option>
                <option value="2" <c:if test="${params.SEARCHTYPE == '2' }">selected="selected"</c:if>>쿠폰명</option>
              </select> 
              <input type="text" id="SEARCHGIFTTEXT" name="SEARCHGIFTTEXT" value="" size="40" title="검색어" onkeypress="fn_Enter()">
                  <input type="button" onclick="div_coupon_search()" value="검색" />
                </th>
          </tr>
              <tr>
            <td>
              <ul class="lecSerial">
              
                <div id="div_coupon_search">
                </div>
                    </ul>          
            </td>
              </tr>
          </table>
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
  
  div_lecture_search();
  div_coupon_search();
  
  
});

//데이타 초기화
function div_lecture_reset() {
   $('#GIFT_NAME').val("");
   $('#GIFT_LECCODE').val("");
}
function div_coupon_reset() {
   $('#COUPON_NAME').val("");
   $('#GIFT_COUPON_CCODE').val("");
}

function div_lecture_search(){
  
  var gift_leccode_arr =  "";
  var gift_name_arr =  "";
  
  var gift_leccode = "";
  var gift_name = "";
  
  var param =  "&SEARCHKIND=" + $('#SEARCHKIND').val()+"&SEARCHFORM=" + $('#SEARCHFORM').val()+"&SEARCHTYPE=" + $('#SEARCHTYPE').val()+"&SEARCHTEXT=" + $('#SEARCHGIFT').val();
  $.ajax({
        type : "POST"
        ,url : "/lecture/listpop.pop?"
        ,data :  param
        ,success : function (data){
          $('#div_lecture_search').html(data);
          
          // 과목선택시 값 SETTING
          $("input[name='LECCODE_INFO_ARR']").click(function(){
            
            if($("#GIFT_LECCODE").val() != ""){
              gift_leccode = $("#GIFT_LECCODE").val();
            }
            if($("#GIFT_NAME").val() != ""){
              gift_name = $("#GIFT_NAME").val();
            }
            if(gift_name != ""){
              gift_leccode_arr = ""+gift_leccode+", ";
              gift_name_arr = ""+gift_name+", ";
            }
            
            
            $("#GIFT_LECCODE").val(gift_leccode_arr + $("input[name='LECCODE_INFO_ARR']:checked").val().split("#")[0]);
            $("#GIFT_NAME").val(gift_name_arr + $("input[name='LECCODE_INFO_ARR']:checked").val().split("#")[1]);
            //$("#SUBJECT_TEACHER_PAYMENT").val($("input[name='SUBJECT_INFO_ARR']:checked").val().split("#")[2]);
          });
        }
    }); 
}
/*
 * 사은품 나갈때 쿠폰 리스트 가져 오기 위함
 */
function div_coupon_search(){
  
  var gift_couponcd_arr =  "";
  var gift_couponname_arr =  "";
  
  var gift_couponcd = "";
  var gift_couponname = "";
  
  var param =  "&GIFT_SEARCHTYPE=" + $('#GIFT_SEARCHTYPE').val()+"&SEARCHGIFTTEXT=" + $('#SEARCHGIFTTEXT').val();
  $.ajax({
        type : "POST"
        ,url : "/lecture/couponpop.pop?"
        ,data :  param
        ,success : function (data){
          $('#div_coupon_search').html(data);
          
          // 과목선택시 값 SETTING
          $("input[name='COUPON_INFO_ARR']").click(function(){
            
            if($("#GIFT_COUPON_CCODE").val() != ""){
              gift_couponcd = $("#GIFT_COUPON_CCODE").val();
            }
            if($("#COUPON_NAME").val() != ""){
              gift_couponname = $("#COUPON_NAME").val();
            }
            if(gift_couponname != ""){
              gift_coupon_arr = ""+gift_couponcd+", ";
              gift_couponname_arr = ""+gift_couponname+", ";
            }
            //alert("gift_couponcd_arr="+gift_couponcd_arr);
            
            $("#GIFT_COUPON_CCODE").val(gift_couponcd_arr + $("input[name='COUPON_INFO_ARR']:checked").val().split("#")[0]);
            $("#COUPON_NAME").val(gift_couponname_arr + $("input[name='COUPON_INFO_ARR']:checked").val().split("#")[1]);
            //$("#SUBJECT_TEACHER_PAYMENT").val($("input[name='SUBJECT_INFO_ARR']:checked").val().split("#")[2]);
          });
        }
    }); 
}

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
    var icongubunStr = "";
    $("input[name='ICON_GUBUN_ARR']:checked").each(function(idx,el){
      if(icongubunStr!="") icongubunStr += ",";
      icongubunStr += $(this).val();
    });   
    $("#ICON_GUBUN").val(icongubunStr);
    
    $("#frm").attr("action","<c:url value='/lecture/save.do' />");
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