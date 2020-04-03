<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<head>
<meta name="decorator" content="index">
<script type="text/javascript">
//검색
function goList() {
    $('#frm').attr('action','<c:url value="/mock/result/mouigosaList.do"/>').submit();
}

function makeResult() {
	if(confirm("모의고사 점수 결과를 집계하시겠습니까?")){
		var dataString = $("#frm").serialize();
		$.ajax({
		     type: "POST", 
		     url : '<c:url value="/mock/result/makeResult.do"/>',
		     data: dataString,
		     dataType: "text",  
		     async : false,
		     success: function(RES) {
		      if($.trim(RES)=="Y"){           
		        alert("집계되었습니다.");
		        location.reload();
		        return;
		      }
		     },error: function(){
		      alert("실패");
		      return;
		     }
		  });
	}
}

function makeStndDev() {
	if(confirm("모의고사 표준편차를 산출하시겠습니까?")){
		var dataString = $("#frm").serialize();
		$.ajax({
		     type: "POST", 
		     url : '<c:url value="/mock/result/makeMockStndDev.do"/>',
		     data: dataString,
		     dataType: "text",  
		     async : false,
		     success: function(RES) {
		      if($.trim(RES)=="Y"){           
		        alert("산출되었습니다.");
		        location.reload();
		        return;
		      }
		     },error: function(){
		      alert("실패");
		      return;
		     }
		  });
	}
}

function makeAdjust() {
	if(confirm("조정점수를 반영하시겠습니까?")){
		var dataString = $("#frm").serialize();
		$.ajax({
		     type: "POST", 
		     url : '<c:url value="/mock/result/makeMockAdjust.do"/>',
		     data: dataString,
		     dataType: "text",  
		     async : false,
		     success: function(RES) {
		      if($.trim(RES)=="Y"){           
		        alert("처리되었습니다.");
		        location.reload();
		        return;
		      }
		     },error: function(){
		      alert("실패");
		      return;
		     }
		  });
	}
}
</script>
</head>

<form id="frm" name="frm" method="post" >
    <input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
    <input type="hidden" id="MENU_ID" name="MENU_ID" value="${MENU_ID}" />
    <input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
    <input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
    <input type="hidden" id="MENU_NM" name="MENU_NM" value="${MENU_NM}" />
    <input type="hidden" id="MOCKCODE" name="MOCKCODE" value="${params.MOCKCODE}" />

    <!--content -->
    <div id="content">
      <h2>● ${L_MENU_NM} > <strong>${MENU_NM}</strong></h2>
 
    <!--테이블-->
    <table class="table02">
        <caption></caption>
        <colgroup>
        <col width="5%">
        <col width="">
        <col width="">
        <col width="">
        <col width="">
        <col width="">
        <col width="">
        <col width="">
        <col width="">
        </colgroup>
        <thead>
        <tr>
            <th scope="col">No</th>
            <th scope="col">직렬</th>
            <th scope="col">과목명</th>
            <th scope="col">응시인원</th>
            <th scope="col">총점</th>
            <th scope="col">표준편차</th>
            <th scope="col">원점수평균</th>
            <th scope="col">조정점수평균</th>
            <th scope="col">상위10%평균</th>
            <th scope="col">상위3%평균</th>
            <th scope="col">조정점수상위10%평균</th>
            <th scope="col">조정점수상위3%평균</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${not empty mresult}">
          <c:forEach items="${mresult}" var="list" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td>${list.NAME}</td>
                <td>${list.SBJ_NM}</td>
                <td>${list.REQ_USR_NUM}</td>
                <td>${list.SUM_POINT}</td>
                <td>${list.SDV}</td>
                <td>${list.MOCK_AVR_POINT}</td>
                <td>${list.MOCK_ADJ_AVR_POINT}</td>
                <td>${list.MOCK_10_POINT}</td>
                <td>${list.MOCK_3_POINT}</td>
                <td>${list.MOCK_ADJ_10_POINT}</td>
                <td>${list.MOCK_ADJ_3_POINT}</td>
            </tr>
          </c:forEach>
        </c:if>
        <c:if test="${empty mresult}">
            <tr bgColor=#ffffff align=center>
                <td colspan="10">데이터가 존재하지 않습니다.</td>
            </tr>
        </c:if>
        </tbody>
    </table>
    <!--//테이블-->

    <!--버튼-->
    <ul class="boardBtns">
        <li><a href="javascript:goList();">목록</a></li>
        <li><a href="javascript:makeResult();">점수집계</a></li>
        <li><a href="javascript:makeStndDev();">표준편차산출</a></li>
        <li><a href="javascript:makeAdjust();">조정점수반영</a></li>
    </ul>
    <!--//버튼-->

  </div>
  </form>
  <!--//content -->