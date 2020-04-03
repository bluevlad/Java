<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/jsp/academy/common/topInclude.jsp" %>
<html>
<head>
<script type="text/javascript">
//숫자 입력 폼
function chk(obj){
	var val = obj.value;
	if (val) {
		if (val.match(/^\d+$/gi) == null) {
			$('#pageRow').val("");
			$('#pageRow').focus();
			return;
			}
		else {
			if (val < 1) {
				$('#pageRow').val("");
				$('#pageRow').focus();
				return;
				}
			}
		}
}

//엔터키 검색
function fn_checkEnter(){

	$('#SEARCHKEYWORD').keyup(function(e)  {
		if(e.keyCode == 13) {
			goList(1);
		}
	});

	$('#pageRow').keyup(function(e)  {
		if(e.keyCode == 13) {
			goList(1);
		}
	});
}

//All Checkbox Controller
function fn_CheckAll(id, name) {
	if($("#"+id).attr("checked") == "checked") {
		$("input[name="+name+"]").attr("checked", "checked");
	} else {
		$("input[name="+name+"]").removeAttr("checked");
	}
}

//RowNum 숫자만 입력(엔터키 허용)
function fn_RowNumCheck(input) {
	if(event.keyCode == 13){
		fn_Search();
		return;
	}
	if(!fn_IsNumber(input)) {
        alert("숫자만 입력 가능합니다");
        $("#pageRow").val("${params.pageRow}");
    }
}

function fn_IsNumber(input) {
    var chars = "0123456789";
    for (var inx = 0; inx < input.value.length; inx++) {
        if (chars.indexOf(input.value.charAt(inx)) == -1)
            return false;
     }
     return true;
}

//검색
function goList(page) {
	if(typeof(page) == "undefined") $("#currentPage").val(1);
	else $("#currentPage").val(page);

	$('#searchFrm').attr('action','<c:url value="/eventManagement/eventMgtList.do"/>').submit();
}

//등록
function fn_Insert() {
	$('#searchFrm').attr('action','<c:url value="/eventManagement/eventInsertView.do"/>').submit();
}

//삭제
function fn_Delete(){
	if($("input[name='DEL_ARR']:checked").length > 0){
		if(confirm("선택한 항목을 정말 삭제하시겠습니까?")){
			$("#searchFrm").attr("action", "<c:url value='/eventManagement/eventCheckDelete.do' />");
			$("#searchFrm").submit();
		}
	}else{
		alert("선택된 항목이 없습니다");
		return;
	}
}

// 회원 상세
function fn_view(EVENTNO) {
	$("#EVENT_NO").val(EVENTNO);
	$('#searchFrm').attr('action','<c:url value="/eventManagement/eventDetail.do"/>').submit();
}

//결과보기
function fn_result(EVENTNO) {
	$("#EVENT_NO").val(EVENTNO);
	$('#searchFrm').attr('action','<c:url value="/eventManagement/eventResultList.do"/>').submit();
}

</script>
</head>

<!--content -->
<div id="content">
<h2>● ${L_MENU_NM} > <strong>${MENU_NM}</strong></h2>

<form id="searchFrm" name="searchFrm" method="post" onsubmit="fn_checkEnter()">
<input type="hidden" id="TOP_MENU_ID" name="TOP_MENU_ID" value="${TOP_MENU_ID}" />
<input type="hidden" id="MENUTYPE" name="MENUTYPE" value="${MENUTYPE}" />
<input type="hidden" id="L_MENU_NM" name="L_MENU_NM" value="${L_MENU_NM}" />
<input type="hidden" id="MENU_NM" name="MENU_NM" value="${MENU_NM}" />
<input type="hidden" id="currentPage" name="currentPage" value="${params.currentPage}">
<input type="hidden" id="EVENT_NO" name="EVENT_NO" value="" />
	
<!--테이블-->
	<table class="table01">
        <tr>
          <th width="10%">검색</th>
          <td>
			<select name="SEARCHCATEGORY" id="SEARCHCATEGORY">
				<option value="">전체</option>
				<c:if test="${ MENUTYPE eq 'FM_ROOT' }">
					<option value="H" <c:if test="${params.SEARCHCATEGORY eq 'H' }">selected="selected"</c:if>>Hub</option>
				</c:if>
				<c:forEach items="${kindlist}" var="item" varStatus="loop">
					<option value="${item.CODE}" <c:if test="${params.SEARCHCATEGORY eq item.CODE }">selected="selected"</c:if>>${item.NAME}</option>
				</c:forEach>
			</select>

			<label for="SEARCHTYPE"></label>
			<select style="width:80px;" id="SEARCHTYPE" name="SEARCHTYPE">
				<option value="TITLE"  <c:if test="${params.SEARCHTYPE == 'TITLE'}">selected</c:if>>제목</option>
				<option value="CONTENTS_TEXT"  <c:if test="${params.SEARCHTYPE == 'CONTENTS_TEXT'}">selected</c:if>>내용</option>
			</select>
			<label for="SEARCHKEYWORD"></label>
			<input class="i_text"  title="검색" type="text" id="SEARCHKEYWORD" name="SEARCHKEYWORD"  type="text" size="40"  value="${params.SEARCHKEYWORD}" onkeypress="fn_checkEnter()">
          </td>
		  <th width="15%">화면출력건수</th>
		  <td width="20%">
               	<input   size="5" title="검색" type="text" id="pageRow" name="pageRow"  type="text" maxlength="2"  onkeyup="chk(this)" onblur="chk(this)" value="${params.pageRow }" onkeypress="fn_RowNumCheck()">
                <input name="textfield3" type="button" id="textfield3" value="검색" onclick="goList(1)"  >
          </td>
        </tr>
    </table>

    <ul class="boardBtns">
		<li><a href="javascript:fn_Insert();">등록</a></li>
    </ul>

    <p>Total: ${totalCount} | page: ${currentPage} / ${totalPage}</p>

    <table class="table02">
      <tr>
        <th width="60">
			<!-- <input id="allCheck"  value="" type="checkbox" onclick="fn_CheckAll('allCheck', 'DEL_ARR')" />  -->
			번호
		</th>
        <th width="90">등록일</th>
        <th width="100">직렬</th>
        <th width="100">썸네일</th>
        <th>제목</th>
        <th width="90">시작일</th>
        <th width="90">종료일</th>
        <th width="60">적용여부</th>
        <th width="60">상태</th>
        <c:if test="${MENUTYPE eq 'FM_ROOT'}">
         <th width="70">자동문자발송</th>
        </c:if>
        <th width="90">참여인원</th>
        <th width="70">뷰카운트</th>
        <th width="70">결과</th>
      </tr>
      	<c:if test="${not empty list}">
	      <c:forEach var="item" items="${list}" varStatus="status">
		      <tr>
		        <td>
		        	<!-- <input type="checkbox" name="DEL_ARR" value="${item.SEQ},${item.BANNER_IMAGE},${item.BANNER_THUMBNAIL_IMAGE}"/> -->
		        	${totalCount - (( currentPage - 1) * pageRow) - status.index}</td>
		        <td>${item.UPD_DT }</td>
		        <td>${item.CATEGORY_NAME }</td>
		        <td><img src="<c:url value="/imgFileView.do?path=${item.LIST_THUMBNAIL}"/>" width="132" height="67"></td>
		        <td><a href="javascript:fn_view('${item.EVENT_NO}')">${item.TITLE}</a></td>
		        <td>${fn:substring(item.START_DATE, 0, 4)}-${fn:substring(item.START_DATE, 4, 6)}-${fn:substring(item.START_DATE, 6, 8)}</td>
		        <td>${fn:substring(item.END_DATE, 0, 4)}-${fn:substring(item.END_DATE, 4, 6)}-${fn:substring(item.END_DATE, 6, 8)}</td>
		        <td class="txt04">
		        	<c:if test="${ item.OPEN_YN eq 'N' }">미적용</c:if>
		        	<c:if test="${ item.OPEN_YN eq 'Y' }">적용</c:if>
		        </td>
		        <td>${item.STATUS}</td>
	         	<c:if test="${MENUTYPE eq 'FM_ROOT'}">
		         <td>
		         	<c:choose>
			         	<c:when test="${item.OPTION3_YN eq 'Y'}">예</c:when>
			         	<c:otherwise>아니오</c:otherwise>
		         	</c:choose>
		         </td>
		        </c:if>
		        <td>
			        <c:choose>
			        	<c:when test="${item.PEOPLE_CNT > 0}">
			        		${item.PEOPLE_CNT}(신청인원)
			        	</c:when>
			        	<c:when test="${item.OPTION2_CNT > 0}">
			        		${item.OPTION2_CNT}(댓글인원)
			        	</c:when>
			        	<c:otherwise>0</c:otherwise>
			        </c:choose>
		        </td>
		        <td>${item.HIT }</td>
		        <td><a href="javascript:fn_result('${item.EVENT_NO}')">결과</a></td>
		      </tr>
	      </c:forEach>
	    </c:if>

		<c:if test="${empty list}">
			<tr bgColor=#ffffff align=center>
				<td colspan="11">데이터가 존재하지 않습니다.</td>
			</tr>
		</c:if>
    </table>

	<!-- paginate-->
		<c:if test="${not empty list}">
			<c:out value="${pagingHtml}" escapeXml="false" />
		</c:if>
	<!--//paginate-->

    <ul class="boardBtns">
		<li><a href="javascript:fn_Insert();">등록</a></li>
    </ul>

</form>
</div>
<!--//content -->